package tests.sum

import mapelites.Search
import mapelites.core.BehaviourSpace
import mapelites.core.SimpleSummary
import mapelites.core.binning.LinearBinning
import mapelites.core.selection.RandomSelection
import mapelites.rainbow.MapEliteUISource
import ui.ConsoleUI.UI
import ui.elements.DataSource
import ui.elements.RectSourced

class TestSum {
    companion object {
        fun main() {
            val max = 5
            val s = Search(
                SumSpace(5, max),
                SumFitnessTrap(max),
                BehaviourSpace(arrayOf(LinearBinning(0, max, 5), LinearBinning(0, max, 30))),
                SumBehaviours(max)
            )
            s.selection = RandomSelection()
            s.startInteractiveSearch(3000, 300)

            val adapter = MapEliteUISource(1, 0, s.bs)
            UI.sourcedRect(0, 0, adapter.width(), adapter.height(), adapter)
            val summaryRect = UI.rect(0, adapter.height()+1,50,50, "")
            val summary = SimpleSummary()
            RectSourced.defaultSymbol = "•"
            RectSourced.maxSymbol = "*"

            while (!s.searchIsOver()) {
                s.interactiveSearch(15)
                adapter.update()
                summaryRect.update(summary.summarise(s.bs))
                UI.draw()
            }

        }
    }
}