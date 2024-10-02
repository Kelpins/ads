import java.util.*;
import sh.*;

public class CustomHashMap<K,V> {

   private ArrList<KeyValuePair<K,V>>[] data;
   
   public CustomHashMap() {
   
      this.data = (ArrList<KeyValuePair<K,V>>[])new ArrList[3];
   
   }
   
   private void grow() {
      
      ArrList<KeyValuePair<K,V>>[] temp_arr = this.data;
      this.data = (ArrList<KeyValuePair<K,V>>[])new ArrList[this.data.length * 2];
      
      for (int i = 0; i < temp_arr.length; i++) {
      
         if (temp_arr[i] != null) {
            
            for (int j = 0; j < temp_arr[i].size(); j++) {
            
               KeyValuePair<K,V> kvp = temp_arr[i].get(j);
            
               this.put(kvp.getKey(), kvp.getValue());
            
            }
         
         }
      
      }
      
   }
   
   private int getSize() {
   
      int count = 0;
   
      for (int i = 0; i < this.data.length; i++) {
      
         if (this.data[i] != null) {
         
            for (int j = 0; j < this.data[i].size(); j++) {
            
               count++;
            
            }
         
         }
      
      }
      
      return count;
   
   }
   
   public V get(K key) {
   
      int index = Math.abs(key.hashCode());
      int size = this.data.length;
      
      ArrList<KeyValuePair<K,V>> values = data[index%size];
      
      if (values != null) {
      
         for (int i = 0; i < values.size(); i++) {
      
            if (values.get(i).getKey().equals(key)) {
            
               return values.get(i).getValue();
            
            }
            
         }
      
      }
      
      return null;
   
   }
   
   public void put(K key, V value) {
   
      int index = Math.abs(key.hashCode());
      int size = this.data.length;
      
      if (this.getSize()+1 > (0.6 * size)) {
      
         this.grow();
      
      }
      
      size = this.data.length;
      
      if (this.data[index%size] == null) {
      
         this.data[index%size] = new ArrList<KeyValuePair<K,V>>();
         this.data[index%size].add(new KeyValuePair(key, value));
         
      
      }
      else {
               
         for (int i = 0; i < this.data[index%size].size(); i++) {
         
            if (this.data[index%size].get(i).getKey().equals(key)) {
            
               this.data[index%size].get(i).setValue(value);
               
               return ;
            
            }
         
         }
         
         this.data[index%size].add(new KeyValuePair(key, value));
      
      }
   
   }
   
   public String toString() {
   
      String out = "HashMap with " + this.getSize() + " elements / " + this.data.length + " slots:\n";
      
      for (int i = 0; i < this.data.length; i++) {
      
         if (this.data[i] == null) {
         
            out += (i + ": null\n");
         
         }
         else {
         
            out += (i + ": [");
            for (int j = 0; j < this.data[i].size(); j++) {
               
               if (j > 0) {
               
                  out += (", ");
               
               }
               
               out += ("(key: ");
               out += ("" + this.data[i].get(j).getKey());
               out += ("; value: ");
               out += ("" + this.data[i].get(j).getValue());
               out += (")");
            
            }
            out += ("]\n");
         
         }
      
      }
      
      return out;
   
   }

}