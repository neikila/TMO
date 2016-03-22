package tasks

import java.io.File

import models.NoQueueMultiThread
import utils.Gnuplot

/**
  * Created by neikila.
  */
class Task1 (val lambda: Double, val mu: Double) extends Task {
  val label: IndexedSeq[Double] = IndexedSeq(0.25, 0.1, 0.05, 0.01)

  var onePercent = -1

  var resultsProb = List[(Int, Double)]()
  var resultsLoad = List[(Int, Double)]()

  val dir = "out/Task1/"
  override def execute: Unit = {

    var i = 1

    var result: NoQueueMultiThread = null
    do {
      result = new NoQueueMultiThread(lambda, mu, i)
      resultsProb = resultsProb :+ (i, result.getReject)
      resultsLoad = resultsLoad :+ (i, result.getLoad)
      i += 1
    } while (result.getReject > 0.01)

    resultsProb.foreach((result) =>
      println("i = " + result._1 + " prob = " + result._2)
    )

    i = 0
    resultsProb.foreach((result) =>
      if (label(i) > result._2) {
        println("First under " + label(i) * 100)
        println("Unit amount: " + result._1 + " Probability: " + result._2)
        i += 1
        if (label.length == i)
          onePercent = result._1
      }
    )

    val plotter = new Gnuplot(dir)
    plotter.printToFile("Reject probability", resultsProb)
    plotter.printToFile("Load", resultsLoad)

    plotter.createGnuplotScript("gnuplotScript", "Probability", "Units")
  }
}
