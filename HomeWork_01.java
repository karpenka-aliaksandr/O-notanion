import java.util.Random;

public class HomeWork_01 {

 

    public static int CountDividers(int Ai){
        int res = 0;
        for (int i = 1; i*i <= Ai; i++) {
            if (Ai % i == 0) {           
                if (i*i==Ai) 
                    res += 2; 
                else 
                    res += 4;
            }
        }
        return res;
    }
    
    public static void main(String[] args) {
        Random rnd = new Random();
        int N = rnd.nextInt(1000);
        for (int i = 0; i < N; i++) {
            int num = rnd.nextInt(1000000);
            System.out.printf("num = %d: %d\n", num, CountDividers(num)); 
        }
    }
}

