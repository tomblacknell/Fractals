
public class ComplexNumber {

    private double realPart, imaginaryPart;
    private double modulus;

    public ComplexNumber(double a, double b) {
        realPart = a;
        imaginaryPart = b;
    }

    public double getRealPart() {
        return realPart;
    }

    public double getImaginaryPart() {
        return imaginaryPart;
    }

    public ComplexNumber square() {
        double newRealPart = (realPart * realPart) + (imaginaryPart * imaginaryPart * - 1);
        double newImaginaryPart = (realPart * imaginaryPart * 2);
        realPart = newRealPart;
        imaginaryPart = newImaginaryPart;
        return this;
    }

    public double modulusSquared() {
        return (realPart*realPart) + (imaginaryPart*imaginaryPart);
    }

    public ComplexNumber add(ComplexNumber other) {
        realPart += other.getRealPart();
        imaginaryPart += other.getImaginaryPart();
        return this;
    }

    @Override
    public String toString() {
        return realPart + " + " + imaginaryPart + "i";
    }

}
