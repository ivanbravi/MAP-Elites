package mapelites.core.summary

import mapelites.Search
import java.lang.StringBuilder

class SummariseSearch (private val sbs:SummariseBehaviourSpace) {

    var progressSize:Int = 20

    fun summarise(s:Search):String{
        val builder = StringBuilder()

        if(s.searchIsOver())
            builder.append("[done]").append("\n")
        else{
            if(!s.bootIsOver())
                builder.append("[booting]").append(progress((s.iteration/s.bootIterations.toDouble()),progressSize)).append("\n")
            else
                builder.append("[search]").append(progress((s.iteration/s.iterations.toDouble()),progressSize)).append("\n")
        }

        builder.append(sbs.summarise(s.bs))

        return builder.toString()
    }

    private fun progress(d:Double, s:Int):String{
        val p=(s*d).toInt()
        return "["+"#".repeat(p)+" ".repeat(s-p)+"]"
    }

}