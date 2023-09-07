package app.sample.base

import android.app.Activity
import android.app.Dialog
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import app.sample.ui.login.LoginActivity
import app.sample.R
import app.sample.databinding.FragmentNoInternetBinding
import app.sample.utils.NetworkHelper
import app.sample.utils.custom.CustomTextView
import app.sample.utils.custom.MyCustomDialogWithWarning
import com.kaopiz.kprogresshud.KProgressHUD
import app.sample.utils.SharedPref
import com.leo.simplearcloader.ArcConfiguration
import com.leo.simplearcloader.SimpleArcDialog
import com.leo.simplearcloader.SimpleArcLoader
import render.animations.Render
import javax.inject.Inject


abstract class BaseActivity : AppCompatActivity() {
    private lateinit var hud: KProgressHUD
    private lateinit var mDialog: SimpleArcDialog
    private val mColors = intArrayOf(
        Color.parseColor("#2275C5"),
        Color.parseColor("#48AF43")
    )
    lateinit var render: Render


    @Inject
    lateinit var networkHelper: NetworkHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // making the status bar transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        setContentView(R.layout.activity_toolbar_base)
        hideSoftKeyboard()


        hud = KProgressHUD.create(this)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setLabel(getString(R.string.loading))
            .setCancellable(false)
            .setAnimationSpeed(2)
            .setDimAmount(0.5f)


        val configuration = ArcConfiguration(this)
        configuration.loaderStyle = SimpleArcLoader.STYLE.COMPLETE_ARC
        configuration.animationSpeed = SimpleArcLoader.SPEED_MEDIUM
        configuration.colors = mColors
        configuration.text = getString(R.string.loading)


        val d: Drawable = ColorDrawable(Color.BLACK)
        d.alpha = 178 //alpha 0.7 (0-250 range)

        mDialog = SimpleArcDialog(this, configuration)
        mDialog.setCancelable(false)
        mDialog.window?.setBackgroundDrawable(d)

    }

    fun hideSoftKeyboard() {
        if (currentFocus != null) {
            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }

    fun showLoading() {
//        hud.show()
        // MyCustomDialog.showDialog(this)
//        mDialog.show()
        mDialog.show()
    }

    fun hideLoading() {
//        hud.dismiss()
//        mDialog.hide()
        // MyCustomDialog.hideDialog()
        mDialog.dismiss()
    }

    fun showLoadingWithWarning() {
        MyCustomDialogWithWarning.showDialog(this)
    }

    fun hideLoadingWithWarning() {
        MyCustomDialogWithWarning.hideDialog()
    }

    fun isInternetAvailable(): Boolean {
        return networkHelper.isNetworkConnected()
    }

    fun showNoInternetAlert() {
        val noInternetAlertDialog = Dialog(this, R.style.DialogSlideAnim)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(this),
            R.layout.fragment_no_internet,
            null,
            false
        ) as FragmentNoInternetBinding
        noInternetAlertDialog.setContentView(binding.root)
        noInternetAlertDialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        noInternetAlertDialog.window?.setGravity(Gravity.BOTTOM)
        noInternetAlertDialog.setTitle(null)
//        binding.tvTryAgain.setOnClickListener {
//            mViewModel?.callTryAgain(true)
//            noInternetAlertDialog.dismiss()
//        }
        noInternetAlertDialog.setCanceledOnTouchOutside(true)
        noInternetAlertDialog.setCancelable(true)
        noInternetAlertDialog.show()
    }

    fun Toast.errorToast(context: Context, message: String) {
        val inflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout = inflater.inflate(
            R.layout.custom_toast_error_layout,
            (context as Activity).findViewById(R.id.toast_layout_error_root)
        )
        val toast: CustomTextView
        toast = layout.findViewById(R.id.toastTextView)
        toast.text = message
        setGravity(Gravity.BOTTOM, 0, 40)
        duration = Toast.LENGTH_LONG
        view = layout
        show()
    }

    fun Toast.successToast(context: Context, message: String) {
        val inflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout = inflater.inflate(
            R.layout.custom_success_toast,
            (context as Activity).findViewById(R.id.successToast)
        )
        val toast: CustomTextView
        toast = layout.findViewById(R.id.toastMessage)
        toast.text = message
        setGravity(Gravity.BOTTOM, 0, 40)
        duration = Toast.LENGTH_LONG
        view = layout
        show()
    }

    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onBackPressed() {
        hideKeyboard()
        super.onBackPressed()
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out)
    }

    protected fun startLeftAnimatedActivity(intent: Intent, finish: Boolean) {
        if (finish) {
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }

        startActivity(intent)
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out)
    }

    protected fun startRightAnimatedActivity(intent: Intent) {
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out)
    }

    fun showAlertDialog(dialogBuilder: AlertDialog.Builder.() -> Unit) {
        val builder = AlertDialog.Builder(this)
        builder.dialogBuilder()
        val dialog = builder.create()
        dialog.show()
    }

    fun AlertDialog.Builder.positiveButton(
        text: String = "Yes",
        handleClick: (which: Int) -> Unit = {}
    ) {
        this.setPositiveButton(text) { dialogInterface, which -> handleClick(which) }
    }

    fun AlertDialog.Builder.negativeButton(
        text: String = "No",
        handleClick: (which: Int) -> Unit = {}
    ) {
        this.setNegativeButton(text) { dialogInterface, which -> handleClick(which) }
    }


    open fun forceLogout(sharedPref: SharedPref) {
        val notificationManager =
            this.applicationContext
                .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.cancelAll()

        sharedPref.clearPreference()
        startLeftAnimatedActivity(
            Intent(
                this,
                LoginActivity::class.java
            ), true
        )
    }

}