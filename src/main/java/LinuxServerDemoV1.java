import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author winson
 * @date 2021/8/23
 **/
public class LinuxServerDemoV1 {

    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("172.16.2.214", 20001));
            System.out.println("server bind success ********* ");

            while (true) {
                Socket socket = serverSocket.accept();
                System.in.read();
                new Thread(() -> {
                    System.out.println("accept socket : " + socket);

                    try {
                        InputStream in = socket.getInputStream();

                        while (true){
                            byte[] buf = new byte[1024];
                            int readLength = in.read(buf);
                            if(readLength > 0){

                            } else if (readLength == 0){
                                System.out.println("read nothing");
                            } else {
                                System.out.println("read -1 , end ,ready to close ... ");
                                System.in.read();
                                socket.close();
                                break;
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        System.out.println("server finish ... ");
    }

}
