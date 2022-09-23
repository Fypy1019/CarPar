package com.qf.utils;

import java.io.FileWriter;
import java.io.IOException;

//支付宝工具类
public class AlipayConfig {
    public static String app_id = "2016102200740980";

    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCaQ8kYffkbUTyRobRZJ74imCmQw9Fm1VtblxV9iIXxt6dYLxhGmehgXe6cH3iv7pxNUfSDtHBh4IsFY/nyy38j93dWtkRBOhCd6lrMAbO5tBeG+RabzHzSHFFrx8jh3b53AGuPeBVmlPe7kxc4+dXp1k8MqQAYF2HPusuq2rNfdoRUGhxOuRmfCFItuW5U/plGinteEZoieoVoF4/CgzgYXsLuBBfdRXpTT1K4ht+ivkfoWmktuU8R7nOjYRFXc7RXp4PCZEXkkJuvIBlXLNplF/Nw0BCuzo7sJg5HNO/f0I4Ls35Y2FBacqHvyY3ZH1V5ureDa1C9Rm/gGwUbcoLDAgMBAAECggEAQvxwVCxPgWczL+HR2G0DiS4dznjZcxfuhcUDVfF0iFPBXO+GURH/7+iZ9j1MQMkSIhkWXiJ6MmY4t+yD2sQ2vz10GrkmxlQaZxgeuTt7+YWUOc3r3VdcVDhruquUNU/+ptoN2szALj09nbvvd5Q1KUcgKFUyb0On+3YXfmddQHQfr9pKQzZIhBK3wissISSkmd9QGNczKj0xYLXleIQgRb6LK8G5rzS6O3VR7PHRWall6FGAr1didpIIuHdJ6T1Aqukx80EjVTMDmmI/LKvzi8lybGC/noDZB1VC/w7J/j4wXi6CPQ6PbblX6vgUYLJOa5m22POxsRafmLQA3TGggQKBgQDNYLoQyZyY8UY4j54PPenfZGFiz9lDjr97ijeXfBU89uc3YhyFf1QStq/O8a4pFRhzQncR+78FeJKjVUOH5U3WHt3DOsAp+E+y7vU5ZGhNFqsRioyzy3PW42TgNMzml/7QUgT86GVQmu62sCboa25MIAPGZRaNHXEbYMR0EIL5oQKBgQDASdeY1EAundxVROpAjk2eBzAKd4VucFH9dBeB+k+/j/HqH+4SWX7SiH6mY85Y7jystadiV6ITyX/NjEaSazqPHK/M+dKlycLINacgp7vAkPd37nmEXjavrz6hl4t85vOq3fHKLrgnWDzR7oxJ6sefq24y0TJuRBeatL2EdLiJ4wKBgQC4hC6m5nvK7Pop1t4q03VLPuQ/4n1QNO/UDlnjv5dVLn6NQ5hcWAkwxvEs+/V883cEFrLjdUopoCoelHwXm+xSc2jJ/f6ntm/kUsv+xDbxC2ymVdQTtLZeWe5HgSiWA/jrMMVKc4CS6p+NgrVyGxL9UcYSeL/KCKmV71qQ7ETAAQKBgQC1jkMxut5q5YRfQnWHxJUGq7SxfU6QCx9vFwkHIYfg0JHSxJkt3DCBqn0ir8Pqde72B4JmuWWAF1ZDhrovj204KRbYi91XyhvOm9IUZCtDU91E8Pbtg2NRGD8jpgn/QbU6VNryUpBwHcE3Wmw9cp9PZDblZl6efE5pNVRn1ylSjwKBgEQ0N6Is5l+x2GU7Wo7/J7Oi5vGF0mlvbcD3dtsUMDOYgWSQ39iKWTFy5TZcHlCkIEq881rshrYrSMDWvJ/SH7TTXeaoEI+Xnu0RMZLNxFosNykrmfGWfyHqlS0xtsbtznE6LoGPyFXmcTP79r3DcrtNQXQ6FXrQCJ9aUdhoQpib";

    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkIcmTHFYN/IMdK4ey1hSSj3ZSXjfnqfJwqnk9awvFvkqV7a/e7+tSMtpAf5gVf1rdnLyiwuIl00Ul3b96fDIxDFOrS0F57WS29qjQVADs7zh1wF3oeLasyNcCzvntK3RKDSrqxO7D4C05QXVvwOYAgXZ+SB6b9O9bBhsfWxozxZbF31TtOooyso1QZNVrUW9IKnDSJGxpvBuPuF7W+13yo2F9RzUJtWoX/pEKvWMGkVvCtnoa5ZP8bkjbrulzaeWEUUy6fItyReWgBPnZ23FujRfG6MXEnYHratinqp5ysdEGgl9utTqfyiPRqDkQMdYXU89M8HCOBaAoqZfzJUktQIDAQAB";

    public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

    public static String return_url = "http://localhost:8081/transaction/paySuccess";

    public static String sign_type = "RSA2";

    public static String charset = "utf-8";

    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    public static String log_path = "C:\\";

    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
