package mapelites.core.binning

class LinearBinning(private val min:Int, private val max:Int, private val n:Int) : Binning{

    override fun bin(v: Double): Int {
        if(v>max || v<min)
            return -1
        if(v == max.toDouble())
            return n-1
        return ((v-min)/(max-min)*n).toInt()
    }

    override fun binCount(): Int {
        return n
    }

}