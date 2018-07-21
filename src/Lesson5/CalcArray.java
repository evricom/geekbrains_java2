package Lesson5;

import java.util.Arrays;

public class CalcArray {

    static final int size = 10000000;
    static final int h = size / 2;
    float[] arr = new float[size];

    public static void main(String[] args) {
        //new CalcArray().multiTreadCalc(4);
        int[] a = {1111,1,2,3,4,5,6,7,8,9,10,11,12,13,145,15};
        int[] b = new int[16];
        System.out.println(Arrays.toString(a));
        int p = 4;
        int lenghtTempArr = a.length/p;
        for (int i = 0; i < p; i++) {
            int[] tempArr = new int[lenghtTempArr];
            System.arraycopy(a, i*p, tempArr, 0, lenghtTempArr );
            System.out.println(Arrays.toString(tempArr));
            System.arraycopy(tempArr, 0, b, i*p, lenghtTempArr );
        }
        System.out.println(Arrays.toString(b));
    }

    void simpleCalc() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("В один поток " + (System.currentTimeMillis() - a) + " мсек.");
    }

    void multiTreadCalc(int numThreads) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();

        int lengthTempArr = arr.length/numThreads;
        for (int i = 0; i < numThreads; i++) {
            int[] tempArr = new int[lengthTempArr];
            System.arraycopy(a, i*numThreads, tempArr, 0, lengthTempArr);



            System.out.println(Arrays.toString(tempArr));
           /// System.arraycopy(tempArr, 0, b, i*numThreads, lengthTempArr );
        }

        for (int i = 0; i < numThreads; i++) {

        }

        float[] a1 = new float[h];
        float[] a2 = new float[h];


        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < a1.length; i++) {
                    arr[i] = (float) (a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < a2.length; i++) {
                    arr[i] = (float) (a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
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
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        System.out.println("В два потока " + (System.currentTimeMillis() - a) + " мсек.");
    }
}
