/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import jxl.Workbook;

/**
 *
 * @author Alex
 */
public class Exporter {
    private File file;
    private List<JTable> tabla;
    private List<String> nom_files;
    
    public Exporter(File file, List<JTable> tabla, List<String> nom_files) throws Exception
    {
        this.file = file;
        this.tabla = tabla;
        this.nom_files = nom_files;
        if(nom_files.size()!=tabla.size())
            throw new Exception("Error");
    }
    
//    public boolean a()
//    {
//        try {
////            DataOutputStream = out new DataOutputStream(new FileOutputStream(file));
////                WritetableWorkbook = w Workbook.createWorkbook(out);
//            return false;
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Exporter.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }
}



