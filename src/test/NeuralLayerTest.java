/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Website: https://github.com/MinhasKamal/Intellectron     *
* License: MIT License                                     *
***********************************************************/

package test;

import com.minhaskamal.intellectron.neuralnetworks.neuronLayers.NeuronLayer;

public class NeuralLayerTest {
	public static void main(String[] args) {
		NeuronLayer neuronLayer = new NeuronLayer(3, 3, 0.1);
		
		double[] inputs = new double[]{0.3, 0.2, 0.99, 1};//last one is bias
		neuronLayer.processForward(inputs);
		
		double[] outputs = neuronLayer.getOutputs();
		for(int i=0; i<outputs.length; i++){
			System.out.print(outputs[i]+", ");
		}
		
		System.out.println(neuronLayer.dump());
	}
}
