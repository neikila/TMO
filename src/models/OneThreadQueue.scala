package models

/**
  * Created by neikila.
  */
class OneThreadQueue (val lambda: Double, val mu: Double, val n: Integer) extends Model {

  val a = lambda / mu
  val p0 = 1.0 / List.tabulate(n + 1)(num => getProbabilityDivPo(num)).sum
  val probabilities: IndexedSeq[Double] = IndexedSeq.tabulate(n + 1)(num => p0 * getProbabilityDivPo(num))

  def getLoad: Double = 1 - p0

  def getReject: Double = probabilities(n)

  override def getProbability(i: Int): Double = probabilities(i)

  override def getProbabilityDivPo(i: Integer): Double = math.pow(a, i.toDouble)
}
