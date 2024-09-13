import java.util.*;

public class CustomArrayListTester
{
   public static void main(String[] args)
   {
      //Do your own testing here as you're working on it!
      
      //Uncomment this when you think you're done:
      runFullTestSuite();
   }
   
   /**
      Run all tests
   */
   public static void runFullTestSuite()
   {
      testAddAtIndex();
      testGrow();
      testRemoveObject();
      testRemoveInvalidObject();
      testRemoveIndex();
      testRemoveInvalidIndex();
      testSet();
      testContains();
      testIndexOf();
      testGet();
      testGetInvalid();
      testIsEmpty();
   }
   
   /**
      Creates a CustomArrayList to test on, which includes two elements.
      @return the test list
   */
   public static CustomArrayList<String> createStringList()
   {
      CustomArrayList<String> list = new CustomArrayList<>();
      list.add("Element 0");
      list.add("Element 1");
      return list;
   }
   
   /**
      Creates a CustomArrayList to test on, which includes two elements.
      @return the test list
   */
   public static CustomArrayList<Integer> createIntList()
   {
      CustomArrayList<Integer> list = new CustomArrayList<>();
      list.add(0);
      list.add(1);
      return list;
   }
   
   /**
      Tests that a given list has the expected toString and size, and 
      prints whether the test passed or failed
      @param testName the name of the test being run
      @param list the custom array list to test
      @param correctToString the expected toString() for the list
      @param correctSize the expected size() for the list
   */
   public static void validateResults(String testName, CustomArrayList list, String correctToString, int correctSize)
   {
      if (!list.toString().equals(correctToString))
      {
         System.out.println("Test FAILED: " + testName + ". Expected: " + correctToString + ", got " + list.toString());
      }
      else if (list.size() != correctSize)
      {
         System.out.println("Test FAILED: " + testName + ". Expected: " + correctSize + ", got " + list.size());
      }
      else
      {
         System.out.println("Test passed: " + testName);
      }
   }
   
   /**
      Test if adding a third element works
   */
   public static void testAddAtIndex()
   {
      CustomArrayList<String> list = createStringList();
      list.add(0, "New Element 0");
      list.add(2, "New Element 2");
      
      String correctToString = "[New Element 0, Element 0, New Element 2, Element 1]";
      int correctSize = 4;
      
      validateResults("add at index", list, correctToString, correctSize);
   }
   
   /**
      Test that array grows appropriately with significant data
   */
   public static void testGrow()
   {
      CustomArrayList<Integer> list = new CustomArrayList<>(1);
      
      String correctToString = "[";
      
      for (int i = 0; i < 100; i++)
      {
         list.add(i);
         correctToString = correctToString + i + ", ";
      }
      
      correctToString = correctToString.substring(0, correctToString.length() - 2) + "]";
      int correctSize = 100;
      
      validateResults("grow", list, correctToString, correctSize);
   }
   
   /**
      Test that you can remove by object from the list
   */
   public static void testRemoveObject()
   {
      CustomArrayList<String> list = createStringList();
      list.remove("Element 0");
      
      String correctToString = "[Element 1]";
      int correctSize = 1;
      
      validateResults("remove object", list, correctToString, correctSize);
            
      list.remove("Element 1");
      
      correctToString = "[]";
      correctSize = 0;
      
      validateResults("remove all objects", list, correctToString, correctSize);
   }
   
   /**
      Tests removing an invalid object from the list
   */
   public static void testRemoveInvalidObject()
   {
      CustomArrayList<String> list = createStringList();
      
      //remove invalid object - should return false
      boolean returnVal = list.remove("Not in the list");
      if (returnVal)
      {
         System.out.println("Test FAILED: remove invalid object. Expected return value false, but returned true.");
         return;
      }
      
      //list should be the same after removing invalid object
      String correctToString = "[Element 0, Element 1]";
      int correctSize = 2;
      validateResults("remove invalid object", list, correctToString, correctSize);
   }
   
   /**
      Tests removing an element by its index
   */
   public static void testRemoveIndex()
   {
      CustomArrayList<String> list = createStringList();
      String removed = list.remove(0);
      
      if (!removed.equals("Element 0"))
      {
         System.out.println("Test FAILED: remove by index. Expected return value Element 0, but returned " + removed);
         return;
      }
      
      String correctToString = "[Element 1]";
      int correctSize = 1;
      
      validateResults("remove by index", list, correctToString, correctSize);
   }
   
   /**
      Tests that correct exception is thrown when removing invalid index
   */
   public static void testRemoveInvalidIndex()
   {
      CustomArrayList<String> list = createStringList();
      
      //remove invalid index - should throw IndexOutOfBoundsException
      try
      {
         list.remove(5);
         System.out.println("Test FAILED: remove invalid index. Expected IndexOutOfBoundsException, got no exception.");
      }
      catch (IndexOutOfBoundsException e)
      {
         System.out.println("Test passed: remove invalid index");
      }
      catch (Exception e)
      {   
         System.out.println("Test FAILED: remove invalid index. Expected IndexOutOfBoundsException, got a different exception.");
      }
   }
   
   /**
      Test the set method
   */
   public static void testSet()
   {
      CustomArrayList<Integer> list = createIntList();
      list.set(1, 100);
      
      String correctToString = "[0, 100]";
      int correctSize = 2;
      validateResults("set method", list, correctToString, correctSize);
   }
   
   /**
      Test the contains method
   */
   public static void testContains()
   {
      CustomArrayList<String> list = createStringList();
            
      //using new String() to test == vs .equals
      if (list.contains(new String("Element 0")) && list.contains(new String("Element 1")) && !list.contains(new String("Element 2")))
      {
         System.out.println("Test passed: contains.");
      }
      else
      {
         System.out.println("Test FAILED: contains. Expected list contains Element 0 and Element 1 but not Element 2.");
      }
   }
   
   /**
      Test the indexOf method
   */
   public static void testIndexOf()
   {
      CustomArrayList<String> list = createStringList();
      int indexOfInvalid = list.indexOf(new String("Element 50"));
      if (indexOfInvalid != -1)
      {
         System.out.println("Test FAILED: indexOf invalid. Expected return value -1, but returned " + indexOfInvalid);
      }
      
      int indexOf = list.indexOf(new String("Element 1"));
      if (indexOf != 1)
      {
         System.out.println("Test FAILED: indexOf. Expected return value 1, but returned " + indexOfInvalid);
      }
      
      //indexOf should not modify the list
      String correctToString = "[Element 0, Element 1]";
      int correctSize = 2;
      
      validateResults("indexOf", list, correctToString, correctSize);
   }
   
   /**
      Test the get method
   */
   public static void testGet()
   {
      CustomArrayList<String> list = createStringList();
            
      if (list.get(0).equals("Element 0") && list.get(1).equals("Element 1"))
      {
         System.out.println("Test passed: get method basic.");
      }
      else
      {
         System.out.println("Test FAILED: get method basic. get(0) should return Element 0, but returns " + list.get(0));
      }
      
      //get should not modify the list
      String correctToString = "[Element 0, Element 1]";
      int correctSize = 2;
      
      validateResults("get method", list, correctToString, correctSize);
   }
   
   /**
      Test getting an invalid index - should throw IndexOutOfBoundsException
   */
   public static void testGetInvalid()
   {
      CustomArrayList<Integer> list = createIntList();
      
      //remove invalid index - should throw IndexOutOfBoundsException
      try
      {
         list.get(2);
         System.out.println("Test FAILED: get invalid index. Expected IndexOutOfBoundsException, got no exception.");
      }
      catch (IndexOutOfBoundsException e)
      {
         System.out.println("Test passed: get invalid index");
      }
      catch (Exception e)
      {   
         System.out.println("Test FAILED: get invalid index. Expected IndexOutOfBoundsException, got a different exception.");
      }
   }
   
   /**
      Test the isEmpty method
   */
   public static void testIsEmpty()
   {
      CustomArrayList<Integer> list = new CustomArrayList<>();
      if (!list.isEmpty())
      {
         System.out.println("Test FAILED: isEmpty. Expected new list to be empty, but wasn't.");
         return;
      }
      
      list.add(4);
      if (list.isEmpty())
      {
         System.out.println("Test FAILED: isEmpty. Expected list of size 1 to not be empty.");
         return;
      }
      
      list.remove(0);
      if (!list.isEmpty())
      {
         System.out.println("Test FAILED: isEmpty. Expected new list to be empty, but wasn't.");
      }
      else
      {
         System.out.println("Test passed: isEmpty");
      }
   }

   
}