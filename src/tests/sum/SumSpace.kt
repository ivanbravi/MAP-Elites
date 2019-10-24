package tests.sum

import mapelites.interfaces.Solution
import mapelites.interfaces.SolutionSpace
import java.util.*


class SumSpace(private val size:Int, private val max:Int): SolutionSpace{
    private val rnd = Random()

    override fun rndPoint(): Solution {
        val s = SumSolution(IntArray(size))
        for(i in 0 until size){
            s.x[i] = rnd.nextInt(max)
        }
        return s
    }

    override fun mutate(s: Solution): Solution {
        if(s !is SumSolution){
            return rndPoint()
        }
        val i = rnd.nextInt(size)
        val r = SumSolution(s)
        val newValue = Math.floorMod((s.x[i]+(if (rnd.nextBoolean()) 1 else -1)), max)
        r.x[i] = newValue
        return r
    }
}