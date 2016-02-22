package org.jenkinsci.plugins.cockpitnotification;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author n.prakasha
 */
public class TripleDES {

    private static String key;
    private static String initializationVector;
    
    public TripleDES(String encryptionkey,String vectorkey)
    {
        this.key = encryptionkey;
        this.initializationVector = vectorkey;
    }
    
    public static String encryptText(String plainText) throws Exception {
        //---- Use specified 3DES key and IV from other source --------------
        byte[] plaintext = plainText.getBytes();
        byte[] tdesKeyData = key.getBytes();
        // byte[] myIV = initializationVector.getBytes();
        Cipher c3des = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        SecretKeySpec myKey = new SecretKeySpec(tdesKeyData, "DESede");
        IvParameterSpec ivspec = new IvParameterSpec(initializationVector.getBytes());
        c3des.init(Cipher.ENCRYPT_MODE, myKey, ivspec);
        byte[] cipherText = c3des.doFinal(plaintext);
        return new sun.misc.BASE64Encoder().encode(cipherText);
    }

    public static String decryptText(String cipherText) throws Exception {
        byte[] encData = new sun.misc.BASE64Decoder().decodeBuffer(cipherText);
        Cipher decipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        byte[] tdesKeyData = key.getBytes();
        SecretKeySpec myKey = new SecretKeySpec(tdesKeyData, "DESede");
        IvParameterSpec ivspec = new IvParameterSpec(initializationVector.getBytes());
        decipher.init(Cipher.DECRYPT_MODE, myKey, ivspec);
        byte[] plainText = decipher.doFinal(encData);
        return new String(plainText);
    }
}
