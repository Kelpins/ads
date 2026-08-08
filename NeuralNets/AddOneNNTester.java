import java.util.*;

public class AddOneNNTester
{
   public static void main(String[] args) {
      
      AddOneNN neuralNet = new AddOneNN(2, 0.000001);
      
      Map<Double, Double> training = new HashMap<>();
      for (double i = -1000; i <= 1000; i++) {
      
         training.put(i, i+1);
      
      }
      
      Map<Double, Double> testing = new HashMap<>();
      for (double i = 1000; i <= 2000; i++) {
      
         testing.put(i, i+1);
      
      }
      
      neuralNet.train(training, 200);
      System.out.println(neuralNet.test(testing));
      
        /*
         Create an AddOneNN object with 2 hidden nodes, and a learning rate of 10^-6.
         
         Create a Map<Double, Double> for your training data. It should map a valid input to its correct output.
         Add input data from -1000 to 1000 to your training data set. It should contain (-1000, -999) through (1000, 1001)
         Create a Map<Double, Double> for your testing data. It should map a valid input to its correct output.
         Add input data from 1000 to 2000 to your testing data set. It should contain (1000, 1001) through (2000, 2001)
         
         Call the train method to train your neural network with 20 epochs.
         Call the test method to test your neural network, and print its return value (the root mean squared error).
         */
         
         /*
            Testing your code:
            Before modifying AddOneNN, this should print zero.
            After completing everything except the train() function in AddOneNN, this should print a large error, around 1500.
            After completing the entirety of AddOneNN, this should print a small error, around 0.2.
         */
   }
}