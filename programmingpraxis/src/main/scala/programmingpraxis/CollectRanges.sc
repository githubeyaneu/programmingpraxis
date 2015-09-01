package programmingpraxis

object CollectRanges {
  val numbers = List(0, 1, 2, 7, 21, 22, 108, 109)//> numbers  : List[Int] = List(0, 1, 2, 7, 21, 22, 108, 109)

  def collectRanges(in: Stream[Int], left: Option[Int] = None, right: Option[Int] = None): Stream[Tuple2[Int, Int]] = {
    println(in.toList+" "+left+" "+right)
    if (left == None)
      if (in.isEmpty) Stream.Empty
      else collectRanges(in.tail, Some(in.head), Some(in.head))
    else
      if (in.isEmpty) Stream((left.get, right.get))
      else if (in.head == right.get + 1) collectRanges(in.tail, left, Some(right.get + 1))
      else Stream((left.get, right.get)) #::: collectRanges(in)
  }                                               //> collectRanges: (in: Stream[Int], left: Option[Int], right: Option[Int])Strea
                                                  //| m[(Int, Int)]

  collectRanges(Stream.Empty).toList              //> List() None None
                                                  //| res0: List[(Int, Int)] = List()
  collectRanges(Stream(42)).toList                //> List(42) None None
                                                  //| List() Some(42) Some(42)
                                                  //| res1: List[(Int, Int)] = List((42,42))
  collectRanges(Stream(42, 43, 45)).toList        //> List(42, 43, 45) None None
                                                  //| List(43, 45) Some(42) Some(42)
                                                  //| List(45) Some(42) Some(43)
                                                  //| List(45) None None
                                                  //| List() Some(45) Some(45)
                                                  //| res2: List[(Int, Int)] = List((42,43), (45,45))
  collectRanges(numbers.toStream).toList          //> List(0, 1, 2, 7, 21, 22, 108, 109) None None
                                                  //| List(1, 2, 7, 21, 22, 108, 109) Some(0) Some(0)
                                                  //| List(2, 7, 21, 22, 108, 109) Some(0) Some(1)
                                                  //| List(7, 21, 22, 108, 109) Some(0) Some(2)
                                                  //| List(7, 21, 22, 108, 109) None None
                                                  //| List(21, 22, 108, 109) Some(7) Some(7)
                                                  //| List(21, 22, 108, 109) None None
                                                  //| List(22, 108, 109) Some(21) Some(21)
                                                  //| List(108, 109) Some(21) Some(22)
                                                  //| List(108, 109) None None
                                                  //| List(109) Some(108) Some(108)
                                                  //| List() Some(108) Some(109)
                                                  //| res3: List[(Int, Int)] = List((0,2), (7,7), (21,22), (108,109))
}