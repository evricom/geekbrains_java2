package Lesson5;

public class MainThread {
    public static void main(String[] args) {
//        MyThread myThread_1 = new MyThread();
//        MyThread myThread_2 = new MyThread();
//        myThread_1.start();
//        myThread_2.start();
//        Thread thread1 = new Thread(new MyRunnableClass());
//        Thread thread2 = new Thread(new MyRunnableClass());
//        thread1.start();
//        thread2.start();
        Thread t1 = new Thread(new MyRunnableClass());
        Thread t2 = new Thread(new MyRunnableClass());
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("END");
    }
}

class MyThread extends Thread {
   @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }
}

class MyRunnableClass implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }
}