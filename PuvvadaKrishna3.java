// 
// Class: CS210
// Written By:Krishna Puvvada 
// Date:	10 May 2017   
//
// Assignment3
//This program will conduct 4 tasks. The first task is returning a quadrant in 
//on a graph by the x and y value that the user inputs. The second task is to
//calculate income tax using the irs database, accoording to the income the user
//inputs. The third task is to calculate the amount of seconds after midnight a
// cetrain time is, which the user inputs. The fourth task is to calculate the
//difference is seconds between two times, that are inputed by the user aswell. 
// 
import java.util.*;

public class PuvvadaKrishna3 { 
	
	// quadrant
	// Method specifications as in textbook Chapter 4 Exercise 19
	// Calculates what quadrant a certain point is in.
   // The book says to return a point on an axis as 0, but thats too easy
   // so I created a self check which requires the user to input non zero
   //values for x and y, code can be cound in main.
	public static int quadrant(double x, double y) {
         int quadrant = 0; // variable quadrant is initialized
         if (x > 0 && y > 0){ // code for quadrant 1
               quadrant = 1;
         } else if (x < 0 && y < 0){ //code for quadrant 3   
               quadrant = 3;
         } else if (x < 0 && y > 0){ //code for quadrant 2
               quadrant = 2;
         } else {    // defaulted to quadrant 4
               quadrant = 4;
         }
         return quadrant; 
	} 
   //
	// singleTax
	// Use current year 1040 tax instructions (http://www.irs.gov/pub/irs-pdf/i1040.pdf).
	//                  1040 form (http://www.irs.gov/pub/irs-pdf/f1040.pdf).
	// Input:   taxable income (Line 43 of 1040 form)
	// Returns: the proper tax for single filing status (Line 44 of 1040 form)
	// 
	// To determine tax:
	// 		Use Tax Table
	//		or 
	//      Tax Computation as appropriate.
	//
	// - Round tax to nearest penny.
	// - Return 0 if taxable income is negative
	//
	//
	public static double singleTax(double income) {
	   //all values and calculations based off of 1040 form.
      double tax; //initializing tax, will be returned in the end.
      if (income < 0){ //determine whether income is negative.
            tax = 0;
      } else if (income > 0 && income <= 9275){
            tax = income * 0.1; //decimal is the tax rate for associated income
      } else if (income > 9275 && income <= 37650){
            tax = income * 0.15; 
      } else if (income > 37650 && income <= 91150){
            tax = income * 0.25;
      } else if (income > 91150 && income <= 190150){
            tax = income * 0.28 - 6963.25; //incorperates reimbursment at higher incomes
      } else if (income > 190150 && income <= 413350){
            tax = income * 0.33 - 16470.75;
      } else if (income > 413350 && income <= 415050){
            tax = income * 0.35 - 24737.75;
      } else {
            tax = income * 0.396 - 43830.05;
      }
        tax = Math.round(tax * 100.0)/100.0; //rounds tax to nearest cent.
         return tax; //returns tax as value to main
	} 
	
	//
	// secondsAfterMidnight
	// Input:   String that represents time of day
	// Returns: integer number of seconds after midnight (return -1 if String is not valid time of day)
	// 
	// General time of day format HH:MM:SS
	//
	// Examples:
	// Input String		Return Value
	// "12:34:09AM"		2049
	// "12:00:00PM"     43200 (common noon)
	// "12:00:02am"		2 (AM/PM case insensitive)
	// "3:03:03Pm"      54183 (two digit MM and SS required)
	// "7:11:03A"		-1
	// "7:11:3AM"		-1
	// "7:91:73PM"		-1
	// "23:45:12"		-1 (do not allow 24 hour clock format)
	//
	public static int secondsAfterMidnight(String t) {
      int seconds; //initializing seconds, will be returned to main.
      t.toLowerCase(); //setting all input to lowercase so it is easier to handle.
      if (t.length() == 10){ //if input is 10 characters
            // if statement below checks whether each character is appropriate before continuing.
            if((t.startsWith("0") || t.startsWith("1"))&& Character.isDigit(t.charAt(1))&& 
            t.charAt(2) == ':' && Character.isDigit(t.charAt(3))&& Character.isDigit(t.charAt(4))&&
            t.charAt(5) == ':' && Character.isDigit(t.charAt(6))&& Character.isDigit(t.charAt(7))&&
            (t.endsWith("am") || t.endsWith("pm"))){
                  // Characters converted to numeric values and hours, minutes, and seconds are
                  // calculated below.
                  int hours = Character.getNumericValue(t.charAt(0))*10 
                  + Character.getNumericValue(t.charAt(1));
                        //if hours is equal to 12 such as 12:35, switched to 0:35, for easier calc.
                        if(hours == 12){ 
                              hours = 0;
                        }
                  int minutes = Character.getNumericValue(t.charAt(3))*10 
                  + Character.getNumericValue(t.charAt(4));
                  seconds = Character.getNumericValue(t.charAt(6))*10 
                  + Character.getNumericValue(t.charAt(7)); 
                  if(minutes < 60 && seconds < 60){//checks whether minute and second input is below 60.
                        if(t.endsWith("pm")){ //adding 43200 (12 hours in seconds) if time is pm.
                              seconds = hours*3600 + minutes*60 + seconds + 43200;
                        }else{
                              seconds = hours*3600 + minutes*60 + seconds;    
                        }
                  }else{
                        seconds = -1; //if proper input is not given, seconds set to -1.
                  }
            } else {
                  seconds = -1; //improper input leads to seconds set to -1.
            }
      //below is for input that has 9 characters, such as 9:12:23am, instead of 09:12:23am. The code below
      // is very similar to to code for 10 characters above.      
      }else if (t.length() == 9){
            if(Character.isDigit(t.charAt(0))&& t.charAt(1) == ':' && Character.isDigit(t.charAt(2))
            && Character.isDigit(t.charAt(3))&& t.charAt(4) == ':' && Character.isDigit(t.charAt(5))
            && Character.isDigit(t.charAt(6))&&(t.endsWith("am") || t.endsWith("pm"))){
                  int hours = Character.getNumericValue(t.charAt(0));
                  int minutes = Character.getNumericValue(t.charAt(2))*10 
                  + Character.getNumericValue(t.charAt(3));
                  seconds = Character.getNumericValue(t.charAt(5))*10 
                  + Character.getNumericValue(t.charAt(6)); 
                  if(minutes < 60 && seconds < 60){
                        if(t.endsWith("pm")){
                              seconds = hours*3600 + minutes*60 + seconds + 43200;
                        }else{
                              seconds = hours*3600 + minutes*60 + seconds;    
                        }
                  }else{
                        seconds = -1;
                  }
            } else {
                  seconds = -1;
            }
      }else{ // if input has neither 10 or 9 characters, input not proper.
            seconds = -1;
      }
      return seconds; //seconds is returned to main.
}
      
	//
	// secondsDifference
	// Input:   two time of day Strings
	// Returns: integer number of seconds difference between time of day inputs
	//          (Returns -99999 if either time of day inputs invalid)
	//
	// General time of day format HH:MM:SS
	//
	// Examples:
	// start            end   		  	Return Value
	// "12:34:09AM"		"12:00:00PM"  	41151
	// "3:03:03PM"		"12:00:02am"	-54181
	// "6:34:52PM"		"6:34:52PM"		0
	// "3:03:03PM"      "7:91:73PM"		-99999
	// "Nice"			"Day"			-99999
	//
	public static int secondsDifference(String start, String end) {
      int difference; //difference is initialized,and will be returned at the end.
      //firstsec is the amount of seconds past midnight in the start time, and
      //secondsec is the amount of seconds past midnight in the end time. In order to
      //calculate this, secondsAfterMidnight() method was called for start and end.
      int firstsec = secondsAfterMidnight(start); 
      int secondsec = secondsAfterMidnight(end);
      //If start or end returns -1 through secondsAfterMidnight(), then the input was 
      //not proper, so difference is set to -99999.
      if (firstsec == -1 || secondsec == -1){
            difference = -99999;
      }else { //if both inputs are proper, then proceed to calculating difference.
            difference = secondsec - firstsec;
      }
      return difference; //difference is returned to main.      
   }   
         
		
	// main
	public static void main(String args[]) {
		   //Test Your Methods
         //Code associated with quadrant.
         Scanner console = new Scanner(System.in); //new scanner set for user input.
         System.out.print("x = ");
         double x = console.nextDouble(); //asks user for x value.
         while (x == 0){ //if x value is 0 then user is asked to re-enter non-zero value.
               System.out.println("Invalid Input: choose non-zero value");
               System.out.print("x = ");
               x = console.nextDouble();
         }
         System.out.print("y = ");
         double y = console.nextDouble(); //asls user for y value.
         while (y == 0){ //if y value is 0 then user is asked to re-enter non-zero value.
               System.out.println("Invalid Input: choose non-zero value");
               System.out.print("y = ");
               x = console.nextDouble();
         }
         //quadrant() method returns int value which is added to string to create output.
         System.out.println("The point is in quadrant " + quadrant (x, y) + ".");
         
         //Code associated with Tax.
         System.out.print("What is income? ");
         double income = console.nextDouble(); //Prompts user for income.
         income = Math.round(income * 100.0)/100.0; //rounds income to nearest cent.
         double tax = singleTax(income); //sets tax variable to value returned by singleTax().
         System.out.print("You will be taxed $" + tax + " for an income");
         System.out.println(" of $" + income + "."); //prints output to user.
         
         //Code associated with Midnight.
         System.out.print("What is the time? ");
         String time = console.next();	//Prompts user to input time.
         int sec = secondsAfterMidnight(time);//variable sec is set to value returned by method.
         System.out.println("Seconds after midnight: " +sec);//output is printed.
         
         //Code associated with Difference.
         System.out.print("Start time? ");
         String start = console.next(); //Prompts user to enter start time.
         System.out.print("End time? ");
         String end = console.next(); //Prompts user to enter end time.
         int diff = secondsDifference(start, end); //sets diff as value returned by method.
         System.out.println("The difference(sec) between the two times is: " + diff);
         	
		
	} //End Main

} //End Class
