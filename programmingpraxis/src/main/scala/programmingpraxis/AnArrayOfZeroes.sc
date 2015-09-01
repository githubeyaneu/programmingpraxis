package programmingpraxis

object AnArrayOfZeroes {
/**
 * An Array Of Zeroes
 * by programmingpraxis
 * http://programmingpraxis.com/2014/11/21/an-array-of-zeroes/
 *
 * Beware! Today's exercise, which derives from an interview question asked at Facebook, is trickier than it looks:
 *
 * You are given an array of integers.
 * Write a program that moves all non-zero integers to the left end of the array, and all zeroes to the right end of the array.
 * Your program should operate in place.
 * The order of the non-zero integers doesn't matter.
 *
 * As an example, given the input array [1,0,2,0,0,3,4], your program should permute the array to [1,4,2,3,0,0,0] or something similar,
 * and return the value 4.
 *
 * Your task is to write the indicated program.
 */
  
  val orig = Array(1, 0, 2, 0, 0, 3, 4)           //> orig  : Array[Int] = Array(1, 0, 2, 0, 0, 3, 4)
  val expected = Array(1, 2, 3, 4, 0, 0, 0)       //> expected  : Array[Int] = Array(1, 2, 3, 4, 0, 0, 0)
  
  def sortZeroes(in: Array[Int]):Array[Int] = {
  	in.filter(e=>e!=0) ++ in.filter(e=>e==0) // Does not operate in place
  	
  }                                               //> sortZeroes: (in: Array[Int])Array[Int]
  
  sortZeroes(orig)                                //> res0: Array[Int] = Array(1, 2, 3, 4, 0, 0, 0)
  
  assert(expected.sameElements(sortZeroes(orig)))
}