//
// Class:	   FunctionTesterSample
// Written By: Frank Lee
// Date:	      June 2017
//
// Sample client code that uses the Function class defined for Program 5
// Additional testing is required.
//

public class FunctionTesterSample {
	// main method
	public static void main(String[] args){
		Function zero;
		Function a, b, c, d, e, f, g;
		int pass=0, count=0;
      String s,q;
      q ="";
      q = new String();
      s = new String("Happy");
		
		zero = new Function();
		a = new Function(5);
		b = new Function(4, -9);
		c = new Function(2, 4, 1);
		d = new Function(3, "SIN", 1);
		e = new Function(1, "COS", 2);
				
		System.out.println("FUNCTION CLASS TESTER");	  
		// test constructor	
		System.out.println("Test constructors and .evaluate:");	  
		if (zero.evaluate(18)==0.0) {
			pass++; 
		} else {
			System.out.println("FAIL zero");
		}
		count++;
		
		if (a.evaluate(18)==5.0) {
			pass++; 
		} else {
			System.out.println("FAIL constant");
		}
		count++;	
		
		if (b.evaluate(1)==-5.0) {
			pass++; 
		} else {
			System.out.println("FAIL linear");
		}
		count++;			
		
		if (c.evaluate(2)==17.0) {
			pass++; 
		} else {
			System.out.println("FAIL quadratic");
		}
		count++;			
		
		if (percentError(d.evaluate(2),3*Math.sin(2),0.001)) {
			pass++; 
		} else {
			System.out.println("FAIL sine:" + d.evaluate(2) + " != " + 3*Math.sin(2));
		}
		count++;			
		
		if (percentError(e.evaluate(2),1*Math.cos(2*2),0.001)) {
			pass++; 
		} else {
			System.out.println("FAIL cosine:" + e.evaluate(2) + " != " + 1*Math.cos(2*2));
		}
		count++;	
      
      System.out.println("PASS = " + pass + "; OUT OF " + count);	 

				
		// TEST toString VISUALLY 
      // [There are NO specifications as to the order terms are displayed]
		
		System.out.println("Test .toString:");	
      
      System.out.println("     TEST: " + a.toString());	
		System.out.println("SHOULD BE: 5.00");	
		System.out.println("     TEST: " + b.toString());	
		System.out.println("SHOULD BE: -9.00+4.00x");	
		System.out.println("     TEST: " + c.toString());	
		System.out.println("SHOULD BE: 2.00x^2.00+4.00x+1.00");	
		System.out.println("     TEST: " + d.toString());	
		System.out.println("SHOULD BE: 3.00sin(x)");	
		System.out.println("     TEST: " + e.toString());	
		System.out.println("SHOULD BE: cos(2.00x)");	
	}
	
	// check if val is within a percentage of standard
	public static Boolean percentError(double val, double standard, double p) {
		return (Math.abs((val-standard)/standard)<=p/100.0);
	}
}
