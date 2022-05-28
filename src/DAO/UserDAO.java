package DAO;

import DTO.UserDTO;
import VIEW.MainJFrame;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;


public class UserDAO {
    PreparedStatement pstm;
    ConnectionDAO db = new ConnectionDAO();
    ResultSet rSet;
    UserDTO objUser = new UserDTO("","");
    ArrayList<UserDTO> lista = new ArrayList<UserDTO>();

    public void registerUser(UserDTO objUser){
        String insert = "insert into useraccount(UserName,UserPassword) values(?,?) ";

        try {
            pstm = db.getConnection().prepareStatement(insert);
            pstm.setString(1, objUser.getNome());
            pstm.setString(2, objUser.getPassword());
            pstm.execute();
            pstm.close();
        } catch (Exception error) {
            System.out.println(error);
        }
        JOptionPane.showMessageDialog(null, "Usuário inserido com sucesso");

    }

    //search
    public boolean login(String name,String pass){
        String sql = "select * from useraccount where UserName=? and UserPassword=?";
        try {
            pstm = db.getConnection().prepareStatement(sql);
            pstm.setString(1,name);
            pstm.setString(2,pass);
            rSet = pstm.executeQuery();

            if(rSet.next()){
                objUser.setUserId(rSet.getInt("UserId"));
                objUser.setNome(rSet.getString("UserName"));
                objUser.setPassword(rSet.getString("UserPassword"));
                objUser.setUserId(rSet.getInt("UserId"));
                lista.add(objUser);
                JOptionPane.showMessageDialog(null,"Usuário encontrado!"+objUser.toString());
                return true;
            }
            else{
                JOptionPane.showMessageDialog(null,"Usuário não encontrado!");
                return false;
            }

        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }


    return false;
    }
    public void removeUser(UserDTO objUser){
        int id = MainJFrame.idSessao;
        JOptionPane.showMessageDialog(null,"id = "+id);
        String sql = "delete from useraccount WHERE useraccount.UserId =?";
        try {
            pstm = db.getConnection().prepareStatement(sql);
            pstm.setInt(1,id);
            pstm.execute();
            pstm.close();
            JOptionPane.showMessageDialog(null,"Usuário removido com sucesso!");

        }catch(Exception ex){
            System.out.println(ex);
        }


    }


}
