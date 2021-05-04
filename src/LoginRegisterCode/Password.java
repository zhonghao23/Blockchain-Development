package LoginRegisterCode;

import java.util.Base64;
import java.util.UUID;

public class Password {
    
    private static final String ALGO = "SHA-256";
    
    private static final String FILE_SECRET = "secret.txt";
    private static final String FILE_LOGIN = "login.txt";
   
    public static void create( String username, String passwd, String entityType) throws Exception{
        String uuid = UUID.randomUUID().toString();
        System.out.println( uuid ); 
        String rand = Base64.getEncoder().encodeToString( LoginHasher.getSecureRand() );
        String hash = LoginHasher.hash( Txt.append(rand, passwd), ALGO);
        //write to file
        //FORMAT: UID|SALT|PASSWORDHASH
        IO.write(FILE_SECRET, String.join("|", uuid, rand, hash));
        //FORMAT: USERNAME|UUID|ENTITY
        IO.write(FILE_LOGIN, String.join("|", username, uuid, entityType));
        System.out.println( "rand: " + rand );
        System.out.println( "Password Hash: " + hash );
    }
}

