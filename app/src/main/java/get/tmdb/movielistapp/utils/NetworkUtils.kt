package get.tmdb.movielistapp.utils

import android.content.Context
import android.net.ConnectivityManager


class NetworkUtils {

    companion object {

        @Suppress("DEPRECATION")
        @JvmStatic
        fun isNetworkAvailable(context: Context?): Boolean {
            if (context == null) return false

            val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            return if (manager != null) {
                val isMobile =
                    manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null
                            && manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)?.isConnectedOrConnecting ?: false
                val isWifi =
                    manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI) != null
                            && manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)?.isConnectedOrConnecting ?: false
                isMobile || isWifi
            } else {
                false
            }
        }
    }
}