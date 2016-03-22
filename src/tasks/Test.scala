package tasks

import models.{QueueWithMultiThread, NoQueueMultiThread, Model}

/**
  * Created by neikila.
  */
class Test extends Task{
  def execute: Unit = {

    val lambda = 37
    val mu = 7

    val result: Model = new NoQueueMultiThread(lambda, mu, 6)
//    val result: Model = new QueueWithMiltiThread(lambda, mu, 7, 6)

    println("Reject = " + result.getReject)
    println("Load = " + result.getLoad)
    // сошлось =)
  }
}
