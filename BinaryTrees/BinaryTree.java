/**
   A binary tree, defined as a tree where each node has at most 2 children.
*/
public abstract class BinaryTree<T extends Comparable<T>>
{
   /**
      Returns the root of this binary tree
      @return the root node
   */
   public abstract BinaryNode<T> getRoot();
}