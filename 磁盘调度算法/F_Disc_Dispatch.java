package com.system.algorithm;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class F_Disc_Dispatch {
	 private static int maxsize = 100;
	    // 要访问的磁道数
	    private static int count;
	    // 磁道
	    private static int cidao[] = new int[maxsize];
	    // 当前磁道号
	    private static int now;
	    // 总寻道长度
	    private static int sum = 0;
	    // 平均寻道长度
	    private static double AverageDistance;
	    // 当前移动臂的移动的方向(1 (true)表示向外，0(false)表示向内)
	    private static boolean direction;
	    // 算法选择
	// 1-使用FCFS算法
	// 2-使用SSTF算法
	// 3-使用SCAN算法
	// 4-使用CSCAN算法
	    private static int option = 0;
	    // for循环用到变量
	    private static int i;
	    private static int j;
	    private static int k;
	    private static Scanner stdin;

	    public static void main(String[] args) throws FileNotFoundException {
	// 输入数据
	        input();
	// int a;
	        switch (option) {
	            case 1: // 使用FCFS算法
	                FCFS();
	                break;
	            case 2: // 使用SSTF算法
	                SSTF();
	                break;
	            case 3: // 使用SCAN算法
	                SCAN();
	                break;
	            case 4: // 使用CSCAN算法
	                CSCAN();
	                break;
	        }
	    }

	    // 输入数据
	    public static void input() {
	    	System.out.println("------------磁盘调度算法模拟实现-------------");
	        stdin = new Scanner(System.in);
	// 算法选择
	// 1-使用FCFS算法
	// 2-使用SSTF算法
	// 3-使用SCAN算法
	// 4-使用CSCAN算法
	        System.out.println("磁盘调度算法如下：\n1.FCFS算法  2.SSTF算法  3.SCAN算法  4.CSCAN算法");
	        System.out.print("请选择调度算法(1-4)：");
	        option = stdin.nextInt();
	        switch (option) {
            case 1: // 使用FCFS算法
                System.out.println("您已选择FCFS算法！");
                break;
            case 2: // 使用SSTF算法
                System.out.println("您已选择SSTF算法！");
                break;
            case 3: // 使用SCAN算法
                System.out.println("您已选择SCAN算法！");
                break;
            case 4: // 使用CSCAN算法
                System.out.println("您已选择CSCAN算法！");
                break;
        }
	// 要访问的磁道数
	        System.out.print("请输入要磁道访问数：");
	        count = stdin.nextInt();
	// 输入磁道
	        for (i = 0; i < count; i++) {
	        	System.out.print("请输入磁道序列：");
	            cidao[i] = stdin.nextInt();
	        }
	// 当前磁道号
	        System.out.print("请输入当前磁道号：");
	        now = stdin.nextInt();
	        if (option == 3) {
	// 输入当前移动臂的移动的方向(1 表示向外，0表示向内) :
	            try {
	            	System.out.print("请输入移动臂开始移动方向(0:向内,1:向外)：");
	                int g = stdin.nextInt();
	                if (g == 1) {
	                    direction = true;
	                } else {
	                    direction = false;
	                }
	            } catch (Exception e) {
	// TODO: handle exception
	                System.out.println("direction没有正确输入");
	                return;
	            }
	        }
	        stdin.close();
	    }

	    /********************* 先来先服务调度算法**************************/
	    public static void FCFS() {
	        sum += Math.abs(cidao[0] - now);
	        System.out.print("磁盘扫描序列为：");
	        for (i = 0; i < count; i++) // 输出磁盘扫描序列
	        {
	            System.out.print(cidao[i] + " ");
	        }
	        for (i = 0, j = 1; j < count; i++, j++) // 求平均寻道长度
	        {
	            sum += Math.abs(cidao[j] - cidao[i]);
	            AverageDistance = (float) (sum) / (float) (count);
	        }
	        System.out.println("");
	        System.out.println("平均寻道长度：" + AverageDistance);
	    }

	        /********************** 最短寻道时间优先调度算法********************/

	    public static void SSTF() {
	        k = 1;
	        int l, r;
	        bubble(); // 调用冒泡排序算法排序
	        if (cidao[count - 1] <= now) // 若当前磁道号大于请求序列中最大者，则直接由外向内依次给予各请求服务
	        {
	            System.out.print("磁盘扫描序列为：");
	            for (i = count - 1; i >= 0; i--) {
	                System.out.print(cidao[i] + " ");
	            }
	            sum = now - cidao[0];
	        }
	        if (cidao[0] >= now) // 若当前磁道号小于请求序列中最小者，则直接由内向外
	        //依次给予各请求服务
	        {
	            System.out.print("磁盘扫描序列为：");
	            for (i = 0; i < count; i++) {
	                System.out.print(cidao[i] + " ");
	            }
	            sum = cidao[count - 1] - now;
	        }
	        if (now > cidao[0] && now < cidao[count - 1]) // 若当前磁道号大于请求序
	        // 列中最小者且小于最大者
	        {
	            System.out.print("磁盘扫描序列为：");
	            while (cidao[k] < now) // 确定当前磁道在已排的序列中的位置，后面的算
	            // 法都用到了，可以直接复制后少量修改，节省时间。
	            {
	                k++;
	            }
	            l = k - 1;
	            r = k;
	            while ((l >= 0) && (r < count)) // 当前磁道在请求序列范围内
	            {
	                if ((now - cidao[l]) <= (cidao[r] - now)) // 选择与当前磁道最近
	                //的请求给予服务
	                {
	                    System.out.print(cidao[l] + " ");
	                    sum += now - cidao[l];
	                    now = cidao[l];
	                    l = l - 1;
	                } else {
	                    System.out.print(cidao[r] + " ");
	                    sum += cidao[r] - now;
	                    now = cidao[r];
	                    r = r + 1;
	                }
	            }
	            if (l == -1) // 磁头移动到序列的最小号，返回外侧扫描仍未扫描的磁道
	            {
	                for (j = r; j < count; j++) {
	                    System.out.print(cidao[j] + " ");
	                }
	                sum += cidao[count - 1] - cidao[0];
	            } else // 磁头移动到序列的最大号，返回内侧扫描仍未扫描的磁道
	            {
	                for (j = l; j >= 0; j--) {
	                    System.out.print(cidao[j] + " ");
	                }
	                sum += cidao[count - 1] - cidao[0];
	            }
	        }
	        AverageDistance = (float) (sum) / (float) (count);
	        System.out.println("");
	        System.out.println("平均寻道长度： " + AverageDistance);
	    }

	    /************************** 扫描调度算法*******************************/
	    public static void SCAN() // 先要给出当前磁道号和移动臂的移动方向
	    {
	        k = 1;
	        int l, r;
	        bubble(); // 调用冒泡排序算法排序
	        if (cidao[count - 1] <= now) // 若当前磁道号大于请求序列中最大者，则直接由外向内依次给予各请求服务,此情况同最短寻道优先
	        {
	            System.out.print("磁盘扫描序列为：");
	            for (i = count - 1; i >= 0; i--) {
	                System.out.print(cidao[i] + " ");
	            }
	            sum = now - cidao[0];
	        }
	        if (cidao[0] >= now) // 若当前磁道号小于请求序列中最小者，则直接由内向外 依次给予各请求服务,此情况同最短寻道优先
	        {
	            System.out.print("磁盘扫描序列为：");
	            for (i = 0; i < count; i++)
	                System.out.print(cidao[i] + " ");
	            sum = cidao[count - 1] - now;
	        }
	        if (now > cidao[0] && now < cidao[count - 1]) // 若当前磁道号大于请求序列中最小者且小于最大者
	        {
	            while (cidao[k] < now) {
	                k++;
	            }
	            l = k - 1;
	            r = k;
	            if (direction == false) // 选择移动臂方向向内，则先向内扫描
	            {
	                System.out.print("磁盘扫描序列为：");
	                for (j = l; j >= 0; j--) {
	                    System.out.print(cidao[j] + " "); // 输出向内扫描的序列
	                }
	                for (j = r; j < count; j++) // 磁头移动到最小号，则改变方向向外扫描未扫描的磁道
	                {
	                    System.out.print(cidao[j] + " "); // 输出向外扫描的序列
	                }
	                sum = now - 2 * cidao[0] + cidao[count - 1];
	            } else // 选择移动臂方向向外，则先向外扫描
	            {
	                System.out.print("磁盘扫描序列为：");
	                for (j = r; j < count; j++) {
	                    System.out.print(cidao[j] + " "); // 输出向外扫描的序列
	                }
	                for (j = l; j >= 0; j--) // 磁头移动到最大号，则改变方向向内扫描未扫描的磁道
	                {
	                    System.out.print(cidao[j] + " ");
	                }
	                sum = -now - cidao[0] + 2 * cidao[count - 1];
	            }
	        }
	        AverageDistance = (float) (sum) / (float) (count);
	        System.out.println("");
	        System.out.println("平均寻道长度： " + AverageDistance);
	    }

	    /************************ 循环扫描调度算法*****************************/
	    public static void CSCAN() {
	        k = 1;
	        int l, r;
	        bubble(); // 调用冒泡排序算法排序
	        if (cidao[count - 1] <= now) // 若当前磁道号大于请求序列中最大者，则直接将移动臂移动到最小号磁道依次向外给予各请求服务
	        {
	            System.out.print("磁盘扫描序列为：");
	            for (i = 0; i < count; i++) {
	                System.out.print(cidao[i] + " ");
	            }
	            sum = now - 2 * cidao[0] + cidao[count - 1];
	        }
	        if (cidao[0] >= now) // 若当前磁道号小于请求序列中最小者，则直接由内向外依次给予各请求服务,此情况同最短寻道优先
	        {
	            System.out.print("磁盘扫描序列为：");
	            for (i = 0; i < count; i++)
	                System.out.print(cidao[i] + " ");
	            sum = cidao[count - 1] - now;
	        }
	        if (now > cidao[0] && now < cidao[count - 1]) // 若当前磁道号大于请求序
	        //列中最小者且小于最大者
	        {
	            System.out.print("磁盘扫描序列为：");
	            while (cidao[k] < now) // 单向反复地从内向外扫描
	            {
	                k++;
	            }
	            l = k - 1;
	            r = k;
	            for (j = r; j < count; j++) {
	                System.out.print(cidao[j] + " "); // 输出从当前磁道向外扫描的序列
	            }
	            for (j = 0; j < r; j++) // 当扫描完最大号磁道，磁头直接移动到最小号磁道，再向外扫描未扫描的磁道
	            {
	                System.out.print(cidao[j] + " ");
	            }
	            sum = 2 * cidao[count - 1] + cidao[l] - now - 2 * cidao[0];
	        }
	        AverageDistance = (float) (sum) / (float) (count);
	        System.out.println("");
	        System.out.print("平均寻道长度： " + AverageDistance);
	    }

	    /********************* 冒泡排序算法**************************/
	    public static void bubble() {
	        int temp;
	        for (int i = 0; i < count; i++) { // 使用冒泡法按从小到大顺序排列
	            for (int j = i + 1; j < count; j++) {
	                if (cidao[i] > cidao[j]) {
	                    temp = cidao[i];
	                    cidao[i] = cidao[j];
	                    cidao[j] = temp;
	                }
	            }
	        }
	        System.out.println("排序后的磁盘序列为：");
	        for (i = 0; i < count; i++) // 输出排序结果
	        {
	            System.out.print(cidao[i] + " ");
	        }
	        System.out.println("");

	    }
}
