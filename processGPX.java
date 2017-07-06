//Krishna Puvvada


import java.io.*;
import java.util.*; 

public class processGPX {
   public static void main(String[] args) throws FileNotFoundException {
      Scanner console = new Scanner(System.in);
      System.out.print("What is the the name of your file?");
      String name = console.nextLine();
      
      File f = new File("\"" + name + "\"");
      
      if(!f.exists()) {
         System.out.println("file does not exist");
      } else {
         System.out.println("file exists");
      }
      Scanner input = new Scanner(f);
      
      if (f.substring(0, 39) == "<?xml version=\"1.0\"?><gpx version=\"1.1\""){
         System.out.println("File is in proper GPX format");
      } else {
         System.out.println("File is not in proper GPX format");
      }
      
   }
}