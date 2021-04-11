public class MyFirstThread extends Thread {
    public void run(){
        System.out.println("Hello Thread " + Thread.currentThread().getName());
    }
    public static void main(String[] arg){
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getId());

        MyFirstThread helloThread1 = new MyFirstThread();
        MyFirstThread helloThread2 = new MyFirstThread();
        helloThread1.start();
        helloThread2.start();
    }
}

// My name is TAN YI CHENG
// NO.MATRKS : 270150
