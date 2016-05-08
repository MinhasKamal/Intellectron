/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Website: https://github.com/MinhasKamal/Intellectron     *
* License:  GNU General Public License version-3           *
***********************************************************/

package test;

import com.minhaskamal.intellectron.neuralnetwork.NeuralNetwork;

public class NeuralNetworkTest {
	public static void main(String[] args) {
		int[] numbersOfNeuronsInLayers = new int[]{4, 2, 1};
		NeuralNetwork neuralNetwork = new NeuralNetwork(numbersOfNeuronsInLayers, 0.1, 2);
		
		//input only, bias is handled internally//
		double[][] inputs = new double[][]{
			{0, 0},
			{0, 1},
			{1, 0},
			{1, 1}
		};
		//output//
		double[][] outputs = new double[][]{
			{0},
			{1},
			{1},
			{0}
		};
		
		System.out.println(neuralNetwork.toString());
		
		//training
		for(int cycle=0; cycle<100000; cycle++){
			for(int i=0; i<inputs.length; i++){
				
				neuralNetwork.process(inputs[i]);
				neuralNetwork.calculateErrors(outputs[i]);
				neuralNetwork.learn(inputs[i]);
				
				if(cycle%1000==0){
					double[] o = neuralNetwork.getOutputs();
					for(int p=0; p<o.length-1; p++){
						System.out.print(o[p]+" ");
					}
					System.out.print("#");
				}
			}
			if(cycle%1000==0){
				System.out.println("cycle->" + cycle);
			}
		}
		
		System.out.println(neuralNetwork.toString());
	}
}
