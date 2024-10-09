import java.util.*;

/**
* CustomHashMap
* Defines the data structure CustomHashMap with get, put, and toString methods.
* @author Kellan, Applications of Data Structures
*/
public class CustomHashMap<K,V> {

   private ArrList<KeyValuePair<K,V>>[] data;
   private int size;
   private static final double LOAD_FACTOR = 0.6;
   
   /**
   * Constructs a CustomHashMap object
   */
   public CustomHashMap() {
   
      this.data = (ArrList<KeyValuePair<K,V>>[])new ArrList[3];
      this.size = 0;
   
   }
   
   /**
   * Grows the map's capacity; this method is called when the load factor is reached.
   */
   private void grow() {
      
      // Creates a temporary array and resets the original array, with double capacity!
      ArrList<KeyValuePair<K,V>>[] temp_arr = this.data;
      this.data = new ArrList[this.data.length * 2];
      
      // Copies each element back over into the original array.
      for (int i = 0; i < temp_arr.length; i++) {
      
         if (temp_arr[i] != null) {
            
            for (int j = 0; j < temp_arr[i].size(); j++) {
            
               KeyValuePair<K,V> kvp = temp_arr[i].get(j);
               
               this.size = 0;
               
               // In order not to rehash, simply use the built-in put functionality!
               this.put(kvp.getKey(), kvp.getValue());
            
            }
         
         }
      
      }
      
   }
   
   /**
   * Gets the value associated with a given key in the CustomHashMap
   * @param key the given key to associate with a value
   * @return the value associated with the given key
   */
   public V get(K key) {
   
      int index = Math.abs(key.hashCode()) % this.data.length;
      
      ArrList<KeyValuePair<K,V>> values = data[index];
      
      if (values != null) {
      
         for (int i = 0; i < values.size(); i++) {
      
            if (values.get(i).getKey().equals(key)) {
            
               return values.get(i).getValue();
            
            }
            
         }
      
      }
      
      return null;
   
   }
   
   /**
   * Puts a key value pair into the CustomHashMap
   * @param key the key of the key value pair
   * @param value the value of the key value pair
   */
   public void put(K key, V value) {
   
      /*
      * HashMaps work by hashing each key and modding by the capacity,
      * using the result as the index in the underlying array
      */
      
      // Checks if, by the end of this operation, we will have hit the load factor
      if (this.size+1 > (this.LOAD_FACTOR * this.data.length)) {
      
         this.grow();
      
      }
      
      int index = Math.abs(key.hashCode()) % this.data.length;
      
      /*
      * If there is nothing at that index in the underlying array,
      * then it is as simple as creating an empty ArrList at that index
      * and filling it with the KeyValuePair.
      */
      if (this.data[index] == null) {
      
         this.data[index] = new ArrList<KeyValuePair<K,V>>();
         this.data[index].add(new KeyValuePair(key, value));
      
      }
      else {
         
         /*
         * If there is already an ArrList at that index in the underlying array,
         * we first have to check if the input key already has an associated value
         * by looping through each key value pair in that ArrList and comparing the keys
         */
         for (int i = 0; i < this.data[index].size(); i++) {
         
            // If the keys match up, then we only need to change the value at that spot in the map
            if (this.data[index].get(i).getKey().equals(key)) {
            
               this.data[index].get(i).setValue(value);
               
               this.size++;
               
               return ;
            
            }
         
         }
         
         // Otherwise, we add a new KeyValuePair to the ArrList at that index
         this.data[index].add(new KeyValuePair(key, value));
      
      }
      
      this.size++;
         
   }

   @Override
   public String toString() {
   
      String out = "HashMap with " + this.size + " elements / " + this.data.length + " slots:\n";
      
      for (int i = 0; i < this.data.length; i++) {
      
         out += (i + ": " + this.data[i] + "\n");
               
      }
      
      return out;
   
   }

}