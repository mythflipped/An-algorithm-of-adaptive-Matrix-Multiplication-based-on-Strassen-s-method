public class Matrix {
    int[] store ;
    int size;

    public Matrix(int[][] matix) {

        int row=matix.length;

        int column=matix[0].length;
        this.store=new int[row*column];
        int index=0;
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                this.store[index] =matix[i][j] ;//make sure insert row first
                index ++;}}
        this.size=row;//for only n*n is concidered the row and column is the same
    }

    public int getStore(int row,int column) {
        return store[ this.size*row+column];//get the number of the corresponding position
    }

    public void setStore(int row,int column,int number) {
        this.store[ this.size*row+column]= number;//set the number into the corresponding position
    }
}
