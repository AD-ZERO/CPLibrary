import java.util.*;

// Working program using Reader Class
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

class Main {
    public static Reader sc = new Reader();

    public static boolean check(int a[][]) {
        int n = a.length;
        int m = a[0].length;
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (a[i][j] == 1) {
                    int d = a[i - 1][j] + a[i + 1][j] + a[i][j + 1] + a[i][j - 1];
                    if (d < 2)
                        return false;
                }
            }
        }
        return true;
    }

    public static void main(String args[]) throws IOException, FileNotFoundException {
        PrintWriter pw = new PrintWriter("out.txt");
        int t = sc.nextInt();
        // pw.print(t);
        for (int test = 1; test <= t; test++) {
            int n = sc.nextInt();
            int a[] = scanIntArray(n);
            Bit bit[]=new Bit[1000000];
            for(int i=0;i<n;i++)bit[i]=new Bit(n);
            for(int i=0;i<n;i++){
                bit[a[i]].update(i+1, 1);
            }
            int m=sc.nextInt();
            for(int i=0;i<n;i++){
                int qi=sc.nextInt();
                
                if(qi==1){
                    int in=sc.nextInt()-1;
                    int v=sc.nextInt();
                    bit[a[in]].update(i, -1);
                    a[in]=v;
                    bit[a[in]].update(i, 1);
                }
                else{
                    int l=sc.nextInt();
                    int r=sc.nextInt();
                }
            }


            // pw.println("Case #" + test + ": " + ans);
        }
        pw.flush();
        pw.close();

    }

    public static int[] get_dif(int pre[][], int l, int r) {
        int ans[] = new int[26];
        l--;
        for (int i = 0; i < 26; i++) {
            ans[i] = pre[r][i];
            if (l >= 0)
                ans[i] -= pre[l][i];
        }
        return ans;
    }

    public static int[] scanIntArray(int n) throws IOException {
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        return a;
    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 32;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(
                    new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[1000000]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') {
                    if (cnt != 0) {
                        break;
                    } else {
                        continue;
                    }
                }
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0,
                    BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }
}

class Bit {// 1...n
    int a[];

    Bit(int n) {
        a = new int[n + 1];
    }

    void update(int i, int delta) {
        while (i < a.length) {
            a[i] += delta;
            i += i & (-i);
        }
    }

    int query(int i) {
        int sum = 0;
        while (i > 0) {
            sum += a[i];
            i -= i & (-i);
        }
        return sum;
    }
}