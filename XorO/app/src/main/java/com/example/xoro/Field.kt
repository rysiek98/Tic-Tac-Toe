package com.example.xoro




class Field(ID: Int, Directions: Array<Directions>) {

    private var ID = ID
    private var Owner = com.example.xoro.Owner.empty
    private var Directions = Directions


    fun setOwner(Owner: Owner){
        this.Owner = Owner
    }

    fun getOwner(): Owner{
        return this.Owner
    }

    fun getDirections(): Array<Directions>{
        return this.Directions
    }

    fun getID(): Int{
        return  this.ID
    }


    fun directionToID(Directions: Directions,id: Int): Int{
        var ID = id
        when (Directions) {
            com.example.xoro.Directions.North -> ID = ID - 3
            com.example.xoro.Directions.North_West -> ID = ID - 4
            com.example.xoro.Directions.West -> ID = ID - 1
            com.example.xoro.Directions.South_West -> ID = ID + 2
            com.example.xoro.Directions.South -> ID = ID + 3
            com.example.xoro.Directions.South_East -> ID = ID + 4
            com.example.xoro.Directions.East -> ID = ID + 1
            com.example.xoro.Directions.Nort_East -> ID = ID - 2
            else -> {
                ID = 0
            }
        }
        return  ID
    }

}


