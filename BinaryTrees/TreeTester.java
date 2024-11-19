public class TreeTester
{
   public static void main(String[] args)
   {      
      BinarySearchTree<Integer> rbt = new BinarySearchTree<>();
      rbt.add(4);
      rbt.add(7);
      rbt.add(2);
      rbt.add(3);
      rbt.add(6);
      rbt.add(5);
      rbt.add(8);
      rbt.add(5);
      rbt.add(50);
      rbt.add(533);
      rbt.add(946);
      rbt.add(284);
      
      rbt.printPreOrder();
      System.out.println("");
      rbt.printLevelOrder();
      
      BinaryTreeVisualizer.visualizeBinaryTree(rbt);
   }
}