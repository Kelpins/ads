import java.io.*;
import java.util.*;
import sh.*;

public class SpeedTester {
   
   public static void main(String[] args) {
   
      Scanner s;
      try {
         
         s = new Scanner(new File("students.txt"));
      
      }
      catch (FileNotFoundException e) {
      
         System.out.println("File not found!");
         return; //exit out of main
         
      }
      
      Set<String> hset = new HashSet<>();
      ArrList<String> arr = new ArrList<>();
      ArrayList<String> arrlist = new ArrayList<>();
      
      while (s.hasNextLine()) {
         String student = s.nextLine();
         hset.add(student);
         arr.add(student);
         arrlist.add(student);
      }
      
      long before = System.nanoTime();
      hset.contains("Yael Norton");
      long hSetEnd = System.nanoTime();
      arr.contains("Yael Norton");
      long arrEnd = System.nanoTime();
      arrlist.contains("Yael Norton");
      long arrlistEnd = System.nanoTime();
      
      sh.p("HashSet: " + (hSetEnd - before));
      sh.p("ArrList: " + (arrEnd - hSetEnd));
      sh.p("ArrayList: " + (arrlistEnd - arrEnd));
      
      
      /*
         TO DO:
         1. Create two empty data structures: a HashSet<String> and an ArrayList<String>.
         2. Loop through the contents of the file "students.txt" (Scanner defined above), which contains
               the names of 100,000 students, each name on its own line.
               Put each student's name into your hashset.
               Also put each student into your ArrayList.
         3.  Using the function System.nanoTime() to record your starting and ending times, we will now figure out
               how long it's taking (in nanoseconds) to find students in each of our data structures. 
               Use the contains() method of your HashSet to check if it has student "Yael Norton" in it. 
                     Print how many nanoseconds it took.
               Use the contains() method of your ArrayList to check if it has student "Yael Norton" in it. 
                     Print out how many nanoseconds it took.
         4.  Note the difference in nanoseconds - the HashSet was orders of magnitude faster, as expected.
         5.  Instead of searching for "Yael Norton", switch both data structures to search for "Yasmine King".
               Note how many nanoseconds it took this time. 
               THINK: Why is it different for Yasmine than Yael?
         6.  In addition the the HashSet and ArrayList, also test the timing for your
                  CustomArrayList.java. Is the timing similar to Java's ArrayList? Identical? Why or why not?
                  
                  
         7.  Optional challenge: modify your CustomArrayList.java to make its contains() method significantly faster
                  than Java's built-in version by using a Binary Search algorithm. 
                  HINT: You may want to make your CustomArrayList only allow Comparable objects, so you can use compareTo as-needed:
                        public class CustomArrayList<E extends Comparable<E>>
                        To initialize: (E[])new Comparable[someLength];
                  THINK: Which method(s) in your CustomArrayList need to become slower to allow your contains method
                           to be faster?
                  TEST: With your modified, faster version of contains, is your CustomArrayList search now 
                           faster, slower, or similar to a HashSet search? Why?
                  THINK: What is the Big O of each of the following:
                           ArrayList add
                           CustomArrayList add
                           HashSet add
                           ArrayList contains
                           CustomArrayList contains
                           HashSet contains
       */
}
}