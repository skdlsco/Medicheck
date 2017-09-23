package medical.medicheck.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_epidemic.*
import medical.medicheck.Models.NavItem
import medical.medicheck.Adapter.NavRecyclerViewAdapter
import medical.medicheck.R

class EpidemicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_epidemic)

        epidemic_drawer_btn.setOnClickListener {
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
    }
}
