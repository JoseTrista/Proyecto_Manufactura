/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cifrado;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author jctri
 */
public class DesencriptarERP {
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String KEY = "x+]1F(P2E4,ANu`N{oI<wCHha_Kc9.Zg"; // La misma clave

    public static String decrypt(String encryptedData) throws Exception {
        byte[] decodedEncryptedData = Base64.getDecoder().decode(encryptedData);

        // Extraer IV
        byte[] iv = new byte[16];
        System.arraycopy(decodedEncryptedData, 0, iv, 0, iv.length);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Extraer datos cifrados
        int encryptedSize = decodedEncryptedData.length - iv.length;
        byte[] encryptedBytes = new byte[encryptedSize];
        System.arraycopy(decodedEncryptedData, iv.length, encryptedBytes, 0, encryptedSize);

        SecretKeySpec secretKeySpec = new SecretKeySpec(KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] decrypted = cipher.doFinal(encryptedBytes);

        return new String(decrypted);
    }
}
