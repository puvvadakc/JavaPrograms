// Krishna Puvvada
// CSE 143 AB, TA: Erika Wolfe
// 10/5/2017
/*
  Class LetterInventory can be used to organize and store the character count
  of letters in a String. This class does NOT store the count of any other 
  characters other than letters (not case sensitive). The following methods are
  available for this class: get(char letter), set(char letter, int value),
  size(), isEmpty(), toString(), add(LetterInventory other),
  subtract(LetterInventory other).
*/

public class LetterInventory{
   
   private int[] countArray;
   private boolean empty;
   private int totalSum;
   private static final int CAPACITY = 26;
   
   
   // If no String is given as input, all letter counts are initialized to 0.
   
   public LetterInventory(){
      this("");
   }
   
   
   // If a String is given as input the letters counts will be set and stored accordingly.
   
   public LetterInventory(String data){
      char cLetter = 'a';
      empty = true;
      totalSum = 0;
      countArray = new int[CAPACITY];
      String lcData = data.toLowerCase();
      int lettersFromA;
      
      for (int i = 0; i < lcData.length(); i++){
         lettersFromA = lcData.charAt(i) - 'a';
         if(lettersFromA >= 0 && lettersFromA <= 25){
            countArray[lettersFromA]++;
            empty = false;
            totalSum++;
         }
      }   
   }
   

   // Pre: character input of a letter should be given. Character MUST be a letter 
   // (not case sensitive), otherwise an IllegaArgumentException will be thrown.
   //  
   // Post: Indexes the letter and returns the corresponding integer count value 
   // that has been stored in a certain LetterInventory object. If a character that
   // is not a letter is inputted -1 will be returned.
   
   public int get(char letter){
      char uLetter = Character.toLowerCase(letter);
      int lettersFromA = uLetter - 'a';
      
      if(lettersFromA < 0 || lettersFromA > 25){
         throw new IllegalArgumentException("char must be a letter");
      }
      
      if(lettersFromA >= 0 && lettersFromA <= 25){
         return countArray[lettersFromA];
      }else{
         return -1;
      }      
   }
   

   // Pre: character input of a letter, and an positive integer value must be
   // given. If properly specified inputs are NOT given an IllegalArgumentException 
   // will be thrown.
   //  
   // Post: Indexes the letter and sets the corresponding count to the inputed 
   // integer value. 
   
   public void set(char letter, int value){
      char uLetter = Character.toLowerCase(letter);
      int lettersFromA = uLetter - 'a';
      
      if(lettersFromA < 0 || lettersFromA > 25){
         throw new IllegalArgumentException("char must be a letter");
      }
      
      if(value < 0){
         throw new IllegalArgumentException("must be a positive integer");
      }
      
      if(lettersFromA >= 0 && lettersFromA <= 25 && value >= 0){
         totalSum += value - countArray[lettersFromA];
         countArray[lettersFromA] = value;
      }
      
      if(totalSum > 0){
         empty = false;
      }else{
         empty = true;
      }
   }
   
 
   // Returns the size or total count of all the letters that have been stored in a 
   // certain LetterInventory object. 
   
   public int size(){
      return totalSum; 
   }
   
 
   // If LetterInventory object is empty isEmpty method will return true, if there are 
   // sored values isEmpty method will return false.
   
   public boolean isEmpty(){
      return empty;
   }
   

   // toString method will return a string representation of all the letters being stored. 
   // The letters will be ordered in lower case within square brackets.
   //  
   //   Ex. If there are 3 a's 2 b's and 1 c in a string, the following will be returned:
   //   [aaabbc]  
   
   public String toString(){
      String orgString = "[";
      
      for(int i = 0; i < CAPACITY; i++){
         for(int j = 0; j < countArray[i]; j++){
            orgString += (char)('a' + i); // (char) converts integer value to char value.
         }
      } 
      
      orgString += "]";
      return orgString;
   }
   
   
 
   // Pre: proper LetterInventoy object needs to be inputed into the add method.
   //
   // Post: add method finds the sum of counts between two LetterInventory objects. 
   // The method returns a new LetterInventory object with the sum of counts for each 
   // corresponding letter. 

   
   public LetterInventory add (LetterInventory other){
      LetterInventory sum = new LetterInventory();
      
      for(int i = 0; i < CAPACITY; i++){
         sum.countArray[i] = this.countArray[i] + other.countArray[i];
         sum.totalSum += sum.countArray[i];
      }
      
      if(sum.totalSum > 0){
         sum.empty = false;
      }
      
      return sum;
   }
   
   
 
   // Pre: proper LetterInventoy object needs to be inputed into the subtract method.
   //
   // Post: subtract method finds the difference of counts between two LetterInventory objects. 
   // The method returns a new LetterInventory object with the difference of counts for each 
   // corresponding letter. The inputed LetterInventory object values will be subtracted from
   // the current LetterInventory object. If any difference between the counts is a negative 
   // integer the method will return null. 

   
   public LetterInventory subtract(LetterInventory other){
      LetterInventory diff = new LetterInventory();
      
      for(int i = 0; i < CAPACITY; i++){
         diff.countArray[i] = this.countArray[i] - other.countArray[i];
         if(diff.countArray[i] < 0){
            return null;
         }
         diff.totalSum += diff.countArray[i];
      }
      
      if(diff.totalSum > 0){
         diff.empty = false;
      }
      
      return diff;
   }
}