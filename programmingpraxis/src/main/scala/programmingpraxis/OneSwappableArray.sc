package programmingpraxis

/*
  http://programmingpraxis.com/2015/07/24/one-swappable-array/
  One-Swappable Array

  Given an array of unique integers, determine if it is possible to sort the array by swapping two elements of the array.
  For instance, the array [1,2,6,4,5,3,7] can be sorted by swapping 3 and 6,
  but there is no way to sort the array [5,4,3,2,1] by swapping two of its elements.
  You may use O(n) time, where the array has n integers, and constant additional space.
*/
object OneSwappableArray {
	val ok = Array(1,2,6,4,5,3,7)             //> ok  : Array[Int] = Array(1, 2, 6, 4, 5, 3, 7)
	val nok = Array(5,4,3,2,1)                //> nok  : Array[Int] = Array(5, 4, 3, 2, 1)

	def oneSwappable(in:Array[Int]):Boolean={
		if(in.length<2) return true
		else return false
	}                                         //> oneSwappable: (in: Array[Int])Boolean
}