/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Website: https://github.com/MinhasKamal/Intellectron     *
* License:  GNU General Public License version-3           *
***********************************************************/

package com.minhaskamal.intellectron.util;

public class NeuralNetworkUtils {
	
	public static double[] addBias(double[] inputs){
		double[] inputsWithBias = new double[inputs.length+1];
		
		for(int i=0; i<inputs.length; i++){
			inputsWithBias[i] = inputs[i];
		}
		inputsWithBias[inputsWithBias.length-1] = 1;
		
		return inputsWithBias;
	}
	
	public static double[] removeBias(double[] outputs){
		double[] outputsWithoutBias = new double[outputs.length-1];
		
		for(int i=0; i<outputsWithoutBias.length; i++){
			outputsWithoutBias[i] = outputs[i];
		}
		
		return outputsWithoutBias;
	}
}
