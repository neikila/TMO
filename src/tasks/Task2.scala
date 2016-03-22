package tasks

import java.io.File

import models.QueueWithMultiThread
import utils.Gnuplot

/**
  * Created by neikila.
  */
class Task2 (val lambda: Double, val mu: Double, val start: Int) extends Task {
  var resultsProb = List[(Int, Double)]()
  var resultsLoad = List[(Int, Double)]()

  val dir = "out/Task2/"

  override def execute: Unit = {
    println()
    println("   Task 2   ")
    println("Start from " + start)
    var i = start

    var result: QueueWithMultiThread = null
    do {
      result = new QueueWithMultiThread(lambda, mu, start + 1, i)
      resultsProb = (i, result.getReject) +: resultsProb
      resultsLoad = (i, result.getLoad) +: resultsLoad
      i -= 1
    } while (i > 0)

    resultsProb.foreach((result) =>
      println("i = " + result._1 + " prob = " + result._2)
    )

    i = 0

    val plotter = new Gnuplot(dir)
    plotter.printToFile("Reject probability", resultsProb)
    plotter.printToFile("Load", resultsLoad)

    plotter.createGnuplotScript("gnuplotScript", "Probability", "Units")
  }
}
