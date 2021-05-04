package BlockCode;

import CryptoCode.AppConfig;
import CryptoCode.Asymmetric;
import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import org.javatuples.Sextet;

public class Block implements Serializable{
    
    //data properties
    private Integer index;
    private String hash, previousHash;
    //private Sextet<String,String,String,String,String,String> data;
    //private String val0,val1,val2,val3,val4,val5;
    private long timestamp;
    ArrayList<String> data = new ArrayList<String>();
    //ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
    //constructor (objects to be stored into a block)
    public Block(Sextet<String,String,String,String,String,String> data1, String previousHash, Asymmetric crypto) throws Exception {
        //this.data = data;
        //data.add(new ArrayList<String>());
        //get encrypted data
        data.add(crypto.encrypt(data1.getValue0(), AppConfig.getPublickey(AppConfig.PUBLICKEY_FILE)));
        data.add(crypto.encrypt(data1.getValue1(), AppConfig.getPublickey(AppConfig.PUBLICKEY_FILE)));
        data.add(crypto.encrypt(data1.getValue2(), AppConfig.getPublickey(AppConfig.PUBLICKEY_FILE)));
        data.add(crypto.encrypt(data1.getValue3(), AppConfig.getPublickey(AppConfig.PUBLICKEY_FILE)));
        data.add(crypto.encrypt(data1.getValue4(), AppConfig.getPublickey(AppConfig.PUBLICKEY_FILE)));
        data.add(crypto.encrypt(data1.getValue5(), AppConfig.getPublickey(AppConfig.PUBLICKEY_FILE)));
//        this.val0 = crypto.encrypt(data1.getValue0(), AppConfig.getPublickey(AppConfig.PUBLICKEY_FILE));
//        this.val1 = crypto.encrypt(data1.getValue1(), AppConfig.getPublickey(AppConfig.PUBLICKEY_FILE));
//        this.val2 = crypto.encrypt(data1.getValue2(), AppConfig.getPublickey(AppConfig.PUBLICKEY_FILE));
//        this.val3 = crypto.encrypt(data1.getValue3(), AppConfig.getPublickey(AppConfig.PUBLICKEY_FILE));
//        this.val4 = crypto.encrypt(data1.getValue4(), AppConfig.getPublickey(AppConfig.PUBLICKEY_FILE));
//        this.val5 = crypto.encrypt(data1.getValue5(), AppConfig.getPublickey(AppConfig.PUBLICKEY_FILE));
        this.previousHash = previousHash;
        this.timestamp = new Timestamp(System.currentTimeMillis()).getTime();
        //convert block object to byte[] for hashing
        byte[] blockBytes = Block.getBytes1(this);
        if (blockBytes != null) {
            //put all data into the baos
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.write(data.get(0).getBytes());
            baos.write(data.get(1).getBytes());
            baos.write(data.get(2).getBytes());
            baos.write(data.get(3).getBytes());
            baos.write(data.get(4).getBytes());
            baos.write(data.get(5).getBytes());
//            baos.write(val0.getBytes());
//            baos.write(val1.getBytes());
//            baos.write(val2.getBytes());
//            baos.write(val3.getBytes());
//            baos.write(val4.getBytes());
//            baos.write(val5.getBytes());
            baos.write(previousHash.getBytes());
            baos.write(Long.toString(timestamp).getBytes());
            baos.write(blockBytes);
            //send all the data in baos to Hasher.hash method
            this.hash = Hasher.hash(baos.toByteArray(), "SHA-256");
        }else{
            throw new Exception("Unable to generate current hash!");
        }
    }
    
    //convert the Block object into byte[]
    private static byte[] getBytes1(Block blockData){
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(baos);){
            //write block into stream
            out.writeObject(blockData);
            //get byte[] from baos
            return baos.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

//    @Override
//    public String toString() {
//        return "Block{" + "hash=" + hash + ", previous_hash=" + previous_hash + ", val0=" + val0 + ", val1=" + val1 + ", val2=" + val2 + ", val3=" + val3 + ", val4=" + val4 + ", val5=" + val5 + '}' + ", timestamp=" + timestamp;
//    }

    @Override
    public String toString() {
        return "Block{" + "hash=" + hash + ", previous_hash=" + previousHash + ", data=" + data + ", timestamp=" + timestamp + '}';
    }
    
}
