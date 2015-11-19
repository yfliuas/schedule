package com.common.client;

import com.common.utils.PropertiesReaderUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TR on 2015/11/18.
 */
public class WrappedClient {
    private static Log log = LogFactory.getLog(WrappedClient.class);

    // 单例模式
    private static WrappedClient wrappedClient;

    private String host;
    private int port;

    // 默认的构造函数
    private WrappedClient(String propertiesFileName) throws Exception {
        this.host = PropertiesReaderUtils.getPropertiesAttr(propertiesFileName, "host");
        String p = PropertiesReaderUtils.getPropertiesAttr(propertiesFileName, "port");
        if(p == null) {
            throw new Exception();
        }
        this.port = Integer.parseInt(p);
    }

    // 默认的构造函数
    private WrappedClient(String propertiesFileName, String hostAttr, String portAttr) throws Exception {
        this.host = PropertiesReaderUtils.getPropertiesAttr(propertiesFileName, hostAttr);
        String p = PropertiesReaderUtils.getPropertiesAttr(propertiesFileName, portAttr);
        if(p == null) {
            throw new Exception();
        }
        this.port = Integer.parseInt(p);
    }

    // 用户自定义
    private WrappedClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static WrappedClient getInstance(String propertiesFileName) throws Exception {
        wrappedClient = new WrappedClient(propertiesFileName);
        return wrappedClient;
    }

    public static WrappedClient getInstance(String propertiesFileName, String hostAttr, String portAttr) throws Exception {
        wrappedClient = new WrappedClient(propertiesFileName, hostAttr, portAttr);
        return wrappedClient;
    }

    public static WrappedClient getInstance(String host, int port) {
        wrappedClient = new WrappedClient(host, port);
        return wrappedClient;
    }

    // 发送数据
    // 发送的数据是一个Object
    public void send(final Object data) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new WrappedClientHandler(data));
                        }
                    });

            // Start the client.
            ChannelFuture f = b.connect(this.host, this.port).sync();

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            // Shut down the event loop to terminate all threads.
            group.shutdownGracefully();
        }
    }

    // 回调函数
    public static void callback(Object callback) {
        log.info("回调函数");
    }

    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<String>();
        list.add("sjhdbsjdbj");
        list.add("sjhdbsjdbj");
        list.add("sjhdbsjdbj");
        list.add("sjhdbsjdbj");
        list.add("sjhdbsjdbj");

        WrappedClient wrappedClient = WrappedClient.getInstance("127.0.0.1", 10000);
        wrappedClient.send(list);
    }

}
