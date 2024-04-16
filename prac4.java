import java.awt.*; 
import java.awt.event.*; 

class Bouncing_Balls extends Frame implements MouseListener 
{ 
	int x = 40, y = 40, t1 = 1, t2 = 1; 
	int x1 = 200, y1 = 40, t12 = 1, t22 = 1; 
	int x2 = 100, y2 = 100, t13 = 1, t23 = 1; 
	int x3 = 500, y3 = 500, t14 = 1, t24 = 1; 
	int x4 = 300, y4 = 300, t15 = 1, t25 = 1; 
	int R,G,B;
	Thread th; 
	Bouncing_Balls() 
	{ 
		setSize(700, 800); 
		setVisible(true); 
		th = new Thread(new Thread() 
		{ 
			public void run() 
			{ 
				while (true) 
				{ 
					x = x + t1; 
					y = y + t2; 
					x1 = x1 + t12; 
					y1 = y1 + t22; 
					x2 = x2 - t13; 
					y2 = y2 - t23;  
					x3 = x3 + t14;
					y3 = y3 + t24;
					x4 = x4 - t15;
					y4 = y4 - t25;
					if (x < 0 || x > 680) 
						t1 = t1 * (-1); 
					if (y < 20 || y > 780) 
						t2 = t2 * (-1); 
					if (x1 < 0 || x1 > 680) 
						t12 = t12 * (-1); 
					if (y1 < 20 || y1 > 780) 
						t22 = t22 * (-1); 
					if (x2 < 0 || x2 > 680) 
						t13 = t13 * (-1); 
					if (y2 < 20 || y2 > 780) 
						t23 = t23 * (-1);
					if (x3 < 0 || x3 > 680) 
						t14 = t14 * (-1);
					if (y3 < 20 || y3 > 780) 
						t24 = t24 * (-1);  
					if (x4 < 0 || x4 > 680) 
						t15 = t15 * (-1);
					if (y4 < 20 || y4 > 780) 
						t25 = t25 * (-1);  

					try 
					{ 
						this.sleep(5); 
					} 
					catch (Exception E) 
					{ 
					} 
					repaint(); 
				} 
			} 
		}); 
		addMouseListener(this); 
	} 

	public void mouseClicked(MouseEvent M) 
	{ 
		th.start(); 
	} 
	public void mousePressed(MouseEvent M) {} 
	public void mouseReleased(MouseEvent M) {} 
	public void mouseEntered(MouseEvent M) {} 
	public void mouseExited(MouseEvent M) {} 
      public void color()
	{
		 R=(int) (Math.random() * 256);
		 G=(int) (Math.random() * 256);
		 B=(int) (Math.random() * 256);
	}
	public void paint(Graphics g) 
	{ 
		g.setColor(Color.green); 
		g.fillOval(x, y, 50, 50); 
		g.setColor(Color.blue); 
		g.fillOval(x1, y1, 60, 60); 
		g.setColor(Color.red);
		g.fillOval(x2, y2, 70, 70);
		g.setColor(Color.orange); 
		g.fillOval(x3, y3, 80, 80); 
		g.setColor(Color.black);
		g.fillOval(x4, y4, 90, 90); 
		g.setColor(Color.white );
		
	} 
	public static void main(String[] args) 
	{ 
		Bouncing_Balls B = new Bouncing_Balls(); 
	} 
}