/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Website: https://github.com/MinhasKamal/Intellectron     *
* License:  GNU General Public License version-3           *
***********************************************************/

package com.minhaskamal.intellectron.util;

public class NeuralNetworkUtils {
	
	//BIAS-ADD_REMOVE/////////////////////////////////////////////////////
	
	/**
	 * Adds a '1' element at the end of the array.
	 * @param inputs array of doubles without bias
	 * @return
	 */
	public static double[] addBias(double[] inputs){
		double[] inputsWithBias = new double[inputs.length+1];
		
		for(int i=0; i<inputs.length; i++){
			inputsWithBias[i] = inputs[i];
		}
		inputsWithBias[inputsWithBias.length-1] = 1;
		
		return inputsWithBias;
	}
	
	/**
	 * Removes the last element of the array.
	 * @param outputs array of doubles with bias
	 * @return
	 */
	public static double[] removeBias(double[] outputs){
		double[] outputsWithoutBias = new double[outputs.length-1];
		
		for(int i=0; i<outputsWithoutBias.length; i++){
			outputsWithoutBias[i] = outputs[i];
		}
		
		return outputsWithoutBias;
	}
	
	
	//TEST//////////////////////////////////////////////////////////////
	
	/*public double test(double[][] inputs, double[][] outputs, double tolerance){
		double accuracy = 0;
		
		for(int i=0; i<inputs.length; i++){
			double[] predictedOutput = predict(inputs[i]);
			
			if(NeuralNetworkUtils.isEqual(predictedOutput, outputs[i], tolerance)){
				accuracy++;
			}
		}
		
		accuracy = accuracy/inputs.length;
		
		return accuracy;
	}*/
	
	/**
	 * Returns true if all the elements of <code>predictedOutput</code> are equal to 
	 * all the elements of <code>expectedOutput</code> in one-to-one relation with
	 * respect to the tolerance.
	 * @param predictedOutput
	 * @param expectedOutput
	 * @param tolerance
	 * @return
	 */
	public static boolean isEqual(double[] predictedOutput, double[] expectedOutput, double tolerance){
		for(int i=0; i<predictedOutput.length; i++){
			double diffrence = Math.abs(predictedOutput[i]-expectedOutput[i]);
			if(diffrence>tolerance){
				return false;
			}
		}
		
		return true;
	}
	
}
