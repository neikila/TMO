package tasks

import models._

/**
  * Created by neikila.
  */
class Test extends Task{
  def execute: Unit = {

    val lambda = 23
    val mu = 5

//    val result = new NotLimitedQueueWithMultiThread(lambda, mu, 5)

//    val result: Model = new QueueWithMultiThread(lambda, mu, 11, 9)
//    val result: Model = new QueueWithMiltiThread(lambda, mu, 7, 6)
//    val result = new BusyQueueMultiThread(lambda, mu, 6, 3)
    val result: Model = new NoQueueMultiThread(lambda, mu, 3)

//    println("Reject = " + result.getReject)
    println("getLoad = " + result.getLoad)
    println("getReject = " + result.getReject)
    // сошлось =)
  }
}
