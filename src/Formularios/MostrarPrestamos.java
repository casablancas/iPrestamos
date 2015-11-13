/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import BD.ConexionBD;
import java.awt.Desktop;
//import java.awt.Desktop;
import java.awt.Rectangle;
import static java.awt.image.ImageObserver.ERROR;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
//import jxl.write.biff.File;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Alex
 */
public class MostrarPrestamos extends javax.swing.JFrame {

    /**
     * Creates new form MostrarPrestamos
     */
    public MostrarPrestamos() {
        initComponents();
        this.setTitle("Registro de todos los préstamos realizados");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        buscarNombre("");
        txtBuscarFecha.setEnabled(false);
        txtBuscarMaterial.setEnabled(false);
//        mostrarPrestamo("");
        tablaPrestamos.setEditingRow(ERROR);
    }
    
    //Creamos la instancia 'con' de tipo ConexionBD
    ConexionBD cc = new ConexionBD();
    Connection cn = cc.conectar();
    
    DefaultTableModel model;
    
    public void buscarNombre(String valor)
    {
        
        //Encabezados de la tabla
        String [] titulos = {"", "Profesor", "Apellidos", "Colegio", "Material", "Usuario", "Apellidos", "FechaPrestamo", "HoraPrestamo", "Salón/Hora"};
        String [] registros = new String[10];
        
        String sql = "SELECT IdPrestamo, Profesor.Nombre AS profe, Profesor.Apellidos AS apellidosprofe, Colegio, Usuario.Nombre AS prestador, Material.Nombre AS material, Usuario.Apellidos AS apellidosprestador, Fecha_Entrega, Hora_Entrega, Salon FROM Prestamo INNER JOIN Profesor ON Prestamo.Profesor_IdProfesor = Profesor.IdProfesor INNER JOIN Material ON Prestamo.Material_IdMaterial = Material.IdMaterial INNER JOIN Usuario ON Prestamo.Usuario_IdPrestador = Usuario.IdPrestador WHERE Profesor.Nombre LIKE '%"+valor+"%' ";
//        String sql2 = "SELECT * FROM somhue WHERE hueapellidos LIKE '%"+valor+"%' ";
        //String sql = "SELECT * FROM somhue";
        
        //Creamos nuestro objeto para la tabla que muestra los datos de la BD
        model = new DefaultTableModel(null, titulos);
        
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next())
            {
                registros[0] = rs.getString("IdPrestamo");
                registros[1] = rs.getString("profe");
                registros[2] = rs.getString("apellidosprofe");
                registros[3] = rs.getString("colegio");
                registros[4] = rs.getString("material");
                registros[5] = rs.getString("prestador");
                registros[6] = rs.getString("apellidosprestador");
                registros[7] = rs.getString("Fecha_Entrega");
                registros[8] = rs.getString("Hora_Entrega");
                registros[9] = rs.getString("Salon");
                model.addRow(registros);
            }
            tablaPrestamos.setModel(model);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public void buscarFecha(String valor)
    {
        //Encabezados de la tabla
        String [] titulos = {"", "Profesor", "Apellidos", "Colegio", "Material", "Usuario", "Apellidos", "FechaPrestamo", "HoraPrestamo", "Salón/Hora"};
        String [] registros = new String[10];
        
        String sql = "SELECT IdPrestamo, Profesor.Nombre AS profe, Profesor.Apellidos AS apellidosprofe, Colegio, Usuario.Nombre AS prestador, Material.Nombre AS material, Usuario.Apellidos AS apellidosprestador, Fecha_Entrega, Hora_Entrega, Salon FROM Prestamo INNER JOIN Profesor ON Prestamo.Profesor_IdProfesor = Profesor.IdProfesor INNER JOIN Material ON Prestamo.Material_IdMaterial = Material.IdMaterial INNER JOIN Usuario ON Prestamo.Usuario_IdPrestador = Usuario.IdPrestador WHERE Fecha_Entrega LIKE '%"+valor+"%' ";
//        String sql2 = "SELECT * FROM somhue WHERE hueapellidos LIKE '%"+valor+"%' ";
        //String sql = "SELECT * FROM somhue";
        
        //Creamos nuestro objeto para la tabla que muestra los datos de la BD
        model = new DefaultTableModel(null, titulos);
        
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next())
            {
                registros[0] = rs.getString("IdPrestamo");
                registros[1] = rs.getString("profe");
                registros[2] = rs.getString("apellidosprofe");
                registros[3] = rs.getString("colegio");
                registros[4] = rs.getString("material");
                registros[5] = rs.getString("prestador");
                registros[6] = rs.getString("apellidosprestador");
                registros[7] = rs.getString("Fecha_Entrega");
                registros[8] = rs.getString("Hora_Entrega");
                registros[9] = rs.getString("Salon");
                model.addRow(registros);
            }
            tablaPrestamos.setModel(model);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public void buscarMaterial(String valor)
    {
        
        //Encabezados de la tabla
        String [] titulos = {"", "Profesor", "Apellidos", "Colegio", "Material", "Usuario", "Apellidos", "FechaPrestamo", "HoraPrestamo", "Salón/Hora"};
        String [] registros = new String[10];
        
        String sql = "SELECT IdPrestamo, Profesor.Nombre AS profe, Profesor.Apellidos AS apellidosprofe, Colegio, Usuario.Nombre AS prestador, Material.Nombre AS material, Usuario.Apellidos AS apellidosprestador, Fecha_Entrega, Hora_Entrega, Salon FROM Prestamo INNER JOIN Profesor ON Prestamo.Profesor_IdProfesor = Profesor.IdProfesor INNER JOIN Material ON Prestamo.Material_IdMaterial = Material.IdMaterial INNER JOIN Usuario ON Prestamo.Usuario_IdPrestador = Usuario.IdPrestador WHERE Material.Nombre LIKE '%"+valor+"%' ";
//        String sql2 = "SELECT * FROM somhue WHERE hueapellidos LIKE '%"+valor+"%' ";
        //String sql = "SELECT * FROM somhue";
        
        //Creamos nuestro objeto para la tabla que muestra los datos de la BD
        model = new DefaultTableModel(null, titulos);
        
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next())
            {
                registros[0] = rs.getString("IdPrestamo");
                registros[1] = rs.getString("profe");
                registros[2] = rs.getString("apellidosprofe");
                registros[3] = rs.getString("colegio");
                registros[4] = rs.getString("material");
                registros[5] = rs.getString("prestador");
                registros[6] = rs.getString("apellidosprestador");
                registros[7] = rs.getString("Fecha_Entrega");
                registros[8] = rs.getString("Hora_Entrega");
                registros[9] = rs.getString("Salon");
                model.addRow(registros);
            }
            tablaPrestamos.setModel(model);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    
//    void cargar(String valor)
//    {
////        txtNombre.setEnabled(false);
////        txtApellidos.setEnabled(false);
////        btnModificar.setEnabled(false);
//        
//        //Encabezados de la tabla
//        String [] titulos = {"", "Profesor", "Apellidos", "Material", "Colegio", "Usuario", "Apellidos", "FechaPrestamo", "HoraPrestamo"};
//        String [] registros = new String[9];
//        
//        String sql = "SELECT IdPrestamo, Profesor.Nombre AS profe, Profesor.Apellidos AS apellidosprofe, Material.Nombre AS material, Colegio, Usuario.Nombre AS prestador, Usuario.Apellidos AS apellidosprestador, Fecha_Entrega, Hora_Entrega FROM Prestamo INNER JOIN Profesor ON Prestamo.Profesor_IdProfesor = Profesor.IdProfesor INNER JOIN Material ON Prestamo.Material_IdMaterial = Material.IdMaterial INNER JOIN Usuario ON Prestamo.Usuario_IdPrestador = Usuario.IdPrestador WHERE Profesor.Nombre LIKE '%"+valor+"%' ";
////        String sql2 = "SELECT * FROM somhue WHERE hueapellidos LIKE '%"+valor+"%' ";
//        //String sql = "SELECT * FROM somhue";
//        
//        //Creamos nuestro objeto para la tabla que muestra los datos de la BD
//        model = new DefaultTableModel(null, titulos);
//        
//        Statement st;
//        try {
//            st = cn.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            
//            while(rs.next())
//            {
//                registros[0] = rs.getString("IdPrestamo");
//                registros[1] = rs.getString("profe");
//                registros[2] = rs.getString("apellidosprofe");
//                registros[3] = rs.getString("material");
//                registros[4] = rs.getString("colegio");
//                registros[5] = rs.getString("prestador");
//                registros[6] = rs.getString("apellidosprestador");
//                registros[7] = rs.getString("Fecha_Entrega");
//                registros[8] = rs.getString("Hora_Entrega");
//                model.addRow(registros);
//            }
//            tablaPrestamos.setModel(model);
//            
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex);
//        }
//    }
    
//    public void mostrarPrestamo(String valor)
//  {
//      //Establece los valores para la sentencia SQL
////       Connection c = con.conectar();
//      //Creamos la instancia 'con' de tipo ConexionBD
//        ConexionBD cc = new ConexionBD();
//        Connection cn = cc.conectar();
//       
//       //Encabezados de la tabla
//        String [] titulos = {"Profesor", "Material Prestado(s)", "Prestador"};
//        String [] registros = new String[3];
//       
////       String sql = "SELECT Profesor.Nombre,Material.Nombre,Usuario.Nombre FROM Prestamo "
////               + "INNER JOIN Profesor ON Prestamo.Profesor_IdProfesor = Profesor.IdProfesor"
////               + "INNER JOIN Material ON Prestamo.Material_IdMaterial = Material.IdMaterial"
////               + "INNER JOIN Usuario ON Prestamo.Usuario_IdPrestador = Usuario.IdPrestador";
//
////        String sql = "SELECT Profesor.Nombre AS profe, Material.Nombre AS material, Usuario.Nombre AS prestador FROM Prestamo INNER JOIN Profesor ON Prestamo.Profesor_IdProfesor = Profesor.IdProfesor INNER JOIN Material ON Prestamo.Material_IdMaterial = Material.IdMaterial INNER JOIN Usuario ON Prestamo.Usuario_IdPrestador = Usuario.IdPrestador";
//        String sql = "SELECT Profesor.Nombre AS profe, Material.Nombre AS material, Usuario.Nombre AS prestador FROM Prestamo INNER JOIN Profesor ON Prestamo.Profesor_IdProfesor = Profesor.IdProfesor INNER JOIN Material ON Prestamo.Material_IdMaterial = Material.IdMaterial INNER JOIN Usuario ON Prestamo.Usuario_IdPrestador = Usuario.IdPrestador";
//
//       
//        //Creamos nuestro objeto para la tabla que muestra los datos de la BD
//        DefaultTableModel model;
//        model = new DefaultTableModel(null, titulos);
//        
//        Statement st;
//        try {
//            st = cn.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            
//            while(rs.next())
//            {
//                registros[0] = rs.getString("profe");
//                registros[1] = rs.getString("material");
//                registros[2] = rs.getString("prestador");
//                model.addRow(registros);
//            }
//            tablaPrestamos.setModel(model);
//            
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex);
//            System.out.println(ex);
//        }
//  }
    
    public void eliminar()
    {
        //Establece los valores para la sentencia SQL
        Connection c = cc.conectar();
        
        int fila = tablaPrestamos.getSelectedRow();
        if(fila>=0){
            String idPrestamo = tablaPrestamos.getValueAt(fila, 0).toString();
            System.out.println("ID PARA ELIMINAR: " +idPrestamo);
            String sql = "DELETE FROM Prestamo WHERE  IdPrestamo = '"+idPrestamo+"'";
            try {
                PreparedStatement pst = (PreparedStatement) c.prepareStatement(sql);
                pst.executeUpdate();
                buscarNombre("");
            } catch (SQLException ex) {
                Logger.getLogger(VerRegistrosProfesores.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else
            JOptionPane.showMessageDialog(null, "Debe elegir el elemento de la tabla que desea modificar.", "No se seleccionó ningún elemento.", JOptionPane.ERROR_MESSAGE);
    }
    
    public void confirmacion(){
        if (JOptionPane.showConfirmDialog(rootPane, "¿Realmente desea eliminar este elemento?",
                "Confirmación para borrar profesor", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            eliminar();
    } 
    
//    public void generarExcel()
//    {
//        try{
//            Thread thread;
//            thread = new Thread(){
//         public void run()
//         {
//             XSSFWorkbook workbook = new XSSFWorkbook();
//             XSSFSheet hoja = workbook.createSheet();
//
//             XSSFRow fila = hoja.createRow(0);
//             fila.createCell(0).setCellValue("Probando");
//             fila.createCell(1).setCellValue("Excel");
//
//             barraProgreso.setMaximum(tablaPrestamos.getRowCount());
//             XSSFRow filas;
//             Rectangle rect;
//             for(int i=0; i<tablaPrestamos.getRowCount(); i++)
//             {
//                 rect = tablaPrestamos.getCellRect(i, 0, true);
//                 try{
//                     tablaPrestamos.scrollRectToVisible(rect);
//                 }catch(java.lang.ClassCastException e){}
//
//                 tablaPrestamos.setRowSelectionInterval(i, i);
//                 barraProgreso.setValue((i+1));
//
//                 filas = hoja.createRow((i+1));
//                 filas.createCell(0).setCellValue(tablaPrestamos.getValueAt(i, 0).toString());
//                 filas.createCell(1).setCellValue(tablaPrestamos.getValueAt(i, 1).toString());
//             }
//
//             barraProgreso.setValue(0);
//             barraProgreso.setString("Generando Excel...");
//
//             try {                
//                 workbook.write(new FileOutputStream(new File("//C://Users//Alex//Documents//NetBeansProjects//SSING_2_1_4//Excel.xlsx")));
////                 Desktop.getDesktop().open(new File("/Desktop///Excel.xlsx"));
//                 Desktop.getDesktop().open(new File("//C://Users//Alex//Documents//NetBeansProjects//SSING_2_1_4//Excel.xlsx"));
//             } catch (IOException ex) {
//                 Logger.getLogger(MostrarPrestamos.class.getName()).log(Level.SEVERE, null, ex);
//             }
//         }
//};
//        thread.start();
//        }catch(Exception e){}
//    }
    
//    public static void escribirExcel()
//    {
//        try
//        {
//            //Se crea el libro Excel
//            WritableWorkbook workbook =
//                    Workbook.createWorkbook(new File("ejemplo.xls")); 
// 
//            //Se crea una nueva hoja dentro del libro
//            WritableSheet sheet =
//                    workbook.createSheet("HojaEjemplo", 0); 
// 
//            //Creamos celdas de varios tipos
//            sheet.addCell(new jxl.write.Number(0, 0, 1));
//            sheet.addCell(new jxl.write.Number(1, 0, 1.2));
//            sheet.addCell(new jxl.write.Label(2, 0, "ejemplo"));
//            sheet.addCell(new jxl.write.Boolean(3,0,true)); 
// 
//            //Creamos una celda de tipo fecha y la mostramos
//            //indicando un patón de formato
//            DateFormat customDateFormat =
//                    new DateFormat ("d/m/yy h:mm"); 
// 
//            WritableCellFormat dateFormat =
//                    new WritableCellFormat (customDateFormat); 
// 
//            sheet.addCell(new jxl.write.DateTime(4, 0, new Date(), dateFormat));
// 
//            //Escribimos los resultados al fichero Excel
//            workbook.write();
//            workbook.close(); 
// 
//            leerExcel();
// 
//            System.out.println("Ejemplo finalizado.");
//        }
//        catch (IOException ex)
//        {
//            System.out.println("Error al crear el fichero.");
//        }
//        catch (WriteException ex)
//        {
//            System.out.println("Error al escribir el fichero.");
//        }
//    }
    File archivo;
    JFileChooser selecArchivo = new JFileChooser();
    
    public static String exportar(File archivo, JTable tablaD){
        Workbook wb;
        
        String respuesta="No se realizo con exito la exportación.";
        
        int numFila=tablaD.getRowCount(), numColumna=tablaD.getColumnCount();
        if(archivo.getName().endsWith("xls")){
            wb = new HSSFWorkbook();
        }else{
            wb = new XSSFWorkbook();
        }
        Sheet hoja = wb.createSheet("Préstamos realizados");
        
        try {
            for (int i = -1; i < numFila; i++) {
                Row fila = hoja.createRow(i+1);
                for (int j = 0; j < numColumna; j++) {
                    Cell celda = fila.createCell(j);
                    if(i==-1){
                        celda.setCellValue(String.valueOf(tablaD.getColumnName(j)));
                    }else{
                        celda.setCellValue(String.valueOf(tablaD.getValueAt(i, j)));
                    }
                    wb.write(new FileOutputStream(archivo));
                }
            }
            respuesta="Exportación exitosa.";
            System.out.println(respuesta);
        } catch (Exception e) {
        }
        return respuesta;
    }
    
     public void agregarFiltro(){
        selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xls)", "xls"));
//        selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xlsx)", "xlsx"));
    }
     
     public void limpiarJTextField()
     {
         txtBuscarFecha.setText("");
         txtBuscarMaterial.setText("");
         txtBuscarNombre.setText("");
     }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        OpcionEliminar = new javax.swing.JPopupMenu();
        Eliminar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPrestamos = new javax.swing.JTable();
        txtBuscarNombre = new javax.swing.JTextField();
        btnNombre = new javax.swing.JButton();
        btnFecha = new javax.swing.JButton();
        txtBuscarFecha = new javax.swing.JTextField();
        btnMaterial = new javax.swing.JButton();
        txtBuscarMaterial = new javax.swing.JTextField();
        btnExportar = new javax.swing.JButton();
        barraProgreso = new javax.swing.JProgressBar();

        Eliminar.setText("Eliminar");
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });
        OpcionEliminar.add(Eliminar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setIconImage( new ImageIcon(getClass().getResource("/logotipo/fingerprint10.png")).getImage());
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registo de préstamos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(255, 255, 255)));

        tablaPrestamos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaPrestamos.setComponentPopupMenu(OpcionEliminar);
        jScrollPane1.setViewportView(tablaPrestamos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
        );

        txtBuscarNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarNombreKeyReleased(evt);
            }
        });

        btnNombre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logotipo/teacher4.png"))); // NOI18N
        btnNombre.setText("Buscar por nombre");
        btnNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNombreActionPerformed(evt);
            }
        });

        btnFecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logotipo/schedule3.png"))); // NOI18N
        btnFecha.setText("Buscar por fecha");
        btnFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFechaActionPerformed(evt);
            }
        });

        txtBuscarFecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarFechaKeyReleased(evt);
            }
        });

        btnMaterial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logotipo/pc6.png"))); // NOI18N
        btnMaterial.setText("Buscar por material");
        btnMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaterialActionPerformed(evt);
            }
        });

        txtBuscarMaterial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarMaterialKeyReleased(evt);
            }
        });

        btnExportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logotipo/microsoft-excel.png"))); // NOI18N
        btnExportar.setText("Exportar a Excel");
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBuscarNombre))
                .addGap(194, 194, 194)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnMaterial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBuscarMaterial))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtBuscarFecha)
                    .addComponent(btnFecha))
                .addGap(101, 101, 101)
                .addComponent(btnExportar)
                .addContainerGap())
            .addComponent(barraProgreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNombre)
                    .addComponent(btnFecha)
                    .addComponent(btnMaterial)
                    .addComponent(btnExportar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscarMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(barraProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        this.dispose();
        try {
            new Principal().setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(VerRegistrosProfesores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosing

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        // TODO add your handling code here:
        confirmacion();
    }//GEN-LAST:event_EliminarActionPerformed

    private void txtBuscarNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarNombreKeyReleased
        // TODO add your handling code here:
        //        mostrarPrestamo(txtBuscar.getText());
        buscarNombre(txtBuscarNombre.getText());
    }//GEN-LAST:event_txtBuscarNombreKeyReleased

    private void btnNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNombreActionPerformed
        // TODO add your handling code here:
        //        btnFecha.setEnabled(false);
        //        btnNombre.setEnabled(true);
        limpiarJTextField();
        txtBuscarNombre.setEnabled(true);
        txtBuscarFecha.setEnabled(false);
        txtBuscarMaterial.setEnabled(false);
        txtBuscarNombre.grabFocus();
        buscarNombre(txtBuscarNombre.getText());
    }//GEN-LAST:event_btnNombreActionPerformed

    private void txtBuscarFechaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarFechaKeyReleased
        // TODO add your handling code here:
        buscarFecha(txtBuscarFecha.getText());
    }//GEN-LAST:event_txtBuscarFechaKeyReleased

    private void btnFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFechaActionPerformed
        // TODO add your handling code here:
        //        btnNombre.setEnabled(false);
        //        btnFecha.setEnabled(true);
        limpiarJTextField();
        txtBuscarFecha.setEnabled(true);
        txtBuscarNombre.setEnabled(false);
        txtBuscarMaterial.setEnabled(false);
        txtBuscarFecha.grabFocus();
        buscarFecha(txtBuscarFecha.getText());
    }//GEN-LAST:event_btnFechaActionPerformed

    private void txtBuscarMaterialKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarMaterialKeyReleased
        // TODO add your handling code here:
        buscarMaterial(txtBuscarMaterial.getText());
    }//GEN-LAST:event_txtBuscarMaterialKeyReleased

    private void btnMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaterialActionPerformed
        // TODO add your handling code here:
        limpiarJTextField();
        txtBuscarMaterial.setEnabled(true);
        txtBuscarFecha.setEnabled(false);
        txtBuscarNombre.setEnabled(false);
        txtBuscarMaterial.grabFocus();
        buscarMaterial(txtBuscarMaterial.getText());
    }//GEN-LAST:event_btnMaterialActionPerformed

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        // TODO add your handling code here:
//        generarExcel();
//        exportar(archivo, tablaPrestamos);
        agregarFiltro();
        if(selecArchivo.showDialog(null, "Exportar")==JFileChooser.APPROVE_OPTION){
                archivo = selecArchivo.getSelectedFile();
                if(archivo.getName().endsWith("xls") || archivo.getName().endsWith("xlsx")){
                    JOptionPane.showMessageDialog(null, MostrarPrestamos.exportar(archivo, this.tablaPrestamos) + "\n Formato ."+ archivo.getName().substring(archivo.getName().lastIndexOf(".")+1));
                }else{
                    JOptionPane.showMessageDialog(null, "Elija un formato valido\ncon extensión: .xls");
                }
            }
    }//GEN-LAST:event_btnExportarActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(MostrarPrestamos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(MostrarPrestamos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(MostrarPrestamos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MostrarPrestamos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MostrarPrestamos().setVisible(true);
//            }
//        });
//    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Eliminar;
    private javax.swing.JPopupMenu OpcionEliminar;
    private javax.swing.JProgressBar barraProgreso;
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnFecha;
    private javax.swing.JButton btnMaterial;
    private javax.swing.JButton btnNombre;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaPrestamos;
    private javax.swing.JTextField txtBuscarFecha;
    private javax.swing.JTextField txtBuscarMaterial;
    private javax.swing.JTextField txtBuscarNombre;
    // End of variables declaration//GEN-END:variables
}
