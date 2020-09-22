package agenda;

public class Agenda {

	private String[] contatos;

	public Agenda() {
		this.contatos = new String[100];
	}
	
	public String[] getContatos() {
		return this.contatos;
	}

	public String getContato(int posicao) {
		return contatos[posicao];
	}

	public void cadastraContato(int posicao, String nome) {
		this.contatos[posicao] = nome;
	}

}
