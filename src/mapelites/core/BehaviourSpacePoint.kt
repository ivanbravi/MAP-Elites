package mapelites.core

import mapelites.interfaces.Solution
import java.util.*

data class BehaviourSpacePoint(val x: Solution, val fitness:Double, val behaviours:DoubleArray) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BehaviourSpacePoint

        if (x != other.x) return false
        if (fitness != other.fitness) return false
        if (!behaviours.contentEquals(other.behaviours)) return false

        return true
    }

    override fun toString(): String {
        return "x: $x\nf: $fitness\nb: ${Arrays.toString(behaviours)}"
    }

    override fun hashCode(): Int {
        var result = x.hashCode()
        result = 31 * result + fitness.hashCode()
        result = 31 * result + behaviours.contentHashCode()
        return result
    }
}