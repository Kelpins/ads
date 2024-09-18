package ads.sh;
import java.util.*;

public class sh {

   public static void p(String p) {
   
      System.out.println(p);
   
   }
   
   public static void print(String p) {
   
      System.out.print(p);
   
   }
   
   public static void p(int p) {
   
      System.out.println(p);
   
   }
   
   public static void print(int p) {
   
      System.out.print(p);
   
   }
   
   public static void p(double p) {
   
      System.out.println(p);
   
   }
   
   public static void print(double p) {
   
      System.out.print(p);
   
   }
   
   public static Stack cns (int i) {
   
      return new Stack<Integer>();
   
   }
   
   public static Stack cns (String i) {
   
      return new Stack<String>();
   
   }
   
   public static Stack cns (double i) {
   
      return new Stack<Double>();
   
   }
   
   public static ArrList cnal (int i) {
   
      return new ArrList<Integer>();
   
   }
   
   public static ArrList cnal (String i) {
   
      return new ArrList<String>();
   
   }
   
   public static ArrList cnal (double i) {
   
      return new ArrList<Double>();
   
   }
   
   public static Stack arr_stk (ArrList arr, int a) {
   
      Stack<Integer> stk = new Stack<>();
   
      for (int i = 1; i <= arr.size(); i++) {
      
         stk.push((int)arr.get(-1 * i));
      
      }
      
      return stk;
   
   }
   
   public static Stack arr_stk (ArrList arr, String a) {
   
      Stack<String> stk = new Stack<>();
   
      for (int i = 1; i <= arr.size(); i++) {
      
         stk.push("" + arr.get(-1 * i));
      
      }
      
      return stk;
   
   }
   
   public static Stack arr_stk (ArrList arr, double a) {
   
      Stack<Double> stk = new Stack<>();
   
      for (int i = 1; i <= arr.size(); i++) {
      
         stk.push(((int)arr.get(-1 * i) * 1.0));
      
      }
      
      return stk;
   
   }
   
   public static ArrList stk_arr (Stack stk, int a) {
   
      ArrList<Integer> temp_list = new ArrList<>();   
      ArrList<Integer> list = new ArrList<>();
   
      for (int i = 0; i < stk.size(); i++) {
      
         temp_list.add((int)stk.pop());
      
      }
      
      for (int i = 1; i <= temp_list.size(); i++) {
      
         list.add((int)temp_list.get(-i));
      
      }
      
      return list;
   
   }
   
   public static ArrList stk_arr (Stack stk, String a) {
   
      ArrList<String> temp_list = new ArrList<>();   
      ArrList<String> list = new ArrList<>();
   
      for (int i = 0; i < stk.size(); i++) {
      
         temp_list.add("" + stk.pop());
      
      }
      
      for (int i = 1; i <= temp_list.size(); i++) {
      
         list.add("" + temp_list.get(-i));
      
      }
      
      return list;
   
   }
   
   public static ArrList stk_arr (Stack stk, double a) {
   
      ArrList<Double> temp_list = new ArrList<>();   
      ArrList<Double> list = new ArrList<>();
   
      for (int i = 0; i < stk.size(); i++) {
      
         temp_list.add((double)stk.pop());
      
      }
      
      for (int i = 1; i <= temp_list.size(); i++) {
      
         list.add((double)temp_list.get(-i));
      
      }
      
      return list;
   
   }
   
   public static int cast (String s) {return Integer.parseInt(s);}
   public static double doub (String s) {return (double)cast(s);}   
   public static String str (int i) {return ("" + i);}   
   public static String str (double d) {return ("" + d);}
   
}