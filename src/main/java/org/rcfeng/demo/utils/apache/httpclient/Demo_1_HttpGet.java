package org.rcfeng.demo.utils.apache.httpclient;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

public class Demo_1_HttpGet {

//	public static void main(String[] args) {
//		 InputStream is = null ;
//	        try {
//	        	//创建默认的httpClient实例.
//	        	HttpClient client = HttpClients.createDefault() ;
//	        	//创建get请求方式
//	            HttpGet httpget = new HttpGet(remotePath);  
////	            HttpParams httpParameters = new BasicHttpParams();    
////	            HttpConnectionParams.setConnectionTimeout(httpParameters,    
////	                    timeoutConnection);    
////	            HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);    
//	            
//	            httpget.getParams() ;
//	            //获取HttpResponse
//	            HttpResponse response = client.execute(httpget); 
//	            
//	            StatusLine statusLine = response.getStatusLine() ;
//	            //判断状态码是否一致
//	            if(HttpStatus.OK.value()==statusLine.getStatusCode()){
//	            	//调用HttpResponse的getEntity()方法可获取HttpEntity对象，该对象包装了服务器的响应内容。程序可通过该对象获取服务器的响应内容。
//	            	HttpEntity entity = response.getEntity();
//	                //获取一个字符输入流
//	            	is = entity.getContent();  
//	            	//读取输入流内容的字节到字节数组中。
//	            	byte[] data = IOUtils.toByteArray(is);
//	            	
//	            	if(data.length==0){
//	            		throw new Exception("!!!!! 远程文件不能找到 ,文件下载地址 ["+remotePath+"] !!!!!") ;
//	            	}
//	            	 
//	            	return data ;
//	            }else{
//	            	throw new Exception(statusLine.toString()) ;
//	            }
//			} catch (Exception e) {
//				throw e ;
//			}finally {
//				//关流
//				IOUtils.closeQuietly(is);
//			}
//	        
//	}
}
