/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Website: https://github.com/MinhasKamal/Intellectron     *
* License:  GNU General Public License version-3           *
***********************************************************/

package com.minhaskamal.intellectron.neuralnetwork.neuron;

import java.util.LinkedList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Neuron {
	private LinkedList<Dendrite> dendrites;
	
	public static final String NEURON_TAG = "neuron";
	
	public Neuron(LinkedList<Dendrite> dendrites) {
		this.dendrites = dendrites;
	}
	
	public Neuron(Node neuronNode) {
		this.dendrites = new LinkedList<Dendrite>();
		
		NodeList dendriteNodeList = neuronNode.getChildNodes();
		for(int i=0; i<dendriteNodeList.getLength(); i++){
			Node dendriteNode = dendriteNodeList.item(i);
			if(dendriteNode.getNodeName()==Dendrite.WEIGHT_TAG){
				this.dendrites.add(new Dendrite(Double.parseDouble(dendriteNode.getTextContent())));
			}
		}
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
	
	public String dump(){
		String string = "<"+NEURON_TAG+">\n";
		
		for(Dendrite dendrite: this.dendrites){
			string += dendrite.dump()+"\n";
		}
		
		string += "</"+NEURON_TAG+">";
		
		return string;
	}
}
