package com.mebank.views

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.mebank.R
import timber.log.Timber

/*Helper class to show progress*/
class Progress {
    private lateinit var dialog: Dialog
    private val TAG = Progress::class.java.simpleName
    private var activity: Activity? = null

    constructor(activity: Activity) {
        this.activity = activity
        Timber.tag(TAG)
    }

    /*Function to show dialog*/
    fun showDialog() {
        Timber.i("In show dialog")
        if (activity != null && !activity!!.isFinishing) {
            dialog = Dialog(activity!!)

            dialog.let {
                it.requestWindowFeature(Window.FEATURE_NO_TITLE)
                it.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                it.setCancelable(false)
                it.setCanceledOnTouchOutside(false)
                it.setContentView(R.layout.dialog_progress)
                it.show()
            }
        }
    }

    /*Function to hide dialog*/
    fun hideDialog() {
        Timber.i("In hide dialog")

        if (activity != null && !activity!!.isFinishing) {
            dialog.dismiss()
        }
    }
}