package com.hadilabs.jawwalstoreseller.recyclerview.item

import android.graphics.Color
import android.view.Gravity
import android.widget.FrameLayout
import com.google.firebase.auth.FirebaseAuth
import com.hadilabs.jawwalstoreseller.R
import com.hadilabs.jawwalstoreseller.model.Message
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_text_message.*
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.textColor
import org.jetbrains.anko.wrapContent
import java.text.SimpleDateFormat

abstract class MessageItem(private val message: Message): Item(){

    override fun bind(viewHolder: ViewHolder, position: Int) {
        setTimeText(viewHolder)
        setMessageRootGravity(viewHolder)
    }

    private fun setTimeText(viewHolder: ViewHolder){
        val dateFormat = SimpleDateFormat.getDateTimeInstance( SimpleDateFormat.SHORT, SimpleDateFormat.SHORT )
        viewHolder.textView_message_time.text = dateFormat.format( message.time )
    }

    private fun setMessageRootGravity(viewHolder: ViewHolder){
        if (message.senderId == FirebaseAuth.getInstance().currentUser?.uid ){

            viewHolder.message_root.apply {

                backgroundResource = R.drawable.rect_round_primary_color
                this.layoutParams = FrameLayout.LayoutParams(wrapContent, wrapContent, Gravity.START)

            }


            viewHolder.textView_message_time.textColor = Color.WHITE
            if ( message.type == "TEXT" )
                viewHolder.textView_message_text.textColor = Color.WHITE

        }else{

            viewHolder.message_root.apply {

                backgroundResource = R.drawable.rect_round_white
                this.layoutParams = FrameLayout.LayoutParams(wrapContent, wrapContent, Gravity.END)

            }

        }
    }

}