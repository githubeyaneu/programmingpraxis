package programmingpraxis

/**
 * http://programmingpraxis.com/2014/09/09/drawing-diamonds/
 *
 * Given a small positive integer n, write a function that draws a diamond, either filled or in outline as specified by the user.
 * For instance, here are filled and outline diamonds for n = 5:
 *
 *          *              *
 *         * *            * *
 *        * * *          *   *
 *       * * * *        *     *
 *      * * * * *      *       *
 *       * * * *        *     *
 *        * * *          *   *
 *         * *            * *
 *          *              *
 *
 *
 * Note that there is a single space between asterisks in the filled version of the diamond.
 *
 * Your task is to write a program that draws diamonds as described above.
 *
 */
object DrawDiamonds {
  def draw(n:Int, filled:Boolean=false):String={
  	"\r\n"+(for(i<- -1*n+1 to n-1) yield (
  	  if (filled) " "*i.abs+"* "*(n-i.abs)
  	  else " "*i.abs+(if(n-1-i.abs==0)"*" else ("*"+" "*((n-1-i.abs)*2-1)+"*"))
  	  )).mkString("\r\n")
  }                                               //> draw: (n: Int, filled: Boolean)String
   
  draw(1)                                         //> res0: String = "
                                                  //| *"
  draw(1, true)                                   //> res1: String = "
                                                  //| * "
  draw(5)                                         //> res2: String = "
                                                  //|     *
                                                  //|    * *
                                                  //|   *   *
                                                  //|  *     *
                                                  //| *       *
                                                  //|  *     *
                                                  //|   *   *
                                                  //|    * *
                                                  //|     *"
  draw(5,true)                                    //> res3: String = "
                                                  //|     * 
                                                  //|    * * 
                                                  //|   * * * 
                                                  //|  * * * * 
                                                  //| * * * * * 
                                                  //|  * * * * 
                                                  //|   * * * 
                                                  //|    * * 
                                                  //|     * "
}