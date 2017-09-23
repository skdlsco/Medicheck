package medical.medicheck.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_result.*
import medical.medicheck.Adapter.NavRecyclerViewAdapter
import medical.medicheck.Models.NavItem
import medical.medicheck.R
import medical.medicheck.ResultDialog
import org.jetbrains.anko.find

class ResultActivity : AppCompatActivity() {

    private val ask = arrayOf(R.id.result_ask1_img, R.id.result_ask2_img,
            R.id.result_ask3_img, R.id.result_ask4_img)
    private var askSelected = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        result_drawer_btn.setOnClickListener {
            drawer_layout.openDrawer(nav_view)
        }
        val nav_items = ArrayList<NavItem>()
        nav_items.add(NavItem(false, R.drawable.ic_sidebar_dianositcrecord, R.drawable.ic_sidebar_dianosticrecord_white, "진단 기록"))
        nav_items.add(NavItem(false, R.drawable.ic_sidebar_mediinfo, R.drawable.ic_sidebar_mediinfo_white, "약 정보"))
        nav_items.add(NavItem(false, R.drawable.ic_sidebar_nearhospital, R.drawable.ic_sidebar_nearhospital_white, "근처 병원"))
        val nav_adapter = NavRecyclerViewAdapter(this, nav_items)
        nav_adapter.itemClick = object : NavRecyclerViewAdapter.ItemClick {
            override fun onItemClick(view: View?, position: Int) {
            }
        }
        nav_recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        nav_recyclerView.setHasFixedSize(true)
        nav_recyclerView.adapter = nav_adapter


        val sick = intent.getStringExtra("sick")
        val term = intent.getStringExtra("term")
        val start = intent.getStringExtra("start")
        result_sick.text = sick
        result_sickStart.text = start
        result_sickTerm.text = term
        result_ask1_img.setOnClickListener {
            getAsk(0)
        }

        result_ask2_img.setOnClickListener {
            getAsk(1)
        }
        result_ask3_img.setOnClickListener {
            getAsk(2)
        }
        result_ask4_img.setOnClickListener {
            getAsk(3)
        }

        result_btn_next.setOnClickListener {
            val ask_ = when (askSelected) {
                0 -> "같은 증상, 같은 병원 재방문"
                1 -> "같은 증상, 다른 병원 처음방문"
                2 -> "다른 증상, 같은 병원 재방문"
                3 -> "처음 방문"
                else -> "처음 방문"
            }
            intent.putExtra("ask", ask_)
            val dialog = ResultDialog(this, ask_, term, sick, start)
            dialog.show()
        }

        result_ask1_img.isSelected = true
    }

    fun getAsk(pos: Int) {
        askSelected = pos
        for (i in 0 until 4) {
            find<ImageView>(ask[i]).isSelected = false
        }
        find<ImageView>(ask[pos]).isSelected = true
    }
}
