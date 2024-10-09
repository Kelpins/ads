import java.util.*;
import java.io.*;

public class CourseDatabase {
   
   /**
   * Creates a console database that stores student name & course load data.
   * Uses a CustomHashMap as the underlying data structure.
   * @param args console input
   */
   public static void main(String[] args) {
      
      CustomHashMap<String, Integer> data = new CustomHashMap<>();
      File courseInfo = new File("courseInfo.txt");
      
      try {
      
         Scanner scan = new Scanner(courseInfo);
         
         // Adds all courseInfo to the database
         while (scan.hasNextLine()) {
            
            String name = scan.nextLine();
            String classNum = scan.nextLine();
            
            data.put(name, Integer.valueOf(classNum));
                     
         }
         
         // Infinite input loop         
         while (true) {
         
            System.out.print("What would you like to do: add, update, get, or quit? ");
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
                        
            if (input.equals("quit")) {
            
               break;
            
            }
            else if (!(input.equals("add") || input.equals ("update") || input.equals("get"))) {
            
               System.out.println("Invalid option, please try again.");
               continue;
            
            }
            
            System.out.print("Name of student: ");
            Scanner n = new Scanner(System.in);
            String name = n.nextLine();
            
            // Most of these conditionals are pretty self explanatory
            if (input.equals("add")) {
               
               // Checks if the student isn't yet in the database
               if (data.get(name) == null) {
                  
                  System.out.print("Number of courses for " + name + ": ");
                  Scanner cl = new Scanner(System.in);
                  int courseLoad = cl.nextInt();
                  
                  data.put(name, courseLoad);
                  
                  System.out.println("Student successfully added.");
               
               }
               else {
               
                  System.out.println("Error: student already added!");
               
               }
            
            }
            else if (input.equals("update")) {
               
               // This time we check to make sure the student does exist in the database
               if (data.get(name) != null) {
                  
                  System.out.print("New number of courses for " + name + ": ");
                  Scanner cl = new Scanner(System.in);
                  int courseLoad = cl.nextInt();
                  
                  if (courseLoad == data.get(name)) {
                  
                     System.out.println("Warning: update did not change courseload.");
                  
                  }
                  
                  /*
                  * Because we built the put function to overwrite entries with the same key,
                  * we don't have to worry about any issues here and can just use
                  * the put function we created as a sort of 'update' function
                  */
                  data.put(name, courseLoad);
                  
                  System.out.println("Student successfully updated.");
               
               }
               else {
               
                  System.out.println("Student is not in the database.");
               
               }
               
            }
            else if (input.equals("get")) {
            
               if (data.get(name) != null) {
                                    
                  System.out.println(name + " is taking " + data.get(name) + " courses.");
               
               }
               else {
               
                  System.out.println("Student is not in the database.");
               
               }
               
            }
         
         }
         
      }
      catch (FileNotFoundException e) {
      
         System.out.println("Kellan forgot to include courseInfo.txt :(");
         System.out.println("Except he would never do that...");
         System.out.println("So unfortunately this code is unreachable");
      
      }
   
   }

}