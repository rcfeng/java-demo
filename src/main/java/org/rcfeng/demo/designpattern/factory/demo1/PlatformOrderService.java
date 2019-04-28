package org.rcfeng.demo.designpattern.factory.demo1;

import org.rcfeng.demo.designpattern.factory.core.DynamicBeanFactoryDefine;

/**
 * 订单业务接口
 * @author hesp
 *
 */
public interface PlatformOrderService extends DynamicBeanFactoryDefine{
	
	Object getOrderlist() ;
	
	
}
