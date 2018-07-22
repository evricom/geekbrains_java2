package Lesson5;

import java.util.Arrays;

public class CalcArray {

    static final int size = 10000000;
    static final int h = size / 2;
    float[] arr = new float[size];

    public static void main(String[] args) {
        int p = 1;
        while (p < 129){
            new CalcArray().multiThreadsCalc(p);
            p *= 2;
        }
    }

    void multiThreadsCalc(int numThreads) {
        //заполенение массива
        Arrays.fill(arr,1);
        // замер времени
        long time0 = System.currentTimeMillis();
        //размер частей массива
        final int lengthTempArr = arr.length/numThreads;
        // массив потоков
        Thread[] threads = new Thread[numThreads];
        // двумерный массив для параллельной обработки
        float[][] arr2D = new float[numThreads][lengthTempArr];
        //
        for (int i = 0; i < numThreads; i++) {
            float[] tempArr = new float[lengthTempArr];
            // получим новый массив
            System.arraycopy(arr, lengthTempArr*i, tempArr, 0, lengthTempArr);
            // формируем массив потоков
            int line = i;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int col = 0; col < lengthTempArr; col++) {
                        arr2D[line][col] = (float) (arr2D[line][col] * Math.sin(0.2f + col / 5) * Math.cos(0.2f + col / 5) * Math.cos(0.4f + col / 2));
                    }
                }
            });
            threads[i] = thread;
        }
        // стартуем потоки
        for (int i = 0; i < numThreads; i++) {
            threads[i].start();
        }
        // ожидаем завершения работы потоков и сливаемся в один массив
        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.arraycopy(arr2D[i],0, arr, i*lengthTempArr, lengthTempArr);
        }
        System.out.println("Потоков: " + numThreads + " время выполнения: " + (System.currentTimeMillis() - time0) + " мсек.");
    }
}
