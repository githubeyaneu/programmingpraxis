package eu.eyan.codefights;

import org.junit.Test;

public class CountFours {
	int Count4s(int n) {
	    int s = 0;
	    for(int i=4; i<n; i++){
	        s+=(""+i).replaceAll("[^4]","").length();
	    }
	    System.out.println("res: "+s);

	    s = 0;
	    for(int l=(""+n).length(); l>0; l--){
	    	int szorzando = (l-1)*ad(10,l-2);
			int elsoszam = n/ad(10,l-1);
			int negyes = elsoszam>4?ad(10,l-1):0;
			System.out.println(s+" "+n +" "+l+" "+szorzando+" "+elsoszam+" "+negyes);
			s += elsoszam * szorzando + negyes;
	    	n = n % ad(10,l-1);
	    }
	    System.out.println("res2: "+s);
	    
	    return s;
	}
	
	int ad(int n, int x) {
		return x<1?1:n*ad(n,x-1);
	}

	@Test
	public void testName() throws Exception {
		new CountFours().Count4s(45);
	}
	
	public static void main(String[] args) {
		System.out.println(new CountFours().Count4s(10));
		System.out.println(new CountFours().Count4s(20));
		System.out.println(new CountFours().Count4s(30));
		System.out.println(new CountFours().Count4s(40));
		System.out.println(new CountFours().Count4s(50));
		System.out.println(new CountFours().Count4s(60));
		System.out.println(new CountFours().Count4s(70));
		System.out.println(new CountFours().Count4s(80));
		System.out.println(new CountFours().Count4s(90));
		System.out.println(new CountFours().Count4s(100));
System.out.println();
		System.out.println(new CountFours().Count4s(100));
		System.out.println(new CountFours().Count4s(200));
		System.out.println(new CountFours().Count4s(300));
		System.out.println(new CountFours().Count4s(400));
		System.out.println(new CountFours().Count4s(500));
		System.out.println(new CountFours().Count4s(600));
		System.out.println(new CountFours().Count4s(700));
		System.out.println(new CountFours().Count4s(800));
		System.out.println(new CountFours().Count4s(900));
		System.out.println(new CountFours().Count4s(1000));
		System.out.println();
		System.out.println(new CountFours().Count4s(1000));
		System.out.println(new CountFours().Count4s(2000));
		System.out.println(new CountFours().Count4s(3000));
		System.out.println(new CountFours().Count4s(4000));
		System.out.println(new CountFours().Count4s(5000));
		System.out.println(new CountFours().Count4s(6000));
		System.out.println(new CountFours().Count4s(7000));
		System.out.println(new CountFours().Count4s(8000));
		System.out.println(new CountFours().Count4s(9000));
		System.out.println(new CountFours().Count4s(10000));
System.out.println();
		System.out.println(new CountFours().Count4s(12345));
	}
}

