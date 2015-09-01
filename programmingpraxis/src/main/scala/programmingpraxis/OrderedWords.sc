package programmingpraxis

// http://programmingpraxis.com/2015/07/14/ordered-words/
object OrderedWords {
  val words = List("abc","cba", "billowy", "deglory", "egilops", "aZ", "Za")
  val orderedWords = words.filter(x=>x.toLowerCase==x.toLowerCase.sorted)
  val longest= orderedWords.map(x => x.size).max
  orderedWords.filter(x=>x.size==longest)          //> res0: List[String] = List(billowy, deglory, egilops)
}