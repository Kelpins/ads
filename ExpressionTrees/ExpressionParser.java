import java.util.*;
import sh.*;

public class ExpressionParser
{
   /**
      Asks the user for a math expression, and gives them the solution to that expression.
 
      Example: 
      input: 1 + (1 + 2*3)*(3-1-(2*2-9))
      prints: 50
 
      @param args unused
   */
   public static void main(String[] args)
   {
      //Get equation from the user
      Scanner input = new Scanner(System.in);
      System.out.print("Equation: ");
      String equation = input.nextLine();
      
      //Generate expression tree
      BinaryNode<String> root = generateExpressionTree(equation);
      
      //Print the expression tree's version of the equation (mostly for testing)
      System.out.println("\nEquation reformatted: ");
      ExpressionTreeParser.print(root);
      System.out.println("\n");
      
      //Calculate and print the answer to the equation!
      double answer = ExpressionTreeParser.evaluate(root);
      System.out.println("Answer: " + answer);
   }
   
   
   /**
      Converts a String from infix notation to an Expression Tree using
          a modified Shunting Yard algorithm.
      Valid operators include "+", "-", "*", "/"
      Valid operands include any one-digit number ("1", "2", etc)
      Example: (1+2)*(3+4) becomes:
      
                   *
              +         -
            1  2       3  4
            
      Optional challenge: throw IllegalArgumentException for invalid inputs. Example: 1++2
      Optional challenge: handle ^ operator (exponents)
      Optional challenge: handle multi-digit numbers. Example: (12+15)*390
      Optional challenge: handle negative numbers. Example: (-3 * 5)
      
      @param expression the infix-formatted String expression
   */
   
   private static boolean precedence(char C, char topStack) {
   
      if ((C == '+' || C == '-') && (topStack == '*' || topStack == '/' || topStack == '-' || topStack == '+')) {
      
         return true;
      
      }
      else if ((C == '*' || C == '/') && (topStack == '*' || topStack == '/')) {
      
         return true;
      
      }
      
      return false;
   
   }
   
   public static BinaryNode<String> generateExpressionTree(String expression)
   {
      
      Stack<String> operators = new Stack<>();
      Stack<BinaryNode<String>> operands = new Stack<>();
      //General algorithm:
      //    Create two stacks: one for operands (strings), and one for operator (nodes).
      //    for each token in the string:
      //       if it's a number, push to the operands stack
      //       if it's an operator +-*/:
      //          while the top of the operator stack has same or higher precedence than the token:
      //                combine the top 2 operands using that operator
      //          add the operator to the operators stack
      //       if it's a (, push to the operators stack
      //       if it's a ):
      //          while we haven't hit a ( yet:
      //             combine the top 2 operands using the operator at the top of the operators stack
      //          remove the (
      //       if it's a space, ignore it
      //       anything else, throw an IllegalArgumentException for invalid character
      //    at the end, go through the remaining operator stack and combine all remaining nodes
      
      boolean expectingNum = true;      
      
      for (int i = 0; i < expression.length(); i++) {
                  
         char C = expression.charAt(i);
         
         if (expectingNum) {
            
            if (C >= '0' && C <= '9') {
         
               operands.push(new BinaryNode<String>(Character.toString(C)));
               expectingNum = false;
            
            }
            else if (C == '-') {
            
               operands.push(new BinaryNode<String>("-" + expression.charAt(i+1)));
               i++;
               sh.p("nums: " + operands);
               sh.p("ops: " + operators);
               expectingNum = false;
            
            }
            else if (C == '(') {
            
               operators.push("(");
               expectingNum = true;
            
            }
                     
         }
         else if (C == ')') {
                          
            while (!operators.isEmpty() && !operators.peek().equals("(")) { 
               
               sh.p("yayyy: " + operands);              
               
               BinaryNode<String> newNode = new BinaryNode<String>(operators.pop());
               newNode.setRight(operands.pop());
               newNode.setLeft(operands.pop());
               operands.push(newNode);
            
            }
            
            operators.pop();
                  
         }
         else if (!operators.empty()) {
                        
            while (!operators.empty() && precedence(C, operators.peek().charAt(0))) {
                              
               BinaryNode<String> newNode = new BinaryNode<String>(operators.pop());
               newNode.setRight(operands.pop());
               newNode.setLeft(operands.pop());
               operands.push(newNode);
                           
            }
            
            operators.push("" + C);
                     
         }
         else {
            
            operators.push("" + C);
            sh.p("pain: " + operators.peek());
            expectingNum = true;
                     
         }
         
      }
      
      while (!operators.isEmpty()) {
         
         sh.p("nums: " + operands);
         sh.p("ops: " + operators);
         
         BinaryNode<String> newNode = new BinaryNode<String>(operators.peek());
         newNode.setRight(operands.pop());
         newNode.setLeft(operands.pop());
         operands.push(newNode);
         operators.pop();
      
      }
   
      return operands.pop();
   
   }
         
}

