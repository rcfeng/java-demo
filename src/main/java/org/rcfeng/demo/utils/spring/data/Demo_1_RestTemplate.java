package org.rcfeng.demo.utils.spring.data;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.ueb.commons.utils.JsonUtils;

/**
 * Spring Data - RestTemplate
 * @author hesp
 *
 */
public class Demo_1_RestTemplate {

	public static void main(String[] args) throws Exception {
		String url = "http://172.16.6.161:8084/image/skuimageinfo" ;
		RestTemplate rs = new RestTemplate() ;
		
		Map<String,String> paramMap = new HashMap<>() ;
		paramMap.put("account","25") ;
		paramMap.put("platform","pm") ;
		paramMap.put("sku","111030") ;
		
		System.out.println("请求参数=========="+JsonUtils.toJson(paramMap));
		System.out.println("请求过程==========");
		Object response = rs.postForObject(url, paramMap, Object.class) ;
		
		System.out.println("返回结果=========="+JsonUtils.toJson(response));
	}
}
