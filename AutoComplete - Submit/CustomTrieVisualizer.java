import java.util.*;
import java.awt.*;

/**
   Visualize a CustomTrie..
   To use, just pass it your Trie:
   CustomTrieVisualizer.visualizeTrie(myTrie);
*/
public class CustomTrieVisualizer
{
   public static final int PANEL_SIZE = 800;
   public static final int MIN_NODE_HEIGHT = 200;
   public static final Font font = new Font("Roman", 0, 14);
   
   public static void visualizeTrie(CustomTrie nt)
   {
      DrawingPanel panel = new DrawingPanel(PANEL_SIZE, PANEL_SIZE);
      visualize(nt.getRoot(), panel);
   }
   
   public static void visualize(CustomTrieNode root, DrawingPanel panel)
   {   
      if (root == null) 
         return;
      Graphics g = panel.getGraphics();
      int height = getHeight(root);
      int heightPerNode = Math.min(MIN_NODE_HEIGHT, PANEL_SIZE / height);
   
      drawTree(panel, root, 0, PANEL_SIZE, 0, heightPerNode);
   }
   
   public static void drawTree(DrawingPanel panel, CustomTrieNode node, 
                                          int minX, int maxX, int y, int yStep) {
      Graphics g = panel.getGraphics();
      g.setFont(font);
      FontMetrics fm = g.getFontMetrics();
   
      int childCount = node.getChildren().size();
        
      if (childCount == 0) {
         return;
      }
        
      int childWidth = (maxX - minX) / childCount;
        
      int childX = minX;
      for (CustomTrieNode child : node.getChildren()) {
         Character childChar = child.getCharacter();
            
         //draw my data
         int width = fm.stringWidth(childChar.toString().toUpperCase());
         int height = fm.getHeight();
            
         int childCenterX = childX + childWidth / 2;
         g.drawString(childChar.toString().toUpperCase(), 
                        childCenterX - width / 2, y + yStep / 2);
                        
         if (child.isEndOfWord())
         {
            //Circle it if it's the end of a word
            g.drawOval(childCenterX - width / 2 - 10, y + yStep / 2 - 20, 30, 30);
         }
            
         int startX = node.getChildren().size() == 0 ? minX + childWidth : (int)(minX + childWidth * node.getChildren().size() * 0.5);
         g.drawLine(startX, y,
                           childCenterX, y + yStep / 2 - height);
            
                       
         drawTree(panel, child, childX, childX + childWidth, y + yStep, yStep);
            
         childX += childWidth;
      }
   }

    
       
   public static int getHeight(CustomTrieNode root)
   {
      if (root == null)
         return 0;
      else {
         int maxHeight = 0;
         for (CustomTrieNode child : root.getChildren()) {
            Character childChar = child.getCharacter();
            int childHeight = getHeight(child);
            if (childHeight > maxHeight)
               maxHeight = childHeight;
         }
         return maxHeight + 1;
      }
   }
}
