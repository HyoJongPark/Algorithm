import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static LinkedList<Document> documents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int K = Integer.parseInt(br.readLine());
        for (int k = 0; k < K; k++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            documents = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                Document document = new Document(i, Integer.parseInt(st.nextToken()));
                documents.offer(document);
            }

            sb.append(findDocument()).append("\n");
        }
        System.out.println(sb);
    }

    private static int findDocument() {
        int cnt = 0;
        while (!documents.isEmpty()) {
            Document currentDocument = documents.poll();
            boolean isMaximumImportance = true;
            for (int i = 0; i < documents.size(); i++) {
                if (currentDocument.degreeOfImportance < documents.get(i).degreeOfImportance) {
                    isMaximumImportance = false;
                    documents.offer(currentDocument);
                    for (int j = 0; j < i; j++) {
                        documents.offer(documents.poll());
                    }
                    break;
                }
            }

            if (isMaximumImportance) {
                cnt++;
                if (currentDocument.index == M) {
                    break;
                }
            }
        }

        return cnt;
    }

    static class Document {
        int index;
        int degreeOfImportance;

        public Document(int index, int degreeOfImportance) {
            this.index = index;
            this.degreeOfImportance = degreeOfImportance;
        }
    }
}
