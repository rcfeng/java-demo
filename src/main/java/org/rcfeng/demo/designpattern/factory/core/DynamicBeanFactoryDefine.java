package org.rcfeng.demo.designpattern.factory.core;

import org.rcfeng.demo.designpattern.factory.demo1.PlatformOrderService;

/**
 * 动态Bean工厂声明接口
 *	业务接口继承该接口后,会把业务接口类型注册到factoryMap
 * @see PlatformOrderService
 * @author hesp
 */
public interface DynamicBeanFactoryDefine {

	/**
	 * 实现类的唯一Key,用于定位到具体实现类
	 * @return
	 */
	public DynamicBeanFactoryKey getKey() ;
}
