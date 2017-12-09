import java.math.BigInteger;
import java.util.Random;

class KeyGenerator {

    private BigInteger prime, primRoot;
    private int privateValue;

    KeyGenerator(int prime, int primRoot) {
        Random random = new Random();

        // BigIntegers required for calculations
        this.prime = BigInteger.valueOf(prime);
        this.primRoot = BigInteger.valueOf(primRoot);

        // Generate random private value
        privateValue = random.nextInt(1000);
    }

    // Return x = g^(a mod p)
    // g = primitive root
    // p = prime
    // a = private value
    int generateValue() {
        BigInteger calc = primRoot.pow(privateValue);
        return calc.mod(prime).intValue();
    }

    // Return k = y^(a mod p)
    // y = other generated value
    int generateKey(int value) {
        BigInteger bigValue = BigInteger.valueOf(value);
        BigInteger calc = bigValue.pow(privateValue);
        return calc.mod(prime).intValue();
    }
}
