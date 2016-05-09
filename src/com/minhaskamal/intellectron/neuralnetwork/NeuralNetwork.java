/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Website: https://github.com/MinhasKamal/Intellectron     *
* License:  GNU General Public License version-3           *
***********************************************************/

package com.minhaskamal.intellectron.neuralnetwork;

import java.util.LinkedList;

public class NeuralNetwork {
	private LinkedList<NeuronLayer> neuronLayers;
	//public String neuralNetworkName;
	
	private String NEURAL_NETWORK_TAG= "neural-network";
	
	/**
	 * @param numbersOfNeuronsInLayers numbersOfNeuronsInLayers.length is the number of layers in the network, the last layer's 
	 * neuron number is the number of outputs
	 * @param numberOfInputs 
	 */
	public NeuralNetwork(int[] numbersOfNeuronsInLayers, double[] learningRatesOfTheLayers, int numberOfInputs) {
		neuronLayers = new LinkedList<NeuronLayer>();
		
		neuronLayers.add(new NeuronLayer(numbersOfNeuronsInLayers[0], numberOfInputs, learningRatesOfTheLayers[0]));
		for(int i=1; i<numbersOfNeuronsInLayers.length; i++){
			neuronLayers.add(new NeuronLayer(numbersOfNeuronsInLayers[i], neuronLayers.getLast(), learningRatesOfTheLayers[i]));
		}
	}
	
	public NeuralNetwork(int[] numbersOfNeuronsInLayers, double learningRateOfAllLayers, int numberOfInputs) {
		neuronLayers = new LinkedList<NeuronLayer>();
		
		neuronLayers.add(new NeuronLayer(numbersOfNeuronsInLayers[0], numberOfInputs, learningRateOfAllLayers));
		for(int i=1; i<numbersOfNeuronsInLayers.length; i++){
			neuronLayers.add(new NeuronLayer(numbersOfNeuronsInLayers[i], neuronLayers.getLast(), learningRateOfAllLayers));
		}
	}
	
	public NeuralNetwork(String string) {
		load(string);
	}
	
	///////////////////////////////PROCESS//////////////////////////////////
	
	public void process(double[] inputs){
		inputs = addBias(inputs);
		
		neuronLayers.getFirst().process(inputs);
		for(int i=1; i<neuronLayers.size(); i++){
			neuronLayers.get(i).process(neuronLayers.get(i-1).getOutputs());
		}
	}
	
	/**
	 * @return does not includes bias
	 */
	public double[] getOutputs(){
		return removeBias(neuronLayers.getLast().getOutputs());
	}
	
	private double[] removeBias(double[] outputs){
		double[] outputsWithoutBias = new double[outputs.length-1];
		
		for(int i=0; i<outputsWithoutBias.length; i++){
			outputsWithoutBias[i] = outputs[i];
		}
		
		return outputsWithoutBias;
	}
	
	/////////////////////////////ERROR CALCULATION///////////////////////////////
	
	public void calculateErrors(double[] expectedOutputs){
		this.neuronLayers.getLast().calculateErrors(expectedOutputs);
		for(int i=this.neuronLayers.size()-1; i>0; i--){
			this.neuronLayers.get(i-1).calculateErrors(this.neuronLayers.get(i)); //calculate error of i layer
		}
	}
	
	public double[] getErrors(){
		return this.neuronLayers.getLast().getErrors();
	}
	
	/////////////////////////////LEARN///////////////////////////////
	
	public void learn(double[] inputs){
		inputs = addBias(inputs);
		
		for(int i=this.neuronLayers.size()-1; i>0; i--){
			this.neuronLayers.get(i).learn(this.neuronLayers.get(i-1)); //learn i+1 layer
		}
		this.neuronLayers.getFirst().learn(inputs);
	}
	
	private double[] addBias(double[] inputs){
		double[] inputsWithBias = new double[inputs.length+1];
		
		for(int i=0; i<inputs.length; i++){
			inputsWithBias[i] = inputs[i];
		}
		inputsWithBias[inputsWithBias.length-1] = 1;
		
		return inputsWithBias;
	}
	
	//////////////////////////////KNOWLEDGE LOAD-STORE/////////////////////////////////
	//TODO not workable
	private void load(String string){
		int startIndex = string.indexOf(NEURAL_NETWORK_TAG);
		startIndex = string.indexOf('>', startIndex+NEURAL_NETWORK_TAG.length()) + 1 + 1;
		int stopIndex = string.indexOf("</"+NEURAL_NETWORK_TAG, startIndex);
		
		String[] neuronStrings = string.substring(startIndex, stopIndex).split("\n");
		
		this.neuronLayers = new LinkedList<NeuronLayer>();
		for(int i=0; i<neuronStrings.length; i++){
			this.neuronLayers.add(new NeuronLayer(neuronStrings[i]));
		}
	}
	
	public String toString(){
		String string = "<"+NEURAL_NETWORK_TAG+">\n";
		
		for(NeuronLayer neuronLayer: this.neuronLayers){
			string += neuronLayer.toString()+"\n";
		}
		
		string += "</"+NEURAL_NETWORK_TAG+">";
		
		return string;
	}
}
