package com.hadilabs.jawwalstoreseller.recyclerview.item

import android.graphics.Color
import com.hadilabs.jawwalstoreseller.R
import com.hadilabs.jawwalstoreseller.model.RepairOrder
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_order.*
import org.jetbrains.anko.textColor

class RepairOrderItem(val repairOrder: RepairOrder,val orderStatus:String, val userRating:Double, val orderId: String ) : Item(){

    override fun bind(viewHolder: ViewHolder, position: Int) {

        viewHolder.textView_phone_type.text = repairOrder.phoneType
        viewHolder.textView_problem_predescription.text = "(${repairOrder.problemTitle})"
        //viewHolder.textView_problem_predescription.text = repairOrder.problemPreDescription
        viewHolder.textView_order_status.text = orderStatus

        if (orderStatus == "تم تقديم عرض"){
            viewHolder.textView_order_status.textColor = "#FF992C".toColor()

        }

        chooseRatingIcon( viewHolder, userRating )



    }

    override fun getLayout() = R.layout.item_order

    fun String.toColor(): Int = Color.parseColor(this)


    private fun chooseRatingIcon(viewHolder: ViewHolder, rating: Double ){

        val first: Int
        val second: Int
        val third: Int
        var forth: Int
        var fifth: Int

        when (rating) {


            in 0.0..0.4 -> {

                first = R.drawable.ic_star_border_gary_24dp
                second = R.drawable.ic_star_border_gary_24dp
                third = R.drawable.ic_star_border_gary_24dp
                forth = R.drawable.ic_star_border_gary_24dp
                fifth = R.drawable.ic_star_border_gary_24dp

            }

            in 0.5..0.9 -> {

                first = R.drawable.ic_star_half_24dp
                second = R.drawable.ic_star_border_gary_24dp
                third = R.drawable.ic_star_border_gary_24dp
                forth = R.drawable.ic_star_border_gary_24dp
                fifth = R.drawable.ic_star_border_gary_24dp

            }

            in 1.0..1.4 -> {

                first = R.drawable.ic_star_24dp
                second = R.drawable.ic_star_border_gary_24dp
                third = R.drawable.ic_star_border_gary_24dp
                forth = R.drawable.ic_star_border_gary_24dp
                fifth = R.drawable.ic_star_border_gary_24dp

            }

            in 1.5..1.9 -> {

                first = R.drawable.ic_star_24dp
                second = R.drawable.ic_star_half_24dp
                third = R.drawable.ic_star_border_gary_24dp
                forth = R.drawable.ic_star_border_gary_24dp
                fifth = R.drawable.ic_star_border_gary_24dp

            }

            in 2.0..2.4 -> {

                first = R.drawable.ic_star_24dp
                second = R.drawable.ic_star_24dp
                third = R.drawable.ic_star_border_gary_24dp
                forth = R.drawable.ic_star_border_gary_24dp
                fifth = R.drawable.ic_star_border_gary_24dp

            }

            in 2.5..2.9 -> {

                first = R.drawable.ic_star_24dp
                second = R.drawable.ic_star_24dp
                third = R.drawable.ic_star_half_24dp
                forth = R.drawable.ic_star_border_gary_24dp
                fifth = R.drawable.ic_star_border_gary_24dp

            }

            in 3.0..3.4 -> {

                first = R.drawable.ic_star_24dp
                second = R.drawable.ic_star_24dp
                third = R.drawable.ic_star_24dp
                forth = R.drawable.ic_star_border_gary_24dp
                fifth = R.drawable.ic_star_border_gary_24dp

            }

            in 3.5..3.9 -> {

                first = R.drawable.ic_star_24dp
                second = R.drawable.ic_star_24dp
                third = R.drawable.ic_star_24dp
                forth = R.drawable.ic_star_half_24dp
                fifth = R.drawable.ic_star_border_gary_24dp

            }

            in 4.0..4.4 -> {

                first = R.drawable.ic_star_24dp
                second = R.drawable.ic_star_24dp
                third = R.drawable.ic_star_24dp
                forth = R.drawable.ic_star_24dp
                fifth = R.drawable.ic_star_border_gary_24dp

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