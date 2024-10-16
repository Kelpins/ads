import java.util.*;

public class CustomTrie<T> {

   private CustomTrieNode root;
   private ArrList<CustomTrieNode> children;   

   public CustomTrie() {
   
      this.root = new CustomTrieNode();
      this.children = children;
   
   }
   
   public CustomTrieNode getRoot() {
   
      return this.root;
   
   }
   
   private void insertHelper(CustomTrieNode trie, String str) {
   
      if (str.equals("")) {
      
         return;
      
      }
      
      ArrList<CustomTrieNode> children = new ArrList(trie.getChildren());
      
      for (CustomTrieNode child : children) {
            
         /*
         * If the child is equal to the next character in the string
         * then we can imagine the remainder of the process as if we
         * cut off the first character of the string and the current
         * root node of the trie
         */
         if (child.equals(str.charAt(0))) {
                                       
            insertHelper(trie.getChild(child.getCharacter()), str.substring(1));
         
         }
      
      }
      
      //Recursively adds each of the word's remaining characters as a path of nodes on the trie   
      trie.addChild(str.charAt(0), str.length()==1);
      insertHelper(trie.getChild(str.charAt(0)), str.substring(1));
      
   
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
   
   public boolean search (String word) {
   
      CustomTrieNode currentTrie = this.root;
   
      for (int i = 0; i < word.length(); i++) {
      
         if (!currentTrie.containsChild(word.charAt(i))) {
         
            return false;
         
         }
         else {
         
            currentTrie = currentTrie.getChild(word.charAt(i));
         
         }
      
      }
      
      if (currentTrie.isEndOfWord()) {
      
         return true;
      
      }
      
      return false;
   
   }
   
   private ArrList<String> getAllWordsHelper(CustomTrieNode trie, String prefix) {
      
      ArrList<String> words = new ArrList<>();
      ArrList<CustomTrieNode> children = new ArrList(trie.getChildren());
      
      for (CustomTrieNode child : children) {
         
         if (child.isEndOfWord()) {
         
            words.add(prefix + child.getCharacter());
         
         }
             
         String newPrefix = prefix + child.getCharacter();
   
         words.addAll(getAllWordsHelper(trie.getChild(child.getCharacter()), newPrefix));
            
         
      }
      
      return words;
   
   }
   
   public ArrList<String> getAllWords() {
      
      return getAllWordsHelper(this.root, "");   
   
   }
   
   public ArrList<String> searchPrefix(String prefix) {
   
      CustomTrieNode prefixRoot = this.root;
   
      for (int i = 0; i < prefix.length(); i++) {
      
         if (!prefixRoot.containsChild(prefix.charAt(i))) {
         
            return new ArrList<String>();
         
         }
         else {
         
            prefixRoot = prefixRoot.getChild(prefix.charAt(i));
         
         }
      
      }
      
      return this.getAllWordsHelper(prefixRoot, prefix);
   
   }

}