package app.sample.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.text.Html
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import app.sample.R
import app.sample.utils.custom.CustomTextView
import com.varunjohn1990.iosdialogs4android.IOSDialog
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.RandomAccessFile
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*


object AppUtil {

    private const val INSTALLATION = "INSTALLATION"

    /**----- Custom Toast -----**/
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

    /**----- Internet Check -----**/
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    /**----- Custom Alert Dialog -----**/

    interface CallBackCreateWalletClick {
        fun onClickCreatePlayList()
    }

    fun loadHtmlText(textView: TextView, data: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.text = Html.fromHtml(
                data,
                Html.FROM_HTML_MODE_COMPACT
            );
        } else {
            textView.text = Html.fromHtml(data);
        }
    }


    @Synchronized
    fun isFirstLaunch(context: Context): Boolean {
        var sID: String? = null
        var launchFlag = false
        if (sID == null) {
            val installation = File(context.filesDir, INSTALLATION)
            try {
                if (!installation.exists()) {
                    launchFlag = true
                    writeInstallationFile(installation)
                }
                sID = readInstallationFile(installation)
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
        return launchFlag
    }

    @Throws(IOException::class)
    private fun readInstallationFile(installation: File): String? {
        val f = RandomAccessFile(installation, "r") // read only mode
        val bytes = ByteArray(f.length().toInt())
        f.readFully(bytes)
        f.close()
        return String(bytes)
    }

    @Throws(IOException::class)
    private fun writeInstallationFile(installation: File) {
        val out = FileOutputStream(installation)
        val id = UUID.randomUUID().toString()
        out.write(id.toByteArray())
        out.close()
    }


    fun actualDurationDMY(days: Int): String {
        val years = days / 365
        var remainder = days - years * 365
        val months = remainder / 30
        remainder -= months * 30
        val day = remainder
        var retDuration = ""
        if (years >= 1) {
            retDuration = "$years year"
        }
        if (months >= 1) {
            retDuration = "$retDuration$months month"
        }
        if (day > 0) {
            retDuration = "$retDuration$day days"
        }

        return retDuration
    }


    fun showDetailsDialog(context: Context, message: String, title: String) {

        IOSDialog.Builder(context)
            //.title("Kawawa Sounds")
            .title("$title")
            .cancelable(false)
            .message("$message")
            .enableAnimation(true)
            .positiveButtonText("Okay")
            .positiveClickListener { iosDialog -> iosDialog!!.dismiss() }
            .build()
            .show()

    }


    fun roundOffDecimal(number: Double): Double? {
        return try {
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.CEILING
            df.format(number).toDouble()
        } catch (e: Exception) {
            0.0
        }
    }

    fun getCurrentTimeZoneTime(
        context: Context,
        dateStr: String,
        inputPattern: String,
        outputPattern: String,
        splitDateTime: Boolean
    ): String {
        //val dateStr = "Jul 16, 2013 12:08:59 AM"
        try {
            val df = SimpleDateFormat(inputPattern, Locale.ENGLISH)
            val dfOutPut = SimpleDateFormat(outputPattern, Locale.ENGLISH)
            df.timeZone = TimeZone.getTimeZone("UTC")
            val date: Date = df.parse(dateStr)
            df.timeZone = TimeZone.getDefault()
            val formattedDate: String = dfOutPut.format(date)
            var finalDateTime = ""
            if (splitDateTime) {
                finalDateTime =
                    context.resources.getString(R.string.date) + " " + formattedDate.split(" ")[0] + " " + context.resources.getString(
                        R.string.time
                    ) + " " + formattedDate.split(
                        " "
                    )[1] + " " + formattedDate.split(
                        " "
                    )[2]
                return finalDateTime
            }

            return formattedDate
        } catch (e: Exception) {
            e.printStackTrace()
            return "Invalid Date Format"
        }
    }

}

