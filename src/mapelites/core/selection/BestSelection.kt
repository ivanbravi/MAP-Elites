package mapelites.core.selection

import mapelites.core.BehaviourSpace
import mapelites.interfaces.SelectionStrategy
import mapelites.interfaces.Solution

class BestSelection:SelectionStrategy {
    override fun selection(bs: BehaviourSpace): Solution {
        return bs.best!!.x
    }
}