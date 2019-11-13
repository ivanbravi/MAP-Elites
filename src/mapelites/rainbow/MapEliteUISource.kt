package mapelites.rainbow

import mapelites.core.BehaviourSpace
import ui.elements.DataSource
import ui.elements.LabelledDataSource

class MapEliteUISource(private val d1:Int, private val d2:Int, private val bs: BehaviourSpace): LabelledDataSource {
    private var data = Array(width()*height()){0.0}

    fun width():Int = bs.dimensions[d1]

    fun height():Int = bs.dimensions[d2]
    fun update(){
        val newData = bs.collapse(d1,d2)

        for(i in 0 until newData.size){
            val line = newData[i]
            for(j in 0 until line.size){
                val value = line[j]?.let { normalise(it.fitness) } ?: noData()
                data[i+j*width()] = value
            }
        }
    }

    override fun xLabel(): String = bs.names?.get(d1) ?: ""

    override fun yLabel(): String = bs.names?.get(d2) ?: ""

    private fun normalise(v:Double):Double = (v-bs.minFitness)/(bs.maxFitness-bs.minFitness)

    override fun noData(): Double {
        return Double.MIN_VALUE
    }

    override fun showData(i: Int, j: Int): Double {
        if((i in 0 until width()) && (j in 0 until height())){
            return data[j*width()+i]
        }
        return 0.0
    }

}