import models.{Model, NoQueueMultiThread}
import tasks.Task1

/**
  * Created by neikila.
  */

object main {
  def main(args: Array[String]): Unit = {
    new Task1().execute
  }
}
