public class AlgorithmFactory {
	public AlgorithmFactory(){};
	
	public Encryption getShape(String AlgorithmType) {
		if (AlgorithmType == null) {
			return null;
		}
		if (AlgorithmType.equalsIgnoreCase("CEASARCIPHER")) {
			return new CeasarCipher();

		} else if (AlgorithmType.equalsIgnoreCase("DOUBLEENCRYPTION")) {
			return new DoubleEncryption();

		} else if (AlgorithmType.equalsIgnoreCase("REVERSEENCRYPTION")) {
			return new ReverseEncryption();

		} else if (AlgorithmType.equalsIgnoreCase("RSA")) {
			return new RSA();

		} else if (AlgorithmType.equalsIgnoreCase("SPLITENCRYPTION")) {
			return new SplitEncryption();

		}else if (AlgorithmType.equalsIgnoreCase("XORENCRYPTION")) {
			return new XORencryption();

		}

		return null;
	}
}
