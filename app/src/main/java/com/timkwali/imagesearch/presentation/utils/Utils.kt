package com.timkwali.imagesearch.presentation.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.timkwali.imagesearch.R
import com.timkwali.imagesearch.presentation.ui.imagelist.ImageListViewModel
import android.graphics.Typeface
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.widget.Toast
import com.timkwali.imagesearch.common.Constants

fun Fragment.showSnackBar(
    message: String?,
    duration: Int = 3000,
    view: View? = requireView()
) {
    Snackbar.make(view!!, message!!, duration).show()
}

@SuppressLint("MissingPermission")
fun isNetworkAvailable(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        ?: return false
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val cap = cm.getNetworkCapabilities(cm.activeNetwork) ?: return false
        return cap.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val networks = cm.allNetworks
        for (n in networks) {
            val nInfo = cm.getNetworkInfo(n)
            if (nInfo != null && nInfo.isConnected) return true
        }
    } else {
        val networks = cm.allNetworkInfo
        for (nInfo in networks) {
            if (nInfo != null && nInfo.isConnected) return true
        }
    }
    return false
}

fun showYesNoDialog(
    activity: Activity,
    title: String,
    message: String,
    yesString: String = "YES",
    noString: String = "NO",
    isCancelable: Boolean,
    yesCallback: () -> Unit = {},
    noCallback: () -> Unit = {}
) {
    try {
        var builder: AlertDialog.Builder? = null
        builder =
            if (activity.parent != null) AlertDialog.Builder(activity.parent) else AlertDialog.Builder(
                activity
            )
        val inflater = activity.layoutInflater
        val view: View = inflater.inflate(R.layout.custom_yes_no_dialog, null)
        builder.setView(view)
        val titleTv = view.findViewById<TextView>(R.id.yesNoDialog_title_tv)
        val messageTv = view.findViewById<TextView>(R.id.yesNoDialog_message_tv)
        val yesTv = view.findViewById<TextView>(R.id.yesNoDialog_yes_tv)
        val noTv = view.findViewById<TextView>(R.id.yesNoDialog_no_tv)

        titleTv.text = title
        messageTv.text = message
        yesTv.text = yesString
        noTv.text = noString

        val dialog = builder.create()
        dialog.setCancelable(isCancelable)
        dialog.show()

        yesTv.setOnClickListener {
            dialog.dismiss()
            yesCallback()
        }
        noTv.setOnClickListener {
            noCallback()
            dialog.dismiss()
        }
    }catch (ex: Exception) {
        Log.d("error", ex.toString())
    }
}