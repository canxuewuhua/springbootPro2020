package com.example.demo.util;

import com.google.common.base.Joiner;
import org.apache.http.NameValuePair;
import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 */
public class Md5Utils {

    public final static String SEPARATOR_SIGN = "&";

    public final static String SECRET_KEY = "secretKey";

    public final static String EQUALS_FLAG = "=";

    /**
     * 通过map,secretKey获取签名内容
     * map转换升序排序，最后加上secretKey
     * @param params
     * @return
     */
    public static String getSignContent(Map<String, String> params,String secretKey) {
        return getSignContent(params) + SEPARATOR_SIGN + SECRET_KEY + EQUALS_FLAG + secretKey;
    }
    /**
     * 对签名转换升序排序
     *
     * @param params
     * @return
     */
    public static String getSignContent(List<NameValuePair> params, String secretKey) {
        return getSignContent(params) + SEPARATOR_SIGN + SECRET_KEY + EQUALS_FLAG + secretKey;
    }
    /**
     * 对签名转换升序排序
     *
     * @param params
     * @return
     */
    public static String getSignContent(List<NameValuePair> params) {
        Map<String, String> keyValueMap = new TreeMap<String, String>();
        for (NameValuePair nameValuePair : params) {
            if (!"sign".equals(nameValuePair.getName()) && !"signType".equals(nameValuePair.getName())) {
                keyValueMap.put(nameValuePair.getName(), nameValuePair.getValue());
            }
        }
//        logger.info("参加签名的参数列表：{}", Joiner.on("&").withKeyValueSeparator("=").join(keyValueMap));
        return Joiner.on("&").withKeyValueSeparator("=").join(keyValueMap);
    }

    public static String getMD5Encrypt(String string) {
        if (string == null || string.trim().length() < 1) {
            return null;
        }
        String result = null;
        // 用来将字节转换成 16 进制表示的字符
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(string.getBytes("UTF-8"));
            byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，
            // 用字节表示就是 16 个字节
            char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
            // 所以表示成 16 进制需要 32 个字符
            int k = 0; // 表示转换结果中对应的字符位置
            for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
                // 转换成 16 进制字符的转换
                byte byte0 = tmp[i]; // 取第 i 个字节
                str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
                // >>> 为逻辑右移，将符号位一起右移
                str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
            }
            result = new String(str); // 换后的结果转换为字符串

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 对签名转换升序排序
     *
     * @param params
     * @return
     */
    public static String getSignContent(Map<String, String> params) {
        Map<String, String> keyValueMap = new TreeMap<String, String>();

        for (Map.Entry<String, String> entry : params.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            if (!"sign".equals(key) && !"signType".equals(key)) {
                if (StringUtils.isNotEmpty(value) && value instanceof String ){
                    keyValueMap.put(key, value);
                }
            }

        }
        return Joiner.on("&").withKeyValueSeparator("=").join(keyValueMap);
    }

    public static void main(String[] args){
        System.out.println(Md5Utils.getMD5Encrypt("1111111"));
    }


}
