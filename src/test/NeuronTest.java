/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Website: https://github.com/MinhasKamal/Intellectron     *
* License:  GNU General Public License version-3           *
***********************************************************/

package test;

import com.minhaskamal.neuralnetwork.neuron.Neuron;

public class NeuronTest {
	public static void main(String[] args) throws Exception {
		
		Neuron neuron = new Neuron(2);
		neuron.processSignal(new double[]{0.54, 0.01});
		System.out.println(neuron.getOutput());
		System.out.println();
		System.out.println(neuron.toString());
	}
}
