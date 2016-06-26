package com.minhaskamal.intellectron.neuralnetworks;

import java.util.LinkedList;

public class DeepNeuralNetwork extends NeuralNetwork{

	private LinkedList<MultiLayerNeuralNetwork> neuralNetworks;
	
	public DeepNeuralNetwork(int[] numbersOfNeuronsInLayers, double learningRateOfAllLayers, int numberOfInputs) {
		if(numbersOfNeuronsInLayers.length<3){
			return;
		}
		
		neuralNetworks = new LinkedList<MultiLayerNeuralNetwork>();
		
		neuralNetworks.add(new MultiLayerNeuralNetwork(new int[]{numbersOfNeuronsInLayers[0], numberOfInputs},
				learningRateOfAllLayers, numberOfInputs));	//input network
		for(int i=1; i<numbersOfNeuronsInLayers.length-2; i++){
			neuralNetworks.add(new MultiLayerNeuralNetwork(new int[]{numbersOfNeuronsInLayers[i], numbersOfNeuronsInLayers[i-1]},
					learningRateOfAllLayers, numbersOfNeuronsInLayers[i-1]));
		}
		neuralNetworks.add(new MultiLayerNeuralNetwork(new int[]{numbersOfNeuronsInLayers[numbersOfNeuronsInLayers.length-2],
				numbersOfNeuronsInLayers[numbersOfNeuronsInLayers.length-1]},
				learningRateOfAllLayers, numbersOfNeuronsInLayers[numbersOfNeuronsInLayers.length-3]));	//output network
	}
	
	/////////////////////////////////////////////////////
	
	public void processForward(double[] inputs){
		inputs = addBias(inputs);
		
		neuralNetworks.get(0).getLayer(0).processForward(inputs);
		for(int i=1; i<neuralNetworks.size()-1; i++){
			neuralNetworks.get(i).getLayer(0).processForward(neuralNetworks.get(i-1).getLayer(0).getOutputs());
		}
		neuralNetworks.get(neuralNetworks.size()-1).processForward(
			removeBias( neuralNetworks.get(neuralNetworks.size()-2).getLayer(0).getOutputs() ));
	}
	
	public double[] getOutputs(){
		return neuralNetworks.getLast().getOutputs();
	}
	
	//////////////////////////////////////////////////////////
	
	public void train(double[] inputs, double[] expectedOutputs){
		double[] input = inputs.clone();
		
		for(int i=0; i<this.neuralNetworks.size()-1; i++){
			this.neuralNetworks.get(i).processForward(input);
			this.neuralNetworks.get(i).calculateErrors(input);
			this.neuralNetworks.get(i).learn(input);
			
			input = removeBias(this.neuralNetworks.get(i).getLayer(0).getOutputs());
		}
		
		this.neuralNetworks.getLast().processForward(input);
		this.neuralNetworks.getLast().calculateErrors(expectedOutputs);
		this.neuralNetworks.getLast().learn(input);
	}
	
	
	
}



