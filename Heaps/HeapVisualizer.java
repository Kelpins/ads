import java.util.*;
import java.awt.*;

public class HeapVisualizer
{
   public static final int PANEL_SIZE = 800;
   public static final int MIN_NODE_HEIGHT = 200;
   public static final Font font = new Font("Roman", 0, 14);
   
   public static void visualizeHeap(CustomPriorityQueue heap)
   {
      DrawingPanel panel = new DrawingPanel(PANEL_SIZE, PANEL_SIZE);
      visualize(heap.getArray(), panel);
   }
   
   public static void visualize(ArrayList arr, DrawingPanel panel)
   {   
       if (arr==null) return;
       Graphics g = panel.getGraphics();
       int height = getHeight(arr);
       int heightPerNode = Math.min(MIN_NODE_HEIGHT, PANEL_SIZE / height);

       drawTree(panel, arr, 0, 0, PANEL_SIZE, 0, heightPerNode);
    }
        
        
    public static void drawTree(DrawingPanel panel, ArrayList arr, int index,
                                          int minX, int maxX, int y, int yStep) {
        Graphics g = panel.getGraphics();
        
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();
        int width = fm.stringWidth(arr.get(index).toString());
        int height = fm.getHeight();
        
        String s = arr.get(index).toString();
        
        int xSep = Math.min((maxX - minX)/8, 10);

        g.drawString(arr.get(index).toString(), 
                     (minX + maxX)/2 - width/2, y + yStep/2);

        if ((2 * index) + 1 < arr.size()) {
            // if left tree not empty, draw line to it and recursively
            // draw that tree
            g.drawLine((minX + maxX)/2 - xSep, y + yStep/2 + 5,
                       (minX + (minX + maxX)/2) / 2, 
                       y + yStep + yStep/2 - height);
            drawTree(panel, arr, (2 * index) + 1, minX, (minX + maxX)/2, y + yStep, yStep);
        }
        if ((2 * index) + 2 < arr.size()) {
            // same thing for right subtree.
            g.drawLine((minX + maxX)/2 + xSep, y + yStep/2 + 5,
                       (maxX + (minX + maxX)/2) / 2, 
                       y + yStep + yStep/2 - height);
            drawTree(panel, arr, (2 * index) + 2, (minX + maxX)/2, maxX, y + yStep, yStep);
        }
   }
   
    public static int getHeight(ArrayList arr)
    {
        if (arr == null) return 0;
        
        return getHeightHelper(arr, 0);
    }
    
    private static int getHeightHelper(ArrayList arr, int index)
    {
         if (index >= arr.size()) return 0;
         
         // Compute  height of each subtree
         int lheight = getHeightHelper(arr, (2 * index) + 1); //left
         int rheight = getHeightHelper(arr, (2 * index) + 2); //right
 
         // use the larger one
         if (lheight > rheight)
             return (lheight + 1);
         else
             return (rheight + 1);
    }
}