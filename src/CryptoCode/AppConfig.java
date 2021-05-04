package CryptoCode;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class AppConfig {
    //algorithm
    public static final String ALGORITHM = "RSA";
    
    //location of keypair   //prepare 2 files for each entity
    public static final String AS_PUBLICKEY_FILE = "KeyPair/AgroSupplierPublicKey";
    public static final String AS_PRIVATEKEY_FILE = "KeyPair/AgroSupplierPrivateKey";
    public static final String PC_PUBLICKEY_FILE = "KeyPair/ProcessorPublicKey";
    public static final String PC_PRIVATEKEY_FILE = "KeyPair/ProcessorPrivateKey";
    public static final String RT_PUBLICKEY_FILE = "KeyPair/RetailerPublicKey";
    public static final String RT_PRIVATEKEY_FILE = "KeyPair/RetailerPrivateKey";
    public static final String PUBLICKEY_FILE = "KeyPair/PublicKey";
    public static final String PRIVATEKEY_FILE = "KeyPair/PrivateKey";
    
    public static PublicKey getPublickey(String filename) throws Exception{
        byte[] keyBytes = Files.readAllBytes(Paths.get(filename));
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance(AppConfig.ALGORITHM).generatePublic(spec);
    }
    
    public static PrivateKey getPrivatekey(String filename) throws Exception{
        byte[] keyBytes = Files.readAllBytes(Paths.get(filename));
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance(AppConfig.ALGORITHM).generatePrivate(spec);
    }
}
