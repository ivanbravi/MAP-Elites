package mapelites.core.selection

import mapelites.core.BehaviourSpace
import mapelites.interfaces.SelectionStrategy
import mapelites.interfaces.Solution
import java.util.*

class RandomSelection:SelectionStrategy{
    private val rnd:Random = Random()

    override fun selection(bs: BehaviourSpace): Solution {
        val all = bs.populatedCoordinates()
        val key = all[rnd.nextInt(all.size)]
        return bs.getSolution(key)!!.x
    }

}