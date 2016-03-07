import junit.framework.TestCase;
import org.junit.Test;

public class MandelbrotTests extends TestCase {

    @Test
    public void testSquare() {
        ComplexNumber num1 = new ComplexNumber(-2,3);
        ComplexNumber num2 = new ComplexNumber(2,-3);
        ComplexNumber num3 = new ComplexNumber(-2,-3);
        ComplexNumber num4 = new ComplexNumber(2,3);

        assertEquals(num1.square().toString(), new ComplexNumber(-5, -12).toString());
        assertEquals(num2.square().toString(), new ComplexNumber(-5, -12).toString());
        assertEquals(num3.square().toString(), new ComplexNumber(-5, 12).toString());

        assertEquals(num4.square().toString(), new ComplexNumber(-5, 12).toString());
    }

    @Test
    public void testAdd() {
        ComplexNumber num1 = new ComplexNumber(-5, 7);
        ComplexNumber num2 = new ComplexNumber(3, -9);

        assertEquals(num1.add(num2).toString(), new ComplexNumber(-2, -2).toString());
    }

    @Test
    public void testModulus() {
        ComplexNumber num1 = new ComplexNumber(-4, 3);
        ComplexNumber num2 = new ComplexNumber(5, -7);

        assertEquals(num1.modulusSquared(), 5D);
        assertEquals(num2.modulusSquared(), Math.sqrt(74));
    }



}