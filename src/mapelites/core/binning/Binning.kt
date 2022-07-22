package mapelites.core.binning

interface Binning {
    fun bin(v:Double):Int
    fun binCount():Int
    fun min():Double
    fun max():Double
    fun marker(position:Int):Double
}