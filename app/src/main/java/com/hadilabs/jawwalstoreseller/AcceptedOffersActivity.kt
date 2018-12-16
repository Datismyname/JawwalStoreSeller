package com.hadilabs.jawwalstoreseller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.firebase.firestore.ListenerRegistration
import com.hadilabs.jawwalstoreseller.recyclerview.item.RepairOrderItem
import com.hadilabs.jawwalstoreseller.util.FirestoreUtil
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.OnItemClickListener
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.activity_accepted_offers.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class AcceptedOffersActivity : AppCompatActivity() {

    private var offeredOrdersSection: Section? = null
    private var shouldInitOfferedRecyclerView = true

    private lateinit var respondsListenerRegistration: ListenerRegistration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accepted_offers)


        FirestoreUtil.addAcceptedOfferesListener( this::updateRecyclerView )

    }



    private fun updateRecyclerView( items: List<Item> ){

        fun init(){
            Log.e("FIXSTORESEAPP", items.size.toString())

            recycler_view_accepted_offers.apply {
                layoutManager = LinearLayoutManager(context)

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

    }



    private val onItemClick = OnItemClickListener{ item, view ->


        if ( item is RepairOrderItem){

            toast("RepairOrderItem: $item, order ID: ${item.orderId}")

            startActivity<RepairOrderDetailsActivity>(
                    "phoneType" to item.repairOrder.phoneType,
                    "problemTitle" to "("+item.repairOrder.problemTitle+")",
                    "problemPreDescription" to "- " + item.repairOrder.problemPreDescription,
                    "problemDescription" to "- " + item.repairOrder.problemDescription,
                    "repairOrderId" to item.orderId
            )

        }

    }




}
