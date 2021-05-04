package CryptoCode;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class KeyMaker {
    KeyPairGenerator keygen;
    KeyPair keypair;
    
    //constructor
    public KeyMaker() throws Exception {
        keygen = KeyPairGenerator.getInstance(AppConfig.ALGORITHM);
        //keysize = 1024
        keygen.initialize(1024);
    }
    
    //make keypair
    public static void mkKeyPair(int entity_type){
        try{
            //instantiate KeyMaker object
            KeyMaker keymkr = new KeyMaker();
            keymkr.keypair = keymkr.keygen.generateKeyPair();
            
            //Public key
            PublicKey pubkey = keymkr.keypair.getPublic();
            //Private key
            PrivateKey prikey = keymkr.keypair.getPrivate();
            
            //display them-optional
            //System.out.println("Public Key: "+pubkey.toString());
            //System.out.println("Private Key: "+prikey.toString());
            
            //store them into file
            if (entity_type == 1) {
                KeyMaker.store(AppConfig.AS_PUBLICKEY_FILE, pubkey.getEncoded());
                KeyMaker.store(AppConfig.AS_PRIVATEKEY_FILE, prikey.getEncoded());
            }else if (entity_type == 2){
                KeyMaker.store(AppConfig.PC_PUBLICKEY_FILE, pubkey.getEncoded());
                KeyMaker.store(AppConfig.PC_PRIVATEKEY_FILE, prikey.getEncoded());
            }else if (entity_type == 3){
                KeyMaker.store(AppConfig.RT_PUBLICKEY_FILE, pubkey.getEncoded());
                KeyMaker.store(AppConfig.RT_PRIVATEKEY_FILE, prikey.getEncoded());
            }else {
                KeyMaker.store(AppConfig.PUBLICKEY_FILE, pubkey.getEncoded());
                KeyMaker.store(AppConfig.PRIVATEKEY_FILE, prikey.getEncoded());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    //store the keypair
    public static void store(String path, byte[] key){
        File file = new File(path);
        file.getParentFile().mkdirs();
        try{
            Files.write(Paths.get(path), key, StandardOpenOption.CREATE);
            System.out.println("DONE");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        KeyMaker.mkKeyPair(1);
        KeyMaker.mkKeyPair(2);
        KeyMaker.mkKeyPair(3);
        KeyMaker.mkKeyPair(4);
    }
}
