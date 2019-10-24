package mapelites.core.binning

class CustomBinning (private val limits:DoubleArray):Binning{

    override fun bin(v: Double): Int {
        if(v<limits.first())
            return 0;

        for(i in 0 until limits.size)
            if(v>=limits[i] && v<limits[i+1])
                return i

        return limits.size
    }

    override fun binCount():Int{
        return limits.size+1
    }

}