/**
* A class which represents a Key/Value Pair ("KVP")
* To be used in CustomHashMap
* You do NOT need to modify this file.
* @author Amanda O'Neal, 2024
*/
public class KeyValuePair<K, V>
{
   private K key;
   private V value;
   
   /**
   * Constructs the KVP with a key and a value
   */
   public KeyValuePair(K key, V value)
   {
      this.key = key;
      this.value = value;
   }
   
   /**
   * Gets the key
   * @return the key from this KVP
   */
   public K getKey()
   {
      return this.key;
   }
   
   /**
   * Gets the value
   * @return the value from this KVP
   */
   public V getValue()
   {
      return this.value;
   }
   
   /**
   * Sets the value of this KVP
   * @param value the value to set to
   */
   public void setValue(V value)
   {
      this.value = value;
   }
   
   @Override
   public String toString()
   {
      return "(key: " + this.key + "; value: " + this.value + ")";
   }
}