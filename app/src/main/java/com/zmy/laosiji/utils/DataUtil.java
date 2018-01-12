package com.zmy.laosiji.utils;

/*
 * Copyright (C) 2012-10-24 JIN BIN BIN (AstroBoy)
 */

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * 数据处理的工具类
 *
 * 主要实现对数据进行加密、解密、排序等
 * 进制转换
 * 随机字符串
 * 随机数字
 * 数组转字符串
 * MD5加密
 *
 */
public class DataUtil {
    /**
     * 类标记
     */
    private static final String TAG = "DataUtil";
    /**
     * 所有大写拉丁字母字符
     */
    public static final String UPPER_LETTER_CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /**
     * 所有小写拉丁字母字符
     */
    public static final String LOWER_LETTER_CHAR = "abcdefghijklmnopqrstuvwxyz";
    /**
     * 所有数字字符
     */
    public static final String ARAB_NUMBER_CHAR = "0123456789";
    /**
     * 所有大写中文数字字符
     */
    public static final String UPPER_CHINA_NUMBER_CHAR = "零壹贰叁肆伍陆柒捌玖拾佰仟萬億";
    /**
     * 所有小写中文数字字符
     */
    public static final String LOWER_CHINA_NUMBER_CHAR = "零一二三四五六七八九十百千万亿";


    /**
     * 随机生成唯一UUID字符串
     *
     * @return 返回随机产生的唯一字符
     */
    public static String getOnlyStr() {
        Log.v(TAG, "getOnlyStr");

        return UUID.randomUUID().toString();
    }

    /**
     * 随机生成字符串
     *
     * @param length
     *            指定生产字符串长度
     * @param chars
     *            生成的字符串中的每个字符都是随机从字符粗chars取出字符
     * @return 返回随机生成的字符 (不能保证不重复)
     */
    public static String generateStr(int length, String chars) {
        Log.v(TAG, "generateStr");
        String result = null;
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        int size = chars.length();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(size)));
        }
        result = sb.toString();
        return result;
    }

    /**
     * 将list倒序输出
     * @param rawList
     * @param <T>
     * @return
     */
    public static <T> List<T> reverseList(List<T> rawList) {
        List<T> resultList = new ArrayList<T>();
        ListIterator<T> li = rawList.listIterator();
        // 将游标定位到列表结尾
        for (li = rawList.listIterator(); li.hasNext();) {
            li.next();
        }
        // 逆序输出列表中的元素
        for (; li.hasPrevious();) {
            resultList.add(li.previous());
        }
        return resultList;
    }

    /**
     * 反转数组
     *
     * @param arrays
     * @param <T>
     * @return
     */
    public static <T> T[] reverse(T[] arrays) {
        if (arrays == null) {
            return null;
        }
        int length = arrays.length;
        for (int i = 0; i < length / 2; i++) {
            T t = arrays[i];
            arrays[i] = arrays[length - i - 1];
            arrays[length - i - 1] = t;
        }
        return arrays;
    }

    /**
     * 十六进制数值字符串转byte类数据
     *
     * @param strhex
     *            数据源
     * @return 返回转换后的byte数据
     */
    public static byte[] hex2byte(String strhex) {
        Log.v(TAG, "hex2byte");
        if (strhex == null) {
            return null;
        }
        int l = strhex.length();
        if (l % 2 == 1) {
            return null;
        }
        byte[] b = new byte[l / 2];
        for (int i = 0; i != l / 2; i++) {
            b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2),
                    16);
        }
        return b;
    }

    /**
     * byte数据转十六进制数值字符串
     *
     * @param b
     *            byte数据源
     * @return 返回转换字符串数据
     */
    public static String byte2hex(byte[] b) {
        Log.v(TAG, "byte2hex");
        StringBuffer hs = new StringBuffer(b.length);
        String stmp = "";
        for (byte aB : b) {
            stmp = Integer.toHexString(aB & 0xFF);//转换为16进制数值字符串
            if (stmp.length() == 1) {
                hs = hs.append("0").append(stmp);
            } else {
                hs = hs.append(stmp);
            }
        }
        return String.valueOf(hs);
    }

    /**
     * MD5加密(一般认为无法解密，不过现在已经被证明可以解密)
     *
     * 信息-摘要
     *
     * @param data
     *            数据源
     * @return 返回加密的字符串数据
     */
    public static String encryptData2MD5(String data) {
        Log.v(TAG, "encryptionData2MD5");
        String result = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(data.getBytes("UTF-8"));
            result = byte2hex(md5.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * SHA加密 (不可以解密)
     *
     * @param data
     *            数据源
     * @return 返回加密的字符串数据
     */
    public static String encyptData2SHA(String data) {
        Log.v(TAG, "encyptData2SHA");
        String result = null;
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA");
            sha.update(data.getBytes("UTF-8"));
            result = byte2hex(sha.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 简单可逆加密解密
     *
     * @param data
     *            数据源
     * @return 解密或加密后的数据
     */
    public static String encryptDataReversible(String data) {
        Log.v(TAG, "encryptionDataReversible");
        char[] chars = data.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] ^ '1');
        }
        return new String(chars);
    }

    /**
     * 获取用于加密的公钥和私钥的钥匙包
     *
     * @param type
     *            加密方式
     * @param size
     *            生成公钥和私钥的字符大小
     * @return 换回生成的钥匙包
     */
    public static KeyPair getKeyCase(String type, int size) {
        Log.v(TAG, "getKeyCase");
        KeyPair keyPair = null;
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(type);
            keyPairGen.initialize(size);
            keyPair = keyPairGen.generateKeyPair();
        } catch (Exception e) {
            e.printStackTrace();
            Log.v(TAG, e.getMessage());
        }
        return keyPair;
    }

    /**
     * 获取消息验证码
     *
     * @param data
     *            数据源
     * @param key
     *            获取数据源钥匙
     * @return 返回加密的消息验证码
     */
    public static String getEncryptHMACMD5(String data, String key) {
        Log.v(TAG, "getEncryptHMACMD5");
        String result = null;
        try {
            byte[] keyByte = key.getBytes("UTF-8");
            SecretKeySpec sks = new SecretKeySpec(keyByte, "HMACMD5");
            Mac mac = Mac.getInstance("HMACMD5");
            mac.init(sks);
            mac.update(data.getBytes());
            byte[] certifyCode = mac.doFinal();
            if (certifyCode != null) {
                result = byte2hex(certifyCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 加密
     *
     * @param src
     *            数据源
     * @param key
     *            密钥
     * @param type
     *            加密类型 RAS DSA
     * @return 返回加密后的数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] src, byte[] key, String type) {
        Log.v(TAG, "encrypt");
        byte[] reuslt = null;
        try {
            SecretKeySpec sKeySpec = new SecretKeySpec(key, type);
            Cipher cipher = Cipher.getInstance(type);
            cipher.init(Cipher.ENCRYPT_MODE, sKeySpec);
            reuslt = cipher.doFinal(src);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
        }
        return reuslt;
    }

    /**
     * 解密
     *
     * @param src
     *            数据源
     * @param key
     *            密匙
     * @param type
     *            加密类型 RSA DSA
     * @return 返回解密后的原始数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] src, byte[] key, String type) {
        Log.v(TAG, "decrypt");
        byte[] reuslt = null;
        try {
            SecretKeySpec sKeySpec = new SecretKeySpec(key, type);
            Cipher cipher = Cipher.getInstance(type);
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec);
            reuslt = cipher.doFinal(src);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
        }
        return reuslt;
    }

    /**
     * 使用公钥加密
     *
     * @param publicKey
     *            加密公钥
     * @param srcBytes
     *            数据源
     * @param type
     *            加密类型主要有 RSA
     * @return 返回加密后的数据
     */
    public static byte[] encrypt(PublicKey publicKey, byte[] srcBytes,
                                 String type) {
        Log.v(TAG, "encrypt");
        byte[] result = null;
        if (publicKey != null) {
            try {
                Cipher cipher = Cipher.getInstance(type);
                cipher.init(Cipher.ENCRYPT_MODE, publicKey);
                result = cipher.doFinal(srcBytes);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, e.getMessage());
            }
        }
        return result;
    }

    /**
     * 使用私钥加密
     *
     * @param privateKey
     *            加密私钥
     * @param srcBytes
     *            数据源
     * @param type
     *            加密类型 RSA DSA
     * @return 返回加密后的数据
     */
    public static byte[] encrypt(PrivateKey privateKey, byte[] srcBytes,
                                 String type) {
        Log.v(TAG, "encrypt");
        byte[] result = null;
        if (privateKey != null) {
            try {
                Cipher cipher = Cipher.getInstance(type);
                cipher.init(Cipher.ENCRYPT_MODE, privateKey);
                result = cipher.doFinal(srcBytes);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, e.getMessage());
            }
        }
        return result;
    }

    /**
     * 使用私钥解密
     *
     * @param privateKey
     *            解密私钥
     * @param serBytes
     *            数据源
     * @param type
     *            解密类型 RSA
     * @return 解密后的数据
     */
    public static byte[] decrypt(PrivateKey privateKey, byte[] serBytes,
                                 String type) {
        Log.v(TAG, "decryot");
        byte[] result = null;
        if (privateKey != null) {
            try {
                Cipher cipher = Cipher.getInstance(type);
                cipher.init(Cipher.DECRYPT_MODE, privateKey);
                result = cipher.doFinal(serBytes);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, e.getMessage());
            }
        }
        return result;
    }

    /**
     * 使用公钥解密
     *
     *            解密私钥
     * @param serBytes
     *            数据源
     * @param type
     *            解密类型 RSA DSA
     * @return 解密后的数据
     */
    public static byte[] decrypt(PublicKey publicKey, byte[] serBytes,
                                 String type) {
        Log.v(TAG, "decryot");
        byte[] result = null;
        if (publicKey != null) {
            try {
                Cipher cipher = Cipher.getInstance(type);
                cipher.init(Cipher.DECRYPT_MODE, publicKey);
                result = cipher.doFinal(serBytes);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, e.getMessage());
            }

        }
        return result;
    }

    /**
     * AES加密
     *
     * @param data
     *            数据源
     * @param key
     *            加密钥匙
     * @return 加密后的数据
     */
    public static String encryptAESStr(String data, String key) {
        Log.v(TAG, "encryptAESStr");
        String result = null;
        if (key != null && key.length() == 16) {
            try {
                byte[] resultbytes = encrypt(data.getBytes(),
                        key.getBytes("UTF-8"), "AES");
                if (resultbytes != null) {
                    result = byte2hex(resultbytes);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                Log.e(TAG, e.getMessage());
            }
        }
        return result;
    }

    /**
     * AES 解密
     *
     * @param data
     *            数据源
     * @param key
     *            解密钥匙
     * @return 解密后的数据
     */
    public static String decryptAESStr(String data, String key) {
        Log.v(TAG, "decryptAESStr");
        String result = null;
        if (key != null && key.length() == 16) {
            try {
                byte[] databytes = hex2byte(data);
                byte[] resultbytes = decrypt(databytes, key.getBytes("UTF-8"),
                        "AES");
                if (resultbytes != null) {
                    result = new String(resultbytes);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                Log.e(TAG, e.getMessage());
            }
        }
        return result;
    }

    /**
     * 3DES加密
     *
     * @param data
     *            数据源
     * @param key
     *            加密钥匙
     * @return 返回加密后的数据
     */
    public static String decrypt3DESStr(String data, String key) {
        Log.v(TAG, "decrypt3DESStr");
        String result = null;
        if (key != null && key.length() == 16) {
            try {
                byte[] databytes = hex2byte(data);
                byte[] resultbytes = decrypt(databytes, key.getBytes("UTF-8"),
                        "DESede");
                if (resultbytes != null) {
                    result = new String(resultbytes);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                Log.e(TAG, e.getMessage());
            }
        }
        return result;
    }

    /**
     * 3DES解密
     *
     * @param data
     *            数据源
     * @param key
     *            解密钥匙
     * @return 返回解密后的原始数据
     */
    public static String encrypt3DESStr(String data, String key) {
        Log.v(TAG, "encrypt3DESStr");
        String result = null;
        if (key != null && key.length() == 16) {
            try {
                byte[] resultbytes = encrypt(data.getBytes(),
                        key.getBytes("UTF-8"), "DESede");
                if (resultbytes != null) {
                    result = byte2hex(resultbytes);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                Log.e(TAG, e.getMessage());
            }
        }
        return result;
    }

    /**
     * 3DES解密
     *
     * @param data
     *            数据源
     * @param key
     *            解密钥匙
     * @return 返回解密后的原始数据
     */
    public static String decryptDESStr(String data, String key) {
        Log.v(TAG, "decryptDESStr");
        String result = null;
        if (key != null && key.length() == 8) {
            try {
                byte[] databytes = hex2byte(data);
                byte[] resultbytes = decrypt(databytes, key.getBytes("UTF-8"),
                        "DES");
                if (resultbytes != null) {
                    result = new String(resultbytes);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                Log.e(TAG, e.getMessage());
            }
        }
        return result;
    }

    /**
     * DES加密
     *
     * @param data
     *            数据源
     * @param key
     *            加密钥匙
     * @return 返回加密后的数据
     */
    public static String encryptDESStr(String data, String key) {
        Log.v(TAG, "encryptDESStr");
        String result = null;
        if (key != null && key.length() == 8) {
            try {
                byte[] resultbytes = encrypt(data.getBytes(),
                        key.getBytes("UTF-8"), "DES");
                if (resultbytes != null) {
                    result = byte2hex(resultbytes);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                Log.e(TAG, e.getMessage());
            }
        }
        return result;
    }

    /**
     * RSA私密加密
     *
     * @param data
     *            数据源
     * @param type
     *            加密钥匙
     * @return 返回加密的数据
     */
    public static String encryptPrivateKeyStr(String data,
                                              PrivateKey privateKey, String type) {
        Log.v(TAG, "encryptPrivateKeyStr");
        String result = null;
        try {
            byte[] resultbytes = encrypt(privateKey, data.getBytes(), type);
            if (resultbytes != null) {
                result = byte2hex(resultbytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
        }
        return result;
    }

    /**
     * RSA公钥加密
     *
     * @param data
     *            数据源
     * @param type
     *            加密钥匙
     * @return 返回加密后的数据
     */
    public static String encryptPublicKeyStr(String data, PublicKey publicKey,
                                             String type) {
        Log.v(TAG, "encryptPublicKeyStr");
        String result = null;
        try {
            byte[] resultbytes = encrypt(publicKey, data.getBytes(), type);
            if (resultbytes != null) {
                result = byte2hex(resultbytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
        }
        return result;
    }

    /**
     * RSA私密解密
     *
     * @param data
     *            数据源
     * @param type
     *            解密钥匙
     * @return 返回解密后的数据
     */
    public static String decryptPrivateKeyStr(String data,
                                              PrivateKey privateKey, String type) {
        Log.v(TAG, "decryptPrivateKeyStr");
        String result = null;
        try {
            byte[] databytes = hex2byte(data);
            byte[] resultbytes = decrypt(privateKey, databytes, type);
            if (resultbytes != null) {
                result = new String(resultbytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
        }
        return result;
    }

    /**
     * RSA公钥解密
     *
     * @param data
     *            数据源
     * @param type
     *            解密钥匙
     * @return 返回解密后的数据
     */
    public static String decryptPublicKeyStr(String data, PublicKey publicKey,
                                             String type) {
        Log.v(TAG, "decryptPublicKeyStr");
        String result = null;
        try {
            byte[] databytes = hex2byte(data);
            byte[] resultbytes = decrypt(publicKey, databytes, type);
            if (resultbytes != null) {
                result = new String(resultbytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
        }
        return result;
    }

    /**
     * DSA私钥数据签名
     *
     * @param data
     *            数据源
     * @param privateKey
     *            签名私钥
     * @return 返回已经签名了的数据
     */
    public static String signetPrivateKeyDSAStr(String data,
                                                PrivateKey privateKey) {
        String result = null;
        try {
            java.security.Signature signet = java.security.Signature
                    .getInstance("DSA");
            signet.initSign(privateKey);
            signet.update(data.getBytes());
            byte[] resultbytes = signet.sign();
            if (resultbytes != null) {
                result = byte2hex(resultbytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * DSA公钥验证
     *
     * @param data
     *            原始数据源
     * @param publicKey
     *            验证签名的公钥
     * @param signed
     *            签名的数据源
     * @return 返回验证是否通过
     */
    public static boolean verificationPublicKeyDSA(String data,
                                                   PublicKey publicKey, String signed) {
        boolean result = false;
        try {
            java.security.Signature signetcheck = java.security.Signature
                    .getInstance("DSA");
            signetcheck.initVerify(publicKey);
            signetcheck.update(data.getBytes());
            if (signetcheck.verify(hex2byte(signed))) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
