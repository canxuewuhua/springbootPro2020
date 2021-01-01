package mi;

import com.example.demo.SpringBootWildApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * 小米金融网关加解密 单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootWildApplication.class)
@Slf4j
public class XiaoMiSignTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 小米金融私钥 1024位 PKCS#8
     */
    private static final String sncfcPriKeyStr = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDoIkQpg0U3HrfA3E4qfjBvV4CvJnl8YhF0qkNvUa0Uxrq9AT/k2S16R5JuZmEICSx/RatFLM/nrYG/Qj+dbRyFEqlQmW4I1rOps9TkiEHPzBVfllGXGv56K9RXSeyRbrzc+EuHFbs/Hs3umkRsnBBCgNvCtHU6GUaaFcepnlya6yvI1+xmQZA0HNcnvHg3Sg1fnMRQ5PdMmtfBkvOylpp42sIMuX8e8bQRQ5ZwTGYCHMe4XxzG0zLGZgWZS77anFHvEnRep+hTN80dWsl8ibQFpDe3w+OJB1ZGEiNY66GXpVVnrZ1TUiO9QwRAM4dco70xmwqA3OpeOpIchXyBqw//AgMBAAECggEBAOLrhFhUY5gDZjuY4Kx3WN+u2TPe14l40HmMa4lpfVm+/xL+IriSePt89a8IU7goR6BOuaNQsBhZK2mM2GULIYMQgAUzRzZKs6ykB+wzKqp4ZThg2HdvQu6o49VEhYaRivwzmJl/6+BDO6oQkezeDqMEFk/aWnIl/TdpLRopnXstpkXiYb/nI9OASGmNBszB9llv53bAlyhW8ak+5joFV40JdpSCmqj9lqevm6AkgzkqvK+X5/YCqd/99mgVD6WFKK4zx0L26SSFHRLDTLpREL/d3wCFCDOqPbIlnhndxeWGRvqDon3SmFRKKSYrgD3abDAgqHmykgT57jIrPGfY78ECgYEA/h7+MPC9T2F1Wvj6Ug0cTJlzknAleuinEVxLlROvQCEnaglqcrCqtImVkTE1zwaYPO6Qa8Awc1l/y4h47QGMcxLRoTZs3q2J2t69mVtWN29qCtYhyStNfsrSS/9ObAEH8oFi5x391yxLtGAoGhwX+9VaAVxd5jkHF7CKfXkXzh8CgYEA6dmnxLp+uEyItWzYeBECXVp7NDV8V6sC/M6+1JTLs+PewdqBZ5yeBfA6gStpY2JZdmVMkccqax3L2yNQmuS0+mIKHVKmnbHyLLKdXak2OT9bY94zWDdtVHoY6GMGaVOcMpDEq/8yKDtMklip3TyqzbnBdXibRtC/P2CbNQvawiECgYA6S23QWEl1PkwnlFoIHI3TXJhQjX1rlpdMujl0d1GKipB6GLLcoH5ob+9poTpQxZiC0c87iJkUvQE99Y6X//TyKO0B6HRNGM448UPZPESo7twKpFiXx2E9ptVbLbQVvp7OGLRRhGXIyJaNSEmqw6sU0DqXeVLpD0f7PTpn0jGNzQKBgBRYkZY2O6xBTGusirD3meMRakLCnIzsHhhszIGmaIrAOrNXFYc0QzN1EUIHyuWQuwPoEF9mmrQzJXdtfSfc2jomqYafmEuEHfPxnmt1u3gDoSnxPTphqCt/bAdcBkTHt4FWwyXL0EtbzW+2MyFClAHONZzI34mrw9f5ycc1vtTBAoGAbLzsgGvYMVHdIgExBX6oEGUtdUo3knsv58FJmR0kncGit64f1gCeSaztMxQAn5G/e3fOEwOdXym+Pn6XXaphO2/MWSuv76ISFUDy+fIP6J66Kxvk9IVemPiqN45nDc/DyXtiLfBIHAW4gquYWXHE8yC8mfCQI+qTdmmzbfxnR7A=";

    /**
     * 小米金融公钥
     */
    private static final String sncfcPubKeyStr = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6CJEKYNFNx63wNxOKn4wb1eAryZ5fGIRdKpDb1GtFMa6vQE/5NktekeSbmZhCAksf0WrRSzP562Bv0I/nW0chRKpUJluCNazqbPU5IhBz8wVX5ZRlxr+eivUV0nskW683PhLhxW7Px7N7ppEbJwQQoDbwrR1OhlGmhXHqZ5cmusryNfsZkGQNBzXJ7x4N0oNX5zEUOT3TJrXwZLzspaaeNrCDLl/HvG0EUOWcExmAhzHuF8cxtMyxmYFmUu+2pxR7xJ0XqfoUzfNHVrJfIm0BaQ3t8PjiQdWRhIjWOuhl6VVZ62dU1IjvUMEQDOHXKO9MZsKgNzqXjqSHIV8gasP/wIDAQAB";

    /**
     * 公司私钥（公司自己生成）
     */
    private static final String partnerPriKeyStr = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCrB9bBmWMKTXFUcAOXV4Hbu0u9eoxzW5U-5g5N5WSTRf3qWX-AMJ6_AHcMOmWhXI2KwO9DS9oJr7qsfXbB9ZJ64KzouJFDaLdDQiqvoeOuZYbrUwaEAId-N2a7dAPTdYuT5gJTmaDtoncmv7hQ_msD-Iabc7rNOGmYGNAE8tklWj0UFIX4P-2I-pggrEKpHAlJxqutPn7Uq9uPRFeR-h2ibRW-ITiH5dwe0ecluEPUqIQXCtLnbQSlmExwWRtxzcgao_EGcaqrzVrrF1JCrxsuVTlsbwandqYZrvC2sWjWEILMqQAhL_ylrwKvg-valq_WdciX6kfJNFoZ5oufVA4dAgMBAAECggEALwQYpV_lOg_P98ZTNmd360-xooTt10h13rSfegp1e4biJmo0Aqla-mbGUzY6egFXZ61iGL3hUohcaM6Utv0rJ1xcLB2Q0VcYCgVGdsGqNWbzj_1i7Sa4GJpiQ2XBJ4BIvn-cZB73flwLJTvPD3ThNlY7qsBFueSoIoevp6yaxQl2ZFR9SRutkZjJ0UCdHtnb1NwvqppmPlfuspZfC1NcLLXaUOceliVUnLIpeX3kgGP41dgHYSucTwIgUwF2dXgxBabwCMtK9IRP3cY-U7_NWODdjBm2WoW6FyLf6oO_VSq37Q400sPBl59FYkn6QDwdcIgJ5Ahvy2Jyp-yr4uAIAQKBgQDYh6_MHSiwpy7CygymEWBNZP0uruI_qkOyLohLy46TME1XrF9NuD4snoOkoxdK3QjvHOBusNsv1hkcWoVfRO9w-7sZdNmjFklzXykPcwi3B6jPvo-FvNnMB05bpskIKRZEraT3lVdxO6Fu4n8SijoBUeJL0Ppyqi4dQc5uHFUfkQKBgQDKNO9jfuKf0cqIzhAsvg6Q8S11ny79yn1Y-P1F9BV-n1apgvWIoiDwfthnLQoJ0NpV3F644qI8j2fHqDei1yzpkSe8-OD08ZqT2dN9GVbNStkcMdy9j0W1vWPkchKGhF2jLtfmbfDPOzS5hPT7uf2uVpKoQebpTSUvgp5tUbfXzQKBgA99JUYkj9STIPUuHtmCRLFEmBfOysBjHZSRX46McRZ2ThIHconMtCXc8O3F_NSOGyV_m05PuF4joog0sXWvKLGcUDXx9d-EqJxoIjfIssbk9V5m8z1N1QaTPzevJpI-3ijrgvFy7V5cNaAI864P1TZi9R0XRdMN-V5-ZpXZ0P0BAoGAJqKaot4ZmwPQuPb2bDYcPmF2mCTEK8XG0XsxmW4btUfonRbipyqjNJBn9L7MTjfTn9gtWRIkgYG9CI8BomGOuUn818SN5v9K07kpCZwP08XbWeHFFYsMfL8lGP8avw0HfyhyJBsZid78If5feMF-0RLFjtSYtDcDReKlNqoIgHkCgYB6ErH9N10_hojkxlqkdqHVlq2ZJV4XTFSuXfvc-EtZnV65BGpDxy2M2_SsbZ4CONL89c32IKREs6R0jATKK1OobCHAAqQlLeyxYBtcCKimlkOjvoP3HLi-6gdSuvqFf0-zxz1PgH-80nybymZwQDU3JA2DJmCjuNs5JI04GNPvwg";

    /**
     * 公司公钥（公司自己生成）
     */
    private static final String partnerPubKeyStr = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqwfWwZljCk1xVHADl1eB27tLvXqMc1uVPuYOTeVkk0X96ll_gDCevwB3DDploVyNisDvQ0vaCa-6rH12wfWSeuCs6LiRQ2i3Q0Iqr6HjrmWG61MGhACHfjdmu3QD03WLk-YCU5mg7aJ3Jr-4UP5rA_iGm3O6zThpmBjQBPLZJVo9FBSF-D_tiPqYIKxCqRwJScarrT5-1Kvbj0RXkfodom0VviE4h-XcHtHnJbhD1KiEFwrS520EpZhMcFkbcc3IGqPxBnGqq81a6xdSQq8bLlU5bG8Gp3amGa7wtrFo1hCCzKkAIS_8pa8Cr4Pr2pav1nXIl-pHyTRaGeaLn1QOHQIDAQAB";


    /**
     *  加解密demo，以渠道方请求小米金融为例
     */
    @Test
    public void testXiaoMiSign() throws IOException, GeneralSecurityException {
        // ***********加*****密*****流*****程*****公司方************
        // 业务参数
        TreeMap<String, String> paramsMap = initDataParams();
        System.out.println("加密前业务参数：" + paramsMap);
        String dataJson = objectMapper.writeValueAsString(paramsMap);

        // 时间戳
        String timestamp = String.valueOf(System.currentTimeMillis());

        // 生成AES密钥

        String aesKey = AESUtils.generateAESKey();
        String encryptKey = RSAUtils.encryptByPublicKey(aesKey, sncfcPubKeyStr);

        // 公共参数
        TreeMap<String, String> commonParams = initCommonParams(timestamp, encryptKey);

        // 对业务参数进行aes加密
        if (false) {
            dataJson = new String(GzipUtils.compress(dataJson), StandardCharsets.UTF_8);
            commonParams.put("compressed", "true");
        } else {
            commonParams.put("compressed", "false");
        }
        commonParams.put("params", AESUtils.encrypt(dataJson, aesKey));

        // 获取待签名字符串
        String content = commonParams.entrySet().stream().map(entry -> entry.getKey() + "=" + entry.getValue()).collect(Collectors.joining("&"));
        // 签名
        String sign = signByPrivateKey(content, partnerPriKeyStr);

        // 将签名、加密后的业务参数放入公共参数
        commonParams.put("params", AESUtils.encrypt(dataJson, aesKey));
        commonParams.put("sign", sign);

        // HTTP请求报文
        String message = objectMapper.writeValueAsString(commonParams);


        // ***********解*****密*****流*****程******小米方***********

        // 接收到的报文
        log.debug("请求小米mi.loan.apply接口的报文详情：{}", message);

        // 公共参数
        commonParams = objectMapper.readValue(message, TreeMap.class);
        // 解密aesKey 使用小米私钥
        String aesKeyStr = (String) commonParams.get("key");
        aesKey = RSAUtils.decryptByPrivateKey(aesKeyStr, sncfcPriKeyStr);

        String encryptData = (String) commonParams.get("params");
        dataJson = new String(AESUtils.decrypt(encryptData, aesKey));
        // 业务参数
        paramsMap = (TreeMap<String, String>) objectMapper.readValue(dataJson, TreeMap.class);
        System.out.println("解密后业务参数：" + paramsMap);

        // 验签
        sign = (String) commonParams.get("sign");
        // 公共参数去除sign字段
        commonParams.remove("sign");

        // 将解密后的业务参数放入公共参数
        content = commonParams.entrySet().stream().map(entry -> entry.getKey() + "=" + entry.getValue()).collect(Collectors.joining("&"));

        System.out.println("sign content: " + content);

        if (RSAUtils.verifySignByPublicKey(content, sign, partnerPubKeyStr)) {
            System.out.println("验签通过");
        } else {
            System.out.println("验签不通过");
        }

        // 业务处理
        // ……

    }

    /**
     * 初始化业务参数
     * @return
     */
    private static TreeMap<String, String> initDataParams() {
        TreeMap<String, String> dataMap = new TreeMap<>();

        dataMap.put("applyNo", "APPLY2019101279272280");
        dataMap.put("creditType", "0");
        //……
        dataMap.put("openId", "20180424184601719732");

        return dataMap;
    }

    private static TreeMap<String, String> initCommonParams(String timestamp, String encryptKey) {
        TreeMap<String, String> commonMap = new TreeMap<>();
        // 平台方标识
        commonMap.put("appId", "wps");
        // 每次请求的唯⼀标⽰ID
        commonMap.put("requestId", "1877381763824616454");
        // 需要调用的接口名
        commonMap.put("method", "mi.loan.apply");
        // 版本号 注意 demo中给的是1，文档中给的1.0
        commonMap.put("version", "1.0");
        // AES key，使用RSA私钥加密
        commonMap.put("key", encryptKey);
        // 时间戳
        commonMap.put("timestamp", timestamp);

        return commonMap;
    }


    /**
     * RSA私钥签名
     *
     * @param content    待签名数据
     * @param privateKey 私钥
     * @return 签名值
     */
    public static String signByPrivateKey(String content, String privateKey) {
        try {
            PrivateKey priKey = getPrivateKey(privateKey);
            Signature signature = Signature.getInstance(RSAUtils.SIGNATURE_ALGORITHM);
            signature.initSign(priKey);
            signature.update(content.getBytes(RSAUtils.ENCODING));
            byte[] signed = signature.sign();
            return new String(Base64.encodeBase64URLSafe(signed), RSAUtils.ENCODING);
        } catch (Exception e) {
            log.warn("sign error, content: {}, priKey: {}", new Object[] {content, privateKey});
            log.error("sign error", e);
        }
        return null;
    }

    /**
     * 获取私钥
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(key.getBytes(RSAUtils.ENCODING));
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSAUtils.KEY_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }
}
