package org.rcfeng.demo.designpattern.factory.demo2;

import org.rcfeng.demo.designpattern.factory.core.DynamicBeanFactoryDefine;

/**
 * 产品业务接口
 * @author hesp
 *
 */
public interface PlatformProductService extends DynamicBeanFactoryDefine{
	
	Object getProductlist() ;
	
	
}
