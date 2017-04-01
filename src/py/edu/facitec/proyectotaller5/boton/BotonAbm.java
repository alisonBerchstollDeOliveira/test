package py.edu.facitec.proyectotaller5.boton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class BotonAbm extends JButton{

	public  BotonAbm() {
		setMaximumSize(new Dimension(90, 75));
		setPreferredSize(new Dimension(90, 75));
		setFont(new Font("Dialog", Font.BOLD, 11));
		setHorizontalTextPosition(SwingConstants.CENTER);
		setVerticalTextPosition(SwingConstants.BOTTOM);
		setBackground(new Color(211, 211, 211));
		setBorderPainted(false);
		
	}
	

	public void setIcon(String icono) {
		setIcon(new ImageIcon(BotonAbm.class.getResource("/py/edu/facitec/proyectotaller5/img/"+icono+".png")));
		
	}
}

