package com.hadilabs.jawwalstoreseller.model

import com.google.firebase.firestore.GeoPoint

data class Store(val name:String, val rating:Double, val location:GeoPoint?, val registrationTokens: MutableList<String>) {

    constructor(): this("",0.0,null, mutableListOf() )

}