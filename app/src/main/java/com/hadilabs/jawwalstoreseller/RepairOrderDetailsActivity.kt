package com.hadilabs.jawwalstoreseller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.hadilabs.jawwalstoreseller.util.FirestoreUtil
import kotlinx.android.synthetic.main.activity_repair_order_details.*
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.startActivity

class RepairOrderDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repair_order_details)

        textView_phone_type.text = intent.getStringExtra("phoneType")
        textView_phone_problem_title.text = intent.getStringExtra("problemTitle")
        textView_problem_predescription.text = intent.getStringExtra("problemPreDescription")
        textView_problem_description.text = intent.getStringExtra("problemDescription")


        button_send_order.setOnClickListener {
            val progressDialog = indeterminateProgressDialog("يتم ارسال عرضك الآن")

            val price = editText_offer_price.text.toString().toDoubleOrNull()

            FirestoreUtil.getCurrentUser { store->



                FirestoreUtil.addRepairOffer(intent.getStringExtra("repairOrderId"), store , price!!){
                    startActivity<MainActivity>()

                    progressDialog.dismiss()
                }

            }
        }



    }
}
