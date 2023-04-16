import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
    
    static int N;
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
 
		if (N == 1) {
			System.out.print(1);
		} else {
			System.out.print(findResult());
		}
	}
    
    private static int findResult() {
        int count = 1, range = 2;
        
        while (range <= N) {
			range = range + (6 * count);
			count++;
		}
        return count;
    }
}