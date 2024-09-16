import java.util.*;

public class CustomStackTester {

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
      Creates a CustomStack to test on, which includes two elements.
      @return the test stack
   */
   public static CustomStack<String> createStringStack() {
   
      CustomStack<String> stack = new CustomStack<>();
      
      stack.push("Element 1");
      stack.push("Element 2");
      
      return stack;
      
   }
   
   /*
      Creates a CustomStack to test on, which includes two elements.
      @return the test stack
   */
   public static CustomStack<Integer> createIntStack() {
   
      CustomStack<Integer> stack = new CustomStack<>();
      
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
   public static void validateResults(String testName, CustomStack stack, String correctToString, int correctSize) {
   
      if (!stack.toString().equals(correctToString)) {
      
         System.out.println("Test FAILED: " + testName + ". Expected Elements: " + correctToString + ", got " + stack.toString());
         
      }
      else if (stack.size() != correctSize) {
      
         System.out.println("Test FAILED: " + testName + ". Expected Size: " + correctSize + ", got " + stack.size());
      
      }
      else {
      
         System.out.println("Test passed: " + testName);
      
      }
   }
   
   /*
      Test if empty checking works
   */
   public static void testEmpty()
   {
      CustomStack<Integer> stack = new CustomStack<>();
      
      if (!stack.empty()) {
      
         System.out.println("Test FAILED: Empty. Expected new stack to be empty, but wasn't.");
         
         return;
         
      }
      
      stack.push(4);
      
      if (stack.empty()) {
      
         System.out.println("Test FAILED: Empty. Expected stack of size 1 to not be empty.");
         
         return;
      
      }
      
      stack.pop();
      
      if (!stack.empty()) {
      
         System.out.println("Test FAILED: Empty. Expected new stack to be empty, but wasn't.");
      
      }
      else {
      
         System.out.println("Test passed: Empty");
      
      }
   }
   
   /*
      Test that you can check the top object in the stack by peeking
   */
   public static void testPeek() {
   
      CustomStack<Integer> stack = createIntStack();
      
      result = stack.peek();
      
      if (result == 2) {
      
         validateResults("peek", stack, "[1, 2]", 2);
      
      }
      else {
      
         System.out.println("Test FAILED: Peek. Expected return value 2, got " + result)
         
      
      }
      
   }
   
   /*
      Test that proper exception will raise when attempting to peek at an empty stack
   */
   public static void testPeekEmptyStack() {
      
      CustomStack<Integer> stack = new CustomStack<>();
      
      try {
      
         stack.peek();
         
         System.out.println("Test FAILED: peek empty stack. Expected EmptyStackException, got no exception.");
      
      }
      catch (EmptyStackException e) {
      
         System.out.println("Test passed: peek empty stack");
      
      }
      catch (Exception e) {
         
         System.out.println("Test FAILED: peek empty stack. Expected EmptyStackException, got a different exception.");
      
      }

   }
   
   /*
      Test that you can remove the top object by popping
   */
   public static void testPop() {
      
      CustomStack<String> stack = createStringStack();
      
      result = stack.pop();
      
      if (result.equals("Element 2") {
      
         validateResults("pop", stack, "[Element 1]", 1);
      
      }
      else {
      
         System.out.println("Test FAILED: Pop. Expected return value 2, got " + result)
      
      }
   }
   
   /*
      Test that proper exception will raise when attempting to pop from an empty stack
   */
   public static void testPopEmptyStack() {
      
      CustomStack<String> stack = new CustomStack<>();
      
      try {
      
         stack.pop();
         
         System.out.println("Test FAILED: pop empty stack. Expected EmptyStackException, got no exception.");
      
      }
      catch (EmptyStackException e) {
      
         System.out.println("Test passed: pop empty stack");
      
      }
      catch (Exception e) {
         
         System.out.println("Test FAILED: pop empty stack. Expected EmptyStackException, got a different exception.");
      
      }
      
   }
   
   /*
      Tests pushing an element to the top of the stack
   */
   public static void testPush() {
      
      CustomStack<Integer> stack = new CustomStack<>();
      
      stack.push(72);
      stack.push(81);
      
      out_val = stack.push(2);
      
      if (!out_val == 2) {
      
         System.out.println("Test FAILED: push. Expected return value 2, got return value " + out_val);
         
         return;
      
      }
      
      result = stack.peek();
      
      if (!result == 2) {
      
         System.out.println("Test FAILED: push. Expected top element 2, got top element " + result);
      
         return;
      
      }
      
      stack.pop();
      
      result = stack.peek();
      
      if (!result == 81) {
      
         System.out.println("Test FAILED: push. Expected second element 81, got second element " + result);
      
         return;
      
      }
      
      validateResults("push", stack, "[72, 81]", 2);
      
   }
   
   /*
      Test the search method
   */
   public static void testSearch() {
   
      CustomStack<String> stack = createStringStack();
      
      int invalidSearch = stack.search(new String("Element 50"));
      
      if (invalidSearch != -1) {
      
         System.out.println("Test FAILED: invalid search. Expected return value -1, but returned " + invalidSearch);
      
      }
      
      int validSearch = stack.search(new String("Element 1"));
      
      if (indexOf != 1) {
      
         System.out.println("Test FAILED: search. Expected return value 1, but returned " + validSearch);
      
      }
      
      String correctToString = "[Element 1, Element 2]";
      
      int correctSize = 2;
      
      validateResults("search", stack, correctToString, correctSize);
      
   }
      
}
