// Krishna Puvvada
// CSE 143 AB, TA: Erika Wolfe
// 10/12/2017
/*
  Class Guitar37  simulates a guitar which consists of 37 different strings,
  associated with different pitches ranging from -24 to 12 respectively. The strings can
  be accessed with the following keys:
  
  q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/'
  
  The keys are numerically ordered with q associated with pitch -24, 2 associated with -23,
  and so on. Spacebar has a string as well with the highest pitch of 12. The Guitar will NOT
  play a string if any other key is used (letters NOT case sensitive).
  
  The following methods are available: playNote(int pitch), hasString (char string), 
  pluck (char string), sample(), tic(), time().
  
  Requires Guitar String class, StdAudio class, Guitar interface to function properly.
*/


public class Guitar37 implements Guitar {
    public static final String KEYBOARD =
        "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout
    private int ticTime;
    private GuitarString[] stringArray;
    
    
    // Simulated guitar with 37 strings of different pitches is initialized.
    // Each string is associated with a different key on the keyboard. 
    
    public Guitar37(){
      
      stringArray = new GuitarString[KEYBOARD.length()];
      ticTime = 0;
      
      for (int i = 0; i < stringArray.length; i++){
         stringArray[i] = new GuitarString(440*Math.pow(2,(i-24)/12.0));
      }    
    }
    
    
    // Pre: A valid pitch (integer value ranging from -24 to 12) must be inputted into the
    // method. The method will do nothing if a proper pitch value is not inputted.
    // Post: The string corrusponding to the inputted pitch will be played.
    
    public void playNote(int pitch) {
      
      if(pitch >= -24 && pitch <= 12){
         stringArray[pitch + 24].pluck();
      }
    }
    
    
    // Pre: This method can be used to check if a certain key on the keyboard has a pitch
    // and corrusponding string associated to it. A character must be inputted into the method
    // for this check to proceed.
    // Post: If there is a string associated with the key the method will return true, if NOT
    // it will return false.
    
    public boolean hasString (char string){
      
      if(KEYBOARD.indexOf(Character.toLowerCase(string)) != -1){
         return true;
      }else{
         return false;
      }
    }
    
    
    // Pre: A character (key) that has a string associated with it must be inputted into
    // the method. The method will throw an Illegal Argument Exception if the inputted key
    // does not have an associated string.
    // Post: The method will play the string to associated inputed key on the guitar.
    
    public void pluck (char string){
      int stringIndex = KEYBOARD.indexOf(Character.toLowerCase(string));
      
      if(stringIndex == -1){
         throw new IllegalArgumentException
         (" String is not available within this guitar. Please choose one of the"
          + " following available strings: q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' "  
          + " (spacebar is string as well).");
      }
      
      if(stringIndex != -1){
         stringArray[stringIndex].pluck();
      }
    }
    
    
    // Returns the current value of the sample corrusponding to all the strings that have
    // been played on the guitar.
    
    public double sample(){
      double sampleSum = 0;
      
      for(int i = 0; i < stringArray.length; i++){
         sampleSum += stringArray[i].sample();
      }
      return sampleSum;
    }
    
    
    // Simulates one tic (unit of time) during and after a string has been played and 
    // accounts for the displacement of sound as time goes by. 
    
    public void tic(){
      
      for(int i = 0; i < stringArray.length; i++){
         stringArray[i].tic();
      }
      ticTime++;
    }
    
    
    // Records the total amount of tics (unit of time) that has been played in the current
    // session of Guitar37.
    
    public int time(){
      return ticTime;
    }

}