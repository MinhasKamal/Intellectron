# Intellectron

#### An Innocent Library of Artificial Neural Network

The project is a simple implementation of Deep Neural Network. Several machine learning algorithms are put together here. This tiny library is particularly suitable for small projects.

### How to Use?
1. [Download](https://github.com/MinhasKamal/Intellectron/archive/master.zip) the full project, unzip it, import the project to your IDE, and attach it in your project's build path as a required project.
2. Now use it in your project like this-

```
  public static void main(String[] args) {
		//data//
		double[][] inputs = new double[][]{
			{0, 0},	{0, 1},	{1, 0},	{1, 1}
		};
		double[][] expectedOutputs = new double[][]{
			{1}, {0}, {0}, {1}
		};
		
		//deep neural network//
		int[] numbersOfNeuronsInLayers = new int[]{4, 3, 2, 1}; //neural network structure
		DeepNeuralNetworkImplementation deepNeuralNetworkImplementation = 
		    new DeepNeuralNetworkImplementation(numbersOfNeuronsInLayers, 0.1, 2);
		
		//training//
		int cycle = 20000;
		for(int i=0; i<cycle; i++){
			deepNeuralNetworkImplementation.train(inputs, expectedOutputs);
		}
		
		//store knowledge//
		String workspace = System.getenv("SystemDrive") + System.getenv("HOMEPATH") + "\\Desktop\\";
		deepNeuralNetworkImplementation.dump(workspace+"knowledge.xml");
  }
```

You can find some simple implementations in the [test](https://github.com/MinhasKamal/Intellectron/tree/master/src/test) section. There is also a beautiful project- [DeepGenderRecognizer](https://github.com/MinhasKamal/DeepGenderRecognizer), which uses [Intellectron](https://github.com/MinhasKamal/Intellectron); you will get a nice insight from it too.

### License

<a rel="license" href="https://opensource.org/licenses/MIT"><img alt="MIT License" src="https://cloud.githubusercontent.com/assets/5456665/18950087/fbe0681a-865f-11e6-9552-e59d038d5913.png" width="60em" height=auto/></a><br/>

<a href="https://github.com/MinhasKamal/Intellectron">Intellectron</a> is licensed under <a rel="license" href="https://opensource.org/licenses/MIT">MIT License</a>.

