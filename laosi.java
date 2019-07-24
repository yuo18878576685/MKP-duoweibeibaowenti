public class laosi {

	int n;
	int m;
	int[][] shuxing_all;					//所有属性
	int[] limite;							//限制条件
	int[] c_shuxing;						//当前属性
	double[] value_all;						//所有价值
	double c_value=0;						//当前价值
	double bestv=0;							//最优方案值
	int[] best_selection;					//最优值的标记数组
	int[] selection;						//标记数组
	
	//构造方法
	public  laosi(int n,int m,int []limite,int [][]shuxing_all,double []value_all){
		this.n=n;
		this.m=m;
		this.limite=limite;
		this.shuxing_all=shuxing_all;
		this.value_all=value_all;
		this.best_selection = new int[n];
		this.selection=new int[50];
		this.c_shuxing = new int[50];	
	};
	//布尔型函数，限定条件：当前背包重量加上新物品的质量是否会超出背包上限
	public boolean ok(int[] c_shuxing,int m,int[] limite,int t,int[][] shuxing_all){
		int i;
		for(i=0;i<m;i++){
			if(c_shuxing[i]+shuxing_all[i][t]<=limite[i])
				continue;
			else break;
		}
		if(i==m)
			return true;
		else 
			return false;
	}
	public void backtrack(int t) {
		//t用来指示到达的层数（第几步，从0开始），同时也指示当前选择了几个物品
		if (t >= n) {  //已经搜索到叶子节点了
			// 限界函数：剪去那些可行，但不可能是最优解的树枝
			if (c_value > bestv) {
				bestv = c_value;
				// 保存最优的selection值：与动态优化的区别，因为selection会在不同的剪枝过程中发生变化
				for (int i = 0; i < n; i++) {
					best_selection[i] = selection[i]; // 把最优的选择序列保存在best_selection中
					// System.out.print(selection[i] + " ");
				}
			}
		}//还未搜索到最后一个物品，即叶子节点，则直接搜索左子树;
		else {
			// 搜索左子树
			if (ok(c_shuxing,m,limite,t,shuxing_all)) {	//将物品t放入背包,搜索左子树
				for(int i=0;i<m;i++){					//更新所有属性，即当前背包的重量
					c_shuxing[i] += shuxing_all[i][t];
				}
				c_value += value_all[t];				//更新当前背包的总价值，加入放入的新物品的价值

				selection[t] = 1;						//放入背包，标记为1
				backtrack(t + 1);						//深度搜索进入下一层

				// 将背包当前价值和重量还原
				for(int i=0;i<m;i++){					//还原当前所有属性，即还原当前背包的重量
					c_shuxing[i] -= shuxing_all[i][t];
				}
				c_value -= value_all[t];				//还原当前价值
			}
			selection[t] = 0;							//不放入背包，标记为0
			backtrack(t + 1); 							// 搜索右子树
		}
	}
}