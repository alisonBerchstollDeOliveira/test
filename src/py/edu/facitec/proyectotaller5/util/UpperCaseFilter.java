package py.edu.facitec.proyectotaller5.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class UpperCaseFilter extends DocumentFilter {
	@Override
	public void insertString(FilterBypass fb, int offset, 
			String texto, AttributeSet atributo) throws BadLocationException {
		fb.insertString(offset, texto.toUpperCase(), atributo);
	};
	
	@Override
	public void replace(FilterBypass fb, int offset, int tamanho, String text,
			AttributeSet atributo) throws BadLocationException {
		fb.replace(offset, tamanho, text.toUpperCase(), atributo);
	}
}
