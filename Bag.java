import java.util.Scanner;
public class Bag{
	public static void main(String args[]) {
		
		//输入
		Scanner ls = new Scanner(System.in);//声明Scanner的对象ls
		
		int n = ls.nextInt();// 表示物体的个数
	    int m = ls.nextInt();//物品约束条件
	    double maxValue = ls.nextDouble();
	    
	    double[] value_all = new double[n];
	    for(int i = 0; i < n; i++) {        //物品价值
	    	value_all[i] = ls.nextDouble();
	    }
	    
	    int[][] shuxing_all =  new int[m][n];
	    for(int i = 0; i < m; i++) {            //物品重量
	    	for(int j=0;j < n;j++) {
	    		shuxing_all[i][j] = ls.nextInt();
	    	}
	    }
	    int[] limite = new int[m];				//约束条件
	    for(int j = 0; j < m; j++) {            //物品约束条件
	    	limite[j] = ls.nextInt();
        }
	    
	    laosi b = new laosi(n,m,limite,shuxing_all,value_all); 
		b.backtrack(0);
		System.out.println("回溯法背包的最优值为：" + b.bestv);
	}
}