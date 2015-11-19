/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.common.server;

import com.common.utils.PropertiesReaderUtils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 调度服务器的服务器
 */
public final class ScheduleServer {
    private static Log log = LogFactory.getLog(ScheduleServer.class);

    // 单例模式
    private static ScheduleServer scheduleServer;

    // 端口号
    private int port;

    private EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    private EventLoopGroup workerGroup = new NioEventLoopGroup();

    // 从配置文件获得
    private ScheduleServer(String propertiesFileName) throws Exception {
        String p = PropertiesReaderUtils.getPropertiesAttr(propertiesFileName, "port");
        if(p == null) {
            throw new Exception();
        }
        port = Integer.parseInt(p);
    }

    // 从配置文件获得
    private ScheduleServer(String propertiesFileName, String portAttr) throws Exception {
        String p = PropertiesReaderUtils.getPropertiesAttr(propertiesFileName, portAttr);
        if(p == null) {
            throw new Exception();
        }
        port = Integer.parseInt(p);
    }

    // 用户自己设定
    private ScheduleServer(int port) {
        this.port = port;
    }

    /**
     * 三个getInstance
     * @param propertiesFileName
     * @return
     * @throws Exception
     */
    public static ScheduleServer getInstance(String propertiesFileName) throws Exception {
        scheduleServer = new ScheduleServer(propertiesFileName);
        return scheduleServer;
    }

    public static ScheduleServer getInstance(String propertiesFileName, String portAttr) throws Exception {
        scheduleServer = new ScheduleServer(propertiesFileName);
        return scheduleServer;
    }

    public static ScheduleServer getInstance(int port) {
        scheduleServer = new ScheduleServer(port);
        return scheduleServer;
    }

    // 开启服务器
    public void startServer() throws Exception {
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class)
             .option(ChannelOption.SO_BACKLOG, 100)
             .handler(new LoggingHandler(LogLevel.INFO))
             .childHandler(new ChannelInitializer<SocketChannel>() {
                 @Override
                 public void initChannel(SocketChannel ch) throws Exception {
                     ChannelPipeline p = ch.pipeline();
                     //p.addLast(new LoggingHandler(LogLevel.INFO));
                     p.addLast(new ScheduleServerHandler());
                 }
             });

            // Start the server.
            ChannelFuture f = b.bind(this.port).sync();

            // Wait until the server socket is closed.
            f.channel().closeFuture().sync();
        } finally {
            // Shut down all event loops to terminate all threads.
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    // 关闭服务器
    public void closeServer() {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

    // test
    public static void main(String[] args) {
        try {
            new ScheduleServer(10000).startServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
