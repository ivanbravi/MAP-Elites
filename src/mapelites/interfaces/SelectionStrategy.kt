package mapelites.interfaces

import mapelites.core.BehaviourSpace
import mapelites.core.BehaviourSpacePoint

interface SelectionStrategy {
    fun selection(bs:BehaviourSpace):Solution
}