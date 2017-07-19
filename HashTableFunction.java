import java.util.Arrays;


public class HashTableFunction{
   String[] mainArray;
   int size;
   int itemsStored;
   
   HashTableFunction(int size){
      this.size = size;
      mainArray = new String[size];
      Arrays.fill(mainArray, "null");
   }
   public void addFunction(String[]toBeAdded, String[] mainArray){
      for(int i = 0; i < toBeAdded.length; i++){
         String value = toBeAdded[i];
         int index = Function(value); 
         System.out.println("Index = " + index + " for input " + value);
         while(mainArray[index] != "null"){
            System.out.println("COLLISION");
            index++;
            System.out.println("Try " + index);
            index %= size;
         }
         mainArray[index] = value;
      }
   }
   public String retrieveKey(String key){
      int index = Function(key);
      while (mainArray[index] != "null"){
         if(mainArray[index] == key){
            System.out.println("The key " + key + " was found at index of " + index);
            return mainArray[index]; 
         }
         index++;
         index %= size;
      }
      return "null";
   }
   public int Function(String value){
      int index = Integer.parseInt(value)%29;
      return index; 
   }
   public static void main(String[] args){
      HashTableFunction Function1 = new HashTableFunction(30);
      String[] values = {"123", "21", "235", "654", "954", "5", "24", "29","54", "47",
                         "97", "3476", "239", "934", "34"};
      Function1.addFunction(values, Function1.mainArray);
      Function1.retrieveKey("3476");
   }
}