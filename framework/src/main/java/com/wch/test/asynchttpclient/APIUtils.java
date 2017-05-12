package com.wch.test.asynchttpclient;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.StringUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.security.GeneralSecurityException;

/**
 *
 * @author: wandou
 * @date: 14-8-18 上午11:20
 */
public class APIUtils {
    /** UTF-8字符集 **/
    public static final String CHARSET_UTF8 = "UTF-8";
    
    public static int check(String appKey, String timestamp, String sign, String version) {

        if (StringUtils.indexOf(version, "RWB") != -1 || StringUtils.indexOf(version, "MALL") != -1) {
            return 0;
        }
        
        if (StringUtils.isBlank(appKey) || StringUtils.isBlank(timestamp) || StringUtils.isBlank(sign)
                || StringUtils.isBlank(version)) {
            return 1;
        }
        // 去掉sessionid
//        if (!oldSessionId.equals(sessionId)) {
//            return 1;
//        }
        
        if (!getAppKey().equals(appKey)) {
            return 1;
        }
        
        // 平板收银的场合
        if ("RCP".equals(StringUtils.substringBefore(version, "_"))) {
            // API服务端允许客户端请求时间误差为10分钟
            if (TimeUtils.cutDateMinute(Long.parseLong(timestamp), System.currentTimeMillis()) > 10 || 
                    TimeUtils.cutDateMinute(System.currentTimeMillis(), Long.parseLong(timestamp)) > 10) {
                return 2;
            }
        }

        try {
            if (!getSign(timestamp).equals(sign)) {
                return 1;
            }
        } catch (IOException e) {
            return 1;
        }
        
        return 0;
    }
    
    private static String getAppKey() {
        return "54b1304dd75c4d89a97a4241563ab4bc";
    }
    
    /**
     * 获取request请求签名
     * @param params
     * @param secret
     * @param isHmac
     * @return
     * @throws IOException
     */
    public static String getSign(String timestamp) throws IOException {
        // 把所有参数值串在一起
        StringBuilder query = new StringBuilder();

        query.append(timestamp);
        // HMAC加密
        byte[] bytes;
        bytes = encryptHMAC(query.toString(), "0447f776c68a4f4e99282787422a828d");

        // 把二进制转化为大写的十六进制
        return byte2hex(bytes);
    }

    private static byte[] encryptHMAC(String data, String secret) throws IOException {
        byte[] bytes = null;
        try{
            SecretKey secretKey = new SecretKeySpec(secret.getBytes(CHARSET_UTF8), "HmacMD5");
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            bytes = mac.doFinal(data.getBytes(CHARSET_UTF8));
        } catch (GeneralSecurityException gse){
            String msg = getStringFromException(gse);
            throw new IOException(msg);
        }
        return bytes;
    }

    private static String getStringFromException(Throwable e){
        String result = "";
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(bos);

        e.printStackTrace();
        try{
            result = bos.toString(CHARSET_UTF8);
        } catch (IOException ioe){

        }
        return result;
    }

    private static String byte2hex(byte[] bytes){
        StringBuilder sign = new StringBuilder();
        for(int i = 0;i < bytes.length; i++){
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if(hex.length() == 1){
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }
}
