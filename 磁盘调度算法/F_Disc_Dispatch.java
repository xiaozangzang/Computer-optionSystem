package com.system.algorithm;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class F_Disc_Dispatch {
	 private static int maxsize = 100;
	    // Ҫ���ʵĴŵ���
	    private static int count;
	    // �ŵ�
	    private static int cidao[] = new int[maxsize];
	    // ��ǰ�ŵ���
	    private static int now;
	    // ��Ѱ������
	    private static int sum = 0;
	    // ƽ��Ѱ������
	    private static double AverageDistance;
	    // ��ǰ�ƶ��۵��ƶ��ķ���(1 (true)��ʾ���⣬0(false)��ʾ����)
	    private static boolean direction;
	    // �㷨ѡ��
	// 1-ʹ��FCFS�㷨
	// 2-ʹ��SSTF�㷨
	// 3-ʹ��SCAN�㷨
	// 4-ʹ��CSCAN�㷨
	    private static int option = 0;
	    // forѭ���õ�����
	    private static int i;
	    private static int j;
	    private static int k;
	    private static Scanner stdin;

	    public static void main(String[] args) throws FileNotFoundException {
	// ��������
	        input();
	// int a;
	        switch (option) {
	            case 1: // ʹ��FCFS�㷨
	                FCFS();
	                break;
	            case 2: // ʹ��SSTF�㷨
	                SSTF();
	                break;
	            case 3: // ʹ��SCAN�㷨
	                SCAN();
	                break;
	            case 4: // ʹ��CSCAN�㷨
	                CSCAN();
	                break;
	        }
	    }

	    // ��������
	    public static void input() {
	    	System.out.println("------------���̵����㷨ģ��ʵ��-------------");
	        stdin = new Scanner(System.in);
	// �㷨ѡ��
	// 1-ʹ��FCFS�㷨
	// 2-ʹ��SSTF�㷨
	// 3-ʹ��SCAN�㷨
	// 4-ʹ��CSCAN�㷨
	        System.out.println("���̵����㷨���£�\n1.FCFS�㷨  2.SSTF�㷨  3.SCAN�㷨  4.CSCAN�㷨");
	        System.out.print("��ѡ������㷨(1-4)��");
	        option = stdin.nextInt();
	        switch (option) {
            case 1: // ʹ��FCFS�㷨
                System.out.println("����ѡ��FCFS�㷨��");
                break;
            case 2: // ʹ��SSTF�㷨
                System.out.println("����ѡ��SSTF�㷨��");
                break;
            case 3: // ʹ��SCAN�㷨
                System.out.println("����ѡ��SCAN�㷨��");
                break;
            case 4: // ʹ��CSCAN�㷨
                System.out.println("����ѡ��CSCAN�㷨��");
                break;
        }
	// Ҫ���ʵĴŵ���
	        System.out.print("������Ҫ�ŵ���������");
	        count = stdin.nextInt();
	// ����ŵ�
	        for (i = 0; i < count; i++) {
	        	System.out.print("������ŵ����У�");
	            cidao[i] = stdin.nextInt();
	        }
	// ��ǰ�ŵ���
	        System.out.print("�����뵱ǰ�ŵ��ţ�");
	        now = stdin.nextInt();
	        if (option == 3) {
	// ���뵱ǰ�ƶ��۵��ƶ��ķ���(1 ��ʾ���⣬0��ʾ����) :
	            try {
	            	System.out.print("�������ƶ��ۿ�ʼ�ƶ�����(0:����,1:����)��");
	                int g = stdin.nextInt();
	                if (g == 1) {
	                    direction = true;
	                } else {
	                    direction = false;
	                }
	            } catch (Exception e) {
	// TODO: handle exception
	                System.out.println("directionû����ȷ����");
	                return;
	            }
	        }
	        stdin.close();
	    }

	    /********************* �����ȷ�������㷨**************************/
	    public static void FCFS() {
	        sum += Math.abs(cidao[0] - now);
	        System.out.print("����ɨ������Ϊ��");
	        for (i = 0; i < count; i++) // �������ɨ������
	        {
	            System.out.print(cidao[i] + " ");
	        }
	        for (i = 0, j = 1; j < count; i++, j++) // ��ƽ��Ѱ������
	        {
	            sum += Math.abs(cidao[j] - cidao[i]);
	            AverageDistance = (float) (sum) / (float) (count);
	        }
	        System.out.println("");
	        System.out.println("ƽ��Ѱ�����ȣ�" + AverageDistance);
	    }

	        /********************** ���Ѱ��ʱ�����ȵ����㷨********************/

	    public static void SSTF() {
	        k = 1;
	        int l, r;
	        bubble(); // ����ð�������㷨����
	        if (cidao[count - 1] <= now) // ����ǰ�ŵ��Ŵ�����������������ߣ���ֱ�������������θ�����������
	        {
	            System.out.print("����ɨ������Ϊ��");
	            for (i = count - 1; i >= 0; i--) {
	                System.out.print(cidao[i] + " ");
	            }
	            sum = now - cidao[0];
	        }
	        if (cidao[0] >= now) // ����ǰ�ŵ���С��������������С�ߣ���ֱ����������
	        //���θ�����������
	        {
	            System.out.print("����ɨ������Ϊ��");
	            for (i = 0; i < count; i++) {
	                System.out.print(cidao[i] + " ");
	            }
	            sum = cidao[count - 1] - now;
	        }
	        if (now > cidao[0] && now < cidao[count - 1]) // ����ǰ�ŵ��Ŵ���������
	        // ������С����С�������
	        {
	            System.out.print("����ɨ������Ϊ��");
	            while (cidao[k] < now) // ȷ����ǰ�ŵ������ŵ������е�λ�ã��������
	            // �����õ��ˣ�����ֱ�Ӹ��ƺ������޸ģ���ʡʱ�䡣
	            {
	                k++;
	            }
	            l = k - 1;
	            r = k;
	            while ((l >= 0) && (r < count)) // ��ǰ�ŵ����������з�Χ��
	            {
	                if ((now - cidao[l]) <= (cidao[r] - now)) // ѡ���뵱ǰ�ŵ����
	                //������������
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
	            if (l == -1) // ��ͷ�ƶ������е���С�ţ��������ɨ����δɨ��Ĵŵ�
	            {
	                for (j = r; j < count; j++) {
	                    System.out.print(cidao[j] + " ");
	                }
	                sum += cidao[count - 1] - cidao[0];
	            } else // ��ͷ�ƶ������е����ţ������ڲ�ɨ����δɨ��Ĵŵ�
	            {
	                for (j = l; j >= 0; j--) {
	                    System.out.print(cidao[j] + " ");
	                }
	                sum += cidao[count - 1] - cidao[0];
	            }
	        }
	        AverageDistance = (float) (sum) / (float) (count);
	        System.out.println("");
	        System.out.println("ƽ��Ѱ�����ȣ� " + AverageDistance);
	    }

	    /************************** ɨ������㷨*******************************/
	    public static void SCAN() // ��Ҫ������ǰ�ŵ��ź��ƶ��۵��ƶ�����
	    {
	        k = 1;
	        int l, r;
	        bubble(); // ����ð�������㷨����
	        if (cidao[count - 1] <= now) // ����ǰ�ŵ��Ŵ�����������������ߣ���ֱ�������������θ�����������,�����ͬ���Ѱ������
	        {
	            System.out.print("����ɨ������Ϊ��");
	            for (i = count - 1; i >= 0; i--) {
	                System.out.print(cidao[i] + " ");
	            }
	            sum = now - cidao[0];
	        }
	        if (cidao[0] >= now) // ����ǰ�ŵ���С��������������С�ߣ���ֱ���������� ���θ�����������,�����ͬ���Ѱ������
	        {
	            System.out.print("����ɨ������Ϊ��");
	            for (i = 0; i < count; i++)
	                System.out.print(cidao[i] + " ");
	            sum = cidao[count - 1] - now;
	        }
	        if (now > cidao[0] && now < cidao[count - 1]) // ����ǰ�ŵ��Ŵ���������������С����С�������
	        {
	            while (cidao[k] < now) {
	                k++;
	            }
	            l = k - 1;
	            r = k;
	            if (direction == false) // ѡ���ƶ��۷������ڣ���������ɨ��
	            {
	                System.out.print("����ɨ������Ϊ��");
	                for (j = l; j >= 0; j--) {
	                    System.out.print(cidao[j] + " "); // �������ɨ�������
	                }
	                for (j = r; j < count; j++) // ��ͷ�ƶ�����С�ţ���ı䷽������ɨ��δɨ��Ĵŵ�
	                {
	                    System.out.print(cidao[j] + " "); // �������ɨ�������
	                }
	                sum = now - 2 * cidao[0] + cidao[count - 1];
	            } else // ѡ���ƶ��۷������⣬��������ɨ��
	            {
	                System.out.print("����ɨ������Ϊ��");
	                for (j = r; j < count; j++) {
	                    System.out.print(cidao[j] + " "); // �������ɨ�������
	                }
	                for (j = l; j >= 0; j--) // ��ͷ�ƶ������ţ���ı䷽������ɨ��δɨ��Ĵŵ�
	                {
	                    System.out.print(cidao[j] + " ");
	                }
	                sum = -now - cidao[0] + 2 * cidao[count - 1];
	            }
	        }
	        AverageDistance = (float) (sum) / (float) (count);
	        System.out.println("");
	        System.out.println("ƽ��Ѱ�����ȣ� " + AverageDistance);
	    }

	    /************************ ѭ��ɨ������㷨*****************************/
	    public static void CSCAN() {
	        k = 1;
	        int l, r;
	        bubble(); // ����ð�������㷨����
	        if (cidao[count - 1] <= now) // ����ǰ�ŵ��Ŵ�����������������ߣ���ֱ�ӽ��ƶ����ƶ�����С�Ŵŵ��������������������
	        {
	            System.out.print("����ɨ������Ϊ��");
	            for (i = 0; i < count; i++) {
	                System.out.print(cidao[i] + " ");
	            }
	            sum = now - 2 * cidao[0] + cidao[count - 1];
	        }
	        if (cidao[0] >= now) // ����ǰ�ŵ���С��������������С�ߣ���ֱ�������������θ�����������,�����ͬ���Ѱ������
	        {
	            System.out.print("����ɨ������Ϊ��");
	            for (i = 0; i < count; i++)
	                System.out.print(cidao[i] + " ");
	            sum = cidao[count - 1] - now;
	        }
	        if (now > cidao[0] && now < cidao[count - 1]) // ����ǰ�ŵ��Ŵ���������
	        //������С����С�������
	        {
	            System.out.print("����ɨ������Ϊ��");
	            while (cidao[k] < now) // ���򷴸��ش�������ɨ��
	            {
	                k++;
	            }
	            l = k - 1;
	            r = k;
	            for (j = r; j < count; j++) {
	                System.out.print(cidao[j] + " "); // ����ӵ�ǰ�ŵ�����ɨ�������
	            }
	            for (j = 0; j < r; j++) // ��ɨ�������Ŵŵ�����ͷֱ���ƶ�����С�Ŵŵ���������ɨ��δɨ��Ĵŵ�
	            {
	                System.out.print(cidao[j] + " ");
	            }
	            sum = 2 * cidao[count - 1] + cidao[l] - now - 2 * cidao[0];
	        }
	        AverageDistance = (float) (sum) / (float) (count);
	        System.out.println("");
	        System.out.print("ƽ��Ѱ�����ȣ� " + AverageDistance);
	    }

	    /********************* ð�������㷨**************************/
	    public static void bubble() {
	        int temp;
	        for (int i = 0; i < count; i++) { // ʹ��ð�ݷ�����С����˳������
	            for (int j = i + 1; j < count; j++) {
	                if (cidao[i] > cidao[j]) {
	                    temp = cidao[i];
	                    cidao[i] = cidao[j];
	                    cidao[j] = temp;
	                }
	            }
	        }
	        System.out.println("�����Ĵ�������Ϊ��");
	        for (i = 0; i < count; i++) // ���������
	        {
	            System.out.print(cidao[i] + " ");
	        }
	        System.out.println("");

	    }
}
