package com.clkj.common.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author Created by jojo on 2019/12/2
 */
public class RSAUtil {
    private static Charset charset = StandardCharsets.UTF_8;

    public static String encrypt(String publicKey, String content) {
        RSA rsa = new RSA(null, publicKey);
        return Base64.encode(rsa.encrypt(content, KeyType.PublicKey));
    }

    public static String decrypt(String privateKey, String content) {
        RSA rsa = new RSA(privateKey, null);
        return rsa.decryptStr(content, KeyType.PrivateKey);
    }
}
