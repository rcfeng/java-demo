package org.rcfeng.demo.designpattern.factory.demo1;

import org.rcfeng.demo.designpattern.factory.core.DynamicBeanFactoryKey;
import org.springframework.stereotype.Service;

/**
 * 订单业务-ali平台的实现类demo
 * @author hesp
 *
 */
@Service
public class AliExpressOrderServiceImpl implements PlatformOrderService {

	
	@Override
	public DynamicBeanFactoryKey getKey() {
		return PlatformKeyEnum.ALI;
	}
	
	
	@Override
	public Object getOrderlist() {
		
		
		System.out.println("AliExpressOrderServiceImpl.getOrderlist()");
		return null;
	}



}
