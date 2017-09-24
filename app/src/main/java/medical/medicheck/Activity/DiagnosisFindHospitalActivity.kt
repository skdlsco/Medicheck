package medical.medicheck.Activity

import android.Manifest
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.support.v4.app.ActivityCompat
import android.content.pm.PackageManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_diagnosis_find_hospital.*
import medical.medicheck.Adapter.NavRecyclerViewAdapter
import medical.medicheck.GpsInfo
import medical.medicheck.Models.NavItem
import medical.medicheck.R


class DiagnosisFindHospitalActivity : AppCompatActivity(), OnMapReadyCallback {

    val PERMISSIONS_REQUEST = 100

    private var mMap: GoogleMap? = null
    private var gpsInfo: GpsInfo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diagnosis_find_hospital)


        diagnosisFindHospital_drawer_btn.setOnClickListener {
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

        gpsInfo = GpsInfo(this)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), PERMISSIONS_REQUEST)
        }

        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.main_map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        diagnosisFindHospital_btn_next.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("sick", getIntent().getStringExtra("sick"))
            intent.putExtra("start", getIntent().getStringExtra("start"))
            intent.putExtra("term", getIntent().getStringExtra("term"))
            startActivity(intent)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap!!.setMinZoomPreference(10f)
        setLocation()
    }

    override fun onDestroy() {
        gpsInfo!!.stopUsingGPS()
        super.onDestroy()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSIONS_REQUEST -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED || grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                setLocation()
            } else {
                Toast.makeText(this, "권한이 없습니다.", Toast.LENGTH_SHORT).show()
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), PERMISSIONS_REQUEST)
            }
        }
    }

    private fun setLocation() {
        var isSuccess = false
        if (gpsInfo!!.isGetLocation) {
            if (!(gpsInfo!!.latitude == 0.0 && gpsInfo!!.longitude == 0.0)) {
                isSuccess = true
            }
        }
        if (isSuccess) {
            mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(gpsInfo!!.latitude, gpsInfo!!.longitude), 17.0f))
        } else {
            Toast.makeText(this, "자신의 위치 불러오기 실패", Toast.LENGTH_SHORT).show()
        }
    }

}
