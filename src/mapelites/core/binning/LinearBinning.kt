package mapelites.core.binning

class LinearBinning(min:Double, max:Double,breaks:Int) :
        CustomBinning(
            DoubleArray(breaks+1)
            {i -> (min+(max-min)*i/breaks.toDouble())}
        ){
}