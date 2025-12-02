import scala.io.Source.fromFile

object Solution {
  def main(args: Array[String]): Unit = {
    // val file = "./example.txt"
    val file = "./input.txt"
    println(s"onto: ${dial(file, 50)}")
  }

  def dial(file: String, input: Int): Int = {
    var ontoZero = 0
    var sum: Int = input
    val s = fromFile(file).getLines().toSeq.map(t => t.splitAt(1)).collect {
      case (rotation, distance) => {
        var temp = distance.toInt;
        (if (temp > 99) { while (temp > 99) { temp -= 100 } }); (rotation, temp)
      }
    }
    val r = s.map {
      case (rotation, distance) => { /*println(sum);*/ if (rotation == "R") {
          sum += (distance.toInt - (if (sum + distance.toInt > 99) { 100 } else 0))
        } else {
          sum -= (distance.toInt - (if (sum - distance.toInt < 0) { 100 } else 0))
        }
        if (sum == 0) ontoZero += 1
      }
    }
    ontoZero
  }
}
