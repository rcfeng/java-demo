package org.rcfeng.demo.utils.apache.commons;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

/**
 * Apache Commons - IOUtils
 * @author hesp
 *
 */
public class Demo_2_io_IOUtils {

	public static void main(String[] args) throws IOException {
		
//		 try{
//	            byte[] bytes = new byte[4];
//	            InputStream is = IOUtils.toInputStream("hello world");
//	            IOUtils.read(is, bytes);
//	            System.out.println(new String(bytes));
//
//	            bytes = new byte[20];
//	            is = IOUtils.toInputStream("hello world");
//	            IOUtils.readFully(is, bytes);
//	            System.out.println(new String(bytes));
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//		IOUtils.copyLarge(input, output)
//		IOUtils.toByteArray(input)
//		IOUtils.closeQuietly(input);
//		IOUtils.toString(input)
		 IOUtils.toInputStream("hello world", "utf8") ;
	}
}
