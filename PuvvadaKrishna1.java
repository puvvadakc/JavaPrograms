//Krishna Puvvada
//CS 210 D Frank Lee
//11 April 2017
//Program 1

// This program will perform three tasks, the first being printing
// out the first 20 integers of the Fibonacci sequence, the second
// task creates a figure made of consecutive numerals increasing in
// value and frequency through the progression of the figure, and the
// third task is to create a specific stairway figure, that uses a class
// constant to change the amount of stairs in the figure.

public class PuvvadaKrishna1 {

//class constant for third task staircase, which changes amount of
//stairs in figure.
public static final int SIZE = 5;

// Prints titles and executes each task.
      public static void main(String[] args) {
            System.out.println("Fibonacci Sequence:");
            fibonacci();
            System.out.println("Number Figure:");
            numberFigure();
            System.out.println("Staircase Figure:");
            stairs(); 
      }
      
//Prints first 20 digits of the Fibonacci sequence.      
      public static void fibonacci(){
//Two variables set at 1, which represent first 2 digits of sequence.
// Commas added to sequence.
            int prev = 1;
            int current = 1;
            int next = 1;
            System.out.print(prev + ", " + current);
//For loop which adds "first" and "second" digit and sets this value 
//as the "first" variable, and then adding this new "first" value and 
//adding to "second" value and setting this sum as the new "second" value.
//This is in a loop so once 20 digits are printed the function will end. 
            
            for(int i = 2; i < 20; i++){
            next=prev+current;
            System.out.print( ", " + next);
            prev=current;
            current=next;
            }
//Used a loop to add extra space between each task being performed
//for organization.
            for(int n = 0; n < 2; n++){
                  System.out.println();
            }
      }
      
//Prints the number figure, aspects accounted for were amount of spaces and
// numerals on each line.      
      public static void numberFigure(){
//Two variables set, num tracks the value of the number being printed, and
//length tracks value of amount of numbers being printed.
            int num = 1;
            int length = 9;
//For loop in order for printing the number of lines with charectors.
            for(int j = 0; j < length; j++){
//For loop inside main for loop controlling amount of spaces printed on each line.
                  for(int k = 0; k < (length - num); k++){
                        System.out.print(" ");
                  }
//For loop inside main for loop controlling amount of numbers being printed on 
//each line.
                  for(int m = 1; m <= num; m++){
                        System.out.print(num);
                  }
//Increases value of "num" variable which increases the value of the number
//being printed.
                  num++;
                  System.out.println();
            }
            System.out.println();       
      }
      
//Prints the unique figure of a staircase with a stick figure on each step.
//Class constant SIZE controls how many stepsthere are.      
      public static void stairs(){
//"step" variable records amount of space sections to add on the left.
//"gap" variable to records amount of space sections to the right.            
            int step = 1;
            int gap = SIZE - step;
//Main for loop produces the amount steps that "SIZE" determines.
//Each Step is broken down in to three lines,top, middle and bottom.
//Each line has its  own set of for loops to produce.
            for(int p = 0; p < SIZE; p++){
//Start of first line.
//First for loop accounts for space on left side of line.
                  for(int q = 0; q < gap; q++){
                        System.out.print("     ");
                  }
                  System.out.print("  O  ******");
//Second for loop accounts for space on right side of line.
                  for(int r = 1; r < step; r++){
                        System.out.print("     ");
                  }
//Stair verticle border.
                  System.out.println("*");
//Start of middle line.
//For loops are formatted similarly.
                  for(int q = 0; q < gap; q++){
                        System.out.print("     ");
                  }
                  System.out.print(" /|\\ *     ");
                  for(int r = 1; r < step; r++){
                        System.out.print("     ");
                  }
                  System.out.println("*");
//Start of bottom line. 
                  for(int q = 0; q < gap; q++){
                        System.out.print("     ");
                  }
                  System.out.print(" / \\ *     ");
                  for(int r = 1; r < step; r++){
                        System.out.print("     ");
                  }
                  System.out.println("*");
//"step" and "gap variable increased and decreased respectively in order to increase
//and deacrease amount of space on left and right.
                  step++;
                  gap--;     
            }
//Prints the bottom line of the staircase.
            System.out.print("*");
            for(int f = 0; f < SIZE;f++){
                  System.out.print("*****");
            }
            System.out.println("******");
      }
}