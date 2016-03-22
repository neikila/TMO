package models

/**
  * Created by neikila.
  */
trait Model {

  def getReject: Double

  def getLoad: Double

  def getProbability(i: Int): Double

  def getProbabilityDivPo(i: Integer): Double
}
