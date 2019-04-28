package org.rcfeng.demo.designpattern.factory.core;

import org.rcfeng.demo.designpattern.factory.demo1.PlatformKeyEnum;

/**
 * 动态Bean工厂Key接口
 * 实现该接口的类将作为key,从factoryMap获取具体实现类,建议使用枚举类
 * @author hesp
 * @see PlatformKeyEnum
 */
public interface DynamicBeanFactoryKey {

}
