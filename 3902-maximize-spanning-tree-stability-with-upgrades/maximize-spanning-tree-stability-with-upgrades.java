
class Solution {

    class DSU {
        int[] parent, rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];

            for(int i=0;i<n;i++) parent[i]=i;
        }

        int find(int x){
            if(parent[x]!=x)
                parent[x]=find(parent[x]);
            return parent[x];
        }

        boolean union(int a,int b){
            int pa=find(a);
            int pb=find(b);

            if(pa==pb) return false;

            if(rank[pa]<rank[pb]) parent[pa]=pb;
            else if(rank[pb]<rank[pa]) parent[pb]=pa;
            else{
                parent[pb]=pa;
                rank[pa]++;
            }
            return true;
        }
    }

    public int maxStability(int n, int[][] edges, int k) {

        int maxS=0;
        for(int[] e:edges)
            maxS=Math.max(maxS,e[2]);

        int left=0,right=2*maxS;
        int ans=-1;

        while(left<=right){

            int mid=(left+right)/2;

            if(canBuild(n,edges,k,mid)){
                ans=mid;
                left=mid+1;
            }
            else
                right=mid-1;
        }

        return ans;
    }

    boolean canBuild(int n,int[][] edges,int k,int target){

        DSU dsu=new DSU(n);

        int upgrades=k;
        int used=0;

        // mandatory edges
        for(int[] e:edges){

            int u=e[0],v=e[1],s=e[2],must=e[3];

            if(must==1){

                if(s<target) return false;

                if(!dsu.union(u,v))
                    return false;

                used++;
            }
        }

        // optional edges without upgrade
        for(int[] e:edges){

            int u=e[0],v=e[1],s=e[2],must=e[3];

            if(must==0 && s>=target){
                if(dsu.union(u,v))
                    used++;
            }
        }

        // optional edges with upgrade
        for(int[] e:edges){

            int u=e[0],v=e[1],s=e[2],must=e[3];

            if(must==0 && s<target && 2*s>=target && upgrades>0){
                if(dsu.union(u,v)){
                    upgrades--;
                    used++;
                }
            }
        }

        return used==n-1;
    }
}