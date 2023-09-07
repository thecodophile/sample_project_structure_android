package app.sample.base

import android.app.Activity
import android.app.Dialog
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import app.sample.ui.login.LoginActivity
import app.sample.R
import app.sample.databinding.FragmentNoInternetBinding
import app.sample.utils.AppUtil
import app.sample.utils.custom.CustomTextView
import com.kaopiz.kprogresshud.KProgressHUD
import app.sample.utils.SharedPref
import app.sample.utils.startLeftAnimatedActivity
import com.leo.simplearcloader.ArcConfiguration
import com.leo.simplearcloader.SimpleArcDialog
import com.leo.simplearcloader.SimpleArcLoader


abstract class BaseFragment(fragmentLayout: Int) : Fragment(fragmentLayout) {
    private lateinit var hud: KProgressHUD
    private lateinit var mDialog : SimpleArcDialog
    private val mColors = intArrayOf(
        Color.parseColor("#2275C5"),
        Color.parseColor("#48AF43")
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hud = KProgressHUD.create(activity)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setLabel(getString(R.string.loading))
            .setCancellable(false)
            .setAnimationSpeed(2)
            .setDimAmount(0.5f)


        val configuration = ArcConfiguration(context)
        configuration.loaderStyle = SimpleArcLoader.STYLE.COMPLETE_ARC
        configuration.animationSpeed = SimpleArcLoader.SPEED_MEDIUM
        configuration.colors =  mColors
        configuration.text = getString(R.string.loading)


        val d: Drawable = ColorDrawable(Color.BLACK)
        d.alpha = 178 //alpha 0.7 (0-250 range)

        mDialog = SimpleArcDialog(activity, configuration)
        mDialog.setCancelable(false)
        mDialog.window?.setBackgroundDrawable(d)


    }

    fun showLoading() {
//        hud.show()
        mDialog.show()
    }

    fun hideLoading() {
//        hud.dismiss()
        mDialog.dismiss()
    }

    fun isInternetAvailable(): Boolean {
        return AppUtil.isNetworkAvailable(requireContext())
    }

    fun showNoInternetAlert() {
        val noInternetAlertDialog = Dialog(requireContext(), R.style.DialogSlideAnim)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(requireContext()),
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

    open fun forceLogout(sharedPref: SharedPref) {
        val notificationManager =
            requireActivity().applicationContext
                .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.cancelAll()

        sharedPref.clearPreference()
        startLeftAnimatedActivity(
            Intent(
                requireActivity(),
                LoginActivity::class.java
            ), true
        )
    }

}

