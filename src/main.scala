import models.{Model, NoQueueMultiThread}
import tasks.{Test, Task1}

/**
  * Created by neikila.
  */

object main {
  def main(args: Array[String]): Unit = {
    new Test().execute
  }
}
