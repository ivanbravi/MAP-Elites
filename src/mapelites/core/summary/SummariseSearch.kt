package mapelites.core.summary

import mapelites.Search
import java.lang.StringBuilder

class SummariseSearch (private val sbs:SummariseBehaviourSpace) {

    fun summarise(s:Search):String{
        val builder = StringBuilder()
        val pSize=20

        if(s.searchIsOver())
            builder.append("[done]").append("\n")
        else{
            if(!s.bootIsOver())
                builder.append("[booting]").append(progress((s.iteration/s.bootIterations.toDouble()),pSize)).append("\n")
            else
                builder.append("[search]").append(progress((s.iteration/s.iterations.toDouble()),pSize)).append("\n")
        }

        builder.append(sbs.summarise(s.bs))

        return builder.toString()
    }

    private fun progress(d:Double, s:Int):String{
        val p=(s*d).toInt()
        return "["+"#".repeat(p)+" ".repeat(s-p)+"]"
    }

}