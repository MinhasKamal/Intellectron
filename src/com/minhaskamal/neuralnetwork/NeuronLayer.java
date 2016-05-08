/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Website: https://github.com/MinhasKamal/Intellectron     *
* License:  GNU General Public License version-3           *
***********************************************************/

package com.minhaskamal.neuralnetwork;

import java.util.LinkedList;
import com.minhaskamal.neuralnetwork.neuron.Neuron;

public class NeuronLayer {
	private LinkedList<Neuron> neurons;
	
	public NeuronLayer(LinkedList<Neuron> neurons){
		this.neurons = neurons;
	}
	
	public NeuronLayer(int numberOfNeurons, int numberOfDendritesInEachNeuron) {
		this(new LinkedList<Neuron>());
		
		for(int i=0; i<numberOfNeurons; i++){
			neurons.add(new Neuron(numberOfDendritesInEachNeuron));
		}
	}
	
	public NeuronLayer(int numberOfNeurons, NeuronLayer previousNeuronLayer) {
		this(numberOfNeurons, previousNeuronLayer.neurons.size());
	}
	
	public void process(double[] inputs){
		
		for(Neuron neuron: neurons){
			neuron.processSignal(inputs);
		}
	}
	
	public double[] getOutputs(){
		double[] outputs = new double[neurons.size()];
		
		for(int i=0; i<outputs.length; i++){
			outputs[i] = neurons.get(i).getOutput();
		}
		
		return outputs;
	}
	
	public String toString(){
		String string = "<neuron-layer>\n";
		
		for(Neuron neuron: this.neurons){
			string += neuron.toString()+"\n";
		}
		
		string += "</neuron-layer>";
		
		return string;
	}
}
