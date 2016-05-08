/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Website: https://github.com/MinhasKamal/Intellectron     *
* License:  GNU General Public License version-3           *
***********************************************************/

package com.minhaskamal.intellectron.neuralnetwork.neuron;

import java.util.LinkedList;

public class Neuron {
	private LinkedList<Dendrite> dendrites;
	
	public static final String NEURON_TAG = "neuron";
	
	public Neuron(LinkedList<Dendrite> dendrites) {
		this.dendrites = dendrites;
	}
	
	public Neuron(String string) {
		load(string);
	}
	
	public Neuron(int numberOfDendrites) {
		this(new LinkedList<Dendrite>());
		
		for(int i=0; i<numberOfDendrites; i++){
			this.dendrites.add(new Dendrite());
		}
	}
	
	/**
	 * feed forward - sigmoid
	 * @param inputSignals
	 * @return output of the process
	 */
	public double processSignal(double[] inputSignals){
		double signalWeightSum = 0;
		
		for(int i=0; i<inputSignals.length; i++){
			signalWeightSum += this.dendrites.get(i).catchSignal(inputSignals[i]);
		}
		
		double outputSignal = 1 / ( 1 + Math.exp(-signalWeightSum) );
		
		return outputSignal;
	}
	
	/**
	 * Resets all the weights of dendrites of a neuron
	 * @param errorRate errorRate = - learningRate(eta) * delta
	 * @param previousInputSignals
	 */
	public void learn(double errorRate, double[] previousInputSignals){
		for(int i=0; i<previousInputSignals.length; i++){
			this.dendrites.get(i).updateWeight(errorRate*previousInputSignals[i]);
		}
	}
	
	public double getWeight(int dendriteNumber){
		return this.dendrites.get(dendriteNumber).getWeight();
	}
	
	private void load(String string){
		int startIndex = string.indexOf(NEURON_TAG);
		startIndex = string.indexOf('>', startIndex+NEURON_TAG.length()) + 1 + 1;
		int stopIndex = string.indexOf("</"+NEURON_TAG, startIndex);
		
		String[] weightStrings = string.substring(startIndex, stopIndex).split("\n");
		
		dendrites = new LinkedList<Dendrite>();
		for(int i=0; i<weightStrings.length; i++){
			this.dendrites.add(new Dendrite(weightStrings[i]));
		}
	}
	
	public String toString(){
		String string = "<"+NEURON_TAG+">\n";
		
		for(Dendrite dendrite: this.dendrites){
			string += dendrite.toString()+"\n";
		}
		
		string += "</"+NEURON_TAG+">";
		
		return string;
	}
}
