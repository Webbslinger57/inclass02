package cs335.inclass02

import java.util.*

class CrimeDB private constructor() {

    val crimeList = mutableListOf<Crime>()

    init{
        for(i in 0 until 100){
            val crime = Crime()
            crime.title = "Crime #%d".format(i)
            crime.isSolved = i % 2 == 0
            crimeList += crime
        }
    }

    public fun getCrime(id: UUID):Crime?{

        for(c in crimeList) {
            if(id == c.id){
                return c
            }
        }
        return null
    }

    private object HOLDER{
        val INSTANCE = CrimeDB()
    }

    companion object{

        val instance:CrimeDB by lazy{HOLDER.INSTANCE}

    }

}