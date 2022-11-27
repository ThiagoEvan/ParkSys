package Model;

import javax.swing.JOptionPane;
public class Classes_DAO {
    
    public static int Close_window(){
        Object[] options = {"Sim", "Não"};
        int sair = JOptionPane.showOptionDialog(null, "Você quer mesmo fechar o programa?", "ParkSys", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
        System.out.println(sair);
        return sair;
    }
    
}
