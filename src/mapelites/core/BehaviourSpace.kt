package mapelites.core

import mapelites.core.binning.Binning
import mapelites.interfaces.Solution
import kotlin.math.max
import kotlin.math.min

class BehaviourSpace(cbins:Array<Binning>){

    private val dimensionality:Int=cbins.size
    val dimensions:IntArray= this.computeDimensions(bins = cbins)

    private val bins:Array<Binning> = cbins.clone()
    private val space = HashMap<CHIntArray,BehaviourSpacePoint>()

    var maxFitness=Double.MIN_VALUE
        private set
    var minFitness=Double.MAX_VALUE
        private set

    var names:Array<String>?=null

    var best:BehaviourSpacePoint?=null

    fun reset(){
        this.space.clear()
        this.best = null
        maxFitness=Double.MIN_VALUE
        minFitness=Double.MAX_VALUE
    }

    fun getSolution(features:DoubleArray):BehaviourSpacePoint?{
        return space[bin(features)]
    }

    private fun bin(features:DoubleArray):CHIntArray{
        val coordinates =  IntArray(dimensionality)
        for (i in 0 until dimensionality){
            coordinates[i] = bins[i].bin(features[i])
        }
        return CHIntArray(coordinates)
    }

    fun isEmpty(features:DoubleArray):Boolean{
        val bin = bin(features)
        return !space.containsKey(bin)
    }

    fun add(x: Solution, f:Double, b:DoubleArray){
        val bin = bin(b)
        val point = BehaviourSpacePoint(x,f,b)
        if(best?.fitness?:Double.MIN_VALUE <= f)
            best = point
         updateMinMax(f)
        space[bin] = point
    }

    private fun updateMinMax(f:Double){
        minFitness = min(minFitness,f)
        maxFitness = max(maxFitness,f)
    }

    fun collapse(d1:Int, d2:Int):Array<Array<BehaviourSpacePoint?>>{
        val d1Size = bins[d1].binCount()
        val d2Size = bins[d2].binCount()

        val grid= Array(d1Size){arrayOfNulls<BehaviourSpacePoint>(d2Size)}

        for( point in space.keys){
            val el=space[point]
            val x = point[d1]
            val y = point[d2]

            if(grid[x][y]==null){
                grid[x][y] = el
            }else if(grid[x][y]!!.fitness>el!!.fitness){
                grid[x][y] = el
            }
        }

        return grid
    }

    private fun computeDimensions(bins:Array<Binning>):IntArray{
        val ds = IntArray(bins.size)
        for(i in 0 until bins.size)
            ds[i] = bins[i].binCount()
        return ds
    }

    fun populatedCoordinates():List<CHIntArray>{
        return space.keys.toList()
    }

    fun getSolution(coords: CHIntArray):BehaviourSpacePoint?{
        return space[coords]
    }
}