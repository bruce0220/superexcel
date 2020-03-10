package com.bruce.superexcel.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * StringUtils处理类，继承apache的StringUtils,
 * 扩展的字符处理相关方法写在这里
 * */
public class SuperExcelStringUtils extends org.apache.commons.lang3.StringUtils{

	public SuperExcelStringUtils() {
		super();
	}
	
	public static String cutLastChar(String str) {
		if (str.length() > 0) {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}

	public static String URLDecodeUTF8(String input) throws UnsupportedEncodingException {
		return URLDecode(input, "utf-8");
	}
	
	public static String URLDecode(String input, String encoding) throws UnsupportedEncodingException {
		String output = "";
		if (SuperExcelStringUtils.isNotBlank(input)) {
			output = URLDecoder.decode(input, encoding);
		}
		return output;
	}

    public static String URLEnCode(String input, String encoding) throws UnsupportedEncodingException {
        String output = "";
        if (SuperExcelStringUtils.isNotBlank(input)) {
            output = URLEncoder.encode(input, encoding);
        }
        return output;
    }

	public static String numberPadding0(long value, int len){
	    return String.format("%0"+len+"d", value);
	}
	
	/** 
     * 使用java正则表达式去掉多余的.与0 
     * @param s 
     * @return  
     */  
    public static String subZeroAndDot(String s){
        if(s.indexOf(".") > 0){  
            s = s.replaceAll("0+?$", "");//去掉多余的0  
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉  
        }  
        return s;  
    }  
    
    public static String firstCharUpper(String str) {
    	String first = str.substring(0, 1);
		String second = str.substring(1);
		return first.toUpperCase() + second;
    }

    /**
     * 通过正则表达式获取内容
     *
     * @param regex 正则表达式
     * @param from  原字符串
     *
     * @return
     */
    public static String[] regex(String regex, String from) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(from);
        List<String> results = new ArrayList<String>();
        while (matcher.find()) {
            for (int i = 0; i < matcher.groupCount(); i++) {
                results.add(matcher.group(i + 1));
            }
        }
        return results.toArray(new String[] {});
    }

    public static <T> String customerJoin(String separator, T... elements) {
        return join((Object[]) elements, separator);
    }

    /**
     * 类似 {@link SuperExcelStringUtils#substringBefore(String, String)},但是它保留截取的符号
     *
     * @param str
     * @param separator
     *
     * @return
     */
    public static String capture(String str, String separator) {
        if (isEmpty(str) || separator == null) {
            return str;
        }
        if (separator.isEmpty()) {
            return EMPTY;
        }
        final int pos = str.indexOf(separator);
        if (pos == INDEX_NOT_FOUND) {
            return str;
        }
        return str.substring(0, pos + 1);
    }


    public static String char2Ascii(String str, int position){
        StringBuilder sb = new StringBuilder();
        for(char c :str.toCharArray()){
            int intC = (int)c;
            char sr;
            if(intC + position  > 125 ) {
                sr =  (char)(33 + intC + position - 125);
            }else{
                sr = (char)(intC + position);
            }
            sb.append(sr);
        }
        return sb.toString();
    }
}
