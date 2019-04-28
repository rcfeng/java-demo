package org.rcfeng.demo.designpattern.factory.demo2;

import org.rcfeng.demo.designpattern.factory.core.DynamicBeanFactoryKey;
import org.rcfeng.demo.designpattern.factory.demo1.PlatformKeyEnum;
import org.springframework.stereotype.Service;

/**
 * 订单业务-EBAY平台的实现类demo
 * @author hesp
 *
 */
@Service
public class EbayProductServiceImpl implements PlatformProductService {

	
	@Override
	public DynamicBeanFactoryKey getKey() {
		return PlatformKeyEnum.EBAY;
	}
	
	
	@Override
	public Object getProductlist() {
		
		
		System.out.println("EbayProductServiceImpl.getOrderlist()");
		return null;
	}



}
