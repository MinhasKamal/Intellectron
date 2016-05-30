package test;

import com.minhaskamal.intellectron.MultiLayerNeuralNetworkImplementation;


public class MultiLayerNeuralNetworkImplementationTest {
	public static void main(String[] args) {
		int[] numbersOfNeuronsInLayers = new int[]{4, 1};
		MultiLayerNeuralNetworkImplementation neuralNetworkImplementation = new MultiLayerNeuralNetworkImplementation(numbersOfNeuronsInLayers, 0.1, 2);
		
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
		
		int cycle = 10000;
		for(int i=0; i<cycle; i++){
			neuralNetworkImplementation.train(inputs, outputs);
		}
		
//		String workspace = System.getenv("SystemDrive") + System.getenv("HOMEPATH") + "\\Desktop\\";
//		neuralNetworkImplementation.dump(workspace+"knowledge.xml");
		
		//another NeuralNetworkImplementation//
//		NeuralNetworkImplementation neuralNetworkImplementation2 = new NeuralNetworkImplementation(workspace+"knowledge.xml");
//		double accuracy = neuralNetworkImplementation2.test(inputs, outputs, 0.1);
//		System.out.println("accuracy: " + accuracy);
		
		//back processing//
		double[] seed = new double[]{0.001};
		double[] data = neuralNetworkImplementation.generate(seed);
		for(int i=0; i<data.length; i++){
			System.out.print(data[i] + " ");
		}
	}
}
