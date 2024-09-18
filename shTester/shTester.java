package ads.shTester;
import ads.sh.*;
import java.util.*;

public class shTester {

   public static void main(String[] args) {
   
      ArrList<String> list = new ArrList<>();
      list.add("swoggy!");
      list.add("hiya there ");
      list.add("yayyy");
      sh.print(list.get(-2));
      sh.p(list.get(-3));
      
   }
   
}