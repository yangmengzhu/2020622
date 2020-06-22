import java.util.Scanner;

/**
 * @program: 2020621
 * @description
 * @author: mrs.yang
 * @create: 2020 -06 -22 13 :37
 */
/*
无法控制生产者明确唤醒消费者线程或者消费者明确唤醒生产者
当生产者的容量小，生产者的数量远远大于消费者数量，生产者唤醒其他生产者时，并且消费者也在等待，由于队列
已满，所以两个在外面的生产者也同时需要wait
所以生产者必须唤醒消费者，消费者必须唤醒生产者
 */
public class MoreProducer {
    private static final MyQueue queue=new MyQueue();
    static class Producer extends Thread{
        Producer(int i){
            super("生产者 "+i);
        }
        @Override
        public void run() {
            try{
                for (int i = 0; true; i++) {
                  queue.push(i);
                    System.out.println(getName()+": 放入 "+i);
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            new Producer(i).start();
        }
        Scanner scan=new Scanner(System.in);
        while(true){
           // System.out.print("输入回车进行消费");
            //scan.nextLine();
            int i=queue.pop();
            System.out.println("消费者取出 "+i);
        }
    }
}
