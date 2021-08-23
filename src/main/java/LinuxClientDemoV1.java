import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author winson
 * @date 2021/8/23
 **/
public class LinuxClientDemoV1 {

    public static void main(String[] args) {

        Socket socket = new Socket();

        try {
            socket.connect(new InetSocketAddress("172.16.2.214", 20001));
            System.out.println("connect server success ... ");
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
