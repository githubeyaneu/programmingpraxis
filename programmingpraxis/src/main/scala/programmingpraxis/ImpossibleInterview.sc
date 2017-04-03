package programmingpraxis

import scala.util.Random

object ImpossibleInterview {
  val abc = "abcde".toStream                      //> abc  : scala.collection.immutable.Stream[Char] = Stream(a, ?)

  def rndFromStream(stream: Stream[Char], cnt: Int = 1, res: Char = 'x'): Char = {
    if (stream.isEmpty) res
    else rndFromStream(stream.tail, cnt + 1, if (Random.nextInt(cnt) < 1) stream.head else res)
  }                                               //> rndFromStream: (stream: Stream[Char], cnt: Int, res: Char)Char

  (1 to 10000 map ( x => rndFromStream(abc) )).groupBy(identity).mapValues { _.size }
                                                  //> res0: scala.collection.immutable.Map[Char,Int] = Map(e -> 1990, a -> 2048, b
                                                  //|  -> 2019, c -> 1975, d -> 1968)

}