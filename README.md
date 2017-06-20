# Intellectron

#### An Innocent Library of Artificial Neural Network

The project is a simple implementation of Deep Neural Network. Several machine learning algorithms are put together here. This tiny library is particularly suitable for small projects.

### How to Use?
1. Download [Intellectron.jar](https://github.com/MinhasKamal/Intellectron/releases/download/release/Intellectron-V0.1.jar), and [integrate](https://stackoverflow.com/a/3280451/4684058) it in your project's build path.
2. Now, use it in your project like this-

```
  // This is a demonstration of XOR gate implementation in Neural Network.
  public static void main(String[] args) {
		// For XOR gate-
		// ------------------
		// | input | output |
		// |----------------|
		// | 0 | 0 |   0    |
		// | 0 | 1 |   1    |
		// | 1 | 0 |   1    |
		// | 1 | 1 |   0    |
		// ------------------
		double[][] inputs = new double[][]{
			{0, 0},	{0, 1},	{1, 0},	{1, 1}
		};
		double[][] expectedOutputs = new double[][]{
			{0}, {1}, {1}, {0}
		};
		
		// Here, we are creating four layers of neural network.
		// First layer is created automatically using the input data.
		// Here the input has two categories, so first layer (input layer)
		// will have 2 neurons. 
		// The second layer will contain- 4, third layer- 2, and fourth layer
		// (output layer) contains 1 neuron.
		int[] numbersOfNeuronsInLayers = new int[]{4, 2, 1};
		
		// The 'DeepNeuralNetworkImplementation' object takes the network 
		// structure, learning rate, and number of inputs (input categories).
		DeepNeuralNetworkImplementation deepNeuralNetworkImplementation = 
		    new DeepNeuralNetworkImplementation(numbersOfNeuronsInLayers, 0.1, 2);
		
		// Here we are running 20,000 cycles to train the network.
		// In each cycle we are passing the input and expected output.
		int cycle = 20000;
		for(int i=0; i<cycle; i++){
			deepNeuralNetworkImplementation.train(inputs, expectedOutputs);
		}
		
		// Storing knowledge in the memory storage
		String workspace = System.getenv("SystemDrive") + System.getenv("HOMEPATH") + "\\Desktop\\";
		deepNeuralNetworkImplementation.dump(workspace+"knowledge.xml");
  }
```

You can find some simple implementations in the [test](https://github.com/MinhasKamal/Intellectron/tree/master/src/test) section. There is also a beautiful project- [DeepGenderRecognizer](https://github.com/MinhasKamal/DeepGenderRecognizer), which uses [Intellectron](https://github.com/MinhasKamal/Intellectron); you will get a nice insight from it too.

### Releases
- [Intellectron-V0.1.jar](https://github.com/MinhasKamal/Intellectron/releases/download/release/Intellectron-V0.1.jar)

### License

<a rel="license" href="https://opensource.org/licenses/MIT"><img alt="MIT License" src="https://cloud.githubusercontent.com/assets/5456665/18950087/fbe0681a-865f-11e6-9552-e59d038d5913.png" width="60em" height=auto/></a><br/>

<a href="https://github.com/MinhasKamal/Intellectron">Intellectron</a> is licensed under <a rel="license" href="https://opensource.org/licenses/MIT">MIT License</a>.

