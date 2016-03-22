package utils

import java.io.{File, PrintWriter}

/**
  * Created by neikila on 05.11.15.
  */
class Gnuplot (val directory: String) {
  val graphs = collection.mutable.MutableList[String]()
  var max = -1

  new File(directory).mkdirs()

  def printToFile(fileName: String, list: List[(Int, Double)]): Unit = {
    graphs += fileName

    val out = new PrintWriter(directory + fileName)
    var num = 0
    list.foreach((tuple) => {out.println(tuple._1 + " " + tuple._2); num += 1; })
    if (list.last._1 > max) max = list.last._1
    out.close()
  }

  def createGnuplotScript(fileName: String, YTitle: String, XTitle: String): Unit = {
    val out = new PrintWriter(directory + fileName)
    var scriptCode = "set terminal x11 size 1360, 700\n" +
      "set xlabel '" + XTitle + "'\n" +
      "set ylabel '" + YTitle + "'\n" +
      "set xrange [:" + (max - 1) + "]\n" +
//      "set yrange [0:" + maxY + "]\n" +
      "set grid\n" +
      "plot "
    for (i <- 0 until (graphs.length - 1)) {
      scriptCode += "'" + graphs(i) + "' using 1:2 w l lw 2 title '" +
        graphs(i) + "',\\\n"
    }
    scriptCode += "'" + graphs.last + "' using 1:2 w l lw 2 title '" + graphs.last + "'\n" +
      "pause -1"
    out.print(scriptCode)
    out.close()
  }
}