package eu.eyan.codefights;

public class BeautifulBinaryStrings {
	boolean beautiful_binary_strings(String s) {
	    int l=s.length();
	    while(l>0)
	    {
	        s= s.replaceAll("AA|BB", "");
	        if(l==s.length()) return false;
	        l=s.length();
	    }
	    return true;
	}
}
