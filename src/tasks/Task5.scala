package tasks

import models.{ClosedMultiThread, QueueWithMultiThread, BusyQueueMultiThread}
import utils.Gnuplot

/**
  * Created by neikila.
  */
class Task5 extends Task {
  val lambda = 1.0 / 369.0
  val mu = 1.0 / 9.0
  val machines = 43

  var load = List[(Int, BigDecimal)]()
  var broken = List[(Int, BigDecimal)]()

  val dir = "out/Task5/"

  override def execute: Unit = {
    println()
    println("   Task 5   ")
    println("a = " + lambda / mu)

    (1 to machines).foreach((index) => {
      val result = new ClosedMultiThread(lambda, mu, machines, index)
      load = load :+ (index, result.getLoad)
      broken = broken :+ (index, result.machinesBroken)
    })

    load.foreach((result) =>
      println("units = " + result._1 + " load = " + result._2)
    )

    broken.foreach((result) =>
      println("units = " + result._1 + " machines broken = " + result._2)
    )

    val plotter = new Gnuplot(dir)
    plotter.printToFileBigDecimal("Units loaded", load)
    plotter.printToFileBigDecimal("Machines broken", broken)

    plotter.createGnuplotScript("gnuplotScript", "Percent", "Units")
  }
}
