package Model;

//---------------------> Gerar PDF
import javax.swing.JOptionPane;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
//---------------------> Gerar PDF

//---------------------> Escolher Pasta
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
//---------------------> Escolher Pasta

public class PDFgen_DAO {
    public static String Caminho = ""; 
    
    public static final Font texto =
    new Font(FontFamily.HELVETICA, 18);
    
    public static final Font titulo =
    new Font(FontFamily.HELVETICA, 24, Font.BOLD);
    
    public static void GerarPDF(){
          JFileChooser arquivo = new JFileChooser();

          arquivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
          int retorno = arquivo.showSaveDialog(null);
 
            
       if(retorno == JFileChooser.APPROVE_OPTION){
           Caminho = arquivo.getSelectedFile().getPath()+"//";
       }else{
           JOptionPane.showMessageDialog(null, "Algo deu errado!");
       }
        Document documentopdf = new Document();
        
        try{
            //caminho
            PdfWriter.getInstance(documentopdf, new FileOutputStream(Caminho+"Cupom de declaracao ParkSys.pdf"));
            
            documentopdf.open();
            
            Paragraph preface = new Paragraph("Cupom "+view.TelaAlterBD_GUI.txtCupom.getText(), titulo); 
            preface.setAlignment(Element.ALIGN_CENTER);
            documentopdf.add(preface);
            documentopdf.setPageSize(PageSize.A4);
            documentopdf.add(new Paragraph(" "));
            documentopdf.add(new Paragraph("Referente a placa: "+ view.TelaAlterBD_GUI.txtPlacaAlt1.getText(), texto));
            documentopdf.add(new Paragraph(" "));
            documentopdf.add(new Paragraph("Na data e hora: "+view.TelaAlterBD_GUI.txtData_Hora.getText(), texto));
            documentopdf.add(new Paragraph(" "));
            Paragraph assResp = (new Paragraph("Assinatura da empresa: ParkSys"));
            assResp.setAlignment(Element.ALIGN_CENTER);
            documentopdf.add(assResp);
            documentopdf.add(new Paragraph(" "));
            Paragraph camp = (new Paragraph("____________________________________"));
         
    
        }catch(DocumentException de){
            de.printStackTrace();
            JOptionPane.showMessageDialog(null, "Algo deu errado!");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PDFgen_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            documentopdf.close();
            JOptionPane.showMessageDialog(null, "Seu pdf foi criado com sucesso!");
        }    
    }
    
}
