package agenda;

/**
 * Um agenda que mantém uma lista de contatos com posições. Podem existir 100 contaatos 
 * 
 * @author nazarenoandrade
 *
 */
public class Agenda {
	
	private static final int TAMANHO_AGENDA = 100;
	
	private String[] contatos;

	public Agenda() {
		this.contatos = new String[TAMANHO_AGENDA];
	}
	
	public String[] getContatos() {
		return this.contatos.clone();
	}

	public String getContato(int posicao) {
		return contatos[posicao];
	}

	public void cadastraContato(int posicao, String nome) {
		this.contatos[posicao] = nome;
	}

}
