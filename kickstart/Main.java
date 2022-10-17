import java.util.*;

// Working program using Reader Class
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

class Solution {
    public static Reader sc = new Reader();

    public static int[] reverseArray(int a[]) {
        int n = a.length;
        int newa[] = new int[n];
        for (int i = 0; i < n; i++) {
            newa[i] = a[n - i - 1];
        }
        return newa;
    }

    public static void main(String args[]) throws IOException, FileNotFoundException {
        PrintWriter pw = new PrintWriter(System.out);
        int t = sc.nextInt();
        for (int test = 1; test <= t; test++) {
            int n = sc.nextInt();
            int a[] = scanIntArray(n);
            long ans = 0, c = 0;
            Stack<Long[]> st = new Stack<>();
            for (int i = 0; i < n; i++) {
                int temp = a[i];
                while (temp < 0 && st.size() > 0) {
                    Long[] peek = st.pop();
                    temp += peek[0];
                }
                if (temp >= 0) {
                    long count = st.size();
                    long pre = 0;
                    if (count > 0)
                        pre = st.peek()[1];
                    pre += (count + 1) * temp;
                    Long x[] = new Long[2];
                    x[0] = (long) temp;
                    x[1] = pre;
                    st.add(x);
                    ans += pre;
                }
                // for (int j = 0; j < st.size(); j++)
                // pw.print(Arrays.toString(st.get(j)));
                // pw.print("\n");
            }

            pw.println("Case #" + test + ": " + ans);
        }
        pw.flush();
        pw.close();

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
            byte[] buf = new byte[64]; // line length
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

        public String readString() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n' || c == ' ') {
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