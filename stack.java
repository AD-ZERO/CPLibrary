import java.util.*;
class stack{
    public static long [] nextGreaterElementR(long a[]){//next right hand side greater element
		int n=a.length;
		Stack<Integer> st=new Stack<>();
		long ans[]=new long [n];
		Arrays.fill(ans, -1);
		st.push(0);
		for(int i=1;i<n;i++){
			while(!st.empty()&&a[st.peek()]<a[i]){
				int in=st.pop();
				ans[in]=a[i];
			}
			st.push(i);
		}
		return ans;
	}
	public static int [] nextGreaterElementIndexR(long a[]){///left to right
		int n=a.length;
        Stack<Integer> st=new Stack<>();
		int ans[]=new int [n];
		Arrays.fill(ans, n);
		st.push(0);
		for(int i=1;i<n;i++){
			while(!st.empty()&&a[st.peek()]<a[i]){
				int in=st.pop();
				ans[in]=i;
			}
			st.push(i);
		}
		return ans;
	}
	public static int[] nextGreaterElementIndexL(long a[]){//right to left
		int n=a.length;
		Stack<Integer> st=new Stack<>();
		int ans[]=new int [n];
		Arrays.fill(ans, -1);
		st.push(n-1);
		for(int i=n-2;i>=0;i--){
			while(!st.empty()&&a[st.peek()]<a[i]){
				int in=st.pop();
				ans[in]=i;
			}
			st.push(i);
		}
		return ans;
    }
    public static long MaxRectangleArea(long b[]){
		Stack<Integer> st=new Stack<>();
		long a[]=new long[b.length+1];
		for(int i=0;i<b.length;i++){
			a[i]=b[i];
		}
		long max=a[0];
		st.add(0);
		for(int i=1;i<a.length;i++){
			while(!st.empty()&&a[st.peek()]>=a[i]){
				int k=st.pop();
				if(!st.empty())
				max=Math.max(max, (i-st.peek()-1)*a[k]);
				else{
					max=Math.max(max, i*a[k]);
				}
			}
			if(st.empty()){
				max=Math.max(max, (i+1)*a[i]);
			}
			else{
				max=Math.max(max, (i-st.peek())*a[i]);
			}
			st.add(i);
		}
		
		return max;
    }
    public static int [] ConsecutiveLessOrEqualToLeft(int a[]){//Number of consecutive left hand side less elements 
		int n=a.length;
		int ans[]=new int [n];
		Stack <Integer> st=new Stack<>();
		for(int i=0;i<a.length;i++){
			if(st.empty()){
				st.add(i);
				ans[i]=1;
			}
			else{
				while(!st.empty()&&a[st.peek()]<=a[i]){
					st.pop();
				}
				if(st.empty())
				{
					ans[i]=i+1;
				}
				else
				ans[i]=i-st.peek();
				st.add(i);
			}
		}
		return ans;
}
public static String StringPattern(String s){////1[b]or3[a3[b]1[ab]] resolve it in string
	//Class object
	/*class obj{
	String s="";
	int a=0;
	} */
	Stack<obj> st=new Stack<>();
		st.add(new obj());
		int pre=0;
		for(int i=0;i<s.length();i++){
			char c=s.charAt(i);
			if(c<='9'&&c>='0'){
				pre=pre*10+c-'0';
			}
			else if(c=='['){
				st.add(new obj());
				st.peek().a=pre;
				pre=0;
			}
			else if(c>='a'&&c<='z'){
				st.peek().s+=c;
			}
			else if(c==']'){
				obj o=st.pop();
				String temp="";
				for(int j=0;j<o.a;j++){
					temp+=o.s;
				}
				st.peek().s+=temp;
			}
		}
		return st.pop().s;
}
public static int [] nextSmallerElementIndexR(long a[]){///left to right i.e next smaller right element index 
    int n=a.length;
    Stack<Integer> st=new Stack<>();
    int ans[]=new int [n];
    Arrays.fill(ans, n);
    st.push(0);
    for(int i=1;i<n;i++){
        while(!st.empty()&&a[st.peek()]>a[i]){
            int in=st.pop();
            ans[in]=i;
        }
        st.push(i);
    }
    return ans;
}
public static int[] nextsmallerElementIndexL(long a[]){//right to left i.e next smaller left element index
    int n=a.length;
    Stack<Integer> st=new Stack<>();
    int ans[]=new int [n];
    Arrays.fill(ans, -1);
    st.push(n-1);
    for(int i=n-2;i>=0;i--){
        while(!st.empty()&&a[st.peek()]>a[i]){
            int in=st.pop();
            ans[in]=i;
        }
        st.push(i);
    }
    return ans;
}

public static int [] MaxWindowSizeSmall(long a[]){//Max window size in which element is smallest
    int n=a.length;
    int left[]=nextsmallerElementIndexL(a);
    int right[]=nextSmallerElementIndexR(a);
    int dif[]=new int[n];
    for(int i=0;i<n;i++){
        dif[i]=right[i]-left[i]-1;
    }
    return dif;
}
public static int [] MaxWindowSizeLarge(long a[]){//Max window size in which element is largest
    int n=a.length;
    int left[]=nextGreaterElementIndexL(a);
    int right[]=nextGreaterElementIndexR(a);
    int dif[]=new int[n];
    for(int i=0;i<n;i++){
        dif[i]=right[i]-left[i]-1;
    }
    return dif;
}

	
}