import java.util.*;

/**
* CustomTrie
* Defines the data structure CustomTrie that can insert, search, searchPrefix, and getAllWords
* @author Kellan Williams, Applications of Data Structures
*/
public class CustomTrie {

   private CustomTrieNode root;
   
   /**
   * Constructs a new CustomTrie object
   */
   public CustomTrie() {
   
      this.root = new CustomTrieNode();
         
   }
   
   /**
   * Getter method for the root node of the CustomTrie
   * @return the root node of the CustomTrie
   */
   public CustomTrieNode getRoot() {
   
      return this.root;
   
   }
   
   /**
   * Recusive helper method for insert functionality
   * @param trie the current node in the recursion
   * @param str the remainder of the string to be inserted
   */
   private void insertHelper(CustomTrieNode trie, String str) {
   
      if (str.equals("")) {
      
         return;
      
      }
      
      /*
      * Since CustomTrieNode's built-in addChild function checks to see if the child already
      * exists, the result of this line will always be the same: guaranteed existence of
      * a child node with the character being the first one in the given string. Also, if
      * the length of the string is one (ie. there is only one character left to be added),
      * the second parameter of addChild will be true and it will be marked as end of word.
      */
      trie.addChild(str.charAt(0), str.length()==1);
      
      /*
      * Since we have guaranteed the existence of the correct child node, we can move down into
      * that child node and use this same function, cutting off the first character in the string.
      */
      insertHelper(trie.getChild(str.charAt(0)), str.substring(1));
      
   
   }
   
   /**
   * Inserts a word into the CustomTrie
   * @param word the word to be inserted
   */
   public void insert (String word) {
      
      //Initiate the recursion for inserting a word
      insertHelper(this.root, word);
   
   }
   
   /**
   * Searches for the presence of a word in the CustomTrie
   * @param word the word we're searching for
   * @return whether or not that word exists in the CustomTrie
   */
   public boolean search (String word) {
   
      CustomTrieNode currentTrie = this.root;
      
      /*
      * Looping through each character in the desired string:
      * if that character exists, then we continue onto the next character but with
      * the current character as the new root node. If that character does not exist,
      * then the word cannot exist in the tree, so we just return false.
      */
      for (int i = 0; i < word.length(); i++) {
      
         if (!currentTrie.containsChild(word.charAt(i))) {
         
            return false;
         
         }
         else {
         
            currentTrie = currentTrie.getChild(word.charAt(i));
         
         }
      
      }
      
      /*
      * If the entire inputted string exists within the word but the final character is not
      * marked as the end of the word, then we still return false because we have just
      * proven the existence of a string, not found a true word.
      */
      if (!currentTrie.isEndOfWord()) {
      
         return false;
      
      }
      
      return true;
   
   }
   
   /**
   * Recursive helper method for getAllWords functionality
   * @param trie the current node in the recursion
   * @param prefix a string representing how far down a given path the recursion has gotten
   * @return an ArrayList containing all words from the current iteration and all below iterations
   */
   private ArrayList<String> getAllWordsHelper(CustomTrieNode trie, String prefix) {
      
      //This ArrayList stores all words found in this current iteration of the recursive function
      ArrayList<String> words = new ArrayList<>();
      
      ArrayList<CustomTrieNode> children = trie.getChildren();
      
      //For each child node,
      for (CustomTrieNode child : children) {
         
         /*
         * first check if that node is marked as the end of a word. If it is, then add the
         * whole word to the ArrayList keeping track of words found in this iteration.
         */
         if (child.isEndOfWord()) {
         
            words.add(prefix + child.getCharacter());
         
         }
         
         /*
         * Then, update the prefix to include the next character. The String prefix
         * is what keeps track of where we are in the recursion, by concatenating
         * all previously run-through child nodes into one string.
         */
         String newPrefix = prefix + child.getCharacter();
   
         /*
         * finally, add all words from the next layer down into the words ArrayList,
         * which will be passed up the recursion until it reaches the very top, and
         * includes all pathways that have been recursively traversed.
         */
         words.addAll(getAllWordsHelper(trie.getChild(child.getCharacter()), newPrefix));
            
         
      }
      
      return words;
   
   }
   
   /**
   * Gets all words in the CustomTrie
   * @return an ArrayList containing all words represented by the CustomTrie
   */
   public ArrayList<String> getAllWords() {
      
      //Initiate the recursion for getting all words, with the initial prefix being an empty string
      return getAllWordsHelper(this.root, "");   
   
   }
   
   /**
   * Gets all words in the CustomTrie beginning with a given prefix
   * @param prefix the string to find all words starting with
   * @return an ArrayList containing all words in the trie beginning with the prefix
   */
   public ArrayList<String> searchPrefix(String prefix) {
   
      CustomTrieNode prefixRoot = this.root;
      
      /*
      * We can find the root node of the tree containing every word with a given prefix
      * by traversing down the tree, similar to what we did for the search function.
      */
      for (int i = 0; i < prefix.length(); i++) {
         
         /*
         * If the prefix is not included within the tree, then there are no words
         * that begin with that prefix, so we return an empty ArrayList.
         */
         if (!prefixRoot.containsChild(prefix.charAt(i))) {
         
            return new ArrayList<String>();
         
         }
         //Otherwise, traverse down to the next character in the prefix
         else {
         
            prefixRoot = prefixRoot.getChild(prefix.charAt(i));
         
         }
      
      }
      
      /*
      * Now that we have acquired the root node for the tree containing all words that begin
      * with the given prefix, we can simulate getting all the words in that tree by using
      * our above getAllWordsHelper method, which accepts a prefix already (which will be
      * added to the start of each word before it is added to the list and returned).
      */
      ArrayList<String> words = this.getAllWordsHelper(prefixRoot, prefix);
      
      /*
      * Finally, if the prefix itself is a word, we stick it at the beginning of the
      * ArrayList before we return it, so that when it appears later it will still
      * be in alphabetical order.
      */
      if (prefixRoot.isEndOfWord()) {
         
         words.add(0, prefix);
      
      }
      
      return words;
   
   }

}