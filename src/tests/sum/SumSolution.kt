package tests.sum

import mapelites.interfaces.Solution
import java.util.*

class SumSolution(cx:IntArray) : Solution {

    val x:IntArray=cx.clone()

    constructor(s:SumSolution):this(s.x)

    override fun toString(): String {
        return Arrays.toString(x)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SumSolution

        if (!x.contentEquals(other.x)) return false

        return true
    }

    override fun hashCode(): Int {
        return x.contentHashCode()
    }
}