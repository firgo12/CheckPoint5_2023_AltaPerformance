package arvores;

import java.util.LinkedList;

import entidade.Pessoa;

public class ABBPessoa {
	
	//Ana Beatriz dos Santos Souza		RM 96229
	//Yann Santana						RM 93609
	//Daniel Franceschi					RM 95175					
	//Guilherme Mendes da Cunha			RM 95173					
	//Enzo Mansi						RM 92955
	
	private class ARVORE {
		Pessoa dado;
		ARVORE dir;
		ARVORE esq;
		int hEsq;
		int hDir;
	}

	public ARVORE root = null;

	public ARVORE rotacaoEsquerda(ARVORE p) {
		// faz rota��o para esquerda em rela��o ao n� apontado por p
		ARVORE q, temp;
		q = p.dir;
		temp = q.esq;
		q.esq = p;
		p.dir = temp;
		return q;
	}

	public ARVORE rotacaoDireita(ARVORE p) {
		// faz rota��o para direita em rela��o ao n� apontado por p
		ARVORE q, temp;
		q = p.esq;
		temp = q.dir;
		q.dir = p;
		p.esq = temp;
		return q;
	}

	public ARVORE balanceamento(ARVORE p) {
		// analisa FB e realiza rota��es necess�rias para balancear �rvore
		int FB = p.hDir - p.hEsq;
		if (FB > 1) {
			int fbFilhoDir = p.dir.hDir - p.dir.hEsq;
			if (fbFilhoDir >= 0)
				p = rotacaoEsquerda(p);
			else {
				p.dir = rotacaoDireita(p.dir);
				p = rotacaoEsquerda(p);
			}
		} else {
			if (FB < -1) {
				int fbFilhoEsq = p.esq.hDir - p.esq.hEsq;
				if (fbFilhoEsq <= 0)
					p = rotacaoDireita(p);
				else {
					p.esq = rotacaoEsquerda(p.esq);
					p = rotacaoDireita(p);
				}
			}
		}
		return p;
	}

	public ARVORE inserirAVL(ARVORE p, Pessoa info) {
		if (p == null) { // n� inserido sempre ser� n� folha
			p = new ARVORE();
			p.dado = info;
			p.esq = null;
			p.dir = null;
			p.hDir = 0;
			p.hEsq = 0;
		} else if (info.getNumeroConta() < p.dado.getNumeroConta()) {
			p.esq = inserirAVL(p.esq, info);
			if (p.esq.hDir > p.esq.hEsq) // Altura do n� ser� a maior
				p.hEsq = p.esq.hDir + 1; // altura dos seus filhos
			else
				p.hEsq = p.esq.hEsq + 1;
			p = balanceamento(p);
		} else {
			p.dir = inserirAVL(p.dir, info);
			if (p.dir.hDir > p.dir.hEsq)
				p.hDir = p.dir.hDir + 1;
			else
				p.hDir = p.dir.hEsq + 1;
			p = balanceamento(p);
		}
		return p;
	}

	public void atualizaAlturas(ARVORE p) {
		/*
		 * atualiza informa��o da altura de cada n� depois da remo��o percorre a �rvore
		 * usando percurso p�s-ordem para ajustar primeiro os n�s folhas (profundidade
		 * maior) e depois os n�veis acima
		 */
		if (p != null) {
			atualizaAlturas(p.esq);
			if (p.esq == null)
				p.hEsq = 0;
			else if (p.esq.hEsq > p.esq.hDir)
				p.hEsq = p.esq.hEsq + 1;
			else
				p.hEsq = p.esq.hDir + 1;
			atualizaAlturas(p.dir);
			if (p.dir == null)
				p.hDir = 0;
			else if (p.dir.hEsq > p.dir.hDir)
				p.hDir = p.dir.hEsq + 1;
			else
				p.hDir = p.dir.hDir + 1;
		}
	}

	public void mostraFB(ARVORE p) {
		if (p != null) {
			mostraFB(p.esq);
			mostraFB(p.dir);
			System.out.println("Dado: " + p.dado + "\t FB= " + (p.hDir - p.hEsq));
		}
	}
	public ARVORE removeValor(ARVORE p, int info) {
		if (p == null) {
			return p;
		}
	
		if (info < p.dado.getNumeroConta()) {
			p.esq = removeValor(p.esq, info);
		} else if (info > p.dado.getNumeroConta()) {
			p.dir = removeValor(p.dir, info);
		} else {
			// Encontrou o nó a ser removido
	
			// Caso 1: Nó com apenas um filho ou nenhum filho
			if (p.esq == null) {
				return p.dir;
			} else if (p.dir == null) {
				return p.esq;
			}
	
			// Caso 2: Nó com dois filhos, encontre o sucessor in-order
			ARVORE aux = findMinValueNode(p.dir);
	
			// Copie os dados do sucessor para este nó
			p.dado = aux.dado;
	
			// Remova o sucessor
			p.dir = removeValor(p.dir, aux.dado.getNumeroConta());
		}
	
		return p;
	}
	
	private ARVORE findMinValueNode(ARVORE node) {
		ARVORE current = node;
		while (current.esq != null) {
			current = current.esq;
		}
		return current;
	}
	
	public ARVORE atualizaAlturaBalanceamento (ARVORE p) {
		/*atualiza informa��o da altura de cada n� depois da remo��o percorre a �rvore usando percurso p�s-ordem para ajustar primeiro os n�s folhas (profundidade maior) e depois os n�veis acima */ 
		 	if( p != null) {
				p.esq = atualizaAlturaBalanceamento (p.esq);
				if (p.esq == null)
					p.hEsq = 0;
				else  if (p.esq.hEsq > p.esq.hDir)
					  p.hEsq = p.esq.hEsq+1;
				      else
					  p.hEsq = p.esq.hDir+1;
				p.dir = atualizaAlturaBalanceamento (p.dir);
				if (p.dir == null)
				  	p.hDir = 0;
				else if (p.dir.hEsq > p.dir.hDir)
					 p.hDir = p.dir.hEsq+1;
				     else
					p.hDir = p.dir.hDir+1;
				p = balanceamento(p);
			}
			return p;
		}

		public LinkedList<Pessoa> listaOferta(ARVORE p, double limite) {
			LinkedList<Pessoa> lista = new LinkedList<Pessoa>();
		
			if (p != null) {
				listaOferta(p.esq, limite);
		
				if (p.dado.getSaldo() >= limite) {
					lista.add(p.dado);
				}
		
				listaOferta(p.dir, limite);
			}
		
			return lista;
		}
		
	public Pessoa consultaCodigo(ARVORE p, int codigo) {
		if(p!=null) {
			if (codigo == p.dado.getNumeroConta())
				return p.dado;
			else {
				if (codigo < p.dado.getNumeroConta())
					return consultaCodigo(p.esq, codigo);
				else
					return consultaCodigo(p.dir, codigo);
			}
		}
		return null;
	}
	
	public Pessoa consultaCPF(ARVORE p, int codigo) {
		if(p!=null) {
			if (codigo == Integer.parseInt(p.dado.getCpfCnpj()))
				return p.dado;
			else {
				if (codigo < Integer.parseInt(p.dado.getCpfCnpj()))
					return consultaCPF(p.esq, codigo);
				else
					return consultaCPF(p.dir, codigo);
			}
		}
		return null;
	}
	
	public int contaNos(ARVORE p, int cont) {
		if (p!=null) {
			cont++;
			cont = contaNos(p.esq,cont);
			cont = contaNos(p.dir,cont);
		}
		return cont;
	}
	
	public void listaSaldoAcima(ARVORE p, double limite) {
		if (p != null) {
			listaSaldoAcima(p.esq, limite);
			if (p.dado.getSaldo() > limite) 
				System.out.println(p.dado);
			listaSaldoAcima(p.dir, limite);
		}
	}
	
	public int contaSaldoAcima(ARVORE p, double limite ,int cont) {
		if (p!=null) {
			contaSaldoAcima(p.esq,limite ,cont);
			if (p.dado.getSaldo() > limite) 
				cont++;
			contaSaldoAcima(p.dir,limite,cont);
		}
		return cont;
	}
	
	public void listarArvore(ARVORE p) {
		if (p != null) {
			listarArvore(p.esq);
			System.out.println(p.dado);
			listarArvore(p.dir);
		}
	}

}
