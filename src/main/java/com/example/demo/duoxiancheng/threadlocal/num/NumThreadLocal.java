package com.example.demo.duoxiancheng.threadlocal.num;

/**
 * 运行结果
 * Thread-0 : ThreadLocal num=1
 * Thread-1 : ThreadLocal num=1
 * Thread-0 : ThreadLocal num=2
 * Thread-0 : ThreadLocal num=3
 * Thread-1 : ThreadLocal num=2
 * Thread-2 : ThreadLocal num=1
 * Thread-0 : ThreadLocal num=4
 * Thread-2 : ThreadLocal num=2
 * Thread-1 : ThreadLocal num=3
 * Thread-1 : ThreadLocal num=4
 * Thread-2 : ThreadLocal num=3
 * Thread-0 : ThreadLocal num=5
 * Thread-2 : ThreadLocal num=4
 * Thread-2 : ThreadLocal num=5
 * Thread-1 : ThreadLocal num=5
 *
 * 参考网址：https://blog.csdn.net/meism5/article/details/90413860
 */
public class NumThreadLocal {

    //线程本地存储变量
    private static final ThreadLocal<Integer> THREAD_LOCAL_NUM = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };
    private static final ThreadLocal<Integer> THREAD_LOCAL_NUM_2 = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 1;
        }
    };

    public static void main(String[] args) {


        for (int i = 0; i < 3; i++) {//启动三个线程
            Thread t = new Thread() {
                @Override
                public void run() {
                    add10ByThreadLocal();
                }
            };
            t.start();
        }
    }

    /**
     * 线程本地存储变量加 5
     */
    private static void add10ByThreadLocal() {
        for (int i = 0; i < 5; i++) {
            Integer n = THREAD_LOCAL_NUM.get();
            Integer m = THREAD_LOCAL_NUM_2.get();
            n += 1;
            THREAD_LOCAL_NUM.set(n);
            System.out.println(Thread.currentThread().getName() + " : ThreadLocal num=" + n);
            System.out.println(m);
        }
    }

}
