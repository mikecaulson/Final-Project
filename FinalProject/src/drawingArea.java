import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class drawingArea
	{
		public static void main(String[] args)
		{
			//blue button
			Icon imageb = new ImageIcon("Blue.gif");
			//red button
			Icon imager = new ImageIcon("2000px-Red_flag.svg.png");
			//green button
			Icon imageg = new ImageIcon("imgres.jpg");
			//black image icon
			Icon iconBl = new ImageIcon("Flag_of_Afghanistan_(1880–1901).svg.png");
			
			Icon imagee = new ImageIcon("Flaf_of_Taliban_(original).svg.png");
			
			
			JFrame frame = new JFrame("Paint It");
			
			Container content = frame.getContentPane();
			//Creates a new container
			content.setLayout(new BorderLayout());
			//sets the layout
			
			final PadDraw drawPad = new PadDraw();
			//creates a new padDraw, which is pretty much the paint program
			
			content.add(drawPad, BorderLayout.CENTER);
			
			JPanel panel = new JPanel();
			panel.setPreferredSize(new Dimension(32, 68));
			panel.setMinimumSize(new Dimension(32, 68));
			panel.setMaximumSize(new Dimension(32, 68));
			//This sets the size of the panel
			
			JButton clearButton = new JButton("Clear");
			clearButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					drawPad.clear();
				}
			});
			
			
			JButton redButton = new JButton(imager);
			redButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					drawPad.red();
				}

			});
			
			
			JButton blackButton = new JButton(iconBl);
			//same thing except this is the black button
			blackButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					drawPad.black();
				}
			});
			
			
			JButton blueButton = new JButton(imageb);
			blueButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					drawPad.blue();
				}
			});
			
			JButton greenButton = new JButton(imageg);
			greenButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					drawPad.green();
				}
				
			});
			JButton eraserButton = new JButton(imagee);
			eraserButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						drawPad.eraser();
					}
				});
			blackButton.setPreferredSize(new Dimension(16, 16));
			eraserButton.setPreferredSize(new Dimension(16, 16));
			redButton.setPreferredSize(new Dimension(16, 16));
			blueButton.setPreferredSize(new Dimension(16, 16));
			greenButton.setPreferredSize(new Dimension(16,16));
			//sets the sizes of the buttons
			
			panel.add(greenButton);
			panel.add(blueButton);
			panel.add(eraserButton);
			panel.add(blackButton);
			panel.add(redButton);
			panel.add(clearButton);
			//adds the buttons to the panel
			
			content.add(panel, BorderLayout.WEST);
			
			frame.setSize(1925, 1040);
			//sets the size of the frame
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			
		}
	}


	class PadDraw extends JComponent{
		Image image;
		
		Graphics2D graphics2D;
		
		int currentX, currentY, oldX, oldY;
		

		
		public PadDraw(){
			setDoubleBuffered(false);
			addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e){
					oldX = e.getX();
					oldY = e.getY();
				}
			});
			//if the mouse is pressed it sets the oldX & oldY coordinates as the mouses x & y coordinates
			addMouseMotionListener(new MouseMotionAdapter(){
				public void mouseDragged(MouseEvent e){
					currentX = e.getX();
					currentY = e.getY();
					if(graphics2D != null)
					graphics2D.drawLine(oldX, oldY, currentX, currentY);
					repaint();
					oldX = currentX;
					oldY = currentY;
				}

			});
			//while the mouse is dragged it sets currentX & currentY as the mouses x and y
			//then it draws a line at the coordinates
			//it repaints it and sets oldX and oldY as currentX and currentY
		}

		public void paintComponent(Graphics g){
			if(image == null){
				image = createImage(getSize().width, getSize().height);
				graphics2D = (Graphics2D)image.getGraphics();
				graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				clear();

			}
			g.drawImage(image, 0, 0, null);
		}
		


		public void clear()
			{
			graphics2D.setPaint(Color.white);
			graphics2D.fillRect(0, 0, getSize().width, getSize().height);
			graphics2D.setPaint(Color.black);
			repaint();
		}
		//this is the red paint
		public void red()
			{
			graphics2D.setPaint(Color.red);
			repaint();
		}
		//black paint
		public void black()
			{
			graphics2D.setPaint(Color.black);
			repaint();
		}
		
		//blue paint
		public void blue(){
			graphics2D.setPaint(Color.blue);
			repaint();
		}
		//green paint
		public void green(){
			graphics2D.setPaint(Color.green);
			repaint();
		}
		//eraser paint
		public void eraser()
			{
			graphics2D.setPaint(Color.white);
			repaint();
		}

	}