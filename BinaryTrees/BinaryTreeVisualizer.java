import java.util.*;
import java.awt.*;

/**
   Visualize a tree that extends BinaryTree.
   To use, just pass it your tree:
   BinaryTreeVisualizer.visualizeBinaryTree(myTree);
*/
public class BinaryTreeVisualizer
{
   public static final int PANEL_SIZE = 800;
   public static final int MIN_NODE_HEIGHT = 200;
   public static final Font font = new Font("Roman", 0, 14);
   
   public static void visualizeBinaryTree(BinaryTree bt)
   {
      DrawingPanel panel = new DrawingPanel(PANEL_SIZE, PANEL_SIZE);
      visualize(bt.getRoot(), panel);
   }
   
   private static void visualize(BinaryNode root, DrawingPanel panel)
   {   
       if (root==null) return;
       Graphics g = panel.getGraphics();
       int height = getHeight(root);
       int heightPerNode = Math.min(MIN_NODE_HEIGHT, PANEL_SIZE / height);

       drawTree(panel, root, 0, PANEL_SIZE, 0, heightPerNode);
  }
        
        
   private static void drawTree(DrawingPanel panel, BinaryNode node, 
                                          int minX, int maxX, int y, int yStep) {
        Graphics g = panel.getGraphics();
        
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();
        int width = fm.stringWidth(node.getData().toString());
        int height = fm.getHeight();
        
        String s = node.getData().toString();
        
        int xSep = Math.min((maxX - minX)/8, 10);

        g.drawString(node.getData().toString(), 
                     (minX + maxX)/2 - width/2, y + yStep/2);

        if (node.getLeft() != null) {
            // if left tree not empty, draw line to it and recursively
            // draw that tree
            g.drawLine((minX + maxX)/2 - xSep, y + yStep/2 + 5,
                       (minX + (minX + maxX)/2) / 2, 
                       y + yStep + yStep/2 - height);
            drawTree(panel, node.getLeft(), minX, (minX + maxX)/2, y + yStep, yStep);
        }
        if (node.getRight() != null) {
            // same thing for right subtree.
            g.drawLine((minX + maxX)/2 + xSep, y + yStep/2 + 5,
                       (maxX + (minX + maxX)/2) / 2, 
                       y + yStep + yStep/2 - height);
            drawTree(panel, node.getRight(), (minX + maxX)/2, maxX, y + yStep, yStep);
        }
   }
   
    public static int getHeight(BinaryNode root)
    {
        if (root == null)
            return 0;
        else {
            // Compute  height of each subtree
            int lheight = getHeight(root.getLeft());
            int rheight = getHeight(root.getRight());
 
            // use the larger one
            if (lheight > rheight)
                return (lheight + 1);
            else
                return (rheight + 1);
        }
    }
}