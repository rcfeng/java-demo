package org.rcfeng.demo.designpattern.factory.core;

import org.rcfeng.demo.designpattern.factory.TestController;

/**
 * 动态Bean工厂
 * 通过该工厂可以获取不同业务接口类型的具体实现类
 * @author hesp
 * @see TestController
 */
public interface DynamicBeanFactory {

	/**
	 * 从动态Bean工厂获取具体实现类
	 * @param clz 业务接口类型
	 * @param key 业务接口唯一Key(不同业务接口类型可重复)
	 * @return 具体实现类
	 * @throws Exception
	 */
	public <T> T getBean(Class<? extends DynamicBeanFactoryDefine> clz,DynamicBeanFactoryKey key) throws Exception  ;
	
	
	/**
	 * 从动态Bean工厂获取具体实现类
	 * @param clz 业务接口类型
	 * @param key 业务接口唯一Key(不同业务接口类型可重复)
	 * @param defaultKey 若key没有具体实现,使用该key的默认实现
	 * @return 具体实现类
	 * @throws Exception
	 */
	public <T> T getBean(Class<? extends DynamicBeanFactoryDefine> clz,DynamicBeanFactoryKey key,DynamicBeanFactoryKey defaultKey) throws Exception  ;
}
