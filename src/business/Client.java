package business;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {

	private Integer id;
	private String name;
	private String cpf;
	private int age;
	private ClientGender gender;
	private ClientCategory category;
	private Date checkIn;
	private Date checkOut;

	public Client(Integer id, String name, String cpf, int age, ClientGender gender, ClientCategory category, Date check_in, Date check_out) throws IllegalArgumentException {
		if (!ClientValidator.getInstance().isValidName(name)) {
			throw new IllegalArgumentException("Nome inválido!");
		}
		if (!ClientValidator.getInstance().isValidCpf(cpf)) {
			throw new IllegalArgumentException("CPF inválido!");
		}
		if (!ClientValidator.getInstance().isValidAge(age)) {
			throw new IllegalArgumentException("Idade inválida!");
		}
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.age = age;
		this.gender = gender;
		this.category = category;
		this.checkIn = check_in;
		this.checkOut = check_out;
	}

	public Client(String name, String cpf, int age, ClientGender gender, ClientCategory category) {
		this(null, name, cpf, age, gender, category, new Date(), null);
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getCpf() {
		return this.cpf;
	}

	public int getAge() {
		return this.age;
	}

	public ClientGender getGender() {
		return this.gender;
	}

	public ClientCategory getCategory() {
		return this.category;
	}

	public Date getCheckIn() {
		return this.checkIn;
	}

	public Date getCheckOut() {
		return this.checkOut;
	}

	@Override
	public String toString() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String check_out = null;
		if (this.checkOut != null) {
			check_out = dateFormat.format(this.checkOut);
		}

		return "Id: " + this.id + "\n" +
				"Nome: " + this.name + "\n" +
				"CPF: " + this.cpf + "\n" +
				"Idade: " + this.age + "\n" +
				"Gênero: " + this.gender + "\n" +
				"Categoria: " + this.category + "\n" +
				"Entrada: " + dateFormat.format(this.checkIn) + "\n" +
				"Saída: " + check_out;
	}

}