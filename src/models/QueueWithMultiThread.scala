package models

/**
  * Created by neikila.
  */
class QueueWithMultiThread(val lambda: Double, val mu: Double, val states: Integer, val units: Integer) extends Model {
  import utils.Utils._

  val a = lambda / mu
  val p0 = 1.0 / (0 until states).foldLeft(0.0)(_ + getProbabilityDivPo(_))
  val probabilities: IndexedSeq[Double] = IndexedSeq.tabulate(states)(num => p0 * getProbabilityDivPo(num))

  val load = {
    var counter = -1
    probabilities.foldLeft(0.0)((a, b) => { if (counter < units) counter += 1; a + counter * b })
  }

  override def getLoad: Double = load

  override def getReject: Double = probabilities(states - 1)

  override def getProbability(i: Int): Double = probabilities(i)

  val const = spec(units)
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
