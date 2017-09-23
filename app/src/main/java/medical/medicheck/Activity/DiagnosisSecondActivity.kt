package medical.medicheck.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_diagnosis_second.*
import medical.medicheck.Adapter.NavRecyclerViewAdapter
import medical.medicheck.Models.NavItem
import medical.medicheck.R
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class DiagnosisSecondActivity : AppCompatActivity() {

    private var startSelected = 0
    private var termSelected = 0
    private val start = arrayOf(R.id.diagnosis_second_start1_img, R.id.diagnosis_second_start2_img,
            R.id.diagnosis_second_start3_img, R.id.diagnosis_second_start4_img, R.id.diagnosis_second_start5_img)
    private val term = arrayOf(R.id.diagnosis_second_term1_img, R.id.diagnosis_second_term2_img,
            R.id.diagnosis_second_term3_img, R.id.diagnosis_second_term4_img, R.id.diagnosis_second_term5_img)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diagnosis_second)

        diagnosisSecond_drawer_btn.setOnClickListener {
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

        diagnosis_second_start1_img.isSelected = true
        diagnosis_second_term1_img.isSelected = true

        diagnosis_second_start1_img.setOnClickListener {
            getStart(0)
        }
        diagnosis_second_start2_img.setOnClickListener {
            getStart(1)
        }
        diagnosis_second_start3_img.setOnClickListener {
            getStart(2)
        }
        diagnosis_second_start4_img.setOnClickListener {
            getStart(3)
        }
        diagnosis_second_start5_img.setOnClickListener {
            getStart(4)
        }


        diagnosis_second_term1_img.setOnClickListener {
            getTerm(0)
        }
        diagnosis_second_term2_img.setOnClickListener {
            getTerm(1)
        }
        diagnosis_second_term3_img.setOnClickListener {
            getTerm(2)
        }
        diagnosis_second_term4_img.setOnClickListener {
            getTerm(3)
        }
        diagnosis_second_term5_img.setOnClickListener {
            getTerm(4)
        }
        diagnosisSecond_btn_next.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("sick", getIntent().getStringExtra("sick"))
            val start = when (startSelected) {
                0 -> "오늘"
                1 -> "1일 ~ 1주일 전"
                2 -> "1주일 ~ 2주일 전"
                3 -> "2주일 ~ 한달 전"
                4 -> "2주일 ~ 한달 전"
                else -> "1일 ~ 1주일 전"
            }
            intent.putExtra("start", start)
            val term = when (termSelected) {
                0 -> "1분 이내"
                1 -> "1분 ~ 30분"
                2 -> "30분 ~"
                3 -> "특정 상황"
                4 -> "불규칙적"
                else -> "특정 상황"
            }
            intent.putExtra("term", term)
            startActivity(intent)
        }
    }

    private fun getStart(pos: Int) {
        startSelected = pos
        for (i in 0 until 5) {
            find<ImageView>(start[i]).isSelected = false
        }
        find<ImageView>(start[pos]).isSelected = true
    }

    private fun getTerm(pos: Int) {
        termSelected = pos
        for (i in 0 until 5) {
            find<ImageView>(term[i]).isSelected = false
        }
        find<ImageView>(term[pos]).isSelected = true
    }
}
