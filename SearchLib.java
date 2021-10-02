import java.util.*;

class SearchLib{
    public static long quickSelect(long a[],int s,int e,int k){
		int i=quickIndex(a,s,e);
		if(i==k){
			return a[i];
		}
		else if(i<k){
			return quickSelect(a, i+1, e, k);
		}
		else{
			return quickSelect(a, s, i-1, k);
		}
	}
	public static int quickIndex(long a[],int s,int e){
		long v=a[s];
		int l=s+1;
		int r=e;
		while(l<r){
			while(l<=e&&a[l]<v){
				l++;
			}
			while(s<=r&&a[r]>=v){
				r--;
			}
			if(l<r){
				long t=a[l];
				a[l]=a[r];
				a[r]=t;
				l++;r--;
			}
		}
		if(l>e||a[l]>v)
		l--;
		a[s]=a[l];
		a[l]=v;
		return l;
 
	}
    
    public static int floorSerch(long a[],int l,int r,long k){
		if(l>r){
			return -1;
		}
		
		int mid=(l+r)/2;
		if(a[mid]==k){
			return mid;
		}
		else if(mid+1<a.length&&a[mid]<k&&a[mid+1]>k){
			return mid;
		}
		else if(a[a.length-1]<k){
			return a.length-1;
		}
		else if(a[mid]<k){
			return floorSerch(a, mid+1, r, k);
		}
		else {
			return floorSerch(a, l, mid-1, k);
		}
	}
 
	public static int CeilSerch(long a[],int l,int r,long k){
		if(l>r){
			return -1;
		}
		
		int mid=(l+r)/2;
		if(a[mid]==k){
			return mid;
		}
		else if(mid+1<a.length&&a[mid]<k&&a[mid+1]>k){
			return mid+1;
		}
		else if(a[0]>k){
			return 0;
		}
		else if(a[mid]<k){
			return CeilSerch(a, mid+1, r, k);
		}
		else {
			return CeilSerch(a, l, mid-1, k);
		}
		
		
	}
}