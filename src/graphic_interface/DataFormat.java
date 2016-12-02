package graphic_interface;

import javax.swing.text.MaskFormatter;

public class DataFormat {
	public static String upperCaseWords(String givenString) {
		String[] arr = givenString.split(" ");
		StringBuffer sb = new StringBuffer();
	
		for (int i = 0; i < arr.length; i++) {
			sb.append(Character.toUpperCase(arr[i].charAt(0)))
			.append(arr[i].substring(1)).append(" ");
		}
		return sb.toString().trim();
	}

	public static String formatCpf(String cpf) {
		try {
			MaskFormatter mf = new MaskFormatter("###.###.###-##");
			mf.setValueContainsLiteralCharacters(false);
			return mf.valueToString(cpf);
		} catch (Exception e) {
			return cpf;
		}
	}
}