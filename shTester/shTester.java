package ads.shTester;
import ads.sh.*;
import java.util.*;

public class shTester {

   public static void main(String[] args) {
   
      ArrList<Integer> list = sh.cnal(3);
      list.add(18);
      list.add(27);
      sh.print(list.get(-1));
      sh.p(list.get(-2));
      
      Stack<String> stk = sh.arr_stk(list, 1);
      sh.p(stk.toString());
      
   }
   
}