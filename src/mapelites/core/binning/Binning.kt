package mapelites.core.binning

interface Binning {
    fun bin(v:Double):Int
    fun binCount():Int
}