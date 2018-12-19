package com.hadilabs.jawwalstoreseller.model

import java.util.*
import kotlin.collections.HashMap

data class RepairOrder(
        val brand: String,
        val phoneType: String,
        val problemTitle: String,
        val problemPreDescription: String,
        val problemDescription: String,
        val orderStatus: HashMap<String,*>?,
        val userId: String?,
        val orderTime: Date?,
        val acceptedStoreName: String?,
        val acceptedStoreId: String?,
        val repairOrderId: String?
) {

    constructor(): this( "","","","","",null,null,null,null, null, null)

}