package programmingpraxis

object MostLivingPeople {

def mostLivingPeopleMain(input: String)={
	val lines = input.split("\n")
	var count = 0
	for(block <- 1 to lines(count).toInt) yield {
		count += 1
		mostLivingPeople(
		for(line <- 1 to lines(count).toInt) yield {
			count += 1
			lines(count)
		})
	}
}                                                 //> mostLivingPeopleMain: (input: String)scala.collection.immutable.IndexedSeq[s
                                                  //| cala.collection.immutable.Iterable[Int]]

def mostLivingPeople(input: Seq[String]) = {
	val intervals = input
	.map { x => if (x.contains(" ")) x else x+" 2015" }
	.map { x => (x.split(" ")(0) toInt, x.split(" ")(1) toInt) }
	
	val years_count = intervals
	  .map{case (f,t) => f to t}
	  .flatten
	  .groupBy { x => x }
	  .map { x => (x._1, x._2.size)}
	  
	years_count filter(p=>p._2==years_count.values.max) map(f=>f._1)
}                                                 //> mostLivingPeople: (input: Seq[String])scala.collection.immutable.Iterable[In
                                                  //| t]

mostLivingPeopleMain("3\n"+
"3\n"+
"1910 1948\n"+
"1948 2011\n"+
"1927 1995\n"+
"3\n"+
"1910 1948\n"+
"1927 1995\n"+
"1945\n"+
"1\n"+
"123 125\n" ).mkString("\n")                      //> res0: String = List(1948)
                                                  //| List(1946, 1947, 1948, 1945)
                                                  //| List(125, 124, 123)

}