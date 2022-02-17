package drawimage;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
//import java.io.IOException;


import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


import java.awt.BorderLayout;
import java.awt.Dimension;




	class MyCanvas extends JComponent {

	  /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
	//	 private BufferedImage image;
		 private JPanel canvas;
		 
		 public MyCanvas(final BufferedImage image)
		 {
			 
			// 		this.image=image;
			        this.canvas = new JPanel() {
			            private static final long serialVersionUID = 1L;
			            @Override
						protected void paintComponent(Graphics g) {
			            	
			                super.paintComponent(g);
			        	//	String str="ha ha ha";
			        		
			                g.drawImage(image,0,0, this);
			               // g.drawString(str, 50,50);
			            }
			        };
			        canvas.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
			        JScrollPane sp = new JScrollPane(canvas);
			        setLayout(new BorderLayout());
			        add(sp, BorderLayout.CENTER);
			 }

	}
	

	public class DisplayImage {
		
		public DisplayImage(BufferedImage image)
		{

			JFrame window = new JFrame();
		    window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    window.setBounds(0, 0, image.getWidth(), image.getHeight());
		    window.getContentPane().add(new MyCanvas(image));
		    window.setVisible(true);
		}
		

	}



