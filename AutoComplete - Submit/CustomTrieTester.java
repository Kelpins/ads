import sh.*;
import java.util.*;

public class CustomTrieTester {

   public static void main (String[] args) {
   
      CustomTrie trie = new CustomTrie();
      
      System.out.println(trie.getRoot());
            
      trie.insert("yay");
      trie.insert("yap");

      trie.insert("addition");
      trie.insert("additive");
      
      trie.insert("audit");
      trie.insert("yup");
      trie.insert("orange");

      trie.insert("orangutan");
      trie.insert("adds");
      trie.insert("add");
      trie.insert("adds");
      
      sh.p("all words:\n");
      
      ArrayList<String> words = trie.getAllWords();
      for (String word : words) {
      
         sh.p(word);
      
      }
      
      
      sh.p("\n\nprefix(y):\n");
      
      ArrayList<String> prefixWords = trie.searchPrefix("y");
      for (String word : prefixWords) {
      
         sh.p(word);
      
      }
      
      sh.p("\n\nprefix(add):\n");
      
      ArrayList<String> prefixWords2 = trie.searchPrefix("add");
      for (String word : prefixWords2) {
      
         sh.p(word);
      
      }
      
      sh.p("\n");
      sh.p(trie.search("add"));
      sh.p(trie.search("adds"));
      sh.p(trie.search("orangutan"));
      sh.p(trie.search("ya"));
      sh.p(trie.search("addi"));
      sh.p(trie.search("oranguta"));
      
      CustomTrieVisualizer.visualizeTrie(trie);
   
   }

}