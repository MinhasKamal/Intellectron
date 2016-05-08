/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Website: https://github.com/MinhasKamal/Intellectron     *
* License:  GNU General Public License version-3           *
***********************************************************/

package com.minhaskamal.intellectron.neuralnetwork;

import java.util.LinkedList;

public class NeuralNetwork {
	LinkedList<NeuronLayer> neuronLayers;
	
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
	
	///////////////////////////////PROCESS//////////////////////////////////
	
	public void process(double[] inputs){
		inputs = addBias(inputs);
		
		neuronLayers.getFirst().process(inputs);
		for(int i=1; i<neuronLayers.size(); i++){
			neuronLayers.get(i).process(neuronLayers.get(i-1).getOutputs());
		}
	}
	
	//output includes bias
	public double[] getOutputs(){
		return neuronLayers.getLast().getOutputs();
	}
	
	/////////////////////////////ERROR CALCULATION///////////////////////////////
	
	public void calculateErrors(double[] expectedOutputs){
		this.neuronLayers.getLast().calculateErrors(expectedOutputs);
		for(int i=this.neuronLayers.size()-1; i>0; i--){
			this.neuronLayers.get(i-1).calculateErrors(this.neuronLayers.get(i)); //calculate error of i layer
		}
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
	
	public String toString(){
		String string = "<neural-network>\n";
		
		for(NeuronLayer neuronLayer: this.neuronLayers){
			string += neuronLayer.toString()+"\n";
		}
		
		string += "</neural-network>";
		
		return string;
	}
}
