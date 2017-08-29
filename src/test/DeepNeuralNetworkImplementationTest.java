/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Website: https://github.com/MinhasKamal/Intellectron     *
* License: MIT License                                     *
***********************************************************/

package test;

import com.minhaskamal.intellectron.DeepNeuralNetworkImplementation;

public class DeepNeuralNetworkImplementationTest {
	public static void main(String[] args) {
		int[] numbersOfNeuronsInLayers = new int[]{4, 3, 2, 1};
		DeepNeuralNetworkImplementation deepNeuralNetworkImplementation = new DeepNeuralNetworkImplementation(numbersOfNeuronsInLayers, 0.1, 2);
		
		//input only, bias is handled internally//
		double[][] inputs = new double[][]{
			{0, 0}, {0, 1}, {1, 0}, {1, 1}
		};
		//output//
		double[][] outputs = new double[][]{
			{1}, {0}, {0}, {1}
		};
		
		int cycle = 15000;
		for(int i=0; i<cycle; i++){
			deepNeuralNetworkImplementation.train2(inputs, outputs);
			if(i%100==0){
				System.out.println("accuracy: " + deepNeuralNetworkImplementation.test(inputs, outputs, 0.1));
			}
		}
		
//		String workspace = System.getenv("SystemDrive") + System.getenv("HOMEPATH") + "\\Desktop\\";
//		deepNeuralNetworkImplementation.dump(workspace+"knowledge.xml");
		
		//another NeuralNetworkImplementation//
//		DeepNeuralNetworkImplementation deepNeuralNetworkImplementationn2 = new DeepNeuralNetworkImplementation(workspace+"knowledge.xml");
//		double accuracy = deepNeuralNetworkImplementationn2.test(inputs, outputs, 0.1);
//		System.out.println("accuracy: " + accuracy);
		
		//back processing//
//		double[] seed = new double[]{0.001};
//		double[] data = neuralNetworkImplementation.generate(seed);
//		for(int i=0; i<data.length; i++){
//			System.out.print(data[i] + " ");
//		}
	}
}
