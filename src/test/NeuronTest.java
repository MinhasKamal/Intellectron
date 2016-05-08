/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Website: https://github.com/MinhasKamal/Intellectron     *
* License:  GNU General Public License version-3           *
***********************************************************/

package test;

import com.minhaskamal.neuralnetwork.neuron.Neuron;

public class NeuronTest {
	public static void main(String[] args) throws Exception {
		
		//layer-1//
//		Neuron layer_1_neuron_1 = new Neuron(new LinkedList<Dendrite>( Arrays.asList(new Dendrite(1)) ));
//		Neuron layer_1_neuron_2 = new Neuron(new LinkedList<Dendrite>( Arrays.asList(new Dendrite(1)) ));
//		Neuron layer_1_bias_neuron = new Neuron(new LinkedList<Dendrite>( Arrays.asList(new Dendrite(1)) ));
		
		Neuron neuron = new Neuron(3);

		//input & bias//
		double[][] input = new double[][]{
			{0, 0, 1},
			{0, 1, 1},
			{1, 0, 1},
			{1, 1, 1}
		};
		//output//
		double[] output = new double[]{
			0,
			1,
			1,
			1
		};
		
		//processing with gradient descent//
		double error;
		double learningRate = 1;
		for(int cycle=0; cycle<100; cycle++){
			for(int i=0; i<input.length; i++){
				neuron.processSignal(input[i]);
				
				double o = neuron.getOutput();
				
				error = output[i]-o;
				neuron.learn(error*learningRate, input[i]);
				
				System.out.print(o + ", ");
			}
			System.out.println(" -> "+cycle);
		}
		
		System.out.println(neuron.toString());
	}
}
