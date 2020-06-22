/*
 * @program: 2020621
 * @description
 * @author: mrs.yang
 * @create: 2020 -06 -21 09 :33
 */

public class Demo {
    static class subThread extends Thread{
        @Override
        public void run() {
             try{
                 synchronized(this){
                     wait();
                     System.out.println("子线程被唤醒");
                 }
             }catch(InterruptedException e){
               e.printStackTrace();
             }
        }
    }

    public static void main(String[] args) {
        subThread s=new subThread();//是局部变量但是还是共享的
        synchronized(s){
            s.notify();
        }
        s.start();
    }
}
