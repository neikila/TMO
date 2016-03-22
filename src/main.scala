import models.{ClosedMultiThread, Model, NoQueueMultiThread}
import tasks._

/**
  * Created by neikila.
  */

object main {
  val lambda = 121 // 1 / 30.0
  val mu = 27 //1 / 123.0
  val nu = mu / 2.0

  def main(args: Array[String]): Unit = {
    production()
  }

  def production() = {
    val task1 = new Task1(lambda, mu)
    task1.execute
    val task2 = new Task2(lambda, mu, task1.onePercent)
    task2.execute
    new Task3(lambda, mu, task1.onePercent).execute
    new Task4(lambda, mu, task1.onePercent, nu).execute
    new Task5().execute
  }

  def test() = {
    new Test().execute
  }
}
