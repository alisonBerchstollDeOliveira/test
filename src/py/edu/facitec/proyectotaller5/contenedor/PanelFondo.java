package py.edu.facitec.proyectotaller5.contenedor;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelFondo extends JPanel {
	
	URL url = getClass().getResource("/py/edu/facitec/proyectotaller5/img/fondo1.png");
	Image image = new ImageIcon(url).getImage();
	
	
	
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		setOpaque(false);
		super.paintComponent(g);
	}

}
