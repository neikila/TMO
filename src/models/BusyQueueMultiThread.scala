package models

import utils.Utils._

/**
  * Created by neikila.
  */
class BusyQueueMultiThread (val lambda: Double, val mu: Double, val units: Int, val nu: Double) extends Model {

  val a = lambda / mu

  var probabilitiesDivP0 = Vector.tabulate(units)(spec(_))

  val p0 = 1.0 / {
      while (probabilitiesDivP0.last > 0.01)
        getProbabilityDivPo(1 + probabilitiesDivP0.length)
      probabilitiesDivP0.par.sum
    }

  val probabilities = probabilitiesDivP0.par.map(_ * p0)

  val queueAverageSize = (0.0 /: (0 until probabilities.length).par)((sum, index) => {
    sum + {
      if (index < units)
        0
      else
        (index - units) * getProbability(index)
    }
  })

  def last = probabilities.last

  override def getReject: Double = ???

  override def getLoad: Double = ???

  override def getProbability(i: Int): Double = probabilities(i)

  override def getProbabilityDivPo(i: Integer): Double = {
    if (probabilitiesDivP0.length <= i) {
      val prob = getProbabilityDivPo(i - 1) * lambda / (mu * units + (i - units) * nu)
      probabilitiesDivP0 = probabilitiesDivP0 :+ prob
    }
    probabilitiesDivP0(i)
  }

  def spec(i: Integer): Double = {
    math.pow(a, i.toDouble) / factor(i)
  }
}
