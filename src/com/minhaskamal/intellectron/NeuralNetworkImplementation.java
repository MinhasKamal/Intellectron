package com.minhaskamal.intellectron;

import com.minhaskamal.intellectron.neuralnetwork.NeuralNetwork;
import com.minhaskamal.intellectron.util.FileIO;

public class NeuralNetworkImplementation {
	
	private NeuralNetwork neuralNetwork;
	
	public NeuralNetworkImplementation(int[] numbersOfNeuronsInLayers, double learningRateOfAllLayers, int numberOfInputs) {
		this.neuralNetwork = new NeuralNetwork(numbersOfNeuronsInLayers, learningRateOfAllLayers, numberOfInputs);
	}
	
	public NeuralNetworkImplementation(String filePath) {
		try {
			this.neuralNetwork = new NeuralNetwork(FileIO.readWholeFile(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void train(double[][] inputs, double[][] outputs){
		for(int i=0; i<inputs.length; i++){
			
			this.neuralNetwork.process(inputs[i]);
			this.neuralNetwork.calculateErrors(outputs[i]);
			this.neuralNetwork.learn(inputs[i]);
		}
	}
	
	public double test(double[][] inputs, double[][] outputs){
		double accuracy = 0;
		double tolerance = 0.1;
		
		for(int i=0; i<inputs.length; i++){
			double[] predictedOutput = predict(inputs[i]);
			
			if(isEqual(predictedOutput, outputs[i], tolerance)){
				accuracy++;
			}
		}
		
		accuracy = accuracy/inputs.length;
		
		return accuracy;
	}
	
	private boolean isEqual(double[] predictedOutput, double[] expectedOutput, double tolerance){
		for(int i=0; i<predictedOutput.length; i++){
			double diffrence = Math.abs(predictedOutput[i]-expectedOutput[i]);
			if(diffrence>tolerance){
				return false;
			}
		}
		
		return true;
	}
	
	public double[] predict(double[] input){
		this.neuralNetwork.process(input);
		return neuralNetwork.getOutputs();
	}
	
	public void dump(String filePath){
		try {
			FileIO.writeWholeFile(filePath, this.neuralNetwork.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
