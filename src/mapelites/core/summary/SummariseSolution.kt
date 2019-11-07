package mapelites.core.summary

import mapelites.interfaces.Solution

interface SummariseSolution {
    fun summarise(s:Solution?):String
}