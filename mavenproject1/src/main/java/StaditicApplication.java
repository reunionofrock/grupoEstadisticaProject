
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class StaditicApplication extends javax.swing.JFrame {

    /**
     * Creates new form StaditicApplication
     */
    private JTextField dataCountField;
    private JTextArea dataInputArea;
    private JTextArea resultArea;
    private List<Double> data;

    public StaditicApplication() {
        setTitle("Calculadora de Medidas Estadísticas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Paso 1: Solicitar la cantidad de datos al usuario
        panel.add(new JLabel("Ingrese la cantidad de datos que utilizarán:"));
        dataCountField = new JTextField();
        panel.add(dataCountField);

        JButton submitButton = new JButton("Ingresar Datos");
        panel.add(submitButton);

        dataInputArea = new JTextArea(10, 50);
        dataInputArea.setLineWrap(true);
        dataInputArea.setWrapStyleWord(true);
        panel.add(new JScrollPane(dataInputArea));

        resultArea = new JTextArea(10, 50);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setEditable(false);
        panel.add(new JScrollPane(resultArea));

        JButton calculateButton = new JButton("Calcular Medidas");
       // panel.add(calculateButton);

        JButton pdfButton = new JButton("Descargar PDF");
        //panel.add(pdfButton);
        
        JButton clearButton = new JButton("Limpiar");
        //panel.add(clearButton);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(calculateButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10,0)));
        buttonPanel.add(pdfButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10,0)));
        buttonPanel.add(clearButton);
        
        panel.add(buttonPanel);
        
        add(panel);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dataCount = Integer.parseInt(dataCountField.getText());
                data = new ArrayList<>();
                for (int i = 0; i < dataCount; i++) {
                    String input = JOptionPane.showInputDialog("Ingrese el dato " + (i + 1) + ":");
                    data.add(Double.parseDouble(input));
                }
                dataInputArea.setText(data.toString());
            }
        });

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dao myDao = new dao();
                if (data != null && !data.isEmpty()) {
                    boolean isGrouped = data.size() > 15;
                    double mean = 0.0;
                    double median = 0.0;
                    double mode =0.0;
                    double variance = 0.0;
                    

                    if (isGrouped) {
                        // Lógica para datos agrupados
                        mean = myDao.calculateGroupedMean(data);
                        median = myDao.calculateGroupedMedian(data);
                        mode = myDao.calculateGroupedMode(data);
                        variance = myDao.calculateGroupedVariance(data, mean);
                    } else {
                        // Lógica para datos simples
                        mean = myDao.calculateMean(data);
                        median = myDao.calculateMedian(data);
                        mode = myDao.calculateMode(data);
                        variance = myDao.calculateVariance(data, mean);
                    }
                    double standardDeviation = Math.sqrt(variance);

                    String solution = "Cantidad de datos: " + data.size() + "\n"
                            + "Datos: " + data + "\n"
                            + "Media: " + mean + "\n"
                            + "Mediana: " + median + "\n"
                            + "Moda: " + mode + "\n"
                            + "Varianza: " + variance + "\n"
                            + "Desviación estándar: " + standardDeviation;

                    resultArea.setText(solution);
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese los datos primero.");
                }
            }
        });

        pdfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (resultArea.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, calcule las medidas primero.");
                    return;
                }

                String fileName = JOptionPane.showInputDialog("Ingrese el nombre del archivo PDF (sin extensión):");
                try {
                    Document document = new Document();
                    PdfWriter.getInstance(document, new FileOutputStream(fileName + ".pdf"));
                    // PdfWriter writer = new PdfWriter(fileName + ".pdf");
                    //com.itextpdf.kernel.pdf.PdfDocument pdfDoc = new com.itextpdf.kernel.pdf.PdfDocument(writer);

                    document.open();
                    document.add(new Paragraph(resultArea.getText()));

                    document.close();
                    
                    //El archivo se guarda en la carpeta raiz
                    JOptionPane.showMessageDialog(null, "El archivo PDF ha sido generado exitosamente.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al generar el archivo PDF.");
                }
            }
        });
        
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataCountField.setText("");
                dataInputArea.setText("");
                resultArea.setText("");
                data = null;
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StaditicApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StaditicApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StaditicApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StaditicApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StaditicApplication().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
