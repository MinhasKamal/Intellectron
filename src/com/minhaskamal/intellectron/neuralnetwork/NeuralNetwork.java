/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Website: https://github.com/MinhasKamal/Intellectron     *
* License:  GNU General Public License version-3           *
***********************************************************/

package com.minhaskamal.intellectron.neuralnetwork;

import java.util.LinkedList;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NeuralNetwork {
	private LinkedList<NeuronLayer> neuronLayers;
	//public String neuralNetworkName;
	
	public static String NEURAL_NETWORK_TAG= "neural-network";
	
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
	
	public NeuralNetwork(Node neuralNetworkNode) {
		this.neuronLayers = new LinkedList<NeuronLayer>();
		
		NodeList neuronLayerNodeList = neuralNetworkNode.getChildNodes();
		for(int i=0; i<neuronLayerNodeList.getLength(); i++){
			Node neuronLayerNode = neuronLayerNodeList.item(i);
			if(neuronLayerNode.getNodeName()==NeuronLayer.NEURON_LAYER_TAG){
				this.neuronLayers.add(new NeuronLayer(neuronLayerNode));
			}
		}
	}
	
	///////////////////////////////PROCESS//////////////////////////////////
	
	public void processForward(double[] inputs){
		inputs = addBias(inputs);
		
		neuronLayers.getFirst().processForward(inputs);
		for(int i=1; i<neuronLayers.size(); i++){
			neuronLayers.get(i).processForward(neuronLayers.get(i-1).getOutputs());
		}
	}
	
	public double[] processBackward(double[] seed){
		seed = addBias(seed);
		
		neuronLayers.getLast().setOutputs(seed);
		for(int i=neuronLayers.size()-1; i>0; i--){
			neuronLayers.get(i-1).setOutputs(neuronLayers.get(i).processBackward());
		}
		double[] generatedSignal = neuronLayers.getFirst().processBackward();
		
		return removeBias(generatedSignal);
	}
	
	/**
	 * @return does not includes bias
	 */
	public double[] getOutputs(){
		return removeBias(neuronLayers.getLast().getOutputs());
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
	
	//////////////////////////////KNOWLEDGE STORE/////////////////////////////////
	
	public String dump(){
		String string = "<"+NEURAL_NETWORK_TAG+">\n";
		
		for(NeuronLayer neuronLayer: this.neuronLayers){
			string += neuronLayer.dump()+"\n";
		}
		
		string += "</"+NEURAL_NETWORK_TAG+">";
		
		return string;
	}
	
	//////
	
	private double[] addBias(double[] inputs){
		double[] inputsWithBias = new double[inputs.length+1];
		
		for(int i=0; i<inputs.length; i++){
			inputsWithBias[i] = inputs[i];
		}
		inputsWithBias[inputsWithBias.length-1] = 1;
		
		return inputsWithBias;
	}
	
	private double[] removeBias(double[] outputs){
		double[] outputsWithoutBias = new double[outputs.length-1];
		
		for(int i=0; i<outputsWithoutBias.length; i++){
			outputsWithoutBias[i] = outputs[i];
		}
		
		return outputsWithoutBias;
	}
}
