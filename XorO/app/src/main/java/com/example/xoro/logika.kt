package com.example.xoro
import android.animation.ValueAnimator
import android.graphics.Color
import android.view.animation.Animation
import android.widget.Button
import android.widget.TextView
import kotlin.collections.ArrayList


fun threeInRow(showPlayer: TextView, gameBoard: ArrayList<Field>, winner: TextView ,buttons: ArrayList<Button>, animation: Animation): Boolean{

    var someoneWinGame = false
    var neighbor = 0
    var neighborTwo = 0
    var player = Owner.empty

    for(field in 0..8){

        if((gameBoard[field].getOwner() == Owner.X || gameBoard[field].getOwner() == Owner.O) && !someoneWinGame){
            for(fieldDirections in gameBoard[field].getDirections()) {

                neighbor = gameBoard[field].directionToID(fieldDirections,field + 1)
                if(gameBoard[field].getOwner() == gameBoard[neighbor-1].getOwner()) {
                    for (neighborDirections in gameBoard[neighbor - 1].getDirections()) {

                        neighborTwo = gameBoard[neighbor - 1].directionToID(neighborDirections, neighbor)
                        if (gameBoard[field].getOwner() == gameBoard[neighborTwo - 1].getOwner() && fieldDirections == neighborDirections) {
                            someoneWinGame = true
                            player = gameBoard[field].getOwner()
                            colorWinButtons(buttons, neighbor, neighborTwo, field, animation)
                            break
                        }

                    }
                }
            }
        }

    }

    if (someoneWinGame)
    {
        for(i in 0..8) {
            buttons[i].isClickable = false
     }
        winner.text = "The winner is $player"
        winner.visibility = TextView.VISIBLE
        showPlayer.text = "End game!"
        showPlayer.visibility = TextView.VISIBLE
        return true
    }else{
        return false
    }

}


fun play(
    gameBoard: ArrayList<Field>, player: Player, b: Button, showPlayer: TextView, winner: TextView,buttons: ArrayList<Button>, id:Int, moves:Int, playervsPlayer: Boolean, animation: Animation): Player
{
    if(moves > 8)
    {
        b.text = "X"
        showPlayer.text = "End game!"
        winner.text = "Draw!"
        winner.visibility = TextView.VISIBLE
        showPlayer.visibility = TextView.VISIBLE
        threeInRow(showPlayer, gameBoard, winner,buttons, animation)

        return Player.defalut
    }else {
        if (player == Player.X) {
            showPlayer.text = "Player X"
            b.text = "X"
            gameBoard[id].setOwner(Owner.X)
            threeInRow(showPlayer, gameBoard, winner, buttons, animation)
            if (!playervsPlayer) {
                return Player.X
            } else {
                return Player.O
            }
        } else {
            showPlayer.text = "Player X"
            b.text = "O"
            gameBoard[id].setOwner(Owner.O)
            threeInRow(showPlayer, gameBoard, winner, buttons, animation)

            return Player.X
        }
    }
}

fun reset(showPlayer: TextView, winner: TextView ,buttons: ArrayList<Button>){

    for(field in 0..8) {
        buttons[field].text = ""
        buttons[field].isClickable = true
        buttons[field].setBackgroundResource(R.drawable.buttons2)
        buttons[field].setTextColor(Color.parseColor("#8BC34A"))
    }

    winner.visibility = TextView.INVISIBLE
    showPlayer.text = "Player X"

}

fun colorWinButtons(buttons: ArrayList<Button>,neighbor: Int, neighborTwo: Int, field: Int, animation: Animation ){

    buttons[field].setBackgroundResource(R.drawable.winbuttons)
    buttons[neighbor-1].setBackgroundResource(R.drawable.winbuttons)
    buttons[neighborTwo-1].setBackgroundResource(R.drawable.winbuttons)
    buttons[field].setTextColor(Color.parseColor("#6f0000"))
    buttons[field].startAnimation(animation)
    buttons[neighbor-1].setTextColor(Color.parseColor("#6f0000"))
    buttons[neighbor-1].startAnimation(animation)
    buttons[neighborTwo-1].setTextColor(Color.parseColor("#6f0000"))
    buttons[neighborTwo-1].startAnimation(animation)
}

fun firstMoveAi(showPlayer: TextView, gameBoard: ArrayList<Field>,buttons: ArrayList<Button>, moves: Int, animation: Animation): Boolean{
    if(moves == 1){
        if(gameBoard[4].getOwner() == Owner.empty) {
            showPlayer.text = "Player X"
            buttons[4].text = "O"
            gameBoard[4].setOwner(Owner.O)
            buttons[4].isClickable = false
            buttons[4].startAnimation(animation)

        }else{
            var flag: Boolean = true
            showPlayer.text = "Player X"
            while(flag){
                val randomField = (0..8).random()
                if(gameBoard[randomField].getOwner() == Owner.empty){
                    buttons[randomField].text = "O"
                    gameBoard[randomField].setOwner(Owner.O)
                    buttons[randomField].isClickable = false
                    buttons[randomField].startAnimation(animation)
                    flag = false
                }
            }
        }
        return false
    }else{
        return true
    }

}


fun moveAI(showPlayer: TextView, gameBoard: ArrayList<Field>, winner: TextView ,buttons: ArrayList<Button>, moves: Int, animation: Animation): Int{
    if(moves < 9 && !threeInRow(showPlayer, gameBoard, winner, buttons, animation)) {
    var neighbor = 0
    var neighborTwo = 0
    var flaga = firstMoveAi(showPlayer,gameBoard,buttons, moves, animation)

        for (field in 0..8) {
            if (gameBoard[field].getOwner() == Owner.O && flaga) {
                for (fieldDirections in gameBoard[field].getDirections()) {
                    neighbor = gameBoard[field].directionToID(fieldDirections, field + 1)
                    if (gameBoard[neighbor - 1].getOwner() == Owner.empty) {
                        for (neighborDirections in gameBoard[neighbor - 1].getDirections()) {
                            neighborTwo =
                                gameBoard[neighbor - 1].directionToID(neighborDirections, neighbor)
                            if (gameBoard[neighborTwo - 1].getOwner() == Owner.X && fieldDirections == neighborDirections) {
                                showPlayer.text = "Player X"
                                buttons[neighbor - 1].text = "O"
                                gameBoard[neighbor - 1].setOwner(Owner.O)
                                threeInRow(showPlayer, gameBoard, winner, buttons, animation)
                                flaga = false
                                buttons[neighbor - 1].isClickable = false
                                buttons[neighbor - 1].startAnimation(animation)
                            }
                        }
                    } else if (gameBoard[neighbor - 1].getOwner() == Owner.O && flaga) {
                        for (neighborDirections in gameBoard[neighbor - 1].getDirections()) {
                            neighborTwo =
                                gameBoard[neighbor - 1].directionToID(neighborDirections, neighbor)
                            if (gameBoard[neighborTwo - 1].getOwner() == Owner.empty && fieldDirections == neighborDirections) {
                                showPlayer.text = "Player X"
                                buttons[neighborTwo - 1].text = "O"
                                gameBoard[neighborTwo - 1].setOwner(Owner.O)
                                threeInRow(showPlayer, gameBoard, winner, buttons, animation)
                                flaga = false
                                buttons[neighborTwo - 1].isClickable = false
                                buttons[neighborTwo - 1].startAnimation(animation)
                            }
                        }
                    }
                }
            }


        }

        for (field in 0..8) {

            if (gameBoard[field].getOwner() == Owner.X && flaga) {
                for (fieldDirections in gameBoard[field].getDirections()) {
                    neighbor = gameBoard[field].directionToID(fieldDirections, field + 1)
                    if (gameBoard[neighbor - 1].getOwner() == Owner.empty) {
                        for (neighborDirections in gameBoard[neighbor - 1].getDirections()) {
                            neighborTwo =
                                gameBoard[neighbor - 1].directionToID(neighborDirections, neighbor)
                            if (gameBoard[neighborTwo - 1].getOwner() == Owner.X && fieldDirections == neighborDirections) {
                                showPlayer.text = "Player X"
                                buttons[neighbor - 1].text = "O"
                                gameBoard[neighbor - 1].setOwner(Owner.O)
                                threeInRow(showPlayer, gameBoard, winner, buttons, animation)
                                flaga = false
                                buttons[neighbor - 1].isClickable = false
                                buttons[neighbor - 1].startAnimation(animation)
                            }

                        }
                    } else if (gameBoard[neighbor - 1].getOwner() == Owner.X && flaga) {
                        for (neighborDirections in gameBoard[neighbor - 1].getDirections()) {
                            neighborTwo =
                                gameBoard[neighbor - 1].directionToID(neighborDirections, neighbor)
                            if (gameBoard[neighborTwo - 1].getOwner() == Owner.empty && fieldDirections == neighborDirections) {
                                showPlayer.text = "Player X"
                                buttons[neighborTwo - 1].text = "O"
                                gameBoard[neighborTwo - 1].setOwner(Owner.O)
                                threeInRow(showPlayer, gameBoard, winner, buttons, animation)
                                flaga = false
                                buttons[neighborTwo - 1].isClickable = false
                                buttons[neighborTwo - 1].startAnimation(animation)
                            }

                        }
                    }

                }
            }
        }

        if (flaga) {
            for (field in 0..8) {
                if (gameBoard[field].getOwner() == Owner.O && flaga) {
                    for (fieldDirections in gameBoard[field].getDirections()) {
                        neighbor = gameBoard[field].directionToID(fieldDirections, field + 1)
                        if (gameBoard[neighbor - 1].getOwner() == Owner.empty) {
                            showPlayer.text = "Player X"
                            buttons[neighbor - 1].text = "O"
                            gameBoard[neighbor - 1].setOwner(Owner.O)
                            threeInRow(showPlayer, gameBoard, winner, buttons, animation)
                            buttons[neighbor - 1].isClickable = false
                            buttons[neighbor - 1].startAnimation(animation)
                            flaga = false
                            break
                        }
                    }
                }
            }
        }

        threeInRow(showPlayer, gameBoard, winner, buttons, animation)
        return moves + 1
    }else{
        return moves
    }
}