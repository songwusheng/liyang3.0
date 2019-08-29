package com.heziz.liyang.utils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

public class StringUtil {

	public static boolean isNull(String str) {
		return str == null ? true : false;
	}

	public static boolean isEmpty(String str) {
		return str .equals("") ? true : false;
	}

	public static String isNullOrEmpty(String param) {
		if (isNull(param) || param.trim().length() == 0||param.equals("null")||param.equals("Null")||param.equals("NULL")) {
			return "-";
		}
		return param;
	}
	public static String isNullOrEmpty1(String param) {
		if (isNull(param) || param.trim().length() == 0||param.equals("null")||param.equals("Null")||param.equals("NULL")) {
			return "暂无描述";
		}
		return param;
	}

	public static String isNullOrEmpty2(String param) {
		if (isNull(param) || param.trim().length() == 0||param.equals("null")||param.equals("Null")||param.equals("NULL")) {
			return "";
		}
		return param;
	}

	public static boolean isSet(String param) {
		// doesn't ignore spaces, but does save an object creation.
		return param != null && param.length() != 0;
	}

	/**
	 * 拼接json字符串中属性的方法
	 * @param key 对象的属性名
	 * @param value 对象的属性值
	 * @return "key":"value"
	 */
	public static String JsonKeyValue(String key,String value){
		StringBuffer sb = new StringBuffer();
		sb.append("\"");
		sb.append(key);
		sb.append("\":");

		sb.append("\"");
		sb.append(value);
		sb.append("\"");

		return sb.toString();
	}

	/**
	 * 从服务器返回的图片链接获取图片名
	 * @param httpFileName
	 * @return
	 */
	public static String getFileNameByHttp(String httpFileName){
		String str = "";
		if(isSet(httpFileName)){
			String[] strs = httpFileName.split("/");
			str = strs[strs.length - 1];
		}

		return str;
	}

	public static String clearStr(String inputStr){
		String outPutStr = inputStr;
		if(inputStr != null){
			switch (inputStr){
				case "null,":
					outPutStr = inputStr.replaceAll("null,","");
					break;
				case "null,;":
					outPutStr = inputStr.replaceAll("null,;","");
					break;
				case "null":
					outPutStr = inputStr.replaceAll("null","");
					break;
				case "null,null,":
					outPutStr = inputStr.replaceAll("null,null,","");
					break;
				case "二级,null,":
					outPutStr = inputStr.replaceAll("二级,null,","二级");
					break;
				case "NULL":
					outPutStr = inputStr.replaceAll("NULL,","");
					break;
			}
		}else {
			outPutStr =" ";
		}
		return outPutStr;

	}

	public static List<String> getListfromString(String s) {
		if (s.length() == 0 && s == null) {
			return null;
		} else {
			String[] temp = s.split("@");
			List<String> string_list = Arrays.asList(temp);
			return string_list;
		}
	}

	public static String toUtf8(String str) {
		String result = null;
		try {
			result = new String(str.getBytes("UTF-8"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
