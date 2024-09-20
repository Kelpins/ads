import java.util.*;

public class CustomStack<E> {

   private ArrList<E> arr;
   private E top;
   
   //O(1)
   public CustomStack() {
   
      this.arr = new ArrList<E>();
      this.top = null;

   }
   
   //O(1)
   public int size() {
   
      return this.arr.size();
   
   }
   
   //O(1) -> O(N)
   public E push(E item) {
      
      this.top = item;
      return this.arr.add(item);
      
   }
   
   //O(1)
   public boolean empty() {
   
      return this.size()==0;
      
   }
   
   public void clear() {
   
      this.arr.removeAll();
   
   }
   
   //O(1)
   public E peek() {
   
      if (!(this.top == null)) {
      
         return this.top;
      
      }
      
      throw new EmptyStackException();
      
   }
   
   //O(1)
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
   
   //O(N)
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