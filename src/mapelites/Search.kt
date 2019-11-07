package mapelites

import mapelites.core.BehaviourSpace
import mapelites.core.selection.RandomSelection
import mapelites.interfaces.*

class Search(private val ss: SolutionSpace,
             private val ff: FitnessFunction,
             val bs: BehaviourSpace,
             private val bf: BehavioursFunction) {

    var iteration:Int=0
        private set
    var iterations:Int=0
        private set
    var bootIterations:Int=0
        private set

    var selection:SelectionStrategy = RandomSelection()

    private fun reset(){
        bs.reset()
        iteration = 0
        iterations = 0
        bootIterations = 0
    }

    fun bootIsOver():Boolean{
        return iteration >= bootIterations
    }

    fun searchIsOver():Boolean{
        return iteration>=iterations
    }

    fun startInteractiveSearch(iterations:Int, bootIterations:Int){
        reset()
        this.bs.reset()
        this.iterations = iterations
        this.bootIterations = bootIterations
    }

    fun interactiveSearch(steps:Int){
        var current:Solution
        val loopsLeft = if(steps>(iterations-iteration)) iterations-iteration else steps

        for (iteration in 0 until loopsLeft){
            current = if(bootIsOver()) ss.mutate(selection.selection(bs)) else ss.rndPoint()

            val f = ff.evaluate(current)
            val b = bf.evaluate(current)

            if(!bs.isEmpty(b)) {
                if (bs.getSolution(b)!!.fitness > f)
                    bs.add(current, f, b);
            }else {
                bs.add(current, f, b)
            }
        }
        iteration += loopsLeft
    }

    fun search(iterations:Int, bootIterations:Int){
        reset()
        this.iterations = iterations
        this.bootIterations = bootIterations
        interactiveSearch(iterations)
    }

}