package UsersCode;

import BlockCode.Block;
import BlockCode.BlockChain;
import CryptoCode.AppConfig;
import CryptoCode.Asymmetric;
import CryptoCode.Symmetric;
import DigitalSignature.DigSign;
import LoginRegisterCode.IO;
import LoginRegisterCode.ViewLogin;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import org.javatuples.Sextet;

public class ViewProcessor extends javax.swing.JFrame {
    static String username;
    static ArrayList<String> allUsername;
    static ArrayList<String> allEntity;
    /**
     * Creates new form ViewProcessor
     */
    String data;
    FileOutputStream writer;
    String[][] listOfContracts2 = new String[20][7];
    ArrayList<String> encrypted_data = new ArrayList<String>();
    //<List<List<String>> listOfContracts = new ArrayList<List<String>>()
    int listCount = 0;
    int printCount = 0;
    public ViewProcessor(String username, ArrayList<String> allUsername, ArrayList<String> allEntity){
        this.username = username;
        this.allUsername = allUsername;
        this.allEntity = allEntity;
        initComponents();
        List<String> allContract = IO.read("AStoPCcontract.txt");
        Iterator<String> iterator = allContract.iterator(); 
        DigSign ds = new DigSign();
        try {
            PublicKey public_key = AppConfig.getPublickey(AppConfig.AS_PUBLICKEY_FILE);
            Symmetric sym_crypto = new Symmetric();
            while(iterator.hasNext()) {
                //FORMAT: username|receiver|product_type|supply_date|quantity|price|status|digitalsignature
                data = (String)iterator.next(); //symmetric encryped & digitally signed
                String decrypted_data = sym_crypto.decrypt(data);   //decrypted but digitally signed
                String tempData = data;
                String[] temp = decrypted_data.split("\\|");
                if (temp.length > 5){
                    String data_concat = String.join("|", temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6]);
                    try {
                        boolean isValid = ds.verify(data_concat, temp[7], public_key);
                        if(isValid){
                            if(temp[1].equals(username) && listCount < 20){
                                encrypted_data.add(tempData);
                                for (int i=0;i<7;i++){
                                    listOfContracts2[listCount][i] = temp[i];
                                }
                                listCount++;
                            }
                        }      
                    } catch (Exception e) {
                        break;
                    }
                }
            }
            if (listCount != 0){
                jLabel200.setText(listOfContracts2[printCount][0]);
                jLabel201.setText(listOfContracts2[printCount][2]);
                jLabel202.setText(listOfContracts2[printCount][3]);
                jLabel203.setText(listOfContracts2[printCount][4]);
                jLabel204.setText(listOfContracts2[printCount][5]);
                jLabel205.setText(listOfContracts2[printCount][6]);  
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        jLabel200.setText(listOfContracts2[printCount][0]);
//        jLabel201.setText(listOfContracts2[printCount][2]);
//        jLabel202.setText(listOfContracts2[printCount][3]);
//        jLabel203.setText(listOfContracts2[printCount][4]);
//        jLabel204.setText(listOfContracts2[printCount][5]);
//        jLabel205.setText(listOfContracts2[printCount][6]);      
    }

    public void deleteLineFromFile(String file, String lineToRemove) {
        try {
            File inFile = new File(file);

            if (!inFile.isFile()) {
            System.out.println("Not existing file");
            return;
            }

            //Construct the new file that will later be renamed to the original filename.
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

            BufferedReader br = new BufferedReader(new FileReader(file));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            String line = null;

            //Read from the original file and write to the new
            //unless content matches data to be removed.
            while ((line = br.readLine()) != null) {
                if (!line.trim().equals(lineToRemove)) {
                    pw.println(line);
                    pw.flush();
                }
            }
            pw.close();
            br.close();

            //Delete the original file
            if (!inFile.delete()) {
                System.out.println("Unable to delete ");
                return;
            }

            //Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(inFile))
                System.out.println("Unable to rename file");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel213 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel200 = new javax.swing.JPanel();
        jButton100 = new javax.swing.JButton();
        jButton102 = new javax.swing.JButton();
        jButton101 = new javax.swing.JButton();
        jLabel208 = new javax.swing.JLabel();
        jLabel209 = new javax.swing.JLabel();
        jLabel210 = new javax.swing.JLabel();
        jLabel211 = new javax.swing.JLabel();
        jLabel212 = new javax.swing.JLabel();
        jLabel207 = new javax.swing.JLabel();
        jLabel200 = new javax.swing.JLabel();
        jLabel201 = new javax.swing.JLabel();
        jLabel202 = new javax.swing.JLabel();
        jLabel203 = new javax.swing.JLabel();
        jLabel204 = new javax.swing.JLabel();
        jLabel205 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel214 = new javax.swing.JLabel();
        jLabel215 = new javax.swing.JLabel();
        jLabel216 = new javax.swing.JLabel();
        jLabel217 = new javax.swing.JLabel();
        jLabel218 = new javax.swing.JLabel();
        jTextField100 = new javax.swing.JTextField();
        jTextField101 = new javax.swing.JTextField();
        jTextField102 = new javax.swing.JTextField();
        jTextField103 = new javax.swing.JTextField();
        jTextField104 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel213.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel213.setText("Welcome Processor");

        jButton100.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton100.setText("Next Deal");
        jButton100.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton100ActionPerformed(evt);
            }
        });

        jButton102.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton102.setText("Accept");
        jButton102.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton102ActionPerformed(evt);
            }
        });

        jButton101.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton101.setText("Reject");
        jButton101.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton101ActionPerformed(evt);
            }
        });

        jLabel208.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel208.setText("Product Type:");

        jLabel209.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel209.setText("Supply Date (DDMMYYYY):");

        jLabel210.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel210.setText("Quantity:");

        jLabel211.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel211.setText("Price (RM):");

        jLabel212.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel212.setText("Status:");

        jLabel207.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel207.setText("Sender:");

        jLabel200.setBackground(new java.awt.Color(153, 153, 153));
        jLabel200.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N

        jLabel201.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N

        jLabel202.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N

        jLabel203.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N

        jLabel204.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N

        jLabel205.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Verified Pending Deal");

        javax.swing.GroupLayout jPanel200Layout = new javax.swing.GroupLayout(jPanel200);
        jPanel200.setLayout(jPanel200Layout);
        jPanel200Layout.setHorizontalGroup(
            jPanel200Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel200Layout.createSequentialGroup()
                .addGroup(jPanel200Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel200Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jButton100, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton101, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jButton102, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel200Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(jPanel200Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel209)
                            .addComponent(jLabel208)
                            .addComponent(jLabel207)
                            .addComponent(jLabel210, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel211)
                            .addComponent(jLabel212))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel200Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel200Layout.createSequentialGroup()
                                .addComponent(jLabel200, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1))
                            .addGroup(jPanel200Layout.createSequentialGroup()
                                .addGroup(jPanel200Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel201, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                                    .addComponent(jLabel202, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel203, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel204, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel205, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel200Layout.setVerticalGroup(
            jPanel200Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel200Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(jPanel200Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel207, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jLabel200, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel200Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel208, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel201, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel200Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel209, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel202, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel200Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel210, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel203, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel200Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel204, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel211, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel200Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel212, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel205, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addGroup(jPanel200Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton100, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton101, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton102, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("View", jPanel200);

        jLabel214.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel214.setText("Product Type:");

        jLabel215.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel215.setText("Processed Date (DDMMYYYY):");

        jLabel216.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel216.setText("Price (RM):");

        jLabel217.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel217.setText("Receiver (Username):");

        jLabel218.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel218.setText("Quantity:");

        jTextField100.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jTextField101.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jTextField102.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jTextField103.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jTextField104.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setText("Create Deal (Contract)");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel214)
                            .addComponent(jLabel215)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel217)
                            .addComponent(jLabel216)
                            .addComponent(jLabel218))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField103)
                    .addComponent(jTextField104)
                    .addComponent(jTextField100, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField101)
                    .addComponent(jTextField102))
                .addGap(194, 194, 194))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(233, 233, 233)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel214, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField100, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel215, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel218, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel216, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField101, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField102, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField103, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField104, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel217, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        jTabbedPane1.addTab("Create", jPanel1);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton2.setText("Logout");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel213)
                .addGap(87, 87, 87)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel213, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(37, 37, 37)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton100ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton100ActionPerformed
        //NEXT DEAL
        if(listCount == 0){
            JOptionPane.showMessageDialog(null, "No Deal");
        }else{
            printCount++;
            if(printCount < listCount){    
                jLabel200.setText(listOfContracts2[printCount][0]);
                jLabel201.setText(listOfContracts2[printCount][2]);
                jLabel202.setText(listOfContracts2[printCount][3]);
                jLabel203.setText(listOfContracts2[printCount][4]);
                jLabel204.setText(listOfContracts2[printCount][5]);
                jLabel205.setText(listOfContracts2[printCount][6]);
            }else if (printCount >= listCount){
                printCount = 0;
                jLabel200.setText(listOfContracts2[printCount][0]);
                jLabel201.setText(listOfContracts2[printCount][2]);
                jLabel202.setText(listOfContracts2[printCount][3]);
                jLabel203.setText(listOfContracts2[printCount][4]);
                jLabel204.setText(listOfContracts2[printCount][5]);
                jLabel205.setText(listOfContracts2[printCount][6]);
            }
        }
    }//GEN-LAST:event_jButton100ActionPerformed

    private void jButton102ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton102ActionPerformed
        //ACCEPTS (ADD TO BLOCK)
        if(listCount == 0){
            JOptionPane.showMessageDialog(null, "No Deal");
        }else{
            String val0 = listOfContracts2[printCount][0];
            String val1 = listOfContracts2[printCount][1];
            String val2 = listOfContracts2[printCount][2];
            String val3 = listOfContracts2[printCount][3];
            String val4 = listOfContracts2[printCount][4];
            String val5 = listOfContracts2[printCount][5];
            Sextet<String,String,String,String,String,String> data_block = new Sextet(val0,val1,val2,val3,val4,val5);   
            //create first block (head)
            Asymmetric crypto = new Asymmetric();
            if(BlockChain.getChain() == null){ //if no chain, create genesis block
                try {
                    Block genesis = new Block(data_block, "0", crypto);
                    BlockChain.nextBlock(genesis);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{  //got chain, add block into chain
                try {
                    Block newBlock = new Block(data_block,BlockChain.getChain().getLast().getHash(), crypto);
                    BlockChain.nextBlock(newBlock);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            deleteLineFromFile("AStoPCcontract.txt",encrypted_data.get(printCount));
            JOptionPane.showMessageDialog(null, "Accepted, Saved into Ledger.");
            try {
                ViewProcessor view = new ViewProcessor(username, allUsername, allEntity);
                view.setLocationRelativeTo(null);
                view.setVisible(true);
                this.dispose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton102ActionPerformed

    private void jButton101ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton101ActionPerformed
        //REJECT
        if(listCount == 0){
            JOptionPane.showMessageDialog(null, "No Deal");
        }else{
            deleteLineFromFile("AStoPCcontract.txt",encrypted_data.get(printCount));
            JOptionPane.showMessageDialog(null, "Successfully Deleted.");
            try {
                ViewProcessor view = new ViewProcessor(username, allUsername, allEntity);
                view.setLocationRelativeTo(null);
                view.setVisible(true);
                this.dispose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        deleted = printCount;
//        printCount++;
//        jLabel200.setText(listOfContracts2[printCount][0]);
//        jLabel201.setText(listOfContracts2[printCount][2]);
//        jLabel202.setText(listOfContracts2[printCount][3]);
//        jLabel203.setText(listOfContracts2[printCount][4]);
//        jLabel204.setText(listOfContracts2[printCount][5]);
//        jLabel205.setText(listOfContracts2[printCount][6]);
//        
        
    }//GEN-LAST:event_jButton101ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //CREATE DEAL
        String product_type = jTextField100.getText();
        String processed_date= jTextField101.getText();
        String quantity = jTextField102.getText();
        String price = jTextField103.getText();
        String receiver = jTextField104.getText();
        if(product_type.equals("") == false && processed_date.equals("") == false && quantity.equals("") == false && price.equals("") == false && receiver.equals("") == false){
            boolean found = false;
            for (int i = 0; i < allUsername.size(); i++) {
                if ((receiver.equals(allUsername.get(i)) == true) && (username.equals(allUsername.get(i)) == false) && (allEntity.get(i).equals("Retailer"))){
                    found = true;
                    break;
                }
            }
            if (found){
                String data = String.join("|", username, receiver, product_type, processed_date, quantity, price, "Pending");
                DigSign ds = new DigSign();
                try {
                    PrivateKey private_key = AppConfig.getPrivatekey(AppConfig.PC_PRIVATEKEY_FILE);
                    //PublicKey public_key = AppConfig.getPublickey(AppConfig.AS_PUBLICKEY_FILE);
                    String digital_signature = ds.sign(data, private_key);
                    //System.out.println("digitalsignature: " + digital_signature);
                    //boolean isValid = ds.verify(data, digital_signature, public_key);
                    //System.out.println(isValid);
                    String to_be_written = String.join("|", data, digital_signature);
                    Symmetric sym_crypto = new Symmetric();
                    String encrypted_to_be_written = sym_crypto.encrypt(to_be_written);
                    IO.write("PCtoRTcontract.txt", encrypted_to_be_written);
                } catch (Exception e) {
                    e.printStackTrace();
                }  
//                //Asymmetric crypto = new Asymmetric();
//                try {
//                    //encrypted message
//                    //String cipherText = crypto.encrypt(contract_data, AppConfig.getPublickey(AppConfig.AS_PUBLICKEY_FILE));
//                    //IO.write("AStoPDcontract.txt", cipherText);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                JOptionPane.showMessageDialog(null, "Offer (Contract) is sent to " + receiver + "\nYou can view it when you re-login");
                jTextField100.setText("");
                jTextField101.setText("");
                jTextField102.setText("");
                jTextField103.setText("");
                jTextField104.setText("");
            }else{
                JOptionPane.showMessageDialog(null, "No Retailer Found");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Do not leave blank.");
        }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ViewLogin view = new ViewLogin();
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewProcessor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewProcessor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewProcessor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewProcessor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ViewProcessor(username, allUsername, allEntity).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton100;
    private javax.swing.JButton jButton101;
    private javax.swing.JButton jButton102;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel200;
    private javax.swing.JLabel jLabel201;
    private javax.swing.JLabel jLabel202;
    private javax.swing.JLabel jLabel203;
    private javax.swing.JLabel jLabel204;
    private javax.swing.JLabel jLabel205;
    private javax.swing.JLabel jLabel207;
    private javax.swing.JLabel jLabel208;
    private javax.swing.JLabel jLabel209;
    private javax.swing.JLabel jLabel210;
    private javax.swing.JLabel jLabel211;
    private javax.swing.JLabel jLabel212;
    private javax.swing.JLabel jLabel213;
    private javax.swing.JLabel jLabel214;
    private javax.swing.JLabel jLabel215;
    private javax.swing.JLabel jLabel216;
    private javax.swing.JLabel jLabel217;
    private javax.swing.JLabel jLabel218;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel200;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField100;
    private javax.swing.JTextField jTextField101;
    private javax.swing.JTextField jTextField102;
    private javax.swing.JTextField jTextField103;
    private javax.swing.JTextField jTextField104;
    // End of variables declaration//GEN-END:variables
}
