package medical.medicheck.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.TypedValue
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_diagnosis.*
import medical.medicheck.Adapter.NavRecyclerViewAdapter
import medical.medicheck.Models.NavItem
import medical.medicheck.R
import medical.medicheck.Adapter.SpinnerAdapter
import org.jetbrains.anko.startActivity

class DiagnosisActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diagnosis)

        diagnosis_drawer_btn.setOnClickListener {
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

        val spinnerTopItem = arrayOf("머리", "눈", "코", "입", "귀", "팔", "다리", "손", "발", "가슴", "허리", "배")
        val spinnerTopAdapter = SpinnerAdapter(spinnerTopItem, this)
        diagnosis_spinner_top.adapter = spinnerTopAdapter

        val topPopup = Spinner::class.java.getDeclaredField("mPopup")
        topPopup.isAccessible = true

        val topPopupWindow = topPopup.get(diagnosis_spinner_top) as android.widget.ListPopupWindow

        topPopupWindow.height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70f, resources.displayMetrics).toInt()


        val spinnerBottomItem = arrayOf(arrayOf("두통", "기타"), arrayOf("각막", "간지러움", "다래끼", "기타"),
                arrayOf("콧물", "호흡 곤란", "상습적 코피", "기타"), arrayOf("치통", "악취", "기타"), arrayOf("청각 장애", "출혈", "기타"),
                arrayOf("오금", "간지러움", "팔꿈치", "두드러기", "기타"),
                arrayOf("간지러움", "무릎", "기타"), arrayOf("물집", "화상", "손가락", "기타"),
                arrayOf("물집", "간지러움", "아킬레스건", "기타"), arrayOf("통증", "답답함", "기타"),
                arrayOf("불편함", "간지러움", "두드러기", "기타"), arrayOf("복통", "기타"))

        val spinnerBottomAdapter = SpinnerAdapter(spinnerBottomItem[0], this)
        diagnosis_spinner_bottom.adapter = spinnerBottomAdapter
        diagnosis_spinner_bottom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                spinnerBottomItem[p2]
            }
        }
        val bottomPopup = Spinner::class.java.getDeclaredField("mPopup")
        bottomPopup.isAccessible = true

        val bottomPopupWindow = bottomPopup.get(diagnosis_spinner_bottom) as android.widget.ListPopupWindow
        bottomPopupWindow.height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70f, resources.displayMetrics).toInt()


        diagnosis_spinner_top.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                spinnerBottomAdapter.items = spinnerBottomItem[p2]
                spinnerBottomAdapter.notifyDataSetChanged()
            }
        }

        diagnosis_btn_next.setOnClickListener {
            val intent = Intent(this, DiagnosisSecondActivity::class.java)
            intent.putExtra("sick", "" + diagnosis_spinner_top.selectedItem + ", " + diagnosis_spinner_bottom.selectedItem)
            startActivity(intent)
        }
    }
}
