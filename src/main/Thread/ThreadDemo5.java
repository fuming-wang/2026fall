package src.main.Thread;

/**
 * 线程状态以及状态转移的观察
 */
public class ThreadDemo5 {
    public static void main(String[] args) {
        Thread t=new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++){
                    if(i == 10){
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else if(i == 20){
                        try {
                            Thread.currentThread().wait(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        System.out.println("start前："+t.getState());
        t.start();
        while(t.isAlive()){
            System.out.println("Alive:"+t.getState());
        }
        System.out.println("end:" + t.getState());
    }

}