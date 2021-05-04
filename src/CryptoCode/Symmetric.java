package CryptoCode;

import java.security.*;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Symmetric {

    //secret char
    private static final String SECRET = "thisisasecretkeyyyy";
    
    //algorithm
    private static final String ALGORITHM = "AES";
    private Key key;

    public Key getKey() {
        return key;
    }
    
    private static Key genkey(){
        return new SecretKeySpec(Arrays.copyOf(SECRET.getBytes(), 16), ALGORITHM);
    }
    
    
    //cipher supports encryption and decryption
    private Cipher cipher;
    public Symmetric() {
        try{
            cipher = Cipher.getInstance(ALGORITHM);
            key = genkey();
        } catch ( NoSuchAlgorithmException | NoSuchPaddingException ex ){
            ex.printStackTrace();
        }
    }
    
    //encrypt: input= Original data; output= CipherText
    public String encrypt(String data) throws Exception{ //can return either String/byte[]
        cipher.init(Cipher.ENCRYPT_MODE, key);
        //cipher introduces fundamental methods: update(), doFinal()
        byte[] cipherText = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(cipherText);
        
    }
    
    //decrypt: input= CipherText; output= Original data
    public String decrypt(String cipherText) throws Exception{
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] dataBytes = cipher.doFinal( Base64.getDecoder().decode(cipherText));
        return new String(dataBytes);
    } 
}