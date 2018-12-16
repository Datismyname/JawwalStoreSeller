package com.hadilabs.jawwalstoreseller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.hadilabs.jawwalstoreseller.recyclerview.item.RepairOrderItem
import com.hadilabs.jawwalstoreseller.util.FirestoreUtil
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.OnItemClickListener
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.activity_new_repair.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class NewRepairActivity : AppCompatActivity() {

    private var newOrdersSection: Section? = null
    private var shouldInitNewRecyclerView = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_repair)

        FirestoreUtil.addNewRepairOrdersListener( this::updateRecyclerView )


    }




    private fun updateRecyclerView( items: List<Item> ){

        fun init(){
            recycler_view_new_order.apply {
                layoutManager = LinearLayoutManager(context)

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
