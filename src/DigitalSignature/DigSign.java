package DigitalSignature;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

public class DigSign {
    
    private Signature signature;
    //private KeyPairGenerator keygen;
    //private KeyPair keyPair;
    
    //constructor
    public DigSign() {
        try{
            //object instance
            signature = Signature.getInstance("SHA256withRSA");
            //keygen = KeyPairGenerator.getInstance("RSA");
            //keyPair = keygen.generateKeyPair();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    //sign
    public String sign(String data, PrivateKey private_key) throws Exception{
        signature.initSign(private_key);
        signature.update(data.getBytes());
        byte[] dsBytes = signature.sign();
        return Base64.getEncoder().encodeToString(dsBytes);
    }
    
    //verify
    public boolean verify(String data, String digitalSignature, PublicKey public_key) throws Exception {
        signature.initVerify(public_key);
        signature.update(data.getBytes());
        return signature.verify(Base64.getDecoder().decode(digitalSignature));
    }
}
