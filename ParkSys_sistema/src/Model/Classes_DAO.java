package Model;

import java.nio.LongBuffer;
import com.mysql.jdbc.Connection;
import controller.Conexao_DB;
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
    
    static int cupom;
    static String placa;
    static String data;
    static String hora;
    static String obs;
    
    public static int Close_window(){
        Object[] options = {"Sim", "Não"};
        int sair = JOptionPane.showOptionDialog(null, "Você quer mesmo fechar a janela?", "ParkSys", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
        System.out.println(sair);
        return sair;
    }
    
    public static void Inserir(){

        placa = TelaAlterBD_GUI.txtPlacaIns.getText();
        obs = TelaAlterBD_GUI.txtObsIns.getText();
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

            String sql = "INSERT INTO vagas(placa,observação,hr_entrada) values('"+placa+"','"+obs+"','"+data + " " + hora+"');";
           
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
    
    public static void Busca(){
        try{
            cupom = Integer.valueOf(TelaAlterBD_GUI.txtCupom.getText());
            Conexao_DB.carregaDriver(); 
            
            try {
                Connection con = null;

                try {
                    con = (Connection) DriverManager.getConnection(urlBanco, usuarioBanco, senhaBanco);
                } catch (SQLException ex) {
                    Logger.getLogger(Classes_DAO.class.getName()).log(Level.SEVERE, null, ex);
                }

                
                String sql = "SELECT placa,observação,hr, cli_tel FROM cliente where cupom = "+cupom;
                
                Statement stm = (Statement) con.createStatement();
                try{ 
                    ResultSet rs = stm.executeQuery(sql);

                
                    int i=0; // Variavel utilizada para saber se ha dados cadastrados

                    while (rs.next()) {  // Criando variaveis que receberão os valores do banco de dados
                        String placa = rs.getString("placa");
                        String obs = rs.getString("observação");
                        String hr_bruta = rs.getString("hr_entrada");
                        
                        LongBuffer hr_entrada = LongBuffer.allocate(hr_bruta);
                        String data = hr_entrada.s();

                        i++;

                        TelaAlterBD_GUI.txtPlacaAlt.setText(String.valueOf(placa));
                        TelaAlterBD_GUI.txtObsAlt.setText(String.valueOf(obs));
                        txt.setText(String.valueOf(hora));

                    }

                    if(i==0){ // Verificando se ha dados cadastrados atraves da variavel i

                        JOptionPane.showMessageDialog(null,"Dado não cadastrado","Resultado",-1);

                    }

                } catch (Exception ex) { // Consulta mal sucedida
                    JOptionPane.showMessageDialog(null,"\nErro ao consultar!","ERRO",0);
                }

            } catch (SQLException ex) {
                // Conexão com servidor mal sucedida
                JOptionPane.showMessageDialog(null,"Erro ao conectar com o servidor","ERRO!",0);
            }

        }catch(NumberFormatException erro){
            // Código fora do formato
            JOptionPane.showMessageDialog(null,"Digite o código corretamante","ERRO",0);
            TelaAlterBD_GUI.txtCupom.setText("");
        }
    }
}
