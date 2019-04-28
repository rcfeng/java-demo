package org.rcfeng.demo.nio;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

/**
 * Netty 客户端
 * @author hesp
 * 简单测试亦可使用 telnet
 *
 */
public class Demo_2_NettyClient {
	
	private final static String HOST = "127.0.0.1" ;
	private final static int PORT = Demo_1_NettyServer.PORT ;	//端口

	public static void main(String[] args) {
		run();
	}
	
	private static void run() {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
		    Bootstrap b = new Bootstrap();
		    b.group(group) // 注册线程池
            .channel(NioSocketChannel.class) // 使用NioSocketChannel来作为连接用的channel类
            .remoteAddress(new InetSocketAddress(HOST, PORT)) // 绑定连接端口和host信息
            .handler(new ChannelInitializer<SocketChannel>() { // 绑定连接初始化器
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    System.out.println("connected...");
                    ch.pipeline().addLast(new EchoClientHandler());
                }
            });
//		    System.out.println("created..");

		    ChannelFuture cf = b.connect().sync(); // 异步连接服务器
//		    System.out.println("connected..."); // 连接完成

		    cf.channel().closeFuture().sync(); // 异步等待关闭连接channel
		    System.out.println("closed.."); // 关闭完成
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
		    try {
		    	// 释放线程池资源
				group.shutdownGracefully().sync();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
	}
}

class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

	/**
	 * 通道连接时触发
	 */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty message testing !", CharsetUtil.UTF_8)); // 必须有flush

        // 必须存在flush
        // ctx.write(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
        // ctx.flush();
    }

    /**
     * 接受消息时触发
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println("client channelRead..");
        ByteBuf buf = msg.readBytes(msg.readableBytes());
        System.out.println("Client received:" + ByteBufUtil.hexDump(buf) + "; The value is:" + buf.toString(CharsetUtil.UTF_8));
        //ctx.channel().close().sync();// client关闭channel连接
    }

    /**
     * 发生异常时触发
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}