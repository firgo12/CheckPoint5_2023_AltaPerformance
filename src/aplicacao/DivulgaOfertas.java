package aplicacao;

//Ana Beatriz dos Santos Souza		RM 96229
//Yann Santana						RM 93609
//Daniel Franceschi					RM 95175					
//Guilherme Mendes da Cunha			RM 95173					
//Enzo Mansi						RM 92955

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import arvores.ABBPessoa;
import entidade.Pessoa;

public class DivulgaOfertas {

	public static void main(String[] args) {
		
		Scanner le = new Scanner(System.in);
		/*
		 * Cria a uma �rvore de busca bin�ria para cada tipo de conta (pessoa f�sica ou
		 * jur�dica)
		 */
		ABBPessoa clienteF = new ABBPessoa();
		ABBPessoa clienteJ = new ABBPessoa();

		int opcao, op, numeroConta;
		String nome, cpfCnpj;
		String tipoConta = null;
		double saldo;
		do {
			System.out.println(" 0 - Encerrar o programa");
			System.out.println(" 1 - Inscrição do cliente");
			System.out.println(" 2 - Oferta de novo serviço e/ou aplicação");
			System.out.println(" 3 - Entrar no Submenu ");
			opcao = le.nextInt();
			switch (opcao) {
			case 1:
				System.out.print("Digite nome: ");
				nome = le.next();
				System.out.print("Digite cpf/cnpj: ");
				cpfCnpj = le.next();
				System.out.print("Digite número da conta: ");
				numeroConta = le.nextInt();
				do {
					System.out.print("Digite 1- Pessoa Física 2- Pessoa Jurídica: ");
					op = le.nextInt();
					switch (op) {
					case 1:
						tipoConta = "Física";
						break;
					case 2:
						tipoConta = "Jurídica";
						break;
					default:
						System.out.println("Opção inválida ");
						op = -1;
					}
				} while (op == -1);
				System.out.print("Informe saldo em aplicações R$: ");
				saldo = le.nextDouble();
				/*
				 * Intancia um objeto da classe Cliente e insere na ABB correspondente a tipo de
				 * conta
				 */

				Pessoa p = new Pessoa(nome, cpfCnpj, tipoConta, numeroConta, saldo);
				if (tipoConta.equalsIgnoreCase("Física")) {
					clienteF.root = clienteF.inserirAVL(clienteF.root, p);
					clienteF.atualizaAlturas(clienteF.root);
					// System.out.println(clienteF.consultaCodigo(clienteF.root, numeroConta));
				} else {
					clienteJ.root = clienteJ.inserirAVL(clienteJ.root, p);
					clienteJ.atualizaAlturas(clienteJ.root);
					// System.out.println(clienteJ.consultaCodigo(clienteJ.root, numeroConta));
				}
				break;
			case 2:

				System.out.print("Qual tipo de conta a oferta se destina? ");
				do {
					System.out.print("Digite 1- Pessoa Física 2- Pessoa Jurídica: ");
					op = le.nextInt();
					switch (op) {
					case 1:
						tipoConta = "Física";
						break;
					case 2:
						tipoConta = "Jurídica";
						break;
					default:
						System.out.println("Opção inválida ");
						op = -1;
					}
					if (tipoConta != "Física" && tipoConta != "Juridíca") {
						System.out.println("Opção inválida");
					}
				} while (tipoConta != "Física" && tipoConta != "Juridíca");

				System.out.print("Qual o valor de saldo mínimo exigido: R$ ");
				double saldoMinimo = le.nextDouble();

				LinkedList<Pessoa> lista = new LinkedList<Pessoa>();

				if (tipoConta == "Física") {
					lista = clienteF.listaOferta(clienteF.root, saldoMinimo);
				} else {
					lista = clienteJ.listaOferta(clienteJ.root, saldoMinimo);
				}

				if (lista.isEmpty()) {
					System.out.println("Não há mais clientes aptos para a oferta.");
					break;
				}

				lista.sort((c1, c2) -> Double.compare(c2.getSaldo(), c1.getSaldo()));

				int size = lista.size();

				Pessoa cliente = lista.getFirst();
				System.out.println("Clientes aptos para a oferta:");
				System.out.println(cliente);
				System.out.print("Aceitar a oferta (S/N): ");
				String resposta = le.next();

				if (resposta.equalsIgnoreCase("S")) {
					System.out.println("Oferta aceita. Cliente removido da lista.");
					if (tipoConta == "Física") {
						clienteF.root = clienteF.removeValor(clienteF.root, cliente.getNumeroConta());
					} else {
						clienteJ.root = clienteJ.removeValor(clienteJ.root, cliente.getNumeroConta());
					}
					lista.removeFirst();
				} else {
					System.out.println("Oferta recusada. Cliente permanece na lista.");
					lista.removeFirst();
				}
				int i = 0;
				i++;

				do {

					if (tipoConta == "Física") {
						lista = clienteF.listaOferta(clienteF.root, saldoMinimo);
					} else {
						lista = clienteJ.listaOferta(clienteJ.root, saldoMinimo);
					}

					if (lista.isEmpty()) {
						System.out.println("Não há mais clientes aptos para a oferta.");
						break;
					}

					lista.sort((c1, c2) -> Double.compare(c2.getSaldo(), c1.getSaldo()));

					size = lista.size();

					cliente = lista.getFirst();
					System.out.println("Clientes aptos para a oferta:");
					System.out.println(cliente);
					System.out.print("Aceitar a oferta (S/N): ");
					resposta = le.next();

					if (resposta.equalsIgnoreCase("S")) {
						System.out.println("Oferta aceita. Cliente removido da lista.");
						if (tipoConta == "Física") {
							clienteF.root = clienteF.removeValor(clienteF.root, cliente.getNumeroConta());
						} else {
							clienteJ.root = clienteJ.removeValor(clienteJ.root, cliente.getNumeroConta());
						}
						lista.removeFirst();
					} else {
						System.out.println("Oferta recusada. Cliente permanece na lista.");
						lista.removeFirst();
					}

					i++;
				} while (i < size);

				break;

			case 3:
				/*
				 * Implemente o submenu descrito no texto
				 */
				int opcao2;
				do {
					System.out.println("1 - Consultar Cliente pelo CPF/CNPJ");
					System.out.println("2 - Atualização do saldo do cliente número da conta");
					System.out.println("3 - Quantidades de clientes armazenados na ABB");
					System.out.println("4 - Apresenta a quantidade de clientes com saldo acima de um valor a ser consultado");
					System.out.println("5 - Voltar para o menu principal");
					System.out.println("");
					System.out.println("Digite qual opção vai querer entrar: ");
					opcao2 = le.nextInt();
					
					switch (opcao2) {
					case 1:
						System.out.println("Digite o CPF/CNP para encontrar o cliente: ");
						int cpfCnpj2 = le.nextInt();
						System.out.println("-------- Cliente Físico --------");
						System.out.println(clienteF.consultaCPF(clienteF.root, cpfCnpj2));
						System.out.println("-------- Cliente Jurídico --------");
						System.out.println(clienteJ.consultaCPF(clienteJ.root, cpfCnpj2));
						
						break;
						
					case 2:
						System.out.println("Digite o número da conta do cliente: ");
						int nConta = le.nextInt();
						System.out.println("Digite o novo saldo do Cliente: ");
						double novoSaldo = le.nextDouble();
						
						do {
							System.out.print("Digite 1- Pessoa Física 2- Pessoa Jurídica: ");
							op = le.nextInt();
							switch (op) {
							case 1:
								clienteF.consultaCodigo(clienteF.root, nConta).setSaldo(novoSaldo);
								System.out.println(clienteF.consultaCodigo(clienteF.root, nConta));
								break;
							case 2:
								clienteJ.consultaCodigo(clienteJ.root, nConta).setSaldo(novoSaldo);
								System.out.println(clienteJ.consultaCodigo(clienteJ.root, nConta));
								break;
							default:
								System.out.println("Opção inválida ");
								op = -1;
							}
						} while (op == -1);
						
						break;

					case 3:
						 int totalClienteF = clienteF.contaNos(clienteF.root, 0);
						 int totalClienteJ = clienteJ.contaNos(clienteJ.root, 0);
						 int total = totalClienteF + totalClienteJ;
						 System.out.println("Total de Clientes: "+ total);
						 System.out.println(" ");
						
						break;
						
					case 4:
						System.out.println("Digite o saldo que será o saldo minimo para esta consulta: ");
						double saldo2 = le.nextDouble();
						System.out.println("-------- Cliente Físico --------");
						clienteF.listaSaldoAcima(clienteF.root, saldo2);
						
						System.out.println("-------- Cliente Jurídico --------");
						clienteJ.listaSaldoAcima(clienteJ.root, saldo2);
						
						System.out.println("Quantidade: "+ (clienteF.contaSaldoAcima(clienteF.root, saldo2, 0) + clienteJ.contaSaldoAcima(clienteJ.root, saldo2, 0)));
						
						break;
						
					case 5:
						System.out.println(" ");
						break;
						
					default:
						System.out.println("Opção inválida ");
						opcao2 = -1;
						
						break;
					}
				} while (opcao2 != 5);
				
				
				
			}
		} while (opcao != 0);
		System.out.println("Clientes que não aceitaram ou não estavam adequados para a oferta");
		/*
		 * Esvazia as ABBs apresentando todos os clientes que aguardam nova portunidade
		 */
		System.out.println("---------- Cliente Físico ----------");
		clienteF.listarArvore(clienteF.root);
		System.out.println("---------- Jurídico ----------");
		clienteF.listarArvore(clienteJ.root);
		
		clienteF.root = null;
		clienteJ.root = null;
		
		le.close();

	}

}
