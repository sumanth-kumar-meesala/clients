package com.mebank.activities

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mebank.R
import com.mebank.views.Progress

open class BaseActivity : AppCompatActivity() {

    private val TAG = BaseActivity::class.java.simpleName
    private var progress: Progress? = null

    protected fun showProgress() {
        if (this != null && !this.isFinishing) {
            if (progress == null) {
                progress = Progress(this)
            }

            progress?.showDialog()
        }
    }

    /*Function to hide progress*/
    protected fun hideProgress() {
        progress?.hideDialog()
    }

    /*Function to show toast
    * id : string resource id
    * */
    protected fun showToast(messageId: Int) {
        Toast.makeText(this, messageId, Toast.LENGTH_SHORT).show()
    }

    /*Function to show toast
   * id : message string value
   * */
    protected fun showToast(message: String?) {
        message?.let {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    /*Function to check network and display toast if there is no network  */
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

    /*Override finish method to display animation on exit */
    override fun finish() {
        super.finish()
        overridePendingTransitionExit()
    }

    /* Override start activity to display animation
    * intent : Intent of activity to start
    * */
    override fun startActivity(intent: Intent) {
        super.startActivity(intent)
        overridePendingTransitionEnter()
    }

    /*Method to override pending transition for animation on exit activity*/
    private fun overridePendingTransitionEnter() {
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
    }

    /*Method to override pending transition for animation on start activity*/
    private fun overridePendingTransitionExit() {
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }
}