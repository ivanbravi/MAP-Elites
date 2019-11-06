package mapelites.core.binning

class LinearBinning(private val min:Int, private val max:Int, private val n:Int) : Binning{

    private var pad:Boolean=false;

    fun pad():Binning{ pad = true; return this;}

    private fun binPad(v:Double):Int = when{
        v>=max -> n+1
        v<min -> 0
        else -> ((v-min)/(max-min)*n+1).toInt()
    }

    private fun binNoPad(v:Double):Int = when{
        v>=max -> n-1
        v<min -> 0
        else -> ((v-min)/(max-min)*n).toInt()
    }

    override fun bin(v: Double): Int= if(pad) binPad(v) else binNoPad(v)

    override fun binCount(): Int = n+(if(pad)2 else 0)

}