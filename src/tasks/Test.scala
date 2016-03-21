package tasks

import models.{NoQueueMultiThread, Model}

/**
  * Created by neikila.
  */
class Test extends Task{
  def execute: Unit = {

    val lambda = 37
    val mu = 7

    var result: Model = new NoQueueMultiThread(lambda, mu, 6)

    println("Reject = " + result.getReject)
    println("Load = " + result.getLoad)
    // сошлось =)
  }
}
