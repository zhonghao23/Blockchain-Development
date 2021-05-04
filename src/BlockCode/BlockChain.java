package BlockCode;

import java.util.*;
import java.io.*;
import com.google.gson.GsonBuilder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class BlockChain {
    //master/datafile
    private static final String BCHAIN_FILE = "SupplyChain-masterChain.dat";
    
    //data structure - LinkedList
    private static LinkedList<Block> db = new LinkedList<>();
    
    //add new block into linked list
    public static void nextBlock(Block newBlock) {
        BlockChain.db.add(newBlock);
        persist();
        distribute(newBlock);
    }
    
    //update the blockchain in the master chain file
    public static void persist(){
        try(FileOutputStream fos = new FileOutputStream(BCHAIN_FILE);
                ObjectOutputStream out = new ObjectOutputStream(fos)){
            out.writeObject(db);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    //get the existing blockchain
    public static LinkedList<Block> getChain() {
        try(FileInputStream fis = new FileInputStream(BCHAIN_FILE);
                ObjectInputStream in = new ObjectInputStream(fis)){
            return (LinkedList<Block>)in.readObject();
        }catch(IOException e) {
            //e.printStackTrace();
            return null;
        }catch (ClassNotFoundException ex) {
            //ex.printStackTrace();
            return null;
        }
    }
    
    //display and store the blockchain into txt file for easy to read
    public static void distribute(Object block) {
        try{
            PrintWriter Add = new PrintWriter(new FileWriter("SupplyChain-Ledger.txt", true));
            String chain = new GsonBuilder().setPrettyPrinting().create().toJson(block);
            //System.out.println(chain);
            
            Add.println(chain);
            Add.flush();
            Add.close();
//            Files.write(Paths.get("SupplyChain-Ledger.txt"), 
//                    chain.getBytes(),
//                    StandardOpenOption.CREATE
//                    );
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
