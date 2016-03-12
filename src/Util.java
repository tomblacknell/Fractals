import java.awt.*;

public class Util {

    public static int getPointOfDiverge(ComplexNumber c, ComplexNumber d, int iterations) {
        ComplexNumber z = new ComplexNumber(d.getRealPart(), d.getImaginaryPart());
        for (int i = 0; i < iterations; i++) {
            if (z.modulusSquared() > 4) {
                return i;
            }
            z.square();
            z.add(c);
        }
        return iterations;
    }

    public static int getPointOfDiverge(ComplexNumber c, int iterations) {
        return getPointOfDiverge(c, c, iterations);
    }

    public static Color getColor(int p) {
        if (p <= 1 ) {
            return Color.BLACK;
        } else if (p <= 2 ) {
            return new Color(0, 20, 0);
        } else if (p <= 3 ) {
            return new Color(2, 40, 0);
        } else if (p <= 4 ) {
            return new Color(2,60, 0);
        } else if (p <= 5 ) {
            return new Color(3,80,0);
        } else if (p <= 6 ) {
            return new Color(4,100,0);
        } else if (p <= 10) {
            return new Color(5,120,0);
        } else if (p <= 20) {
            return new Color(6,140,0);
        } else if (p <= 30) {
            return new Color(7,160,0);
        } else if (p < 100) {
            return new Color(8,230,0);
        } else {
            return new Color(0,0,0);
        }
    }

}
