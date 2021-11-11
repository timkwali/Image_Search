package com.timkwali.imagesearch.common

object Constants {
    const val BASE_URL = "https://pixabay.com"
    const val API_KEY = "24146487-3ae0514e3d6d73cc960d1dc70"
    const val IMAGE_TYPE = "photo"

    const val successCode = 200
    const val emptyResultCode = 204
    const val noResourceCode = 404
    const val timeoutCode = 408
    const val serverErrorCode = 500

    const val emptyResultMessage = "The request returned an empty result."
    const val noResourceMessage = "No resource found for request."
    const val timeoutMessage = "The request timed out."
    const val serverErrorMessage = "Internal Server error."
    const val errorMessage = "An error occurred."
    const val noSearchResults = "There are no results for this search term. Try another one."
}