package com.cloud.way.demotest.sm2.net;//package com.example.demo.sm2.net;
//
////import com.joinway.framework.bean.utils.xmlHelper;
//
//import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
//import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
//import org.bouncycastle.crypto.params.ECPublicKeyParameters;
//import org.bouncycastle.math.ec.ECPoint;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.math.BigInteger;
//
//public class SM2Utils {
//
//
//    private final static char[] mChars = "0123456789ABCDEF".toCharArray();
//
//    //生成随机秘钥对
//    public static void generateKeyPair() {
//        SM2 sm2 = SM2.Instance();
//        AsymmetricCipherKeyPair key = sm2.ecc_key_pair_generator.generateKeyPair();
//        ECPrivateKeyParameters ecpriv = (ECPrivateKeyParameters) key.getPrivate();
//        ECPublicKeyParameters ecpub = (ECPublicKeyParameters) key.getPublic();
//        BigInteger privateKey = ecpriv.getD();
//        ECPoint publicKey = ecpub.getQ();
//
//        System.out.println("公钥: " + Util.byteToHex(publicKey.getEncoded()));
//        System.out.println("私钥: " + Util.byteToHex(privateKey.toByteArray()));
//    }
//
//    //数据加密
//    public static String encrypt(byte[] publicKey, byte[] data) throws IOException {
//        if (publicKey == null || publicKey.length == 0) {
//            return null;
//        }
//
//        if (data == null || data.length == 0) {
//            return null;
//        }
//
//        byte[] source = new byte[data.length];
//        System.arraycopy(data, 0, source, 0, data.length);
//
//        Cipher cipher = new Cipher();
//        SM2 sm2 = SM2.Instance();
//
//        ECPoint userKey = sm2.ecc_curve.decodePoint(publicKey);
//
//        ECPoint c1 = cipher.Init_enc(sm2, userKey);
//        cipher.Encrypt(source);
//        byte[] c3 = new byte[32];
//        cipher.Dofinal(c3);
//
////		System.out.println("C1 " + Util.byteToHex(c1.getEncoded()));
////		System.out.println("C2 " + Util.byteToHex(source));
////		System.out.println("C3 " + Util.byteToHex(c3));
//        //C1 C2 C3拼装成加密字串
//        return Util.byteToHex(c1.getEncoded()) + Util.byteToHex(source) + Util.byteToHex(c3);
//
//    }
//
//    //数据解密
//    public static byte[] decrypt(byte[] privateKey, byte[] encryptedData) throws IOException {
//        if (privateKey == null || privateKey.length == 0) {
//            return null;
//        }
//
//        if (encryptedData == null || encryptedData.length == 0) {
//            return null;
//        }
//        //加密字节数组转换为十六进制的字符串 长度变为encryptedData.length * 2
//        String data = Util.byteToHex(encryptedData);
//        /***分解加密字串
//         * （C1 = C1标志位2位 + C1实体部分128位 = 130）
//         * （C3 = C3实体部分64位  = 64）
//         * （C2 = encryptedData.length * 2 - C1长度  - C2长度）
//         */
//        byte[] c1Bytes = Util.hexToByte(data.substring(0, 130));
//        int c2Len = encryptedData.length - 97;
//        byte[] c2 = Util.hexToByte(data.substring(130, 130 + 2 * c2Len));
//        byte[] c3 = Util.hexToByte(data.substring(130 + 2 * c2Len, 194 + 2 * c2Len));
//
//        SM2 sm2 = SM2.Instance();
//        BigInteger userD = new BigInteger(1, privateKey);
//
//        //通过C1实体字节来生成ECPoint
//        ECPoint c1 = sm2.ecc_curve.decodePoint(c1Bytes);
//        Cipher cipher = new Cipher();
//        cipher.Init_dec(userD, c1);
//        cipher.Decrypt(c2);
//        cipher.Dofinal(c3);
//
//        //返回解密结果
//        return c2;
//    }
//
//    public static String generateKey(String path) throws Exception {
//        //生成密钥对
////        generateKeyPair();
//
////        String plainText = xmlHelper.getMD5(path + "/Doc_0/Signs/Sign_0/Signature.xml");
//        String plainText = "hello world";
//        byte[] sourceData = plainText.getBytes();
//        // 国密规范正式私钥
//        String prik = "1027b91c2be8dd0354dae2b0c989749866882e56670c3971b76c2ad53351359a";
//        // 国密规范正式公钥
//        String pubk = "0415d291b399072e60da0ff66ccc79021d3b7d750daa37586a7166afd7d4732f27317f241b6ac245b38b22f642febd4c5be217a011a8549c1d9ec45c42b01e8c7c";
//
//        //加密
//        String cipherText = SM2Utils.encrypt(Util.hexToByte(pubk), sourceData);
//
//        //解密
//        plainText = new String(SM2Utils.decrypt(Util.hexToByte(prik), Util.hexToByte(cipherText)));
//
//        return cipherText;
//
//    }
//
//    public static String inputStream2String(InputStream is) throws IOException {
//        BufferedReader in = new BufferedReader(new InputStreamReader(is));
//        StringBuffer buffer = new StringBuffer();
//        String line = "";
//        while ((line = in.readLine()) != null) {
//            buffer.append(line);
//        }
//        return buffer.toString();
//    }
//
//
//    public static String str2HexStr(String str) {
//        StringBuilder sb = new StringBuilder();
//        byte[] bs = str.getBytes();
//
//        for (int i = 0; i < bs.length; i++) {
//            sb.append(mChars[(bs[i] & 0xFF) >> 4]);
//            sb.append(mChars[bs[i] & 0x0F]);
//        }
//        return sb.toString().trim();
//    }
//
//}