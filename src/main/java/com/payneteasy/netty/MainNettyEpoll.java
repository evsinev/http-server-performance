package com.payneteasy.netty;

import com.payneteasy.Config;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class MainNettyEpoll {


    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup group = new EpollEventLoopGroup();

        try {
            ServerBootstrap boot = new ServerBootstrap();
            boot.group(group)
                    .option(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)
                    .channel(EpollServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    .childHandler(new SocketChannelChannelInitializer());

            Channel channel = boot.bind(Config.NETTY_EPOLL).sync().channel();
            channel.closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }


    }


}
