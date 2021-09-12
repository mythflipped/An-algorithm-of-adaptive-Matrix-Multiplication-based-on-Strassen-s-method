import java.io.*;
import java.util.Random;
import java.util.Scanner;
public class StrassenAndTradition{
    public static void matrixSub(int[][] matrixA, int[][] matrixB, int[][] result){
        for(int i = 0; i < matrixA.length; i++)
            for(int j = 0; j < matrixA.length; j++)
                result[i][j] = matrixA[i][j] - matrixB[i][j];
    }//use two "for" circle to realize subtract
    public static void matrixAdd(int[][] matrixA, int[][] matrixB, int[][] result){
        for(int i = 0; i < matrixA.length; i++)
            for(int j = 0; j < matrixA.length; j++)
                result[i][j] = matrixA[i][j] + matrixB[i][j];
    }//use two "for" circle to realize add






    public static int[][]  strassen_mutliply(Matrix matrixA, Matrix matrixB){
        int N= matrixA.size;
        int[][] result= new int[N][N];
        if(N == 1){
            result[0][0] = matrixA.getStore(0,0) *matrixB.getStore(0,0);
            return result;
        }
        int halfSize;
        halfSize=N/2;
        int[][] A = new int[halfSize][halfSize];
        int[][] B = new int[halfSize][halfSize];
        int[][] C = new int[halfSize][halfSize];
        int[][] D = new int[halfSize][halfSize];
        int[][] E = new int[halfSize][halfSize];
        int[][] F = new int[halfSize][halfSize];
        int[][] G = new int[halfSize][halfSize];
        int[][] H = new int[halfSize][halfSize];//create 8 submatrix for copy
        int[][] C1 = new int[halfSize][halfSize];
        int[][] C2 = new int[halfSize][halfSize];
        int[][] C3 = new int[halfSize][halfSize];
        int[][] C4 = new int[halfSize][halfSize];
        int[][] P1 = new int[halfSize][halfSize];
        int[][] P2 = new int[halfSize][halfSize];
        int[][] P3 = new int[halfSize][halfSize];
        int[][] P4 = new int[halfSize][halfSize];
        int[][] P5 = new int[halfSize][halfSize];
        int[][] P6 = new int[halfSize][halfSize];
        int[][] P7 = new int[halfSize][halfSize];//create 7 matrix for subtract and add
        int[][] tempA = new int[halfSize][halfSize];
        int[][] tempB = new int[halfSize][halfSize];//create 2 matrix to store the result subtract and add
        for(int i = 0; i < halfSize; i++)
            for(int j = 0; j < halfSize; j++){
                A[i][j] = matrixA.getStore(i,j) ;
                B[i][j] = matrixA.getStore(i,j+ halfSize) ;//matrixA[i][halfSize + j];
                C[i][j] = matrixA.getStore(i+ halfSize,j) ;//matrixA[i + halfSize][j];
                D[i][j] = matrixA.getStore(i+ halfSize,j+ halfSize) ;//matrixA[i + halfSize][j + halfSize];
                E[i][j] = matrixB.getStore(i,j) ;//matrixB[i][j];
                F[i][j] = matrixB.getStore(i,halfSize +j) ;//matrixB[i][halfSize + j];
                G[i][j] = matrixB.getStore(i+ halfSize,j) ;//matrixB[i + halfSize][j];
                H[i][j] = matrixB.getStore(i+ halfSize,j+ halfSize) ;//matrixB[i + halfSize][j + halfSize];
            } // copy the value for submatrix
        Matrix Au=new Matrix(A);
        Matrix Bu=new Matrix(B);
        Matrix Cu=new Matrix(C);
        Matrix Du=new Matrix(D);
        Matrix Eu=new Matrix(E);
        Matrix Fu=new Matrix(F);
        Matrix Gu=new Matrix(G);
        Matrix Hu=new Matrix(H);


        matrixSub(F,H,tempB);
        Matrix tempBu=new Matrix(tempB);

        P1=  strassen_mutliply(Au,tempBu);

        matrixAdd(A,B,tempA);
        Matrix tempAu=new Matrix(tempA);
        P2 =  strassen_mutliply(tempAu,Hu);

        matrixAdd(C,D,tempA);
        tempAu=new Matrix(tempA);
        P3= strassen_mutliply(tempAu,Eu);

        matrixSub(G,E,tempB);
        tempBu=new Matrix(tempB);
        P4= strassen_mutliply(Du,tempBu);

        matrixAdd(A,D,tempA);
        matrixAdd(E,H,tempB);
        tempAu=new Matrix(tempA);
        tempBu=new Matrix(tempB);
        P5= strassen_mutliply(tempAu,tempBu);

        matrixSub(B,D,tempA);
        matrixAdd(G,H,tempB);
        tempAu=new Matrix(tempA);
        tempBu=new Matrix(tempB);
        P6= strassen_mutliply(tempAu,tempBu);

        matrixSub(A,C,tempA);
        matrixAdd(E,F,tempB);
        tempAu=new Matrix(tempA);
        tempBu=new Matrix(tempB);
        P7= strassen_mutliply(tempAu,tempBu);//divide our matric to submatrix and cope with one by one

        matrixAdd(P5,P4,C1);
        matrixSub(C1,P2,C1);
        matrixAdd(C1,P6,C1);
        matrixAdd(P1,P2,C2);
        matrixAdd(P3,P4,C3);
        matrixAdd(P5,P1,C4);
        matrixSub(C4,P3,C4);
        matrixSub(C4,P7,C4);//create the four submatrix of final result

        for(int i = 0; i < halfSize; i++)
            for(int j = 0; j < halfSize; j++){
                result[i][j] = C1[i][j];
                result[i][j + halfSize] = C2[i][j];
                result[i + halfSize][j] = C3[i][j];
                result[i + halfSize][j + halfSize] = C4[i][j];
            }
        return result;
    } // put four submatrix in their position to complete results.

    public static int[][] square_matrix_mutliply(int[][] matrix1, int[][] matrix2){
        int[][] resulttradition= new int[matrix1.length][matrix1.length];
        int n= matrix1.length;
        for(int i = 0; i < n; i++)
            for(int k = 0; k < n; k++)
                for(int j = 0; j < n; j++)
                    resulttradition[i][k] += matrix1[i][j] * matrix2[j][k];
        return resulttradition;
    } // this is the tradition way which use three for circle

    public static boolean isPowerOfTwo(int n){
        return (n & (n-1))==0;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        int shiji;//to calculate the expanded size of the matrix
        while(input.hasNext()){
            int n = input.nextInt();
            if(!isPowerOfTwo(n)){
                shiji=1;
                while(n>=shiji){
                    shiji=shiji*2;
                }
            }else {
                shiji=n;
            }
            int[][] matrixA = new int[shiji][shiji];
            int[][] matrixB = new int[shiji][shiji];
            int[][] result = new int[shiji][shiji];
            int[][] resulttradition = new int[shiji][shiji];
            System.out.println(" data for matrix A: ");


            for(int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrixA[i][j] = random.nextInt(3);
                    System.out.printf(String.valueOf(matrixA[i][j])+ " ");
                }
                System.out.print("\n");
            }
            System.out.println(" data for matrix B: ");
            for(int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrixB[i][j] = random.nextInt(3);
                    System.out.printf(String.valueOf(matrixB[i][j])+ " ");
                }
                System.out.print("\n");
            }
            if(n/2!=0){
                for(int i=0;i<shiji-1;i++){//add zeros
                    for (int j=0;j<shiji-1;j++)
                        if(i>n-1|j>n-1){
                            matrixA[i][j]=0;
                            matrixB[i][j]=0;
                        }
                }

            }
            Matrix matrixA1=new Matrix(matrixA);
            Matrix matrixB1=new Matrix(matrixB);

            long startTime1=System.nanoTime();
            result= strassen_mutliply(matrixA1,matrixB1);
            long endTime1=System.nanoTime();
            long runtime1 = endTime1-startTime1;

            long startTime2=System.nanoTime();
            resulttradition= square_matrix_mutliply(matrixA, matrixB);
            long endTime2=System.nanoTime();
            long runtime2= endTime2-startTime2;

            System.out.println(" (Strassen) Data for result: ");
            for(int i = 0; i < n; i++)
                for(int j = 0; j < n; j++){
                    if(j != n - 1) System.out.print(result[i][j] + " ");
                    else           System.out.println(result[i][j]);
                }
            System.out.println(runtime1);

            System.out.println(" (Tradition) Data for result: ");
            for(int i = 0; i < n; i++)
                for(int j = 0; j < n; j++){
                    if(j != n- 1) System.out.print(resulttradition[i][j] + " ");
                    else           System.out.println(resulttradition[i][j]);
                }
            System.out.println(runtime2);
            System.out.println(" difference in runtime ");
            System.out.println(runtime2-runtime1);
           // calibrate_crossover_point();
        }
    }


    public static void  calibrate_crossover_point(){
        long timestr=1;
        long timetra=0;
        int shiji;
        int test=1;
        Random random = new Random();
        while(timestr>timetra){
            if(!isPowerOfTwo(test)){
                shiji=1;
                while(test>=shiji){
                    shiji=shiji*2;
                }
            }else {
                shiji=test;
            }//same as before
            int[][] matrixA = new int[shiji][shiji];
            int[][] matrixB = new int[shiji][shiji];
            int[][] result = new int[shiji][shiji];
            int[][] resulttradition = new int[shiji][shiji];
         //   System.out.println(" data for matrix A: ");

            for(int i = 0; i < test; i++) {
                for (int j = 0; j < test; j++) {
                    matrixA[i][j] = random.nextInt(3);
               //     System.out.printf(String.valueOf(matrixA[i][j])+ " ");
                }
             //   System.out.print("\n");
            }
           // System.out.println(" data for matrix B: ");
            for(int i = 0; i < test; i++) {
                for (int j = 0; j < test; j++) {
                    matrixB[i][j] = random.nextInt(3);
              //      System.out.printf(String.valueOf(matrixB[i][j])+ " ");
                }
              //  System.out.print("\n");
            }
            if(test/2!=0){
                for(int i=0;i<shiji-1;i++){//
                    for (int j=0;j<shiji-1;j++)
                        if(i>test-1|j>test-1){
                            matrixA[i][j]=0;
                            matrixB[i][j]=0;
                        }
                }

            }//compare the time
            Matrix matrixA1=new Matrix(matrixA);
            Matrix matrixB1=new Matrix(matrixB);
            long startTime1=System.nanoTime();
            result= strassen_mutliply(matrixA1,matrixB1);
            long endTime1=System.nanoTime();
            timestr = endTime1-startTime1;

            long startTime2=System.nanoTime();
            resulttradition= square_matrix_mutliply(matrixA, matrixB);
            long endTime2=System.nanoTime();
            timetra= endTime2-startTime2;
            test++;
        }
        try {//write into txt
            BufferedWriter out = new BufferedWriter(new FileWriter("crosspoint.txt"));
            out.write(test);
            out.close();
            System.out.println("write down！");
        } catch (IOException e) {
        }
    }
    int[][] adaptive_multiply(int[][]matrixA, int[][]matrixB){//use strassen when needed
        calibrate_crossover_point();
        int test=0;//read the file to get the cross point n0
        try {
            BufferedReader in = new BufferedReader(new FileReader("crosspoint.txt"));
            String str;
            while ((str = in.readLine()) != null) {
                test=Integer.parseInt(str);
            }
        } catch (IOException e) {
        }
        int N= matrixA.length;
        int shiji;
        if(!isPowerOfTwo(N)){
            shiji=1;
            while(N>=shiji){
                shiji=shiji*2;
            }
        }else {
            shiji=N;
        }//get the nearest number which is power of 2
        int[][] result = new int[shiji][shiji];

        if(N<test){
            result= square_matrix_mutliply(matrixA, matrixB);//when n<n0 use naive
        }else{
            Matrix matrixA1=new Matrix(matrixA);
            Matrix matrixB1=new Matrix(matrixB);
            result= strassen_mutliply(matrixA1,matrixB1);//when n>n0 use strassen
        }
        int[][] finalre=new int[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                finalre[i][j]=result[i][j];//delete the excrescent zeros
            }
        }
        return finalre;
    }
}
