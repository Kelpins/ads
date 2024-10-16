import java.util.*;
import sh.*;

public class hMapTester {

   public static void main (String[] args) {
      
      CustomHashMap<String, String> hm = new CustomHashMap<String, String>();
      
      hm.put("a","b");
      
      sh.p(hm.toString());
      
      hm.put("cs","d");
      
      sh.p(hm.toString());
      
      hm.put("ed","f");
      
      sh.p(hm.toString());
      
      hm.put("ga","h");
      
      sh.p(hm.toString());
      
      hm.put("ih","j");
      
      sh.p(hm.toString());
      
      hm.put("ka","l");
      
      sh.p(hm.toString());
      
      hm.put("ma","n");
      
      sh.p(hm.toString());

      hm.put("ox","p");
      
      sh.p(hm.toString());
      
   }

}