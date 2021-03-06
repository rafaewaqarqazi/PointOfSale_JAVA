package com.pos.gui;
import com.pos.Login;
import javax.swing.*;


public class LoginGUI extends JFrame {

    // Creates new form LoginWindow
    
    public LoginGUI() {
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

        LoginPanel = new JPanel();
        loginLabel = new javax.swing.JLabel();
        usernameLabel = new JLabel();
        passwordLabel = new JLabel();
        usernameTextField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton();
        cancelButton = new JButton();
        userTypeLabel = new javax.swing.JLabel();
        userTypeComboBox = new javax.swing.JComboBox<>();
        loginBackground = new JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login to POS");
        setResizable(false);

        LoginPanel.setAutoscrolls(true);
        LoginPanel.setMinimumSize(new java.awt.Dimension(700, 400));
        LoginPanel.setLayout(null);

        loginLabel.setFont(new java.awt.Font("28 Days Later", 0, 60)); // NOI18N
        loginLabel.setForeground(new java.awt.Color(62, 62, 62));
        loginLabel.setText("Log in");
        LoginPanel.add(loginLabel);
        loginLabel.setBounds(430, 60, 150, 60);

        usernameLabel.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(62, 62, 62));
        usernameLabel.setText("Username:");
        LoginPanel.add(usernameLabel);
        usernameLabel.setBounds(340, 210, 80, 20);

        passwordLabel.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        passwordLabel.setForeground(new java.awt.Color(62, 62, 62));
        passwordLabel.setText("Password:");
        LoginPanel.add(passwordLabel);
        passwordLabel.setBounds(340, 250, 80, 19);

        usernameTextField.setBackground(new java.awt.Color(204, 204, 255));
        usernameTextField.setColumns(20);
        usernameTextField.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        usernameTextField.setForeground(new java.awt.Color(62, 62, 62));
        LoginPanel.add(usernameTextField);
        usernameTextField.setBounds(430, 200, 210, 30);

        passwordField.setBackground(new java.awt.Color(204, 204, 255));
        passwordField.setColumns(20);
        passwordField.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        passwordField.setForeground(new java.awt.Color(62, 62, 62));
        passwordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordFieldKeyPressed(evt);
            }
        });
        LoginPanel.add(passwordField);
        passwordField.setBounds(430, 240, 210, 30);

        loginButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        loginButton.setForeground(new java.awt.Color(62, 62, 62));
        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });
        LoginPanel.add(loginButton);
        loginButton.setBounds(430, 290, 100, 40);

        cancelButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cancelButton.setForeground(new java.awt.Color(62, 62, 62));
        cancelButton.setText("Cancel");
        cancelButton.setMaximumSize(new java.awt.Dimension(67, 25));
        cancelButton.setMinimumSize(new java.awt.Dimension(67, 25));
        cancelButton.setPreferredSize(new java.awt.Dimension(67, 25));
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        LoginPanel.add(cancelButton);
        cancelButton.setBounds(540, 290, 100, 40);

        userTypeLabel.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        userTypeLabel.setForeground(new java.awt.Color(62, 62, 62));
        userTypeLabel.setText("Type of User:");
        LoginPanel.add(userTypeLabel);
        userTypeLabel.setBounds(320, 160, 100, 19);

        userTypeComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        userTypeComboBox.setForeground(new java.awt.Color(62, 62, 62));
        userTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Employee" }));
        userTypeComboBox.setPreferredSize(new java.awt.Dimension(67, 25));
        LoginPanel.add(userTypeComboBox);
        userTypeComboBox.setBounds(430, 150, 100, 40);

        loginBackground.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        loginBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pos/icons/POS_Login Background.jpg"))); // NOI18N
        LoginPanel.add(loginBackground);
        loginBackground.setBounds(0, 0, 680, 460);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(LoginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LoginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_cancelButtonActionPerformed
private void onButtonPress(){
    try{
        String username = usernameTextField.getText();
        String password = new String(passwordField.getPassword());
        String userType = userTypeComboBox.getSelectedItem().toString();
        Login login =new Login();
        
        if (userType.equals("Admin")) {
            if (login.evaluateAdmin(username, password)) {
                login.setOnlineUser(username);
                login.setUserType(userType);
                MainMenuGUI mainMenu = new MainMenuGUI();
                mainMenu.setVisible(true);
                this.dispose();
            }
            else{
                JOptionPane.showMessageDialog(null, "Username or Password is Incorrect","Login Failed!",JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
           if (login.evaluateEmployee(username, password)) {
                login.setOnlineUser(username);
                login.setUserType(userType);
                PosGUI pos = new PosGUI();
                pos.setVisible(true);
                this.dispose();
            }
           else{
                JOptionPane.showMessageDialog(null, "Username or Password is Incorrect","Login Failed!",JOptionPane.ERROR_MESSAGE);
            }
        }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Unexpected Error Occured!\nApplication is going to close!","",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
      onButtonPress();     
    }//GEN-LAST:event_loginButtonActionPerformed

    private void passwordFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordFieldKeyPressed
        if (evt.getKeyCode()== evt.VK_ENTER) {
            onButtonPress();
        }
    }//GEN-LAST:event_passwordFieldKeyPressed
    
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
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel LoginPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel loginBackground;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JComboBox<String> userTypeComboBox;
    private javax.swing.JLabel userTypeLabel;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables
}
