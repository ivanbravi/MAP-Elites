package mapelites.core.binning

open class CustomBinning (private val limits:DoubleArray):Binning{
    override fun bin(v: Double): Int {
        if(v<limits.first())
            return 0

        for(i in 0 until limits.size-1)
            if(v>=limits[i] && v<limits[i+1])
                return i+1

        return limits.size
    }

    override fun binCount():Int{
        return limits.size+1
    }

    override fun min(): Double = limits.first()

    override fun max(): Double = limits.last()
    override fun marker(position: Int): Double{
        if(position<0)
            return Double.NEGATIVE_INFINITY
        else if (position>= limits.size)
            return Double.POSITIVE_INFINITY
        return limits[position]
    }

}
