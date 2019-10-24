package tests.sum

import mapelites.Search
import mapelites.core.BehaviourSpace
import mapelites.core.binning.LinearBinning
import mapelites.core.selection.BestSelection
import mapelites.core.selection.RandomSelection
import mapelites.core.selection.UCBSelection
import mapelites.interfaces.SelectionStrategy

fun main() {
    val s = Search(
        SumSpace(5, 5),
        SumFitness(),
        BehaviourSpace(arrayOf(LinearBinning(0, 5, 5), LinearBinning(0, 5, 10))),
        SumBehaviours(5)
    )

    val samples:Int=100
    val selMethods= arrayOf(
        UCBSelection(),
        RandomSelection(),
        BestSelection()
    )

    for(sel in selMethods){
        println("method: ${sel.javaClass.simpleName}\nperformance: ${avgPerformance(samples, s, sel)}\n")
    }
}

private fun avgPerformance(samples:Int, s:Search, sel:SelectionStrategy):Double{
    var acc:Double=0.0
    for (i in 1..samples){
        s.selection = sel
        s.search(1000, 30)
        acc += s.bs.best!!.fitness
    }
    return acc/samples
}