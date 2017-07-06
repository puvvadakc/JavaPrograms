// 
// Class: Function
// Written By: Krishna Puvvada
// Date:5 June 2017	   
//
// Assignment 5
// Complete the implementation of the following class.
//
// This class will model a class variable to hold a mathematical function of up to 10 terms.
// The function will have one independent variable.
// Terms that the function will model are:
//     Monomials of the form: 5.3x^0.5
//     Trigonometric terms sin(x), cos(x), tan(x) of the form: 1.2cos(3.0x) 
//
import java.awt.*; //importing packages 
import java.util.*;
public class Function { // PLEASE leave your class name as Function
	// CLASS VARIABLES
   //I set 2 arrays for each type of possible term such as ax^b, a*tan(bx), a*cos(bx)
   // and a*sin(bx).
   // co sinc, cosc, and tanc record the coefficient for each term in its respective place.
   // degree, sind, cosd, tand record degree of term. 
   double[] co = new double[13];
   double[] degree = new double[13];
   //cindex, sinindex, tanindex, and cosindex all keep track of what index the last term filled
   //in the array was as they get filled.
   int cindex;
   double[] sinc = new double[11];
   double[] sind = new double[11];
   int sinindex;
   double[] cosc = new double[11];
   double[] cosd = new double[11];
   int cosindex;
   double[] tanc = new double[11];
   double[] tand = new double[11];
   int tanindex;
    
	// use any collection of variables as you see fit.
	
	// CONSTRUCTORS
   //Point p0 = new Point();
	// Intialize Function to f(x)=0
	public Function() {
      //y = 0;
      //I am asked for the same task at clear(); so I typed my reset code there,
      //and just called the method here to make things easier.
      clear();       
	} 

	// Set Function to f(x)=c
	//the next set of public functionsallow for the storage of the degree and coefficient
   //of each term and store it to the associated array.
   public Function(double c) {
      co[0]= c;
      degree[0] = 0;
      cindex = 1; 
	} 

	// Set Function to f(x)=bx+c
	public Function(double b, double c) {
	   //y = b*x + c;
      co[0]= c;
      co[1]= b;
      degree[0] = 0;
      degree[1] = 1;
      cindex = 2;      
	} 

	// Set Function to f(x)=ax^2+bx+c
	public Function(double a, double b, double c) {
	   //y = a*(x*x) + b*x + c;
      co[0]= c;
      co[1]= b;
      co[2]= a;
      degree[0] = 0;
      degree[1] = 1;
      degree[2] = 2;
      cindex = 3;
	} 

	// Set Function to f(x)=coeff*trigFunction(px) 
	// - angle in radians
	// allow trigFunction to be "sin", "cos" or "tan"; set f(x)=0 for unknown trigFunctions.
	public Function(double coeff, String trigFunction, double p) {
	   if(trigFunction.toLowerCase().equals("sin")){
         //y = coeff * Math.sin(p*x);
         sinc[0] = coeff;
         sind[0] = p;
         sinindex = 1;
      }else if(trigFunction.toLowerCase().equals("cos")){
         //y = coeff * Math.cos(p*x);
         if( p == 0){
            co[0]+= coeff;
         }else{
            cosc[0] = coeff;
            cosd[0] = p;
            cosindex = 1;
         }   
      }else if(trigFunction.toLowerCase().equals("tan")){
         //y = coeff * Math.tan(p*x);
         tanc[0] = coeff;
         tand[0] = p;
         tanindex = 1;
      }else{
         clear();   
      }
	} 
		
	// Add a polynomial term of the form: Cx^P. to the Function
	public void addTerm(double C, double P) {
      boolean found = false;
      //for loop is used to parse through array and find existing degree locations
      //if none found it adds a new degree location on the array.
      //if same degree is found between 2 terms the coefficients are added and placed
      // in corusponding location with the degree.
      for(int i = 0; i < cindex; i++){
         if(P == degree[i]){
            co[i] += C;
            found = true;
         }
      }
      if(!found){
         co[cindex] = C;
         degree[cindex] = P;
         cindex++;
      }
	} 
	
	// Add a trigonometric term of the form c*trigFunction(px) 
	// - angle in radians
	// allow trigFunction to be "sin", "cos" or "tan"; 
	// make no additions to function for unknown trigFunctions and return false.
	public boolean addTerm(double c, String trigFunction, double p) {
		//the logic for this method is similar to the one above but I had to go through
      //sin, cos, and tan seperately sincethey have different arrays associated with them.
      if(trigFunction.toLowerCase().equals("sin")){
         boolean found = false;
         for(int i = 0; i < sinindex; i++){
            if(p == sind[i]){
               sinc[i] += c;
               found = true;
            }
         }
         if(!found){
            sinc[sinindex] = c;
            sind[sinindex] = p;
            sinindex++;
         }
         return true;
      }else if(trigFunction.toLowerCase().equals("cos")){
         //y += c * Math.cos(p*x);
         boolean found = false;
         for(int i = 0; i < cosindex; i++){
            if(p == 0){
               co[i] += c;
               found = true;
            }else if(p == cosd[i]){
               cosc[i] += c;
               found = true;
            }
         }
         if(!found){
            cosc[cosindex] = c;
            cosd[cosindex] = p;
            cosindex++;
         }
         return true;
      }else if(trigFunction.toLowerCase().equals("tan")){
         //y += c * Math.tan(p*x);
         boolean found = false;
         for(int i = 0; i < tanindex; i++){
            if(p == tand[i]){
               tanc[i] += c;
               found = true;
            }
         }
         if(!found){
            tanc[tanindex] = c;
            tand[tanindex] = p;
            tanindex++;
         }
         return true;
      }else{
         return false;   //if invalid string put into method, return false. 
      } 
	} 

	// set Function to f(x)=0
	public void clear() {
		//this method clears all the arrays and initiallizes them to 0.
      Arrays.fill(co,0);
      Arrays.fill(degree,0); 
      Arrays.fill(sinc,0); 
      Arrays.fill(sind,0); 
      Arrays.fill(cosc,0); 
      Arrays.fill(cosd,0); 
      Arrays.fill(tanc,0); 
      Arrays.fill(tand,0);
      cindex = 0;
      sinindex = 0;
      cosindex = 0;
      tanindex = 0;       
	} 
	
	// return the Function value at x
	public double evaluate(double x) {
      //this.x = x;
      //return y;
      //this evaluates each function given x according to the term's degree
      //and coefficient.
      //method goes through each array of each type of term in order to compute
      // a sum of all terms.
      double y = 0;
      for(int i = 0; i < cindex; i++){
         y += co[i]*Math.pow(x, degree[i]);   
      }
      for(int i = 0; i < sinindex; i++){
         y += sinc[i]*Math.sin(sind[i]*x);
      }
      for(int i = 0; i < cosindex; i++){
         y += cosc[i]*Math.cos(cosd[i]*x);
      }
      for(int i = 0; i < tanindex; i++){
         y += tanc[i]*Math.tan(tand[i]*x);
      }
      return y;    
	} 
	
	
	// returns a Function that is the addition of this Function with f
	// (combine similar terms when possible)
	public Function add(Function f) {
      // A totall of 3 function objects are present in this method, the current
      //object called to with "this", the inputed function f, and the final sum of both
      //functions, Function sum.
      //this function uses addterm method to add each term from both functions by scanning
      //both arrays and placing values for degrees, and coefficient in Function sum arrays.
      //sum is then returned.
      Function sum = new Function();
      for (int i = 0; i < this.cindex; i++){
         sum.addTerm(this.co[i],this.degree[i]);  
      }
      for (int i = 0; i < f.cindex; i++){
         sum.addTerm(f.co[i],f.degree[i]);  
      }
      for (int i = 0; i < this.sinindex; i++){
         sum.addTerm(this.sinc[i],"sin",this.sind[i]);  
      } 
      for (int i = 0; i < f.sinindex; i++){
         sum.addTerm(f.sinc[i],"sin",f.sind[i]);  
      }
      for (int i = 0; i < this.cosindex; i++){
         sum.addTerm(this.cosc[i],"cos",this.cosd[i]);  
      }
      for (int i = 0; i < f.cosindex; i++){
         sum.addTerm(f.cosc[i],"cos",f.cosd[i]);  
      }
      for (int i = 0; i < this.tanindex; i++){
         sum.addTerm(this.tanc[i],"tan",this.tand[i]);  
      }
      for (int i = 0; i < f.tanindex; i++){
         sum.addTerm(f.tanc[i],"tan",f.tand[i]);  
      }
      return sum;          
	} 
	
	// returns a Function that is the subtraction of this Function with f
	// (combine similar terms when possible)
   //subract method uses similar logic to add method, with the coefficinets for f
   //being negative in order to subract.
	public Function subtract(Function f) {
      Function diff = new Function();
      for (int i = 0; i < this.cindex; i++){
         diff.addTerm(this.co[i],this.degree[i]);  
      }
      for (int i = 0; i < f.cindex; i++){
         diff.addTerm(-1*f.co[i],f.degree[i]);  
      }
      for (int i = 0; i < this.sinindex; i++){
         diff.addTerm(this.sinc[i],"sin",this.sind[i]);  
      } 
      for (int i = 0; i < f.sinindex; i++){
         diff.addTerm(-1*f.sinc[i],"sin",f.sind[i]);  
      }
      for (int i = 0; i < this.cosindex; i++){
         diff.addTerm(this.cosc[i],"cos",this.cosd[i]);  
      }
      for (int i = 0; i < f.cosindex; i++){
         diff.addTerm(-1*f.cosc[i],"cos",f.cosd[i]);  
      }
      for (int i = 0; i < this.tanindex; i++){
         diff.addTerm(this.tanc[i],"tan",this.tand[i]);  
      }
      for (int i = 0; i < f.tanindex; i++){
         diff.addTerm(-1*f.tanc[i],"tan",f.tand[i]);  
      }
      return diff; 
	} 

	// returns a String to represent the function
	// - combine similar terms
	// - show all coefficients to two places after decimal
	// - omit coefficients of 1.0000 except for negative exponents.
	// [There are NO specifications as to the order terms are displayed].
	//
	// Good Examples:
	//     4.00cos(x)-tan(3.00x)+7.00x^8.00-x^2.00+6.00+9.00x^(-1.00)
	//     -4.50sin(x)+sin(2.5x)+3.25sin(2.0x)
	//	
	// Bad Examples:
	//     7.00x^8.00-1.00x^2.00+6.00+9.00x^-1.00
	//     -4.50sin(x)+sin(x)+3.25sin(x)
	//     7.00x^4.00-x^4.00
	//
	// NOTE: String.format method will be useful/similar to System.out.printf method.
	//
	public String toString() {
      String function = "";
      //in order to print function as a string, we must go through all the possibilities of
      //how snytax between how functions are written and my be interpreted should be explored.
      //for instance if a term = 0 such as sin(0), you wouldnt write sin(0). Or x^0, you would
      //simply represent this as 1.
      //using if else statements within a for loop i scan through all the terms stored in the 
      //arrays and print them accordingly following proper syntax for writing equations,
      //it is quite a bit of code and i found it difficult annotating this method since
      //it was mainly logic.
      boolean start = true;
      for(int i = 0; i < cindex; i++){
         boolean nothing = false;
         if(start){
            if(co[i] == 0){
               nothing = true;
            }else if(co[i] == -1){
               function += "-";
               start = false;
            }else if(co[i] == 1){
               start = false;
               if(degree[i] == 0){
                  function += String.format("%.02f", co[i]);
               }
            }else{
               function += String.format("%.02f", co[i]);
               start = false;
            }
         }else{
            if(co[i] == 0){
               nothing = true;
            }else if(co[i] == -1){
               function += "-";
            }else if(co[i] == 1){
               function += "+";
            }else if(co[i] > 0){
               function += "+" + String.format("%.02f", co[i]);
            }else{
               function += String.format("%.02f", co[i]);
            }
         }
         if(!nothing){
            if(degree[i] == 1){
               function += "x";
            }else if(degree[i] == 0){
               function = function;
            }else if(degree[i] > 0){
               function += "x^" + String.format("%.02f", degree[i]);
            }else{
               function += "x^(" + String.format("%.02f", degree[i]) + ")";
            }
         }
      }
      for(int i = 0; i < sinindex; i++){
         boolean nothing = false;
         if(sind[i] != 0){
            if(start){
               if(sinc[i] == 0){
                  nothing = true;
               }else if(sinc[i] == -1){
                  function += "-";
                  start = false;
               }else if(sinc[i] == 1){
                  start = false;
               }else{
                  function += String.format("%.02f", sinc[i]);
                  start = false;
               }
            }else{
               if(sinc[i] == 0){
                  nothing = true;
               }else if(sinc[i] == -1){
                  function += "-";
               }else if(sinc[i] == 1){
                  function += "+";
               }else if(sinc[i] > 0){
                  function += "+" + String.format("%.02f", sinc[i]);
               }else{
                  function += String.format("%.02f", sinc[i]);
               }
            }
         }
         if(!nothing){
            if(sind[i] == 1){
               function += "sin(x)";
            }else if(sind[i] == -1){
               function += "sin(-x)";
            }else if(sind[i] == 0){
               function = function;
            }else{
               function += "sin(" + String.format("%.02f", sind[i]) + "x)";
            }
         }
      }
      for(int i = 0; i < tanindex; i++){
         boolean nothing = false;
         if(tand[i] != 0){
            if(start){
               if(tanc[i] == 0){
                  nothing = true;
               }else if(tanc[i] == -1){
                  function += "-";
                  start = false;
               }else if(tanc[i] == 1){
                  start = false;
               }else{
                  function += String.format("%.02f", tanc[i]);
                  start = false;
               }
            }else{
               if(tanc[i] == 0){
                  nothing = true;
               }else if(tanc[i] == -1){
                  function += "-";
               }else if(tanc[i] == 1){
                  function += "+";
               }else if(tanc[i] > 0){
                  function += "+" + String.format("%.02f", tanc[i]);
               }else{
                  function += String.format("%.02f", tanc[i]);
               }
            }
         }
         if(!nothing){
            if(tand[i] == 1){
               function += "tan(x)";
            }else if(tand[i] == -1){
               function += "tan(-x)";
            }else if(tand[i] == 0){
               function = function;
            }else{
               function += "tan(" + String.format("%.02f", tand[i]) + "x)";
            }
         }
      }
      for(int i = 0; i < cosindex; i++){
         boolean nothing = false;
         if(start){
            if(cosc[i] == 0){
               nothing = true;
            }else if(cosc[i] == -1){
               function += "-";
               start = false;
            }else if(cosc[i] == 1){
               start = false;
            }else{
               function += String.format("%.02f", cosc[i]);
               start = false;            
            }
         }else{
            if(cosc[i] == 0){
               nothing = true;
            }else if(cosc[i] == -1){
               function += "-";
            }else if(cosc[i] == 1){
               function += "+";
            }else if(cosc[i] > 0){
               function += "+" + String.format("%.02f", cosc[i]);
            }else{
               function += String.format("%.02f", cosc[i]);
            }
         }
         if(!nothing){
            if(cosd[i] == 1){
               function += "cos(x)";
            }else if(cosd[i] == -1){
               function += "cos(-x)";
            }else if(cosd[i] == 0){
               function = function;
            }else{
               function += "cos(" + String.format("%.02f", cosd[i]) + "x)";
            }
         }
      }
      return function;
	} 


	// return function slope=rise/run using a deltaX of 0.0000000001 symmetrically about X
	// (i.e. let run be defined by: X-0.00000000005 to X+0.00000000005)
	public double slope(double X) {
      double slope; //double slope is created which will be retruned
      double deltaX = 0.0000000001; //slope equation is (x2-x1)/(y2-y1).
      double yInitial = evaluate(X - 0.00000000005);
      double yFinal = evaluate(X + 0.00000000005);
      double deltaY = yFinal - yInitial; 
      slope = deltaY/deltaX; // slope equation represented in code.
      return slope; 
	} 

	// return the integral value of the function between x value interval, start to end
	// Use 10 million vertical, trapezoidal slices to determine integral value.
	// If start is greater than end, return the negative value of the integration end to start.
	public double integral(double start, double end) {
      double h = start - end; // h is distance between x values.
      //this will be divided into 10000000 to get length of trapazoid, height is found later
      //using evaluate method to find y.
      //integral is basically area under curve. x initial and x final is given so it makes this
      //quite simple.
      double area = 0.5 * evaluate(start) + 0.5 * evaluate(end);
      h = h/10000000;
      //if h is positive end is greater than start, otherwise it is the opposite.
      if(h >= 0){
         for(int i = 1; i < 10000000; i++){ //for loop set to go through each trapazoid and find height
                                            //then added to compounding sum through variable area.
            double point = h*i + start;
            area += evaluate(point);
         }
      }else{
         h = end - start;
         h = h/10000000;
         for(int i = 1; i < 10000000; i++){
            double point = h*i + end;
            area += evaluate(point);
         }
         area = -1 * area; // inputs negative area when h is negative, but allows for proper computation of value.
      }   
      return area;
       
	} 

	// Use any additional methods as you see fit.

} //End Class
