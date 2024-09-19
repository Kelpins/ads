import java.util.*;
import sh.*;

public class StkTester {

   public static void main(String[] args) {
      
      runFullTestSuite();
      
   }
   
   public static void runFullTestSuite() {
   
      testEmpty();
      testPeek();
      testPeekEmptyStack();
      testPop();
      testPopEmptyStack();
      testPush();
      testSearch();
      
   }
   
   /*
      Creates a Stack to test on, which includes two elements.
      @return the test stack
   */
   public static Stk<String> createStringStack() {
   
      Stk<String> stack = new Stk<>();
      
      stack.push("Element 1");
      stack.push("Element 2");
      
      return stack;
      
   }
   
   /*
      Creates a Stack to test on, which includes two elements.
      @return the test stack
   */
   public static Stk<Integer> createIntStack() {
   
      Stk<Integer> stack = new Stk<>();
      
      stack.add(1);
      stack.add(2);
      
      return stack;
      
   }
   
   /*
      Tests that a given stack has the expected toString and size, and 
      prints whether the test passed or failed
      @param testName the name of the test being run
      @param stack the custom stack to test
      @param correctToString the expected toString() for the stack
      @param correctSize the expected size() for the stack
   */
   public static void validateResults(String testName, Stk stack, String correctToString, int correctSize) {
   
      if (!stack.toString().equals(correctToString)) {
      
         sh.p("Test FAILED: " + testName + ". Expected Elements: " + correctToString + ", got " + stack.toString());
         
      }
      else if (stack.size() != correctSize) {
      
         sh.p("Test FAILED: " + testName + ". Expected Size: " + correctSize + ", got " + stack.size());
      
      }
      else {
      
         sh.p("Test passed: " + testName);
      
      }
   }
   
   /*
      Test if empty checking works
   */
   public static void testEmpty()
   {
      Stk<Integer> stack = new Stk<>();
      
      if (!stack.empty()) {
      
         sh.p("Test FAILED: Empty. Expected new stack to be empty, but wasn't.");
         
         return;
         
      }
      
      stack.push(4);
      
      if (stack.empty()) {
      
         sh.p("Test FAILED: Empty. Expected stack of size 1 to not be empty.");
         
         return;
      
      }
      
      stack.pop();
      
      if (!stack.empty()) {
      
         sh.p("Test FAILED: Empty. Expected new stack to be empty, but wasn't.");
      
      }
      else {
      
         sh.p("Test passed: empty");
      
      }
   }
   
   /*
      Test that you can check the top object in the stack by peeking
   */
   public static void testPeek() {
   
      Stk<Integer> stack = createIntStack();
      
      int result = stack.peek();
      
      if (result == 2) {
      
         validateResults("peek", stack, "[1, 2]", 2);
      
      }
      else {
      
         sh.p("Test FAILED: Peek. Expected return value 2, got " + result);
         
      
      }
      
   }
   
   /*
      Test that proper exception will raise when attempting to peek at an empty stack
   */
   public static void testPeekEmptyStack() {
      
      Stk<Integer> stack = new Stk<>();
      
      try {
      
         stack.peek();
         
         sh.p("Test FAILED: peek empty stack. Expected EmptyStackException, got no exception.");
      
      }
      catch (EmptyStackException e) {
      
         sh.p("Test passed: peek empty stack");
      
      }
      catch (Exception e) {
         
         sh.p("Test FAILED: peek empty stack. Expected EmptyStackException, got a different exception." + e);
      
      }

   }
   
   /*
      Test that you can remove the top object by popping
   */
   public static void testPop() {
      
      Stk<String> stack = createStringStack();
      
      String result = stack.pop();
      
      if (result.equals("Element 2")) {
      
         validateResults("pop", stack, "[Element 1]", 1);
      
      }
      else {
      
         sh.p("Test FAILED: Pop. Expected return value 2, got " + result);
      
      }
   }
   
   /*
      Test that proper exception will raise when attempting to pop from an empty stack
   */
   public static void testPopEmptyStack() {
      
      Stk<String> stack = new Stk<>();
      
      try {
      
         stack.pop();
         
         sh.p("Test FAILED: pop empty stack. Expected EmptyStackException, got no exception.");
      
      }
      catch (EmptyStackException e) {
      
         sh.p("Test passed: pop empty stack");
      
      }
      catch (Exception e) {
         
         sh.p("Test FAILED: pop empty stack. Expected EmptyStackException, got a different exception.");
      
      }
      
   }
   
   /*
      Tests pushing an element to the top of the stack
   */
   public static void testPush() {
      
      Stk<Integer> stack = new Stk<>();
      
      stack.push(72);
      stack.push(81);
      
      int out_val = stack.push(2);
      
      if (out_val != 2) {
      
         sh.p("Test FAILED: push. Expected return value 2, got return value " + out_val);
         
         return;
      
      }
      
      int result = stack.peek();
      
      if (result != 2) {
      
         sh.p("Test FAILED: push. Expected top element 2, got top element " + result);
      
         return;
      
      }
      
      stack.pop();
      
      result = stack.peek();
      
      if (result != 81) {
      
         sh.p("Test FAILED: push. Expected second element 81, got second element " + result);
      
         return;
      
      }
      
      validateResults("push", stack, "[72, 81]", 2);
      
   }
   
   /*
      Test the search method
   */
   public static void testSearch() {
   
      Stk<String> stack = createStringStack();
      
      int invalidSearch = stack.search(new String("Element 50"));
      
      if (invalidSearch != -1) {
      
         sh.p("Test FAILED: invalid search. Expected return value -1, but returned " + invalidSearch);
      
      }
      
      int validSearch = stack.search(new String("Element 2"));
      
      if (validSearch != 1) {
      
         sh.p("Test FAILED: search. Expected return value 1, but returned " + validSearch);
      
      }
      
      validSearch = stack.search(new String("Element 1"));
      
      if (validSearch != 2) {
      
         sh.p("Test FAILED: search. Expected return value 2, but returned " + validSearch);
      
      }
      
      String correctToString = "[Element 1, Element 2]";
      
      int correctSize = 2;
      
      validateResults("search", stack, correctToString, correctSize);
      
   }
      
}
