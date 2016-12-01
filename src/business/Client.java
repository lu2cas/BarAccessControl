package business;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {

	private int id;
	private String name;
	private String cpf;
	private int age;
	private ClientGender gender;
	private ClientCategory category;
	private Date checkIn;
	private Date checkOut;

	public Client(int id, String name, String cpf, int age, ClientGender gender, ClientCategory category, Date check_in, Date check_out) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.age = age;
		this.gender = gender;
		this.category = category;
		this.checkIn = check_in;
		this.checkOut = check_out;
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

		return "Id: " + this.id + "\n" +
				"Nome: " + this.name + "\n" +
				"CPF: " + this.cpf + "\n" +
				"Idade: " + this.age + "\n" +
				"Gênero: " + this.gender + "\n" +
				"Categoria: " + this.category + "\n" +
				"Entrada: " + dateFormat.format(this.checkIn) + "\n" +
				"Saída: " + dateFormat.format(this.checkOut);
	}

}