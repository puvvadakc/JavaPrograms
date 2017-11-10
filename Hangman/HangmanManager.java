// Krishna Puvvada
// CSE 143 AB, TA: Erika Wolfe
// 10/26/2017
/*
  Class HangmanManager is used to manage a game of tricky hangman. Hangman is a 
  game where a certain word is chosen and the player guesses letter by letter
  to figure out the word. THis class however makes the game alittle more challenging,
  with the specific word NOT being chosen until the options have been narrowed,
  through the player guessing words. This class keeps track of what letters have been
  guessed, as well as the currently pattern of the word being guessed. The following 
  methods are available for HangmanManager: words(), guessesLeft(), guesses(), 
  pattern(), record(char guess).  
*/


import java.util.*;

public class HangmanManager {
   
   private Set<String> wordSet;
   private int guesses;
   private SortedSet<Character> guessesMade;
   private String curPattern;
   
   
   // If a Collection interface words, integer length of guessed word, and integer 
   // max amount of mistakes is inputted, Hangman Manager will start a game of hangman
   // with the given specifications. An IllegalArgumentException will be thrown if the
   // collection of inputted words is empty, length of word is less than one, or if max
   // number of mistakes is less than zero.
   
   public HangmanManager(Collection<String> dictionary, int length, int max) {
      
      if (length < 1) {
         throw new IllegalArgumentException( "Make sure length of guessed "
               + "word is longer than one letter.");
      }
      if (max < 0) {
         throw new IllegalArgumentException( "Amount of mistakes allowed in the "
               + "game must be zero or greater."); 
      }
      if (dictionary.isEmpty()) {
         throw new IllegalArgumentException( "Make sure to input a non-empty "
               + "collection of words that the game will use as a word bank.");
      }
      
      guesses = max;
      curPattern = "";
      wordSet = new TreeSet<String>();
      guessesMade = new TreeSet<Character>();
      
      for (String s : dictionary) {
         
         if (s.length() == length) {
            wordSet.add(s);
         }
      }
      
      
      for (int i = 0; i < length - 1; i++) {
         curPattern += "- ";
      }
      curPattern += "-";
   }
   
   
   // words() returns a Set of Strings of the current words that are currently being
   // considered and used within the current game dependent of length of word and
   // current guesses made. 
   
   public Set<String> words() {
      return wordSet;
   }
   
   
   // guessesLeft() returns an integer amount of wrong guesses left that the player has
   // before the game is lost and over.
   
   public int guessesLeft() {

      return guesses;
   }
   
   
   // guesses() returns a SortedSet of characters which contains all the guesses that the
   // player has currently made within the game session. The letters are in ascending
   // alphabetical order within the SortedSet.
   
   public SortedSet<Character> guesses() {
      return guessesMade;
   }
   
   
   // pattern() returns the String of the pattern of the current word that is being guessed
   // within the game. 
   //
   //       ex. If the target word is "dad" and "d" has alreadt been guessed the pattern
   //           would be "d - d"
   //
   // If the current set of words being used according to the pattern is empty then the
   // method will return an IllegalStateException
   
   public String pattern() {
      
      if (wordSet.isEmpty()) {
         throw new IllegalStateException( "The set of words with a certain pattern "
               + "that the game is using is empty, NOT allowing the game to proceed.");
      }
      
      return curPattern;
   }
   
   
   // Pre: If the current set of words that the game is considering is empty, an 
   // IllegalStateException will be thrown. If the amount of wrong guesses left is less
   // than 1, then an IllegalStateException will be thrown. A valid character of a letter
   // (NOT case sensitive) must be inputted or an IllegalArgumentException will be thrown.
   // An IllegalArgumentException will also be thrown if the guess being inputted has
   // already been made within the game session. 
   //
   // Post: Determines whether the guessed letter is within the current word being guessed.
   // The method returns the integer value of how many letters of the guess were in the word.
   // If there were zero letters of the current guess within the word, then the player loses 
   // an incorrect guess, and if the guess was correct, then the pattern will be updated
   // accordingly.  
   
   public int record(char guess) {
      
      if (wordSet.isEmpty()) {
          throw new IllegalStateException( "The set of words with a certain pattern "
               + "that the game is using is empty, NOT allowing the game to proceed.");
      }
      
      if (guesses < 1) {
         throw new IllegalStateException( "The amount of guesses left in the game session "
               + "is less than one and the player has exceeded his guess limit, resulting "
               + "in a loss"); 
      }
      
      if (!Character.isLetter(guess)){
         throw new IllegalArgumentException( "The guess being inputted must be a letter of "
               + "the alphabet. (NOT case sensitive)");
      }
      
      if (guessesMade.contains(Character.toLowerCase(guess))) {
         throw new IllegalArgumentException( "The guess of the letter " + guess + " has"
               + " already been made, and can NOT be used again");
      }
      
      Map<String, Set<String>> possWords = createMap(wordSet, guess);
         
      int largestPosCount = 0;
      String bestPattern = "";
      
      for (String key: possWords.keySet()) {
         
         if (possWords.get(key).size() > largestPosCount) {
            largestPosCount = possWords.get(key).size();
            bestPattern = key;
         }
      }
      
      if (bestPattern == curPattern) {
         guesses--;
      }
      
      curPattern = bestPattern;
      wordSet = possWords.get(bestPattern);
      guessesMade.add(Character.toLowerCase(guess));
      int count = 0;
      
      for (int i = 0; i < bestPattern.length(); i++) {
         
         if (bestPattern.charAt(i) == Character.toLowerCase(guess)) {
            count++;
         }
      }
      return count;        
   } 
   
   
   // Pre: A Set of Strings of words, and a letter character are inputted into createMap.
   //
   // Post: A map of the words organized by pattern according to the guessed letter is returned.
   
   private Map<String, Set<String>> createMap(Set<String> words, char guess){
      
      Map<String, Set<String>> possWords = new TreeMap<String, Set<String>>();
      
      for (String n : words) {
         String propPattern = curPattern;
         
         for (int i = 0; i < n.length(); i++) {
            
            if (n.charAt(i) == Character.toLowerCase(guess)) {
               
               if (i == 0) {
                  propPattern = Character.toLowerCase(guess) 
                        + propPattern.substring(i + 1, propPattern.length());
               
               } else if (i * 2 == propPattern.length() - 1) {
                  propPattern = propPattern.substring(0, i * 2) + Character.toLowerCase(guess);
               
               } else {                 
                  propPattern = propPattern.substring(0, i * 2) + Character.toLowerCase(guess) 
                        + propPattern.substring(i * 2 + 1, propPattern.length());
               }
            }
         }
         
         if (!possWords.containsKey(propPattern)) {
            possWords.put(propPattern, new TreeSet<String>(Arrays.asList(n)));
         
         } else {
            possWords.get(propPattern).add(n);
         }
      }
      
      return possWords;
   } 
}