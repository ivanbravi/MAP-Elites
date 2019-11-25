package tests.sum

import mapelites.Search
import mapelites.core.BehaviourSpace
import mapelites.core.summary.SimpleSummary
import mapelites.core.binning.LinearBinning
import mapelites.core.selection.RandomSelection
import mapelites.core.summary.SummariseSearch
import mapelites.rainbow.MapEliteUISource
import ui.ConsoleUI.UI
import ui.elements.LabelledDataSource
import ui.elements.RectSourced

class TestSum {
    companion object {
        fun main() {
            val max = 5
            val s = Search(
                SumSpace(5, max),
                SumFitnessTrap(max),
                BehaviourSpace(arrayOf(LinearBinning(0.0, max.toDouble(), 15), LinearBinning(0.0, max.toDouble(), 30))),
                SumBehaviours(max)
            )

            s.bs.names = arrayOf("count same","avg")

            s.selection = RandomSelection()
            s.startInteractiveSearch(3000, 300)

            val adapter = MapEliteUISource(1, 0, s.bs)
            val graph = UI.sourcedGraph(0, 0, adapter.width(), adapter.height(), adapter)
            graph.alignOrigin()
            val summaryRect = UI.rect(0, adapter.height()+7,50,20, "")
            val summary = SummariseSearch(SimpleSummary())
            RectSourced.defaultSymbol = " "
            RectSourced.maxSymbol = "*"

            while (!s.searchIsOver()) {
                s.interactiveSearch(15)
                adapter.update()
                summaryRect.update(summary.summarise(s))
                UI.draw()
            }

        }
    }
}