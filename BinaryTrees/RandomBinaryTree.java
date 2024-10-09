/**
   A random binary tree, defined as a binary tree where each new piece of data 
      is inserted into a random open location in the tree.
*/
public class RandomBinaryTree<T extends Comparable<T>> extends BinaryTree<T>
{
   private BinaryNode<T> root;
   
   /**
      Constructs a random binary tree
      with no nodes in it.
   */
   public RandomBinaryTree()
   {
      this.root = null;
   }
   
   @Override
   public BinaryNode<T> getRoot()
   {
      return this.root;
   }
   
   /**
      Adds the data as a node in this Random Binary Tree.
      This involves traversing the tree randomly (50% chance left,
      50% chance right) until reaching an empty slot in the tree
      in which to insert the node.
      @param data the data for the node to add.
   */
   public void add(T data)
   {
      if (this.root == null)
      {
         this.root = new BinaryNode<T>(data);
      }
      else
      {
         addHelper(this.root, data);
      }
   }
   
   //A helper method which is able to add data under a given root.
   //    Created this method to make recursion down the tree easier.
   //    Whenever you think "I want to use recursion, but the parameters
   //       don't give me enough information", consider making a helper method!
   private void addHelper(BinaryNode<T> root, T data)
   {
      //Random number between 0 and 1. 0 = left; 1 = right.
      boolean goLeft = Math.round(Math.random()) == 0;
      if (goLeft)
      {
         if (root.getLeft() == null)
         {
            //Insert the data - we're done recursing!
            root.setLeft(new BinaryNode<T>(data));
         }
         else
         {
            //Recursively call addHelper on the left child
            addHelper(root.getLeft(), data);
         }
      }
      else //go right
      {
         if (root.getRight() == null)
         {
            //Insert the data - we're done recursing!
            root.setRight(new BinaryNode<T>(data));
         }
         else
         {
            //Recursively call addHelper on the right child
            addHelper(root.getRight(), data);
         }
      }
   }
}