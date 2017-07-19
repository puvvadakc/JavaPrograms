//Krishna Puvvada
//This program will calculate the sum of a certain amount of even Fibbinacci values
//dependent on the amount of values the user specifies.
import java.util.*;

public class EvenFibbinacciSum{

   public static void main(String[] args){
      Scanner console = new Scanner(System.in);
      boolean end = false;
      while (!end){
         System.out.print("How many even Fibbinacci values would you like to add(Up to 15):");
         int value = console.nextInt();
         System.out.println("The sum of " + value + " even values is: " + evenSum(value));
         System.out.print("Would you like to calculate another sum (no = 0, yes = 1):");
         int answer = console.nextInt();
         if(answer == 1){
            System.out.print("Awesome! ");
         }else if(answer == 0){
            System.out.println("Good Bye");
            end = true;
         }else{
            while(answer != 1 && answer != 0){
               System.out.println("INVALID INPUT - Please enter 1(Yes) or 0(No): ");
               answer = console.nextInt();
               if(answer == 1){
                  System.out.print("Awesome! ");
               }else if(answer == 0){
                  System.out.println("Good Bye");
                  end = true;
               }
            }
         } 
      }
   }
   public static int evenSum(int value){
      int amount = 0;
      int previous = 1;
      int current = 1;
      int next = 0;
      int sum = 0;
      while(amount < value){
         next = previous + current;
         if(next%2 == 0){
            sum += next;
            amount++;
         }
         previous = current;
         current = next;
      }
      return sum;
   }
}
