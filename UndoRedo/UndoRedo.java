import java.awt.event.*;
import java.awt.*;
import java.util.*;

/**
* UndoRedo
* @author Kellan Williams, Applications of Data Structures
*/
public class UndoRedo {

   private static CustomStack<Character> undoStack = new CustomStack<>();
   private static CustomStack<Character> redoStack = new CustomStack<>();
   
   /**
   * Creates a new DrawingPanel, which allows a user to type any character from A-Z, and backspace
   * When Ctrl+Z is pressed, the text will undo the previous input
   * When Ctrl+Y or Ctrl+Shift+Z is pressed, the text will redo the previous undone input (if exists)
   * @param args console input
   */
   public static void main(String[] args) {
   
      DrawingPanel panel = new DrawingPanel(500, 500);
      panel.addKeyListener(new UndoRedoKeyListener(panel));
      
   }
   
   /**
   * UndoRedoKeyListener
   * @author UW or Amanda O'Neal, Applications of Data Structures
   */
   static class UndoRedoKeyListener extends KeyAdapter {
   
      private DrawingPanel panel;
      private String currentString;
         
      /**
      * Constructs a KeyListener for undo/redo panel
      * @param panel the DrawingPanel to draw onto
      */
      public UndoRedoKeyListener(DrawingPanel panel) {
      
         this.panel = panel;
         this.currentString = "";
         
         Font myFont = new Font("Verdana", Font.BOLD, 24);
         this.panel.getGraphics().setFont(myFont);
          
      }
      
      @Override
      public void keyReleased(KeyEvent event) {
      
         int code = event.getKeyCode();
         
         //Determines whether the keyboard shortcut Ctrl+Y or Ctrl+Shift+Z was inputted
         if ((code == 'Y' && (event.isControlDown() || event.isMetaDown()) && !event.isShiftDown())
               || (code == 'Z' && (event.isControlDown() || event.isMetaDown())
                  && (event.isShiftDown()))) {
         
            if (!redoStack.empty()) {
               
               if (redoStack.peek() == 'b') {
               
                  undoStack.push(this.currentString.charAt(this.currentString.length()-1));
                  undoStack.push('b');
                  redoStack.pop();
                  
                  this.currentString =
                     this.currentString.substring(0,this.currentString.length()-1);
               
               }
               
               else {
            
                  this.currentString += redoStack.peek();
                  
                  undoStack.push(redoStack.pop());
               
               }
            
            }
            
         }
         //Determines whether the keyboard shortcut Ctrl+Z was inputted
         else if (code == 'Z' && (event.isControlDown() || event.isMetaDown())) {
         
            if (!undoStack.empty()) {
            
               if (undoStack.peek() == 'b') {
               
                  undoStack.pop();
                  
                  this.currentString += undoStack.pop();
                  
                  redoStack.push('b');
               
               }
               else {
               
                  redoStack.push(undoStack.pop());
                  
                  this.currentString =
                     this.currentString.substring(0,this.currentString.length()-1);
                  
               }
            
            }
            
         }
         //If neither keyboard shortcut was inputted, determines which key was pressed
         else if (code >= 'A' && code <= 'Z' && !event.isControlDown() && !event.isShiftDown()) {
         
            undoStack.push((char)code);
            
            redoStack.clear();
            
            this.currentString += (char)code;
            
         }
         //The lowercase 'b' character is used to represent a backspace, which has key code 8
         else if (code == 8) {
         
            if (this.currentString.length() >= 1) {
               
               /*
               * By placing the deleted character before the lowercase 'b' in the stack,
               * we can easily reference which character needs to be deleted or returned
               * by removing the 'b' and looking at the next character in the stack
               */
               undoStack.push(this.currentString.charAt(this.currentString.length()-1));
               undoStack.push('b');
               
               this.currentString = this.currentString.substring(0,this.currentString.length()-1);
               
               redoStack.clear();
               
            }
                     
         }
         
         this.panel.clear();
         this.panel.getGraphics().drawString(this.currentString, 0, 20);
         
      }
      
   }

}