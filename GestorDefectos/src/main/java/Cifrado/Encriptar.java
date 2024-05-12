/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cifrado;

import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author jctri
 */
public class Encriptar {

    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String KEY = "x+]1F(P2E4,ANu`N{oI<wCHha_Kc9.Zg"; // Clave de 32 bytes para AES-256

    public static String encrypt(String data) throws Exception {
        byte[] iv = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        SecretKeySpec secretKeySpec = new SecretKeySpec(KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] encrypted = cipher.doFinal(data.getBytes());

        // Concatena IV y datos cifrados para el transporte
        byte[] encryptedIVAndText = new byte[iv.length + encrypted.length];
        System.arraycopy(iv, 0, encryptedIVAndText, 0, iv.length);
        System.arraycopy(encrypted, 0, encryptedIVAndText, iv.length, encrypted.length);

        String encoded = Base64.getEncoder().encodeToString(encryptedIVAndText);
        System.out.println("String encriptado: " + encoded); // Depuración
        return encoded;
    }
}
