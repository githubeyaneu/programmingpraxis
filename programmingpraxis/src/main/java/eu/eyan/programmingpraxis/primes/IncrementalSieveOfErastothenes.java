package eu.eyan.programmingpraxis.primes;

import java.util.Map;
import java.util.TreeMap;

/**
 * http://programmingpraxis.com/2015/07/31/incremental-sieve-of-eratosthenes/
 * 
 * Algorithm PRIMEGEN: Create a generating function that returns the next prime number each time 
 * it is called, starting from zero.
 * 
 * 1. [Prime the pump] Yield 2, then yield 3.
 * 2. [Initialization] Set ps ← PRIMEGEN, D ← {}, p ← next(ps), p ← next(ps) again,
 *                     q ← p × p, and c ← p.
 * 3. [Next candidate] Set c ← c + 2.
 * 4. [Composite candidate] If c ∉ D, skip to Step 5. 
 * 							Otherwise, set s ← D{c}, m ← c + s. 
 *                          Remove c from D. 
 *                          While m ∈ D, set m ← m + s. Set D{x} ← s. Go to Step 3.
 * 5. [Prime candidate] If c = q, skip to Step 6. 
 *                      Otherwise, yield c and go to Step 3.
 * 6. [Next sieving prime] Set s ← p + p, m ← c + s. 
 *                         While m ∈ D, set m ← m + s. Set D{m} ← s. Set p ← next(ps), q ← p × p. 
 *                         Go to Step 3.
 */
public class IncrementalSieveOfErastothenes {
	Integer p = 0;
	Integer q, c, s, m;
	Map<Integer, Integer> D = new TreeMap<Integer, Integer>();
	
	Integer ps(){
// 1. [Prime the pump] Yield 2, then yield 3.
// 2. [Initialization] Set ps ← PRIMEGEN, D ← {}, p ← next(ps), p ← next(ps) again,
//                     q ← p × p, and c ← p.
		if(p<2) {p=2; return p;}
		if(p<3) {p=3;  q=p*p; c=p; return p;}
		
		
// 3. [Next candidate] Set c ← c + 2.
		c=c+2;
// 4. [Composite candidate] If c ∉ D, skip to Step 5. 
// 							Otherwise, set s ← D{c}, m ← c + s. 
//                          Remove c from D. 
//                          While m ∈ D, set m ← m + s. Set D{x} ← s. Go to Step 3.
		if(D.containsKey(c)){
			s = D.get(c);
			m = c+s;
			D.remove(c);
			while(D.containsKey(m)) {
				m=m+s;
				for (Integer key : D.keySet()) {
					D.put(key, s);
				}
			}
		}
		
// 5. [Prime candidate] If c = q, skip to Step 6. 
//                      Otherwise, yield c and go to Step 3.
		if(c!=q){
			return c;
		}
		
// 6. [Next sieving prime] Set s ← p + p, m ← c + s. 
//                         While m ∈ D, set m ← m + s. Set D{m} ← s. Set p ← next(ps), q ← p × p. 
//                         Go to Step 3.
		s=p+p;
		m=c+s;
		while(D.containsKey(m)) {
			m=m+s;
			D.put(m, s);
		}
		
		p=ps();
		q=p*p;
		
		return null;
	}
	public static void main(String[] args) {
		IncrementalSieveOfErastothenes pp = new IncrementalSieveOfErastothenes();
		System.out.println(pp.ps());
		System.out.println(pp.ps());
		System.out.println(pp.ps());
		System.out.println(pp.ps());
		System.out.println(pp.ps());
	}
}
