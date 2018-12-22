package com.hadilabs.jawwalstoreseller.recyclerview.item

import android.graphics.Color
import com.google.firebase.auth.FirebaseAuth
import com.hadilabs.jawwalstoreseller.R
import com.hadilabs.jawwalstoreseller.model.RepairOrder
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_order_notification.*
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.textColor

class OrderNotificationItem(
        val repairOrder: RepairOrder,
        val userRating:Double, val orderId: String, val acceptedStoreId: String?) : Item(){

    override fun bind(viewHolder: ViewHolder, position: Int) {

        viewHolder.textView_phone_type.text = repairOrder.phoneType

        viewHolder.textView_problem_title.text = "(${repairOrder.problemTitle})"

        val storesIds = repairOrder.offeredStoresIds as ArrayList<*>?


        when( repairOrder.orderStatus!!["codeName"].toString() ){

            "new" ->   setStatusUI( "جديد", Color.RED, R.drawable.rect_rounded_red, viewHolder )



            "offered" ->

                if ( !storesIds.isNullOrEmpty() )

                    if ( storesIds.contains( FirebaseAuth.getInstance().currentUser?.uid ) )
                        setStatusUI( "تم تقديم عرض", orange(), R.drawable.rect_round_orange, viewHolder )

                    else
                        setStatusUI( "جديد", Color.RED, R.drawable.rect_rounded_red, viewHolder )




            "accepted" ->   setStatusUI( "تم قبول عرضك!", green(), R.drawable.rect_rounded_green, viewHolder )





        }


        chooseRatingIcon( viewHolder, userRating )



    }

    override fun getLayout() = R.layout.item_order_notification


    private fun setStatusUI(status: String, color: Int, backgroundResource: Int ,viewHolder: ViewHolder){

        viewHolder.textView_order_status.text = status
        viewHolder.textView_order_status.textColor = color
        viewHolder.relativeLayout_prefix.backgroundResource = backgroundResource //

    }

    fun green(): Int = Color.parseColor("#297b00" )
    fun orange() : Int = Color.parseColor("#ff9500")



    private fun chooseRatingIcon(viewHolder: ViewHolder, rating: Double ){

        val first: Int
        val second: Int
        val third: Int
        val forth: Int
        val fifth: Int

        when (rating) {


            in 0.0..0.4 -> {

                first = R.drawable.ic_star_border_24dp
                second = R.drawable.ic_star_border_24dp
                third = R.drawable.ic_star_border_24dp
                forth = R.drawable.ic_star_border_24dp
                fifth = R.drawable.ic_star_border_24dp

            }

            in 0.5..0.9 -> {

                first = R.drawable.ic_star_half_24dp
                second = R.drawable.ic_star_border_24dp
                third = R.drawable.ic_star_border_24dp
                forth = R.drawable.ic_star_border_24dp
                fifth = R.drawable.ic_star_border_24dp

            }

            in 1.0..1.4 -> {

                first = R.drawable.ic_star_24dp
                second = R.drawable.ic_star_border_24dp
                third = R.drawable.ic_star_border_24dp
                forth = R.drawable.ic_star_border_24dp
                fifth = R.drawable.ic_star_border_24dp

            }

            in 1.5..1.9 -> {

                first = R.drawable.ic_star_24dp
                second = R.drawable.ic_star_half_24dp
                third = R.drawable.ic_star_border_24dp
                forth = R.drawable.ic_star_border_24dp
                fifth = R.drawable.ic_star_border_24dp

            }

            in 2.0..2.4 -> {

                first = R.drawable.ic_star_24dp
                second = R.drawable.ic_star_24dp
                third = R.drawable.ic_star_border_24dp
                forth = R.drawable.ic_star_border_24dp
                fifth = R.drawable.ic_star_border_24dp

            }

            in 2.5..2.9 -> {

                first = R.drawable.ic_star_24dp
                second = R.drawable.ic_star_24dp
                third = R.drawable.ic_star_half_24dp
                forth = R.drawable.ic_star_border_24dp
                fifth = R.drawable.ic_star_border_24dp

            }

            in 3.0..3.4 -> {

                first = R.drawable.ic_star_24dp
                second = R.drawable.ic_star_24dp
                third = R.drawable.ic_star_24dp
                forth = R.drawable.ic_star_border_24dp
                fifth = R.drawable.ic_star_border_24dp

            }

            in 3.5..3.9 -> {

                first = R.drawable.ic_star_24dp
                second = R.drawable.ic_star_24dp
                third = R.drawable.ic_star_24dp
                forth = R.drawable.ic_star_half_24dp
                fifth = R.drawable.ic_star_border_24dp

            }

            in 4.0..4.4 -> {

                first = R.drawable.ic_star_24dp
                second = R.drawable.ic_star_24dp
                third = R.drawable.ic_star_24dp
                forth = R.drawable.ic_star_24dp
                fifth = R.drawable.ic_star_border_24dp

            }

            in 4.5..4.9 -> {

                first = R.drawable.ic_star_24dp
                second = R.drawable.ic_star_24dp
                third = R.drawable.ic_star_24dp
                forth = R.drawable.ic_star_24dp
                fifth = R.drawable.ic_star_half_24dp

            }

            5.0 ->  {

                first = R.drawable.ic_star_24dp
                second = R.drawable.ic_star_24dp
                third = R.drawable.ic_star_24dp
                forth = R.drawable.ic_star_24dp
                fifth = R.drawable.ic_star_24dp

            }

            else -> {

                first = R.drawable.ic_baseline_stars_24px
                second = R.drawable.ic_baseline_stars_24px
                third = R.drawable.ic_baseline_stars_24px
                forth = R.drawable.ic_baseline_stars_24px
                fifth = R.drawable.ic_baseline_stars_24px

            }



        }

        viewHolder.imageView_rating.setImageResource( first )
        viewHolder.imageView_rating_2.setImageResource( second )
        viewHolder.imageView_rating_3.setImageResource( third )
        viewHolder.imageView_rating_4.setImageResource( forth )
        viewHolder.imageView_rating_5.setImageResource( fifth )


    }




}