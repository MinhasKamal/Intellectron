# :space_invader: Intellectron &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[![Gitter](https://badges.gitter.im/MinhasKamal/Intellectron.svg)](https://gitter.im/MinhasKamal/Intellectron?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=body_badge)

#### An Innocent Framework of Artificial Neural Network

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
			{1}, {0},	{0}, {1}
		};
		
		//deep neural network//
		int[] numbersOfNeuronsInLayers = new int[]{4, 3, 2, 1};
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

You can also find some simple implementations like it in the [test](https://github.com/MinhasKamal/Intellectron/tree/master/src/test) section. There is also a nice project [DeepGenderRecognizer](https://github.com/MinhasKamal/DeepGenderRecognizer) which uses both [Intellectron](https://github.com/MinhasKamal/Intellectron) and [Egami](https://github.com/MinhasKamal/Egami).

### License
<a rel="license" href="http://www.gnu.org/licenses/gpl.html">
<img alt="GNU General Public License" style="border-width:0" src="http://www.gnu.org/graphics/gplv3-127x51.png" />
</a>
<br/>Intellectron is licensed under a <a rel="license" href="http://www.gnu.org/licenses/gpl.html">GNU General Public License version-3</a>.
