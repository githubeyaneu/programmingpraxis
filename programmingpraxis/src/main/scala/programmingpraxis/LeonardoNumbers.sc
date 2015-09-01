package programmingpraxis

object LeonardoNumbers {


  def LeonardoNumbers(n1: Int = 1, n2: Int = 1): Stream[Int] = n1 #:: LeonardoNumbers(n2, n1 + n2 + 1)
                                                  //> LeonardoNumbers: (n1: Int, n2: Int)Stream[Int]
  LeonardoNumbers() take 15 toList                //> res0: List[Int] = List(1, 1, 3, 5, 9, 15, 25, 41, 67, 109, 177, 287, 465, 75
                                                  //| 3, 1219)
  
                                                  
  def primes(sieve: Stream[Int]=Stream.from(2)):Stream[Int] = sieve.head #:: sieve.tail.filter { x => x % sieve.head != 0 }
                                                  //> primes: (sieve: Stream[Int])Stream[Int]
  primes() take 15 toList                         //> res1: List[Int] = List(2, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29
                                                  //| )
 
}