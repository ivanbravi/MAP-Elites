package tests.sum

import mapelites.interfaces.FitnessFunction
import mapelites.interfaces.Solution

class SumFitness:FitnessFunction{
    override fun evaluate(x: Solution): Double {
        x as SumSolution
        return x.x.sum().toDouble()
    }

}