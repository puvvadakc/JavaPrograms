//Krishna Puvvada
//This program finds all the distinct values produced by A^B with 2 <= A <= 100 and 2 <= B <= 100
import java.util.*;
public class DistinctPowers{
   public static int power = 100;
   public static int number = 100;
   public static void main(String[] args){
      double[] distinct = new double[10000000];
      int currentpow, currentnum;
      int amount = 0;
      for(currentnum = 2; currentnum <= number; currentnum++){
         for(currentpow = 2; currentpow <= power; currentpow++){
            if(amount == 0){
               distinct[amount] = Math.pow(currentnum, currentpow);
               amount++;
            }else{
               boolean repeat = false;
               double calc = Math.pow(currentnum, currentpow);
               for(int i = 0; i < amount; i++){
                  if ( distinct[i] == calc){
                     repeat = true;
                  }
               }
               if(!repeat){
                  distinct[amount] = calc;
                  amount++;
               }
            }
         }
      }
      System.out.println("The amount of distinct power set numbers is: " + amount);
   }
}