import java.util.Random;

public class ParallelSum {
    private static final int ARRAY_SIZE = 100000;

    public static void main(String[] args) {
        // 生成随机数数组
        int[] numbers = generateRandomArray(ARRAY_SIZE);

        // 获取处理器核心数
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Available processor cores: " + cores);

        // 计算单线程求和（作为基准）
        long startTime = System.currentTimeMillis();
        long singleThreadSum = calculateSingleThreadSum(numbers);
        long singleThreadTime = System.currentTimeMillis() - startTime;

        // 计算多线程求和
        startTime = System.currentTimeMillis();
        long multiThreadSum = calculateMultiThreadSum(numbers, cores);
        long multiThreadTime = System.currentTimeMillis() - startTime;

        // 输出结果
        System.out.println("Single thread sum: " + singleThreadSum);
        System.out.println("Single thread time: " + singleThreadTime + " ms");
        System.out.println("Multi-thread sum: " + multiThreadSum);
        System.out.println("Multi-thread time: " + multiThreadTime + " ms");
        System.out.println("Speedup: " + (double) singleThreadTime / multiThreadTime);
    }

    private static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100); // 生成0-99的随机数
        }
        return array;
    }

    private static long calculateSingleThreadSum(int[] numbers) {
        long sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }

    private static long calculateMultiThreadSum(int[] numbers, int numThreads) {
        // 计算每个线程处理的元素数量
        int elementsPerThread = numbers.length / numThreads;
        SumCalculator[] calculators = new SumCalculator[numThreads];

        // 创建并启动线程
        for (int i = 0; i < numThreads; i++) {
            int start = i * elementsPerThread;
            int end = (i == numThreads - 1) ? numbers.length : (i + 1) * elementsPerThread;

            calculators[i] = new SumCalculator(numbers, start, end);
            calculators[i].start(); // SumCalculator已经继承了Thread，直接调用start()
        }

        // 等待所有线程完成并汇总结果
        long totalSum = 0;
        for (int i = 0; i < numThreads; i++) {
            try {
                calculators[i].join();
                totalSum += calculators[i].getPartialSum();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return totalSum;
    }
}