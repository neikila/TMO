import utils.Utils

/**
  * Created by neikila.
  */
class CounterScala (val lambda: Double, val mu: Double, val n: Integer, val queueSize: Integer){
  import Utils._

  val a = lambda / mu
  var p0 = {
    var sum = 0.0
    for (i <- 0 to n - 1) {
      sum += (math.pow(a, i) / factor(i))
    }
    val k = math.pow(a, n.doubleValue()) / factor(n)
    for (i <- 0 to queueSize) {
      sum += k * math.pow(a / n, i)
    }
    1.0 / sum
  }

  def getP(i: Integer): Double = {
    p0 * {
      if (i <= n) {
        math.pow(a, i.doubleValue()) / factor(i)
      } else
        math.pow(a, n.doubleValue()) / factor(n) * math.pow(a / n, i.doubleValue() - n)
    }
  }

  def getQueueSize(i: Integer): Integer = {
    if (i < n) 0 else i - n
  }

  def getBusyWorkers(i: Integer): Integer = {
    if (i > n) n else i
  }
}