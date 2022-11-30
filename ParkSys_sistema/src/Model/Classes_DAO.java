package Model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Classes_DAO {
    
    static String servidor = "com.mysql.jdbc.Driver";
    static String urlBanco = "jdbc:mysql://localhost:3006/ParkSys";
    static String usuarioBanco = "root";
    static  String senhaBanco = "";
    
    public static int Close_window(){
        Object[] options = {"Sim", "Não"};
        int sair = JOptionPane.showOptionDialog(null, "Você quer mesmo fechar a janela?", "ParkSys", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
        System.out.println(sair);
        return sair;
    }
    
    public static void Inserir(){
        
            Connection con = null;
            PreparedStatement ps = null;
            int cod = 0;
        try {   
            Class.forName(servidor);
            con = DriverManager.getConnection(urlBanco,usuarioBanco,senhaBanco);
        } catch (SQLException ex) {
            Logger.getLogger(Classes_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
