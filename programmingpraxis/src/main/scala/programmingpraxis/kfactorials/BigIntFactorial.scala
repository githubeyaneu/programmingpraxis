package programmingpraxis.kfactorials

class BigIntFactorial(n: BigInt) {
  def fact: BigInt = factorial(n)
  def factorial(n: BigInt, fact: BigInt = 1): BigInt = {
    if (n == 0 || n == 1) fact
    else factorial(n - 1, fact * n)
  }
}
