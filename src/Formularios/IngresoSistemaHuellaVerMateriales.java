/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import BD.ConexionBD;
import static Formularios.Principal.TEMPLATE_PROPERTY;
import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPDataAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPErrorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPErrorEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPSensorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Alex
 */
public class IngresoSistemaHuellaVerMateriales extends javax.swing.JFrame {
    
    public class PresionarTecla extends KeyAdapter {
 
//      public void keyPressed(KeyEvent ke) {
//          if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
//              //btnAceptarActionPerformed(null);
//              String usuario = txtUsuario.getText();
//              String contraseña = new String(txtContraseña.getPassword());
//              acceder(usuario, contraseña);
//              
//          }
//      }
    }

    /**
     * Creates new form IngresoAlSistema
     */
    public IngresoSistemaHuellaVerMateriales() {
        
        initComponents();
        this.setTitle("Verificación de identidad para modificar los registros");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        btnIngresar.setEnabled(false);
        txtArea.setEditable(false);
    }
    
    //Creamos la conexión con la Base de Datos.
    ConexionBD cc = new ConexionBD();
    Connection cn = cc.conectar();
    
    void acceder(String usuario, String contraseña) throws UnsupportedLookAndFeelException
    {
       String cap = "";
       String sql = "SELECT usuario, password FROM usuario WHERE usuario='"+usuario+"' && password='"+contraseña+"'";
       
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            if(rs.next())
            {
                JOptionPane.showMessageDialog(null, "Bienvenido.");
//                Principal ventanaPrincipal = new Principal();
//                ventanaPrincipal.setVisible(true);
                new RegistroProfesor().show(true);
                this.dispose();
            }else
            {
//                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta.");
                JOptionPane.showMessageDialog(IngresoSistemaHuellaVerMateriales.this, "El usuario o la contraseña incorrectos.", "Error al intentar ingresar.", JOptionPane.ERROR_MESSAGE);
            }
            
//            while(rs.next())
//            {
//                cap = rs.getString("tipousuario");
//            }
//            if(cap.equals("1"))
//            {
//                  this.setVisible(false);
//                    JOptionPane.showMessageDialog(null, "Bienvenido.");
//                     RegistroProfesor ventanaRegistroProfesor = new RegistroProfesor();
//                    ventanaRegistroProfesor.setVisible(true);
//                    //ingreso.pack();
//                     //ventanaadmin.lblusu.setText(usuario);
//        
//                
//            }
//            if(cap.equals("0"))
//            {
//            this.setVisible(false);
//                    JOptionPane.showMessageDialog(null, "Bienvenido");
//                     ventanacliente ingresos = new ventanacliente();
//                    ingresos.setVisible(true);
//                    ingresos.pack();
//                     ventanacliente.lblconectado.setText(usuario);
//            }
//            if((!cap.equals("Administrador"))&& (!cap.equals("Invitado")))
//            {
//                JOptionPane.showMessageDialog(this, "No existe sus datos");
//            }
        } catch (SQLException ex) {
            Logger.getLogger(IngresoSistemaHuellaVerMateriales.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        }
    
    //Varible que permite iniciar el dispositivo de lector de huella conectado
    // con sus distintos metodos.
    private DPFPCapture Lector = DPFPGlobal.getCaptureFactory().createCapture();

    //Varible que permite establecer las capturas de la huellas, para determina sus caracteristicas
    // y poder estimar la creacion de un template de la huella para luego poder guardarla
    private DPFPEnrollment Reclutador = DPFPGlobal.getEnrollmentFactory().createEnrollment();

    //Esta variable tambien captura una huella del lector y crea sus caracteristcas para auntetificarla
    // o verificarla con alguna guardada en la BD
    private DPFPVerification Verificador = DPFPGlobal.getVerificationFactory().createVerification();

    //Variable que para crear el template de la huella luego de que se hallan creado las caracteriticas
    // necesarias de la huella si no ha ocurrido ningun problema
    private DPFPTemplate template;
    public static String TEMPLATE_PROPERTY = "template";
    
    protected void Iniciar(){
   Lector.addDataListener(new DPFPDataAdapter() {
    @Override public void dataAcquired(final DPFPDataEvent e) {
    SwingUtilities.invokeLater(new Runnable() {	public void run() {
//    EnviarTexto("Huella Digital capturada.");
    ProcesarCaptura(e.getSample());
    }});}
   });

   Lector.addReaderStatusListener(new DPFPReaderStatusAdapter() {
    @Override public void readerConnected(final DPFPReaderStatusEvent e) {
    SwingUtilities.invokeLater(new Runnable() {	public void run() {
    //EnviarTexto("El Sensor de Huella Digital esta Activado o Conectado");
//    labelConectado.setVisible(true);
//    labelDesconectado.setVisible(false);
    }});}
    @Override public void readerDisconnected(final DPFPReaderStatusEvent e) {
    SwingUtilities.invokeLater(new Runnable() {	public void run() {
    //EnviarTexto("El Sensor de Huella Digital esta Desactivado o no Conectado");
//    labelConectado.setVisible(false);
//    labelDesconectado.setVisible(true);
    }});}
   });

   Lector.addSensorListener(new DPFPSensorAdapter() {
    @Override public void fingerTouched(final DPFPSensorEvent e) {
    SwingUtilities.invokeLater(new Runnable() {	public void run() {
    //EnviarTexto("El dedo ha sido colocado sobre el Lector de Huella");
//    labelVerde.setVisible(true);
//    labelRojo.setVisible(false);    
    }});}
    @Override public void fingerGone(final DPFPSensorEvent e) {
    SwingUtilities.invokeLater(new Runnable() {	public void run() {
    //EnviarTexto("El dedo ha sido quitado del Lector de Huella");
//    labelVerde.setVisible(false);
//    labelRojo.setVisible(true); 
    }});}
   });

   Lector.addErrorListener(new DPFPErrorAdapter(){
    public void errorReader(final DPFPErrorEvent e){
    SwingUtilities.invokeLater(new Runnable() {  public void run() {
    EnviarTexto("Error: "+e.getError());
    }});}
   });
}
    
    public void limpiarJTextArea()
{
    //Limpiamos el área de impresión de los datos.
    txtArea.setText("");
}

 public DPFPFeatureSet featuresinscripcion;
 public DPFPFeatureSet featuresverificacion;

 public  void ProcesarCaptura(DPFPSample sample)
 {
 // Procesar la muestra de la huella y crear un conjunto de características con el propósito de inscripción.
 featuresinscripcion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);

 // Procesar la muestra de la huella y crear un conjunto de características con el propósito de verificacion.
 featuresverificacion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);

 // Comprobar la calidad de la muestra de la huella y lo añade a su reclutador si es bueno
 if (featuresinscripcion != null){
     // Dibuja la huella dactilar capturada.
     Image image = CrearImagenHuella(sample);
     DibujarHuella(image);
//     EnviarTexto("Huella digital capturada.");
//     System.out.println("Se capturó una huella.");
//     EnviarTexto("Se capturó una huella digital.");
 }
     
}
 
 public void close(){
        if (JOptionPane.showConfirmDialog(rootPane, "¿Realmente desea salir del sistema?",
                "Salir del sistema.", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            System.exit(0);
    }         

 public  DPFPFeatureSet extraerCaracteristicas(DPFPSample sample, DPFPDataPurpose purpose){
     DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
     try {
      return extractor.createFeatureSet(sample, purpose);
     } catch (DPFPImageQualityException e) {
      return null;
     }
}

  public  Image CrearImagenHuella(DPFPSample sample) {
	return DPFPGlobal.getSampleConversionFactory().createImage(sample);
}

  public void DibujarHuella(Image image) {
        lblImagenHuellaAdmin.setIcon(new ImageIcon(
        image.getScaledInstance(lblImagenHuellaAdmin.getWidth(), lblImagenHuellaAdmin.getHeight(), Image.SCALE_DEFAULT)));
        repaint();
        limpiarJTextArea();
        try {
            // TODO add your handling code here:
            identificarHuella();
        } catch (IOException ex) {
            Logger.getLogger(IngresoSistemaHuellaVerMateriales.class.getName()).log(Level.SEVERE, null, ex);
        }
 }

public  void EstadoHuellas(){
	//EnviarTexto("Muestra de Huellas Necesarias para Guardar Template "+ Reclutador.getFeaturesNeeded());
//        labelListo.setEnabled(false);
        
        if(Reclutador.getFeaturesNeeded()==4)
        {
//            labelCapturando1.setEnabled(false);
//            labelCapturando2.setEnabled(false);
//            labelCapturando3.setEnabled(false);
        }
        
        if(Reclutador.getFeaturesNeeded()==3)
        {
//            labelCapturando1.setEnabled(true);
//            labelCapturando2.setEnabled(false);
//            labelCapturando3.setEnabled(false);
        }
        
        if(Reclutador.getFeaturesNeeded()==2)
        {
//            labelCapturando1.setEnabled(true);
//            labelCapturando2.setEnabled(true);
//            labelCapturando3.setEnabled(false);
        }
        
        if(Reclutador.getFeaturesNeeded()==1)
        {
//            labelCapturando1.setEnabled(true);
//            labelCapturando2.setEnabled(true);
//            labelCapturando3.setEnabled(true);
        }
}

public void EnviarTexto(String string) {
        txtArea.append(string + "\n");
}

//Se inicializa el uso del lector de huellas.
public  void start(){
	Lector.startCapture();
	//EnviarTexto("Lector de huella dactilar listo.");
//        EnviarTexto("Se necesitan tomar 4 muestras periódicas de su huella.");
//        EnviarTexto("Para elegir la de mejor calidad y guardarla.\n");
//        labelVerde.setVisible(false);
}

//Se desactiva el uso del lector de huellas.
public  void stop(){
        Lector.stopCapture();
//        EnviarTexto("Lector de huella dactilar desactivado. ");
//        labelVerde.setVisible(false);
//        labelRojo.setVisible(true);
}

    public DPFPTemplate getTemplate() {
        return template;
    }

    public void setTemplate(DPFPTemplate template) {
        DPFPTemplate old = this.template;
	this.template = template;
	firePropertyChange(TEMPLATE_PROPERTY, old, template);
    }
    
    /**
  * Identifica a una persona registrada por medio de su huella digital
  */
  public void identificarHuella() throws IOException{
      
      //Creamos la instancia 'con' de tipo ConexionBD
      ConexionBD con = new ConexionBD();
      
    //limpiarJTextArea();
      
     try {
       //Establece los valores para la sentencia SQL
       Connection c = con.conectar();

       PreparedStatement identificarStmt;
       identificarStmt = c.prepareStatement("SELECT HuellaUsuario, TipoUsuario FROM Usuario");
       //Query
       ResultSet rs = identificarStmt.executeQuery();

       //Si se encuentra el nombre en la base de datos
       while(rs.next())
       {
        //Lee la plantilla de la base de datos
        byte templateBuffer[] = rs.getBytes("HuellaUsuario");
//        String nombre = rs.getString("Nombre");
//        String apellidos = rs.getString("Apellidos");
        Integer tipo = rs.getInt("TipoUsuario");
        //Crea una nueva plantilla a partir de la guardada en la base de datos
        DPFPTemplate referenceTemplate = DPFPGlobal.getTemplateFactory().createTemplate(templateBuffer);
        //Envia la plantilla creada al objeto contendor de Template del componente de huella digital
        setTemplate(referenceTemplate);

        // Compara las caracteriticas de la huella recientemente capturda con la
        // alguna plantilla guardada en la base de datos que coincide con ese tipo
        DPFPVerificationResult result = Verificador.verify(featuresverificacion, getTemplate());

        //compara las plantilas (actual vs bd)
        //Si encuentra correspondencia dibuja el mapa
        //e indica el nombre de la persona que coincidió.
        if (result.isVerified() && tipo == 1){
            //Tipo de usuario ADMINISTRADOR
             btnIngresar.setEnabled(true);
             btnIngresar.grabFocus();
             System.out.println("La huella si esta registrada");
             EnviarTexto("Huella de administrador.\nPuede registrar más usuarios y profesores.");
             return;
        }else if(result.isVerified() && tipo == 0){
            //Tipo de usuario TRABAJADOR
            btnIngresar.setEnabled(false);
//            btnIngresar.grabFocus();
            System.out.println("La huella está registrada.\nPero no tiene permisos de administrador.");
            EnviarTexto("Huella de usuario.\nNo tiene permisos para registrar.");
            return;
        }
        
       }//fin while
       //Si no encuentra alguna huella correspondiente al nombre lo indica con un mensaje
       //JOptionPane.showMessageDialog(null, "No existe ningún registro que coincida con la huella", "Verificacion de Huella", JOptionPane.ERROR_MESSAGE);
       String error = "\nLa huella no está registrada.";
       txtArea.setText(error);
       btnIngresar.setEnabled(false);
//         System.out.println(error);
       setTemplate(null);
       } catch (SQLException e) {
       //Si ocurre un error lo indica en la consola
       System.err.println("Error al identificar huella dactilar."+e.getMessage());
       JOptionPane.showMessageDialog(IngresoSistemaHuellaVerMateriales.this,"Error al identificar la huella dactilar. \nPresione Aceptar para continuar.");
       }finally{
       con.desconectar();
       }
   }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        lblImagenHuellaAdmin = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnIngresar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setIconImage( new ImageIcon(getClass().getResource("/logotipo/fingerprint10.png")).getImage());
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información de la captura", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(255, 255, 255)));

        txtArea.setColumns(20);
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Huella del usuario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(255, 255, 255)));

        lblImagenHuellaAdmin.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImagenHuellaAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImagenHuellaAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(255, 255, 255)));

        btnIngresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logotipo/login18.png"))); // NOI18N
        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logotipo/cancel30.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnIngresar)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCancelar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnIngresar)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 25, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
//        stop();
//        Principal ventanaPrincipal = new Principal();
//        ventanaPrincipal.show();
//        this.dispose();
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        stop();
        try {
            new Principal().setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(VerRegistrosMateriales.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        Iniciar();
        start();
        EstadoHuellas();
    }//GEN-LAST:event_formWindowOpened

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        stop();
        //        try {
            //            new RegistroProfesor().show(true);
            //        } catch (UnsupportedLookAndFeelException ex) {
            //            Logger.getLogger(IngresoSistemaHuellaRegistroUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            //        }
        new RegistroMaterial().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        try {
            new Principal().setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(VerRegistrosMateriales.class.getName()).log(Level.SEVERE, null, ex);
        }
        stop();
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblImagenHuellaAdmin;
    private javax.swing.JTextArea txtArea;
    // End of variables declaration//GEN-END:variables
}
