package com.hadilabs.jawwalstoreseller.util

import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.hadilabs.jawwalstoreseller.model.*
import com.hadilabs.jawwalstoreseller.recyclerview.item.*
import com.xwray.groupie.kotlinandroidextensions.Item

object FirestoreUtil {


    private val firestoreInstence: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    private val currentUserDocRef: DocumentReference get() = firestoreInstence.document("repairshops/${FirebaseAuth.getInstance().currentUser?.uid ?: throw NullPointerException("UID is null")}")

    private val chatChannelsCollectionReference = firestoreInstence.collection("chatChannels")

    private val repairOrdersCollectionReference = firestoreInstence.collection("repairOrders")


    fun initCurrentUserIfFirstTime(onComplete: () -> Unit){

        currentUserDocRef.get().addOnSuccessListener { documentSnapshot ->
            if (!documentSnapshot.exists()){

                val newUser = Store(FirebaseAuth.getInstance().currentUser?.displayName ?: "", 0.0, null, mutableListOf())

                currentUserDocRef.set(newUser).addOnSuccessListener {
                    onComplete()
                }

            }else{
                onComplete()
            }
        }

    }



    fun getCurrentUser( onComplete: (Store) -> Unit ){

        currentUserDocRef.get().addOnSuccessListener {

            onComplete(it.toObject( Store::class.java )!!)

        }
    }



    fun addNewRepairOrdersListener(onListen: (List<Item>) -> Unit ): ListenerRegistration {

        return repairOrdersCollectionReference.whereEqualTo("orderStatus.codeNumber",0 ).addSnapshotListener{querySnapshot, firebaseFirestoreException ->

            if (firebaseFirestoreException != null) {
                Log.e("FIXSTOREORDER", "stores responds listener error.", firebaseFirestoreException)
                return@addSnapshotListener
            }


            val items = mutableListOf<Item>()

            querySnapshot!!.forEach {
                items.add( RepairOrderItem( it.toObject( RepairOrder::class.java ),"*جديد",0.0, it.id ) )
            }


            onListen(items)
        }

    }

    fun addOfferedRepairOrdersListener(onListen: (List<Item>) -> Unit ): ListenerRegistration {

        return repairOrdersCollectionReference.whereEqualTo("orderStatus.codeNumber",1 ).addSnapshotListener{querySnapshot, firebaseFirestoreException ->

            if (firebaseFirestoreException != null) {
                Log.e("FIXSTOREORDER", "stores responds listener error.", firebaseFirestoreException)
                return@addSnapshotListener
            }


            val items = mutableListOf<Item>()

            querySnapshot!!.forEach {
                items.add( RepairOrderItem( it.toObject( RepairOrder::class.java ),"تم تقديم عرض",0.0, it.id ) )

            }


            onListen(items)
        }

    }

    fun addAcceptedOfferesListener(onListen: (List<Item>) -> Unit ): ListenerRegistration {

        return repairOrdersCollectionReference.whereEqualTo("orderStatus.codeNumber",2 ).whereEqualTo("acceptedStoreId", FirebaseAuth.getInstance().currentUser?.uid ).addSnapshotListener{querySnapshot, firebaseFirestoreException ->

            if (firebaseFirestoreException != null) {
                Log.e("FIXSTOREORDER", "stores responds listener error.", firebaseFirestoreException)
                return@addSnapshotListener
            }


            val items = mutableListOf<Item>()

            querySnapshot!!.forEach {
                items.add( RepairOrderItem( it.toObject( RepairOrder::class.java ),"تم قبول عرضك",0.0, it.id ) )

            }


            onListen(items)
        }

    }

    fun addOrdersNotificationListener( onListen: (List<Item>) -> Unit ): ListenerRegistration {

        return repairOrdersCollectionReference.whereLessThanOrEqualTo("orderStatus.codeNumber",2.0 ).addSnapshotListener{querySnapshot, firebaseFirestoreException ->

            if (firebaseFirestoreException != null) {
                Log.e("FIXSTOREORDER", "stores responds listener error.", firebaseFirestoreException)
                return@addSnapshotListener
            }


            val items = mutableListOf<Item>()
            var orderStatus: Map<*, *>
            var storesIds: ArrayList<*>?
            var codeNumber:Long

            querySnapshot!!.forEach {

                orderStatus = it.get( "orderStatus" ) as Map<*, *>

                storesIds = orderStatus["storesIds"] as ArrayList<*>?


                codeNumber = orderStatus["codeNumber"] as Long

                when ( codeNumber.toDouble() ){

                    0.0 -> items.add( OrderNotificationItem( it.toObject( RepairOrder::class.java ),0.0, it.id, null ) )


                    1.0 -> {

                        if (  storesIds!!.contains(  FirebaseAuth.getInstance().currentUser?.uid ) )
                            items.add( OrderNotificationItem( it.toObject( RepairOrder::class.java ),0.0, it.id, null ) )

                    }

                    2.0 -> {

                        if ( it.getString("acceptedStoreId") == FirebaseAuth.getInstance().currentUser?.uid )
                            items.add( OrderNotificationItem( it.toObject( RepairOrder::class.java ),0.0, it.id, FirebaseAuth.getInstance().currentUser?.uid ) )

                    }



                }


            }


            onListen(items)
        }

    }


    fun addOpenOrdersListener( onListen: (List<Item>) -> Unit ): ListenerRegistration {

        return repairOrdersCollectionReference.whereGreaterThanOrEqualTo("orderStatus.codeNumber",3.0 ).whereLessThanOrEqualTo("orderStatus.codeNumber",5.0 ).addSnapshotListener{querySnapshot, firebaseFirestoreException ->

            if (firebaseFirestoreException != null) {
                Log.e("FIXSTOREORDER", "stores responds listener error.", firebaseFirestoreException)
                return@addSnapshotListener
            }


            val items = mutableListOf<Item>()

            querySnapshot!!.forEach {

                items.add( OpenOrdersListItem( it.toObject( RepairOrder::class.java ),3.5, it.id, FirebaseAuth.getInstance().currentUser?.uid ) )

            }

            onListen(items)
        }

    }



    fun addHistoryListener( onListen: (List<Item>) -> Unit ): ListenerRegistration {

        return repairOrdersCollectionReference.whereEqualTo("orderStatus.codeNumber",6.0 ).addSnapshotListener{querySnapshot, firebaseFirestoreException ->

            if (firebaseFirestoreException != null) {
                Log.e("FIXSTOREORDER", "stores responds listener error.", firebaseFirestoreException)
                return@addSnapshotListener
            }


            val items = mutableListOf<Item>()

            querySnapshot!!.forEach {

                items.add( OpenOrdersListItem( it.toObject( RepairOrder::class.java ),4.0, it.id, FirebaseAuth.getInstance().currentUser?.uid ) )

            }

            onListen(items)
        }

    }


    fun updateRepairOrder( orderId:String, status:Map<String,Any>, onComplete: () -> Unit){

        repairOrdersCollectionReference.document( orderId ).update( status )

        onComplete()

    }

    fun addRepairOffer( orderId:String,store:Store, price:Double, onComplete: () -> Unit){


        repairOrdersCollectionReference.document( orderId )
                .collection("orderOffers")
                .document( FirebaseAuth.getInstance().currentUser!!.uid )
                .set( RepairOffer( store, price ) )
                .addOnSuccessListener {

                    updateRepairOrder( orderId, mapOf( "orderStatus" to mutableMapOf( "codeName" to "offered",  "codeNumber" to 1, "storesIds" to FieldValue.arrayUnion( FirebaseAuth.getInstance().currentUser!!.uid ) ) ) ){
                        //TODO: send notification to the user
                        onComplete()
                    }

                }

    }















    fun getOrCreateChatChannel( otherUserId: String, orderId: String, onComplete:(channelId: String) -> Unit ){

        currentUserDocRef.collection( "engagedChatChannels" ).document( orderId ).get().addOnSuccessListener {

            // if chat channel already exists which mean we already chatting with other user
            if ( it.exists() ){
                onComplete( it["channelId"] as String ) // get the field "channelId" from DocumentSnapshot
                return@addOnSuccessListener
            }

            // otherwise if chat channel doesn't exists create new chat channel

            val currentUserId = FirebaseAuth.getInstance().currentUser!!.uid

            val newChannel = chatChannelsCollectionReference.document()

            newChannel.set( mapOf( "userIds" to mutableListOf( currentUserId, otherUserId )  , "orderId" to orderId ) )

            // save chat channel id in both users who chat together

            currentUserDocRef
                    .collection( "engagedChatChannels" )
                    .document( orderId )
                    .set( mapOf( "channelId" to newChannel.id, "customerId" to otherUserId ) ) // the newChannel.id is the id of channel document inside Firestore

            firestoreInstence.collection("users").document( otherUserId )
                    .collection( "engagedChatChannels" )
                    .document( orderId )
                    .set( mapOf( "channelId" to newChannel.id, "repairShopId" to currentUserId) )

            onComplete( newChannel.id )


        }

    }


    fun addChatMessagesListener(channelId: String, context: Context, onListen: (List<Item>) -> Unit ) : ListenerRegistration {

        return chatChannelsCollectionReference.document( channelId ).collection("messages")
                .orderBy("time")
                .addSnapshotListener { querySnapshot, firebaseFirestoreException ->

                    if ( firebaseFirestoreException != null ){
                        Log.e("FIRESTORE", "ChatMessageListener error.", firebaseFirestoreException)
                        return@addSnapshotListener
                    }

                    val items = mutableListOf<Item>()

                    querySnapshot!!.forEach {

                        if ( it["type"] == MessageType.TEXT ){

                            items.add( TextMessageItem( it.toObject(TextMessage::class.java), context ) )

                        }else{

                            items.add( ImageMessageItem( it.toObject(ImageMessage::class.java), context ) )

                        }

                    }

                    onListen(items)


                }

    }

    fun sendMessage(message: Message, channelId: String ){

        chatChannelsCollectionReference.document( channelId )
                .collection( "messages" )
                .add( message )

    }










    /*************** region FCM ***************/

    fun getFCMRegistrationTokens( onComplete: (tokens: MutableList<String>) -> Unit ){

        currentUserDocRef.get().addOnSuccessListener {
            val store = it.toObject( Store::class.java )!!

            onComplete(store.registrationTokens)

        }

    }

    fun setFCMRegistrationTokens( registrationTokens: MutableList<String> ){

        currentUserDocRef.update( mapOf( "registrationTokens" to registrationTokens ) )

    }


    /*************** end region FCM ***************/



}