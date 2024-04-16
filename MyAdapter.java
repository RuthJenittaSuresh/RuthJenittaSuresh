import java.awt.*;
import java.awt.event.*;
public class MyAdepter implement WindowListener  {
	Frame f;
	AdapterDemo()
	{
		f=new Frame("Adapter Demo");
		f.setVisible(true);
		f.setSize(500,400);
		f.addWindowListener(new MyaAdapter());
		

	}
	class MyAdapter extends WindowAdapter
	public void windowClosing(WindowEvent e)
	{
		f.dispose();
	}
	public static void main(String[] args){
	new ();

	}
}