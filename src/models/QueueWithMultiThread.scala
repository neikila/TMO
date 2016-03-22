package models

import utils.Utils._

/**
  * Created by neikila.
  */
class QueueWithMultiThread (val lambda: Double, val mu: Double, val states: Integer, val units: Integer)
  extends Model {

  val a = lambda / mu

  val const = spec(units)

  val p0 = 1.0 / (0.0 /: (0 until states))(_ + getProbabilityDivPo(_))
  val probabilities: IndexedSeq[Double] = IndexedSeq.tabulate(states)(num => p0 * getProbabilityDivPo(num))

  val load = {
    var counter = -1
    probabilities.foldLeft(0.0)((a, b) => { if (counter < units) counter += 1; a + counter * b })
  }

  def getLoad: Double = load / units

  def getReject: Double = probabilities(states - 1)

  override def getProbability(i: Int): Double = probabilities(i)

  override def getProbabilityDivPo(i: Integer): Double = {
    if (i > units)
      const * math.pow(a / units, i - units)
    else
      spec(i)
  }

  def spec(i: Integer): Double = {
    math.pow(a, i.toDouble) / factor(i)
  }
}
