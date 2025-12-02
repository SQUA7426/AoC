import scala.io.Source.fromFile

object Solution {
  def main(args: Array[String]): Unit = {
    // val file = "./example.txt"
    val file = "./input.txt"
    dial(file, 50)
    println()
  }
  def dial(file: String, input: Int): Int = {
    var left = 0
    var sum: Int = input
    val s = fromFile(file).getLines().toSeq.map(t => t.splitAt(1)).collect {
      case (rotation, distance) => { var temp = distance.toInt; (if (temp > 99) { while (temp>99) { temp -= 100}}); (rotation,temp)}
    }
    val r = s.map { case(rotation,distance) => /*println(sum);*/ if (rotation=="R") {if (sum + distance.toInt > 99) sum += (distance.toInt - 100) else {sum += distance.toInt}}; if (rotation=="L"){if (sum - distance.toInt < 0) { sum += (100 - distance.toInt)} else sum -= distance.toInt }; if (sum == 0) left+=1 }
    print(f"left ${left}") // 1074
    left
  }
}
