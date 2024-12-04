package com.winson.jdkapi.juc.v2;

/**
 * @author winson
 * @date 2022/5/6
 **/
public class FinalObjectEscapeDemoV1 {

    public static class FinalObject {
        private final int t;

        public FinalObject(FinalHolder holder) {
            holder.obj = this;
            int x = 1;
            int y = 2;
            t = 3;
        }

    }

    public static class FinalHolder {
        public FinalObject obj;
    }

    static final FinalHolder holder = new FinalHolder();
    static boolean end = false;

    public static void main(String[] args) throws InterruptedException {

        int i = 0;
        FinalObject foo = null;
        while (!end) {
            i++;
            holder.obj = null;
            FinalObject fo = new FinalObject(holder);
            final int ix = i;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    if (holder.obj != null && holder.obj.t <= 0) {
                        System.out.println("ix : " + ix + " , final t <=0 ");
                        end = true;
                    }
                }
            });
            thread.start();
            thread.join();

            foo = fo;
        }
        System.out.println(foo);

    }

}
