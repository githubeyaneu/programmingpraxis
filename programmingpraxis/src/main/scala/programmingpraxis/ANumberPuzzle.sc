package programmingpraxis

object ANumberPuzzle {
  def findNext(l: Seq[Long] = (0l to 9l), size: Int = 1): Seq[Long] = {
    if (size == 10) l
    else findNext((for (i <- 0 to 9) yield l map { x => x * 10 + i }).flatten filter (x => x % (size + 1) == 0 && x.toString().toSet.size == size + 1) sorted, size + 1)
  }                                               //> findNext: (l: Seq[Long], size: Int)Seq[Long]

  findNext()                                      //> res0: Seq[Long] = Vector(3816547290)

  123.toString.sorted                             //> res1: String = 123
  (1 to 3).mkString.sorted                        //> res2: String = 123

}