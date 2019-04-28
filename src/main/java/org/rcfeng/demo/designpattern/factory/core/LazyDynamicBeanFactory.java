package org.rcfeng.demo.designpattern.factory.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

/**
 * 懒加载动态Bean工厂
 * @author hesp
 */
@Component
public class LazyDynamicBeanFactory implements DynamicBeanFactory,ApplicationContextAware {

	ApplicationContext applicationContext ;
	//存放所有实现类
	private Map<Class<? extends DynamicBeanFactoryDefine>, Map<DynamicBeanFactoryKey, DynamicBeanFactoryDefine>> factoryMap = new HashMap<>() ;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext ;
	}
	
	/**
	 * 从动态Bean工厂获取具体实现类
	 * 懒加载模式
	 * @param clz 业务接口类型
	 * @param key 业务接口唯一Key(不同业务接口类型可重复)
	 * @return 具体实现类
	 * @throws Exception
	 */
	@Override
	public <T> T getBean(Class<? extends DynamicBeanFactoryDefine> clz,DynamicBeanFactoryKey key) throws Exception {
		return getBean(clz, key, null) ;
	}
	
	/**
	 * 从动态Bean工厂获取具体实现类
	 * 懒加载模式
	 * @param clz 业务接口类型
	 * @param key 业务接口唯一Key(不同业务接口类型可重复)
	 * @param defaultKey 若key没有具体实现,使用该key的默认实现
	 * @return 具体实现类
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getBean(Class<? extends DynamicBeanFactoryDefine> clz,DynamicBeanFactoryKey key,DynamicBeanFactoryKey defaultKey) throws Exception {
		
		if(!factoryMap.containsKey(clz)){
			Map<String, DynamicBeanFactoryDefine> implMap = (Map<String, DynamicBeanFactoryDefine>) applicationContext.getBeansOfType(clz) ;
			
			if(implMap != null && implMap.size() > 0){
				Map<DynamicBeanFactoryKey, DynamicBeanFactoryDefine> initBeanMap = Maps.newHashMap() ;
				for (Entry<String, DynamicBeanFactoryDefine> implEntry : implMap.entrySet()) {
					DynamicBeanFactoryDefine entry = implEntry.getValue() ;
					initBeanMap.put(entry.getKey(), entry) ;
				}
				factoryMap.put(clz, initBeanMap) ;
			}
		}
		Map<DynamicBeanFactoryKey, DynamicBeanFactoryDefine> beanMap = factoryMap.get(clz) ;
		
		if(!beanMap.containsKey(key)){
			if(defaultKey!=null && beanMap.containsKey(defaultKey)){
				return (T) beanMap.get(defaultKey) ;	//使用默认实现
			}
			throw new IllegalAccessException("不支持的实现类型") ;
		}
		return (T) beanMap.get(key) ;
	}
	
}
