import scala.util.Try
import scala.util.Failure
import java.math.BigInteger

object Practice {
  def sos(n: Int) = (2 * n * n * n + 3 * n * n + n) / 6
                                                  //> sos: (n: Int)Int

  sos(15)                                         //> res0: Int = 1240

}