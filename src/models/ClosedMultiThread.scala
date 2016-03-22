package models

import utils.Utils._

/**
  * Created by neikila.
  */
class ClosedMultiThread (val lambda: Double, val mu: Double, val machines: Integer, val units: Int) {

  val a = lambda / mu

  val probDivP0 = List.tabulate(machines + 1)(getProbabilityDivPo(_))
  val p0 = 1.0 / probDivP0.sum
  val probabilities = probDivP0.par.map(p0 * _)

//  probDivP0.foreach((num) => println(num))
//  println()
//  probabilities.foreach((num) => println(num))

  val load = (BigDecimal(0.0) /: (0 until probabilities.length).par)((sum, index) => {
    sum + {
      if (index > units)
        units
      else
        index
    } * getProbability(index)
  })

  val machinesBroken = (BigDecimal(0.0) /: (0 until probabilities.length).par)((sum, index) => {
    sum + index * getProbability(index)
  }) / BigDecimal(machines)

  def getLoad = load / units

  def getProbability(i: Int): BigDecimal = probabilities(i)

  def getProbabilityDivPo(i: Integer): BigDecimal = {
    BigDecimal(reducedFactor(machines, i)) / {
      if (i < units)
        BigDecimal(factorBigInt(i))
      else
        BigDecimal(factorBigInt(units)) * math.pow(units, i - units)
    } * BigDecimal(a).pow(i)
  }
}
