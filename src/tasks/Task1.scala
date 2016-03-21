package tasks

import models.{NoQueueMultiThread, Model}
import utils.Gnuplot

/**
  * Created by neikila.
  */
class Task1 extends Task {
  val metki: IndexedSeq[Double] = IndexedSeq(0.25, 0.1, 0.05, 0.01)

  override def execute: Unit = {

    var i = 1
    val lambda = 23
    val mu = 9

    var result: Model = new NoQueueMultiThread(lambda, mu, i)
    var resultsProb = List[(Int, Double)]((i, result.getReject))
    var resultsLoad = List[(Int, Double)]((i, result.getLoad))

    while(result.getReject > 0.01) {
      i += 1
      result = new NoQueueMultiThread(lambda, mu, i)
      resultsProb = resultsProb :+ (i, result.getReject)
      resultsLoad = resultsLoad :+ (i, result.getLoad)
    }

    i = 0
    resultsProb.foreach((result) =>
      if (metki(i) > result._2) {
        println("First under " + metki(i) * 100)
        println("Unit amount: " + result._1 + " Probability: " + result._2)
        i += 1
      }
    )

    val plotter = new Gnuplot("out/")
    plotter.printToFile("Reject probability", resultsProb)
    plotter.printToFile("Load", resultsLoad)

    plotter.createGnuplotScript("gnuplotScript", "Probability", "Units")
  }
}
