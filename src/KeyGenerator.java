import java.math.BigInteger;
import java.util.Random;

class KeyGenerator {

    private BigInteger prime, primRoot;
    private int privateValue;

    KeyGenerator(int prime, int primRoot) {
        Random random = new Random();
        this.prime = BigInteger.valueOf(prime);
        this.primRoot = BigInteger.valueOf(primRoot);
        privateValue = random.nextInt(1000);
    }

    int generateValue() {
        BigInteger calc = primRoot.pow(privateValue);
        return calc.mod(prime).intValue();
    }

    int generateKey(int value) {
        BigInteger bigValue = BigInteger.valueOf(value);
        BigInteger calc = bigValue.pow(privateValue);
        return calc.mod(prime).intValue();
    }
}
