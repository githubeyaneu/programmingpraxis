package programmingpraxis

object PowerSet {
  val s = Set[Int](1, 2, 3)                       //> s  : scala.collection.immutable.Set[Int] = Set(1, 2, 3)

  s.subsets().toList                              //> res0: List[scala.collection.immutable.Set[Int]] = List(Set(), Set(1), Set(2),
                                                  //|  Set(3), Set(1, 2), Set(1, 3), Set(2, 3), Set(1, 2, 3))

  def subsets[T](s: Set[T]): Set[Set[T]] = {
    if (s.isEmpty) Set(s);
    else {
    	Set(s)++((for (i <- s) yield subsets(s - i)).flatten)
    }
  }                                               //> subsets: [T](s: Set[T])Set[Set[T]]

  subsets(Set(1, 2,3))                            //> res1: Set[Set[Int]] = Set(Set(), Set(1, 3), Set(2), Set(1, 2), Set(2, 3), Se
                                                  //| t(3), Set(1, 2, 3), Set(1))
}