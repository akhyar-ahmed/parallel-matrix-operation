public class Matrix {
    protected int rowSize;
    protected int columnSize;
    protected double[][] cells;


    public Matrix() {
        rowSize = 1;
        columnSize = 1;
        cells = new double[rowSize][columnSize];
    }

    public Matrix(int n) {
        rowSize = n;
        columnSize = n;
        cells = new double[rowSize][columnSize];
    }

    public Matrix (int n, int m) {
        rowSize = n;
        columnSize = m;
        cells = new double[rowSize][columnSize];
    }

    /**
     * @return the rowSize
     */
    public int getRowSize() {
        return rowSize;
    }

    /**
     * @param rowSize the rowSize to set
     */
    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }

    /**
     * @return the columnSize
     */
    public int getColumnSize() {
        return columnSize;
    }

    /**
     * @param columnSize the columnSize to set
     */
    protected void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    /**
     * @param d the matrix to set
     */
    public void setCells(double[][] d) {
        this.cells = d;
    }

    /**
     * @return the matrix
     */
    public double[][] getCells() {
        return cells;
    }

}
