package com.minhaskamal.intellectron;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.minhaskamal.intellectron.neuralnetworks.MultiLayerNeuralNetwork;
import com.minhaskamal.intellectron.util.*;

public class MultiLayerNeuralNetworkImplementation {
	
	private MultiLayerNeuralNetwork neuralNetwork;
	
	public MultiLayerNeuralNetworkImplementation(int[] numbersOfNeuronsInLayers, double learningRateOfAllLayers, int numberOfInputs) {
		this.neuralNetwork = new MultiLayerNeuralNetwork(numbersOfNeuronsInLayers, learningRateOfAllLayers, numberOfInputs);
	}
	
	public MultiLayerNeuralNetworkImplementation(String filePath) {
		try {
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(filePath));
			NodeList nodeList = document.getElementsByTagName(MultiLayerNeuralNetwork.MULTI_LAYER_NEURAL_NETWORK_TAG);
	        Node neuralNetworkNode = nodeList.item(0);
			this.neuralNetwork = new MultiLayerNeuralNetwork(neuralNetworkNode);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//TRAIN/////////////////////////////////////////////////////////////
	
	public void train(double[][] inputs, double[][] outputs){
		for(int i=0; i<inputs.length; i++){
			train(inputs[i], outputs[i]);
		}
	}
	
	public void train(double[] input, double[] output){
		this.neuralNetwork.train(input, output);
	}
	
	//TEST//////////////////////////////////////////////////////////////
	
	public double test(double[][] inputs, double[][] outputs, double tolerance){
		double accuracy = 0;
		
		for(int i=0; i<inputs.length; i++){
			double[] predictedOutput = predict(inputs[i]);
			
			if(NeuralNetworkUtils.isEqual(predictedOutput, outputs[i], tolerance)){
				accuracy++;
			}
		}
		
		accuracy = accuracy/inputs.length;
		
		return accuracy;
	}
	
	//PREDICT///////////////////////////////////////////////////////////
	
	public double[] predict(double[] input){
		this.neuralNetwork.processForward(input);
		return neuralNetwork.getOutputs();
	}
	
	//DATA_GENERATION///////////////////////////////////////////////////
	
	public double[] generate(double[] seed){
		return neuralNetwork.processBackward(seed);
	}
	
	//NETWORK_DUMP//////////////////////////////////////////////////////
	
	public void dump(String filePath){
		try {
			FileIO.writeWholeFile(filePath, this.neuralNetwork.dump());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
