//Krishna Puvvada
//CS 210 D Frank Lee
//26 April 2017
//Program 2

//This program will conduct two tasks. The first task is creating a spread sheet that records annual interest and annual
//deposit on an investment per year. The second task is calculating the distance in miles between two locations specified
//the longitude and latitude.  
import java.util.*; //imports utilities such as scanner console.

public class PuvvadaKrishna2 {
      //main method contains code determining user input for multiple variables in the two projects.
      public static void main(String[] args) {
            Scanner console = new Scanner(System.in); //creates new scanner for user input.
            //Beginning Project Two
            System.out.println("PROJECT 2:");
            System.out.print("What is initial investment? ");
            double investment = console.nextDouble(); //asks user for initial investment.
            //If investment is negative, for loop below provides error message and asks to reenter valid amount.
            for(int i = 0; i > investment;){
                  System.out.println("INVALID INVESTMENT");
                  System.out.print("What is initial investment? ");
                  investment = console.nextDouble();
            }
            System.out.print("What is interest rate(%)? ");
            double interest = console.nextDouble(); //asks user for interest rate.
            //If interest is negative, for loop below provides error message and asks to reenter valid percentage.
            for(int i = 0; i > interest;){
                  System.out.println("INVALID INTEREST RATE");
                  System.out.print("What is interest rate(%)? ");
                  interest = console.nextDouble();
            }
            System.out.print("What is annual deposit? ");
            double deposit = console.nextDouble(); //asks user for annual deposit.
            //for loop functions similarly as above.
            for(int i = 0; i > deposit;){
                  System.out.println("INVALID ANNUAL DEPOSIT");
                  System.out.print("What is annual deposit? ");
                  deposit = console.nextDouble();
            }
            System.out.print("How many years? ");
            int years = console.nextInt(); //Asks user how many years to be calculated on spreadsheet.
            //For loop functions similarly to above if negative year value is provided.
            for(int i = 0; i > years;){
                  System.out.println("INVALID AMOUNT OF YEARS"); 
                  System.out.print("How many years? ");
                  years = console.nextInt();
            }
            projectTwo(investment, interest, deposit, years);//calls for projectTwo method.
            //Beginning of Project 5.
            System.out.println();
            System.out.println("PROJECT 5:");
            System.out.print("Latitude of First Location: ");
            double firstLat = console.nextDouble(); //asks user for latitude of first location.
            //for loops below check whether latitude is valid point between -90 and 90, and if 
            //not, prompts the user to reenter valid value.
            for(int i = -90; i > firstLat;){        //checks if value is below -90.
                  System.out.println("INVALID LATITUDE");
                  System.out.print("Latitude of First Location: ");
                  firstLat = console.nextDouble();
            }
            for(int i = 90; i < firstLat;){        //checks if value is above 90.
                  System.out.println("INVALID LATITUDE");
                  System.out.print("Latitude of First Location: ");
                  firstLat = console.nextDouble();
            }
            
            System.out.print("Longitude of First Location: ");
            double firstLong = console.nextDouble(); // Asks user for longitude of first location.
            //for loops below functions similarly to above, but with restrictions at -180 and 180
            // for longitude.
            for(int i = -180; i > firstLong;){
                  System.out.println("INVALID LONGITUDE");
                  System.out.print("Longitude of First Location: ");
                  firstLong = console.nextDouble();
            }
            for(int i = 180; i < firstLong;){
                  System.out.println("INVALID LONGITUDE");
                  System.out.print("Longitude of First Location: ");
                  firstLong = console.nextDouble();
            }
            
            System.out.print("Latitude of Second Location: ");
            double secondLat = console.nextDouble(); //Asks for latitude of second location.
            //For loops function similarly as above.
            for(int i = -90; i > secondLat;){
                  System.out.println("INVALID LATITUDE");
                  System.out.print("Latitude of Second Location: ");
                  secondLat = console.nextDouble();
            }
            for(int i = 90; i < secondLat;){
                  System.out.println("INVALID LATITUDE");
                  System.out.print("Latitude of Second Location: ");
                  secondLat = console.nextDouble();
            }
            
            System.out.print("Longitude of Second Location: ");
            double secondLong = console.nextDouble(); //Asks for longitude of second location.
            //For loops function similarly as above.
            for(int i = -180; i > secondLong;){
                  System.out.println("INVALID LONGITUDE");
                  System.out.print("Longitude of Second Location: ");
                  secondLong = console.nextDouble();
            }
            for(int i = 180; i < secondLong;){
                  System.out.println("INVALID LONGITUDE");
                  System.out.print("Longitude of Second Location: ");
                  secondLong = console.nextDouble();
            }
            
            projectFive(firstLat, firstLong, secondLat, secondLong); //calls for projectFive method.

      }
            
      public static void projectTwo(double investment, double interest, double deposit, int years){
            //Set of System.out.print create structure and labels on the spreadsheet.
            System.out.print("          Current        " + interest);
            System.out.println("%                    New");
            System.out.println(" Year     Balance     Interest     Deposit     Balance");
            System.out.println("======   =========   ==========   =========   =========");
            int yearno = 1; //Year number after investment, aids in creating numarical list.
            double intamount; //Interest amount in dollars, basicly interest rate x investment.
            // below for loop creates the mutiple rows on the spread sheet.
            for (int row = 1; row <= years; row++){
                  System.out.print(yearno + "        ");
                  investment = Math.round(investment*100)/100.00; //rounds investment.
                  System.out.print("$ "+ investment +"   $    ");
                  intamount = interest/100* investment; //calculates interest amount.
                  intamount = Math.round(intamount*100)/100.00; //rounds interest amount.
                  System.out.print(intamount + "   $  "); 
                  deposit = Math.round(deposit*100)/100.00; //rounds deposit.
                  System.out.print(deposit + "   $ ");
                  investment = investment + deposit + intamount; //calculates final amount in investment.
                  investment = Math.round(investment*100)/100.00; //rounds investment.
                  System.out.println(investment);
                  yearno++; //increases year number.
            }
      }
      public static void projectFive(double firstLat, double firstLong, double secondLat, double secondLong){
            //Below changes latitude and longitude inputs which are given in degrees into radians.
            firstLat = firstLat * Math.PI/180;
            firstLong = firstLong * Math.PI/180;
            secondLat = secondLat * Math.PI/180;
            secondLong = secondLong * Math.PI/180;
            
            //Below the varibles are placed into the equation to solve for distance between two points.
            double longDiff = firstLong - secondLong; //calculates difference in longitude.
            // Below calculates anglulr difference.
            double angDiff = Math.acos(Math.sin(firstLat)*Math.sin(secondLat)
            +Math.cos(firstLat)*Math.cos(secondLat)*Math.cos(longDiff));
            double distance = 6372.795*angDiff; //By multiplying Earths radius we can find distance in miles.
            distance = Math.round(distance*1000)/1000.0; //Rounds to the nearest thousandth.
            System.out.println("Distance between two locations is " + distance + " miles.");
      }
}