package org.rcfeng.demo.nio;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;


/**
 * Netty 服务端
 * @author hesp
 * 参考文档:https://javadoop.com/post/netty-1	https://www.cnblogs.com/applerosa/p/7141684.html
 *
 */
public class Demo_1_NettyServer {
	
	public final static int PORT = 8007 ;	//端口

	public static void main(String[] args) {
		run();
	}
	
	private static void run() {


		/***
         * NioEventLoopGroup 是用来处理I/O操作的多线程事件循环器，
         * Netty提供了许多不同的EventLoopGroup的实现用来处理不同传输协议。 在这个例子中我们实现了一个服务端的应用，
         * 因此会有2个NioEventLoopGroup会被使用。 第一个经常被叫做‘boss’，用来接收进来的连接。
         * 第二个经常被叫做‘worker’，用来处理已经被接收的连接， 一旦‘boss’接收到连接，就会把连接信息注册到‘worker’上。
         * 如何知道多少个线程已经被使用，如何映射到已经创建的Channels上都需要依赖于EventLoopGroup的实现，
         * 并且可以通过构造函数来配置他们的关系。
         */
		EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        
        try {
            /**
             * ServerBootstrap 是一个启动NIO服务的辅助启动类 你可以在这个服务中直接使用Channel
             */
            ServerBootstrap b = new ServerBootstrap();
            /**
             * 这一步是必须的，如果没有设置group将会报java.lang.IllegalStateException: group not
             * set异常
             */
            b = b.group(bossGroup, workerGroup);
            /***
             * ServerSocketChannel以NIO的selector为基础进行实现的，用来接收新的连接
             * 这里告诉Channel如何获取新的连接.
             */
            b = b.channel(NioServerSocketChannel.class);
            
            
//            b = b.handler(new LoggingHandler(LogLevel.INFO)) ;
            
            /***
             * 这里的事件处理类经常会被用来处理一个最近的已经接收的Channel。 ChannelInitializer是一个特殊的处理类，
             * 他的目的是帮助使用者配置一个新的Channel。
             * 也许你想通过增加一些处理类比如NettyServerHandler来配置一个新的Channel
             * 或者其对应的ChannelPipeline来实现你的网络程序。 当你的程序变的复杂时，可能你会增加更多的处理类到pipline上，
             * 然后提取这些匿名类到最顶层的类上。
             */
            b = b.childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast(new DiscardServerHandler());
//                    pipeline.addLast(new EchoServerHandler());
                    
//                    pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                }
            });
            /***
             * 你可以设置这里指定的通道实现的配置参数。 我们正在写一个TCP/IP的服务端，
             * 因此我们被允许设置socket的参数选项比如tcpNoDelay和keepAlive。
             * 请参考ChannelOption和详细的ChannelConfig实现的接口文档以此可以对ChannelOptions的有一个大概的认识。
             */
            b = b.option(ChannelOption.SO_BACKLOG, 128);
            /***
             * option()是提供给NioServerSocketChannel用来接收进来的连接。
             * childOption()是提供给由父管道ServerChannel接收到的连接，
             * 在这个例子中也是NioServerSocketChannel。
             */
            b = b.childOption(ChannelOption.SO_KEEPALIVE, true);
            /***
             * 绑定端口并启动去接收进来的连接
             */
            ChannelFuture f = b.bind(PORT).sync();
            System.out.println("启动完成，监听端口：" + PORT);
            
            /**
             * 这里会一直等待，直到socket被关闭
             */
            f.channel().closeFuture().sync();
            
        } catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
            /***
             * 关闭
             */
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
	}
}

/**
 * "抛弃"处理,收到什么都丢弃
 * @author hesp
 *
 */
class DiscardServerHandler extends ChannelInboundHandlerAdapter  {
	/**
     * 这里我们覆盖了chanelRead()事件处理方法。 每当从客户端收到新的数据时， 这个方法会在收到消息时被调用，
     * 这个例子中，收到的消息的类型是ByteBuf
     * @param ctx 通道处理的上下文信息
     * @param msg 接收的消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//    	System.out.println("DiscardServerHandler");
        try {
            ByteBuf in = (ByteBuf) msg;
            // 打印客户端输入，传输过来的的字符
            System.out.print(in.toString(CharsetUtil.UTF_8));
        } finally {
            /**
             * ByteBuf是一个引用计数对象，这个对象必须显示地调用release()方法来释放。
             * 请记住处理器的职责是释放所有传递到处理器的引用计数对象。
             */
            // 抛弃收到的数据
            ReferenceCountUtil.release(msg);
        }
    }

    /***
     * 这个方法会在发生异常时触发
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        /**
         * exceptionCaught() 事件处理方法是当出现 Throwable 对象才会被调用，即当 Netty 由于 IO
         * 错误或者处理器在处理事件时抛出的异常时。在大部分情况下，捕获的异常应该被记录下来 并且把关联的 channel
         * 给关闭掉。然而这个方法的处理方式会在遇到不同异常的情况下有不 同的实现，比如你可能想在关闭连接之前发送一个错误码的响应消息。
         */
        // 出现异常就关闭
        cause.printStackTrace();
        ctx.close();
    }

}

/**
 * "回声"处理,收到什么就返回什么
 * @author hesp
 *
 */
class EchoServerHandler extends ChannelInboundHandlerAdapter {
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
//		System.out.println("EchoServerHandler");
		//ChannelHandlerContext提供各种不同的操作用于触发不同的I/O时间和操作
		//调用write方法来逐字返回接收到的信息
		//这里我们不需要在DISCARD例子当中那样调用释放，因为Netty会在写的时候自动释放
		//只调用write是不会释放的，它会缓存，直到调用flush
		ctx.write(msg);
		ctx.flush();
		//你可以直接使用writeAndFlush(msg)
		//ctx.writeAndFlush(msg);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
