//Krishna Puvvada
import java.io.*;
import java.util.*; 

public class PuvvadaKrishna4 {
   public static void startMenu(){
      System.out.println("GPX Processor");
      System.out.println("1) Load Data");
      System.out.println("2) Distance");
      System.out.println("3) Elevation Gain/Loss");
      System.out.println("4) Elevation Bounds");
      System.out.println("5) Speed Table");
      System.out.println();
      System.out.print("Enter Menu Selection: ");
   }
      
   public static void main(String[] args) throws FileNotFoundException {
         boolean program = true;
         System.out.println("GPX Processor");
         System.out.println("1) Load Data");
         System.out.println("E) Exit Program");
         System.out.println();
         System.out.print("Enter Menu Selection: ");
         Scanner console = new Scanner(System.in);
         String option = console.nextLine();
         while(option.charAt(0) != '1' && option.charAt(0) != 'E'){
            System.out.println("** INVALID SELECTION **");
            System.out.println("GPX Processor");
            System.out.println("1) Load Data");
            System.out.println("E) Exit Program");
            System.out.println();
            System.out.print("Enter Menu Selection: ");
            option = console.nextLine();
         }
         if (option.charAt(0) == '1'){
            System.out.print("Enter GPX filename: ");
            String name = console.nextLine();
            File f = new File(name);
            double[] elevation = new double[100];
            double[] latitude = new double[100];
            double[] longitude = new double[100];
            String[] time = new String[100];
            String[] stdTime = new String[100];
            
            while(!f.exists()) {
               System.out.println("File does not exist");
               System.out.print("Enter GPX filename: ");
               name = console.nextLine();
               f = new File(name);
            }
            Scanner input = new Scanner(f);
            String gpxFormat = input.nextLine();
            String properFormat = "<?xml version=\"1.0\"?><gpx version=\"1.1\"";
            while(!gpxFormat.startsWith(properFormat)){
               System.out.println("File is not in GPX format");
               System.out.print("Enter GPX filename: ");
               name = console.nextLine();
               f = new File(name);
               gpxFormat = input.nextLine();
            }
            
            Scanner ele = new Scanner(f);
            int index1 = 0;
            int index2 = 0;
            while(ele.hasNext()){
               String line = ele.next();
               //System.out.println(line);
               if(line.startsWith("lat")){      
                  String elev = line.substring(21, 30);
                  elevation[index1] = Double.valueOf(elev);
                  String lat = line.substring(5, 14);
                  latitude[index1] = Double.valueOf(lat);
                  time[index1] = line.substring(53, 61);
                        //System.out.println(time[index1]);
                        //System.out.println(latitude[index1]);
                        //System.out.println(elevation[index1]);
                  index1++;
               }
               if(line.startsWith("lon")){
                  String lon = line.substring(5, 16);
                  longitude[index2] = Double.valueOf(lon);
                        //System.out.println(longitude[index2]);
                  index2++;
               }
            }
            
            while (program == true){
               startMenu();
               String options = console.nextLine();
               while(options.charAt(0) != '1' && options.charAt(0) != '2' && 
               options.charAt(0) != '3' && options.charAt(0)!= '4' && options.charAt(0)!= '5'
               && options.charAt(0) != 'E'){
                  System.out.println("** INVALID SELECTION **");
                  System.out.print("Enter Menu Selection: ");
                  options = console.nextLine();
               }
               if(options.charAt(0) == '1'){
                  System.out.print("Enter GPX filename: ");
                  name = console.nextLine();
                  f = new File(name);
               }else if(options.charAt(0) == '2'){
                  double distanceSum = 0;
                  distanceSum = distance(latitude[0],longitude[0],latitude[index1-1],longitude[index1-1]);
                  //for(int i=0; i< index1 - 2; i++){
                  //   distanceSum += distance(latitude[i],longitude[i],latitude[i+1],longitude[i+1]);
                  //}
                  //distanceSum = Math.round(distanceSum*1000)/1000.0;
                  System.out.println("Total distance = " + distanceSum + " kilometers.");
               }else if(options.charAt(0) == '3'){
                  System.out.print("Elevation = ");
                  double gainSum= 0;
                  double lossSum= 0;
                  for(int i=0; i< index1 - 1; i++){
                     double elev = elevationgl(elevation[i],elevation[i+1]);
                     if(elev < 0){
                        gainSum += elev;
                     }else{
                        lossSum += elev;
                     } 
                  }
                  lossSum = Math.round(lossSum*1000)/1000.0;
                  gainSum = Math.round(gainSum*1000)/1000.0;
                  System.out.println("Gain = "+ gainSum +"  Loss = "+ lossSum +" (meters)"); 
                 
               }else if(options.charAt(0) == '4'){
                  double max = elevation[0];
                  double min = elevation[0];
                  System.out.print("Elevation = ");
                  for(int i=0; i< index1; i++){
                     if(elevation[i]> max){
                        max = elevation[i];
                     }
                     if(elevation[i]<min){
                        min = elevation[i];
                     }   
                  }
                  max = Math.round(max*1000)/1000.0;
                  min = Math.round(min*1000)/1000.0;
                  System.out.println("Max = "+max+"  Min = "+min+" (meters)"); 
               }else if(options.charAt(0) == '5'){
                  System.out.println("Speed Table = ");
                  System.out.println("   (km/h)      TIME");
                  System.out.println("==============================");
                  for(int i=0; i< index1; i++){
                     stdTime[i] = militaryToNormal(time[i]);
                  }
                  for(int i=0; i< index1-3; i=i+3 ){ 
                     int tDifference = secondsDifference(stdTime[i],stdTime[i+3] );
                     double dis = distance(latitude[i],longitude[i],latitude[i+3],longitude[i+3]);
                     double speed = dis*3600/tDifference;
                     System.out.println("   "+ Math.round(speed*1000)/1000.0 +"     "+stdTime[i+3]);
                  }
                  
                  //for(int i=0; i< index1 - 2; i++){
                  //   distanceSum += distance(latitude[i],longitude[i],latitude[i+1],longitude[i+1]);
                  //}
                  //distanceSum = Math.round(distanceSum*1000)/1000.0;   
                       
                  
               }else{
                  System.out.println("Program has ended.");
                  program = false;
            }
         }             

         } else {
            System.out.println("Program has ended.");
            program = false;           

         }

   }
   public static double distance(double firstLat, double firstLong, double secondLat, double secondLong){
         //Below changes latitude and longitude inputs which are given in degrees into radians.
            firstLat = firstLat * Math.PI/180;
            firstLong = firstLong * Math.PI/180;
            secondLat = secondLat * Math.PI/180;
            secondLong = secondLong * Math.PI/180;
            
            //Below the varibles are placed into the equation to solve for distance between two points.
            double longDiff = firstLong - secondLong; //calculates difference in longitude.
            // Below calculates anglulr difference.
            double angDiff = Math.acos(Math.sin(firstLat)*Math.sin(secondLat)
            +Math.cos(firstLat)*Math.cos(secondLat)*Math.cos(longDiff));
            double distance = 6372.795*angDiff; //By multiplying Earths radius we can find distance in miles.
            distance = Math.round(distance*1000)/1000.0; //Rounds to the nearest thousandth.
            //System.out.println("Distance between two locations is " + distance + " miles.");
            return distance;
   } 
   public static double elevationgl(double elevation1, double elevation2){
      double elevation = elevation1 - elevation2;
      return elevation;   
   }
   public static String militaryToNormal(String mltime){
      int hours = Integer.valueOf(mltime.substring(0,2));
      String sdhours = mltime.substring(0,2);
      if (hours > 12){
      hours = hours - 12;
      
      mltime = hours + mltime.substring(2,8) +"PM";
      }else{
      mltime = mltime + "AM";
      }
      //System.out.println(mltime);
      return mltime;  
   }
	public static int secondsAfterMidnight(String t) {
      int seconds; //initializing seconds, will be returned to main.
      t.toLowerCase(); //setting all input to lowercase so it is easier to handle.
      if (t.length() == 10){ //if input is 10 characters
            // if statement below checks whether each character is appropriate before continuing.
            //if((t.startsWith("0") || t.startsWith("1"))&& Character.isDigit(t.charAt(1))&& 
            //t.charAt(2) == ':' && Character.isDigit(t.charAt(3))&& Character.isDigit(t.charAt(4))&&
            //t.charAt(5) == ':' && Character.isDigit(t.charAt(6))&& Character.isDigit(t.charAt(7))&&
            //(t.endsWith("am") || t.endsWith("pm"))){
                  // Characters converted to numeric values and hours, minutes, and seconds are
                  // calculated below.
                  int hours = Character.getNumericValue(t.charAt(0))*10 
                  + Character.getNumericValue(t.charAt(1));
                        //if hours is equal to 12 such as 12:35, switched to 0:35, for easier calc.
                        if(hours == 12){ 
                              hours = 0;
                        }
                  int minutes = Character.getNumericValue(t.charAt(3))*10 
                  + Character.getNumericValue(t.charAt(4));
                  seconds = Character.getNumericValue(t.charAt(6))*10 
                  + Character.getNumericValue(t.charAt(7)); 
                  if(minutes < 60 && seconds < 60){//checks whether minute and second input is below 60.
                        if(t.endsWith("pm")){ //adding 43200 (12 hours in seconds) if time is pm.
                              seconds = hours*3600 + minutes*60 + seconds + 43200;
                        }else{
                              seconds = hours*3600 + minutes*60 + seconds;    
                        }
                  }else{
                        seconds = -1; //if proper input is not given, seconds set to -1.
                  }
            //} else {
                 // seconds = -1; //improper input leads to seconds set to -1.
            //}
      //below is for input that has 9 characters, such as 9:12:23am, instead of 09:12:23am. The code below
      // is very similar to to code for 10 characters above.      
      }else if (t.length() == 9){
            //if(Character.isDigit(t.charAt(0))&& t.charAt(1) == ':' && Character.isDigit(t.charAt(2))
            //&& Character.isDigit(t.charAt(3))&& t.charAt(4) == ':' && Character.isDigit(t.charAt(5))
            //&& Character.isDigit(t.charAt(6))&&(t.endsWith("am") || t.endsWith("pm"))){
                  int hours = Character.getNumericValue(t.charAt(0));
                  int minutes = Character.getNumericValue(t.charAt(2))*10 
                  + Character.getNumericValue(t.charAt(3));
                  seconds = Character.getNumericValue(t.charAt(5))*10 
                  + Character.getNumericValue(t.charAt(6)); 
                  if(minutes < 60 && seconds < 60){
                        if(t.endsWith("pm")){
                              seconds = hours*3600 + minutes*60 + seconds + 43200;
                        }else{
                              seconds = hours*3600 + minutes*60 + seconds;    
                        }
                  }else{
                        seconds = -1;
                        System.out.println("M");
                  }
            //} else {
                  //seconds = -1;
                  //System.out.println("J");
            //}
      }else{ // if input has neither 10 or 9 characters, input not proper.
            seconds = -1;
            System.out.println("K");
      }
      //System.out.println(seconds);
      return seconds; //seconds is returned to main.   
   }
	
   public static int secondsDifference(String start, String end) {
      int difference; //difference is initialized,and will be returned at the end.
      //firstsec is the amount of seconds past midnight in the start time, and
      //secondsec is the amount of seconds past midnight in the end time. In order to
      //calculate this, secondsAfterMidnight() method was called for start and end.
      int firstsec = secondsAfterMidnight(start); 
      int secondsec = secondsAfterMidnight(end);
      //If start or end returns -1 through secondsAfterMidnight(), then the input was 
      //not proper, so difference is set to -99999.
      if (firstsec == -1 || secondsec == -1){
            difference = -99999;
      }else { //if both inputs are proper, then proceed to calculating difference.
            difference = secondsec - firstsec;
      }
      //System.out.println(difference);
      return difference; //difference is returned to main.      
   }   
       
}   
   

   