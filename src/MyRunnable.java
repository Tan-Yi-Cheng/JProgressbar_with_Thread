import java.util.Scanner;
public class MyRunnable{
    public static void main(String[] args){
        Printer print = new Printer();
        int x = 0,y = 0, smaller = 0, bigger = 0;
        Scanner input = new Scanner(System.in);

        System.out.print("Please input x: ");
        x = input.nextInt();
        System.out.print("\nPlease input y: ");
        y = input.nextInt();

        if(x>y){
            bigger = x;
            smaller = y;
        } else {
            smaller = x;
            bigger = y;
        }


        Thread t1 = new Thread(new oddOrEven(print, smaller, bigger,false));
        Thread t2 = new Thread(new oddOrEven(print, smaller, bigger,true));
        t1.start();
        t2.start();
    }
}

class oddOrEven implements Runnable{

    private int max;
    private int min;
    private Printer print;
    private boolean isEvenNumber;

    oddOrEven(Printer print, int min, int max, boolean isEvenNumber) {
        this.print = print;
        this.min = min;
        this.max = max;
        this.isEvenNumber = isEvenNumber;
    }

    @Override
    public void run() {
        min = isEvenNumber == true ? 2 : 1;
        while (min <= max) {

            if (isEvenNumber) {
                //System.out.println("Even :"+ Thread.currentThread().getName());
                print.printEven(min);
                //number+=2;
            } else {
                //System.out.println("Odd :"+ Thread.currentThread().getName());
                print.printOdd(min);
                // number+=2;
            }
            min += 2;
        }
    }
}



class Printer {

    boolean isOdd = false;

    synchronized void printEven(int number) {

        while (isOdd == false) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Even:" + number);
        isOdd = false;
        notifyAll();
    }

    synchronized void printOdd(int number) {
        while (isOdd == true) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Odd:" + number);
        isOdd = true;
        notifyAll();
    }

}