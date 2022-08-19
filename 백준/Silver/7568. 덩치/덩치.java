import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Person {
    int weight;
    int height;

    public Person(int weight, int height) {
        this.weight = weight;
        this.height = height;
    }
}

class Main {
    static int N;
    static int[] score;
    static ArrayList<Person> people = new ArrayList<>();

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        score = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            people.add(new Person(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        for (int i = 0; i < N; i++) {
            Person currentPerson = people.get(i);
            int index = 0;
            score[i]++;
            while (index < N) {
                Person nextPerson = people.get(index);

                if (currentPerson.weight < nextPerson.weight && currentPerson.height < nextPerson.height) {
                    score[i]++;
                }

                index++;
            }
            sb.append(score[i]).append(" ");
        }
        System.out.println(sb);
    }
}