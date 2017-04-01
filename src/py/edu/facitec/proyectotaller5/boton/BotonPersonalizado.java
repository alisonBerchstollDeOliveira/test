package py.edu.facitec.proyectotaller5.boton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class BotonPersonalizado extends JButton{

	public BotonPersonalizado() {
		setMaximumSize(new Dimension(90, 90));
		setPreferredSize(new Dimension(90, 90));
		setFont(new Font("Dialog", Font.PLAIN, 14));
		setHorizontalTextPosition(SwingConstants.CENTER);
		setVerticalTextPosition(SwingConstants.BOTTOM);
		setBackground(Color.YELLOW);
		setOpaque(false);
		
	}
	public void setIcon(String icono) {
		setIcon(new ImageIcon(BotonPersonalizado.class.getResource("/py/edu/facitec/proyectotaller5/img/"+icono+".png")));
		
	}
	
	
}
