import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

/**
 * @author winson
 * @date 2021/8/23
 **/
public class LinuxClientDemoV4 {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        try {
            for (int i = 10001; i < 65525; i++) {
                try {
                    Socket socket = new Socket();
                    socket.bind(new InetSocketAddress("192.168.159.1", i));
                    socket.connect(new InetSocketAddress("192.168.159.130", 10000));
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
//            System.out.println("connect server success ... ");
//            System.in.read();
//            while (true){
//                byte[] buf = new byte[1024];
//                int readLength = System.in.read(buf);
//                for (int i = 0; i < readLength; i++) {
//                    socket.getOutputStream().write(buf[i]);
//                }
//            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            try {
//                socket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("client demo finish ");

    }

}
