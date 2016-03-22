package tasks

import models.NotLimitedQueueWithMultiThread
import utils.Gnuplot

/**
  * Created by neikila.
  */
class Task3 (val lambda: Double, val mu: Double, val limit: Int) extends Task {
  val a = lambda / mu
  var resultsQueueSize = List[(Int, Double)]()

  val dir = "out/Task3/"

  override def execute: Unit = {
    println()
    println("   Task 3   ")

    val start: Int = math.ceil(a).toInt
    println("Start from " + start + " executors")

    var i = start
    var model: NotLimitedQueueWithMultiThread = null
    do {
      model = new NotLimitedQueueWithMultiThread(lambda, mu, i)
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
