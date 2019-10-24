package mapelites.core

import mapelites.interfaces.SummariseBehaviourSpace
import java.util.*

class SimpleSummary:SummariseBehaviourSpace {

    override fun summarise(bs:BehaviourSpace): String {
        val b = StringBuilder()

        b.append("min fitness: ").append(bs.minFitness).append("\n")
        b.append("max fitness: ").append(bs.maxFitness).append("\n")
        b.append("best solution: ").append(bs.best?.x.toString()).append("\n")
        b.append("best behaviour: ").append(Arrays.toString(bs.best?.behaviours)).append("\n")

        return b.toString()
    }
}