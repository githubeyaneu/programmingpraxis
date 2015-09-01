package programmingpraxis

object ClosestTwoSumToZero {
  val input = List(45, -29, -96, -7, -17, 72, -60)//> input  : List[Int] = List(45, -29, -96, -7, -17, 72, -60)
  val pairs = (for(i1 <- input;  i2<-input.filter { x => x!=i1 })   yield (i1,i2))
                                                  //> pairs  : List[(Int, Int)] = List((45,-29), (45,-96), (45,-7), (45,-17), (45,
                                                  //| 72), (45,-60), (-29,45), (-29,-96), (-29,-7), (-29,-17), (-29,72), (-29,-60)
                                                  //| , (-96,45), (-96,-29), (-96,-7), (-96,-17), (-96,72), (-96,-60), (-7,45), (-
                                                  //| 7,-29), (-7,-96), (-7,-17), (-7,72), (-7,-60), (-17,45), (-17,-29), (-17,-96
                                                  //| ), (-17,-7), (-17,72), (-17,-60), (72,45), (72,-29), (72,-96), (72,-7), (72,
                                                  //| -17), (72,-60), (-60,45), (-60,-29), (-60,-96), (-60,-7), (-60,-17), (-60,72
                                                  //| ))
  val min = pairs.map(x=>(x._1+x._2).abs).min     //> min  : Int = 12
  //
  val inputS = input.sorted                       //> inputS  : List[Int] = List(-96, -60, -29, -17, -7, 45, 72)

	def mins(input: List[Int], min:Tuple2[Int,Int]):Tuple2[Int,Int]={
		val difOld=(min._1+min._2).abs
		
		
		(1,1)
	}                                         //> mins: (input: List[Int], min: (Int, Int))(Int, Int)
	
	mins(inputS,(-100,100))                   //> res0: (Int, Int) = (1,1)
	
}