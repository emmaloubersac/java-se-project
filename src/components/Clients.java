package components;

//1.1.1

public class Clients {
	private String name;
	private String firstName;
	private int id;
	
	private static int numberOfClients;
	
	public Clients(String name, String firstName) {
		this.name = name;
		this.firstName = firstName;
		numberOfClients++;
		this.id = numberOfClients;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Client number " + this.id + " : " + this.firstName + " " + this.name;
	}
	
}
