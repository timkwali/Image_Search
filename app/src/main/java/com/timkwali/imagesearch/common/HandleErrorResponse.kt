package com.timkwali.imagesearch.common

import com.timkwali.imagesearch.common.Constants

object HandleErrorResponse {
    fun setErrorMessage(errorCode: Int): String {
       return when(errorCode) {
            Constants.emptyResultCode -> Constants.emptyResultMessage
            Constants.noResourceCode -> Constants.noResourceMessage
            Constants.timeoutCode -> Constants.timeoutMessage
            Constants.serverErrorCode -> Constants.serverErrorMessage
           else -> Constants.errorMessage
       }
    }
}