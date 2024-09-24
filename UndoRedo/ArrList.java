public class ArrList<E> {

   //Instance variables
   private E[] arr;
   private int size; //how many elements are in this ArrayList; NOT the same as the array length
   
   /**
      Initializes the array with default size 10
   */
   //O(1)
   public ArrList() {
   
      this.size = 0;
      this.arr = (E[])new Object[10];

   }
   
   /**
      Initializes the array with an initial capacity
      @param initialCapacity the initial length of the internal array
      @throws IllegalArgumentException if initialCapacity is less than zero
   */
   //O(1)
   public ArrList(int initialCapacity) {
   
      if (initialCapacity <= 0) {
      
         throw new IllegalArgumentException();
      
      }
      
      this.arr = (E[])new Object[initialCapacity];
      
   }
   
   /**
      Doubles the capacity of the internal array.
      After growing, the last half (at least) of the internal array
      is comprised of null values.
   */
   //O(N)
   private void grow() {
      
      E[] temp_arr = this.arr;
      this.arr = (E[])new Object[this.arr.length*2];
      
      for (int i = 0; i < this.size; i++) {
      
         this.arr[i] = temp_arr[i];
      
      }
      
   }
   
   /**
      Adds an element to the end of the ArrayList.
      @param element the element to add
      @throws IllegalArgumentException if element is null
   */
   //O(1) -> O(N)
   public E add(E element) {
      
      if (element.equals(null)) {
      
         throw new IllegalArgumentException();
      
      }
   
      if (this.size == this.arr.length) {
      
         this.grow();
      
      }
      
      this.arr[this.size] = element;
      this.size++;
      
      return element;
      
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
   public E add(int index, E element) {
   
      if (element.equals(null)) {
      
         throw new IllegalArgumentException();
      
      }
   
      if (index > this.size+1 || index < 0) {
      
         throw new IndexOutOfBoundsException();
      
      }
      
      
      if (this.size >= this.arr.length-1) {
   
         this.grow();
   
      }
      
      for (int i = this.size; i > index; i--) {
      
         this.arr[i] = this.arr[i-1];
      
      }
      
      this.arr[index] = element;
      this.size++;
      
      return element;

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
   public boolean remove(E object) {
   
      if (this.size >= this.arr.length-1) {
   
         this.grow();
   
      }
   
      for (int i = 0; i < this.size; i++) {
      
         if (arr[i].equals(object)) {
         
            for (int j = i; j < this.size; j++) {
         
               this.arr[j] = this.arr[j+1];
         
            }
            
            this.size--;
            return true;
         
         }
      
      }
            
      return false;

   }
   
   /**
      Remove the object at a certain index from the ArrayList
      @param index the index of the object to remove
      @return the removed object
      @throws IndexOutOfBoundsException if the index is out of bounds
   */
   //O(N)
   public E remove(int index) {
   
      if (index < -1 * this.size || index >= this.size) {
      
         throw new IndexOutOfBoundsException();
      
      }
      
      if (index < 0) {
         
         E out_val = this.arr[this.size+index];
         this.arr[this.size+index] = null;
         this.size--;
         return out_val;
      
      }
      
      if (this.size >= this.arr.length-1) {
   
         this.grow();
   
      }
      
      E out_val = this.arr[index];
         
      for (int i = index; i < this.size; i++) {
         
         this.arr[i] = this.arr[i+1];
         
      }
      
      this.size--;
      return out_val;
   }
   
   //O(N)
   public void removeAll() {
   
      while (!(this.arr[0] == null)) {
      
         this.remove(this.size-1);
      
      }
   
   }
   
   /**
      Update the element at a certain index to be a new element.
      @param index the index to update
      @param element the new element
      @return the element that was previously at that index
   */
   //O(1)
   public E set(int index, E element) {
      
      if (index < -1 * this.size || index >= this.size) {
      
         throw new IndexOutOfBoundsException();
      
      }
      
      if (index < 0) {
         
         E out_val = this.arr[this.size+index];
         this.arr[this.size+index] = element;
         return out_val;
      
      }
      
      E out_element = this.arr[index];
      this.arr[index] = element;
      return out_element;
      
   }
   
   /**
      Returns whether the ArrayList contains a certain object
      @param object the object to search for
      @return true if o is found in the ArrayList; false otherwise
   */
   //O(N)
   public boolean contains(E object) {
   
      for (int i = 0; i < this.size; i++) {
      
         if (this.arr[i].equals(object)) {
         
            return true;
         
         }
      
      }
      
      return false;
   }
   
   /**
      Returns the first index of a certain object in the ArrayList.
      @param object the object to search for
      @return the first index of o in the ArrayList, or
               -1 if o is not in the ArrayList.
   */
   //O(N)
   public int indexOf(E object) {
      
      for (int i = 0; i < this.size; i++) {
      
         if (this.arr[i].equals(object)) {
         
            return i;
         
         }
      
      }
      
      return -1;
      
   }
   
   /**
      Returns the object at a certain index
      @param index the index of the object
      @return the object at the index
      @throws IndexOutOfBoundsException if the index is out of bounds
   */
   //O(1)
   public E get(int index) {
   
      if (index < -1 * this.size || index >= this.size) {
      
         throw new IndexOutOfBoundsException();
      
      }
      
      if (index < 0) {
      
         return this.arr[this.size+index];
      
      }
   
      return this.arr[index];
    
   }
   
   /**
      Returns the current size of the ArrayList
      @return number of elements in this ArrayList
   */
   //O(1)
   public int size() {
      
      return this.size;
      
   }
   
   /**
      Checks if the ArrayList is empty
      @return true if this ArrayList has zero elements; false otherwise
   */
   //O(1)
   public boolean isEmpty() {
      
      return (this.size == 0);
      
   }
   
   @Override
   /**
      Returns a string in the format:
         [1, 3, 5]
      @return formatted ArrayList string
   */
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