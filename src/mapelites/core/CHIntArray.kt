package mapelites.core

class CHIntArray(val value:IntArray) {
    override fun hashCode(): Int {
        return value.contentHashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CHIntArray

        if (!value.contentEquals(other.value)) return false

        return true
    }

    operator fun get(index:Int):Int{
        return value[index]
    }
}