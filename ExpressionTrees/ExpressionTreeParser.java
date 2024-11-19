import sh.*;
public class ExpressionTreeParser
{
   /**
      Creates an expression tree for you to evaluate!
      
                (+)
              /     \
             /       \
           (*)       (/)
           / \       / \
          /   \     /   \
        (-)    4   21    7
        / \
       /   \
      10    5
 
      The above tree represents ((10-5) * 4) + (21 / 7)
 
 
      Optional challenge: Handle negative numbers. For example, the above tree without
                           the 5 would be interpreted as:
                           (-10 * 4) + (21 / 7)
      
 
      @param args unused
   */
   public static void main(String[] args)
   {
      BinaryNode<String> root = new BinaryNode<>("+");
      root.setLeft(new BinaryNode<String>("5"));
      root.setRight(new BinaryNode<String>("-3"));
      
      print(root); //Should print: (((10-5)*4)+(21/7))
      System.out.println();
      System.out.println(evaluate(root)); //Should print: 23.0
   }
   
   /**
      Prints an expression tree as an equation, including parentheses around
         all non-leaf subtrees. 
         
      @param root the root of the tree to print
   */
   public static void print(BinaryNode<String> root)
   {
      
      if (root.getRight() == null && root.getLeft() != null && root.getData().equals("-")) {
      
         sh.print("-");
         print(root.getLeft());
      
      }
      else {
      
         if (root.getLeft() != null) {
            
            sh.print("(");
            print(root.getLeft());
         
         }
         
         sh.print("" + root + "");
         
         if (root.getRight() != null) {
         
            print(root.getRight());
            sh.print(")");
         
         }
      
      }
      
   }
   

   /**
      Evaluates and returns the result of an expression tree
      @return the answer as a double
      @throws IllegalArgumentException if the expression tree is invalid, including:
              - a leaf node which is not a number
              - a non-leaf node containing anything other than + - * /
              - any node with one child that should have two (example: * has one child)
   */
   public static double evaluate(BinaryNode<String> root) 
   {
      
      try {
      
         double num1 = 0;
         String oper = root.getData();
         double num2 = 0;
         
         if (root.getRight() == null && root.getLeft() != null && oper.equals("-")) return (-1.0) * evaluate(root.getLeft());
         
         if (root.getLeft() != null) num1 = evaluate(root.getLeft());
         if (root.getRight() != null) num2 = evaluate(root.getRight());
         
         if (oper.equals("+")) return num1 + num2;
         if (oper.equals("-")) return num1 - num2;
         if (oper.equals("*")) return num1 * num2;
         if (oper.equals("/")) return num1 / num2;
         
         return Double.parseDouble(root.getData());
         
      }
      catch (Exception e) {
      
         throw new IllegalArgumentException();
      
      }
            
   }
   
}

