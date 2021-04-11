public class MyRunnable1 {
    public static void main(String[] args) {
        ThreadPrint t=new ThreadPrint();
        Thread th1=new Thread(t,"线程1");
        Thread th2=new Thread(t,"线程2");
        th2.start();
        th1.start();
    }
}

class ThreadPrint implements Runnable{
    int i=1;
    boolean flag=true;
    @Override
    public void run() {
        while(i<=100){
            synchronized(this){
                //限制只有第一次进来的线程为线程1，才允许向下执行，保证线程1打单数，线程2打双数
                flag=(i==1)?(("线程1".equals(Thread.currentThread().getName()))?true:false):true;
                if(flag){
                    //当前线程进来后唤醒所有等待线程，由于"钥匙"持有在当前线程手里，所以另外一条线程只能在锁外等待
                    //只能等当前线程执行完后才会拿到钥匙，进入锁对象包含的代码块
                    this.notifyAll();
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                    i++;
                    try {
                        //当前线程执行完毕后，将当前线程设置为等待状态，等到下一个线程的唤醒，这样的交替等待唤醒就
                        //能实现交替打印
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

