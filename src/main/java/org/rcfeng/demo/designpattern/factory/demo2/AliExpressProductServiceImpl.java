package org.rcfeng.demo.designpattern.factory.demo2;

import org.rcfeng.demo.designpattern.factory.core.DynamicBeanFactoryKey;
import org.rcfeng.demo.designpattern.factory.demo1.PlatformKeyEnum;
import org.springframework.stereotype.Service;

/**
 * 订单业务-ali平台的实现类demo
 * @author hesp
 *
 */
@Service
public class AliExpressProductServiceImpl implements PlatformProductService {

	
	@Override
	public DynamicBeanFactoryKey getKey() {
		return PlatformKeyEnum.ALI;
	}
	
	
	@Override
	public Object getProductlist() {
		
		
		System.out.println("AliExpressProductServiceImpl.getProductlist()");
		return null;
	}



}
