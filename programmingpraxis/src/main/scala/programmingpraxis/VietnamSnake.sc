package programmingpraxis

object VietnamSnake {
 //(1 to 9).permutations.filter({List(a,b,c,d,e,f,g,h,i) => 13*b*i+c*g*h==(66+10+11-a-d-12*e+f)*c*i}).size
 
 (1 to 9).permutations filter{case Vector(a,b,c,d,e,f,g,h,i) => 13*b*i+c*g*h == (66+10+11-a-d-12*e+f)*c*i } size
                                                  //> res0: Int = 136
 
 
 val x = (1 to 3)                                 //> x  : scala.collection.immutable.Range.Inclusive = Range(1, 2, 3)
 
 x.permutations                                   //> res1: Iterator[scala.collection.immutable.IndexedSeq[Int]] = non-empty itera
                                                  //| tor
                                                  
                                                  
                                                  
 // (1 to 9).permutations.filter(l=>13*l(1)*l(8)+l(2)*l(6)*l(7)==(66+10+11-l(0)-l(3)-12*l(4)+l(5))*l(2)*l(8)).size
  
  
 // (1.0 to 9.0 by 1.0).permutations.filter(l=>l(0)+13*l(1)/l(2)+l(3)+12*l(4)-l(5)-11+l(6)*l(7)/l(8)-10==66).size
                                                  
}