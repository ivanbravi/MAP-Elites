package mapelites.core.summary

import mapelites.core.BehaviourSpace
import java.util.*

class SimpleSummary: SummariseBehaviourSpace {
    private var ss:SummariseSolution?=null

    constructor(ss: SummariseSolution? = null){
        this.ss = ss
    }

    override fun summarise(bs: BehaviourSpace): String {
        val b = StringBuilder()

        b.append("min fitness: ").append(bs.minFitness).append("\n")
        b.append("max fitness: ").append(bs.maxFitness).append("\n")
        if(ss==null)
            b.append("best solution: ").append(bs.best?.x.toString()).append("\n")
        else
            b.append("best solution:\n").append(ss?.summarise(bs.best?.x))
        b.append("best behaviour: ").append(Arrays.toString(bs.best?.behaviours)).append("\n")

        return b.toString()
    }
}