/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Website: https://github.com/MinhasKamal/Intellectron     *
* License:  GNU General Public License version-3           *
***********************************************************/

package com.minhaskamal.intellectron.neuralnetwork.neuron;

import java.util.Random;

public class Dendrite {
	private double weight;
	
	public static final String WEIGHT_TAG = "weight";
	
	public Dendrite(double weight) {
		this.weight = weight;
	}
	
	/**
	 * randomly generates weight between -0.5 & 0.5
	 */
	public Dendrite() {
		this(new Random().nextDouble()-0.5);
	}
	
	public double catchSignal(double signal){
		return signal*this.weight;
	}
	
	public void updateWeight(double corection){
		this.weight += corection;
	}
	
	public double getWeight(){
		return this.weight;
	}
	
	public String dump(){
		return "<"+WEIGHT_TAG+">"+this.weight+"</"+WEIGHT_TAG+">";
	}
}
