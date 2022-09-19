import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class FastReader {
    BufferedReader br;
    StringTokenizer st;

    public FastReader(String fname) throws FileNotFoundException {
        br = new BufferedReader(new FileReader(fname));
    }

    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }

    long nextLong() {
        return Long.parseLong(next());
    }

    double nextDouble() {
        return Double.parseDouble(next());
    }

    String nextLine() throws IOException {
        st = new StringTokenizer(br.readLine());
        String str = "";
        try {
            if (st.hasMoreTokens()) {
                str = st.nextToken("\n");
            } else {
                str = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
