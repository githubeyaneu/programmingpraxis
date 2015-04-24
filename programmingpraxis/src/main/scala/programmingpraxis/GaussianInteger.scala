package programmingpraxis

class GaussianInteger(val a: Int, val b: Int) {
  def addition(that: GaussianInteger): GaussianInteger = new GaussianInteger(a + that.a, b + that.b)
  def subtraction(that: GaussianInteger): GaussianInteger = new GaussianInteger(a - that.a, b - that.b)
  def crossMultiply(that: GaussianInteger): GaussianInteger = new GaussianInteger(a * that.a - b * that.b, a * that.b + b * that.a)
  def quotient(that: GaussianInteger): GaussianInteger = {
    val n = that.a * that.a + that.b * that.b
    new GaussianInteger((a * that.a - b * that.b) / n, (b * that.a - a * that.b) / n)
  }
  def remainder(that: GaussianInteger): GaussianInteger = subtraction(quotient(that))
  override def toString: String = a + " + " + b + "i"

}