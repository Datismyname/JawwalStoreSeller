package com.hadilabs.jawwalstoreseller

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.firebase.firestore.ListenerRegistration
import com.hadilabs.jawwalstoreseller.fragment.NotificationFragment
import com.hadilabs.jawwalstoreseller.fragment.OpenOrdersListFragment
import com.hadilabs.jawwalstoreseller.fragment.OrdersHistoryFragment
import com.hadilabs.jawwalstoreseller.recyclerview.item.RepairOrderItem
import com.hadilabs.jawwalstoreseller.util.FirestoreUtil
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.OnItemClickListener
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        replaceFragment( NotificationFragment() )


        navigation.setOnNavigationItemSelectedListener{

            when (it.itemId) {

                R.id.navigation_notifications -> {
                    replaceFragment( NotificationFragment() )
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_open_orders -> {
                    replaceFragment( OpenOrdersListFragment() )
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_history -> {
                    replaceFragment( OrdersHistoryFragment() )
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_store -> {

                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_account -> {

                    return@setOnNavigationItemSelectedListener true
                }

            }

            return@setOnNavigationItemSelectedListener false

        }



    }


    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_layout, fragment)
                .commit()
    }













}
