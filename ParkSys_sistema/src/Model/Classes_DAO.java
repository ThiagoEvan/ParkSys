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
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static org.eclipse.persistence.logging.SessionLog.SQL;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeUnit;

public class Classes_DAO {
   
    static String urlBanco = "jdbc:mysql://localhost/ParkSys";
    static String usuarioBanco = "root";
    static String senhaBanco = "";
    
    static int cupom;
    static String placa;
    static String data;
    static String hora;
    static String obs;
    static double taxa = 4.5;
    static Date d1 = null;
    static Date d2 = null;
    
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

                
                String sql = "SELECT placa,observação,hr_entrada FROM vagas where cupom = "+cupom+";";
                
                Statement stm = (Statement) con.createStatement();
                try{ 
                    ResultSet rs = stm.executeQuery(sql);

                
                    int i=0; // Variavel utilizada para saber se ha dados cadastrados

                    while (rs.next()) {  // Criando variaveis que receberão os valores do banco de dados
                        String placa = rs.getString("placa");
                        String obs = rs.getString("observação");
                        String data = rs.getString("hr_entrada");
                        
                        i++;
                        
                        TelaAlterBD_GUI.txtPlacaAlt1.setText(String.valueOf(placa));
                        TelaAlterBD_GUI.txtObsAlt.setText(String.valueOf(obs));
                        TelaAlterBD_GUI.txtData_Hora.setText(String.valueOf(data));

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
    
    public static void Deletar(){
        
        cupom = Integer.parseInt(TelaAlterBD_GUI.txtCupom.getText());

        controller.Conexao_DB.carregaDriver();
        
        try{
        
            Connection con = null;

            try {
                con = (Connection) DriverManager.getConnection(urlBanco, usuarioBanco, senhaBanco);
            } catch (SQLException ex) {
                Logger.getLogger(Classes_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            String sql = "DELETE FROM vagas WHERE cupom = "+cupom+";";
           
            try {
              PreparedStatement insert = (PreparedStatement) con.prepareStatement(sql);
               insert.execute();
            
                TelaAlterBD_GUI.txtPlacaIns.setText("");
                TelaAlterBD_GUI.txtObsIns.setText("");
                TelaAlterBD_GUI.txtHoraIns.setText("");
                TelaAlterBD_GUI.txtDataIns.setText("");
                
                JOptionPane.showMessageDialog(null,"Registro deletado com sucesso");

            } catch (SQLException ex) {
                Logger.getLogger(Classes_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }catch(NumberFormatException erro){
                // Tratamento de erro caso o usuario não digite o telefone corretamente
                JOptionPane.showMessageDialog(null,"Digite os dados corretamente","ERRO",0);
                
        }
    }
    
    public static void Pagar() throws ParseException{
        try{
            cupom = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite o cupom"));
            Conexao_DB.carregaDriver(); 
            
            try {
                Connection con = null;

                try {
                    con = (Connection) DriverManager.getConnection(urlBanco, usuarioBanco, senhaBanco);
                } catch (SQLException ex) {
                    Logger.getLogger(Classes_DAO.class.getName()).log(Level.SEVERE, null, ex);
                }

                
                String sql = "SELECT hr_entrada FROM vagas where cupom = "+cupom+";";
                
                Statement stm = (Statement) con.createStatement();
                try{ 
                    ResultSet rs = stm.executeQuery(sql);

                
                    int i=0; // Variavel utilizada para saber se ha dados cadastrados

                    while (rs.next()) {  // Criando variaveis que receberão os valores do banco de dados
                        
                        String data = rs.getString("hr_entrada");
                        
                        i++;
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
            
        }
            
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now(); 
        String datnow = String.valueOf(dtf);
        String datbef = String.valueOf(data);
        
        String sql = "INSERT INTO vagas(hr_saida) values('"+dtf+"') WHERE cupom = "+cupom+";";
           
        try {
            Connection con = null;

            PreparedStatement insert = (PreparedStatement) con.prepareStatement(sql);
            insert.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Classes_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
        
        d1 = format.parse(datnow);
        d2 = format.parse(datbef);
        
        long diff = d1.getTime() - d2.getTime();
        
        long diffHours = diff / (60 * 60 * 1000);
        
        double preco_pagar = ( diffHours * taxa ) +taxa;
        
        JOptionPane.showMessageDialog(null, "O preço a pagar é de:"+preco_pagar);
    }
}
