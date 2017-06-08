package hw2;                       

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] opened;
    private int top = 0;
    private int bottom;
    private int size;
    private WeightedQuickUnionUF qf;
    private int opensite;

    public Percolation(int N) {
        if (N <=0){
        	throw new java.lang.IllegalArgumentException();
        }
        size = N;
        bottom = size * size + 1;
        qf = new WeightedQuickUnionUF(size * size + 2);
        opened = new boolean[size][size];
        opensite=0;
    }

    public void open(int i, int j) {
        if (i>size || i<0 || j>size || j<0){
        	throw new java.lang.IndexOutOfBoundsException();
        }
        if (!isOpen(i,j)){
        	opensite++;
        }
        opened[i][j] = true;
        if (i == 0) {
            qf.union(getQFIndex(i, j), top);
        }
        if (i == size) {
            qf.union(getQFIndex(i, j), bottom);
        }
        if (j > 0&& isOpen(i, j - 1)) {
            qf.union(getQFIndex(i, j), getQFIndex(i, j - 1));
        }
        if (j!=size-1){
        	if (j < size && isOpen(i, j + 1)) {
            qf.union(getQFIndex(i, j), getQFIndex(i, j + 1));
        	}
        }
        if (i > 0 && isOpen(i - 1, j)) {
            qf.union(getQFIndex(i, j), getQFIndex(i - 1, j));
        }
        if (i!=size-1){
        	if (i < size && isOpen(i + 1, j)) {
            qf.union(getQFIndex(i, j), getQFIndex(i + 1, j));
        	}
    	}
    }

    public boolean isOpen(int i, int j) {
        if (i>size || i<0 || j>size || j<0){
        	throw new java.lang.IndexOutOfBoundsException();
        }
        return opened[i][j];
    }

    public boolean isFull(int i, int j) {
	   if (i>size || i<0 || j>size || j<0){
        	throw new java.lang.IndexOutOfBoundsException();
        }    
        return qf.connected(top, getQFIndex(i , j));
    }

    public boolean percolates() {
        return qf.connected(top, bottom);
    }

    public int numberOfOpenSites(){
    	return opensite;
    }

     private int getQFIndex(int i, int j) {
        return size * i +j+1;
    }

}