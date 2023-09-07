package app.sample.utils


import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.text.TextUtils
import android.util.Base64
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import app.sample.R
import app.sample.utils.Constant.UTC_FORMAT
import app.sample.utils.retrofit.ApiConstants
import render.animations.Attention
import render.animations.Render
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern


/**
 * Kotlin Extension function
 * */
fun Application.generateKeyHash(): String? {
    val packageInfo: PackageInfo
    var key: String? = null
    try {
        val packageName: String = packageName
        packageInfo = packageManager.getPackageInfo(
            packageName,
            PackageManager.GET_SIGNATURES
        )
        for (signature in packageInfo.signatures) {
            val md = MessageDigest.getInstance("SHA")
            md.update(signature.toByteArray())
            key = String(Base64.encode(md.digest(), 0))
        }
    } catch (ignored: Exception) {
    }
    return key
}

/**
 * Kotlin Extension function
 * */
fun Activity.errorToast(message: String) {
    val inflater: LayoutInflater =
        getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    val layout = inflater.inflate(
        R.layout.custom_toast_error_layout, findViewById(R.id.toast_layout_error_root)
    )
    val toast: TextView
    toast = layout.findViewById(R.id.toastTextView)
    toast.text = message

    val customToast = Toast(this)
    customToast.setGravity(Gravity.BOTTOM, 0, 40)
    customToast.duration = Toast.LENGTH_LONG
    customToast.view = layout
    customToast.show()
}


/**
 * Kotlin Extension function
 * */
fun Activity.warningToast(message: String) {
    val inflater: LayoutInflater =
        getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    val layout = inflater.inflate(
        R.layout.custom_toast_warning_layout, findViewById(R.id.toast_layout_warning_root)
    )
    val toast: TextView
    toast = layout.findViewById(R.id.toastTextView)
    toast.text = message

    val customToast = Toast(this)
    customToast.setGravity(Gravity.BOTTOM, 0, 40)
    customToast.duration = Toast.LENGTH_LONG
    customToast.view = layout
    customToast.show()
}

/**
 * Kotlin Extension function
 * */
fun Activity.successToast(message: String) {
    val inflater: LayoutInflater =
        getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    val layout = inflater.inflate(
        R.layout.custom_success_toast, findViewById(R.id.successToast)
    )
    val toast: TextView
    toast = layout.findViewById(R.id.toastMessage)
    toast.text = message
    val customToast = Toast(this)
    customToast.duration = Toast.LENGTH_LONG
    customToast.view = layout
    customToast.show()
}

/**
 * Kotlin Extension function
 * */
fun Fragment.errorToast(message: String) {
    val inflater: LayoutInflater =
        context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    val layout = inflater.inflate(
        R.layout.custom_toast_error_layout,
        activity?.findViewById(R.id.toast_layout_error_root)
    )
    val toast: TextView = layout.findViewById(R.id.toastTextView)
    toast.text = message

    val customToast = Toast(context)
    customToast.setGravity(Gravity.BOTTOM, 0, 40)
    customToast.duration = Toast.LENGTH_LONG
    customToast.view = layout
    customToast.show()
}

fun Fragment.successToast(message: String) {
    val inflater: LayoutInflater =
        context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    val layout = inflater.inflate(
        R.layout.custom_success_toast,
        activity?.findViewById(R.id.successToast)
    )
    val toast: TextView
    toast = layout.findViewById(R.id.toastMessage)
    toast.text = message
    val customToast = Toast(context)
    customToast.setGravity(Gravity.BOTTOM, 0, 40)
    customToast.duration = Toast.LENGTH_LONG
    customToast.view = layout
    customToast.show()
}

fun String.toDate(
    dateFormat: String = UTC_FORMAT,
    timeZone: TimeZone = TimeZone.getTimeZone("UTC")
): Date {
    val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
    parser.timeZone = timeZone
    return parser.parse(this)
}

fun Date.formatTo(dateFormat: String, timeZone: TimeZone = TimeZone.getDefault()): String {
    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    formatter.timeZone = timeZone
    return formatter.format(this)
}

fun View.viewGoneAnimator(context: Context) {
    val slideDown: Animation = AnimationUtils.loadAnimation(context, R.anim.slide_out_down)
    startAnimation(slideDown)
    visibility = View.GONE
}

fun View.viewVisibleAnimator(context: Context) {
    val slideUp: Animation = AnimationUtils.loadAnimation(context, R.anim.slide_up_dialog)
    visibility = View.VISIBLE
    startAnimation(slideUp)
}

fun View.viewGoneAnimatorForHeader(context: Context) {
    val slideUp: Animation = AnimationUtils.loadAnimation(context, R.anim.slide_out)
    visibility = View.GONE
    startAnimation(slideUp)
}

fun View.viewVisibleAnimatorForHeader(context: Context) {
    val slideDown: Animation = AnimationUtils.loadAnimation(context, R.anim.slide_in)
    startAnimation(slideDown)
    visibility = View.VISIBLE
}

fun Activity.startLeftAnimatedActivity(intent: Intent, finish: Boolean) {
    if (finish) {
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    }

    startActivity(intent)
    overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out)
}

fun Fragment.startLeftAnimatedActivity(intent: Intent, finish: Boolean) {
    if (finish) {
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    }

    startActivity(intent)
    activity?.overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out)
}

fun Fragment.startRightAnimatedActivity(intent: Intent) {
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    startActivity(intent)
    activity?.overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out)
}


//fun Activity.showSnackBar()
//{
//    val parentLayout: View = findViewById(android.R.id.content)
//    Snackbar.make(parentLayout, "This is main activity", Snackbar.LENGTH_LONG)
//        .setAction("CLOSE") { }
//        .setActionTextColor(resources.getColor(android.R.color.holo_red_light))
//        .show()
//}


fun Activity.viewGoneAnimator(view: View) {
    val slideDown: Animation = AnimationUtils.loadAnimation(this, R.anim.slide_out_down)
    view.startAnimation(slideDown)
    view.visibility = View.GONE
}

fun Activity.viewVisibleAnimator(view: View) {
    val slideUp: Animation = AnimationUtils.loadAnimation(this, R.anim.slide_up_dialog)
    view.visibility = View.VISIBLE
    view.startAnimation(slideUp)
}


val String.isValidEmail: Boolean
    get() {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this)
            .matches()
    }
val String.isValidMobile: Boolean
    get() {
        val p = Pattern.compile("[0-9]{10}$")

        // Pattern class contains matcher() method
        // to find matching between given number
        // and regular expression
        val m = p.matcher(this)
        return m.find() && m.group() == this
    }
val String.containSpecialCharacter: Boolean
    get() {
        val pattern = Pattern.compile("[a-zA-Z\\s+]*")
        val matcher = pattern.matcher(this)
        return !matcher.matches()
    }
val String.containSpace: Boolean
    get() {
        return this[0].isWhitespace()
    }
val String.mustContainAtleastSixChar: Boolean
    get() {
        val pattern = Pattern.compile(".{5,}\$")
        val matcher = pattern.matcher(this)
        return matcher.matches()
    }
val TextInputEditText.value: String
    get() {
        return this.text.toString()
    }

val EditText.value: String
    get() {
        return this.text.toString()
    }

fun String.toFileUrl(): String = "${ApiConstants.FILE_URL}$this"

fun Int.getDuration(): String {
    val minutes = (this % 3600) / 60
    val seconds = this % 60;
    return String.format("%02d:%02d", minutes, seconds)
}

fun Context.vibrateToNotify() {
    val vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    if (Build.VERSION.SDK_INT >= 26) {
        vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.EFFECT_TICK))
    } else {
        vibrator.vibrate(50)
    }
}

fun View.showPulseAnimation(ctx: Context) {
    Render(ctx).apply {
        setAnimation(Attention().Pulse(this@showPulseAnimation))
        start()
    }
}
