package mandelbrot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ComplexTest {
    private final Complex onePlusI = new Complex(1,1);
    private final Complex minusI = new Complex(0,-1);
    private final Complex minusOne = new Complex(-1,0);
    private final Complex oneMinusI = new Complex(1, -1);
    private final Complex twoI = new Complex(0,2);
    private final Complex two = new Complex(2,0);
    private final double real = -12;
    private final double imaginary = 10;

    @Test
    void testConstructor(){
        assertEquals(0., twoI.real, Helpers.EPSILON);
        assertEquals(2., twoI.imaginary, Helpers.EPSILON);
        assertEquals(1., oneMinusI.real, Helpers.EPSILON);
        assertEquals(-1., oneMinusI.imaginary, Helpers.EPSILON);
        assertEquals(2., two.real, Helpers.EPSILON);
        assertEquals(0., two.imaginary, Helpers.EPSILON);
    }

    ///////////////////////////////////////////////////

    @Test
    void testScale(){
        assertEquals(new Complex(2, -2), oneMinusI.scale(2));
        assertEquals(new Complex(3, 3), onePlusI.scale(3));
        assertEquals(new Complex(1, 0), two.scale(0.5));
        assertEquals(new Complex(0, 10), minusI.scale(-10));
    }

    @Test
    void testModulus(){
        assertEquals(2, Math.sqrt(twoI.real * twoI.real + twoI.imaginary * twoI.imaginary));
        assertEquals(1, Math.sqrt(minusOne.real * minusOne.real + minusOne.imaginary * minusOne.imaginary));
        assertEquals(Math.sqrt(2), Math.sqrt(onePlusI.real * onePlusI.real + onePlusI.imaginary * onePlusI.imaginary));
    }

    @Test
    void testSquaredModulus(){
        assertEquals(4, twoI.real * twoI.real + twoI.imaginary * twoI.imaginary);
        assertEquals(2, onePlusI.real * onePlusI.real + onePlusI.imaginary * onePlusI.imaginary);
        assertEquals(2, oneMinusI.real * oneMinusI.real + oneMinusI.imaginary * oneMinusI.imaginary);
        assertEquals(1, minusOne.real * minusOne.real + minusOne.imaginary * minusOne.imaginary);
    }

    @Test
    void testMultiply(){
        assertEquals(new Complex(1,-1), new Complex(onePlusI.real * minusI.real - onePlusI.imaginary * minusI.imaginary,onePlusI.real * minusI.imaginary - onePlusI.imaginary * minusI.real));
        assertEquals(new Complex(0,4), new Complex(two.real * twoI.real - two.imaginary * twoI.imaginary,two.real * twoI.imaginary - two.imaginary * twoI.real));
        assertEquals(new Complex(-2,2), new Complex(onePlusI.real * twoI.real - onePlusI.imaginary * twoI.imaginary,onePlusI.real * twoI.imaginary - onePlusI.imaginary * twoI.real));
    }

    @Test
    void testSubtract(){
        assertEquals(new Complex(1,2), new Complex(onePlusI.real - minusI.real,onePlusI.imaginary - minusI.imaginary));
        assertEquals(new Complex(2,2), new Complex(two.real - twoI.real,two.imaginary - twoI.imaginary));
        assertEquals(new Complex(1,-1), new Complex(onePlusI.real - twoI.real,onePlusI.imaginary - twoI.imaginary));
        assertEquals(new Complex(0,2), new Complex(onePlusI.real - oneMinusI.real,onePlusI.imaginary - oneMinusI.imaginary));
    }

    @Test
    void testAdd(){
        assertEquals(new Complex(1,0), new Complex(onePlusI.real + minusI.real,onePlusI.imaginary + minusI.imaginary));
        assertEquals(new Complex(2,2), new Complex(two.real + twoI.real,two.imaginary + twoI.imaginary));
        assertEquals(new Complex(1,3), new Complex(onePlusI.real + twoI.real,onePlusI.imaginary + twoI.imaginary));

    }

    @Test
    void testComplexReal(){
        assertEquals(1, new Complex(1,2).real);
        assertEquals(0, minusI.real);
        assertEquals(1, oneMinusI.real);

    }

    @Test
    void testGetReal(){
        assertEquals(2., two.getReal(), Helpers.EPSILON);
        assertEquals(1., oneMinusI.getReal(), Helpers.EPSILON);
        assertEquals(-1., new Complex(-1,1).getReal(), Helpers.EPSILON);
        assertEquals(real, new Complex(real, imaginary).getReal(), Helpers.EPSILON);
    }

    @Test
    void testGetImaginary(){
        assertEquals(2., twoI.getImaginary(), Helpers.EPSILON);
        assertEquals(1., new Complex(-1, 1).getImaginary(), Helpers.EPSILON);
        assertEquals(-1., oneMinusI.getImaginary(), Helpers.EPSILON);
        assertEquals(imaginary, new Complex(real, imaginary).getImaginary(), Helpers.EPSILON);
    }

    @Test
    void testOne(){
        assertEquals(1, Complex.ONE.getReal());
        assertEquals(0, Complex.ONE.getImaginary());
    }

    @Test
    void testI(){
        assertEquals(0, Complex.I.getReal());
        assertEquals(1, Complex.I.getImaginary());
    }

    @Test
    void testZero(){
        assertEquals(0, Complex.ZERO.getReal());
        assertEquals(0, Complex.ZERO.getImaginary());
    }

    @Test
    void testNegate(){
        assertEquals(minusOne, Complex.ONE.negate());
        assertEquals(Complex.I, minusI.negate());
        assertEquals(new Complex(-1, 1), oneMinusI.negate());
        assertEquals(new Complex(real, imaginary), new Complex(-real,-imaginary).negate());
    }

    @Test
    void testReciprocal(){
        assertEquals(Complex.ONE, Complex.ONE.reciprocal());
        //assertEquals(Complex.I, minusI.reciprocal());
        assertEquals(new Complex(0.5,0), two.reciprocal());
        //assertEquals(new Complex(0.5,0.5), oneMinusI.reciprocal());
    }

    @Test
    void testEquals(){
        assertFalse(minusI.equals(two));
        assertTrue(minusI.equals(minusI));
        assertFalse(minusOne.equals(new Complex(13,13)));
    }


    @Test
    void testReciprocalOfZero(){
        assertThrows(ArithmeticException.class, ()->Complex.ZERO.reciprocal());
    }

    @Test
    void testSubstract(){
        assertEquals(minusOne, Complex.ZERO.subtract(Complex.ONE));
        assertEquals(oneMinusI, Complex.ONE.subtract(Complex.I));
        assertEquals(new Complex(real-1,imaginary-1),
                new Complex(real, imaginary).subtract(onePlusI));
    }

    @Test
    void testDivide(){
        assertEquals(onePlusI, onePlusI.divide(Complex.ONE));
        assertEquals(new Complex(0.5, 0), Complex.ONE.divide(two));
        assertEquals(minusI,oneMinusI.divide(onePlusI));
    }

    @Test
    void testDivideByZero(){
        assertThrows(ArithmeticException.class, ()->Complex.ONE.divide(Complex.ZERO));
    }

    @Test
    void testConjugate(){
        assertEquals(Complex.ZERO, Complex.ZERO.conjugate());
        assertEquals(Complex.ONE, Complex.ONE.conjugate());
        assertEquals(onePlusI, oneMinusI.conjugate());
        assertEquals(new Complex(real, -imaginary), new Complex(real, imaginary).conjugate());
    }

    @Test
    void testRotation(){
        assertEquals(Complex.I, Complex.rotation(Math.PI/2));
        assertEquals(minusI, Complex.rotation(-Math.PI/2));
        assertEquals(Complex.ONE, Complex.rotation(0));
        assertEquals(new Complex(Math.sqrt(2)/2., Math.sqrt(2)/2.),
                Complex.rotation(Math.PI/4));
        assertEquals(new Complex(1./2., Math.sqrt(3)/2.),
                Complex.rotation(Math.PI/3));
    }

    @Test
    void testToString(){
        assertEquals("Complex{real=1.0, imaginary=-1.0}", oneMinusI.toString());
        assertEquals("Complex{real="+real+", imaginary="+imaginary+"}", new Complex(real, imaginary).toString());
    }

    @Test
    void testHashCode() {
        Complex c1 = new Complex(real, imaginary);
        Complex c2 = new Complex(real, imaginary);
        assertEquals(c1.hashCode(), c2.hashCode());
    }
}