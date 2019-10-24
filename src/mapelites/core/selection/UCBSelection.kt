package mapelites.core.selection

import mapelites.core.BehaviourSpace
import mapelites.core.CHIntArray
import mapelites.interfaces.SelectionStrategy
import mapelites.interfaces.Solution
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.ln
import kotlin.math.sqrt

class UCBSelection:SelectionStrategy {

    private val k:Double=1.4
    private val eps:Double=1E-9

    private var visits:Int=0
    private val counters = HashMap<CHIntArray, Int>()
    private val rnd: Random = Random()

    private var hasBooted:Boolean=false

    override fun selection(bs: BehaviourSpace): Solution {
        val selectedCoord = ucbSelection(bs)
        val counter = (counters[selectedCoord] ?: 0)+1
        counters[selectedCoord] = counter
        return bs.getSolution(selectedCoord)!!.x
    }

    private fun boot(bs:BehaviourSpace){
        hasBooted=true
        visits+=bs.populatedCoordinates().size
    }

    private fun ucbSelection(bs: BehaviourSpace):CHIntArray{
        val popSpace = bs.populatedCoordinates()
        var bestUCB = Double.MIN_VALUE
        var bestCoord:CHIntArray?=null

        if(!hasBooted) boot(bs)

        for(coord in popSpace){
            val currSol = bs.getSolution(coord)!!
            if(counters[coord]==null){
                counters[coord] = 1
            }
            val currUCB = ucb(currSol.fitness,counters[coord]!!)
            if(currUCB>bestUCB){
                bestUCB = currUCB
                bestCoord = coord
            }
        }
        return bestCoord!!
    }

    private fun ucb(value:Double, visits:Int):Double{
        val exploit = value
        val explore = k* sqrt((ln(this.visits.toDouble()+eps))/(visits+eps))
        val noise = rnd.nextGaussian()*eps
        return exploit+explore+noise
    }
}