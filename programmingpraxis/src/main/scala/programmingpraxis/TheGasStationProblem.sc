package programmingpraxis

object TheGasStationProblem {

  val gallons = List(15, 8, 2, 6, 18, 9, 21, 30)  //> gallons  : List[Int] = List(15, 8, 2, 6, 18, 9, 21, 30)
  val nextTrip = List(8, 6, 30, 9, 15, 21, 2, 18) //> nextTrip  : List[Int] = List(8, 6, 30, 9, 15, 21, 2, 18)

  /* O(n*n) */
  val size = gallons.size                         //> size  : Int = 8
  def setStart[A](l: List[A], start: Int):List[A] = {
    l.slice(start, l.size) ++ l.slice(0, start)
  }                                               //> setStart: [A](l: List[A], start: Int)List[A]
  setStart((gallons.zip(nextTrip)), 2)            //> res0: List[(Int, Int)] = List((2,30), (6,9), (18,15), (9,21), (21,2), (30,18
                                                  //| ), (15,8), (8,6))
  def checkRoute(route: List[Tuple2[Int, Int]], gas:Int=0):Boolean={
  	if(route.isEmpty) gas>=0
  	else if(gas + route.head._1 - route.head._2 < 0) false
  	else checkRoute(route.tail, gas + route.head._1 - route.head._2)
  }                                               //> checkRoute: (route: List[(Int, Int)], gas: Int)Boolean
  for(i<-0 until gallons.size) yield (checkRoute(setStart(gallons zip nextTrip, i)))
                                                  //> res1: scala.collection.immutable.IndexedSeq[Boolean] = Vector(false, false, 
                                                  //| false, false, false, false, true, false)
  /* O(n*n)  END */
  
  /* O(2*n) */
  val plusGas = gallons zip nextTrip map{ x=> x._1-x._2 }
                                                  //> plusGas  : List[Int] = List(7, 2, -28, -3, 3, -12, 19, 12)
  val start = plusGas.sum                         //> start  : Int = 0
  def check2n(route: List[Int], gas:Int){
  	
  }
}