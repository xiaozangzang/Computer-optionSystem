package com.pagechange.main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class FIFO {
	  /** 
     *�Ƚ��ȳ��㷨 
     */  
    private static final int PRO_MEMORY = 5;//ϵͳ������ڴ����  
    private static Page[] pages = new Page[PRO_MEMORY];  
    private static int countOldPoint ;//��¼��õ�ҳ���±�  
    private static int count ;//��¼��ǰ��ʹ�õ���ҳ����  
    private static int lackTime;//ȱҳ����  
    private List<Integer> usePageNumList = new ArrayList<Integer>();//ҳ��ʹ���б�  
      
    public static void main(String[] args){  
        System.out.println("--------�Ƚ��ȳ��㷨------------");  
        FIFO fifo = new FIFO();  
        fifo.init();  
        fifo.input();  
        fifo.running();  
    }  
      
    //��ʼ��  
    public  void init(){  
        for(int i=0;i<pages.length;i++){  
            pages[i] = new Page();  
        }  
    }  
    //�����û������б�  
    public void input(){  
        System.out.print("������ҳ��ʹ���б�,�Կո�ֿ�:");  
        Scanner sc = new Scanner(System.in);  
        String input = sc.nextLine();  
        String[] inputList = input.split("\\s");  
        try{  
            for(String in : inputList){  
                usePageNumList.add(Integer.valueOf(in));  
            }  
        }  
        catch(Exception e){  
            System.out.println("����ı���������,�����¿�ʼ������");  
            System.exit(0);  
        }  
    }  
    //ϵͳ����  
    public void running(){  
        Iterator<Integer> it = usePageNumList.iterator();  
        //�б������滻  
        while(it.hasNext()){  
            countOldPoint = countOldPoint % PRO_MEMORY;  
            int inPageId = it.next();  
            //�����ڴ����Ƿ��и�ҳ��  
            if(search(inPageId)){  
                System.out.println("�ڴ�����IDΪ"+inPageId+" ���ҳ��,�������滻");  
            }  
            else if(count<PRO_MEMORY){//�п����ڴ�ҳ  
                pages[count].setId( (Integer)inPageId);  
                System.out.println("ҳ��ID:"+pages[count].getId()+" ���ڷ����ڴ�");  
                count ++;  
            }  
            else{//�滻  
                replace(inPageId);  
                lackTime ++;  
                countOldPoint ++;  
            }  
            display();  
        }  
        System.out.println("ȱҳ����Ϊ:"+lackTime+",ȱҳ����:"+(float)lackTime/usePageNumList.size());  
    }  
    //�����ڴ����Ƿ��и�ҳ��  
    public boolean search(int pageId){  
        for(int i=0;i<pages.length;i++){  
            if(pages[i].getId() == pageId){  
                return true;  
            }  
        }  
        return false;  
    }  
      
    //�滻�㷨  
    public void replace(int pageId){  
        //�û���õ�ҳ��  
        int outPageId = -1;  
        outPageId = pages[countOldPoint].getId();  
        pages[countOldPoint].setId(pageId);  
        System.out.println("ҳ��ID:"+pageId+" ���ڷ����ڴ�,ҳ��ID:"+outPageId+"���滻��ȥ");  
    }     
    //��ʾ��ǰ�ڴ�ҳ  
    public void display(){  
        System.out.print("��ǰ�ڴ汣����ҳ����:");  
        for(Page page : pages){  
            System.out.print(page.getId()+" ");  
        }  
        System.out.println();  
    }  
  
}
