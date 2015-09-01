package programmingpraxis

import scala.Nothing;

object DistinctProducts {

  def dpOn2(n: Int): Int =
    (for (i <- 1 to n; j <- 1 to n) yield i * j).toSet.size
                                                  //> dpOn2: (n: Int)Int

  def dpOn2p2(n: Int): Int =
    (for (i <- 1 to n; j <- i to n) yield i * j).toSet.size
                                                  //> dpOn2p2: (n: Int)Int

  def dpVarSet(n: Int): Int = {
    var s = Set[Int]()
    for (i <- 1 to n; j <- i to n) { s += i * j }
    s.size
  }                                               //> dpVarSet: (n: Int)Int

  def dpPlus(n: Int): Int = {
    var s = Set[Int]()
    for (i <- 1 to n) { var c = i*i; for (j <- i to n) { s += c; c += i } }
    s.size
  }                                               //> dpPlus: (n: Int)Int

  def time[R](block: => R): (R,Long) = {
    val t0 = System.nanoTime()
    val result = block // call-by-name
    val t1 = System.nanoTime()
    (result, t1 - t0)
  }                                               //> time: [R](block: => R)(R, Long)

  def timeN[R](block: => R, n:Int=10): String = {
  	var full:Long=0;
  	for(x<-1 to n-1){full+=time(block)._2}
  	val last = time(block)
  	full+=last._2
    last._1 + "  Avarage time: " + (full/n) / 1000000 + "ms"
  }                                               //> timeN: [R](block: => R, n: Int)String

  timeN(dpOn2(1000))                              //> res0: String = 248083  Avarage time: 655ms
  timeN(dpOn2p2(1000))                            //> res1: String = 248083  Avarage time: 266ms
  timeN(dpVarSet(1000))                           //> res2: String = 248083  Avarage time: 225ms
  timeN(dpPlus(1000))                             //> res3: String = 248083  Avarage time: 220ms

}