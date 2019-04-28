package org.rcfeng.demo.designpattern.factory;


import org.rcfeng.demo.designpattern.factory.core.DynamicBeanFactory;
import org.rcfeng.demo.designpattern.factory.demo1.PlatformKeyEnum;
import org.rcfeng.demo.designpattern.factory.demo1.PlatformOrderService;
import org.rcfeng.demo.designpattern.factory.demo2.PlatformProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 使用动态Bean工厂的demo类入口
 * @author hesp
 *
 */
@RestController
public class TestController {

	@Autowired
	DynamicBeanFactory dynamicBeanFactory ;
	
	@GetMapping("/order/{platformKeyEnum}")
	public Object getOrderlist(@PathVariable("platformKeyEnum") PlatformKeyEnum platformKeyEnum) throws Exception {
		
		if(platformKeyEnum==null){
			platformKeyEnum = PlatformKeyEnum.ALI ;
		}
		
		/**
		 * 通过动态Bean工厂获取具体实现类
		 * PlatformKeyEnum.ALI可接受参数传递
		 */
		PlatformOrderService service = dynamicBeanFactory.getBean(PlatformOrderService.class, platformKeyEnum) ;

		service.getOrderlist() ;
		
		return service.getClass() ;
	}
	
	@GetMapping("/product/{platformKeyEnum}")
	public Object getProductlist(@PathVariable("platformKeyEnum") PlatformKeyEnum platformKeyEnum) throws Exception {
		
		if(platformKeyEnum==null){
			platformKeyEnum = PlatformKeyEnum.ALI ;
		}
		
		/**
		 * 通过动态Bean工厂获取具体实现类
		 * PlatformKeyEnum.ALI可接受参数传递
		 */
		PlatformProductService service = dynamicBeanFactory.getBean(PlatformProductService.class, platformKeyEnum) ;

		service.getProductlist() ;
		
		return service.getClass() ;
	}
}
