package business;

public class ClientValidator {
	private static ClientValidator cv = null;

	private ClientValidator() {}

	public static ClientValidator getInstance() {
		if (cv == null) {
			cv = new ClientValidator();
		}
		return cv;
	}

	public boolean isValidName(String name) {
		if (name == null || name.length() == 0 || !name.contains(" ")) {
			return false;
		}
		return true;
	}

	public boolean isValidCpf(String cpf) {
		if (cpf == null || cpf.length() != 11) {
			return false;
		}
		return true;
	}

	public boolean isValidAge(int age) {
		if (age < 0 || age > 150) {
			return false;
		}
		return true;
	}

}