import java.util.*;

/**
* CustomStack
* Defines a CustomStack object with a generic datatype, which operates just as a Java Stack does
* @author Kellan, Applications of Data Structures
*/
public class CustomStack<E> {

   private ArrList<E> arr;
   //Not strictly necessary (same as this.peek() or arr.get(-1)), but is helpful nonetheless
   private E top;
   
   /**
   * Constructs a CustomStack object
   * O(1)
   */
   public CustomStack() {
   
      this.arr = new ArrList<E>();
      this.top = null;

   }
   
   /**
   * Gives the amount of objects in the CustomStack
   * @return the size of the ArrList (which is the amount of objects in the CustomStack)
   * O(1)
   */
   public int size() {
   
      return this.arr.size();
   
   }
   
   /**
   * Adds an element to the top of the stack
   * @param item the object to add
   * @return the item that was added
   * O(1) -> O(N)
   */
   public E push(E item) {
      
      this.top = item;
      return this.arr.add(item);
      
   }
   
   /**
   * Checks if there are no elements in the CustomStack
   * @return whether the CustomStack is empty
   * O(1)
   */
   public boolean empty() {
   
      return this.size()==0;
      
   }
   
   /**
   * Removes all elements from the CustomStack
   * O(N)
   *  This calculation is a little bit confusing since technically there is a nested loop,
   *  but when the remove(int i) function is called within ArrList given the input of this.size-1,
   *  the inside for loop will only ever run once, since its cutoff condition is i < this.size.
   *  Therefore, this function operates in O(N * N^0) time, or O(N).
   */
   public void clear() {
   
      this.arr.removeAll();
   
   }
   
   /**
   * Peeks the top element in the CustomStack
   * @throws an EmptyStackException if the CustomStack is empty
   * @return this.top, the top element of the CustomStack
   * O(1)
   */
   public E peek() {
   
      if (!(this.top == null)) {
      
         return this.top;
      
      }
      
      throw new EmptyStackException();
      
   }
   
   /**
   * Removes the top element in the CustomStack
   * @throws an EmptyStackException if the CustomStack is empty
   * @return the removed element
   * O(1)
   */
   public E pop() {
   
      if (!(this.top == null)) {
      
         E out_val = this.top;
         this.arr.remove(this.size()-1);
         
         if (this.size() == 0) {
         
            this.top = null;
         
         }
         else {
         
            this.top = this.arr.get(this.size()-1);
         
         }
         
         return out_val;
      
      }
      
      throw new EmptyStackException();
         
   }
   
   /**
   * Searches for element in the CustomStack
   * @param e the element to be searched for
   * @return element's index: 1 at the top of the CustomStack, index increasing toward the bottom
   * @return -1 if element is not in CustomStack
   * O(N)
   */
   public int search(E e) {
      
      for (int i = this.size()-1; i >= 0; i--) {
      
         if (this.arr.get(i).equals(e)) {
         
            return (this.size() - i);
         
         }
      
      }
      
      return -1;
            
   }
      
   @Override
   //O(N)
   public String toString() {
   
      String out_str = "[";
      
      if (this.size() != 0) {
      
         out_str += this.arr.get(0);
      
      }
      
      for (int i = 1; i < this.size(); i++) {
         
         out_str += ", " + this.arr.get(i);
         
      }
      
      out_str += "]";
      return out_str;
      
   }
   
}