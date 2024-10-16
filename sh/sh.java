package sh;

import java.util.*;

public class sh {

   public static void p(int p) { System.out.println(p); }
   public static void p(String p) { System.out.println(p); }
   public static void p(double p) { System.out.println(p); }
   public static void p(boolean p) { System.out.println(p); }
   
   public static void print(int p) { System.out.print(p); }
   public static void print(String p) { System.out.print(p); }
   public static void print(double p) { System.out.print(p); }
   public static void print(boolean p) { System.out.print(p); }
   
   public static Stk cns (int i) { return new Stk<Integer>(); }
   public static Stk cns (String i) { return new Stk<String>(); }
   public static Stk cns (double i) { return new Stk<Double>(); }
   
   public static ArrList cnal (int i) { return new ArrList<Integer>(); }
   public static ArrList cnal (String i) { return new ArrList<String>(); }
   public static ArrList cnal (double i) { return new ArrList<Double>(); }
   
   public static Stk arr_stk (ArrList arr, int a) {
      Stk<Integer> stk = new Stk<>();
      for (int i = 1; i <= arr.size(); i++) { stk.push((int)arr.get(-1 * i)); }
      return stk;
   }
   public static Stk arr_stk (ArrList arr, String a) {
      Stk<String> stk = new Stk<>();
      for (int i = 1; i <= arr.size(); i++) { stk.push("" + arr.get(-1 * i)); }
      return stk;
   }
   public static Stk arr_stk (ArrList arr, double a) {
      Stk<Double> stk = new Stk<>();
      for (int i = 1; i <= arr.size(); i++) { stk.push(((int)arr.get(-1 * i) * 1.0)); }
      return stk;
   }
   
   public static ArrList stk_arr (Stk stk, int a) {
      ArrList<Integer> temp_list = new ArrList<>();   
      ArrList<Integer> list = new ArrList<>();
      for (int i = 0; i < stk.size(); i++) { temp_list.add((int)stk.pop()); }
      for (int i = 1; i <= temp_list.size(); i++) { list.add((int)temp_list.get(-i)); }
      return list;
   }
   public static ArrList stk_arr (Stk stk, String a) {
      ArrList<String> temp_list = new ArrList<>();   
      ArrList<String> list = new ArrList<>();
      for (int i = 0; i < stk.size(); i++) { temp_list.add("" + stk.pop()); }
      for (int i = 1; i <= temp_list.size(); i++) { list.add("" + temp_list.get(-i)); }
      return list;
   }
   public static ArrList stk_arr (Stk stk, double a) {
      ArrList<Double> temp_list = new ArrList<>();   
      ArrList<Double> list = new ArrList<>();
      for (int i = 0; i < stk.size(); i++) { temp_list.add((double)stk.pop()); }
      for (int i = 1; i <= temp_list.size(); i++) { list.add((double)temp_list.get(-i)); }
      return list;
   }
   
   public static int cast (String s) { return Integer.parseInt(s); }
   public static double doub (String s) { return (double)cast(s); }   
   public static String str (int i) { return ("" + i); }   
   public static String str (double d) { return ("" + d); }
   
}