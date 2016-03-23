package eu.eyan.hopper

import scala.reflect.io.File
import java.nio.file.Files
import scala.io.Source

object Hopper extends App {

  class RouteAndMax(val route: List[Int], val max: Int)

  def findHops(fileName: String): String = {
    val valuesFromFile = Source.fromFile(fileName).getLines().map { line => line.toInt }
    if (valuesFromFile.isEmpty) "faliure"
    else {
      val first = valuesFromFile.next()
      hopperRec(valuesFromFile, List(0), first, 0, first, 0)
    }
  }

  def hopperStart(input: Iterator[Int]): String = {
    if (input.isEmpty) "faliure"
    else {
      val first = input.next()
      hopperCheckNewCandidate(input, List(0), first, 0, first, 0)
    }
  }
  def hopperCheckNewCandidate(input: Iterator[Int], bestRoute: List[Int], bestRouteMax: Int, index: Int, maxCandidate: Int, candidateIndex: Int): String = {
    ""
  }

  def hopperRec(input: Iterator[Int], bestRoute: List[Int], bestRouteMax: Int, index: Int, maxCandidate: Int, candidateIndex: Int): String = {
    Thread.sleep(1000)
    println(bestRoute + " | " + bestRouteMax + "      " + index + "  " + (maxCandidate, candidateIndex))
    if (!input.hasNext) {
      println("nonext")
      if (index <= bestRouteMax) (bestRoute ++ List("opt")).mkString(", ")
      else "failure"
    } else if (bestRouteMax == index) {
      println("osszegzes")
      hopperRec(input, bestRoute ++ List(candidateIndex), maxCandidate, index, maxCandidate, candidateIndex)
    } else {
      println("kovi")
      val nextValue = input.next
      if (maxCandidate <= index + 1 + nextValue) {
        println("uj kandidans")
        hopperRec(input, bestRoute, bestRouteMax, index + 1, nextValue, index + 1)
      } else {
        println("kovetkezo elem uj kandidans nelkul")
        hopperRec(input, bestRoute, bestRouteMax, index + 1, maxCandidate, candidateIndex)
      }
    }
  }

  // 0  1  2  3  4  5  6  7
  // 4, 5, 1, 1, 0, 3, 2, 1
  //
  //index, max
  //-----------
  //0 | 4          0
  //-----
  //0,1 | 6        1
  //-0,2 | 3       2
  //-0,3 | 4       3
  //-0,4 | 4       4
  //--------
  //0,1,5 | 8      5
  //0,1,6 | 8      6
  //---------
  //0,1,6,7 | 8 
  //0,1,6,8 | -> ready

  def hopperFileRecursive(fileName: String): String = {
    println( (0 until Source.fromFile(fileName).getLines().size).mkString(", "))
    println(Source.fromFile(fileName).getLines().map { line => line.toInt }.mkString(", "))
    val input = Source.fromFile(fileName).getLines().map { line => line.toInt }
    if (input.isEmpty) "failure"
    else {
      val first = input.next
      hopperRecursive(input, List(0), first, 0, first, 0)
    }
  }

  def hopperRecursive(input: Iterator[Int], route: List[Int], max: Int, index: Int, maxCand: Int, maxCandIndex: Int): String = {
    println((route, "max="+max, "index="+index, "maxCand="+maxCand, "maxCandIndex="+maxCandIndex))
    if (input.nonEmpty) {
      val next = input.next

      if (maxCand <= next + index + 1)
        if (max == index + 1)
          if (maxCand <= index + 1) "failure"
          else hopperRecursive(input, route ++ List(maxCandIndex), maxCand, index + 1, next + index + 1, index + 1)
        else hopperRecursive(input, route, max, index + 1, next + index + 1, index + 1)
      else if (max == index + 1)
        if (maxCand <= index + 1) "failure"
        else hopperRecursive(input, route ++ List(maxCandIndex), maxCand, index + 1, maxCand, maxCandIndex)
      else hopperRecursive(input, route, max, index + 1, maxCand, maxCandIndex)
    } else if (index < max) (route ++ List("opt")).mkString(" ,")
    else "failure"
  }

  def hopperFile(fileName: String): String = hopper(Source.fromFile(fileName).getLines().map { line => line.toInt })
  def hopper(input: Iterator[Int]): String = {
    if (input.isEmpty) "failure"
    else {
      var route = List(0)
      var max = input.next
      var index = 0

      var maxCand = max
      var maxCandIndex = index
      while (input.hasNext) {
        val next = input.next
        index += 1

        if (maxCand <= next + index) {
          maxCand = next + index
          maxCandIndex = index
        }

        if (max == index) {
          route = route ++ List(maxCandIndex)
          max = maxCand
          maxCandIndex = maxCandIndex

          if (maxCand <= index) return "failure"
        }
      }

      if (index < max) (route ++ List("opt")).mkString(" ,")
      else "failure"
    }
  }

  override def main(args: Array[String]) {
    //println("eredmeny: "+ hopper(List().iterator, List(), -1,0,0,0)  )

    //    println("eredmeny: " + hopperStart(List(1).iterator))
    //    println("eredmeny: " + hopperStart(List(4, 5, 1, 1, 0, 3, 2, 1).iterator))
    //    println("eredmeny: " + hopperStart(List(4, 5, 1, 1, 0, 3, 2, 1).iterator))
    //    println("eredmeny: " + hopper(List().iterator))
    //    println("eredmeny: " + hopper(List(0).iterator))
    //    println("eredmeny: " + hopper(List(1).iterator))
    //    println("eredmeny: " + hopper(List(4, 5, 1, 1, 0, 3, 2, 1).iterator))

    println(hopperRecursive(List( /*4, */ 5, 1, 1, 0, 3, 2, 1).iterator, List(0), 4, 0, 4, 0))
    //hopper(List(4, 5, 1, 1, 0, 3, 2, 1).iterator, List(), -1, 0, 0, 0)

    //    println(hopper(List(1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1).iterator))
    //    println(hopper(List(5, 6, 0, 4, 2, 4, 1, 0, 0, 4).iterator))
    //    println(hopper(List(0).iterator))
    //    println(hopper(List().iterator))
    //    println(hopper(List(3, 1, 1, 1).iterator))
    //    println(hopper(List(3, 1, 1, 1, 0).iterator))
    //    println(hopper(List(4, 3, 2, 1).iterator))
    //    println(hopper(List(4, 3, 2, 1, 0).iterator))
    //    println(hopper(List(4, 3, 2, 1, 0).iterator))
    //hopper(List(5, 1, 1, 0, 3, 2, 1).iterator, List(0), 4, 1, 4, 0)
  }

  /**
   * Tail Recursive solution
   */
  def hopper_frey(input: Iterator[Int], index: Int, max: Int, routes: List[RouteAndMax]): String = {

    val validRoutes = routes.filter(_.max >= index) // filter out routes that cannot lead to index
    val noMoreValues = input.isEmpty
    val noValidRoutesToIndex = validRoutes.isEmpty && index != 0

    if (noValidRoutesToIndex) "failure"
    else if (noMoreValues) (validRoutes(0).route ++ List("out")).mkString(", ")
    else {
      val next = input.next()
      //  if the item extends the max then create a new route an new max.  // (optimalization: if next=0 dont do anythong)
      if (next != 0 && max < index + next) {
        val newMax = index + next
        val extendableRoutes = validRoutes.filter(r => r.max < index + next)
        val newRoutes = validRoutes ++ List(new RouteAndMax(extendableRoutes(0).route ++ List(index), newMax))
        hopper_frey(input, index + 1, newMax, newRoutes)
      } else
        hopper_frey(input, index + 1, max, validRoutes)
    }
  }

  /**
   * Non recursive solution
   */
  def hopperNr(input: Iterator[Int]): String = {

    if (input.isEmpty) return "failure"

    // Initial state
    var max = input.next()
    var routes: List[RouteAndMax] = List(new RouteAndMax(List(0), max))

    var index = 1
    while (input.hasNext && routes.nonEmpty) {
      val next = input.next()

      //  filter routes thats max does not contain index:
      routes = routes.filter(r => r.max >= index)

      //  if the item extends the max then create a new route an new max.  // (optimalization: if next=0 dont do anythong)
      if (next != 0 && max < index + next) {
        max = index + next
        val extendableRoutes = routes.filter(r => r.max < index + next)
        routes = routes ++ List(new RouteAndMax(extendableRoutes(0).route ++ List(index), max))
      }
      index += 1
    }

    routes = routes.filter(r => r.max >= index)

    if (routes.isEmpty)
      "failure"
    else
      (routes(0).route ++ List("out")).mkString(", ")
  }

  // 0  1  2  3  4  5  6  7
  // 4, 5, 1, 1, 0, 3, 2, 1
  //
  //index, max
  //-----------
  //0 | 4          0
  //-----
  //0,1 | 6        1
  //-0,2 | 3       2
  //-0,3 | 4       3
  //-0,4 | 4       4
  //--------
  //0,1,5 | 8      5
  //0,1,6 | 8      6
  //---------
  //0,1,6,7 | 8 
  //0,1,6,8 | -> ready

}