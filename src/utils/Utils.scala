package utils

/**
  * Created by neikila.
  */
object Utils {
  def factor(num: Int): Int = (1 to num).product

  def factorBigInt(num: Int): BigInt = (BigInt(1) to BigInt(num)).product
  def reducedFactor(num: Int, reduce: Int): BigInt = (BigInt(1) /: (BigInt(num) until BigInt(num - reduce) by -1))(_ * _)
}
