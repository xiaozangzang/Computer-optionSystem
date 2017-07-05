package com.pagechange.main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class LRU {
	 /** 
     * 最近最久未使用算法 
     */  
    private static final int PRO_MEMORY = 5;//系统分配的内存块数  
    private static Page[] pages = new Page[PRO_MEMORY];  
    private static int count ;//纪录当前在使用的总页面数  
    private static int lackTime;//缺页次数  
    private List<Integer> usePageNumList = new ArrayList<Integer>();//页面使用列表  
    public static void main(String[] args) {  
        System.out.println("--------最近最久未使用算法------------");  
        LRU lru = new LRU();  
        lru.init();  
        lru.input();  
        lru.running();  
    }  
    //初始化  
    public  void init(){  
        for(int i=0;i<pages.length;i++){  
            pages[i] = new Page();  
        }  
    }  
    //接收用户输入列表  
    public void input(){  
        System.out.print("请输入页面使用列表,以空格分开:");  
        Scanner sc = new Scanner(System.in);  
        String input = sc.nextLine();  
        String[] inputList = input.split("\\s");  
        try{  
            for(String in : inputList){  
                usePageNumList.add(Integer.valueOf(in));  
            }  
        }  
        catch(Exception e){  
            System.out.println("输入的必须是数字,请重新开始！！！");  
            System.exit(0);  
        }  
    }  
    //系统运行  
    public void running(){  
        Iterator<Integer> it = usePageNumList.iterator();  
        //列表置入替换  
        while(it.hasNext()){  
            int inPageId = it.next();  
            //查找内存中是否有该页面  
            if(search(inPageId)){  
                System.out.println("内存中有ID为"+inPageId+" 这个页面,不进行替换");  
            }  
            else if(count<PRO_MEMORY){//有空闲内存页  
                pages[count].setId( (Integer)inPageId);  
                System.out.println("页号ID:"+pages[count].getId()+" 正在放入内存");  
                reSet(count);  
                count ++;  
                timeRefresh();  
            }  
            else{//替换  
                replace(inPageId);  
                timeRefresh();  
                lackTime ++;  
            }  
            display();  
        }  
        System.out.println("缺页次数为:"+lackTime+",缺页率是:"+(float)lackTime/usePageNumList.size());  
    }  
    //查找内存中是否有该页面  
    public boolean search(int pageId){  
        for(int i=0;i<pages.length;i++){  
            if(pages[i].getId() == pageId){  
                timeRefresh();  
                reSet(i);  
                return true;  
            }  
        }  
        return false;  
    }  
    //访问后置0  
    public void reSet(int cur){  
        pages[cur].setCount(0);  
    }  
    //时间更新  
    public void timeRefresh(){  
        for(Page page : pages){  
            page.inc();  
        }  
    }  
    //替换算法  
    public void replace(int pageId){  
        //寻找时间数最大的页面  
        int max = 0,perCount,outPageId = -1,cur = 0;//cur为下标  
        for(int i=0;i<pages.length;i++){  
            perCount = pages[i].getCount();  
            if(max<perCount){  
                max = perCount;  
                outPageId = pages[i].getId();//换出去的页号  
                cur = i;  
            }  
        }  
        reSet(cur);  
        pages[cur].setId(pageId);  
        System.out.println("页号ID:"+pageId+" 正在放入内存,页号ID:"+outPageId+"被替换出去");  
    }     
      
    //显示当前内存页  
    public void display(){  
        System.out.print("当前内存保留的页数是:");  
        for(Page page : pages){  
            System.out.print(page.getId()+" ");  
        }  
        System.out.println();  
    }  
      
}  

