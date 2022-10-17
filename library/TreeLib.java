import java.util.*;
class TreeLib{
	public static int firstNode(int a,int p[]){
		if(a!=p[a]){
			p[a]=firstNode(p[a], p);
		}
		return p[a];
	}
	public static void Union(int a,int b,int p[]){
		 a=firstNode(a, p);
		 b=firstNode(b, p);
		/*if(a!=b){
			if(r[a]<r[b]){
				p[a]=b;
			}
			else if(r[a]>r[b]){
				p[b]=a;
			}
			else{
				r[b]++;
				p[a]=b;
			}
		}*/
		if(a!=b)
		p[a]=b;//if no rank than 
	}
	public static int hieght(int r,int h[],ArrayList<ArrayList <Integer>> a,int vis[],int p[]){//bottom Up
		vis[r]=1;
		for(int i=0;i<a.get(r).size();i++){
			int v=a.get(r).get(i);
			if(vis[v]==0){
				p[v]=r;
				h[r]=Math.max(h[r], 1+hieght(v, h, a, vis,p));
			}
		}
		return h[r];
	}
	public static void hieghtBfs(int r,int h[],ArrayList<ArrayList <Integer>> a,int p[],int vis[]){/// top to bottom
		Deque<Integer> q=new LinkedList<>();
		q.add(r);
		int c=1;
		p[r]=-1;
		vis[r]=1;
		while(q.size()>0){
			int l=q.size();
			while(l-->0){
				int v=q.remove();
				h[v]=c;
				for(int i=0;i<a.get(v).size();i++){
					int u=a.get(v).get(i);
					if(vis[u]==0){
						p[u]=v;
						vis[u]=1;
						q.add(u);
					}
				}
			}
			c++;
		}
	}
	public static String treeCode(int r,ArrayList<ArrayList<Integer>> a,int vis[]){
		String s="";
		vis[r]=1;
		ArrayList<String> l=new ArrayList<>();
		for(int i=0;i<a.get(r).size();i++){
			int u=a.get(r).get(i);
			if(vis[u]==0)
			l.add(treeCode(u, a, vis));
		}
		Collections.sort(l);
		for(int i=0;i<l.size();i++){
			s+=l.get(i);
		}
		return "("+s+")";
	}
	public static int daimeterUtil(int r,ArrayList<ArrayList <Integer>> a,int h[],int vis[]){
		vis[r]=1;
		int m1=0,m2=0,d=0;
		for(int i=0;i<a.get(r).size();i++){
			int u=a.get(r).get(i);
			if(vis[u]!=1){
				if(h[u]+1>m1){
					m2=m1;
					m1=h[u]+1;
				}
				else if(h[u]+1>m2){
					m2=h[u]+1;
				}
			}
		}
		d=Math.max(m1+m2, d);
		for(int i=0;i<a.get(r).size();i++){
			int u=a.get(r).get(i);
			if(vis[u]!=1){
				d=Math.max(daimeterUtil(u, a, h, vis), d);
			}
		}
		return d;

	}
	public static int daimeter(int r,ArrayList<ArrayList <Integer>> a){
		int n=a.size();
		int h[]=new int[n];
		int p[]=new int[n];
		hieght(r, h, a, new int[n], p);
		return daimeterUtil(r, a, h, new int[n]);
	}


	public static void centerTree(ArrayList<ArrayList<Integer>> a){
		int n=a.size();
		int d[]=new int[n];
		int vis[]=new int[n];
		Deque<Integer> q= new LinkedList<>();
		for(int i=0;i<n;i++){
			d[i]=a.get(i).size();
			if(d[i]==1)
			{
				vis[i]=1;
				q.add(i);
				//d[i]=0;
			}
		}
		//pw.println(Arrays.toString(d));
		//Deque<Integer> center=new LinkedList<>();
		int rc=0;
		while(q.size()>0){
			
			if(rc+q.size()==n)
			break;
			int l=q.size();
			while(l-->0){
				int v=q.remove();
				//pw.print(v+" ");
				rc++;
				for(int i=0;i<a.get(v).size();i++){
					int u=a.get(v).get(i);
					d[u]--;
					if(d[u]==1){
						q.add(u);
						//d[u]=0;
					}
				}
			}
			//pw.print("\n");
		}

		while(q.size()>0){
			pw.println(q.remove());
		}
		
	}


    void bfs(Node root){
        if(root==null){return;}
        Deque <Node> qu=new LinkedList<>();
        qu.add(root);
        while(qu.size()>0){
            int n=qu.size();
            while(n-->0){
                Node temp=qu.remove();
                int l=0,r=0;
                if(temp.left!=null){
                 l=temp.left.data;
                 qu.add(temp.left);
                }
                if(temp.right!=null){
                 r=temp.right.data;
                 qu.add(temp.right);
                }
            }
        }
    }





    public static void printPostOrder(int in[], int pre[])
	{
		if(in.length==0){
			return;
		}
		int root=in[0];
		HashSet<Integer> h=new HashSet<>();
		for(int i=0;i<in.length;i++){
			h.add(in[i]);
		}
		for(int i=0;i<pre.length;i++){
			if(h.contains(pre[i])){
				root=pre[i];
				break;
			}
		}
		int s=0;
		for(int i=0;i<in.length;i++){
			if(root==in[i]){
				s=i;
				break;
			}
		}
		printPostOrder(Arrays.copyOfRange(in, 0, s), pre);
		printPostOrder(Arrays.copyOfRange(in, s+1, in.length), pre);
		System.out.print(root+" ");

    }
    


    public static void printPreOrder(int in[], int pos[])
	{
		if(in.length==0){
			return;
		}
		int root=in[0];
		HashSet<Integer> h=new HashSet<>();
		for(int i=0;i<in.length;i++){
			h.add(in[i]);
		}
		for(int i=0;i<pos.length;i++){
			if(h.contains(pos[pos.length-i-1])){
				root=pos[pos.length-i-1];
				break;
			}
		}
		int s=0;
		for(int i=0;i<in.length;i++){
			if(root==in[i]){
				s=i;
				break;
			}
		}
		System.out.print(root+" ");
		printPreOrder(Arrays.copyOfRange(in, 0, s), pos);
		printPreOrder(Arrays.copyOfRange(in, s+1, in.length), pos);

    }
    



    public static int max=0,min=0;
    public static int treeWidth(Node root)//Gives the width of the tree 
	{
	    //Add your code here.
	    if(root==null){return 0;}
	    max=0;min=0;
	    verticalWidth1(root,1);
	    return max-min;
	}
	public static void verticalWidth1(Node root,int l){
	    if(root==null){return;}
	    if(l>0){max=Math.max(max,l);}
	    else{
	        min=Math.min(min,l-1);
	    }
	    verticalWidth1(root.left,l+1);
	    verticalWidth1(root.right,l-1);
	}
}
class SparseTable{
	int p[][];
	int log[];
	SparseTable(int a[]){
		log=new int[a.length];
		for(int i=2;i<a.length;i++){
			log[i]=log[i/2]+1;
		}
		p=new int[a.length][25];
		
		for(int i=0;i<a.length;i++){
			p[i][0]=a[i];
		}
		
		build(p);
		//System.out.println(Arrays.toString(log));
	}
	void build(int p[][]){
		for(int h=1;h<25;h++){
			for(int i=0;i+(1<<(h-1))<p.length;i++){
				p[i][h]=compare(p[i][h-1], p[i+(1<<(h-1))][h-1]);
			}
		}
	}
	int getmin(int l,int r){
		int k=log[r-l+1];
		
			return Math.min(p[l][k],p[r-(1<<k)+1][k]);
		
	}
	int compare(int a,int b){
		return Math.min(a, b);
	}
}
class SegmentTree{
	int s[],n;
	SegmentTree(int a[]){
		n=a.length;
		int l=(int)Math.ceil(Math.log(n)/Math.log(2));
		l=2*(int)Math.pow(2,l)-1;
		s=new int[l];
		createSegmentTreeUtil(a, 0, 0, a.length-1);
	}
	
	int createSegmentTreeUtil(int a[],int root,int l,int r){
		if(l==r)
		s[root]=a[l];
		else
		s[root]= Compare(createSegmentTreeUtil(a, 2*root+1, l, (l+r)/2), createSegmentTreeUtil(a,2*root+2, (l+r)/2+1,r));
		return s[root];
	}
	int getValue(int gl,int gr){
		return getValueUtil(0, 0, n-1, gl, gr);
	}
	int getValueUtil(int root,int l,int r,int gl,int gr){
		if(l>=gl&&r<=gr){
			return s[root];
		}
		if(l>gr||r<gl){
			return Integer.MAX_VALUE;
		}
		return Compare(getValueUtil(2*root+1, l, (l+r)/2, gl, gr), getValueUtil(2*root+2, (l+r)/2+1, r, gl, gr));
	}
	void update(int p,int k){
		updateUtil(p, k,0,0,n-1);
	}
	int updateUtil(int p,int k,int root,int l,int r){
		if(l==r&&l==k){
			return s[root]=p;
		}
		else if(l>k||r<k)
		return s[root];
		else{
			return s[root]=Compare(updateUtil(p, k, 2*root+1, l, (l+r)/2), updateUtil(p, k, 2*root+2,(l+r)/2+1,r ));
		}
	}
	int Compare(int a,int b){
		return a+b;
	}
}

class Bit{//1...n
	int a[];
	Bit(int n){
		a=new int[n+1];
	}
	void update(int i,int delta){
		while(i<a.length){
			a[i]+=delta;
			i+=i&(-i);
		}
	}
	int query(int i){
		int sum=0;
		while(i>0){
			sum+=a[i];
			i-=i&(-i);
		}
		return sum;
	}
}
class Bit2D{
	int a[][];
	Bit2D(int n){
		a=new int[n+1][n+1];
	}
	void update(int x,int y,int delta){
		while(x<a.length){
			int i=y;
			while(i<a.length){
				a[x][i]+=delta;
				i+=i&(-i);
			}
			x+=x&(-x);
		}
	}
	int query(int x,int y){
		int sum=0;
		while(x>0){
			int i=y;
			while(i>0){
				sum+=a[x][i];
				i-=i&(-i);
			}
			x-=x&(-x);
		}
		return sum;
	}

}
class Treapsfunction{
	long mod=(int)1e9+9;
	long factor=31;
	long pow[]=new long[3*(int)1e5+5];
	long j=1;
	public void buildfa(){
		for(int i=0;i<pow.length;i++){
			pow[i]=j;
			j*=31;
			j%=mod;
		}
	}
	
	public Treaps join(Treaps left,Treaps right){
		prop(left);
		prop(right);
		if(left==null)return right;
		if(right==null)return left;
		
		if(right.priority>left.priority){
			left.right=join(left.right, right);
			recalc(left);
			return left;
		}
		else{
			right.left=join(left, right.left);
			recalc(right);
			return right;
		}
	}
	public Treaps[] split(int v,Treaps a){
		prop(a);
		Treaps tr[]={null,null};
		if(a==null)return tr;
		
		int k=1+size(a.left);
		if(size(a.left)>=v){
			Treaps[] kid=split(v, a.left);
			a.left=kid[1];
			recalc(a);
			tr[0]=kid[0];
			tr[1]=a;
			return tr;
		}
		Treaps[] kid=split(v-k, a.right);
		a.right=kid[0];
		recalc(a);
		tr[1]=kid[1];
		tr[0]=a;
		return tr;
	}
	public void prop(Treaps a){
		if(a==null)return;
		if(a.toProp==0&&a.flip==0&&a.slize==0)return;
		
		a.val+=a.toProp;
		if(a.left!=null)a.left.toProp+=a.toProp;
		if(a.right!=null)a.right.toProp+=a.toProp;
		
		a.sum+=a.size*a.toProp;
		a.toProp=0;
	}
	public void recalc(Treaps a){
		if(a==null)return;
		a.size=1;
		
		a.sum=a.val;
		if(a.val==0){
			a.left0=a.right0=a.tot=1;
		}
		else{
			a.right1=a.left1=a.tot=1;
		}
		
		
		if(a.left!=null)a.size+=a.left.size;
		if(a.right!=null)a.size+=a.right.size;
		
		
		if(a.left!=null)a.sum+=a.left.sum+a.left.toProp*size(a.left);
		if(a.right!=null)a.sum+=a.right.sum+a.right.toProp*size(a.right);

	}
	public int size(Treaps a){
		if(a==null) return 0;
		return a.size;
	}
	
	public void Inorder(ArrayList<Long> ans,Treaps tr){
		if(tr==null)return;
		prop(tr);
		Inorder(ans, tr.left);
		ans.add(tr.val);
		Inorder(ans, tr.right);
	}
	
}
class Treaps{
	Treaps left,right=null;
	long val;int priority=0;
	int toProp=0;
	int size=0;
	long sum=0;
	int flip=0;
	long slize=0;
	int left1=0;
	int left0=0;
	int right1=0,right0=0;
	int tot=0;
	
	public Treaps(int val,int priority){
		this.val=val;
		this.priority=priority;
		size=1;
		//sum=val;
		if(val==0){
			left0=right0=tot=1;
		}
		else{
			right1=left1=tot=1;
		}
	}
	
}


