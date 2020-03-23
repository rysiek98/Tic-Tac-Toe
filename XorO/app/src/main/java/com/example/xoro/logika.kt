package com.example.xoro
import android.graphics.Color
import android.widget.Button
import android.widget.TextView


fun threeInRow(showPlayer: TextView, gameBoard: ArrayList<Field>, winner: TextView ,buttons: ArrayList<Button>): Boolean{

    var flag = false
    var neighbor = 0
    var neighborTwo = 0
    var player = Owner.empty

    for(field in 0..8){

        if((gameBoard[field].getOwner() == Owner.X || gameBoard[field].getOwner() == Owner.O) && !flag){
            for(fieldDirections in gameBoard[field].getDirections()) {

                neighbor = gameBoard[field].directionToID(fieldDirections,field + 1)
                if(gameBoard[field].getOwner() == gameBoard[neighbor-1].getOwner()) {
                    for (neighborDirections in gameBoard[neighbor - 1].getDirections()) {

                        neighborTwo = gameBoard[neighbor - 1].directionToID(neighborDirections, neighbor)
                        if (gameBoard[field].getOwner() == gameBoard[neighborTwo - 1].getOwner() && fieldDirections == neighborDirections) {
                            flag = true;
                            player = gameBoard[field].getOwner()
                            colorWinButtons(buttons, neighbor, neighborTwo, field)
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
        return true
    }else{
        return false
    }


}


fun play(
    gameBoard: ArrayList<Field>, player: Player, b: Button, showPlayer: TextView, winner: TextView,buttons: ArrayList<Button>, id:Int, moves:Int, playervsPlayer: Boolean): Player
{
    if(moves > 8)
    {
        b.setText("X")
        showPlayer.setText("Koniec! ")
        winner.setText("Remis")
        winner.visibility = TextView.VISIBLE
        showPlayer.visibility = TextView.VISIBLE
        threeInRow(showPlayer, gameBoard, winner,buttons)

        return Player.defalut
    }else {
        if (player == Player.X) {
            showPlayer.setText("Gracz O")
            b.setText("X")
            gameBoard[id].setOwner(Owner.X)
            threeInRow(showPlayer, gameBoard, winner, buttons)
            if (!playervsPlayer) {
                return Player.X
            } else {
                return Player.O
            }
        } else {
            showPlayer.setText("Gracz X")
            b.setText("O")
            gameBoard[id].setOwner(Owner.O)
            threeInRow(showPlayer, gameBoard, winner, buttons)

            return Player.X
        }
    }
}

fun reset(showPlayer: TextView, winner: TextView ,buttons: ArrayList<Button>){

    for(field in 0..8) {
        buttons[field].setText("")
        buttons[field].isClickable = true
        buttons[field].setBackgroundResource(R.drawable.buttons2)
        buttons[field].setTextColor(Color.parseColor("#8BC34A"))
    }

    winner.visibility = TextView.INVISIBLE
    showPlayer.setText("Gracz X")

}

fun colorWinButtons(buttons: ArrayList<Button>,neighbor: Int, neighborTwo: Int, field: Int ){
    buttons[field].setBackgroundResource(R.drawable.winbuttons)
    buttons[neighbor-1].setBackgroundResource(R.drawable.winbuttons)
    buttons[neighborTwo-1].setBackgroundResource(R.drawable.winbuttons)
    buttons[field].setTextColor(Color.parseColor("#6f0000"))
    buttons[neighbor-1].setTextColor(Color.parseColor("#6f0000"))
    buttons[neighborTwo-1].setTextColor(Color.parseColor("#6f0000"))
}

fun firstMoveAi(showPlayer: TextView, gameBoard: ArrayList<Field>,buttons: ArrayList<Button>, moves: Int): Boolean{
    if(moves == 1){
        if(gameBoard[4].getOwner() == Owner.empty) {
            showPlayer.setText("Gracz X")
            buttons[4].setText("O")
            gameBoard[4].setOwner(Owner.O)
            buttons[4].isClickable = false

        }else{
            showPlayer.setText("Gracz X")
            buttons[0].setText("O")
            gameBoard[0].setOwner(Owner.O)
            buttons[0].isClickable = false
        }
        return false
    }else{
        return true
    }

}


fun moveAI(showPlayer: TextView, gameBoard: ArrayList<Field>, winner: TextView ,buttons: ArrayList<Button>, moves: Int): Int{
    if(moves < 9) {
    var neighbor = 0
    var neighborTwo = 0
    var flaga = firstMoveAi(showPlayer,gameBoard,buttons, moves)

        for (field in 0..8) {
            if (gameBoard[field].getOwner() == Owner.O && flaga) {
                for (fieldDirections in gameBoard[field].getDirections()) {
                    neighbor = gameBoard[field].directionToID(fieldDirections, field + 1)
                    if (gameBoard[neighbor - 1].getOwner() == Owner.empty) {
                        for (neighborDirections in gameBoard[neighbor - 1].getDirections()) {
                            neighborTwo =
                                gameBoard[neighbor - 1].directionToID(neighborDirections, neighbor)
                            if (gameBoard[neighborTwo - 1].getOwner() == Owner.X && fieldDirections == neighborDirections) {
                                showPlayer.setText("Gracz X")
                                buttons[neighbor - 1].setText("O")
                                gameBoard[neighbor - 1].setOwner(Owner.O)
                                threeInRow(showPlayer, gameBoard, winner, buttons)
                                flaga = false
                                buttons[neighbor - 1].isClickable = false
                            }
                        }
                    } else if (gameBoard[neighbor - 1].getOwner() == Owner.O && flaga) {
                        for (neighborDirections in gameBoard[neighbor - 1].getDirections()) {
                            neighborTwo =
                                gameBoard[neighbor - 1].directionToID(neighborDirections, neighbor)
                            if (gameBoard[neighborTwo - 1].getOwner() == Owner.empty && fieldDirections == neighborDirections) {
                                showPlayer.setText("Gracz X")
                                buttons[neighborTwo - 1].setText("O")
                                gameBoard[neighborTwo - 1].setOwner(Owner.O)
                                threeInRow(showPlayer, gameBoard, winner, buttons)
                                flaga = false
                                buttons[neighborTwo - 1].isClickable = false
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
                                showPlayer.setText("Gracz X")
                                buttons[neighbor - 1].setText("O")
                                gameBoard[neighbor - 1].setOwner(Owner.O)
                                threeInRow(showPlayer, gameBoard, winner, buttons)
                                flaga = false
                                buttons[neighbor - 1].isClickable = false
                            }

                        }
                    } else if (gameBoard[neighbor - 1].getOwner() == Owner.X && flaga) {
                        for (neighborDirections in gameBoard[neighbor - 1].getDirections()) {
                            neighborTwo =
                                gameBoard[neighbor - 1].directionToID(neighborDirections, neighbor)
                            if (gameBoard[neighborTwo - 1].getOwner() == Owner.empty && fieldDirections == neighborDirections) {
                                showPlayer.setText("Gracz X")
                                buttons[neighborTwo - 1].setText("O")
                                gameBoard[neighborTwo - 1].setOwner(Owner.O)
                                threeInRow(showPlayer, gameBoard, winner, buttons)
                                flaga = false
                                buttons[neighborTwo - 1].isClickable = false
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
                            showPlayer.setText("Gracz X")
                            buttons[neighbor - 1].setText("O")
                            gameBoard[neighbor - 1].setOwner(Owner.O)
                            threeInRow(showPlayer, gameBoard, winner, buttons)
                            buttons[neighbor - 1].isClickable = false
                            flaga = false
                            break
                        }
                    }
                }
            }
        }

        threeInRow(showPlayer, gameBoard, winner, buttons)
        return moves + 1
    }else{
        return moves
    }
}