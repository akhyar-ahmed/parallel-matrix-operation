import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class BlockOperatorTest {

    public Matrix square1;
    public Matrix square2;
    public Matrix m1;
    public Matrix m2;
    public BlockOperator b;

    @BeforeEach
    public void setup() {
        this.b = new BlockOperator();
        this.square1 = this.b.generateData(8, 8);
        this.square2 = this.b.generateData(8, 8);
        this.m1 = this.b.generateData(6, 8);
        this.m2 = this.b.generateData(8, 3);
    }

    @Test
    void testHadamardMultiply() {
        double[][] cells = {{1.0, 0.0, 1.0, 4.0, 9.0, 16.0, 25.0, 36.0}, {4.0, 1.0, 0.0, 1.0, 4.0, 9.0, 16.0, 25.0}, {9.0, 4.0, 1.0, 0.0, 1.0, 4.0, 9.0, 16.0}, {16.0, 9.0, 4.0, 1.0, 0.0, 1.0, 4.0, 9.0}, {25.0, 16.0, 9.0, 4.0, 1.0, 0.0, 1.0, 4.0}, {36.0, 25.0, 16.0, 9.0, 4.0, 1.0, 0.0, 1.0}, {49.0, 36.0, 25.0, 16.0, 9.0, 4.0, 1.0, 0.0}, {64.0, 49.0, 36.0, 25.0, 16.0, 9.0, 4.0, 1.0}};
        Matrix expected = new Matrix(8, 8);
        expected.setCells(cells);
        Matrix actual = this.b.hadamardMultiply(square1, square2);
        Assertions.assertArrayEquals(expected.getCells(), actual.getCells());
    }

    @Test
    void testTranspose() {
        double[][] cells = {{1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0}, {0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0}, {-1.0, 0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0}};
        Matrix expected = new Matrix(3, 8);
        expected.setCells(cells);
        Matrix actual = b.transpose(m2);

        Assertions.assertArrayEquals(expected.getCells(), actual.getCells());
    }

    @Test
    void testMatrixMultiply() {
        double[][] cells = {{-132.0, -112.0, -92.0}, {-96.0, -84.0, -72.0}, {-60.0, -56.0, -52.0}, {-24.0, -28.0, -32.0}, {12.0, 0.0, -12.0}, {48.0, 28.0, 8.0}};
        Matrix expected = new Matrix(6, 3);
        expected.setCells(cells);
        Matrix actual = b.matrixMultiply(m1, m2);

        Assertions.assertArrayEquals(expected.getCells(), actual.getCells());
    }

    @Test
    void testParallelMatrixMultiply() {
        double[][] cells = {{-132.0, -112.0, -92.0}, {-96.0, -84.0, -72.0}, {-60.0, -56.0, -52.0}, {-24.0, -28.0, -32.0}, {12.0, 0.0, -12.0}, {48.0, 28.0, 8.0}};
        Matrix expected = new Matrix(6, 3);
        expected.setCells(cells);
        Matrix actual = b.parallelMatrixMultiply(m1, m2, 4);

        Assertions.assertArrayEquals(expected.getCells(), actual.getCells());
    }

    @Test
    void testParallelTranspose() {
        double[][] cells = {{1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0}, {0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0}, {-1.0, 0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0}};
        Matrix expected = new Matrix(3, 8);
        expected.setCells(cells);
        Matrix actual = b.parallelTranspose(m2, 4);

        Assertions.assertArrayEquals(expected.getCells(), actual.getCells());
    }

    @Test
    void testParallelHadamard() {
        double[][] cells = {{1.0, 0.0, 1.0, 4.0, 9.0, 16.0, 25.0, 36.0}, {4.0, 1.0, 0.0, 1.0, 4.0, 9.0, 16.0, 25.0}, {9.0, 4.0, 1.0, 0.0, 1.0, 4.0, 9.0, 16.0}, {16.0, 9.0, 4.0, 1.0, 0.0, 1.0, 4.0, 9.0}, {25.0, 16.0, 9.0, 4.0, 1.0, 0.0, 1.0, 4.0}, {36.0, 25.0, 16.0, 9.0, 4.0, 1.0, 0.0, 1.0}, {49.0, 36.0, 25.0, 16.0, 9.0, 4.0, 1.0, 0.0}, {64.0, 49.0, 36.0, 25.0, 16.0, 9.0, 4.0, 1.0}};
        Matrix expected = new Matrix(8, 8);
        expected.setCells(cells);
        Matrix actual = b.parallelHadamard(square1, square2, 4);

        Assertions.assertArrayEquals(expected.getCells(), actual.getCells());
    }
}