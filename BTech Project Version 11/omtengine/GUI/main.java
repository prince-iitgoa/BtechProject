package GUI;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javapy.CreateMid;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;

import org.jfree.ui.Spinner;

import omtengine.OpenMusicTranscriptor;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author DELL
 */
public class main extends javax.swing.JFrame {
    private Object jFileChooser;
    String sname;
    String path;
    String volume;
    String sharp;
    String flat;
    String transpose;
    String zoom;
    String tempo;
    String opfile;
    int blknwht;
    int typeflag;
    
      
   /** URL iconURL = getClass().getResource("/GUI/src/Music-icon.png");
// iconURL is null when not found
ImageIcon icon = new ImageIcon(iconURL);
frame.setIconImage(icon.getImage());
     * Creates new form main
     */
     BufferedImage targetimg = null;
     BufferedImage oriimg = null;
     public static final int basesize=128;////
    public main() {
          
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
 //   @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    	 
        jFileChooser1 = new javax.swing.JFileChooser();
        jFrame1 = new javax.swing.JFrame();
        jToolBar1 = new javax.swing.JToolBar();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollBar1 = new javax.swing.JScrollBar();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        //jSpinner3 = new javax.swing.JSpinner();
        //sharp
        //SpinnerNumberModel jSpinner3 = new SpinnerNumberModel(0.0, 0.0, 7.0, 1.0);  
        jSpinner3 = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 7.0, 1.0));
        /////////////
        jLabel9 = new javax.swing.JLabel();
        //jSpinner4 = new javax.swing.JSpinner();
        //flat
        //SpinnerNumberModel jSpinner4 = new SpinnerNumberModel(0.0, 0.0, 7.0, 1.0);  
        jSpinner4 = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 7.0, 1.0));
        ////////
        jLabel4 = new javax.swing.JLabel();
        
        blknwht=0;
        typeflag=0;
       
        //transpose
        jSpinner2 = new JSpinner(new SpinnerNumberModel(0.0, -10.0, 10.0, 1.0));//default value,lower bound,upper bound,increment by

        jLabel5 = new javax.swing.JLabel();
        jSlider2 = new javax.swing.JSlider(JSlider.HORIZONTAL,0,100,50);
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSlider1 = new javax.swing.JSlider(JSlider.HORIZONTAL,0,100,100);
        jLabel2 = new javax.swing.JLabel();
        //tempo
        jSpinner1 = new JSpinner(new SpinnerNumberModel(50.0, 1.0, 225.0, 1.0));
        /////////
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Vivace 0.2.02(DELTA)");
        setBackground(new java.awt.Color(51, 204, 255));

        jToolBar1.setRollover(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1005, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );

        jToolBar1.add(jPanel2);

        jScrollPane1.setViewportView(jScrollBar1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 143, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(200, 204, 200));

        jLabel7.setBackground(new java.awt.Color(200, 200, 200));
        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
      //  jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("Scale     :");

        jLabel8.setBackground(new java.awt.Color(200, 200, 200));
        jLabel8.setText("SHARP");
        jSpinner3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner3StateChanged(evt);
            }
        });
        

        jLabel9.setText("FLAT");
        jSpinner4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner4StateChanged(evt);
            }
        });

        jLabel4.setText("TRANSPOSE");
        jSpinner2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner2StateChanged(evt);
            }
        });

        jLabel5.setText("VOLUME 0");
        jLabel6.setText("100");
        jSlider2.setBackground(new java.awt.Color(51, 204, 255));
        jSlider2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider2StateChanged(evt);
            }
        });
        
        
        

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSlider2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(200, 204, 200));

        jLabel1.setText("ZOOM +/-");
        jSlider1.setBackground(new java.awt.Color(200, 204, 215));
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });
        


        jLabel2.setText("TEMPO");
        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });

        jLabel3.setText("OPEN  MUSIC  SCORE");

      //  jButton1.setBackground(new java.awt.Color(200, 204, 200));
        jButton1.setText("Open File");
        jButton1.setActionCommand("Open File");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(0));
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.setText(sname);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel10.setText("Output FileName");

        jButton2.setText("Recognise Score");
        jButton2.setActionCommand("Recognise Score");
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(0));
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt1) {
            	if(sname==null)
            	{
            		jButton1ActionPerformed(evt1);
            		if(sname!=null)
            			jButton2ActionPerformed(evt1);
            	}
            	else
            		jButton2ActionPerformed(evt1);
            	/*if(evt1.getSource()==jButton2){
         		  
				//OpenMusicTranscriptor omt= new OpenMusicTranscriptor(sname);
            		System.out.print(sname);
         		   
         		   //OpenMusicTranscriptor(filename);
         	    }*/
            	
            }
        });
        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(jTextField1))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(4, 4, 4)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                        .addComponent(jSlider1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jMenuBar1.setBackground(new java.awt.Color(255, 153, 255));
        jMenuBar1.setOpaque(false);

        jMenu1.setText("File");

        jMenuItem1.setText("Open");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Save");
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Exit");
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem4.setText("Recognise Score");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	if(sname==null)
            	{
            		jButton1ActionPerformed(evt);
            		if(sname!=null)
            			jButton2ActionPerformed(evt);
            	}
            	else
            		jButton2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);
        jMenuItem4.setText("Recognise Score");
        
        jMenu2.add(jMenuItem6);
        jMenuItem6.setText("Single-Hand");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		if(sname==null)
            	{
            		jButton1ActionPerformed(evt);
            		if(sname!=null)
            			typeflag=1;
            	}
            	else			//no need
            		typeflag=0;
            	
            }
        	});
        
        jMenu2.add(jMenuItem7);
        jMenuItem7.setText("Both Hand");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		if(sname==null)
            	{
            		jButton1ActionPerformed(evt);
            		if(sname!=null)
            			typeflag=0;
            	}
            	else
            		typeflag=1;
            	
            }
        	});
        
        jMenu2.add(jMenuItem8);
        jMenuItem8.setText("Black and White");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	if(sname==null)
            	{
            		jButton1ActionPerformed(evt);
            		if(sname!=null)
            			blknwht=1;
            	}
            	else
            		blknwht=1;
            	
            }
        });
        

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Play");
        jMenuItem5.setText("Play");
        jMenu3.add(jMenuItem5);
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	try {
					new CreateMid(opfile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        jMenuBar1.add(jMenu2);
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /*********************************/
    /*********************************/
     
    
    
    protected void jButton2ActionPerformed(ActionEvent evt1) {
    	String tf=null;
    	JPanel panel = new JPanel(new GridLayout(0, 1));
    	
    	JRadioButton b1=new JRadioButton("Single-Hand",true);
		JRadioButton b2=new JRadioButton("Both-Hand",false);
		b1.setActionCommand("sh"); 
		b2.setActionCommand("bh"); 
		ButtonGroup sb=new ButtonGroup();
		sb.add(b1);
		sb.add(b2);
		panel.add(b1);
		panel.add(b2);
		
		/****************************************************************************/
		JCheckBox check=new JCheckBox("Convert to Black and White");
		panel.add(check);
		
		/*****************************************************************************/		
    	/*****************************************************************************/
    	/****************************************************************************/
    	System.out.println(sname);
    	opfile=jTextField1.getText()+".mid";
    	System.out.println(opfile);
    	if(volume==null)
    		volume="50";
    	if(sharp==null)
    		sharp="0";
    	if(flat==null)
    		flat="0";
    	if(transpose==null)
    		transpose="0";
    	if(tempo==null)
    		tempo="50";
		int result = JOptionPane.showConfirmDialog(null, panel, "OMT", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) 
		{
			if(check.isSelected())
				blknwht=1;
			else
				blknwht=0;
			
			System.out.println(sb.getSelection().getActionCommand());
			//System.out.println(check.getSelectedObjects().getClass());
			tf=sb.getSelection().getActionCommand();
			if(tf=="sh")
				typeflag=0;
			else
				typeflag=1;
				
			OpenMusicTranscriptor omt= new OpenMusicTranscriptor(path,volume,sharp,flat,transpose,tempo,opfile,blknwht,typeflag);
		}
	}

	private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
		if(sname==null)
	      {
	         JFileChooser fc = new JFileChooser();
	            int result = fc.showOpenDialog(null);
	            if (result == JFileChooser.APPROVE_OPTION) {
	                File file = fc.getSelectedFile();
	                path=file.getPath();
	               sname = file.getName();
	                System.out.println(path); 
	                jTextField1.setText(sname);
	              /////////////////
	               File targetfile=file;      
	            try {
	               targetimg=rescale(ImageIO.read(targetfile));
	               oriimg=targetimg;
	            } catch (IOException ex) {
	                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
	            }
	            jPanel1.removeAll();
	            jPanel1.setLayout(new BorderLayout(0,0));
	            jPanel1.add(new JLabel(new ImageIcon(targetimg)));
	                setVisible(true);
	               //////////////////// 
	            JLabel image = new JLabel("hello", new ImageIcon(sname), JLabel.CENTER);
	          System.out.println(jPanel1.getLocationOnScreen());
    // image.repaint();
          
     //      jPanel1.add(image,BorderLayout.CENTER);  //pp
        //       jPanel1.revalidate();                  //pp
      //          jPanel1.repaint(100, 100, 100, 100);      pp
              // jPanel1.repaint();
            }
	      }
             
    }//GEN-LAST:event_jMenuItem1ActionPerformed
    
    
 
    
    
    
     public BufferedImage rescale(BufferedImage originalImage)
    {
        int baseSize=500;
        BufferedImage resizedImage = new BufferedImage(1000,500, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, 1000, 500, null);
        g.dispose();
        return resizedImage;
    }
     
     public BufferedImage rescale(BufferedImage originalImage,String percent)
    {
        int baseSize=500;
        double val=Double.parseDouble(percent);
        double per=(val/100);
        //System.out.println(val+" "+per);
        int width=oriimg.getWidth();
        width=(int)(width*per);
        int height=oriimg.getHeight();
        height=(int)(height*per);
        //System.out.println(originalImage.getWidth());
        //System.out.println(width);
        
       BufferedImage resizedImage = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(oriimg, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;///resizedImage;
    }
     
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
      //if(sname==null)
      {
         JFileChooser fc = new JFileChooser();
            int result = fc.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                path=file.getPath();
               sname = file.getName();
                System.out.println(path); 
                jTextField1.setText(sname);
              /////////////////
               File targetfile=file;      
            try {
               targetimg=rescale(ImageIO.read(targetfile));
               oriimg=targetimg;
            } catch (IOException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
            jPanel1.removeAll();
            jPanel1.setLayout(new BorderLayout(0,0));
            jPanel1.add(new JLabel(new ImageIcon(targetimg)));
                setVisible(true);
               //////////////////// 
            JLabel image = new JLabel("hello", new ImageIcon(sname), JLabel.CENTER);
          System.out.println(jPanel1.getLocationOnScreen());
    // image.repaint();
          
     //      jPanel1.add(image,BorderLayout.CENTER);  //pp
        //       jPanel1.revalidate();                  //pp
   //             jPanel1.repaint(100, 100, 100, 100);      pp
             //   jPanel1.repaint();
            }
            }
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private void jSlider1StateChanged(ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
 	   //System.out.println("zoom");
 	   //System.out.println(jSlider1.getValue());
 	   zoom=String.valueOf(jSlider1.getValue());
 	   
 	  targetimg=rescale(oriimg,zoom);
 	  jPanel1.removeAll();
      jPanel1.setLayout(new BorderLayout(0,0));
      jPanel1.add(new JLabel(new ImageIcon(targetimg)));
          setVisible(true);
 	   
 	   
 	  //System.out.println(zoom);
 		// TODO Auto-generated method stub
 		
 	}//GEN-LAST:event_jSlider1StateChanged
    
    
    private void jSlider2StateChanged(ChangeEvent evt) {//GEN-FIRST:event_jSlider2StateChanged
	   //System.out.println("Volume");
    	volume=String.valueOf(jSlider2.getValue());
	   //System.out.println(jSlider2.getValue());
	}//GEN-LAST:event_jSlider2StateChanged
    
    

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
    	//System.out.println("Tempo");
    	//System.out.println(jSpinner1.getValue());
    	tempo=String.valueOf(jSpinner1.getValue());	   
    }//GEN-LAST:event_jSpinner1StateChanged
    
    
    private void jSpinner2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner2StateChanged
    	//System.out.println("Transpose 2");
    	//System.out.println(jSpinner2.getValue());
    	transpose=String.valueOf(jSpinner2.getValue());   
    }//GEN-LAST:event_jSpinner2StateChanged
    
    private void jSpinner3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner3StateChanged
    	//System.out.println("Sharp");
    	//System.out.println(jSpinner3.getValue());
    	sharp=String.valueOf(jSpinner3.getValue());  
    }//GEN-LAST:event_jSpinner3StateChanged
    
    private void jSpinner4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner4StateChanged
    	//System.out.println("Flat");
    	//System.out.println(jSpinner4.getValue());
    	flat=String.valueOf(jSpinner4.getValue());    
    }//GEN-LAST:event_jSpinner4StateChanged
    

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
    	System.out.println("jTextField1ActionPerformed");
    	
    }//GEN-LAST:event_jTextField1ActionPerformed

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
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
     
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new main().setVisible(true);
              
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;

    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;

    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSlider jSlider2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JSpinner jSpinner4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables

    void setLocationRelativeTo(float CENTER_ALIGNMENT) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
