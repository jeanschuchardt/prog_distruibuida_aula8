package cliente;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import interfaces.ServerHostInterface;
import servidor.Clientes;

class HelloClient {
	// Programa cliente para o exemplo "Hello, world!"
	public static void main(String[] argv) {
		Scanner sc = new Scanner(System.in);
		try {
			// ServerHostInterface hello = (ServerHostInterface)
			// Naming.lookup("//192.168.0.13:1099/Hello");
			ServerHostInterface hello = (ServerHostInterface) Naming.lookup("//localhost/Hello");
			Peer cliente = new Peer();
			System.out.println("iniciado");
			int key = 1;
			do {
				System.out.println("------------------" + "\n");
				menu();
				System.out.println("digite uma opção\n-------------------\n");
				key = sc.nextInt();
				switch (key) {
				case 1:

					System.out.println(hello.registraPeer());
					break;

				case 2:
					// calcula hash dos arquivos de um diretorio
					HashMap<String, String> contentList = cliente.contentList();
					break;

				case 3:
					// descreve conteuno no server
					HashMap<String, String> mapFiles = new HashMap<String, String>();
					try {
						mapFiles = cliente.getMapFiles();
						hello.registraRecurso(mapFiles);
					} catch (Exception e) {
						System.out.println("recurso nao definidos");
						System.out.println("utilize a pocao 2 para definir recursos");
						System.out.println(e);

					}

					break;

				case 4:
					System.out.println(hello.listaRecursos());
					break;

				case 5:
					HashMap<String, Clientes> solicitaClientes = hello.solicitaClientes();
					HashMap<String, String> value2 = new HashMap<String, String>();
					for (String name : solicitaClientes.keySet()) {
						String key1 = name.toString();
						String value = solicitaClientes.get(name).getIp();
						value2 = solicitaClientes.get(name).getRecursos();
						System.out.println(key1 + ">" + value);
					}
					for (String name : value2.keySet()) {

					}
					break;
				case 6:
					System.out.println("info o hash do arquivo");
					Scanner h = new Scanner(System.in);
					String hash = h.nextLine();
					String findByHash = hello.findByHash(hash);
					System.out.println(findByHash);
					
					cliente.conectToPeer(findByHash);
					

					break;
				case 7:
					cliente.clienteUDP();
					break;

				case 8:

					break;

				case 9:
					System.out.println(hello.say());
					break;

				case 0:
					System.out.println("\nfim da aplicação \n\n");
					break;
				default:
					System.out.println("opção errada");
					break;
				}

			} while (key != 0);

		} catch (Exception e) {
			System.out.println("HelloClient failed:");
			e.printStackTrace();
		}
	}

	public static void menu() {
		System.out.println("0 - fim da aplicacao\n" + "1 - registra peer\n" + "2 - calcula hash\n"
				+ "3 - registra recurso\n" + "4 - lista recurso\n" + "5 - lista de clientes\n" + ""

				+ "9 - test\n");

	}

}
