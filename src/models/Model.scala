package models

/**
  * Created by neikila.
  */
trait Model {

  def getProbability(i: Int): Double

  def getProbabilityDivPo(i: Integer): Double
}
