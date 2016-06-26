package test;

import com.minhaskamal.intellectron.neuralnetworks.DeepNeuralNetwork;

public class DeepNeuralNetworkTest {
	public static void main(String[] args) {
		int[] numbersOfNeuronsInLayers = new int[]{4, 2, 3, 1};
		DeepNeuralNetwork neuralNetwork = new DeepNeuralNetwork(numbersOfNeuronsInLayers, 0.1, 2);
		
		//input only, bias is handled internally//
		double[][] inputs = new double[][]{
			{0, 0},
			{0, 1},
			{1, 0},
			{1, 1}
		};
		//output//
		double[][] outputs = new double[][]{
			{1},
			{0},
			{0},
			{1}
		};
		
//		System.out.println(neuralNetwork.dump());
		
		//training
		for(int cycle=0; cycle<50000; cycle++){
			for(int i=0; i<inputs.length; i++){
				
				neuralNetwork.train(inputs[i], outputs[i]);
				
				if(cycle%1000==0){
					double[] o = neuralNetwork.getOutputs();
					for(int p=0; p<o.length; p++){
						System.out.print(o[p]+", ");
					}
				}
			}
			if(cycle%1000==0){
				System.out.println("#cycle->" + cycle);
			}
		}
		
//		System.out.println(neuralNetwork.dump());
	}
}
