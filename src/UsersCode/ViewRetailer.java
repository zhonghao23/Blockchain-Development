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
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import org.javatuples.Sextet;

public class ViewRetailer extends javax.swing.JFrame {
    static String username;
    static ArrayList<String> allUsername;
    static ArrayList<String> allEntity;
    /**
     * Creates new form ViewRetailer
     */
    String data;
    FileOutputStream writer;
    String[][] listOfContracts3 = new String[20][7];
    ArrayList<String> encrypted_data = new ArrayList<String>();
    int listCount = 0;
    int printCount = 0;
    public ViewRetailer(String username, ArrayList<String> allUsername, ArrayList<String> allEntity) {
        this.username = username;
        this.allUsername = allUsername;
        this.allEntity = allEntity;
        initComponents();
        List<String> allContract3 = IO.read("PCtoRTcontract.txt");
        Iterator<String> iterator3 = allContract3.iterator();
        //Asymmetric crypto = new Asymmetric();
        //data = crypto.decrypt(data, AppConfig.getPrivatekey(AppConfig.AS_PRIVATEKEY_FILE));  
        DigSign ds2 = new DigSign();
        try {
            PublicKey public_key2 = AppConfig.getPublickey(AppConfig.PC_PUBLICKEY_FILE);
            Symmetric sym_crypto = new Symmetric();
            while(iterator3.hasNext()) {
                //FORMAT: username|receiver|product_type|processed_date|quantity|price|status|digitalsignature
                data = (String)iterator3.next(); //symmetric encryped & digitally signed
                String decrypted_data = sym_crypto.decrypt(data);   //decrypted but digitally signed
                String tempData = data;
                String[] temp = decrypted_data.split("\\|");
                if (temp.length >5){
                    String data_concat = String.join("|", temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6]);
                    try {
                        boolean isValid = ds2.verify(data_concat, temp[7], public_key2);
                        if(isValid){
                            if(temp[1].equals(username) && listCount < 20){
                                encrypted_data.add(tempData);
                                for (int i=0;i<7;i++){
                                    listOfContracts3[listCount][i] = temp[i];
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
                jLabel308.setText(listOfContracts3[printCount][0]);
                jLabel309.setText(listOfContracts3[printCount][2]);
                jLabel310.setText(listOfContracts3[printCount][3]);
                jLabel311.setText(listOfContracts3[printCount][4]);
                jLabel312.setText(listOfContracts3[printCount][5]);
                jLabel313.setText(listOfContracts3[printCount][6]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteLineFromFile1(String file, String lineToRemove) {
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

        jLabel300 = new javax.swing.JLabel();
        jButton300 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel301 = new javax.swing.JLabel();
        jLabel302 = new javax.swing.JLabel();
        jLabel303 = new javax.swing.JLabel();
        jLabel304 = new javax.swing.JLabel();
        jLabel305 = new javax.swing.JLabel();
        jLabel306 = new javax.swing.JLabel();
        jLabel307 = new javax.swing.JLabel();
        jLabel308 = new javax.swing.JLabel();
        jLabel309 = new javax.swing.JLabel();
        jLabel310 = new javax.swing.JLabel();
        jLabel311 = new javax.swing.JLabel();
        jLabel312 = new javax.swing.JLabel();
        jLabel313 = new javax.swing.JLabel();
        jButton301 = new javax.swing.JButton();
        jButton302 = new javax.swing.JButton();
        jButton303 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel300.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel300.setText("Welcome Retailer");

        jButton300.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton300.setText("Logout");
        jButton300.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton300ActionPerformed(evt);
            }
        });

        jLabel301.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel301.setText("Verified Pending Deal");

        jLabel302.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel302.setText("Sender:");

        jLabel303.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel303.setText("Product Type:");

        jLabel304.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel304.setText("Processed Date (DDMMYYYY):");

        jLabel305.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel305.setText("Quantity:");

        jLabel306.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel306.setText("Price (RM):");

        jLabel307.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel307.setText("Status:");

        jLabel308.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel309.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel310.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel311.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel312.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel313.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jButton301.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton301.setText("Next Deal");
        jButton301.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton301ActionPerformed(evt);
            }
        });

        jButton302.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton302.setText("Reject");
        jButton302.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton302ActionPerformed(evt);
            }
        });

        jButton303.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton303.setText("Accept");
        jButton303.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton303ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel301, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel303)
                            .addComponent(jLabel304)
                            .addComponent(jLabel302)
                            .addComponent(jLabel305)
                            .addComponent(jLabel306)
                            .addComponent(jLabel307))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel309, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel308, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel310, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel311, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel312, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel313, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jButton301, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton302, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton303, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel301, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel302)
                    .addComponent(jLabel308))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel303)
                    .addComponent(jLabel309))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel304)
                    .addComponent(jLabel310))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel305)
                    .addComponent(jLabel311))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel306)
                    .addComponent(jLabel312))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel307)
                    .addComponent(jLabel313))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton301, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton302, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton303, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(290, 290, 290)
                .addComponent(jLabel300)
                .addGap(76, 76, 76)
                .addComponent(jButton300, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel300, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton300))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton301ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton301ActionPerformed
        //NEXT DEAL
        if(listCount == 0){
            JOptionPane.showMessageDialog(null, "No Deal");
        }else{
            printCount++;
            if(printCount < listCount){    
                jLabel308.setText(listOfContracts3[printCount][0]);
                jLabel309.setText(listOfContracts3[printCount][2]);
                jLabel310.setText(listOfContracts3[printCount][3]);
                jLabel311.setText(listOfContracts3[printCount][4]);
                jLabel312.setText(listOfContracts3[printCount][5]);
                jLabel313.setText(listOfContracts3[printCount][6]);
            }else if (printCount >= listCount){
                printCount = 0;
                jLabel308.setText(listOfContracts3[printCount][0]);
                jLabel309.setText(listOfContracts3[printCount][2]);
                jLabel310.setText(listOfContracts3[printCount][3]);
                jLabel311.setText(listOfContracts3[printCount][4]);
                jLabel312.setText(listOfContracts3[printCount][5]);
                jLabel313.setText(listOfContracts3[printCount][6]);
            }
        }
    }//GEN-LAST:event_jButton301ActionPerformed

    private void jButton302ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton302ActionPerformed
        //REJECT
        if(listCount == 0){
            JOptionPane.showMessageDialog(null, "No Deal");
        }else{
            deleteLineFromFile1("PCtoRTcontract.txt",encrypted_data.get(printCount));
            JOptionPane.showMessageDialog(null, "Successfully Deleted.");
            try {
                ViewRetailer view = new ViewRetailer(username, allUsername, allEntity);
                view.setLocationRelativeTo(null);
                view.setVisible(true);
                this.dispose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton302ActionPerformed

    private void jButton300ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton300ActionPerformed
        //LOGOUT
        ViewLogin view = new ViewLogin();
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton300ActionPerformed

    private void jButton303ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton303ActionPerformed
        //ACCEPTS (ADD TO BLOCK)
        if(listCount == 0){
            JOptionPane.showMessageDialog(null, "No Deal");
        }else{
            String val0 = listOfContracts3[printCount][0];
            String val1 = listOfContracts3[printCount][1];
            String val2 = listOfContracts3[printCount][2];
            String val3 = listOfContracts3[printCount][3];
            String val4 = listOfContracts3[printCount][4];
            String val5 = listOfContracts3[printCount][5];
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
            deleteLineFromFile1("PCtoRTcontract.txt",encrypted_data.get(printCount));
            JOptionPane.showMessageDialog(null, "Accepted, Saved into Ledger.");
            try {
                ViewRetailer view = new ViewRetailer(username, allUsername, allEntity);
                view.setLocationRelativeTo(null);
                view.setVisible(true);
                this.dispose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        
        
        
    }//GEN-LAST:event_jButton303ActionPerformed

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
            java.util.logging.Logger.getLogger(ViewRetailer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewRetailer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewRetailer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewRetailer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ViewRetailer(username, allUsername, allEntity).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton300;
    private javax.swing.JButton jButton301;
    private javax.swing.JButton jButton302;
    private javax.swing.JButton jButton303;
    private javax.swing.JLabel jLabel300;
    private javax.swing.JLabel jLabel301;
    private javax.swing.JLabel jLabel302;
    private javax.swing.JLabel jLabel303;
    private javax.swing.JLabel jLabel304;
    private javax.swing.JLabel jLabel305;
    private javax.swing.JLabel jLabel306;
    private javax.swing.JLabel jLabel307;
    private javax.swing.JLabel jLabel308;
    private javax.swing.JLabel jLabel309;
    private javax.swing.JLabel jLabel310;
    private javax.swing.JLabel jLabel311;
    private javax.swing.JLabel jLabel312;
    private javax.swing.JLabel jLabel313;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
