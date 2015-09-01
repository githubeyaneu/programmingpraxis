package programmingpraxis

// http://programmingpraxis.com/2015/07/10/partitioning-the-telephone-book/
object TelefonPartitioning {
  val letters = List("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "0", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")
  val num = List(16, 4, 17, 10, 15, 4, 4, 6, 7, 14, 9, 17, 27, 6, 1, 9, 0, 12, 20, 8, 0, 3, 4, 0, 3, 4)
  val volumes = 4                                
  val letterNumbers = letters zip num             

  def partitionsEndCounters(n: Int, parts: Int): Seq[Seq[Int]] = {
    if (parts == 1) {
      List(List(n))
    } else {
      for (
        endIndex <- 1 to n - parts +1;
        rest <- partitionsEndCounters(n - endIndex, parts-1)
      ) yield Seq(endIndex) ++ rest.map(x => x + endIndex)
    }
  }                                               

  val total = num.sum                             
  val optimal = total / volumes                   
  val partitionsEndCts = partitionsEndCounters(num.size, volumes)
  val partitionEndIndices = partitionsEndCts.map(x=> x.map { x => x-1 })
  val partitions=partitionEndIndices.map { x => for(i<-0 until x.size) yield (if(i==0) 0 else (x(i-1)+1),x(i))}
  val partitionSums = partitions.map { x => x.map { x => num.slice(x._1, x._2+1).sum } }
  val differencesSums = partitionSums.map { x => x.map { x => (optimal-x).abs }.sum }
  val min = differencesSums.min                   
  val beste = (differencesSums zip partitions) filter(p=>p._1==min)
  val bestLetters = beste.unzip._2.map { x => x.map(x=>letters(x._1)+"-"+letters(x._2))}
                                                  //> bestLetters  : Seq[scala.collection.immutable.IndexedSeq[String]] = Vector(
                                                  //| Vector(A-D, E-K, L-P, Q-Z), Vector(A-D, E-K, L-Q, R-Z))
}