package com.timkwali.imagesearch.common

object HandleErrorResponse {
    fun setErrorMessage(errorCode: Int): String? {
       return when(errorCode) {
            Constants.successCode -> null
            Constants.emptyResultCode -> Constants.emptyResultMessage
            Constants.noResourceCode -> Constants.noResourceMessage
            Constants.timeoutCode -> Constants.timeoutMessage
            Constants.tooManyRequestsCode -> Constants.tooManyRequestsMessage
            Constants.serverErrorCode -> Constants.serverErrorMessage
           else -> Constants.errorMessage
       }
    }
}