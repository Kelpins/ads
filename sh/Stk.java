package ads.CustomStack;
import ads.sh.*;
import java.util.*;

public class Stk<E>
{

   private E[] arr;
   private E top;
   private int size;
   
   //O(1)
   public Stk(int initialCapacity) {
   
      if (initialCapacity <= 0) {
      
         throw new EmptyStackException();
      
      }
      
      this.arr = (E[])new Object[initialCapacity];
      this.size = 0;
      this.top = null;
      
   }
   
   //O(1)
   public Stk() {
   
      this.arr = (E[])new Object[10];
      this.size = 0;
      this.top = null;

   }
   
   //O(N)
   private void grow() {
      
      E[] temp_arr = this.arr;
      this.arr = (E[])new Object[this.arr.length*2];
      
      for (int i = 0; i < this.size; i++) {
      
         this.arr[i] = temp_arr[i];
      
      }
      
   }
   
   //O(1)
   public int size() {
   
      return this.size;
   
   }
   
   //O(1) -> O(N)
   public E push(E e) {
      
      if (this.size == this.arr.length) {
      
         this.grow();
      
      }
      
      this.top = e;
      this.arr[size] = e;
      this.size++;
      
      return e;
      
   }
   
   //O(1) -> O(N)
   public E add(E e) {
   
      if (this.size == this.arr.length) {
      
         this.grow();
      
      }
      
      this.top = e;
      this.arr[size] = e;
      this.size++;
      
      return e;
         
   }
   
   //O(1)
   public boolean empty() {
   
      return this.size==0;
      
   }

   //O(1)
   public boolean isEmpty() {
   
      return this.size==0;
      
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
         this.arr[this.size-1] = null;
         this.size--;
         
         if (this.size == 0) {
         
            this.top = null;
         
         }
         else {
         
            this.top = this.arr[this.size-1];
         
         }
         
         return out_val;
      
      }
      
      throw new EmptyStackException();
         
   }
   
   //O(N)
   public int search(E e) {
      
      for (int i = this.size-1; i >= 0; i--) {
      
         if (this.arr[i].equals(e)) {
         
            return (this.size - i);
         
         }
      
      }
      return -1;
            
   }
      
   @Override
   //O(N)
   public String toString() {
   
      String out_str = "[";
      
      if (this.size != 0) {
      
         out_str += this.arr[0];
      
      }
      
      for (int i = 1; i < this.size; i++) {
         
         out_str += ", " + this.arr[i];
         
      }
      
      out_str += "]";
      return out_str;
      
   }
   
}