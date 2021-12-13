package com.timkwali.imagesearch.common

import android.annotation.SuppressLint
import android.util.Log
import com.timkwali.imagesearch.domain.model.ImageItem
import com.timkwali.imagesearch.domain.model.ResponseModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

@SuppressLint("CheckResult")
inline fun <ResultType, RequestType>networkBoundResource(
    crossinline query: () -> Observable<ResultType>,
    crossinline fetch: () -> Observable<RequestType>,
    crossinline saveFetchResult: (RequestType) -> Completable,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = Observable.create<Resource<ResultType>> { emitter ->
    val data = query().subscribeOn(Schedulers.io()).blockingFirst(null)

    if(shouldFetch(data)) {
        emitter.onNext(Resource.Loading(data))
        try {
            val fetchResult = fetch().subscribeOn(Schedulers.io()).blockingFirst(null)
            saveFetchResult(fetchResult).subscribeOn(Schedulers.io()).subscribe()
            query().subscribeOn(Schedulers.io()).subscribe(
                { emitter.onNext(Resource.Success(it, null)) },
                { emitter.onNext(Resource.Error(it.localizedMessage ?: "Error fetching local data", null)) }
            )
        } catch (throwable: Throwable) {
            query().subscribeOn(Schedulers.io()).subscribe {
                emitter.onNext(Resource.Error(throwable.localizedMessage ?: "Error fetching local data", it))
            }
        }
    } else {
        query().subscribeOn(Schedulers.io()).subscribe {
            emitter.onNext(Resource.Success(it, null))
        }
    }
}