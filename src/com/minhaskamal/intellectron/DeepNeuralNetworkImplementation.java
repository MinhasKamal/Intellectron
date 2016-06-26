package com.minhaskamal.intellectron;

import com.minhaskamal.intellectron.neuralnetworks.DeepNeuralNetwork;

public class DeepNeuralNetworkImplementation {
	private DeepNeuralNetwork deepNeuralNetwork;
	
	public DeepNeuralNetworkImplementation(int[] numbersOfNeuronsInLayers, double learningRateOfAllLayers, int numberOfInputs) {
		this.deepNeuralNetwork = new DeepNeuralNetwork(numbersOfNeuronsInLayers, learningRateOfAllLayers, numberOfInputs);
	}
	
	public double[] predict(double[] input){
		this.deepNeuralNetwork.processForward(input);
		return deepNeuralNetwork.getOutputs();
	}
	
	
}
