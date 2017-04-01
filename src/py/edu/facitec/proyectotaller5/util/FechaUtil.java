package py.edu.facitec.proyectotaller5.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

public class FechaUtil {
	private static MaskFormatter formatter;
	private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	
	public static MaskFormatter getFormato() {
		try {
			formatter = new MaskFormatter("##/##/####");
			formatter.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return formatter;
	}

	public static Date stringAFecha(String text) {
		DATE_FORMAT.setLenient(false);
		try {
			return DATE_FORMAT.parse(text);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Formato de Fecha Invalido");
			return null;
		}
	}

	public static String fechaAString(Date fecha) {
		return DATE_FORMAT.format(fecha);
	}
	
	
}
