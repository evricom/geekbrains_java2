package Lesson5;

public class MainThread {
    public static void main(String[] args) {
        MyThread myThread_1 = new MyThread();
        MyThread myThread_2 = new MyThread();
        myThread_1.start();
        myThread_2.start();
    }
}

class MyThread extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 10 ; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }
}