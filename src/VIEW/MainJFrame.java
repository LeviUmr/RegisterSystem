package VIEW;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DAO.UserDAO;
import DTO.UserDTO;

public class MainJFrame extends JFrame implements ActionListener {

    private JPanel bgPanel;
    private JTextField nameFieldRegister;
    private JButton btnRegister;
    private JPasswordField passFieldRegister;
    private JTextField nameFieldLogin;
    private JPasswordField passFieldLogin;
    private JButton btnLogin;
    private JLabel StatusCheck;
    private JButton sairButton;
    private JButton apagarContaButton;

    public static int idSessao;

    ImageIcon img = new ImageIcon(
            MainJFrame.class.getResource("/Resources/logo.png")
    );
    public MainJFrame() {
        //window
        this.setIconImage(img.getImage());
        this.setVisible(true);
        this.setSize(500,600);
        this.setLocationRelativeTo(null);
        this.setTitle("Register System By: Levi");
        this.setResizable(false);

        //bgPanel
        bgPanel.setOpaque(true);

        //add
        this.add(bgPanel);

        btnLogin.addActionListener(this);
        btnRegister.addActionListener(this);
        sairButton.addActionListener(this);
        apagarContaButton.addActionListener(this);


    }
    public void actionPerformed(ActionEvent e){
        String name ="";

        UserDTO objUser = new UserDTO("","");
        UserDAO objUserDAO = new UserDAO();

        if(e.getSource()==btnLogin){
            //login
            name = nameFieldLogin.getText();
            String pass = new String (passFieldLogin.getPassword()).trim();
            objUser.setNome(name);
            objUser.setPassword(pass);
            if(objUserDAO.login(name,pass)){
                apagarContaButton.setVisible(true);
                nameFieldRegister.setEnabled(false);
                passFieldRegister.setEnabled(false);
                passFieldRegister.setEnabled(false);
                nameFieldRegister.setEnabled(false);
                nameFieldLogin.setBackground(Color.darkGray);
                passFieldLogin.setBackground(Color.darkGray);
                nameFieldRegister.setBackground(Color.darkGray);
                passFieldRegister.setBackground(Color.darkGray);
                btnLogin.setEnabled(false);
                btnRegister.setEnabled(false);
                idSessao = objUser.getUserId();
                StatusCheck.setText("Connected with: "+objUser.getNome());
                clearFields();
                sairButton.setVisible(true);
            }

            name="";
            pass = "";
        }
        if(e.getSource()==btnRegister){
            //register
            try {
                name = nameFieldRegister.getText();
                String pass = new String(passFieldRegister.getPassword()).trim();
                objUser.setNome(name);
                objUser.setPassword(pass);
                objUserDAO.registerUser(objUser);
                clearFields();
            }catch (Exception ex){
                System.out.println(ex);
            }
        }
        if(e.getSource()==sairButton){
            JOptionPane.showMessageDialog(null,"desconectando...");
            apagarContaButton.setVisible(false);
            nameFieldRegister.setEnabled(true);
            passFieldRegister.setEnabled(true);
            passFieldRegister.setEnabled(true);
            nameFieldRegister.setEnabled(true);
            nameFieldLogin.setBackground(Color.white);
            passFieldLogin.setBackground(Color.white);
            nameFieldRegister.setBackground(Color.white);
            passFieldRegister.setBackground(Color.white);
            btnLogin.setEnabled(true);
            btnRegister.setEnabled(true);
            sairButton.setVisible(false);
            StatusCheck.setText("Disconnected");
        }
        if(e.getSource()==apagarContaButton){
            int choose = 2;
            choose = JOptionPane.showConfirmDialog(null,"apagar conta?");
            objUserDAO.removeUser(objUser);
        }
    }

    public void clearFields(){
        nameFieldRegister.setText("");
        passFieldRegister.setText("");
        nameFieldLogin.setText("");
        passFieldLogin.setText("");
    }
}
