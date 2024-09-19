import java.awt.event.*;
import java.awt.*;
import java.util.*;
import sh.*;

public class UndoRedo {

   private static Stk<Character> undoStack = new Stk<>();
   private static Stk<Character> redoStack = new Stk<>();
   
   public static void main(String[] args) {
   
      DrawingPanel panel = new DrawingPanel(500, 500);
      
      panel.addKeyListener(new UndoRedoKeyListener(panel));
      
   }

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
         
         if ((code == 'Y' && (event.isControlDown() || event.isMetaDown()) && !event.isShiftDown()) || (code == 'Z' && (event.isControlDown() || event.isMetaDown()) && (event.isShiftDown()))) {
         
            if (!redoStack.empty()) {
               
               if (redoStack.peek() == 'b') {
               
                  undoStack.push(this.currentString.charAt(this.currentString.length()-1));
                  undoStack.push('b');
                  
                  redoStack.pop();
                  
                  this.currentString = this.currentString.substring(0,this.currentString.length()-1);
               
               }
               
               else {
            
                  this.currentString += redoStack.peek();
                  
                  undoStack.push(redoStack.pop());
               
               }
            
            }
            
         }
         else if (code == 'Z' && (event.isControlDown() || event.isMetaDown())) {
         
            if (!undoStack.empty()) {
            
               if (undoStack.peek() == 'b') {
               
                  undoStack.pop();
                  
                  this.currentString += undoStack.pop();
                  
                  redoStack.push('b');
               
               }
               else {
               
                  redoStack.push(undoStack.pop());
                  
                  this.currentString = this.currentString.substring(0,this.currentString.length()-1);
                  
               }
            
            }
            
         }
         else if (code >= 'A' && code <= 'Z' && !event.isControlDown() && !event.isShiftDown()) {
         
            undoStack.push((char)code);
            
            redoStack.clear();
            
            this.currentString += (char)code;
            
         }
         else if (code == 8) {
         
            if (this.currentString.length() >= 1) {
            
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