// Krishna Puvvada
// CSE 143 AB, TA: Erika Wolfe
// 10/12/2017
/*
  Class GuitarString simulates a guitar string at a specified sound frequency. A guitar 
  string will NOT be simulated if no frequency is specified. The following methods are
  available: pluck(), tic(), sample(). Requires StdAudio class to function.
*/


import java.util.*;

public class GuitarString{
   
   public static final int SAMPLE_RATE = StdAudio.SAMPLE_RATE;
   private int n;
   private Queue<Double> ringBuffer;
   
   
   // Simulated guitar string is initialized acoording to thre frequency. Frequency must be 
   // greater than zero or Illegal Argument Exception will be thrown. Frequency must also be
   // less than or equal to Sample Rate divided by 2. Sample Rate is typically 44,100, so 
   // make sure frequency is less than or equal to 22,050 or Illegal Argument Exception will
   // be thrown
   
   public GuitarString (double frequency){ 
      ringBuffer = new LinkedList<Double>();
      
      if (frequency <= 0){
         throw new IllegalArgumentException ("Frequency has to be greater than zero.");
      }
      
      if (frequency > 0){
         n = (int)(Math.round(SAMPLE_RATE/frequency));
         
         if (n < 2){
            throw new IllegalArgumentException 
            ("(Sample rate)/frequency needs to be greater than 2. Please provide a frequency" 
             + " less than or equal to (sample rate)/2 ");
         }
         
         if (n >= 2){
            
            for (int i = 0; i < n; i++){
               ringBuffer.add((double)(0));
            }
         }
      }
   }
   
   
   // Requires manually inputing values of the Ring Buffer (wavelength values when 
   // string is played with an array. This is of no practical use, and an input of 
   // frequency is suggested, for better results. There must be two or more values
   // inputted or Illegal Argument Exception will be thrown. 
  
   public GuitarString (double[] init){
      n = init.length;
      ringBuffer = new LinkedList<Double>();
      
      if(n < 2){
         throw new IllegalArgumentException 
         (" There needs to be two or more values in the Ring Buffer, please input an array" 
         + " with two or more value");
      }
      
      if(n >= 2){
         for(int i = 0; i < n; i++){
            ringBuffer.add(init[i]);
         }
      }
   }
   
   
   // Simulates the guitar string being strummed, for a cetrain frequency.
   
   public void pluck(){
      
      for(int i = 0; i < n; i++){
         ringBuffer.add(Math.random() - 0.5);
         ringBuffer.remove();
      }
   }
   
   
   // Simulates one tic (unit of time) during and after a string has been played and 
   // accounts for the displacement of sound as time goes by.
   
   public void tic(){
      double head = ringBuffer.remove();
      ringBuffer.add(0.996 * 0.5 * (head + ringBuffer.peek()));  
   }
   
   
   // Returns the value of the sample which is the initial value of the wavelength of sound.
   
   public double sample(){
    return ringBuffer.peek();
   }
}
