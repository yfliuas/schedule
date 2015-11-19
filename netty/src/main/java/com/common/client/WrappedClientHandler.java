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
package com.common.client;

import com.common.utils.Byte2ObjectUtils;
import com.common.utils.Object2ByteBuffUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Handler implementation for the echo client.  It initiates the ping-pong
 * traffic between the echo client and server by sending the first message to
 * the server.
 */
public class WrappedClientHandler extends ChannelInboundHandlerAdapter {
    private static Log log = LogFactory.getLog(WrappedClientHandler.class);

    private ByteBuf data;

    /**
     * Creates a client-side handler.
     */
    public WrappedClientHandler(Object data) {
        this.data = Object2ByteBuffUtils.transform(data);
        log.info("client has init data");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(data);
        System.out.println(data);
        System.out.println("client has send data to server");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf)msg;
        int len = byteBuf.readableBytes();
        byte[] bytes = new byte[len];
        for(int i=0;i<len;i++) {
            bytes[i] = byteBuf.getByte(i);
        }
        System.out.println(Byte2ObjectUtils.transform(bytes));
        System.out.println("server返回了数据");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        // 把数据释放掉，返回了数据之后直接关闭连接
        ctx.flush();
        ctx.close();
//        System.gc();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }

}
