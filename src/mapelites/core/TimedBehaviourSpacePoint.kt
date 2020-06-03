package mapelites.core

import mapelites.interfaces.Solution
import java.util.*

class TimedBehaviourSpacePoint(val time: Int, x: Solution, fitness:Double, behaviours:DoubleArray): BehaviourSpacePoint(x,fitness, behaviours) {

    override fun toString(): String {
        return "t: $time\n${super.toString()}"
    }

}