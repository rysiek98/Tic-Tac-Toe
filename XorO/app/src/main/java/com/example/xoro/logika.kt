package com.example.xoro



import android.graphics.Color
import android.widget.Button
import android.widget.TextView


fun threeInRow(showPlayer: TextView, gameBoard: ArrayList<Field>, winner: TextView ,buttons: ArrayList<Button>){

    var flag = false
    var neighbor = 0
    var neighborTwo = 0
    var player = Owner.empty
    for(i in 0..8){
        if(gameBoard[i].getOwner() == Owner.X || gameBoard[i].getOwner() == Owner.O){
            for(j in gameBoard[i].getDirections()) {
                neighbor = gameBoard[i].directionToID(j,i + 1)
                if(gameBoard[i].getOwner() == gameBoard[neighbor-1].getOwner()) {
                    for (k in gameBoard[neighbor - 1].getDirections()) {
                        neighborTwo = gameBoard[neighbor - 1].directionToID(k, neighbor)
                        if (gameBoard[i].getOwner() == gameBoard[neighborTwo - 1].getOwner() && j == k) {
                            flag = true;
                            player = gameBoard[i].getOwner()
                            buttons[i].setBackgroundResource(R.drawable.winbuttons)
                            buttons[neighbor-1].setBackgroundResource(R.drawable.winbuttons)
                            buttons[neighborTwo-1].setBackgroundResource(R.drawable.winbuttons)
                            buttons[i].setTextColor(Color.parseColor("#6f0000"))
                            buttons[neighbor-1].setTextColor(Color.parseColor("#6f0000"))
                            buttons[neighborTwo-1].setTextColor(Color.parseColor("#6f0000"))
                            break
                        }
                    }
                }
            }
        }
    }


if (flag)
{
    for(i in 0..8) {
        buttons[i].isClickable = false
    }

    winner.setText("Wygrał gracz "+player.toString())
    winner.visibility = TextView.VISIBLE
    showPlayer.setText("Koniec! ")
    showPlayer.visibility = TextView.VISIBLE
}


}


fun play(
    gameBoard: ArrayList<Field>, player: Player, b: Button, showPlayer: TextView, winner: TextView,buttons: ArrayList<Button>, id:Int, moves:Int): Player
{
    if(moves == 9)
    {
        showPlayer.setText("Koniec! ")
        winner.setText("Remis")
        winner.visibility = TextView.VISIBLE
        showPlayer.visibility = TextView.VISIBLE
        threeInRow(showPlayer, gameBoard, winner,buttons)

        return Player.defalut
    }
     if (player == Player.X) {
        showPlayer.setText("Gracz O")
        b.setText("X")
        gameBoard[id].setOwner(Owner.X)
        threeInRow(showPlayer, gameBoard, winner,buttons)

        return Player.O

    } else {
        showPlayer.setText("Gracz X")
        b.setText("O")
        gameBoard[id].setOwner(Owner.O)
        threeInRow(showPlayer, gameBoard, winner,buttons)

        return Player.X

    }


}

fun reset(showPlayer: TextView, winner: TextView ,buttons: ArrayList<Button>){

    for(i in 0..8) {
        buttons[i].setText("")
        buttons[i].isClickable = true
        buttons[i].setBackgroundResource(R.drawable.buttons2)
        buttons[i].setTextColor(Color.parseColor("#8BC34A"))
    }

    winner.visibility = TextView.INVISIBLE
    showPlayer.setText("Gracz 1 - X")

}


