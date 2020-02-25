package com.example.xoro

import android.content.Intent
import android.icu.text.IDNA
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Full screen

        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)

        //Wychodzenie z aplikacji
        BT_Wyjdz.setOnClickListener{
            finish()
            System.exit(0)
        }

        //Odpalanie pomocy
        BT_Pomoc.setOnClickListener {
            var pomoc: Intent = Intent(applicationContext, info::class.java)
            startActivity(pomoc)
        }

        //Odpalanie gry
        BT_Start.setOnClickListener {
            var graj: Intent = Intent(applicationContext, Game::class.java)
            startActivity(graj)
        }
    }


}
