package CryptoCode;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

public class Asymmetric {
    private Cipher cipher;
    
    //constructor
    public Asymmetric() {
        try{
            cipher = Cipher.getInstance(AppConfig.ALGORITHM);
        }catch (NoSuchAlgorithmException ex){
            ex.printStackTrace();
        }catch (NoSuchPaddingException ex){
            ex.printStackTrace();    
        }
    }
    
    //encrypt
    public String encrypt(String data, PublicKey key) throws Exception{
        String cipherText = "";
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherBytes = cipher.doFinal(data.getBytes());
        cipherText = Base64.getEncoder().encodeToString(cipherBytes);
        return cipherText;
    }
    
    
    //decrypt
    public String decrypt(String cipherText, PrivateKey key) throws Exception{
        String origin = "";
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] cipherBytes = Base64.getDecoder().decode(cipherText);
        origin = new String(cipher.doFinal(cipherBytes));
        return origin;
    }
}
