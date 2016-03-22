package models

import utils.Utils
import Utils._

/**
  * Created by neikila.
  */
class NoQueueMultiThread(val lambda: Double, val mu: Double, val n: Integer) extends Model {

  val a = lambda / mu
  val p0 = 1.0 / (0 to n).foldLeft(0.0)(_ + getProbabilityDivPo(_))
  val probabilities: IndexedSeq[Double] = IndexedSeq.tabulate(n + 1)(num => p0 * getProbabilityDivPo(num))

  val load = {
    var counter = -1
    probabilities.foldLeft(0.0)((a, b) => {counter += 1; a + counter * b })
  }

  override def getProbabilityDivPo(i: Integer): Double = math.pow(a, i.doubleValue()) / factor(i)

  override def getReject = getProbability(n)

  override def getLoad: Double = load

  override def getProbability(i: Int): Double = probabilities(i)
}
