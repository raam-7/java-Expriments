import java.util.Arrays;

public class Array {
    public static void main(String[] args) {

    int[] iArr = new int[5];
    iArr[0] = 20;
    iArr[1] = 40;
    iArr[2] = 100;
    iArr[3] = 32;
    iArr[4] = 22;

    for (int i = 0; i < 5; i++) {
      System.out.println(iArr[i]); 
    }

    int[] myArr = new int[]{10, 20, 30, 40, 50};
    for(int m : myArr) {
      System.out.println(m);
    }

    String[] fruits = new String[]{"apple", "banana", "orange", "grape", "mango"};
    for(String f : fruits){
      System.out.println(f);
    }
    int [] copy = new int[7];
    System.arraycopy(iArr, 0, copy, 0,5);

    for(int n : copy){
      System.out.println(n);
    }

    Arrays.sort(iArr);
    
    boolean e1 = Arrays.equals(iArr, copy);
    System.out.println(e1);
}
}