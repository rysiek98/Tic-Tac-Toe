package com.example.xoro

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game.*


class Game() : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        var mediaPlayer: MediaPlayer? = MediaPlayer.create(this, R.raw.music)
        if (mediaPlayer != null) {
            mediaPlayer.start()
        }

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

        //Variable inform which player have turn
        var gracz = 1
        val plansza = arrayListOf<Int>()
        var ruchy = 0

        for(i in 0..8){
            plansza.add(9)
        }


            b1.setOnClickListener {
                ruchy += 1
                gracz = graj(plansza, gracz,b1,wys_gracza ,wygrany,b1,b2,b3,b4,b5,b6,b7,b8,b9,0,ruchy)
                b1.isClickable = false

            }

            b2.setOnClickListener {
                ruchy += 1
                gracz = graj(plansza, gracz,b2,wys_gracza ,wygrany,b1,b2,b3,b4,b5,b6,b7,b8,b9,1,ruchy)
                b2.isClickable = false

            }

            b3.setOnClickListener {
                ruchy += 1
                gracz = graj(plansza, gracz,b3,wys_gracza ,wygrany,b1,b2,b3,b4,b5,b6,b7,b8,b9,2,ruchy)
                b3.isClickable = false

            }

            b4.setOnClickListener {
                ruchy += 1
                gracz = graj(plansza, gracz,b4,wys_gracza ,wygrany,b1,b2,b3,b4,b5,b6,b7,b8,b9,3,ruchy)
                b4.isClickable = false

            }

            b5.setOnClickListener {
                ruchy += 1
                gracz = graj(plansza, gracz,b5,wys_gracza ,wygrany,b1,b2,b3,b4,b5,b6,b7,b8,b9,4,ruchy)
                b5.isClickable = false

            }

            b6.setOnClickListener {
                gracz = graj(plansza, gracz,b6,wys_gracza ,wygrany,b1,b2,b3,b4,b5,b6,b7,b8,b9,5,ruchy)
                b6.isClickable = false
                ruchy += 1
            }

            b7.setOnClickListener {
                ruchy += 1
                gracz = graj(plansza, gracz,b7,wys_gracza ,wygrany,b1,b2,b3,b4,b5,b6,b7,b8,b9,6,ruchy)
                b7.isClickable = false

            }

            b8.setOnClickListener {
                ruchy += 1
                gracz = graj(plansza, gracz,b8,wys_gracza ,wygrany,b1,b2,b3,b4,b5,b6,b7,b8,b9,7,ruchy)
                b8.isClickable = false

            }

            b9.setOnClickListener {
                ruchy += 1
                gracz = graj(plansza, gracz,b9,wys_gracza ,wygrany,b1,b2,b3,b4,b5,b6,b7,b8,b9,8,ruchy)
                b9.isClickable = false


            }



            end.setOnClickListener {
                mediaPlayer?.stop()
                finish()

            }


            reset.setOnClickListener {
                for(i in 0..8){
                    plansza[i] = 9
                }
                gracz = 1
                ruchy = 0
                resetuj(wys_gracza ,wygrany,b1,b2,b3,b4,b5,b6,b7,b8,b9)

            }

    }

}

