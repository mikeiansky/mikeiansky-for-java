import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author winson
 * @date 2021/8/23
 **/
public class LinuxClientDemoV4 {

    public static void main(String[] args) {

        Socket socket = new Socket();
        try {
            socket.bind(new InetSocketAddress("192.168.159.1", 10005));

            socket.connect(new InetSocketAddress("192.168.159.130", 20001));
            System.out.println("connect server success ... ");
            System.in.read();
            while (true){
                byte[] buf = new byte[1024];
                int readLength = System.in.read(buf);
                for (int i = 0; i < readLength; i++) {
                    socket.getOutputStream().write(buf[i]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("client demo finish ");

    }

}
