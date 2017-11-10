// Krishna Puvvada
// CSE 143 AB, TA: Erika Wolfe
// 10/19/2017
/*
  Class AssassinManager is used to manage a game of Assassin. Assassin is a 
  game where, each player is a target and has a target to "kill" and once
  killed a player is in the graveyard, and player's killer gets the player's
  target. The game continues until there is only one player standing, who is
  the winner. This class can setup a game of Assassin, and keeps track of each
  player's  target, as well as all the players that have died and who they were
  killed by. The following methods are available for AssassinManager: 
  printKillRing(), printGraveyard(), killRingContains(String name), 
  graveyardContains(String name), gameOver(), winner(), kill(String name). 
*/

import java.util.*;

public class AssassinManager {

   private AssassinNode alive;
   private AssassinNode dead;
   private int deadCount;


   // If a List interface object of player names is inputted, Assassin Manager
   // sets up the game by alocating each player with a target. The inputted List
   // object must not be empty, otherwise an IllegalArgumentException will be thrown.  
    
   public AssassinManager(List<String> names){
     
      if (names.size() < 1){
         throw new IllegalArgumentException("The inputted list is empty. Please provide"
                                            + " a list with one or more names");
      }
      
      if (names.size() >= 1){
         dead = null;
         alive = new AssassinNode(names.get(0));
         alive.next = alive;
         deadCount = 0;
         AssassinNode alivePointer = alive;
     
         for (int i = 1; i < names.size(); i++){
            alivePointer.next = new AssassinNode(names.get(i), alive);
            alivePointer = alivePointer.next;
     
         }
      }      
   }
   
   
   // printKillRing prints out the players currently active in the game and their targets.
   // The players are printed out in the format of "player1 is stalking player2."
   // "player1 is stalking player1" is printed if there is only one player left alive,
   // who is thus the winner. 
   
   public void printKillRing(){
      AssassinNode alivePointer = alive;
         
         while (alivePointer.next != alive){
            System.out.println("    " + alivePointer.name + " is stalking " 
                               + alivePointer.next.name);
            
            alivePointer = alivePointer.next;
         }
         
         System.out.println ("    " + alivePointer.name + " is stalking "
                             + alive.name);
   }
   
   
   // printGraveyard prints out all the players that have died in the current game session,
   // and who they were killed by in the format of "player1 was killed by player2."Nothing
   // is printed if the graveyard is empty.
   
   public void printGraveyard(){
      
      if (dead != null){
         AssassinNode deadPointer = dead;
         
         for (int i = deadCount - 1; i >= 0; i--){
            
            for (int j = 0; j < i; j++){
               deadPointer = deadPointer.next;
            }
            
            System.out.println("    " + deadPointer.name + " was killed by " 
                               + deadPointer.killer);
            
            deadPointer = dead;
         } 
      }
   }
   
   
   // Pre: A string of a player name must be inputed into the method. Only
   // one player name should be inputted (not case sensitive).
   // 
   //Post: If the player is currently active in the game, the method returns true.
   // If the player is currently dead, or if the name inputted is not a current player
   // the method returns false.
   
   public boolean killRingContains(String name){
      AssassinNode alivePointer = alive;
      
      while (alivePointer.next != alive){
         
         if (alivePointer.name.toLowerCase().equals(name.toLowerCase())){
            return true;
         }
         
         alivePointer = alivePointer.next;
      }
      
      return alivePointer.name.toLowerCase().equals(name.toLowerCase());
   }
   
  
   // Pre: A string of a player name must be inputed into the method. Only
   // one player name should be inputted (not case sensitive).
   //
   // Post: If the player is currently dead in the game, the method returns true.
   // If the player is currently active and alive, or if the name inputted is not a 
   // current player the method returns false. 
   
   public boolean graveyardContains(String name){
      AssassinNode deadPointer = dead;
      
      while (deadPointer != null){
         
         if (deadPointer.name.toLowerCase().equals(name.toLowerCase())){
            return true;
         }
         
         deadPointer = deadPointer.next;
      }
      
      return false;
   }
   
   
   // gameOver method will return true if the game is over and there is only one player
   // currently active in the game. If the game is not over and there are multiple players
   // alive, the method will return false.
   
   public boolean gameOver(){
      return alive.next == alive;
   }
   
   
   // If the game is over and there is only one player currently alive, the method will return
   // the name of the winning player. If the game is not currently over, and there are multiple
   // players alive the method will return null.
   
   public String winner(){
      
      if (gameOver()){
         return alive.name;
      
      } else{
         return null;
      }
   }
   
   
   // Pre: A valid player name that is currently alive in the game must be inputted into the
   // method. If the player is not currently alive or player name that is not currently
   // in the game is inputted, then an IllegalArgumentException is thrown since they cannot
   // be killed and moved to the graveyard.If the game is currently over, and there is only
   // one player alive, then an IllegalStateException is thrown since the winner cannot be 
   // killed.
   //
   // Post: The inputted player name will be killed and will be removed from the active game
   // and moved to the graveyard, the player in the graveyard will be tagged with who he was
   // killed by.
   
   public void kill(String name){
      
      if (gameOver()){
         throw new IllegalStateException("The current game of Assassin is over with a winner."
                                         + " Please begin a new game to continue playing");
      }
      
      if (!killRingContains(name)){
         throw new IllegalArgumentException("The name provided is not currently" 
                                            + " within the game. Make sure to input a" 
                                            + " valid player that is still alive");
      }
      
      if (killRingContains(name)){
         deadCount++;
         AssassinNode alivePointer = alive;
         AssassinNode deadPointer = dead;
         
         if (alive.name.toLowerCase().equals(name.toLowerCase())){
            
            while (alivePointer.next != alive){
               alivePointer = alivePointer.next;
            } 
            
            if (deadPointer == null){
               dead = new AssassinNode(alive.name);
               dead.killer = alivePointer.name;
            
            } else{
               
               while(deadPointer.next != null){
                  deadPointer = deadPointer.next;
               }
               
               deadPointer.next = new AssassinNode(alive.name);
               deadPointer.next.killer = alivePointer.name;
            }
            alive = alive.next;
            alivePointer.next = alive;
         
         }else{
            
            while (!alivePointer.next.name.toLowerCase().equals(name.toLowerCase())){
            alivePointer = alivePointer.next;
            }
            
            if (deadPointer == null){
               dead = new AssassinNode(alivePointer.next.name);
               dead.killer = alivePointer.name; 
            
            } else{
               
               while (deadPointer.next != null){
                  deadPointer = deadPointer.next;
               }
               
               deadPointer.next = new AssassinNode(alivePointer.next.name);
               deadPointer.next.killer = alivePointer.name;
            }
            
            alivePointer.next = alivePointer.next.next;
         }
      }
   }
}