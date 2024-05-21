
package project;


public class Ratings {
    
  
  
    public static void printArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][0] != 0){
                for (int j = 0; j < arr[i].length; j++) {
                    System.out.print(arr[i][j] + "\t");
                }
                System.out.println();
            }
        }
    }
    

}

    