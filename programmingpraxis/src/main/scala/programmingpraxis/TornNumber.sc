package programmingpraxis

//http://programmingpraxis.com/2014/09/16/torn-numbers/
// A number n is a torn number if it can be chopped into two parts which, when added together and squared,
// are equal to the original number.
// For instance, 88209 is a torn number because (88 + 209)^2 = 297^2 = 88209.
object TornNumber {
  
  implicit def RichInt(i: Int) = new {
    def ^^(ext:Int):Int = if (ext<2) i else i * ^^(ext-1)
    def square():Int = ^^(2)
  }                                               //> RichInt: (i: Int)AnyRef{def ^^(ext: Int): Int; def square(): Int}

  implicit def IsTornInt(n: Int) = new {
		def isTorn:Boolean=isTorn("","")
		def isTorn(pre:String, post:String):Boolean={
				if(pre== "" && post == "") isTorn(""+n.toString.head, n.toString.tail)
				else if(post=="") false
				else if( (pre.toInt + post.toInt).^^(2) == n) true
				else isTorn(pre+post.head, post.tail)
		}
  }                                               //> IsTornInt: (n: Int)AnyRef{def isTorn: Boolean; def isTorn(pre: String,post: 
                                                  //| String): Boolean}
  

	88209 isTorn                              //> res0: Boolean = true
}