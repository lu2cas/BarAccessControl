package business;

public class Client {

	private int id;
	private String name;
	private String cpf;
	private int age;
	private String checkIn;
	private String checkOut;

	public Client(int id, String name, String cpf, int age, String check_in, String check_out) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.age = age;
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

	public String getCheckIn() {
		return this.checkIn;
	}

	public String getCheckOut() {
		return this.checkOut;
	}

	@Override
	public String toString() {
		return "Id: " + this.id +
				"\n Nome: " + this.name +
				"\n CPF: " + cpf +
				"\n Idade: " + this.age +
				"\n Entrada: " + this.checkIn +
				"\n Saída: " + this.checkOut;
	}

}