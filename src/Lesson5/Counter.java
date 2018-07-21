package Lesson5;

public class Counter {

    int c;

    public Counter(int c) {
        this.c = c;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public synchronized void dec() {
        c--;
    }

    public synchronized void inc() {
        c++;
    }
}

class MyCounter {
    public static void main(String[] args) {
//        Counter counter = new Counter(0);
//        Thread t1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 1000000; i++) {
//                    counter.inc();
//                }
//            }
//        });
//
//        Thread t2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 1000000; i++) {
//                    counter.dec();
//                }
//            }
//        });
//
//        t1.start();
//        t2.start();
//
//        try {
//            t1.join();
//            t2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(counter.getC());
        Box box = new Box();

        new Thread(new Runnable() {
            @Override
            public void run() {
                box.doDomeThing();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                box.doDomeThing();
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                box.doDomeThing();
            }
        }).start();


    }
}

class Box {
    Object object = new Object();

    void doDomeThing() {
        System.out.println(1);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (object) {
            System.out.println(998);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(999);
        }
        System.out.println(2);
    }
}