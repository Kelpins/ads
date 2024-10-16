import java.util.*;

public class CustomTrie<T> {

   private CustomTrieNode root;
   private ArrayList<CustomTrieNode> children;   

   public CustomTrie() {
   
      this.root = new CustomTrieNode();
      this.children = children;
   
   }
   
   public CustomTrieNode getRoot() {
   
      return this.root;
   
   }
   
   private void insertHelper(CustomTrieNode trie, String str) {
      
      ArrayList<CustomTrieNode> children = trie.getChildren();
      
      for (CustomTrieNode child : children) {
            
         /*
         * If the child is equal to the next character in the string
         * then we can imagine the remainder of the process as if we
         * cut off the first character of the string and the current
         * root node of the trie
         */
         if (child.equals(str.charAt(0))) {
            
            CustomTrieNode newRoot = trie.getChild(child.getCharacter());
                           
            insertHelper(newRoot, str.substring(1));
            
            break;
         
         }
      
      }
            
      if (str.length() == 1) {
      
         //Adds the final (end-of-word) character
         
         trie.addChild(str.charAt(0), true);
         
         return;
      
      }
      else {
         
         //Recursively adds each of the word's remaining characters as a path of nodes on the trie
         
         trie.addChild(str.charAt(0), false);
         insertHelper(trie.getChild(str.charAt(0)), str.substring(1));
      
      }
   
   }
   
   public void insert (String word) {
   
      /*
      * No reason to add the word if it already exists (though nothing breaks if you try),
      * evident by the fact that search currently returns false and nothing breaks
      *
      * I don't know how efficient my search will be, but I assume that it is
      * going to be more efficient than running the entire insert process again.
      */
      if (!this.search(word)) {
         
         insertHelper(this.root, word);
      
      }
   
   }
   
   /*private boolean searchHelper() {
   
      return false;
   
   }
   
   public boolean search (String word) {
   
      return false;
   
   }*/
   
   private ArrayList<String> getAllWordsHelper(CustomTrieNode trie, String prefix) {
      
      ArrayList<String> words = new ArrayList<>();
      ArrayList<CustomTrieNode> children = trie.getChildren();
      
      for (CustomTrieNode child : children) {
         
         if (child.isEndOfWord()) {
         
            words.add(prefix + child.getCharacter());
         
         }
             
         String newPrefix = prefix + child.getCharacter();
   
         words.addAll(getAllWordsHelper(trie.getChild(child.getCharacter()), newPrefix));
            
         
      }
      
      return words;
   
   }
   
   public ArrayList<String> getAllWords() {
      
      return getAllWordsHelper(this.root, "");   
   
   }
   
   /*public ArrayList<String> searchPrefix(String prefix) {
      
      //return this.getAllWordsHelper([FIND ROOT W/ GIVEN PREFIX], prefix);
      return new ArrayList<String>();
   
   }*/

}