package py.edu.facitec.proyectotaller5.campos;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.text.AbstractDocument;

import py.edu.facitec.proyectotaller5.util.UpperCaseFilter;

public class CampoTestPane extends JTextPane {
	
	public int contador;
	public String texto;

	
	public CampoTestPane() {
	setFont(new Font("Dialog", Font.PLAIN, 11));
	((AbstractDocument) getDocument()).setDocumentFilter(new UpperCaseFilter());	
	setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
	setEditable(false);
	setBounds(99, 215, 201, 55);
	
	
	addKeyListener(new KeyAdapter() {
		@Override
		public void keyTyped(KeyEvent e) {
			contador = getText().length();
			texto = getText();
			//tfError.setText(contador+"");
			if(contador>=60){
				e.consume();
			}
			
		}
		@Override
		public void keyPressed(KeyEvent a) {
			if(a.getKeyCode()==KeyEvent.VK_BACK_SPACE){
				
			}else{			if(contador==20||contador==42||contador==64){
				setText(texto+" \n");
			}}}
	});
	


}
	}
