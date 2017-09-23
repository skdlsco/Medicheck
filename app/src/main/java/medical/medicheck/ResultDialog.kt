package medical.medicheck

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.dialog_reserve.*
import android.widget.TextView
import org.jetbrains.anko.backgroundColor


/**
 * Created by eka on 2017. 9. 23..
 */
class ResultDialog(context: Context, val ask: String, val term: String, val sick: String, val start: String) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_reserve)

        result_sick.text = sick
        result_sickStart.text = start
        result_sickTerm.text = term
        result_ask.text = ask

        dialog_cancel.setOnClickListener {
            onBackPressed()
        }
        dialog_reserve.setOnClickListener {
            val toast = Toast.makeText(context, "예약되었습니다.", Toast.LENGTH_SHORT)
            val v = toast.view.findViewById<TextView>(android.R.id.message) as TextView
            v.setTextColor(Color.WHITE)
            v.backgroundColor = Color.TRANSPARENT
            toast.show()
            dismiss()
            fin?.finish()
        }
    }
    var fin: Finish? = null
    interface Finish {
        fun finish()
    }
}