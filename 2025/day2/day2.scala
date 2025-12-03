import scala.io.Source.fromFile

object Solution2 {
  def main(args: Array[String]): Unit = {
    println(id_Ranges())
  }

  def len(x: Any): Int = x.toString().size

  def id_Ranges(): Long = {
    // val file = "./example.txt"
    val file = "./input.txt"
    val str: Seq[String] =
      fromFile(file).getLines().toList(0).replace(',', '-').split('-')
    // str.foreach(println)
    val m = str.grouped(2).collect { case Seq((a: String), (b: String)) =>
      (a, b)
    }
    // m.foreach(println)
    // val ints = m.map { case (s1,s2) => (s1.toInt, s2.toInt) }.toMap
    // ints.foreach { case (i1,i2) => println(s"1: ${i1} ; 2: ${i2}") }
    val fullInts: Vector[Vector[String]] = m.collect { case (i1, i2) =>
      { for (i <- i1.toLong to i2.toLong) yield i.toString() }.toVector
    }.toVector
    // fullInts.foreach(println)
    val r = fullInts.map {
      case (vec) => {
        vec.map(l => (l, len(l)%2==0))
      }
    }.toVector.flatten.filter(_._2==true).map(_._1)
    // r.foreach(println)
    val s: Seq[Long] = r.collect { case (str) => {
      val l = len(str)/2
      val strTemp = str
      val left = str.dropRight(l)
      val right = strTemp.drop(l)
      if (left.toInt.compare(right.toInt)==0) { (left+right).toLong } else 0
    } }
    // s.foreach(println)
    // println(s.sum)
    s.sum
  }
}
