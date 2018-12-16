package com.hadilabs.jawwalstoreseller.model

data class RepairOffer( val store:Store, val price:Double) {

    constructor(): this( Store("",0.0,null, mutableListOf() ), 0.0 )

}