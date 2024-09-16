package ads.CustomStack;
import ads.sh.*;

public class CustomStack<E>
{
   //Instance variables
   private E[] arr;
   private int size; //how many elements are in this ArrayList; NOT the same as the array length
   
   /**
      Initializes the array with default size 10
   */
   //O(1)
   public CustomStack() {
   
      this.size = 0;
      this.arr = (E[])new Object[10];

   }
   
   /**
      Initializes the array with an initial capacity
      @param initialCapacity the initial length of the internal array
      @throws IllegalArgumentException if initialCapacity is less than zero
   */
   //O(1)
   public CustomStack(int initialCapacity) {
   
      if (initialCapacity <= 0) {
      
         throw new IllegalArgumentException();
      
      }
      
      this.arr = (E[])new Object[initialCapacity];
      
   }
   
   public int size() {
   
      return 0;
   
   }
   
   /**
      Doubles the capacity of the internal array.
      After growing, the last half (at least) of the internal array
      is comprised of null values.
   */
   //O(N)
   public int push(E e) {
      
      return 0;
      
   }
   
   /**
      Adds an element to the end of the ArrayList.
      @param element the element to add
      @throws IllegalArgumentException if element is null
   */
   //O(1) -> O(N)
   public void add(E e) {
   
      
      
   }
   
   /**
      Adds an element to a certain index in the ArrayList.
      For example, if the array was [6,7,8] and we add 
      the number 99 at index 1, it would now be [6,99,7,8]
      @param index the index to add at. 
      @param element the element to add
      @throws IllegalArgumentException if element is null
   */
   //O(N)
   public boolean empty() {
   
      return false;
      
   }
   
   /**
      Remove the object o from the ArrayList.
      If there are two objects equal to o, only the first
      one is removed.
      If your internal array is [1,2,3,null] and you remove 2,
         the resulting internal array should be [1,3,null,null].
      @param object the object to remove
      @return true if o was successfully removed; 
               false if o was not in the ArrayList
   */
   //O(N)
   public E peek() {
   
      return null;
      
   }
   
   /**
      Remove the object at a certain index from the ArrayList
      @param index the index of the object to remove
      @return the removed object
      @throws IndexOutOfBoundsException if the index is out of bounds
   */
   //O(N)
   public E pop() {
   
      return null;
      
   }
   
   /**
      Update the element at a certain index to be a new element.
      @param index the index to update
      @param element the new element
      @return the element that was previously at that index
   */
   //O(1)
   public int search(E e) {
      
      return -1;
            
   }
      
   @Override
   /**
      Returns a string in the format:
         [1, 3, 5]
      @return formatted ArrayList string
   */
   //O(N)
   public String toString() {
   
      return "";
      
   }
   
}