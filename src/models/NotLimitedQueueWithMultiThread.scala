package models

import utils.Utils._

/**
  * Created by neikila.
  */
class NotLimitedQueueWithMultiThread(val lambda: Double, val mu: Double, val units: Integer) extends Model {

  val a = lambda / mu

  val const = spec(units)

  val p0 = 1.0 / (
    (0.0 /: (0 to units)) (_ + getProbabilityDivPo(_)) +
      a / (units - a) * getProbabilityDivPo(units)
    )

  val queueAverageSize = getProbability(units) * a / units / math.pow(1 - a / units, 2)

  override def getLoad: Double = ???

  override def getProbability(i: Int): Double = p0 * getProbabilityDivPo(i)

  override def getProbabilityDivPo(i: Integer): Double = {
    if (i > units)
      const * math.pow(a / units, i - units)
    else
      spec(i)
  }

  def spec(i: Integer): Double = {
    math.pow(a, i.toDouble) / factor(i)
  }

  override def getReject: Double = 0
}
