package com.mebank.activities

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mebank.R
import com.mebank.views.Progress

open class BaseActivity : AppCompatActivity() {

    private var progress: Progress? = null

    protected fun showProgress() {
        if (this != null && !this.isFinishing) {
            if (progress == null) {
                progress = Progress(this)
            }

            progress?.showDialog()
        }
    }

    protected fun hideProgress() {
        progress?.hideDialog()
    }

    protected fun showToast(messageId: Int) {
        Toast.makeText(this, messageId, Toast.LENGTH_SHORT).show()
    }

    protected fun showToast(message: String?) {
        message?.let {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    protected fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        val isAvailable = activeNetworkInfo != null && activeNetworkInfo.isConnected

        if (!isAvailable) {
            showToast(R.string.no_internet_connection)
        }

        return isAvailable
    }

    override fun finish() {
        super.finish()
        overridePendingTransitionExit()
    }

    override fun startActivity(intent: Intent) {
        super.startActivity(intent)
        overridePendingTransitionEnter()
    }

    private fun overridePendingTransitionEnter() {
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
    }

    private fun overridePendingTransitionExit() {
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }
}