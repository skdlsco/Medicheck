package medical.medicheck.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import medical.medicheck.R
import org.jetbrains.anko.startActivity
import java.util.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val timer: Timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                startActivity<MainActivity>()
                finish()
            }
        }, 2000)
    }
}
