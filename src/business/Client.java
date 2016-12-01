package business;

public class Client {

	private int id;
	private String name;
	private String cpf;
	private int age;
	private ClientGender gender;
	private ClientCategory category;
	private String checkIn;
	private String checkOut;

	public Client(int id, String name, String cpf, int age, ClientGender gender, ClientCategory category, String check_in, String check_out) {
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

	public String getCheckIn() {
		return this.checkIn;
	}

	public String getCheckOut() {
		return this.checkOut;
	}

	@Override
	public String toString() {
		return "Id: " + this.id + "\n" +
				"Nome: " + this.name + "\n" +
				"CPF: " + this.cpf + "\n" +
				"Idade: " + this.age + "\n" +
				"Gênero: " + this.gender + "\n" +
				"Categoria: " + this.category + "\n" +
				"Entrada: " + this.checkIn + "\n" +
				"Saída: " + this.checkOut;
	}

}