import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
        
		int row = Integer.parseInt(st.nextToken());
		int column = Integer.parseInt(st.nextToken());

		boolean[][] arr = new boolean[row][column];
		String[] temp = new String[column];
		for (int i=0; i<row; i++) {
			temp = br.readLine().split("");
			
			for (int j=0; j<column; j++) {
				if (temp[j].equals("X")) arr[i][j] = true;
			}
		}
		
		int notRowGuard = 0; 
		int notColumnGuard = 0; 
		boolean hasGuard;
        for (int i=0; i< row; i++) {
			hasGuard = false;
			for (int j=0; j<column; j++) {
				if (arr[i][j]) { 
					hasGuard = true;
					break;
				}
			}
			if (!hasGuard) notRowGuard++;
		}
		
		for (int i=0; i< column; i++) {
			hasGuard = false;
			for (int j=0; j<row; j++) {
				if (arr[j][i]) { 
					hasGuard = true;
					break;
				}
			}
			if (!hasGuard) notColumnGuard++;
		}
		
		System.out.println(notRowGuard>notColumnGuard ? notRowGuard:notColumnGuard);
	}
}