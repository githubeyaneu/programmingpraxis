package programmingpraxis

// http://programmingpraxis.com/2015/09/01/lychrel-numbers/
object LychrelNumbers {
  def r(n: Long): Long = n.toString.reverse.toInt //> r: (n: Long)Long
  def s(n: Long): Long = n + r(n)                 //> s: (n: Long)Long
  def isPalindrome(n: Long): Boolean = n.toString == n.toString.reverse
                                                  //> isPalindrome: (n: Long)Boolean
  def isLeadsToPalindrome(n: Long, max: Long): Boolean = if (n > max) false else isPalindrome(n) || isLeadsToPalindrome(s(n), max)
                                                  //> isLeadsToPalindrome: (n: Long, max: Long)Boolean
  def chain(n: Long, max: Long): Seq[Long] = if (n > max) List() else List(n) ++ chain(s(n), max)
                                                  //> chain: (n: Long, max: Long)Seq[Long]
  def lychrelStream(from: Long, max: Long): Stream[Tuple2[Long, Seq[Long]]] = {
    if (isLeadsToPalindrome(from, max)) lychrelStream(from + 1, max)
    else Stream.cons((from, chain(from, max)), lychrelStream(from + 1, max))
  }                                               //> lychrelStream: (from: Long, max: Long)Stream[(Long, Seq[Long])]

  lychrelStream(1, 1000000000L).take(10).toList.mkString("\r\n")
                                                  //> res0: String = (89,List(89, 187, 968, 1837, 9218, 17347, 91718, 173437, 9078
                                                  //| 08, 1716517, 8872688, 17735476, 85189247, 159487405, 664272356))
                                                  //| (98,List(98, 187, 968, 1837, 9218, 17347, 91718, 173437, 907808, 1716517, 88
                                                  //| 72688, 17735476, 85189247, 159487405, 664272356))
                                                  //| (177,List(177, 948, 1797, 9768, 18447, 92928, 175857, 934428, 1758867, 94474
                                                  //| 38, 17794887, 96644658, 182289327, 906271608))
                                                  //| (187,List(187, 968, 1837, 9218, 17347, 91718, 173437, 907808, 1716517, 88726
                                                  //| 88, 17735476, 85189247, 159487405, 664272356))
                                                  //| (196,List(196, 887, 1675, 7436, 13783, 52514, 94039, 187088, 1067869, 107554
                                                  //| 70, 18211171, 35322452, 60744805, 111589511, 227574622, 454050344, 897100798
                                                  //| ))
}