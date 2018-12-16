package com.hadilabs.jawwalstoreseller.model

data class RepairOrder(val brand:String, val phoneType: String, val problemTitle:String, val problemPreDescription:String, val problemDescription:String) {

    constructor(): this("","","","","")

}