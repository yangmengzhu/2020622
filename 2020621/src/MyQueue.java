/*
 * @program: 2020621
 * @description
 * @author: mrs.yang
 * @create: 2020 -06 -21 10 :13
 */

import java.util.Scanner;

public class MyQueue {
    private int[] arr=new int[1];
    private int frontIndex=0;
    private int rearIndex=0;
    private volatile int size=0;
    public synchronized void push(int value) throws InterruptedException {
        //wait一般建议放在while循环中使用，因为它可能会被打断
        while(size==arr.length){//每次被唤醒之后，队列是否有空间
          wait();//等着调用pop的线程唤醒
        }
        //有空间就放入元素
        arr[rearIndex]=value;
        size++;//破坏了原子性
        rearIndex=(rearIndex+1)%arr.length;
        notifyAll();//唤醒调用pop时堵塞的队列
    }
    public synchronized int pop() throws InterruptedException {
        while(size==0){//唤醒之后继续判断队列是否已满
            wait();//等着调用push的线程唤醒
        }
        //满了就取出元素
        int value=arr[frontIndex];
        frontIndex=(frontIndex+1)%arr.length;
        size--;
        notifyAll();//唤醒调用push时堵塞的队列
        return value;
    }
    public int size(){
        return size;
    }
    //生产者
   /* private static final MyQueue queue=new MyQueue();
        static class Producer extends Thread{
            @Override
            public void run() {
                 try{
                     for (int i = 0; true ; i++) {
                         queue.push(i);
                         System.out.println("生产者放入 "+i);
                     }
                 }catch(InterruptedException e){
                     e.printStackTrace();
                 }
            }
        }
//主线程充当消费者
    public static void main(String[] args) throws InterruptedException {
        Producer p=new Producer();
        p.start();
        Scanner scan=new Scanner(System.in);
        while(true){
            System.out.println("输入任何值，进行一次消费");
            scan.nextLine();
            int i=queue.pop();
            System.out.println("消费者消费 "+i);
        }
    }*/
}
