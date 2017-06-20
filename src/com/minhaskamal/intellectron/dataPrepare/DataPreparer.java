/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Website: https://github.com/MinhasKamal/Intellectron     *
* License:  GNU General Public License version-3           *
***********************************************************/

package com.minhaskamal.intellectron.dataPrepare;

import java.io.File;
import java.io.FileFilter;
import java.util.LinkedList;
import java.util.Random;

public abstract class DataPreparer {
	
	private double[][][] trainingData;
	private double[][][] testingData;
	
	
	/**
	 * After creating training data rest of the data is used for testing.
	 * @param rootPath data should be categorically arranged in folders
	 * @param numberOfTrainingData number of training data for each category
	 * @param magnitudeOfShuffles if <code>'<1'</code> no shuffling occurs
	 */
	public DataPreparer(String rootDirectoryPath, int numberOfTrainingData, int numberOfCrossShuffles) {
		double[][][] inputs = prepareData(prepareDataPaths(rootDirectoryPath)); //[folder][file][data] i.e. [male][001.png][125,233,...]
		double[][] outputs = prepareOutputs(inputs.length);
		
		this.trainingData = createData(inputs, outputs, 0, numberOfTrainingData); 
		this.testingData = createData(inputs, outputs, numberOfTrainingData, Integer.MAX_VALUE); 
		
		if(numberOfCrossShuffles>0){
			this.trainingData = crossShuffle(this.trainingData, numberOfCrossShuffles);
			this.testingData = crossShuffle(this.testingData, numberOfCrossShuffles);
		}
	}
	
	/**
	 * No shuffling occurs.
	 */
	public DataPreparer(String rootDirectoryPath, int numberOfTrainingData){
		this(rootDirectoryPath, numberOfTrainingData, -1);
	}
	
	/**
	 * All data will be added in <code>trainingData</code>, <code>testingData</code> 
	 * will be null. No shuffling occurs.
	 */
	public DataPreparer(String rootDirectoryPath) {
		this(rootDirectoryPath, Integer.MAX_VALUE);
	}
	

	//PREPARE RAW DATA///////////////////////////////////////////////////////
	
	private String[][] prepareDataPaths(String rootFolderPath){
		File[] directories = new File(rootFolderPath).listFiles(new FileFilter() {
			@Override
			public boolean accept(File arg0) {
				return arg0.isDirectory();
			}
		});
		
		String[][] allFilePaths = new String[directories.length][];
		for(int i=0; i<directories.length; i++){
			File[] files = directories[i].listFiles();
			allFilePaths[i] = new String[files.length];
			
			for(int j=0; j<files.length; j++){
				allFilePaths[i][j] = files[j].getAbsolutePath();
			}
		}
		
		return allFilePaths;
	}
	
	private double[][][] prepareData(String[][] allFilePaths){
		double[][][] inputs = new double[allFilePaths.length][][];
		
		for(int i=0; i<allFilePaths.length; i++){
			inputs[i] = new double[allFilePaths[i].length][];
			for(int j=0; j<allFilePaths[i].length; j++){
				inputs[i][j] = readFileVectorizeAndScale(allFilePaths[i][j]);
			}
		}
		
		return inputs;
	}
	
	/**
	 * Read file from the <code>filePath</code>, store and scale (call <code>scale()</code>)
	 * the data in a vector and return it.
	 * @param filePath 
	 * @return
	 */
	public abstract double[] readFileVectorizeAndScale(String filePath);
	
	/**
	 * Scales data from 0 to 1 given <code>minValue</code> & <code>maxValue</code>.
	 * @param vector
	 * @param minValue
	 * @param maxValue
	 * @return
	 */
	public double[] scale(int[] vector, int minValue, int maxValue){
		double[] scaledVector = new double[vector.length];
		
		for(int i=0; i<vector.length; i++){
			scaledVector[i] = (vector[i]-minValue)/(maxValue-minValue);
		}
		
		return scaledVector;
	}
	
	private double[][] prepareOutputs(int numberOfCategories){
		double[][] outputs = new double[numberOfCategories][];
		for(int i=0; i<outputs.length; i++){
			outputs[i] = new double[numberOfCategories];
			outputs[i][i] = 1;
		}
		
		return outputs;
	}
	
	//CREATE INPUT-OUTPUT DATA////////////////////////////////////////////////
	
	private double[][][] createData(double[][][] inputs, double[][] outputs, int start, int end){
		
		LinkedList<double[]> in = new LinkedList<double[]>();
		LinkedList<double[]> out = new LinkedList<double[]>();
		for(int i=0; i<inputs.length; i++){
			for(int j=start; j<inputs[i].length && j<end; j++){
				in.push(inputs[i][j]);
				out.push(outputs[i]);
			}
		}
		
		double[][][] data = new double[][][]{in.toArray(outputs), out.toArray(outputs)};
		return data;
	}
	
	//SHUFFLE-DATA////////////////////////////////////////////////////////////
	
	/**
	 * One cross shuffle and one random shuffle with a 0.3 magnitude.
	 * @param data
	 * @return
	 */
	public static double[][][] shuffle(double[][][] data){
		data =  crossShuffle(data, 1);
		return randomShuffle(data, 0.3);
	}
	
	public static double[][][] randomShuffle(double[][][] data, double magnitudeOfShuffles){
		return randomShuffle(data, (int)(data[0].length*magnitudeOfShuffles));
	}
	
	public static double[][][] randomShuffle(double[][][] data, int numberOfShuffles){
		
		int dataSize = data[0].length;
		
		double[] temp;
		int randomPoint1, randomPoint2;
		Random random = new Random();
		for(int c=0; c<numberOfShuffles; c++){
			randomPoint1 = random.nextInt(dataSize);
			randomPoint2 = random.nextInt(dataSize);
			
			for(int i=0; i<data.length; i++){ //swapping
				temp = data[i][randomPoint1];
				data[i][randomPoint1] = data[i][randomPoint2];
				data[i][randomPoint2] = temp;
			}
		}
		
		
		return data;
	}
	
	public static double[][][] crossShuffle(double[][][] data, int numberOfShuffles){
		
		int dataSize = data[0].length;
		double[] temp;
		for(int c=0; c<numberOfShuffles; c++){
			for(int x=(dataSize-1)/2; x>0; x--){
				for(int i=0; i<data.length; i++){ //swapping
					temp = data[i][x];
					data[i][x] = data[i][x*2];
					data[i][x*2] = temp;
				}
			}
		}
		
		return data;
	}
	
	//GETTERS/////////////////////////////////////////////////////////////////
	
	public double[][] getTrainingInputs() {
		return this.trainingData[0];
	}
	public double[][] getTrainingOutputs() {
		return this.trainingData[1];
	}
	public double[][] getTestingInputs() {
		return this.testingData[0];
	}
	public double[][] getTestingOutputs() {
		return this.testingData[1];
	}

	public double[][][] getTrainingData() {
		return this.trainingData;
	}
	public double[][][] getTestingData() {
		return this.testingData;
	}
	
}
