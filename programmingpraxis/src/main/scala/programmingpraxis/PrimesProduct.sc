package programmingpraxis

//http://programmingpraxis.com/2015/08/28/maximum-product-of-two-primes-less-than-n/
object PrimesProduct {

  def sieve(numbers: Stream[Int]): Stream[Int] = {
    if (numbers.isEmpty) Stream()
    else numbers.head #:: sieve(numbers.tail filter { _ % numbers.head != 0 })
  }                                               //> sieve: (numbers: Stream[Int])Stream[Int]


  def primes(n: Int): Seq[Int] = sieve(2 to n toStream) toList
                                                  //> primes: (n: Int)Seq[Int]

  def primesProduct(n: Int): Tuple3[Int, Int, Int] = {
    (for (p1 <- primes(n / 2); p2 <- primes(n / 2) if p1 * p2 < n) yield (p1 * p2, p1, p2)).max
  }                                               //> primesProduct: (n: Int)(Int, Int, Int)

  primesProduct(27)                               //> res0: (Int, Int, Int) = (26,13,2)
  primesProduct(50)                               //> res1: (Int, Int, Int) = (49,7,7)
  
  val x = <abc></abc>                             //> x  : scala.xml.Elem = <abc></abc>
  
  var a = """
  asdasd
  asdasd
  asd\
  """                                             //> a  : String = "
                                                  //|   asdasd
                                                  //|   asdasd
                                                  //|   asd\
                                                  //|   "
  
}