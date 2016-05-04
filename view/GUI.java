/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.sun.glass.events.KeyEvent;
import dao.CaseHandler;
import dao.CostumerHandler;
import dao.DBHandler;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.KeyCode;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import model.Case;
import model.Costumer;

/**
 *
 * @author Simon
 */
public class GUI extends javax.swing.JFrame {
    ArrayList<Case> cases;
    ArrayList<CasePanel> casePanels;
    Case c;
    Calendar cal;
    CardLayout cl;
    String name;
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
        //Hvis det ikke er admin brug de 2 linjer
        findCostumerSuggestArea.setVisible(false);
        int n = tabbedPane.indexOfTab("Admin");
        tabbedPane.setEnabledAt(2, false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width, screenSize.height);
        
        cal = Calendar.getInstance();
        //c = new Case(5, "Sag", "Objekt", cal.getTime());
        cl = (CardLayout) cardPanel.getLayout();
        //cl.addLayoutComponent(newCasePanel, "New case");
        try {
            cases = CaseHandler.getInstance().getCases();
            for (int i = 0; i < cases.size(); i++) {
                //Skal laves til en metode, der kaldes hver gang, at man opretter nye CasePanels
                CasePanel cp = new CasePanel(cases.get(i), this);
                cp.setBounds(0, 111 * i, 303, 110);
                cp.setBorder(BorderFactory.createLineBorder(Color.black));
                scrollCasePanel.add(cp);
            }
            repaint();
            revalidate();
        } catch (SQLException ex) {
            //Det bliver formentlig efter en login skærm
        }

    }
    
    
     public CardLayout getCl() {
        return cl;
    }

    public JPanel getCardPanel() {
        return cardPanel;
    }

    public void setC(Case c) {
        this.c = c;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cardPanel = new javax.swing.JPanel();
        tabbedPane = new javax.swing.JTabbedPane();
        caseHandlingPanel = new javax.swing.JPanel();
        caseNmbSField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        caseNameSField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        ownerSField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        caseSearchButton = new javax.swing.JButton();
        objectTypeSField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        scroller = new javax.swing.JScrollPane();
        scrollCasePanel = new javax.swing.JPanel();
        newCasePanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        newCaseNameField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        newCaseContactField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        newCaseTlfNumberField = new javax.swing.JTextField();
        newCaseEmailField = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        newCaseAdressField = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        newCaseZipCodeField = new javax.swing.JTextField();
        costumerTypeBox = new javax.swing.JComboBox();
        existingCostumerCheckBox = new javax.swing.JCheckBox();
        findCostumerField = new javax.swing.JTextField();
        objectTypeBox = new javax.swing.JComboBox();
        newObjectButton = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        objectAmountField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        findCostumerSuggestArea = new javax.swing.JTextArea();
        adminPanel = new javax.swing.JPanel();
        createEmployeeButton = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        dbUserField = new javax.swing.JTextField();
        dbPasswordField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        dbUrlField = new javax.swing.JTextField();
        dbInfoCheckBox = new javax.swing.JCheckBox();
        changeDbButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cardPanel.setLayout(new java.awt.CardLayout());

        jLabel1.setText("Kons nr");

        jLabel2.setText("Sagsnavn");

        jLabel3.setText("Ejer");

        caseSearchButton.setText("Søg");
        caseSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caseSearchButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("Genstandstype");

        javax.swing.GroupLayout scrollCasePanelLayout = new javax.swing.GroupLayout(scrollCasePanel);
        scrollCasePanel.setLayout(scrollCasePanelLayout);
        scrollCasePanelLayout.setHorizontalGroup(
            scrollCasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 661, Short.MAX_VALUE)
        );
        scrollCasePanelLayout.setVerticalGroup(
            scrollCasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 524, Short.MAX_VALUE)
        );

        scroller.setViewportView(scrollCasePanel);

        javax.swing.GroupLayout caseHandlingPanelLayout = new javax.swing.GroupLayout(caseHandlingPanel);
        caseHandlingPanel.setLayout(caseHandlingPanelLayout);
        caseHandlingPanelLayout.setHorizontalGroup(
            caseHandlingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(caseHandlingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(caseHandlingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(caseHandlingPanelLayout.createSequentialGroup()
                        .addGroup(caseHandlingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(objectTypeSField, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                            .addComponent(ownerSField)
                            .addComponent(caseNameSField)
                            .addComponent(caseNmbSField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(caseHandlingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)))
                    .addComponent(caseSearchButton))
                .addGap(18, 18, 18)
                .addComponent(scroller)
                .addContainerGap())
        );
        caseHandlingPanelLayout.setVerticalGroup(
            caseHandlingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(caseHandlingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(caseHandlingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(caseHandlingPanelLayout.createSequentialGroup()
                        .addGroup(caseHandlingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(caseNmbSField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(caseHandlingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(caseNameSField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(caseHandlingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ownerSField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(caseHandlingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(objectTypeSField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(20, 20, 20)
                        .addComponent(caseSearchButton)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(scroller)))
        );

        tabbedPane.addTab("Sagsliste", caseHandlingPanel);

        jLabel5.setFont(new java.awt.Font("LiSong Pro", 0, 36)); // NOI18N
        jLabel5.setText("Opret ny Sag");

        jLabel6.setText("Ny kunde");

        jLabel7.setText("Navn");

        jLabel8.setText("Kontaktperson");

        jLabel9.setText("Tlf. Nummer");

        jLabel10.setText("Email");

        jLabel16.setText("Adresse");

        jLabel17.setText("Postnummer");

        costumerTypeBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kunde Type", "Privat", "Museum", "Offentlig" }));

        existingCostumerCheckBox.setText("Eksisterende kunde");

        findCostumerField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                findCostumerFieldKeyReleased(evt);
            }
        });

        objectTypeBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Genstands type", "Maleri", "Arkæologi" }));

        newObjectButton.setText("Ny Genstand");

        jLabel18.setText("Antal genstande:");

        findCostumerSuggestArea.setEditable(false);
        findCostumerSuggestArea.setColumns(20);
        findCostumerSuggestArea.setRows(5);
        jScrollPane1.setViewportView(findCostumerSuggestArea);

        javax.swing.GroupLayout newCasePanelLayout = new javax.swing.GroupLayout(newCasePanel);
        newCasePanel.setLayout(newCasePanelLayout);
        newCasePanelLayout.setHorizontalGroup(
            newCasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newCasePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(newCasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newCasePanelLayout.createSequentialGroup()
                        .addGroup(newCasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(newCasePanelLayout.createSequentialGroup()
                                .addGroup(newCasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(newCaseNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(newCasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(newCaseTlfNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addGroup(newCasePanelLayout.createSequentialGroup()
                                .addGroup(newCasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(newCaseContactField, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(newCasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(newCaseEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(newCasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newCasePanelLayout.createSequentialGroup()
                                .addGroup(newCasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel16)
                                    .addComponent(newCaseAdressField, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(newCaseZipCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(66, 66, 66))
                            .addComponent(costumerTypeBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newCasePanelLayout.createSequentialGroup()
                        .addComponent(existingCostumerCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(newObjectButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(objectTypeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(newCasePanelLayout.createSequentialGroup()
                        .addGroup(newCasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(findCostumerField, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))
                        .addGap(445, 445, 445)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(objectAmountField, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)))
                .addContainerGap())
        );
        newCasePanelLayout.setVerticalGroup(
            newCasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newCasePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(newCasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(costumerTypeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(newCasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newCasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newCaseNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newCaseTlfNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newCaseAdressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(newCasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newCasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newCaseContactField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newCaseEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newCaseZipCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newCasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(existingCostumerCheckBox)
                    .addComponent(objectTypeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newObjectButton))
                .addGroup(newCasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(newCasePanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(findCostumerField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(newCasePanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(newCasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(objectAmountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))))
                .addContainerGap(122, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Opret sag", newCasePanel);

        createEmployeeButton.setText("Opret medarbejder");

        jLabel11.setFont(new java.awt.Font("LiSong Pro", 0, 36)); // NOI18N
        jLabel11.setText("Administrator");

        jLabel12.setFont(new java.awt.Font("LiSong Pro", 0, 24)); // NOI18N
        jLabel12.setText("Database oplysninger");

        jLabel13.setText("Bruger");

        jLabel14.setText("Kode");

        jLabel15.setText("URL");

        dbInfoCheckBox.setText("Ja, jeg ved, at jeg ændrer i databasens oplysninger");

        changeDbButton.setText("Ændre Databasen");
        changeDbButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeDbButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout adminPanelLayout = new javax.swing.GroupLayout(adminPanel);
        adminPanel.setLayout(adminPanelLayout);
        adminPanelLayout.setHorizontalGroup(
            adminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminPanelLayout.createSequentialGroup()
                .addComponent(createEmployeeButton)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(adminPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(adminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 832, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(adminPanelLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel13)
                    .addGroup(adminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(dbUrlField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                        .addComponent(dbPasswordField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(dbUserField, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(dbInfoCheckBox)
                    .addComponent(changeDbButton)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        adminPanelLayout.setVerticalGroup(
            adminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(37, 37, 37)
                .addGroup(adminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(adminPanelLayout.createSequentialGroup()
                        .addComponent(createEmployeeButton)
                        .addGap(115, 115, 115)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addGap(4, 4, 4)
                .addComponent(dbUserField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dbPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dbUrlField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(dbInfoCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(changeDbButton)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Admin", adminPanel);

        cardPanel.add(tabbedPane, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void caseSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caseSearchButtonActionPerformed
        try {
            scrollCasePanel.removeAll();
            cases = CaseHandler.getInstance().searchCases(Integer.parseInt(caseNmbSField.getText()), caseNameSField.getText());
            for (int i = 0; i < cases.size(); i++) {
                CasePanel cp = new CasePanel(cases.get(i), this);
                cp.setBounds(0, 111 * i, 303, 110);
                cp.setBorder(BorderFactory.createLineBorder(Color.black));
                scrollCasePanel.add(cp);
            }
            repaint();
            revalidate();
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(rootPane, ex, title, HEIGHT);
            //Eller label med rød tekst
        }
    }//GEN-LAST:event_caseSearchButtonActionPerformed

    private void changeDbButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeDbButtonActionPerformed
        DBHandler.getInstance().setConfig(dbUrlField.getText(), dbUserField.getText(), dbPasswordField.getText());
    }//GEN-LAST:event_changeDbButtonActionPerformed

    private void findCostumerFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_findCostumerFieldKeyReleased
        name = findCostumerField.getText();
        findCostumerSuggestArea.setVisible(true);
        try {
            findCostumerSuggestArea.setText("");
            ArrayList<Costumer> costumers = CostumerHandler.getInstance().searchCostumerName(name);
            for (Costumer c : costumers) {
                findCostumerSuggestArea.append(c.getCostumerName() + "\n");
            }  
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//             findCostumerField.setText(c.getCostumerName());
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_findCostumerFieldKeyReleased

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
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel adminPanel;
    private javax.swing.JPanel cardPanel;
    private javax.swing.JPanel caseHandlingPanel;
    private javax.swing.JTextField caseNameSField;
    private javax.swing.JTextField caseNmbSField;
    private javax.swing.JButton caseSearchButton;
    private javax.swing.JButton changeDbButton;
    private javax.swing.JComboBox costumerTypeBox;
    private javax.swing.JButton createEmployeeButton;
    private javax.swing.JCheckBox dbInfoCheckBox;
    private javax.swing.JTextField dbPasswordField;
    private javax.swing.JTextField dbUrlField;
    private javax.swing.JTextField dbUserField;
    private javax.swing.JCheckBox existingCostumerCheckBox;
    private javax.swing.JTextField findCostumerField;
    private javax.swing.JTextArea findCostumerSuggestArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField newCaseAdressField;
    private javax.swing.JTextField newCaseContactField;
    private javax.swing.JTextField newCaseEmailField;
    private javax.swing.JTextField newCaseNameField;
    private javax.swing.JPanel newCasePanel;
    private javax.swing.JTextField newCaseTlfNumberField;
    private javax.swing.JTextField newCaseZipCodeField;
    private javax.swing.JButton newObjectButton;
    private javax.swing.JTextField objectAmountField;
    private javax.swing.JComboBox objectTypeBox;
    private javax.swing.JTextField objectTypeSField;
    private javax.swing.JTextField ownerSField;
    private javax.swing.JPanel scrollCasePanel;
    private javax.swing.JScrollPane scroller;
    private javax.swing.JTabbedPane tabbedPane;
    // End of variables declaration//GEN-END:variables
}
