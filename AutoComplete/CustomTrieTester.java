import sh.*;
import java.util.*;

public class CustomTrieTester {

   public static void main (String[] args) {
   
      CustomTrie trie = new CustomTrie<String>();
      
      System.out.println(trie.getRoot());
            
      trie.insert("yay");
      trie.insert("yap");
      trie.insert("add");
      trie.insert("addition");
      trie.insert("additive");
      trie.insert("audit");
      trie.insert("yup");
      trie.insert("orange");
      trie.insert("add");
      trie.insert("orangutan");
      trie.insert("adds");
      trie.insert("add");
      trie.insert("adds");
      
      sh.p("all words:\n");
      
      ArrayList<String> words = trie.getAllWords();
      for (String word : words) {
      
         sh.p(word);
      
      }
      /*
      
      sh.p("prefix:\n");
      
      ArrayList<String> prefixWords = trie.searchPrefix("y");
      for (String word : prefixWords) {
      
         sh.p(word);
      
      }
      */
      
      CustomTrieVisualizer.visualizeTrie(trie);
   
   }

}