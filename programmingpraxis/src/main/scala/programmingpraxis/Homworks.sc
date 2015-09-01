package programmingpraxis

import scala.io.Source
import java.io.PrintWriter

object Homworks {

  /*
 http://programmingpraxis.com/2015/08/04/three-homework-problems/
*/
  // 1. Write a function that takes as input three positive integers and finds the sum of the squares of the two largest of the three.
  def largestSquares(a: Int, b: Int, c: Int): Int = {
    List(a, b, c).sorted.slice(1, 3).map(x => x * x).sum
  }                                               //> largestSquares: (a: Int, b: Int, c: Int)Int

  largestSquares(1, 2, 4)                         //> res0: Int = 20
  largestSquares(4, 1, 2)                         //> res1: Int = 20

  // 2. Write a function that takes a positive integer as input and determines if it is a base-10 palindrome.
  def isPalindrome(n: Int): Boolean = {
    n.toString().reverse == n.toString()
  }                                               //> isPalindrome: (n: Int)Boolean
  isPalindrome(123)                               //> res2: Boolean = false
  isPalindrome(12321)                             //> res3: Boolean = true

  // 3. Write a function that takes a positive integer as input and determines the number of trailing zeroes in the output of that number’s factorial.
  def factorialTrailingZeroesCount(n: Long, res: Long = 0): Long = {
    if (n < 5) res
    else factorialTrailingZeroesCount(n / 5, res + n / 5)
  }                                               //> factorialTrailingZeroesCount: (n: Long, res: Long)Long

  factorialTrailingZeroesCount(26)                //> res4: Long = 6

  // http://programmingpraxis.com/2015/08/21/two-homework-problems/
  // 1. Given an array of positive integers,
  // find the inflection point where the total of the integers before the inflection point
  // and the total of the integers after the inflection point are least different.
  // For instance, given the array [3, 7, 9, 8, 2, 5, 6], the inflection point is between the 9 and 8,
  // which leaves a total of 19 before the inflection point and 21 after, a difference of 2.

  def inflection(nums: List[Int], left: Int = 0, right: Int = 0, inf: Int = -1): Int = {
    if (nums.isEmpty) inf
    else if (left <= right) inflection(nums.tail, left + nums.head, right, inf + 1)
    else inflection(nums.tail, left, right + nums.head, inf)
  }                                               //> inflection: (nums: List[Int], left: Int, right: Int, inf: Int)Int
  inflection(List())                              //> res5: Int = -1
  inflection(List(1))                             //> res6: Int = 0
  inflection(List(1, 2))                          //> res7: Int = 0
  inflection(List(1, 2, 1))                       //> res8: Int = 1
  inflection(List(1, 2, 1, 2))                    //> res9: Int = 2
  inflection(List(3, 7, 9, 8, 2, 5, 6))           //> res10: Int = 3

  // 2. Write a program that reads a file from disk and writes the last n lines of the file, where n is an input parameter.
  def filesLastN(path: String, n: Int): Seq[String] = {
    def lastn(lines: Iterator[String], n: Int, lastLines: List[String]): Seq[String] =
      if (!lines.hasNext) lastLines
      else if (lastLines.size == n) lastn(lines, n, lastLines.tail ++ List(lines.next()))
      else lastn(lines, n, lastLines ++ List(lines.next()))

    
    lastn(Source.fromFile(path).getLines(), n, List())
  }
  new PrintWriter("5lines.txt") { write("l1\r\nl2\r\nl3\r\nl4\r\nl5"); close }
  filesLastN("5lines.txt", 2)                     //> res12: Seq[String] = List(l4, l5)
}