package tasks

import models._

/**
  * Created by neikila.
  */
class Test extends Task{
  def execute: Unit = {
    import utils.Utils._

    val lambda = 1 / 30.0
    val mu = 1 / 123.0
    val nu = mu / 2.0

//    new Task3(lambda, mu, 10).execute
//    val result = new NotLimitedQueueWithMultiThread(lambda, mu, 5)

//    val result: Model = new QueueWithMultiThread(lambda, mu, 11, 9)
//    val result: Model = new QueueWithMiltiThread(lambda, mu, 7, 6)
//    val result = new BusyQueueMultiThread(lambda, mu, 6, 3)
//    val result: Model = new NoQueueMultiThread(lambda, mu, 3)
//
//    println("getLoad = " + result.getLoad)
//    println("getReject = " + result.getReject)

    val result = new ClosedMultiThread(2, 3, 7, 2)
    println("load = " + result.getLoad)
    println("broken = " + result.machinesBroken * 7)
  }
}
