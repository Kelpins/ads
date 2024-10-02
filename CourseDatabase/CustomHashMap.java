import java.util.*;
import sh.*;

public class CustomHashMap<K,V> {

   private ArrList<KeyValuePair<K,V>>[] data;
   
   public CustomHashMap() {
   
      this.data = new ArrList<KeyValuePair<K,V>>[3];
   
   }
   
   public V get(K key) {
   
      int index = key.hashCode();
      int size = data.length;
      
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

}