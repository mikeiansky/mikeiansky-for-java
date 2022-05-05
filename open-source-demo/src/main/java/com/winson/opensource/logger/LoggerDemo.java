package com.winson.opensource.logger;

//import org.apache.log4j.Logger;


//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author winson
 * @date 2021/9/6
 **/
public class LoggerDemo {

    //    private static final Logger logger = Logger.getLogger(LoggerDemo.class);
//    private static final Logger logger = Logger.getLogger(LoggerDemo.class);
    private static final Logger logger = LoggerFactory.getLogger(LoggerDemo.class);

    public static void sayHello(String msg) {
//        System.out.println("this is say hello method : " + msg);
        logger.info("  say hello , are you ok {} , end message ..... ", msg);
    }

    public static void main(String[] args) {
//        logger.traceEntry();
//        logger.trace("Hello World!");
//        logger.debug("Hello World! a={},b={}");
//        System.out.println(new Date());
//        System.out.println(TimeZone.getDefault());
//        logger.info("<< ===> p1:{}, p2:{}, p3:{}, p4:{}", 1, 2, 3, 4);

//        byte[] buf = new byte[1024];
//        try {
//            for (; ; ) {
//                try {
//                    int length = System.in.read(buf);
//                    String msg = new String(buf, 0, length);
//                    msg = msg.substring(0,msg.length()-1);
////                    msg.replace("\n","");
//                    sayHello(msg);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        try {
//            System.in.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        sayHello("ciwei");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("app end xxxxxxxx ");
    }

}
