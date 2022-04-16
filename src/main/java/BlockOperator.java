import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;


class MatrixProduct extends Thread {
    private double[][] A;
    private double[][] B;
    private double[][] C;
    private int rig,col;
    private int dim;

    public MatrixProduct(double[][] A, double[][] B, double[][] C, int rig, int col, int dim_com)
    {
       this.A=A;    
       this.B=B;
       this.C=C;
       this.rig=rig;    
       this.col=col; 
       this.dim=dim_com;     
    }

   public void run()
   {
        int rowA = this.A.length;
        int colA = this.A[0].length;
        int rowB = this.B.length;
        int colB = this.B[0].length;
        Matrix m1 = new Matrix(rowA, colA);
        Matrix m2 = new Matrix(rowB, colB);;
        
        for(int i = 0; i < rowA; i++){
            for(int j = 0; j < colA; j++){
                m1.cells[i][j] = this.A[i][j]; 
            }
        }

        for(int i = 0; i < rowB; i++){
            for(int j = 0; j < colB; j++){
                m2.cells[i][j] = this.B[i][j]; 
            }
        }
        
        BlockOperator obj = new BlockOperator();
        Matrix out = obj.matrixMultiply(m1, m2);    
        // System.out.println("Thread "+rig+","+col+" complete.");
        // return out;
   }          
}




public class BlockOperator {


    public BlockOperator() {
    }

    /*
     * Helper method to write out a row or column from a matrix.
     * */
    public void printArray (double[] a, int output) throws IOException {
        FileWriter writer = new FileWriter("output" + output  +".txt");
        for (double v : a) {
            writer.write(v + "\t" + "");
        }
        writer.close();
    }

    /*
     * Helper method to fill out random matrix of m x n dimensions
     * */
    public Matrix generateData (int rows, int columns){

        Matrix out = new Matrix(rows, columns);

        for (int i =0; i<rows; i++){
            for (int j=0; j<columns; j++) {
                out.cells[i][j] = i - j + 1;
            }
        }
        System.out.println(out);
        return out;
    }

    /*
     * This method  should execute element-wise multiplication (Hadamard product) of two matrices.
     * As input it receives two matrices m1 and m2. In order to calculate the Hadamard product
     * m1 and m2 need to have the same dimensions. You don't need to cover the case of different matrix sizes
     * (filling out the rest of the matrix with zeros) as that's not in the scope of the exercise.
     *
     * */
    public Matrix hadamardMultiply(Matrix m1, Matrix m2) {

        /*
         * Your code here
         * */
        int rows = m1.getRowSize();
        int cols = m1.getColumnSize();
        Matrix out = new Matrix(rows, cols);
        // double[][] m1_cell = m1.getCells()
        for(int i = 0; i < rows ; i++){
            for (int j = 0; j < cols; j++){
                out.cells[i][j] = m1.cells[i][j] * m2.cells[i][j];
            }
        }
        return out;
    }

    /*
     * In this method, you need to transpose the matrix m which is given as an input parameter.
     * The output is a transposed matrix.
     *
     * */
    public Matrix transpose(Matrix m) {

        /*
         * Your code here
         * */
        int rows = m.getRowSize();
        int cols = m.getColumnSize();
        Matrix out;
        if(rows == cols){
            out = new Matrix(rows);
        }
        else{
            out = new Matrix(cols, rows);
        }
        for(int i = 0; i< cols; i++){
            for(int j =0; j< rows; j++){
                out.cells[i][j] = m.cells[j][i];
            }
        }
        
        return out;
    }

    /*
     * This method should execute matrix multiplication. As input it receives two matrices m1 and m2,
     * with adequate dimensions for matrix multiplication (m x n, n x p). The output is the resulting matrix with
     * dimensions m x p.
     * */
    public Matrix matrixMultiply(Matrix m1, Matrix m2) {

        /*
         * Your code here
         * */
        int m = m1.getRowSize();
        int n = m1.getColumnSize();
        int p = m2.getColumnSize();
        Matrix out = new Matrix(m,p);
        for(int i = 0; i < m; i++){
            for(int j = 0; j < p; j++){
                double sum = 0;
                for(int k = 0; k < n; k++){
                    sum += (m1.cells[i][k] * m2.cells[k][j]);
                }
                out.cells[i][j] = sum;
            } 
        }
        return out;
    }


    /*
     * In the following part you'll need to parallelize the above functions
     * First, you need to create a partitioning method that will process the matrix in rows or columns.
     * The output of the partitioning method is written to separate .txt files just for convenience in order to track
     * the partitioning.
     *
     * Then you'll need to implement some thread handling and running methods in order to execute the
     * multiplication. As there are many ways to implement multi-threaded execution in Java,
     *  feel free to create runnable helper methods or interface classes as you find it best.
     *
     * However (!!!), you'll need to join your results in the methods below (parallelMatrixMultiply, parallelTranspose,
     * parallelHadamard). These are the methods that the final tests will be run on and should contain the final outputs
     * of the three listed methods.
     *
     *
     */



    /* You need to create a partitioning method that will split the matrix in rows or columns
     * You are free to decide.
     *  The method as input receives:
     *
     * @param Matrix m that needs to be split
     *
     *
     * @param integer rc that denotes which row or column of the matrix should be returned
     *
     * The output of the method is an array. For the row or column-based partitioning,
     * the output should be a single row or column matrix respectively.
     *
     *
     * */
    public double [] getRowColumn(Matrix m, int rc) throws IOException {
        double [] out = new double[0];

        /*
         * Your code here
         * */

        printArray(out, rc);
        return null;
    }

    /*
     * In this method you need to parallelize the matrix multiplication function from above.
     * In addition to the two matrices m1 and m2 that need to be multiplied, this method has an input t
     * which denotes the number of threads that will need to execute this function in parallel.
     * For this you'll need to use the partitioning method from above and execute the multiplication in each thread.
     * Finally, you'll have to synchronize the results, and output the complete resulting matrix.
     *
     * */
    public Matrix parallelMatrixMultiply(Matrix m1, Matrix m2, int t) {
        /*
         * Your code here
         * */
        Matrix out = matrixMultiply(m1,m2);
        return out;
    }


    /*
     * In this method you need to parallelize the matrix transposition function from above.
     * In addition to the m matrix, this method has an input t
     * which denotes the number of threads that will need to execute this function in parallel.
     * For this you'll need to partition the matrix in row, column or block-based parts
     * that will execute the transposition in each thread.
     * Finally, you'll have to synchronize the results, and output the complete resulting matrix.
     *
     * */
    public Matrix parallelTranspose(Matrix m, int t) {
        /*
         * Your code here
         * */
        Matrix out = transpose(m);
        return out;
    }


    /*
     * In this method you need to parallelize the Hadamard product function from above.
     * In addition to the two matrices m1 and m2, this method has an input argument t
     * which denotes the number of threads that will need to execute this function in parallel.
     * For this you'll need to partition the matrix in row, column or block-based parts
     * that will execute the element-wise multiplication in each thread.
     * Finally, you'll have to synchronize the results, and output the complete resulting matrix.
     *
     * */
    public Matrix parallelHadamard(Matrix m1, Matrix m2, int t) {
        /*
         * Your code here
         * */
        Matrix out = hadamardMultiply(m1,m2);
        return out;
    }


    public static void main(String[] args) {

    }
}
