/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Website: https://github.com/MinhasKamal/Intellectron     *
* License:  GNU General Public License version-3           *
***********************************************************/

package com.minhaskamal.neuralnetwork;

import java.util.LinkedList;

public class NeuralNetwork {
	LinkedList<NeuronLayer> neuronLayers;
	
	public NeuralNetwork(int[] numbersOfNeuronsInLayers, int numberOfInputs) {
		neuronLayers = new LinkedList<NeuronLayer>();
		
		neuronLayers.add(new NeuronLayer(numbersOfNeuronsInLayers[0], numberOfInputs));
		for(int i=1; i<numbersOfNeuronsInLayers.length; i++){
			neuronLayers.add(new NeuronLayer(numbersOfNeuronsInLayers[i], neuronLayers.getLast()));
		}
	}
	
	public void passInput(double[] inputs){
		neuronLayers.getFirst().process(inputs);
		
		for(int i=1; i<neuronLayers.size(); i++){
			neuronLayers.get(i).process(neuronLayers.get(i-1).getOutputs());
		}
	}
	
	public double[] getOutputs(){
		
		return neuronLayers.getLast().getOutputs();
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
