package org.taipei.opendata.celery.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


import org.taipei.opendata.celery.network.model.*


interface ApiVege {

    // http://data.taipei/opendata/datalist/apiAccess?scope=resourceAquire&rid=c71394d5-62a1-430b-8000-e0a980687090
    @GET("/opendata/datalist/apiAccess")
    fun list(@Query("scope") scope: String, @Query("rid")  requestId: String) : Observable<VegeResponse>
}