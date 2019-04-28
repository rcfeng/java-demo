package org.rcfeng.demo.zookeeper;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * Zookeeper基本监听使用
 * @author hesp
 *
 */
public class Demo_1_Listener {

	public static void main(String[] args) throws Exception {
		SampleWatcher sampleWatcher = new SampleWatcher("/hesp");	//监听该路径
		sampleWatcher.connectZookeeper("172.16.6.160:2181");
		
		//防止主线程结束
		System.in.read();
	}

}

class SampleWatcher implements Watcher {

	public ZooKeeper zk = null;
	public CountDownLatch countDownLatch = new CountDownLatch(1);
	public Stat stat = new Stat();
	public String path = null ;

	/**
	 * 超时时间
	 */
	private static final int SESSION_TIME_OUT = 2000;

	 public SampleWatcher(String path) throws Exception {
		 this.path = path ;
	 }

	/**
	 * 连接zookeeper
	 * 
	 * @param host
	 * @throws Exception
	 */
	public void connectZookeeper(String host) throws Exception {
		zk = new ZooKeeper(host, SESSION_TIME_OUT, this);
		countDownLatch.await();
		System.out.println("zookeeper connection success");
	}

	@Override
	public void process(WatchedEvent event) {
		System.out.println("process ============" + event);
		try {
			if (KeeperState.SyncConnected == event.getState()) {
				if (EventType.None == event.getType() && null == event.getPath()) {
					//创建监听
					zk.exists(this.path, Boolean.TRUE) ;
					countDownLatch.countDown();
				}else if (EventType.NodeDataChanged == event.getType()) {// 节点数据变化
					String path = event.getPath();
					System.out.println(
							String.format("zk节点数据变化 path:[%s] data:[%s]", path, new String(zk.getData(path, Boolean.TRUE, stat))));
				}else if (EventType.NodeCreated == event.getType()) {// 节点数据变化
					String path = event.getPath();
					System.out.println(
							String.format("zk节点创建 path:[%s] data:[%s]", path, new String(zk.getData(path, Boolean.TRUE, stat))));
				}else if (EventType.NodeDeleted == event.getType()) {// 节点数据变化
					String path = event.getPath();
					System.out.println(
							String.format("zk节点删除 path:[%s] data:[%s]", path, new String(zk.getData(path, Boolean.TRUE, stat))));
				}else if (EventType.NodeChildrenChanged == event.getType()) {// 节点数据变化
					String path = event.getPath();
					System.out.println(
							String.format("zk子节点变化 path:[%s] data:[%s]", path, new String(zk.getData(path, Boolean.TRUE, stat))));
				}else {
					System.out.println("zk未知事件:"+event.getType());
				}
			}else{
				System.out.println("zookeeper unconnected ...");
			}
		} catch (KeeperException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
