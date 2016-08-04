package eu.eyan.codefights;

public class GCD {
	public static void main(String[] args) {

		for (int t = -48; t < 1000; t++)
			//if (t == 0)
				for (int bit = 0; bit < 10; bit++) {
					String s = "";
					for (int ch = 48; ch < 58; ch++) {
						int bitt = 1 << bit;
//						System.out.println("t: " + t + ", ch: " + ch
//								+ ", bit: " + bit + ", bit: "
//								+ Integer.toString(bitt, 2) + ", "
//								+ Integer.toString((ch & bitt),2) + ", "
//								+Integer.toString((t+ch),2));
						s += ((t+ch) & bitt) > 0 ? 1 : 0;

					}
//					System.out.println(s);
//					if(s.equals("1000001001")) System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
//					if(s.equals("0111110110")) System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
//					if(s.equals("1000001011")) System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
//					if(s.equals("0111110100")) System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
//					System.out.println("069".contains(""+'0'));
					
				}
		System.out.println(1%-2);
	}
}
