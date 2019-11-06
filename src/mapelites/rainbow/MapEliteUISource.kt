package mapelites.rainbow

import mapelites.core.BehaviourSpace
import ui.elements.DataSource

class MapEliteUISource(private val d1:Int, private val d2:Int, private val bs: BehaviourSpace): DataSource {

    private var data = Array(width()*height()){0.0}

    fun width():Int = bs.dimensions[d1]
    fun height():Int = bs.dimensions[d2]

    fun update(){
        val newData = bs.collapse(d1,d2)

        for(i in 0 until newData.size){
            val line = newData[i]
            for(j in 0 until line.size){
                val value = line[j]?.fitness ?: 0.0
                data[i+j*width()] = (value-bs.minFitness)/(bs.maxFitness-bs.minFitness)
                println("$i $j index: ${i+j*width()}")
            }
        }
    }


    override fun showData(i: Int, j: Int): Double {
        if((i in 0 until width())&&(j in 0 until height())){
            return data[j*width()+i]
        }
        return 0.0
    }

}