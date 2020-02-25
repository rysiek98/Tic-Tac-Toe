package com.example.xoro


import android.widget.Button
import android.widget.TextView


fun sprawdz(wys_gracza: TextView,args: ArrayList<Int>, gracz:Int, wygrany: TextView ,b1: Button,b2: Button, b3: Button,b4: Button,b5: Button,b6: Button,b7: Button,b8: Button,b9: Button){

    var flaga = false

    if(
        ((args[0] == args[1]) and (args[0] == args[2]) and (args[0] == gracz)) or
        ((args[3] == args[4]) and (args[3] == args[5]) and (args[3] == gracz)) or
        ((args[6] == args[7]) and (args[6] == args[8]) and (args[6] == gracz)) or
        ((args[0] == args[3]) and (args[0] == args[6]) and (args[0] == gracz)) or
        ((args[1] == args[4]) and (args[1] == args[7]) and (args[1] == gracz)) or
        ((args[2] == args[5]) and (args[2] == args[8]) and (args[2] == gracz)) or
        ((args[0] == args[4]) and (args[0] == args[8]) and (args[0] == gracz)) or
        ((args[6] == args[4]) and (args[6] == args[2]) and (args[6] == gracz))
    )
    {
        if(gracz == 1)
        {
            wygrany.setText("Wygrał gracz 1")
            wygrany.visibility = TextView.VISIBLE
            wys_gracza.setText("Koniec! ")
        }
        else
        {
            wygrany.setText("Wygrał gracz 2")
            wygrany.visibility = TextView.VISIBLE
            wys_gracza.setText("Koniec! ")
        }
        flaga = true
    }

    if (flaga)
    {
        b1.isClickable = false
        b2.isClickable = false
        b3.isClickable = false
        b4.isClickable = false
        b5.isClickable = false
        b6.isClickable = false
        b7.isClickable = false
        b8.isClickable = false
        b9.isClickable = false
    }

}


fun graj(plansza: ArrayList<Int>, gracz:Int,b: Button, wys_gracza: TextView, wygrany: TextView ,b1: Button,b2: Button, b3: Button,b4: Button,b5: Button,b6: Button,b7: Button,b8: Button,b9: Button,id:Int,ruchy:Int ): Int
{
    var tmp: Int
    if(ruchy == 9 )
    {
        wygrany.setText("Remis!")
        wygrany.visibility = TextView.VISIBLE
    }
    if (gracz == 1) {
        wys_gracza.setText("Gracz 2 - O")
        b.setText("X")
        plansza[id] = 1

        sprawdz(wys_gracza,plansza, gracz, wygrany,b1,b2,b3,b4,b5,b6,b7,b8,b9)
        tmp = 2
        return tmp

    } else {
        wys_gracza.setText("Gracz 1 - X")
        b.setText("O")
        plansza[id] = 2
        sprawdz(wys_gracza,plansza, gracz, wygrany,b1,b2,b3,b4,b5,b6,b7,b8,b9)
        tmp = 1
        return tmp

    }


}

fun resetuj(wys_gracza: TextView, wygrany: TextView ,b1: Button,b2: Button, b3: Button,b4: Button,b5: Button,b6: Button,b7: Button,b8: Button,b9: Button){

    b1.setText("")
    b2.setText("")
    b3.setText("")
    b4.setText("")
    b5.setText("")
    b6.setText("")
    b7.setText("")
    b8.setText("")
    b9.setText("")

    b1.isClickable = true
    b2.isClickable = true
    b3.isClickable = true
    b4.isClickable = true
    b5.isClickable = true
    b6.isClickable = true
    b7.isClickable = true
    b8.isClickable = true
    b9.isClickable = true

    wygrany.visibility = TextView.INVISIBLE
    wys_gracza.setText("Gracz 1 - X")

}