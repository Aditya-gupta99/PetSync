package com.sparklead.petsync.routes

object HttpRoutes {

    private const val BASE_URL = "http://192.168.151.21:5000/petSync"

    const val ON_OFF = "$BASE_URL/onOff"

    const val FEED_HISTORY = "$BASE_URL/allList"
}