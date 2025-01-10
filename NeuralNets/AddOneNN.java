/*
   DO NOT START CODING THIS FILE UNTIL YOU HAVE COMPLETED AddOneNNTester.java!
*/

import java.util.*;

/**
   A neural network which is able to add one to a number. 
   Sample input: 1; sample output: 2.
*/
class AddOneNN {
   /* Declare instance variables: 
      an NNLayer for your hidden layer; 
      an NNNode for your output node; and
      a double for your learning rate */
    
    private NNLayer hiddenLayer;
    private NNNode outNode;
    private double learningRate;

    public AddOneNN(int numHiddenNodes, double learningRate) {
      
      this.hiddenLayer = new NNLayer(numHiddenNodes, 1);
      this.outNode = new NNNode(numHiddenNodes);
      this.learningRate = learningRate;
      
    }
    
    private double[] feedForward(Map<Double, Double> data) {
      
      int numHiddenNodes = this.hiddenLayer.getNodes().size();
      double error = 0.0;
      double[] hiddenActivations = new double[numHiddenNodes+1];
      
      for (double key : data.keySet()) {
         
         double expectedOut = data.get(key);
         double predictedOut = 0.0;
         
         for (int i = 0; i < numHiddenNodes; i++) {
         
            NNNode node = this.hiddenLayer.getNodes().get(i);
            
            double activation = (key * node.getWeight(0) + node.getBias());
            hiddenActivations[i] = activation;
            
            predictedOut += (activation * this.outNode.getWeight(i));
         
         }
         
         predictedOut += this.outNode.getBias();
         
         error += Math.pow((predictedOut-expectedOut), 2);
      
      }
            
      error = Math.sqrt(error / 1000);
      hiddenActivations[numHiddenNodes] = error;
      
      return hiddenActivations;
    
    }

    public double test(Map<Double, Double> testData) {
      
      return feedForward(testData)[this.hiddenLayer.getNodes().size()];
    
    }

    public void train(Map<Double, Double> trainingData, int epochs) {
       
       int numHiddenNodes = this.hiddenLayer.getNodes().size();
       
       for (int e = 0; e < epochs; e++) {
         
         double[] hiddenActivations = new double[numHiddenNodes];
         
         for (double key : trainingData.keySet()) {
            
            double expectedOut = trainingData.get(key);
            double actualOut = 0.0;
            double error = 0.0;
            
            //feed forward
            for (int i = 0; i < numHiddenNodes; i++) {
               NNNode node = this.hiddenLayer.getNodes().get(i);
               double activation = (key * node.getWeight(0) + node.getBias());
               hiddenActivations[i] = activation;
               actualOut += (activation * this.outNode.getWeight(i));
            }
            actualOut += this.outNode.getBias();
            
            //calculate error
            error = expectedOut - actualOut;
            
            //backpropogate
            this.outNode.setBias(this.outNode.getBias() + (this.learningRate * error));
            
            for (int i = 0; i < numHiddenNodes-1; i++) {
            
               this.outNode.setWeight(i, this.outNode.getWeight(i) + this.learningRate * error * hiddenActivations[i]);
               NNNode node = this.hiddenLayer.getNodes().get(i);
               node.setBias(node.getBias() + learningRate * error * this.outNode.getWeight(i));
               node.setWeight(0, node.getWeight(0) + this.learningRate * error * this.outNode.getWeight(i) * key);
            
            }
            
         }
       
       }
       
    }

    
    
    
   
   /*
      Done with training and testing?  Check with your teacher to see if it works correctly!
      
      Then, try some optional challenges:
      
      1. Adjust your "hyperparameters"! What happens if you increase or decrease: 
            The learning rate? 
            The number of neurons in the hidden layer?
            The number of epochs?
            The way biases and weights are initialized in NNNode.java?
         More specifically, can you get your test error down to 0.5? 0.1? 0.01?
      
      2. Add an activation function. Google (or ask ChatGPT) for more information!
         Note: this requires some calculus to fully understand, but you can use it
               without understanding the calculus part.
   */
    
}
