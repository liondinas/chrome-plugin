package com.rawind.queqiao.web.util;

import org.apache.commons.lang.StringUtils;


public class ProxyUrlEncrypt {

	
	public static String encode(String proxy){
		
		if(StringUtils.isBlank(proxy)){
			return null;
		}
		
		
		String arr[] = proxy.split("\\.");
				
		
		if(arr!=null && arr.length>2){
			arr[1]="***";
			arr[2]="***";
		}
		
		String retval = "";
		for(int i=0; i<arr.length; i++){
			if(i<arr.length-1){
				retval = retval + arr[i] + ".";
			}else{
				retval = retval + arr[i];
			}
		}
		return retval;
	}
	
	
	
	
	public static void main(String args[]){
		System.out.println(encode("PROXY 070.977.090.090:6008"));
	}
	
	
	
	
	
}
