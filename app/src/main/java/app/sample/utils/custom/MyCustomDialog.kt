package app.sample.utils.custom

import android.R
import android.app.Dialog
import android.content.Context
import android.view.Window


object MyCustomDialog {
    private var myDialog: Dialog? = null
    fun showDialog(context : Context) {
        myDialog?.dismiss()
        myDialog = Dialog(context, R.style.Theme_Translucent_NoTitleBar_Fullscreen)
        myDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        myDialog?.setContentView(app.sample.R.layout.my_custom_dialog_layout)
        myDialog?.window?.setBackgroundDrawableResource(app.sample.R.color.transparent)
        myDialog?.show()
    }

    fun hideDialog() {
        myDialog?.dismiss()
    }

}