
public class ComplexNumber {

    private int realPart, imaginaryPart;
    private double modulus;

    public ComplexNumber(int a, int b) {
        realPart = a;
        imaginaryPart = b;
        modulus = Math.sqrt(realPart*realPart + imaginaryPart*imaginaryPart);
    }

    public int getRealPart() {
        return realPart;
    }

    public int getImaginaryPart() {
        return imaginaryPart;
    }

    public void square() {
        int newRealPart = (realPart * realPart) - (imaginaryPart * imaginaryPart);
        int newImaginaryPart = (realPart * imaginaryPart) + (realPart * imaginaryPart);
        realPart = newRealPart;
        imaginaryPart = newImaginaryPart;
    }

    public double modulusSquared() {
        return modulus;
    }

    public void add(ComplexNumber other) {
        realPart += other.getRealPart();
        imaginaryPart += other.getImaginaryPart();
    }

    @Override
    public String toString() {
        return realPart + " + " + imaginaryPart + "i";
    }

}
