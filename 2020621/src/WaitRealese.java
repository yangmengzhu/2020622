/*
 * @program: 2020621
 * @description
 * @author: mrs.yang
 * @create: 2020 -06 -21 09 :17
 */

public class WaitRealese {
    static final Object o1=new Object();
    static final Object o2=new Object();
    static class subThread extends Thread{
        @Override
        public void run() {
            try{
                synchronized(o1){
                    synchronized(o2){
                        o2.wait();
                    }
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        subThread s=new subThread();
        s.start();
        Thread.sleep(1000);
        synchronized(o2){
            System.out.println("o2加锁成功");
        }
        synchronized(o1){
            System.out.println("o1加锁成功");
        }

    }
}
