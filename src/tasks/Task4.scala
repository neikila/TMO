package tasks

import models.{BusyQueueMultiThread, NotLimitedQueueWithMultiThread}
import utils.Gnuplot

/**
  * Created by neikila.
  */
class Task4 (val lambda: Double, val mu: Double, val limit: Int, val nu: Double) extends Task {
  val a = lambda / mu
  var resultsQueueSize = List[(Int, Double)]()

  val dir = "out/Task4/"

  override def execute: Unit = {
    println()
    println("   Task 4   ")

    var i = 1
    var model: BusyQueueMultiThread = null
    do {
      model = new BusyQueueMultiThread(lambda, mu, i, nu)
      resultsQueueSize = resultsQueueSize :+ (i, model.queueAverageSize)
      i += 1
    } while (i <= limit)

    resultsQueueSize.foreach((result) =>
      println("i = " + result._1 + " Queue Average size = " + result._2)
    )

    val plotter = new Gnuplot(dir)
    plotter.printToFile("Queue average size", resultsQueueSize)

    plotter.createGnuplotScript("gnuplotScript", "Queue size", "Units")
  }
}
