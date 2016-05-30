/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Website: https://github.com/MinhasKamal/Intellectron     *
* License:  GNU General Public License version-3           *
***********************************************************/

package com.minhaskamal.intellectron.neuralnetworks.neuronLayers;

import java.util.LinkedList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.minhaskamal.intellectron.neuralnetworks.neuronLayers.neuron.*;

public class NeuronLayer {
	public double learningRate;
	private LinkedList<Neuron> neurons;
	private double[] errors;
	private double[] outputs;
	
	public static final String NEURON_LAYER_TAG = "neuron-layer";
	public static final String LEARNING_RATE_TAG = "learning-rate";
	
	/**
	 * 
	 * @param numberOfNeurons
	 * @param numberOfDendritesInEachNeuron without bias neuron
	 * @param learningRate
	 */
	public NeuronLayer(int numberOfNeurons, int numberOfDendritesInEachNeuron, double learningRate) {
		this.learningRate = learningRate;
		
		numberOfDendritesInEachNeuron++;//one each for bias input
		this.neurons = new LinkedList<Neuron>();
		for(int i=0; i<numberOfNeurons; i++){
			this.neurons.add(new Neuron(numberOfDendritesInEachNeuron));
		}
		
		this.errors = new double[this.neurons.size()];
		this.outputs = new double[this.neurons.size()+1];//with bias output
	}
	
	public NeuronLayer(int numberOfNeurons, NeuronLayer previousNeuronLayer, double learningRate) {
		this(numberOfNeurons, previousNeuronLayer.neurons.size(), learningRate);
	}
	
	public NeuronLayer(Node neuronLayerNode) {
		this.neurons = new LinkedList<Neuron>();
		
		NodeList neuronNodeList = neuronLayerNode.getChildNodes();
		for(int i=0; i<neuronNodeList.getLength(); i++){
			if(neuronNodeList.item(i).getNodeName()==NeuronLayer.LEARNING_RATE_TAG){
				this.learningRate = Double.parseDouble(neuronNodeList.item(i).getTextContent());
				break;
			}
		}
		for(int i=0; i<neuronNodeList.getLength(); i++){
			Node neuronNode = neuronNodeList.item(i);
			if(neuronNode.getNodeName()==Neuron.NEURON_TAG){
				this.neurons.add(new Neuron(neuronNode));
			}
		}
		
		this.errors = new double[this.neurons.size()];
		this.outputs = new double[this.neurons.size()+1];//with bias output
	}
	
	///////////////////////////////PROCESS//////////////////////////////////
	
	public void processForward(double[] inputs){
		for(int i=0; i<outputs.length-1; i++){
			this.outputs[i] = neurons.get(i).processSignalForward(inputs);
		}
		this.outputs[outputs.length-1] = 1;	//bias
	}
	
	public double[] processBackward(){
		double[] meanGeneratedSignal = new double[this.neurons.get(0).getNumberOfDendrites()];
		
		for(int i=0; i<outputs.length-1; i++){ //excluding bias
			double[] generatedSignals = neurons.get(i).processSignalBackward(outputs[i]);
			for(int j=0; j<meanGeneratedSignal.length; j++){
				meanGeneratedSignal[j] += generatedSignals[j]; //integrating
			}
		}
		
		for(int i=0; i<meanGeneratedSignal.length; i++){
			meanGeneratedSignal[i] /= this.neurons.size(); //meaning
			
			if(meanGeneratedSignal[i]>=1){ //normalization
				meanGeneratedSignal[i]=0.9999999;
			}else if(meanGeneratedSignal[i]<=0){
				meanGeneratedSignal[i]=0.0000001;
			}
		}
		
		return meanGeneratedSignal;
	}
	
	public double[] getOutputs(){
		return this.outputs;
	}
	
	public void setOutputs(double[] outputs){
		this.outputs = outputs.clone();
	}
	
	/////////////////////////////ERROR CALCULATION///////////////////////////////
	
	public void calculateErrors(double[] expectedOutputs){
		for(int i=0; i<this.errors.length; i++){
			this.errors[i] = outputs[i]*(1-outputs[i])*(expectedOutputs[i]-outputs[i]);
		}
	}
	
	public void calculateErrors(NeuronLayer followingLayer){
		for(int i=0; i<this.errors.length; i++){
			double integratedEffectOnError=0;
			
			for(int j=0; j<followingLayer.errors.length; j++){
				integratedEffectOnError += followingLayer.errors[j]*followingLayer.neurons.get(j).getWeight(i);
			}
			
			this.errors[i] = outputs[i]*(1-outputs[i])*integratedEffectOnError;
		}
	}
	
	public double[] getErrors(){
		return this.errors;
	}
	
	///////////////////////////////RSET WEIGHTS///////////////////////////////
	
	public void learn(NeuronLayer previousLayer){
		this.learn(previousLayer.outputs);
	}
	
	public void learn(double[] previousInputSignals){
		int i=0;
		for(Neuron neuron: this.neurons){
			neuron.learn(this.errors[i]*this.learningRate, previousInputSignals);
			i++;
		}
	}
	
	//////////////////////////////KNOWLEDGE STORE/////////////////////////////////
	
	public String dump(){
		String string = "<"+NEURON_LAYER_TAG+">\n";
		
		string+= "<"+LEARNING_RATE_TAG+">"+this.learningRate+"</"+LEARNING_RATE_TAG+">\n";
		
		for(Neuron neuron: this.neurons){
			string += neuron.dump()+"\n";
		}
		
		string += "</"+NEURON_LAYER_TAG+">";
		
		return string;
	}
}
