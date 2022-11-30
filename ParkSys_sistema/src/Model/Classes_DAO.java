package Model;

import com.mysql.jdbc.Connection;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import view.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static org.eclipse.persistence.logging.SessionLog.SQL;

public class Classes_DAO {
   
    static String urlBanco = "jdbc:mysql://localhost/ParkSys";
    static String usuarioBanco = "root";
    static String senhaBanco = "";
    
    static String placa;
    static String data;
    static String hora;
    static String obv;
    
    public static int Close_window(){
        Object[] options = {"Sim", "Não"};
        int sair = JOptionPane.showOptionDialog(null, "Você quer mesmo fechar a janela?", "ParkSys", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
        System.out.println(sair);
        return sair;
    }
    
    
    public static void Inserir(){

        placa = TelaAlterBD_GUI.txtPlacaIns.getText();
        obv = TelaAlterBD_GUI.txtObsIns.getText();
        hora= TelaAlterBD_GUI.txtHoraIns.getText();
        data=TelaAlterBD_GUI.txtDataIns.getText();
        
        controller.Conexao_DB.carregaDriver();
        
        try{
        
            Connection con = null;

            try {
                con = (Connection) DriverManager.getConnection(urlBanco, usuarioBanco, senhaBanco);
            } catch (SQLException ex) {
                Logger.getLogger(Classes_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            String sql = "INSERT INTO vagas(placa,observação,hr_entrada) values('"+placa+"','"+obv+"','"+data + " " + hora+"');";
           
            try {
              PreparedStatement insert = (PreparedStatement) con.prepareStatement(sql);
               insert.execute();
            
                TelaAlterBD_GUI.txtPlacaIns.setText("");
                TelaAlterBD_GUI.txtObsIns.setText("");
                TelaAlterBD_GUI.txtHoraIns.setText("");
                TelaAlterBD_GUI.txtDataIns.setText("");

            } catch (SQLException ex) {
                Logger.getLogger(Classes_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }catch(NumberFormatException erro){
                // Tratamento de erro caso o usuario não digite o telefone corretamente
                JOptionPane.showMessageDialog(null,"Digite os dados corretamente","ERRO",0);
                
        }
    }
}
