package BlockCode;

import java.security.MessageDigest;
import java.util.Base64;

public class Hasher {
    //generate hashcode in SHA-256 for the given input in byte[] format
    public static String hash( byte[] blockBytes, String algo) throws Exception {
        //message digest instance for SHA-256
        MessageDigest md = MessageDigest.getInstance(algo);
        //add bytes input into md
        md.update(blockBytes);
        //digest = create hashcode
        byte[] generatedHash = md.digest();
        //create a string builder to convert hashcode into hexadecimal format
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<generatedHash.length; i++){
            sb.append(Integer.toHexString(0xFF & generatedHash[i]));
        }
        return sb.toString();
    }
}
