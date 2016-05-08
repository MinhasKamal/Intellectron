/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Website: https://github.com/MinhasKamal/Intellectron     *
* License:  GNU General Public License version-3           *
***********************************************************/

package test;

import com.minhaskamal.neuralnetwork.NeuralNetwork;

public class NeuralNetworkTest {
	public static void main(String[] args) {
		int[] numbersOfNeuronsInLayers = new int[]{30, 6, 2};
		NeuralNetwork neuralNetwork = new NeuralNetwork(numbersOfNeuronsInLayers, 5);
		
		double[] inputs = new double[]{0.2, 0.3, 0.1, 0.6, 0.2};
		neuralNetwork.passInput(inputs);
		
		double[] outputs = neuralNetwork.getOutputs();
		for(int i=0; i<outputs.length; i++){
			System.out.println(outputs[i]);
		}
		
		//System.out.println(neuralNetwork.toString());
	}
}
