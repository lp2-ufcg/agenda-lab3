package agenda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class InterfaceAgenda {

	public static void main(String[] args) {
		Agenda agenda = new Agenda();
		
		System.out.println("Carregando agenda inicial");
		try {
			carregaAgenda("agenda_inicial.csv", agenda);
		} catch (IOException e) {
			System.err.println("Erro: " + e.getMessage());
		}
		
		Scanner scanner = new Scanner(System.in);
		String escolha = "";
		while(true) {			
			escolha = menu(scanner);
			comando(escolha, agenda, scanner);
		} 

	}

	/**
	 * Exibe o menu e captura a escolha do/a usuário/a.
	 * 
	 * @param scanner Para captura da opção do usuário. 
	 * @return O comando escolhido.
	 */
	private static String menu(Scanner scanner) {
		System.out.println(
				"\n---\nMENU\n"
				+ "(C)adastrar Contato\n"
				+ "(L)istar Contatos\n"
				+ "(E)xibir Contato\n"
				+ "(S)air\n"
				+ "\n"
				+ "Opção> ");
		return scanner.next().toUpperCase();
	}
	
	private static void comando(String opcao, Agenda agenda, Scanner scanner) {
		switch(opcao) {
			case "c":	
			case "C":
				cadastraContato(agenda, scanner);
				break;
			case "l":
			case "L": 
				listaContatos(agenda);
				break;
			case "e":
			case "E": 
				exibeContato(agenda, scanner);
				break;
			case "s":
			case "S": 
				sai();
				break;
			default:
				System.out.println("Opção inválida!");
		}
		
	}
 
	
	private static void listaContatos(Agenda agenda) {
		System.out.println("\nLista de contatos: ");
		String[] contatos = agenda.getContatos();
		for(int i = 0; i < contatos.length; i++) {
			if(contatos[i] != null) {
				System.out.println(formataContato(i, contatos[i]));
			}
		}
	}


	private static void exibeContato(Agenda agenda, Scanner scanner) {
		System.out.print("\nQual contato> ");
		int posicao = scanner.nextInt();
		String contato = agenda.getContato(posicao);
		System.out.println("Dados do contato:\n" + formataContato(posicao, contato));
	}

	private static String formataContato(int posicao, String contato) {
		return posicao + ": " + contato;
	}

	private static void cadastraContato(Agenda agenda, Scanner scanner) {
		System.out.print("\nPosição na agenda> ");
		int posicao = scanner.nextInt();
		System.out.print("\nNome> ");
		String nome = scanner.next();
		agenda.cadastraContato(posicao, nome);
	}


	private static void sai() {
		System.out.println("\nVlw flw o/");
		System.exit(0);
	}

	
	private static void carregaAgenda(String arquivo, Agenda agenda) throws IOException {
		int carregados = 0; 
		
		try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
		    String linha;
		    while ((linha = br.readLine()) != null) {
		    	carregados += 1;
		    	if(carregados == 1) {
		    		// pulamos a primeira linha, o cabeçalho
		    		continue; 
		    	}
		        String[] campos = linha.split(",");
		        processaLinhaAgendaCSV(campos, agenda);
		    }
		}
		
		System.out.println("Carregamos " + carregados + " registros.");
	}

	private static void processaLinhaAgendaCSV(String[] campos, Agenda agenda) {
		int posicao = Integer.parseInt(campos[0]);
		String nome = campos[1].trim();
		
		agenda.cadastraContato(posicao, nome);
	}
}
