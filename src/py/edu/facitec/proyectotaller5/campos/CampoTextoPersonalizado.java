package py.edu.facitec.proyectotaller5.campos;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

import py.edu.facitec.proyectotaller5.util.NumberUtil;
import py.edu.facitec.proyectotaller5.util.UpperCaseFilter;


public class CampoTextoPersonalizado extends JTextField{
	public CampoTextoPersonalizado() {
		setFont(new Font("Dialog", Font.PLAIN, 11));
		setDisabledTextColor(Color.GRAY);
		((AbstractDocument) getDocument()).setDocumentFilter(new UpperCaseFilter());
		setEditable(false);
	}
	public void recibeEnteros(){
		addKeyListener(new KeyAdapter() {
			
			
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
				
			}
		});
	}
	public void recibeDecimales(){
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(getText().isEmpty()){
					return;
				}
				if(getText().substring(getText().length()-1).equals(",")){
					return;
				}
				if(getText().substring(getText().length()-1).equals("0")&&getText().contains(",")){
					return;
				}
					String t = getText();
					t = t.replace(".","");
					t = t.replace(",", ".");
					Double d = Double.parseDouble(t);
					setText(NumberUtil.getNumeroFormateado(d));
				
			}
						
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)&&c!=',') {
					e.consume();
				}
			}
		});
	}
}
