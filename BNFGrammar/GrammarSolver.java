// Krishna Puvvada
// CSE 143 AB, TA: Erika Wolfe
// 10/26/2017
/*
  Class GrammarSolver organizes Backus-Naur Form (BNF) grammar rules according to symbol.
  BNF displays grammar rules for a specific style as the following:
  
  <nonterminal symbol>::= <rule> | <rule> | <rule> | … | <rule>
  
  GrammarSolver is also able to randomly generaste expressions according to the defined 
  BNF grammar rules. The following methods are available for GrammarSolver:
  grammarContains(String symbol), generate(String symbol, int times), getSymbols(). 
*/

import java.util.*;

public class GrammarSolver {
   
   private SortedMap<String, String[]> grammarMap;
   private static final String SPLIT_RULE = "::=";
   private static final String SPLIT_VALUE = "[|]";
   private static final String SPLIT_SPACE = "[ \t]+";
   
   
   // A list of Strings containing BN formatted strings containing grammar rules must be 
   // inputted into GrammarSover and the rules are organized according to expression. If
   // the List is empty, an IllegalArgumentException will be thrown. If there are two
   // entries of rules for the same symbol, an IllegalArgumentException. This means that
   // each non terminal symbol must only have one set of rules and there can be no 
   // duplicates. 
 
   public GrammarSolver(List<String> grammar) {
      
      if (grammar.isEmpty()) {
         throw new IllegalArgumentException("List of BNF grammar rules is empty. A non-empty "
               + "List of strings must be inputted.");
      }
      
      grammarMap = new TreeMap<String, String[]>();
      
      for (String rule : grammar) {
         String symbol = rule.split(SPLIT_RULE)[0].trim();
         
         if (grammarContains(symbol)) {
            throw new IllegalArgumentException("There are duplicate symbols within List of "
                  + "BNF strings. Make sure there are no duplicate entries of rules for same "
                  + "symbol");
         }
         
         grammarMap.put(symbol, rule.split(SPLIT_RULE)[1].split(SPLIT_VALUE)); 
      }
   }
   
   
   // Pre: A string of a symbol must be inputted into grammarContains method.
   //
   // Post: If the current set of BNF set grammar rules contains the inputted terminal symbol,
   // then the method will return true, if the 
   
   public boolean grammarContains(String symbol) {
      return grammarMap.containsKey(symbol);
   }
   
   
   // Pre: A String of a non-terminal symbol, and an integer amount of times an expression 
   // following the grammar rules created, should be inputted. If the inputted symobl is not
   // currently defined with rules within the grammar, an IllegalArgumentException will be
   // thrown. If the integer amount of times an expression is generated is less than zero
   // then an IllegalArgumentException, since a negative amount of expressions cannot be 
   // generated.
   //
   // Post: An String Array of all the randomly generated epressions that follow the grammar
   // rules, is returned. THe amount of expressions generated and returned within the Array,
   // is dependant on the inputted integer value. THe expressions are randomly created 
   // by ramdomly choosing appropriate rules for the inputted non-terminal symbol.
   
   public String[] generate(String symbol, int times) {
      
      if (!grammarContains(symbol)) {
         throw new IllegalArgumentException("The current BNF grammar does not contain the " 
               + "inputted symbol, a symbol that is currently within the grammar must be "
               + "inputted.");
      }
      
      if (times < 0) {
         throw new IllegalArgumentException("Amount of times grammar is used to create "
               + "Is inputted as less than zero, amount of times expression is created "
               + "must be zero or greater.");
      }
       
      String[] sentences = new String[times];
      
      for (int i = 0; i < times; i++) {
         sentences[i] = createSentence(symbol).trim();
      }
      
      return sentences;
   }
   
   
   // getSymbols method returns a String of all the symbols that are currently organized and
   // defined within the BNF grammar rules. The symbols are listed out in square brackets,
   // alphabetically, seperated by commas. The follwing would be returned if <np>, <s> and
   // <vp> were currently nonterminal symbols within the BNF grammar. 
   //
   //    ex: “[<np>, <s>, <vp>]”
   
   public String getSymbols() {
      String symbols = "["; 
      
      for (String key : grammarMap.keySet()) {
        symbols += key + ", "; 
      }
      
      return symbols.substring(0, symbols.length() -2) + "]";
   }
   
   
   // Pre: A non-terminal symbol is inputted into the method.
   //
   // Post: A randomly generated expression as a String that follows the BNF grammar is
   // returned. Rules are appropriately ordered from random selection and sperated by 
   // single space to create the expression. 
   
   private String createSentence(String symbol) {
      String[] ruleArray = grammarMap.get(symbol);
      String[] chosenOption = ruleArray[(int)(Math.random()*ruleArray.length)].split(SPLIT_SPACE);
      String sentence = "";
      
      for (String value : chosenOption) {
            
         if (grammarMap.containsKey(value)) {
            sentence += createSentence(value);
            
         }else if (!value.equals("")) {
            sentence += value + " ";
            
         }    
      }
      
      return sentence;   
   } 
}