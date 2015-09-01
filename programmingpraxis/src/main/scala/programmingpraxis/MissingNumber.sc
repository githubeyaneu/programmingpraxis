package programmingpraxis

object MissingNumber {

  def check(input: String, size: Int = 1): String = {
    if (size > 5) "No solution"
    else {
      if (input.length < 2 * size || input.length % size != 0) check(input, size + 1)
      else {
        val pairs = input.sliding(size, size).map(x => x.toInt).sliding(2, 1).toList
        val nrOnes = pairs.filter(x => x(1) - x(0) == 1).size
        val nrTwo = pairs.filter(x => x(1) - x(0) == 2).size
        if (nrOnes == pairs.size - 1 && nrTwo == 1) (pairs.filter(x => x(1) - x(0) == 2)(0)(1) - 1).toString
        else check(input, size + 1);
      }
    }
  }                                               //> check: (input: String, size: Int)String

  check("596597598600601602")                     //> res0: String = 599
  check("1")                                      //> res1: String = No solution
  check("12346")                                  //> res2: String = 5
  
  val xml = <hello></hello>                       //> xml  : scala.xml.Elem = <hello></hello>
  val xml2 = <b></b>                              //> xml2  : scala.xml.Elem = <b></b>
  val t ="text"                                   //> t  : String = text
  
  val xt = <x a="b">"text"</x>                    //> xt  : scala.xml.Elem = <x a="b">&quot;text&quot;</x>

	xt                                        //> res3: scala.xml.NodeSeq = NodeSeq(<x a="b">&quot;text&quot;</x>, <x a="b">&q
                                                  //| uot;text&quot;</x>)
  //val json = {"a"="b"}
  
}