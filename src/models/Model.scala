package models

/**
  * Created by neikila.
  */
trait Model {

  def getLoad: Double

  def getReject: Double

  def getProbability(i: Int): Double

  def getProbabilityDivPo(i: Integer): Double
}
