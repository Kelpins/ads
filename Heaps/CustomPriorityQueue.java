import java.util.*;
import sh.*;

/**
   A priority queue stored as a max heap, a tree whose main property is that any 
   given node is larger than all of its child nodes.
*/
public class CustomPriorityQueue
{
   private ArrayList<Integer> arr;
   
   /**
      Constructs an empty PQ
   */
   public CustomPriorityQueue()
   {
      this.arr = new ArrayList();
   }
   
   /**
      Returns the inner array, for testing / visualizing
      @return the inner array
   */
   public ArrayList getArray()
   {
      return this.arr;
   }   
 
   /**
      Swaps the values at two indexes
      
      @param pos1 the first position
      @param pos2 the second position
   */
   private void swap(int pos1, int pos2)
   {
        Integer tmp = this.arr.get(pos1);
        this.arr.set(pos1, this.arr.get(pos2));
        this.arr.set(pos2, tmp);
   }
   
      
   /**
      For a given index in the array, returns the index of that node's
      parent
      @param pos the position in the array
      @return the parent index of that position
   */
   private int parent(int pos) 
   { 
   
       if (pos == 0) {
       
          return -1;
       
       }
       
       return (pos-1)/2;
       
   }
 
   /**
      For a given index in the array, returns the index of that node's
      left child
      @param pos the position in the array
      @return the left child index of that position
   */
   private int leftChild(int pos) 
   {
   
      return pos*2+1;       
   
   }
 
   /**
      For a given index in the array, returns the index of that node's
      right child
      @param pos the position in the array
      @return the right child index of that position
   */
   private int rightChild(int pos)
   {
   
      return pos*2+2;
       
   }

   /**
      Adds a value to this heap
      @param data the value to add
   */
   public void add(Integer data)
   {  
   
      int cur_pos = this.arr.size();
      this.arr.add(data);
      
      while (this.parent(cur_pos) != -1 && this.arr.get(cur_pos) > this.arr.get(this.parent(cur_pos))) {
      
         this.swap(cur_pos, (cur_pos-1) / 2);
         cur_pos = (cur_pos-1) / 2;
         
      }
      
   }
   
   /**
      Removes and returns the maximum value from our Priority Queue
      
      @return the max value in the PQ
   */
   public Integer remove()
   {
      
      this.swap(0, this.arr.size()-1);
   
      int cur_pos = 0;
      int lc = this.arr.get(this.leftChild(cur_pos));
      int rc = this.arr.get(this.rightChild(cur_pos));
      
      while (this.arr.get(cur_pos) < lc || this.arr.get(cur_pos) < rc) {
      
         if ((lc > rc) && this.leftChild(cur_pos) < this.arr.size()-1) {
         
            this.swap(cur_pos, this.leftChild(cur_pos));
            cur_pos = this.leftChild(cur_pos);
         
         }
         else if (this.rightChild(cur_pos) < this.arr.size()-1) {
            
            sh.p(cur_pos);
            sh.p(this.rightChild(cur_pos));
            this.swap(cur_pos, this.rightChild(cur_pos));
            cur_pos = this.rightChild(cur_pos);
         
         }
         else {
         
            return this.arr.remove(this.arr.size()-1);
         
         }
               
      }
      
      return this.arr.remove(this.arr.size()-1);
   }
   
   /**
      Peeks at the top of the Priority Queue without removing
      @return the top of the PQ
   */
   public Integer peek()
   {
      return this.arr.get(0);
   }
   
   
   @Override
   public String toString()
   {
      return this.arr.toString();
   }
   
   public static void main (String[] args) {
   
      CustomPriorityQueue cpq = new CustomPriorityQueue();
      cpq.add(10);
      cpq.add(5);
      cpq.add(3);
      cpq.add(7);
      cpq.add(2);
      cpq.add(8);
      cpq.add(5);
      cpq.add(1);
      cpq.add(81);
      sh.p(cpq.remove());
      HeapVisualizer.visualizeHeap(cpq);
   
   }
   
}