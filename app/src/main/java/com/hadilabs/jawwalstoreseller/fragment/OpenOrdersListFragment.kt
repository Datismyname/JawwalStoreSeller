package com.hadilabs.jawwalstoreseller.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hadilabs.jawwalstoreseller.AppConstants
import com.hadilabs.jawwalstoreseller.ChatActivity

import com.hadilabs.jawwalstoreseller.R
import com.hadilabs.jawwalstoreseller.RepairConstants
import com.hadilabs.jawwalstoreseller.recyclerview.item.OpenOrdersListItem
import com.hadilabs.jawwalstoreseller.util.FirestoreUtil
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.OnItemClickListener
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.fragment_open_orders_list.*
import org.jetbrains.anko.support.v4.startActivity


class OpenOrdersListFragment : Fragment() {

    private var openOrdersSection: Section? = null
    private var shouldInitRecyclerView = true


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        FirestoreUtil.addOpenOrdersListener( this::updateRecyclerView )

        return inflater.inflate(R.layout.fragment_open_orders_list, container, false)
    }




    private fun updateRecyclerView( items: List<Item> ){


        fun init(){
            recycler_view_open_orders_list.apply {

                ResourcesCompat.getDrawable(resources, R.drawable.rect_rounded_red, null)

                layoutManager = LinearLayoutManager( context )

                adapter = GroupAdapter<ViewHolder>().apply {

                    openOrdersSection = Section(items)

                    add(openOrdersSection!!)

                    setOnItemClickListener( onItemClick )

                }
            }

            shouldInitRecyclerView = false
        }

        fun update(){
            if (openOrdersSection != null) {
                openOrdersSection!!.update(items)
            }
        }

        if ( shouldInitRecyclerView ){
            init()
        }else{
            update()
        }

    }




    private val onItemClick = OnItemClickListener { item, view ->

        if( item is OpenOrdersListItem ){

            startActivity<ChatActivity>(
                    AppConstants.CUSTOMER_NAME to "بعدين",
                    AppConstants.CUSTOMER_ID to item.repairOrder.userId!!,
                    RepairConstants.REPAIR_ORDER_ID to item.orderId,
                    RepairConstants.REPAIR_ORDER_STATUS to item.repairOrder.orderStatus!!["codeNumber"]!!.toString()
            )

        }


    }





}
