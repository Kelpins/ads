import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.*;

/**
* AutoCompleter with Trie
* @author Kellan Williams, Applications of Data Structures
*/
public class AutoCompleter {
   
   /** Width for all drawing panels created in AutoCompleter */
   public static final int PANEL_WIDTH = 500;
   /** Height for all drawing panels created in AutoCompleter */
   public static final int PANEL_HEIGHT = 525;
   /** Approximate height of each word (with size 24 Verdana font) */
   public static final double APPRX_WORD_HEIGHT = 20.833;
   
   /**
   * Creates a new DrawingPanel, which allows a user to type any character from A-Z.
   * Every time a character is added to the input line, the lines below it will show
   * each word with that prefix.
   * @param args console input
   */
   public static void main(String[] args) {
   
      DrawingPanel panel = new DrawingPanel(PANEL_WIDTH, PANEL_HEIGHT);
      panel.addKeyListener(new AutoCompleteKeyListener(panel));
      
   }
   
   /**
   * AutoCompleteListener
   * @author UW or Amanda O'Neal, Applications of Data Structures
   */
   static class AutoCompleteKeyListener extends KeyAdapter {
   
      private DrawingPanel panel;
      private String currentInputString;
      private CustomTrie dictionary;
         
      /**
      * Constructs a KeyListener for the AutoCompleter panel
      * @param panel the DrawingPanel to draw onto
      */
      public AutoCompleteKeyListener(DrawingPanel panel) {
      
         this.panel = panel;
         this.currentInputString = "";
         this.dictionary = downloadDictionary();
                  
         Font myFont = new Font("Verdana", Font.BOLD, 24);
         this.panel.getGraphics().setFont(myFont);
         
         System.out.println("initialized!");
          
      }
      
      /**
      * Reads each word from dictionary.txt and inserts it into a CustomTrie
      * @return a CustomTrie object containing all words
      */
      private CustomTrie downloadDictionary() {
         
         CustomTrie allWords = new CustomTrie();
         
         try {
         
            File dict = new File("dictionary.txt");
            Scanner scan = new Scanner(dict);
            
            // Adds all words to the CustomTrie
            while (scan.hasNextLine()) {
            
               String word = scan.nextLine();            
               allWords.insert(word);
                     
            }
            
         }
         catch (FileNotFoundException e) {
         
            System.out.println("dictionary.txt does not exist :(");
            
         }
         
         System.out.println("downloaded!");
                  
         return allWords;
      
      }
      
      /**
      * Updates the drawing panel with the current input string and all associated dictionary words
      */
      private void updatePanel() {
            
         this.panel.clear();
         this.panel.getGraphics().drawString(this.currentInputString, 0, 20);
         
         /*
         * Uses CustomTrie's searchPrefix to get an ArrayList of all words beginning
         * with the current input string.
         */
         ArrayList<String> autoCompleteWords =
            this.dictionary.searchPrefix(this.currentInputString.toLowerCase());
         
         /*
         *Since searchPrefix will return every word with an empty prefix (which we don't want),
         * we clear the whole panel if the current input string is empty.
         */
         if (this.currentInputString.equals("")) {
         
            this.panel.clear();
         
         }
         else {
         
            /*
            * Remember those final variables up top that you probably marked as useless?
            * Well here is where we use them! This will ensure that we don't loop through the
            * entire dictionary every time we go to update the panel; instead, we only look at
            * the exact amount of words necessary to fill up the height of the drawing panel.
            */
            int maxDisplayedWords = (int)(PANEL_HEIGHT/APPRX_WORD_HEIGHT);
            
            /*
            * Of course, if there are less than (int)(PANEL_HEIGHT/APPRX_WORD_HEIGHT) words for
            * a given prefix, we need to make sure we only display the amount of words we have.
            */
            if (autoCompleteWords.size() < maxDisplayedWords) {
            
               maxDisplayedWords = autoCompleteWords.size();
            
            }
            
            //Starting 40 pixels down, we draw words at 20 pixel increments
            for (int i = 0; i < maxDisplayedWords; i++) {
            
               this.panel.getGraphics().drawString(autoCompleteWords.get(i), 0, 40+i*20);
            
            }
         
         }
         
         System.out.println("updated!");
               
      }
      
      @Override
      public void keyReleased(KeyEvent event) {
            
         int code = event.getKeyCode();
         
         if (code >= 'A' && code <= 'Z' && !event.isControlDown()) {
                     
            this.currentInputString += (char)code;
            
         }
         else if (event.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
         
            if (this.currentInputString.length() >= 1) {
               
               this.currentInputString =
                  this.currentInputString.substring(0,this.currentInputString.length()-1);
                              
            }
                     
         }
         
         System.out.println("key pressed!");
         
         //Update the panel with the new input string and the new autocompleted words
         this.updatePanel();
         
      }
      
   }

}