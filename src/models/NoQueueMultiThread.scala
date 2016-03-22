package models

import utils.Utils._

/**
  * Created by neikila.
  */
class NoQueueMultiThread(val lambda: Double, val mu: Double, val n: Integer) extends Model {

  val a = lambda / mu
  val p0 = 1.0 / (0 to n).par.foldLeft(0.0)(_ + getProbabilityDivPo(_))
  val probabilities: IndexedSeq[Double] = IndexedSeq.tabulate(n + 1)(num => p0 * getProbabilityDivPo(num))

  val load = {
    (0.0 /: (0 until probabilities.length).par)((sum, index) => sum + index * probabilities(index))
  }

  override def getProbabilityDivPo(i: Integer): Double = math.pow(a, i.doubleValue()) / factor(i)

  override def getReject = getProbability(n)

  override def getLoad: Double = load / n

  override def getProbability(i: Int): Double = probabilities(i)
}
