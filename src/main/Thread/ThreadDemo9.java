package src.main.Thread;

public class ThreadDemo9 {
    /**
     * 单例模式之饿汉模式
     * 类加载时直接实例化
     * 不会有线程安全问题
     */
    static class Singleton{
        private Singleton(){}
        static private Singleton instance = new Singleton();
        static public Singleton getInstance(){
            return instance;
        }
    }
    /***
     * 1. 仿造饿汉模式写出判断为空创建
     * 2. 线程不安全，需要加锁，如果加上函数，代价大；函数内上来加锁代价大，锁需要在判断内部
     * 3. 在锁里面直接创建？可能有几个线程同时争夺锁，其中一个获取，其他的锁还会new示例，需要再加一个判断
     */
    static class SingleDog{
        private SingleDog(){}
        static private volatile SingleDog instance = null;

        static public SingleDog getInstance(){
            if(instance == null){
                synchronized (SingleDog.class){
                    if(instance == null){
                        instance = new SingleDog();
                    }
                }
            }
            return instance;
        }
    }
}
