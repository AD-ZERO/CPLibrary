import java.util.*;

class stringLib{

	long compute_hash(String s) {
		int p = 31;
		int m = (int)1e9 + 9;
		long hash_value = 0;
		long p_pow = 1;
		char s1[]=s.toCharArray();
		for (char c : s1) {
			hash_value = (hash_value + (c - 'a' + 1) * p_pow) % m;
			p_pow = (p_pow * p) % m;
		}
		return hash_value;
	}

	public static int[] GetLps(String s){
		int n=s.length();
		int a[]=new int[n];
		int j=0;
		for(int i=1;i<n;i++){
			if(s.charAt(i)==s.charAt(j)){
				j++;
				a[i]=j;
			}
			else if(j>0){
				while(j>0&&s.charAt(i)!=s.charAt(j)){
					j=a[--j];
				}
				if(s.charAt(i)==s.charAt(j))
				a[i]=++j;
			}
		}
		//pw.println(Arrays.toString(a));

		return a;

	}

	public static int [] Zfunction(String s){
        int n=s.length();
        int a[]=new int[n];
        int r1=0,r2=1;
        for(int i=1;i<n;){
            int f=0;
            while(r2<n&&s.charAt(r1)==s.charAt(r2)){
                r1++;
                r2++;
            }
            a[i]=r1;
            i++;
            int l2=1;
            for(;i<r2;i++){
                if(i+a[l2]<r2)
                a[i]=a[l2++];
                else{
                    f=1;
                    r1=r2-i;
                    break;
                }
            }
            if(f==0){
                r2=i;
                r1=0;
            }

        }
       return a;
    }
    public static int[] suffixArr(String s){
            int n=s.length();
            int h[]=new int[129];
            int p[]=new int[n];
            int c[]=new int[n];
            for(int i=0;i<n;i++){
                h[s.charAt(i)]++;
            }
            for(int i=1;i<=128;i++){
                h[i]+=h[i-1];
            }
            for(int i=n-1;i>=0;i--){
                p[--h[s.charAt(i)]]=i;
            }
            int t=0;
            c[p[0]]=0;
            for(int i=1;i<n;i++){
               if(s.charAt(p[i])!=s.charAt(p[i-1]))
               t++;
               c[p[i]]=t;
            }
            for(int k=0;(1<<k)<n;k++){
                int j=1<<k;

                int pn[]=new int[n];
                for(int i=0;i<n;i++){
                    pn[i]=(p[i]-j+n)%n;
                }

                
                int cnt[]=new int[n];

                for(int i=0;i<n;i++){
                    cnt[c[pn[i]]]++;
                }
                for(int i=1;i<n;i++){
                    cnt[i]+=cnt[i-1];
                }
                for(int i=n-1;i>=0;i--){
                    p[--cnt[c[pn[i]]]]=pn[i];
                }

                int cn[]=new int[n];
                cn[p[0]]=0;
                t=0;
                for(int i=1;i<n;i++){
                    if((c[p[i]]!=c[p[i-1]])||c[(p[i]+j)%n]!=c[(p[i-1]+j)%n]){
                        t++;
                    }
                    cn[p[i]]=t;
                }
                for(int i=0;i<n;i++){
                    c[i]=cn[i];
                }
            }
            return p;
        }
        public static int[] LCP(int p[],String s){
            int n=s.length();
            int rank[]=new int[n];
            for(int i=0;i<n;i++){
                rank[p[i]]=i;
            }
            int k=0;
            int lcp[]=new int[n];
            for(int i=0;i<n;i++){
                if(rank[i]==0){
                    k=0;
                    continue;
                }
                
                int j=p[rank[i]-1];
                //pw.println(i+" "+j+" "+k);
                while(i+k<n&&j+k<n&&s.charAt(i+k)==s.charAt(j+k)){
                    k++;
                }
                lcp[rank[i]]=k;
                if(k>0)
                k--;
            }
            return lcp;
        }
    public static void StringPermutations(String s,int v[],String r){
		int f=0;
		for(int i=0;i<v.length;i++){
			if(v[i]==0){
				f=1;
				break;
			}
		}
		if(f==0){
			pw.print(r+" ");
		}
		else{
			for(int i=0;i<v.length;i++){
				if(v[i]==0){
					int v1[]=v.clone();
					v1[i]=1;
					StringPermutations(s, v1, r+s.charAt(i));
				}
			}
		}
    }
    
    public static StringBuilder stringSum(String s1,String s2){
		StringBuilder s=new StringBuilder();
		int c=0;
		int l1=s1.length(),l2=s2.length();
		for(int i=0;i<Math.min(l1,l2);i++){
			int t=s1.charAt(l1-1-i)+s2.charAt(l2-i-1)-2*'0'+c;
			c=t/10;
			s.append(t%10);
		}
		if(l1>l2){
			for(int i=l2;i<l1;i++){
				int t=s1.charAt(l1-i-1)+c-'0';
				c=t/10;
				s.append(t%10);
			}
		}
		else{
			for(int i=l1;i<l2;i++){
				int t=s2.charAt(l2-i-1)+c-'0';
				c=t/10;
				s.append(t%10);
			}
		}
		if(c!=0)
		s.append(c);
		return s.reverse();
	}
	public static String stringMultiply(String s1,String s2){
		StringBuilder s=new StringBuilder();
		int c=0;
		for(int i=s1.length()-1;i>=0;i--){
			c=0;
			StringBuilder sf=new StringBuilder();
			for(int j=s2.length()-1;j>=0;j--){
				int k=(s1.charAt(i)-'0')*(s2.charAt(j)-'0')+c;
				c=k/10;
				sf.append(k%10);
			}
			if(c!=0)
			sf.append(c);
			sf.reverse();
			for(int j=s1.length()-1;j>i;j--)
			sf.append("0");
			s=stringSum(s.toString(), sf.toString());
		}

		return s.toString();
	}
	public static String stringMinus(String s1,String s2){
		int c=0;
		StringBuilder s=new StringBuilder();
		int l1=s1.length();
		int l2=s2.length();
		if(s1.length()>=s2.length()){
			if(l1==l2&&s1.compareTo(s2)<0)
			return "-"+stringMinus(s2, s1);
			for(int i=1;i<=s2.length();i++){
				int t=(s1.charAt(l1-i)-'0')-(s2.charAt(l2-i)-'0')-c;
				if(t<0){
					c=1;
					t+=10;
				}
				else
				c=0;
				s.append(t);
			}
			for(int i=l2+1;i<=l1;i++){
				int t=(s1.charAt(l1-i)-'0')-c;
				if(t<0){
					c=1;
					t+=10;
				}
				else
				c=0;
				s.append(t);
			}
		}
		else{
			return "-"+stringMinus(s2, s1);
		}
		return s.reverse().toString();

    }
    
    public static String reverseString(String s){
		StringBuilder input1 = new StringBuilder(); 
        input1.append(s);  
		input1 = input1.reverse();
		return input1.toString();
	}
    public static int diferenceCharacterInBitString(String s1,String s2){
		//For example, let s1=00110, and s2=11000. In these strings, the first, second, third and fourth positions are different.
		//Vus the Cossack counts the number of such substrings c such that f(s1,s2) is even(1186/c) for all substring of s1 of size s2.
		// it is solve using parity of(s1==s2)then diff is even otherwise odd;
		int t1=0,t2=0,c=0;
		for(int i=0;i<s2.length();i++){
			if(s1.charAt(i)=='1')
			t1++;
			if(s2.charAt(i)=='1')
			t2++;
		}
		if(t1%2==t2%2)
		c++;

		for(int i=s2.length();i<s1.length();i++){
			if(s1.charAt(i)=='1')
			t1++;
			if(s1.charAt(i-s2.length())=='1')
			t1--;
			if(t1%2==t2%2)
			c++;
		}
		return c;
	}

}