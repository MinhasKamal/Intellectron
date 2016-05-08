/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Website: https://github.com/MinhasKamal/Intellectron     *
* License:  GNU General Public License version-3           *
***********************************************************/

package com.minhaskamal.neuralnetwork.neuron;

import java.util.LinkedList;

public class Neuron {
	private LinkedList<Dendrite> dendrites;
	private double outputSignal;
	
	public Neuron(LinkedList<Dendrite> dendrites) {
		this.dendrites = dendrites;
	}
	
	public Neuron(int numberOfDendrites) {
		this(new LinkedList<Dendrite>());
		
		for(int i=0; i<numberOfDendrites; i++){
			dendrites.add(new Dendrite());
		}
	}
	
	public void processSignal(double[] inputSignals){	//feed forward
		double signalWeightSum = 0;
		
		for(int i=0; i<inputSignals.length; i++){
			signalWeightSum += this.dendrites.get(i).catchSignal(inputSignals[i]);
		}
		
		this.outputSignal = 1 / ( 1 + Math.exp(-signalWeightSum) );
	}
	
	public void learn(double errorRate, double[] previousInputSignals){
		for(int i=0; i<previousInputSignals.length; i++){
			this.dendrites.get(i).updateWeight(errorRate*previousInputSignals[i]);
		}
	}
	
	public double getOutput(){
		return outputSignal;
	}
	
	public String toString(){
		String string = "<neuron>\n";
		
		for(Dendrite dendrite: this.dendrites){
			string += dendrite.toString()+"\n";
		}
		
		string += "</neuron>";
		
		return string;
	}
}
