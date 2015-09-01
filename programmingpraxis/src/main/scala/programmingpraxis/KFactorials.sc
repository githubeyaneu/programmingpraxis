package programmingpraxis


//http://programmingpraxis.com/2015/08/18/k-factorials-and-factorions/
object KFactorials {

  implicit class BigIntFactorial(n: BigInt) {
    def ! :BigInt = !(n)
    def !(n: BigInt, fact: BigInt = 1): BigInt = {
      if (n == 0 || n == 1) fact
      else !(n - 1, fact * n)
    }
  }

  val n: BigInt = 3
  n!
}