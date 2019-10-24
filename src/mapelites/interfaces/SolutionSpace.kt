package mapelites.interfaces

interface SolutionSpace {
    fun mutate(s:Solution):Solution

    fun crossover(s1:Solution, s2:Solution):Solution{
        return mutate(s1)
    }

    fun rndPoint():Solution
}