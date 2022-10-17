import java.util.*;
import java.io.*;

public class Library {

	public static long mod = (long) Math.pow(10, 9) + 7;
	public static InputReader sc = new InputReader(System.in);
	public static PrintWriter pw = new PrintWriter(System.out);
	public static void main(String[] args) {

		// Code starts..
		
		// code ends...

		pw.flush();
		pw.close();
	}
	public static int longestCommonSubstring(String s1,String s2){
		int dp[][]=new int [s1.length()+1][s2.length()+1];
		int max=0,in=0;
		for(int i=1;i<s1.length()+1;i++){
			for(int j=1;j<s2.length()+1;j++){
				if(s1.charAt(i-1)-s2.charAt(j-1)==0){
					dp[i][j]=1+dp[i-1][j-1];
				}
				else{
					dp[i][j]=0;
				}
				if(max<dp[i][j])
				in=i;
				max=Math.max(max, dp[i][j]);
			}
		}
		//pw.println(s1.substring(in-max, in));//substring form
		//pw.flush();
		return max;

	}
	
	
	
	
	public static int LCIS(int a[],int b[]){
			int n=a.length;
			//int a[]=scanArray(n);
			int m=b.length;
			//int b[]=scanArray(m);
			int c[]=new int[m];
			int max=0;
			for(int i=0;i<n;i++){
				int pre=0;
				for(int j=0;j<m;j++){
					if(a[i]==b[j]&&pre+1>c[j])
					c[j]=pre+1;
					if(a[i]>b[j]&&pre<c[j])
					pre=c[j];
					max=Math.max(max, c[j]);
				}
			}
			return max;
	}
	public static int LongestPalindromesubSequence(String s){
		int dp[][]=new int[s.length()+1][s.length()+1];
		for(int i=0;i<=s.length();i++){
			dp[i][i]=1;
		}
		int max=0;
		for(int i=2;i<=s.length();i++){
			for(int j=1;j+i-1<=s.length();j++){
				int k=j+i-1;
				dp[j][k]=Math.max(dp[j+1][k], dp[j][k-1]);
				if(s.charAt(j-1)==s.charAt(k-1)){
					dp[j][k]=Math.max(2+dp[j+1][k-1],dp[j][k]);
				}
				max=Math.max(dp[j][k], max);
			}
		}
		return max;
	}
	public static String LongestPalindromSubString(String s){
		int dp[][]=new int[s.length()+1][s.length()+1];
		for(int i=0;i<=s.length();i++){
			dp[i][i]=1;
		}
		int max=1;
		int in=1;
		for(int i=2;i<=s.length();i++){
			for(int j=1;j+i-1<=s.length();j++){
				int k=j+i-1;
				if(s.charAt(j-1)==s.charAt(k-1)&&(dp[j+1][k-1]!=0||i==2)){
					dp[j][k]=2+dp[j+1][k-1];
				}
				if(max<dp[j][k]){
					max=dp[j][k];
					in=j;
				}
				else if(max==dp[j][k]){
					in=Math.min(in, j);
				}
			}
		}
		return s.substring(in-1, in+max-1);
	}
	
	
	public static long dp[][]=new long[9000][9000];
	public static long NumberOfSubsequence(int [] a,int end,int k){
		
		if(dp[end][k]!=0)
		return dp[end][k];
		if(k==0){
			dp[end][k]=1;
			return 1;	
		}
		if(end==0){
			if(k==1){
				dp[end][k]=a[end];
				return a[end];
			}
			else{
				return 0;
			}
		}
		dp[end][k]= (NumberOfSubsequence(a, end-1, k)+(NumberOfSubsequence(a, end-1, k-1)*a[end])%mod)%mod;
		return dp[end][k];
	}
	public static void NumberOfSubsequence(){
		// Code starts..
		StringBuilder out =new StringBuilder();
		int n=sc.nextInt();
		int k=sc.nextInt();
		int a[]=new int[8001];
		//int a1[]=new int[n];
		//HashMap<Integer,Integer> h=new HashMap<>();
		for(int i=0;i<n;i++){
			a[sc.nextInt()]++;
		}
		long s=1;
		for(int i=1;i<8001;i++){
			dp[i][0]=1;
			dp[i][1]=dp[i-1][1]+a[i];
		}
		s+=dp[8000][1];
		//pw.println(dp[8000][1]);
		s%=mod;
		for(int i=2;i<=Math.min(8000, k);i++){
			for(int j=i;j<8001;j++){
				dp[j][i]=dp[j-1][i]+(dp[j-1][i-1]*a[j]%mod);
			}
			s+=dp[8000][i];
			s%=mod;
			//pw.println(dp[8000][i]);
		}


		//for(int i=1;i<=Math.min(8000, k);i++){
		//	s+=NumberOfSubsequence(a, t, i);
			//pw.println(NumberOfSubsequence(a, t, i));
		//	s%=mod;
		//}
		out.append(s);



		pw.println(out);
	}
	


	public static boolean [] sieveOfEratosthenes(int n) 
    { 
        // Create a boolean array "prime[0..n]" and initialize 
        // all entries it as true. A value in prime[i] will 
        // finally be false if i is Not a prime, else true. 
        boolean prime[] = new boolean[n+1]; 
        Arrays.fill(prime, true);
          
        for(int p = 2; p*p <=n; p++) 
        { 
            // If prime[p] is not changed, then it is a prime 
            if(prime[p] == true) 
            { 
                // Update all multiples of p 
                for(int i = p*p; i <= n; i += p) 
                    prime[i] = false; 
            } 
		}
		return prime;
	}
	

	public static Comparator<Integer[]> column(int i){
		return 
		new Comparator<Integer[]>() {
			@Override
			public int compare(Integer[] o1, Integer[] o2) {
					Integer quantityOne = o1[i];
				Integer quantityTwo = o2[i];
				// reverse sort on quantity
				return quantityOne.compareTo(quantityTwo);
			}
		};
	}

	public static String doublePrecision(double a){
		return String.format("%.2f", a);
	}
	
	static int binomialCoeff(int n, int k) 
    { 
    int C[][] = new int[n+1][k+1]; 
    int i, j; 
      
    for (i = 0; i <= n; i++) 
    { 
        for (j = 0; j <= Math.min(i, k); j++) 
        {  
            if (j == 0 || j == i) 
                C[i][j] = 1; 
                else
                C[i][j] = C[i-1][j-1] + C[i-1][j]; 
          } 
     } 
       
    return C[n][k]; 
    } 
	public static int shift(int a[],int n){
		int d[][]=new int[2][a.length];
		if(a.length%n==0){
			for(int i=0;i<n;i++){
				//d[0][i]=0;
				for(int j=i;j<a.length;j+=n){
					//if((d[0][j]==0&&a[j]==1)||(d[0][j]==1&&a[j]==0))
					d[1][j]=d[0][j]^a[j];
					if(j+n<a.length)
					d[0][j+n]=d[1][j];
					else{
						if(d[1][j]!=d[0][i])
						return 0;
					}
				}
			}
		}
		else{
			return shift(a, gcd(a.length,n));
		}
 
		//pw.println(n);
		return 1;
	}
	public static int gcd(int a,int b){
		if(b==0)
		return a;
		else
		return gcd(b,a%b);
	}
	
	
	public static long dpPermutation1(int n, int k){//permutation with recurence of sum n with max number k
		long dp[]=new long[n+1];
		long s=0;
		dp[0]=1;
		for(int i=1;i<=Math.min(n, k);i++){
			dp[i]=s+1;
			s+=s+1;
			s%=mod;
		}
		for(int i=Math.min(n, k)+1;i<=n;i++){
			dp[i]=s;
			s+=(s-dp[i-k]+mod);
			s%=mod;
		}
		return dp[n];
	}
	public static long dpPermutation2(int n,int k,int d){
		//permutation with recurence of sum n with max number k & lowest d
		return (dpPermutation1(n,k)-dpPermutation1(n,d-1)+mod)%mod;

	}
	public static int LargestIncreasingSequenceWithOneElementChange(int a[]){
		int n=a.length;
		int l[]=new int [n];
		int r[]=new int [n];
		Arrays.fill(l, 1);
		Arrays.fill(r, 1);
		int t=0,max=1;
		for(int i=1;i<n;i++){
			if(a[i]>a[i-1])
			l[i]=l[i-1]+1;
			if(a[n-i-1]<a[n-i])
			r[n-i-1]=r[n-i]+1;
		}
		for(int i=0;i<n-2;i++){
			if(a[i]+1<a[i+2])
			max=Math.max(max, l[i]+r[i+2]+1);
			else
			max = Math.max(max, Math.max(l[i]+1, r[i+2]+1));
		}
		if(n>=2)
		max=Math.max(max, Math.max(l[n-2]+1, r[1]+1));
		return max;
	}
	
	public static int numOfPlus(String a[]){
		int t=0;
		int x=0,y=0;
		for(int i=1;i<a.length-1;i++){
			for(int j=1;j<a[i].length()-1;j++){
				if(a[i].charAt(j)=='*'&&a[i].charAt(j-1)=='*'&&a[i].charAt(j+1)=='*'&&a[i-1].charAt(j)=='*'&&a[i+1].charAt(j)=='*')
				t++;
			}
		}

		return t;
	}
	
	public static int NumberStringmidTerm(String s){//
		int mid=0,n=s.length(),r=n,f=0;
		for(int i=n/2;i<n;i++){
			if(s.charAt(i)!='0'){
				r=i;
				break;
			}
		}
		for(int i=n/2;i>=0;i--){
			if(s.charAt(i)!='0'){
				f=i;
				break;
			}
		}
		if(r>n-f)
		mid=f;
		else if(r<n-f)
		mid=r;
		else{
			if(s.substring(0,r).compareTo(s.substring(f))<0){
				mid=r;
			}
			else
			mid=f;
		}
		if(n%2==1&&r==f){
			if(s.substring(0,r+1).compareTo(s.substring(f))<0){
				mid=r+1;
			}
		}
		return mid;
	}
	public static int[] scanArray(int n){
		int a[]=new int [n];
		for(int i=0;i<n;i++)
		a[i]=sc.nextInt();

		return a;
	}
	public static String [] scanStrings(int n){
		String a[]=new String [n];
		for(int i=0;i<n;i++)
		a[i]=sc.nextLine();

		return a;
	}
	static class InputReader {

		private final InputStream stream;
		private final byte[] buf = new byte[8192];
		private int curChar, snumChars;
		private SpaceCharFilter filter;

		public InputReader(InputStream stream) {
			this.stream = stream;
		}

		public int snext() {
			if (snumChars == -1)
				throw new InputMismatchException();
			if (curChar >= snumChars) {
				curChar = 0;
				try {
					snumChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (snumChars <= 0)
					return -1;
			}
			return buf[curChar++];
		}

		public int nextInt() {
			int c = snext();
			while (isSpaceChar(c)) {
				c = snext();
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = snext();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = snext();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public long nextLong() {
			int c = snext();
			while (isSpaceChar(c)) {
				c = snext();
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = snext();
			}
			long res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = snext();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public int[] nextIntArray(int n) {
			int a[] = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}

		public String readString() {
			int c = snext();
			while (isSpaceChar(c)) {
				c = snext();
			}
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = snext();
			} while (!isSpaceChar(c));
			return res.toString();
		}

		public String nextLine() {
			int c = snext();
			while (isSpaceChar(c))
				c = snext();
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = snext();
			} while (!isEndOfLine(c));
			return res.toString();
		}

		public boolean isSpaceChar(int c) {
			if (filter != null)
				return filter.isSpaceChar(c);
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

		private boolean isEndOfLine(int c) {
			return c == '\n' || c == '\r' || c == -1;
		}

		public interface SpaceCharFilter {
			public boolean isSpaceChar(int ch);
		}
	}
}