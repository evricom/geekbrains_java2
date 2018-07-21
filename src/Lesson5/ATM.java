package Lesson5;

import java.lang.reflect.Array;

public class ATM {
    int money;

    public ATM(int money) {
        this.money = money;
    }

    public synchronized void take(int amount, String user){
        if (money >= amount){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            money -= amount;
            System.out.println(user+" взял "+amount);
        } else {
            System.out.println(user + " нет денег:" + amount);
        }
    }

    public void info(){
        System.out.println("ATM = " + money);
    }
}

class MainATM{
    public static void main(String[] args) {
        ATM atm = new ATM(100);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                atm.take(40,"user_1");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                atm.take(40,"user_2");
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                atm.take(40,"user_3");
            }
        });

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        atm.info();
    }
}

class VolatitleTest{
    private volatile int value = 1;

    public int getValue() {
        return value;
    }

    void inc(){
        value++;
    }

    void dec(){
        value--;
    }
}

class MainVolatitle{
    public static void main(String[] args) {
        VolatitleTest volatitleTest = new VolatitleTest();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    volatitleTest.inc();
                }

            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    volatitleTest.dec();
                }

            }
        });
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(volatitleTest.getValue());
    }
}

class MainDaemon{
    public static void main(String[] args) {
        Thread timer = new Thread(new Runnable() {
            @Override
            public void run() {
                int seconds = 0;
                while (true){
                    seconds++;
                    System.out.println("time" + seconds + ".");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        timer.setDaemon(true);
        timer.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("END");
    }
}

/**
 * хранидище
 */
class Store{

    /**
     * количество товаров на складе
     */
    private int product = 0;

    /**
     * взять товар
     */
    public synchronized void get(){
        while (product < 1){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        product--;
        System.out.println("Покупатель купил один товар");
        System.out.println("Товаров на складе " + product);
        notify();
    }

    /**
     * положить товар
     */
    public synchronized void put(){
        while (product >= 3 ){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        product++;
        System.out.println("Производитель добавил один товар");
        System.out.println("Товаров на складе " + product);
        notify();
    }
}

/**
 * производитель
 */
class Producer implements Runnable{
    Store store;

    /**
     * конструктор производителя
     * @param store храилище для продукции
     */
    public Producer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        for (int i = 1; i < 6; i++) {
            store.put();
        }
    }
}

class Consuner implements Runnable{
    Store store;

    public Consuner(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        for (int i = 1; i < 6; i++) {
            store.get();
        }
    }
}

class NainClassStore{
    public static void main(String[] args) {
        Store store = new Store();
        Producer producer = new Producer(store);
        Consuner consuner = new Consuner(store);
        new Thread(producer).start();
        new Thread(consuner).start();
    }
}