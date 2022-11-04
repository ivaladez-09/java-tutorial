package Problems.SpiralMatrix;

public class Main {
    public static void main(String[] args) {
        int [][] matrix = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16}
        };

        printSpiralMatrix(matrix);
    }

    private static void printSpiralMatrix(int[][] matrix) {

        final int n = matrix.length, m = matrix[0].length;
        int colStart = 0, colEnd = n - 1;
        int rowStart = 0, rowEnd = m - 1;
        int count = 0, totalNumbers = n * m;

        while (count < totalNumbers){

            for(int i = colStart; i <= colEnd; i++){
                System.out.println(matrix[rowStart][i]);
                count++;
            }
            rowStart++;

            for (int i = rowStart; i <= rowEnd; i++) {
                System.out.println(matrix[i][colEnd]);
                count++;
            }
            colEnd--;

            for(int i = colEnd; i >= colStart; i--){
                System.out.println(matrix[rowEnd][i]);
                count++;
            }
            rowEnd--;

            for (int i = rowEnd; i >= rowStart; i--) {
                System.out.println(matrix[i][colStart]);
                count++;
            }
            colStart++;
        }


    }
}
