package com.hadilabs.jawwalstoreseller.recyclerview.item

import android.graphics.Color
import com.hadilabs.jawwalstoreseller.R
import com.hadilabs.jawwalstoreseller.model.RepairOrder
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_open_orders_list.*

import org.jetbrains.anko.textColor

class OpenOrdersListItem(
        val repairOrder: RepairOrder,
        val userRating:Double, val orderId: String, val acceptedStoreId: String?) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {

        viewHolder.textView_phone_type.text = repairOrder.phoneType

        viewHolder.textView_problem_title.text = "(${repairOrder.problemTitle})"


        viewHolder.textView_order_status.textColor = "#297b00".toColor()

        when( repairOrder.orderStatus!!["codeName"].toString() ){

            "opened by seller" -> viewHolder.textView_order_status.text = "تحت الصيانة"

            "wait for delivery" -> viewHolder.textView_order_status.text = "في انتظار استلام الجوال"

            "delivered" -> viewHolder.textView_order_status.text = "انتهى الطلب"


        }




        chooseRatingIcon(viewHolder, userRating)


    }

    override fun getLayout() = R.layout.item_open_orders_list

    fun String.toColor(): Int = Color.parseColor(this)


    private fun chooseRatingIcon(viewHolder: ViewHolder, rating: Double) {

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

            5.0 -> {

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

        viewHolder.imageView_rating.setImageResource(first)
        viewHolder.imageView_rating_2.setImageResource(second)
        viewHolder.imageView_rating_3.setImageResource(third)
        viewHolder.imageView_rating_4.setImageResource(forth)
        viewHolder.imageView_rating_5.setImageResource(fifth)


    }
}

