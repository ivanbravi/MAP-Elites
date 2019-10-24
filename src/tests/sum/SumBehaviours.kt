package tests.sum

import mapelites.interfaces.BehavioursFunction
import mapelites.interfaces.Solution

class SumBehaviours(private val max:Int) : BehavioursFunction{
    override fun evaluate(x: Solution): DoubleArray {
        x as SumSolution

        val f1= maxSame(x)
        val f2= average(x)

        return doubleArrayOf(f1,f2)
    }

    private fun average(x:SumSolution):Double{
        return x.x.sum().toDouble()/x.x.size;
    }

    private fun maxSame(x:SumSolution):Double{
        val counters = IntArray(max)
        var max = 0

        for(e in x.x)
            counters[e]++

        for(c in counters)
            if(c>max)
                max = c

        return max.toDouble()
    }

}