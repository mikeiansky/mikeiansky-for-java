package com.winson.framework.netty.rpc_v3;

import com.winson.framework.netty.rpc_v2.MyNettyRpcV2;
import com.winson.utils.common.SerUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;

/**
 * @author winson
 * @date 2021/8/31
 **/
public class MyNettyHttpServerV3 {

    public static class ServerReadChannelHandle extends ChannelInboundHandlerAdapter {

        private MyNettyRpcV3.RequestDispatch dispatch;

        public ServerReadChannelHandle(MyNettyRpcV3.RequestDispatch dispatch) {
            this.dispatch = dispatch;
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            FullHttpRequest httpRequest = (FullHttpRequest) msg;
            System.out.println("channel read http request : " + httpRequest);

            ByteBuf buf = httpRequest.content();
            byte[] contentByte = new byte[buf.readableBytes()];
            buf.readBytes(contentByte);
            System.out.println("contentByte[13] ---> " + contentByte[13]);

            MyNettyRpcV3.MyContent content = SerUtil.decode(contentByte, MyNettyRpcV3.MyContent.class);
//            System.out.println(content);


//            // 这里直接写会有问题
            ctx.executor().execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("content ---> " + content);
                    Object target = dispatch.getDispatch(content.getName());

                    String res = null;
                    try {
                        Method method = target.getClass().getMethod(content.getMethod(), content.getParamTypes());
                        res = (String) method.invoke(target, content.getArgs());
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                    MyNettyRpcV3.MyContent resContent = new MyNettyRpcV3.MyContent();
                    resContent.setContent(res);

                    byte[] rcbs = SerUtil.encode(resContent);

                    FullHttpResponse response = new DefaultFullHttpResponse(
                            HttpVersion.HTTP_1_0,
                            HttpResponseStatus.OK
                    );
                    response.headers().add("content-length", rcbs.length);
                    response.content().writeBytes(rcbs);

                    ctx.writeAndFlush(response);

                }
            });
        }

    }

    public static void startServer() throws Exception {
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup work = new NioEventLoopGroup(4);
        MyNettyRpcV3.Car myCar = mark -> " :::: server car mark [ " + mark+" ] :::: ";
        MyNettyRpcV3.RequestDispatch dispatch = new MyNettyRpcV3.RequestDispatch();
        dispatch.register(MyNettyRpcV3.Car.class.getName(),myCar);
        Channel server = new ServerBootstrap()
                .group(boss, work)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        System.out.println(" connect client : " + nioSocketChannel);
//                        nioSocketChannel.pipeline().addLast(new ServerDecode());
//                        nioSocketChannel.pipeline().addLast(new MyNettyRpcV3.ObjectChannelHandle("server"));
//                        nioSocketChannel.pipeline().addLast(new ServerReadChannelHandle(dispatch));

                        nioSocketChannel.pipeline()
                                .addLast(new HttpServerCodec())
                                .addLast(new HttpObjectAggregator(1024 * 512))
                                .addLast(new ServerReadChannelHandle(dispatch));

                    }
                })
                .bind("localhost", 9999)
                .sync()
                .channel();
        System.out.println(" server started ... ");


        server.closeFuture().sync();
        System.out.println(" server finish xxx ");
    }

//    public static class MyHttpServlet extends HttpServlet{
//
//        @Override
//        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//            super.doPost(req, resp);
//        }
//
//    }

    public static void startServerWithJetty() throws Exception{

        MyNettyRpcV3.Car myCar = mark -> "server car mark [ " + mark+" ]";
        MyNettyRpcV3.RequestDispatch dispatch = new MyNettyRpcV3.RequestDispatch();
        dispatch.register(MyNettyRpcV3.Car.class.getName(),myCar);

        Server server = new Server(new InetSocketAddress("localhost",9999));
        ServletContextHandler handler = new ServletContextHandler(server, "/");
        server.setHandler(handler);
        MyHttpServlet.dispatch = dispatch;
        handler.addServlet(MyHttpServlet.class, "/*");
//        handler.addServlet(HttpServlet.class, "/");
        server.start();
        server.join();

        //tomcat jetty  【servlet】
//        Server server = new Server(new InetSocketAddress("localhost", 9999));
//        ServletContextHandler handler = new ServletContextHandler(server, "/");
//        MyHttpServlet.dispatch = dispatch;
//        server.setHandler(handler);
//        handler.addServlet(MyHttpServlet.class,"/*");  //web.xml
//        server.start();
//        server.join();
    }

    public static void main(String[] args) {
        try {
//            startServer();
            startServerWithJetty();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("server end ... ");
    }

}
