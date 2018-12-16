package com.hadilabs.jawwalstoreseller.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.res.ResourcesCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.firebase.firestore.ListenerRegistration
import com.hadilabs.jawwalstoreseller.*
import com.hadilabs.jawwalstoreseller.recyclerview.item.OrderNotificationItem
import com.hadilabs.jawwalstoreseller.recyclerview.item.RepairOrderItem
import com.hadilabs.jawwalstoreseller.util.FirestoreUtil
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.OnItemClickListener
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.activity_accepted_offers.*
import kotlinx.android.synthetic.main.activity_new_repair.*
import kotlinx.android.synthetic.main.activity_offered_repair_orders.*
import kotlinx.android.synthetic.main.fragment_notification.*
import kotlinx.android.synthetic.main.fragment_notification.view.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import java.io.Serializable


class NotificationFragment : Fragment() {

    private var newOrdersSection: Section? = null
    private var offeredOrdersSection: Section? = null
    private var acceptedOffersSection: Section? = null

    private var shouldInitNewRecyclerView = true
    private var shouldInitOfferedRecyclerView = true
    private var shouldInitAcceptedRecyclerView = true


    private lateinit var respondsListenerRegistration: ListenerRegistration


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        val view = inflater.inflate(R.layout.fragment_notification, container, false)

        FirestoreUtil.addOrdersNotificationListener ( context!!, this::updateNewRecyclerView )



        /*view.textView_new_order_title.text = "جديدة"
        view.textView_offered_orders_title.text = "قدمت عرضك"
        view.textView_accepted_orders_title.text = "عروضك الموافق عليها"



        view.cardView_new_order.setOnClickListener {
            startActivity<NewRepairActivity>()
        }


        view.cardView_offered_order.setOnClickListener {
            startActivity<OfferedRepairOrdersActivity>()
        }

        view.cardView_accepted_order.setOnClickListener {
            startActivity<AcceptedOffersActivity>()
        }*/





        return view
    }


    private fun updateNewRecyclerView( items: List<Item> ){

        //textView_new_orders_number.text = items.size.toString()

        fun init(){
            recycler_view_notification_order.apply {

                ResourcesCompat.getDrawable(resources, R.drawable.rect_rounded_red, null)

                layoutManager = LinearLayoutManager( context )

                adapter = GroupAdapter<ViewHolder>().apply {

                    newOrdersSection = Section(items)

                    add(newOrdersSection!!)

                    setOnItemClickListener( onItemClick )

                }
            }

            shouldInitNewRecyclerView = false
        }

        fun update(){
            if (newOrdersSection != null) {
                newOrdersSection!!.update(items)
            }
        }

        if ( shouldInitNewRecyclerView ){
            init()
        }else{
            update()
        }

    }

    /*private fun updateOfferedRecyclerView( items: List<Item> ){


        textView_oferred_orders_number.text = items.size.toString()

        *//*fun init(){
            Log.e("FIXSTORESEAPP", items.size.toString())

            recycler_view_offered_order.apply {
                layoutManager = LinearLayoutManager( context )

                adapter = GroupAdapter<ViewHolder>().apply {

                    offeredOrdersSection = Section(items)

                    add( offeredOrdersSection!! )

                    setOnItemClickListener( onItemClick )

                }
            }

            shouldInitOfferedRecyclerView = false
        }


        fun update(){
            if (offeredOrdersSection != null) {
                offeredOrdersSection!!.update(items)
            }
        }

        if ( shouldInitOfferedRecyclerView ){
            init()
        }else{
            update()
        }
*//*
    }


    private fun updateAcceptedRecyclerView( items: List<Item> ){

        textView_accepted_orders_number.text = items.size.toString()

        *//*
        fun init(){
            Log.e("FIXSTORESEAPP", items.size.toString())

            recycler_view_accepted_offers.apply {
                layoutManager = LinearLayoutManager( context )

                adapter = GroupAdapter<ViewHolder>().apply {

                    offeredOrdersSection = Section(items)

                    add( offeredOrdersSection!! )

                    setOnItemClickListener( onItemClick )

                }
            }

            shouldInitOfferedRecyclerView = false
        }


        fun update(){
            if (offeredOrdersSection != null) {
                offeredOrdersSection!!.update(items)
            }
        }

        if ( shouldInitOfferedRecyclerView ){
            init()
        }else{
            update()
        }*//*

    }
*/


    private val onItemClick = OnItemClickListener{ item, view ->

        Log.e("FIXSTOREORDER", item.toString())

        if ( item is OrderNotificationItem ){

            if ( item.orderStatus == "جديد" ) {

                startActivity<RepairOrderDetailsActivity>(
                        "phoneType" to item.repairOrder.phoneType,
                        "problemTitle" to "(" + item.repairOrder.problemTitle + ")",
                        "problemPreDescription" to "- " + item.repairOrder.problemPreDescription,
                        "problemDescription" to "- " + item.repairOrder.problemDescription,
                        "repairOrderId" to item.orderId
                )

            }

        }

    }





}