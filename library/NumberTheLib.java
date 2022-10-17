import java.util.*;
class NumberTheLib{
	public static long computeXOR(long n) //1 to n 
{  
    // Modulus operator are expensive  
    // on most of the computers.  
    // n & 3 will be equivalent to n % 4  
    // n % 4  
	 long x= (n&3);//it is equal to n%4  
	if(x==0)
	return n;
	if(x==1)
	return (long)1;
	if(x==2)
	return n+1;
	else 
	return (long)0;

	
}  

public static long[][] multiply(long a[][], long b[][]) 
    { 
        long mul[][] = new long[a.length][b[0].length]; 
        for (int i = 0; i < a.length; i++) 
        { 
            for (int j = 0; j < b[0].length; j++) 
            { 
                mul[i][j] = 0; 
                for (int k = 0; k < a[0].length; k++) 
                    mul[i][j] += (a[i][k] * b[k][j])%mod;
                    mul[i][j] %= mod;
            } 
        } 
      
        return mul;
    
    } 
      
    public static long[][] power(long F[][], long n) 
    { 
        
    	long[][] result = new long[F.length][F.length];
    	for(int i=0; i<F.length; i++)
    		result[i][i] = 1;
    	
    	
    	while (n > 0) {
			if (n % 2 == 1)
				result = multiply(result, F);
			
			F = multiply(F, F);
			n = n / 2;
			//System.out.println("==");
		}
    	return result;
	}
    public static long gcd(long a,long b){
		while(b>0){
			long t=b;
			b=a%b;
			a=t;
		}
		return a;
    }
    public static int countSet(int a){
		int c=0;
		while(a>0){
			a&=(a-1);
			c++;
		}
		return c;
	}

	public static long lcm(long a,long b){
		return (a*b)/gcd(a, b);
    }
    
    public static long LcmArray(long a[]){
		long ans=1;
		for(int i=0;i<a.length;i++){
			ans=lcm(ans,a[i]);
		}
		return ans;
    }
    public static long gcdArray(long a[]){
		long ans=a[0];
		for(int i=1;i<a.length;i++){
			ans=gcd(ans,a[i]);
		}
		return ans;
    }
    

    public static long pow(long x,long y,long mod){
		long ans=1;
		//If u know that mod=prime than don't do phi just write (mod-1) instead
		if(gcd(x, mod)==1){
			y=y%phi(mod);//works for both prime or not prime;
			//y=y%(mod-1); //if mod=prime;
			//y=y%phi;
		}
		x%=mod;
		while(y>0){
			if((y&1)==1){
				ans=(ans*x)%mod;
			}
			y=y>>1;
			x=(x*x)%mod;
		}
		return ans;
    }
    public static long phi(long n){
		long res=n;
		for(int i=2;i*i<n;i++){
			if(n%i==0){
				res-=res/i;
				while(n%i==0)
				n/=i;
			}
		}
		if(n>1)
		res-=res/n;
		return res;
	}

    public static  void primeFactor(long n){//Return prime factors of a number
	/*class pfactor{
		long p=0,c=0;//p is prime number & c is its count
		pfactor(long p1,long c1){
			p=p1;
			c=c1;
		}
    } */
        //Deque<pfactor> q=new LinkedList<>();
		//ArrayList<pfactor> a=new ArrayList<>();
		for(int i=2;i<=Math.sqrt(n);i++){
			if(n%i==0){
				int c=0;
				while(n%i==0){
					n/=i;
					c++;
				}
				//q.add(new pfactor(i,c));
			}
		}
		if(n>1){
			//q.add(new pfactor(n,1));
		}
		//return q;
	}


    public static int countDivisor(long n){
		int c=0;
		for(int i=1;i<=Math.sqrt(n);i++){
			if(n%i==0){
				c++;
				if(i!=n/i){
					c++;
				}
			}
		}
		return c;
	}

	public static long nCr(long n,long r)
	{
		long ans=1;
		for(long i=0;i<r;i++)
		{
			ans=(ans*(n-i))/(i+1);
		}
		return ans;
	}



	public static long[] extendedEuclideon(long a,long b){
		//ax+by=gcd(a,b) if(a,b) are relative prime than a is inverse of b & viseversa
		//int x[]=new int[2];


		//** Important --Value can be Negative---
		long x,y,gcd;
		if(b==0){
			x=1;
			y=0;
			gcd=a;
		}
		else {
			long p[]=extendedEuclideon(b, a%b);
			x=p[1];
			y=p[0]-(a/b)*p[1];
			gcd=p[2];
		}
		long point[]=new long [3];
		point[0]=x;point[1]=y;point[2]=gcd;
		return point;
	}

}