package eu.eyan.codefights;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class Ambiguous_Encoding {

	int c[], l, r;
	String n;
	
	int AmbiguousEncoding(String m) {
		c = new int[361];
		l=m.length();
		n=m;
		return r(0);
	}
	
	int r(int i) {
	    r =
    		c[i]>0 ? 
    				c[i] :
    					
	        l-i==0 ? 
	        		1 :
	        			
			c(i) == 48 ?
					0:
	        						
	    	l-i == 1 ? 
	    			1 :
	
			c(i) == 49 ?  
					r(i+1) 
					+ r(i+2) :
			
			c(i) == 50 && c(i+1) < 55 ?
					r(i+1)
					+ r(i+2) :
	    				
	        c(i) > 50 ?  
	        		r(i+1) :
	        			
	        			r(i+2) 
	        ;
	        	
		c[i]=r;
	    return r%1000003;
	}

	char c(int i) {
		return n.charAt(i);
	}	
	
//	int c[], l;
//	String n;
//	
//	int AmbiguousEncoding(String m) {
//		c = new int[361];
//		l=m.length();
//		n=m;
//		return r(m, 0);
//	}
//	
//	int r(String m, int i) {
//		if(c[i]>0) return c[i];
//	    int ret = 
//	        l-i==0 ? 
//	        		1 :
//	        			
//			m.charAt(i) == '0' ?
//					0:
//	        						
//	    	l-i == 1 ? 
//	    			1 :
//	
//			m.charAt(i) == '1' ?  
//					r(m, i+1) 
//					+ r(m, i+2) :
//			
//			m.charAt(i) == '2' && m.charAt(i+1) < 55 ?
//					r(m, i+1)
//					+ r(m, i+2) :
//	    				
//	        m.charAt(i) > '2' ?  
//	        		r(m, i+1) :
//	        			
//	        			r(m, i+2) 
//	        ;
//	        	
//		c[i]=ret;
//	    return ret%1000003;
//	}	
	
	
//	Map<String, Integer> c = new HashMap<>();
//	
//	int AmbiguousEncoding(String m) {
//		return r(m, 0);
//	}
//	
//	int r(String m, int i) {
//		if(c.containsKey(m.substring(i))) return c.get(m.substring(i));
//	    int ret = 
//	        m.length()-i==0 ? 
//	        		1 :
//	        			
//			m.charAt(i) == '0' ?
//					0:
//	        						
//	    	m.length()-i == 1 ? 
//	    			1 :
//	
//			m.charAt(i) == '1' ?  
//					r(m, i+1) 
//					+ r(m, i+2) :
//			
//			m.charAt(i) == '2' && m.charAt(i+1) < 55 ?
//					r(m, i+1)
//					+ r(m, i+2) :
//	    				
//	        m.charAt(i) > '2' ?  
//	        		r(m, i+1) :
//	        			
//	        			r(m, i+2) 
//	        ;
//	        	
//		c.put(m.substring(i), ret);
//	    return ret%1000003;
//	}

//	int AmbiguousEncoding(String m) {
//		if(c.containsKey(m)) return c.get(m);
//	    int ret = 
//	        m.length()==0 ? 
//	        		1 :
//	        			
//			m.charAt(0) == '0' ?
//					0:
//	        						
//        	m.length() == 1 ? 
//        			1 :
//
//			m.charAt(0) == '1' ?  
//					AmbiguousEncoding(m.substring(1)) 
//					+ AmbiguousEncoding(m.substring(2)) :
//			
//			m.charAt(0) == '2' && m.charAt(1) < 55 ?
//					AmbiguousEncoding(m.substring(1))
//					+ AmbiguousEncoding(m.substring(2)) :
//        				
//	        m.charAt(0) > '2' ?  
//	        		AmbiguousEncoding(m.substring(1)) :
//	        			
//	        		AmbiguousEncoding(m.substring(2)) 
//	        ;
//	        		
//		c.put(m, ret);
//	    return ret%1000003;
//	}

	
	@Test
	public void testName() throws Exception {
		assertThat(AmbiguousEncoding("11")).isEqualTo(2);
		assertThat(AmbiguousEncoding("111111")).isEqualTo(13);
		assertThat(AmbiguousEncoding("112935")).isEqualTo(3);
		assertThat(AmbiguousEncoding("999999")).isEqualTo(1);
		assertThat(AmbiguousEncoding("30")).isEqualTo(0);
		assertThat(AmbiguousEncoding("1")).isEqualTo(1);
		assertThat(AmbiguousEncoding("121212121212121212")).isEqualTo(4181);
		assertThat(AmbiguousEncoding("1252171528291251252151829411212312")).isEqualTo(25272);
		assertThat(AmbiguousEncoding("1201")).isEqualTo(1);
		
		assertThat(AmbiguousEncoding("111111111111111111111111111111111111111111111111111111111111111111111111111111")).isEqualTo(804400);
		
		assertThat(AmbiguousEncoding("123456789112345678911234567891123456789112345678911234567891123456789112345678911234567891123456789112345678911234567891123456789112345678911234567891123456789112345678911234567891123456789112345678911234567891123456789112345678911234567891123456789112345678911234567891123456789112345678911234567891")).isEqualTo(928332);
	}
}
