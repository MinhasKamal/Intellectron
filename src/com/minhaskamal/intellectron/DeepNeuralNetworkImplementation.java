/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Website: https://github.com/MinhasKamal/Intellectron     *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.intellectron;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.minhaskamal.intellectron.neuralnetworks.DeepNeuralNetwork;
import com.minhaskamal.intellectron.util.FileIO;
import com.minhaskamal.intellectron.util.NeuralNetworkUtils;

public class DeepNeuralNetworkImplementation {
	private DeepNeuralNetwork deepNeuralNetwork;
	
	public DeepNeuralNetworkImplementation(int[] numbersOfNeuronsInLayers, double learningRateOfAllLayers, int numberOfInputs) {
		this.deepNeuralNetwork = new DeepNeuralNetwork(numbersOfNeuronsInLayers, learningRateOfAllLayers, numberOfInputs);
	}
	
	public DeepNeuralNetworkImplementation(String filePath) {
		try {
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(filePath));
			NodeList nodeList = document.getElementsByTagName(DeepNeuralNetwork.DEEP_NEURAL_NETWORK_TAG);
	        Node neuralNetworkNode = nodeList.item(0);
			this.deepNeuralNetwork = new DeepNeuralNetwork(neuralNetworkNode);
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
		this.deepNeuralNetwork.train(input, output);
	}
	
	public void train2(double[][] inputs, double[][] outputs){
		for(int i=0; i<inputs.length; i++){
			train2(inputs[i], outputs[i]);
		}
	}
	
	public void train2(double[] input, double[] output){
		this.deepNeuralNetwork.train2(input, output);
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
		this.deepNeuralNetwork.processForward(input);
		return this.deepNeuralNetwork.getOutputs();
	}
	
	//NETWORK_DUMP//////////////////////////////////////////////////////
	
	public void dump(String filePath){
		try {
			FileIO.writeWholeFile(filePath, this.deepNeuralNetwork.dump());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
