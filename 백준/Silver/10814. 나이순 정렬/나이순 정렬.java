import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

class People {
    int age;
    String name;

    public People(int age, String name) {
        this.age = age;
        this.name = name;
    }
}

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ArrayList<People> people = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String[] tmp = br.readLine().split(" ");
            people.add(new People(Integer.parseInt(tmp[0]), tmp[1]));
        }
        people.sort(new Comparator<People>() {
            @Override
            public int compare(People o1, People o2) {
                return o1.age - o2.age;
            }
        });

        StringBuilder sb = new StringBuilder();
        for (People person : people) {
            sb.append(person.age).append(" ").append(person.name).append("\n");
        }

        System.out.println(sb);
    }
}