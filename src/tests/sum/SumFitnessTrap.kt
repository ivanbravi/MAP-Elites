package tests.sum

import mapelites.interfaces.FitnessFunction
import mapelites.interfaces.Solution

class SumFitnessTrap(private val max:Int): FitnessFunction {
    override fun evaluate(x: Solution): Double {
        x as SumSolution
        val fitness = x.x.sum()
        if (x.x.size*(max-1) == fitness)
            return -1.0
        return fitness.toDouble()
    }
}