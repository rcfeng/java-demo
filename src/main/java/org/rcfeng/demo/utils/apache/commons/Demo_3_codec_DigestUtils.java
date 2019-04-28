package org.rcfeng.demo.utils.apache.commons;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Apache Commons - DigestUtils
 * @author hesp
 *
 */
public class Demo_3_codec_DigestUtils {

	public static void main(String[] args) throws Exception {
		
		String s = "Hello world !" ;
		
		System.out.println("s = "+s);
		System.out.println("md5 = "+DigestUtils.md5(s));
		System.out.println("md5Hex = "+DigestUtils.md5Hex(s));
		System.out.println("md2Hex = "+DigestUtils.md2Hex(s));
		System.out.println("sha256Hex = "+DigestUtils.sha256Hex(s));
		System.out.println("sha512Hex = "+DigestUtils.sha512Hex(s));
	}
}
