package eu.eyan.hopper

import scala.reflect.io.File
import java.nio.file.Files
import scala.io.Source

object Hopper extends App {

	class RouteAndMax(val route: List[Int], val max: Int)
	
  def findHops(fileName: String): String = {
    val valuesFromFile = Source.fromFile(fileName).getLines().map { line => line.toInt }
    if(valuesFromFile.isEmpty) "faliure"
    else {
      val first = valuesFromFile.next()
      hopper(valuesFromFile, 1, first, List(new RouteAndMax(List(0), first)))
    }
  }


  /**
   * Tail Recursive solution
   */
  def hopper(input: Iterator[Int], index: Int, max: Int, routes: List[RouteAndMax]): String = {

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
        hopper(input, index + 1, newMax, newRoutes)
      } else
        hopper(input, index + 1, max, validRoutes)
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

  override def main(args: Array[String]) {
//    println(hopper(List(1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1).iterator))
    //    println(hopper(List(5, 6, 0, 4, 2, 4, 1, 0, 0, 4).iterator))
    //    println(hopper(List(0).iterator))
    //    println(hopper(List().iterator))
    //    println(hopper(List(3, 1, 1, 1).iterator))
    //    println(hopper(List(3, 1, 1, 1, 0).iterator))
    //    println(hopper(List(4, 3, 2, 1).iterator))
    //    println(hopper(List(4, 3, 2, 1, 0).iterator))
    //    println(hopper(List(4, 3, 2, 1, 0).iterator))
  }
}