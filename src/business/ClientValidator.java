package business;

public class ClientValidator {
	private static ClientValidator clientValidator = null;

	private ClientValidator() {}

	public static ClientValidator getInstance() {
		if (clientValidator == null) {
			clientValidator = new ClientValidator();
		}
		return clientValidator;
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