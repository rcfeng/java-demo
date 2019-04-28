package org.rcfeng.demo.designpattern.factory.demo1;

import org.rcfeng.demo.designpattern.factory.core.DynamicBeanFactoryKey;
import org.springframework.stereotype.Service;

/**
 * 订单业务-wish平台的实现类demo
 * @author hesp
 *
 */
@Service
public class WishOrderServiceImpl implements PlatformOrderService {

	
	@Override
	public DynamicBeanFactoryKey getKey() {
		return PlatformKeyEnum.WISH;
	}
	
	
	@Override
	public Object getOrderlist() {
		
		
		System.out.println("WishOrderServiceImpl.getOrderlist()");
		return null;
	}



}
