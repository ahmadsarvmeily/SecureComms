import java.math.BigInteger;
import java.util.Random;

class KeyGenerator {

    private BigInteger prime, primitiveRoot;
    private int privateValue;

    KeyGenerator(int prime, int primitiveRoot) {
        Random random = new Random();
        this.prime = BigInteger.valueOf(prime);
        this.primitiveRoot = BigInteger.valueOf(primitiveRoot);
        privateValue = random.nextInt(1000);
    }

    int generateValue() {
        BigInteger calc = primitiveRoot.pow(privateValue);
        return calc.mod(prime).intValue();
    }

    int generateKey(int value) {
        BigInteger bigValue = BigInteger.valueOf(value);
        BigInteger calc = bigValue.pow(privateValue);
        return calc.mod(prime).intValue();
    }
}
