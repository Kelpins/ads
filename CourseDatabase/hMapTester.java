import java.util.*;
import sh.*;

public class hMapTester {

   public static void main (String[] args) {
      
      CustomHashMap<String, String> food = new CustomHashMap<String, String>();
      
      sh.p(food.toString());
      
      food.put("Amanda", "French");
            
      food.put("Michele", "Chinese");
      
      food.put("John", "Spanish");
      
      sh.p(food.toString());
      
   }

}