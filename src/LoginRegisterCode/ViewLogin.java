package LoginRegisterCode;

import UsersCode.ViewAgroSupplier;
import UsersCode.ViewProcessor;
import UsersCode.ViewRetailer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;

public class ViewLogin extends javax.swing.JFrame {

    public ViewLogin() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Login Form");

        jLabel2.setText("Username:");

        jLabel3.setText("Password:");

        jButton1.setText("Sign In");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                            .addComponent(jTextField2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(132, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private static final String FILE_SECRET = "secret.txt";
    private static final String FILE_LOGIN = "login.txt";
    private static final String ALGO = "SHA-256";
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String username = jTextField1.getText();
        String password = jTextField2.getText();
        /*
          check username
        */
        if (username.equals("") == false && password.equals("") == false) {
            List<String> loginData = IO.read(FILE_LOGIN);
            Iterator<String> iterator = loginData.iterator();
            Iterator<String> iterator2 = loginData.iterator();
            String retrievedUUID = ""; 
            String entity_type = "";
            String retrievedUsername = "";
            ArrayList<String> allUsername = new ArrayList<String>();
            ArrayList<String> allEntity = new ArrayList<String>();
            boolean found = false;
            while(iterator.hasNext()) {
                String data = (String) iterator.next();
                String[] splitData = data.split("\\|");                
                allUsername.add(splitData[0]);
                allEntity.add(splitData[2]);
            }
            while(iterator2.hasNext()) {
                String data = (String) iterator2.next();
                String[] splitData = data.split("\\|");
                retrievedUsername = splitData[0];
                if (username.equals(retrievedUsername) == false) {
                    found = false;
                } else {
                    found = true;
                    retrievedUUID = splitData[1];
                    entity_type = splitData[2];
                    break;
                }
            }
            if (found == false) {
                JOptionPane.showMessageDialog(null, "Incorrect username.");
            }
            /*
              check password
            */
            else if (found == true) {
                List<String> secretData = IO.read(FILE_SECRET);
                Iterator<String> iterator1 = secretData.iterator();
                while(iterator1.hasNext()) {
                    String data = (String) iterator1.next();
                    String[] splitData = data.split("\\|");
                    String readUUID = splitData[0];
                    if (retrievedUUID.equals(readUUID)) {
                        try{
                            String hash = LoginHasher.hash( Txt.append(splitData[1], password), ALGO); //user input password + salt retrieved
                            if (hash.equals(splitData[2])) { //splitData[2] == hashed password
                                JOptionPane.showMessageDialog(null, "Login successful.");
                                if(entity_type.equals("AgroSupplier")){
                                    ViewAgroSupplier view = new ViewAgroSupplier(retrievedUsername, allUsername, allEntity);
                                    view.setLocationRelativeTo(null);
                                    view.setVisible(true);
                                    this.dispose();
                                }else if (entity_type.equals("Processor")){
                                    ViewProcessor view = new ViewProcessor(retrievedUsername, allUsername, allEntity);
                                    view.setLocationRelativeTo(null);
                                    view.setVisible(true);
                                    this.dispose();
                                }else if (entity_type.equals("Retailer")){
                                    ViewRetailer view = new ViewRetailer(retrievedUsername, allUsername, allEntity);
                                    view.setLocationRelativeTo(null);
                                    view.setVisible(true);
                                    this.dispose();
                                }
                                break;
                            }else {
                                JOptionPane.showMessageDialog(null, "Incorrect password.");
                                break;
                            }
                        }catch (Exception e) {}
                    }
                }
            }            
        }else {
            JOptionPane.showMessageDialog(null, "Do not leave blank.");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ViewParent view = new ViewParent();
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed


    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}