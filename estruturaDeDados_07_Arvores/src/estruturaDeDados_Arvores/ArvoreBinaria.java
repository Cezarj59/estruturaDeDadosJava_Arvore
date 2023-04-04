package estruturaDeDados_Arvores;

public class ArvoreBinaria<T extends Comparable<T>> {

	private BinNo<T> raiz;

	public ArvoreBinaria() {
		this.raiz = null;
	}

	public void inserir(T conteudo) {
		// Cria um novo nó com o conteúdo passado por parâmetro
		BinNo<T> novoNo = new BinNo<>(conteudo);
		// Chama o método de inserção privado passando a raiz da árvore e o novo nó
		// criado
		this.raiz = inserir(raiz, novoNo);
	}

	// Método público que exibe os nós da árvore em ordem in-ordenada
	public void exibirInOrden() {
		System.out.print("\n Exibindo InOrden: "); // Imprime uma mensagem de início

		// Chama o método privado "exibirInOrden" passando a raiz da árvore como
		// argumento
		exibirInOrden(this.raiz);
	}

	// Método privado que exibe os nós da árvore em ordem in-ordenada
	private void exibirInOrden(BinNo<T> atual) {
		if (atual != null) { // Se o nó atual não for nulo, continua a exibição
			exibirInOrden(atual.getNoEsq()); // Chama recursivamente o método para exibir a sub-árvore esquerda
			System.out.print(atual.getConteudo() + ", "); // Exibe o conteúdo do nó atual
			exibirInOrden(atual.getNoDir()); // Chama recursivamente o método para exibir a sub-árvore direita
		}
	}

	// Método público que exibe os nós da árvore em ordem pós-ordenada
	public void exibirPosOrden() {
		System.out.print("\n Exibindo PosOrden: "); // Imprime uma mensagem de início

		// Chama o método privado "exibirPosOrden" passando a raiz da árvore como
		// argumento
		exibirPosOrden(this.raiz);
	}

	// Método privado que exibe os nós da árvore em ordem pós-ordenada
	private void exibirPosOrden(BinNo<T> atual) {
		if (atual != null) { // Se o nó atual não for nulo, continua a exibição
			exibirPosOrden(atual.getNoEsq()); // Chama recursivamente o método para exibir a sub-árvore esquerda
			exibirPosOrden(atual.getNoDir()); // Chama recursivamente o método para exibir a sub-árvore direita
			System.out.print(atual.getConteudo() + ", "); // Exibe o conteúdo do nó atual
		}
	}

	// Método público que exibe os nós da árvore em ordem pré-ordenada
	public void exibirPreOrden() {
		System.out.print("\n Exibindo PreOrden: "); // Imprime uma mensagem de início

		// Chama o método privado "exibirPreOrden" passando a raiz da árvore como
		// argumento
		exibirPreOrden(this.raiz);
	}

	// Método privado que exibe os nós da árvore em ordem pré-ordenada
	private void exibirPreOrden(BinNo<T> atual) {
		if (atual != null) { // Se o nó atual não for nulo, continua a exibição
			System.out.print(atual.getConteudo() + ", "); // Exibe o conteúdo do nó atual
			exibirPreOrden(atual.getNoEsq()); // Chama recursivamente o método para exibir a sub-árvore esquerda
			exibirPreOrden(atual.getNoDir()); // Chama recursivamente o método para exibir a sub-árvore direita
		}
	}

	private BinNo<T> inserir(BinNo<T> atual, BinNo<T> novoNo) {
		if (atual == null) { // Se o nó atual é nulo, significa que chegou no final de uma subárvore e insere
								// o novo nó
			return novoNo;
		} else if (novoNo.getConteudo().compareTo(atual.getConteudo()) < 0) { // Se o conteúdo do novo nó é menor que o
																				// conteúdo do nó atual, chama
																				// recursivamente o método passando o
																				// filho esquerdo do nó atual e o novo
																				// nó
			atual.setNoEsq(inserir(atual.getNoEsq(), novoNo));
		} else { // Caso contrário, chama recursivamente o método passando o filho direito do nó
					// atual e o novo nó
			atual.setNoDir(inserir(atual.getNoDir(), novoNo));
		}
		return atual; // Retorna o nó atual, que pode ser a raiz ou o filho de uma folha
	}
	

	public void remover(T conteudo) {
		try {
			BinNo<T> atual = this.raiz;
			BinNo<T> pai = null;
			BinNo<T> filho = null;
			BinNo<T> temp = null;

			// Procura pelo conteúdo a ser removido
			while (atual != null && !atual.getConteudo().equals(conteudo)) {
				pai = atual;
				if (conteudo.compareTo(atual.getConteudo()) < 0) {
					atual = atual.getNoEsq();
				} else {
					atual = atual.getNoDir();
				}
			}

			if (atual == null) {
				System.out.print("Conteudo nao encontrado. Bloco Try");
			}

			// Verifica se o conteúdo não foi encontrado
			if (atual == null) {
				System.out.print("Conteudo nao encontrado. Bloco Try");
			}

			// Verifica se é a raiz que será removida
			if (pai == null) {
				// Se o filho direito da raiz é nulo, a nova raiz será o filho esquerdo
				if (atual.getNoDir() == null) {
					this.raiz = atual.getNoEsq();
				}
				// Se o filho esquerdo da raiz é nulo, a nova raiz será o filho direito
				else if (atual.getNoEsq() == null) {
					this.raiz = atual.getNoDir();
				}
				// Se ambos os filhos da raiz existem
				else {
					// Percorre a árvore para encontrar o menor elemento à direita
					for (temp = atual, filho = atual.getNoEsq(); filho.getNoDir() != null; temp = filho, filho
							.getNoEsq()) {

						if (filho != atual.getNoEsq()) {
							temp.setNoDir(filho.getNoEsq());
							filho.setNoEsq(raiz.getNoEsq());
						}
						filho.setNoDir(raiz.getNoDir());
						raiz = filho;
					}
				}
			}
			// Se não é a raiz que será removida
			else if (atual.getNoDir() == null) {
				// Se o filho direito do nó atual é nulo, seu filho esquerdo irá ocupar seu
				// lugar
				if (pai.getNoEsq() == atual) {
					pai.setNoEsq(atual.getNoEsq());
				} else {
					pai.setNoDir(atual.getNoEsq());
				}

			} else if (atual.getNoEsq() == null) {
				// Se o filho esquerdo do nó atual é nulo, seu filho direito irá ocupar seu
				// lugar
				if (pai.getNoEsq() == atual) {
					// Se o nó atual é o filho esquerdo do seu pai, o filho direito do nó atual irá
					// se tornar o novo filho esquerdo do pai
					pai.setNoEsq(atual.getNoDir());
				} else {
					// Caso contrário, o filho direito do nó atual irá se tornar o novo filho
					// direito do pai
					pai.setNoDir(atual.getNoDir());
				}
			} else {
				// Se o nó atual possui um filho esquerdo, buscamos pelo seu maior valor à
				// direita
				for (temp = atual, filho = atual.getNoEsq(); filho.getNoDir() != null; temp = filho, filho = filho
						.getNoDir()) {
					// Movemos os nós para a direita
					if (filho != atual.getNoEsq()) {
						temp.setNoDir(filho.getNoEsq()); // O filho esquerdo do nó temporário aponta para o filho
															// esquerdo do filho atual
						filho.setNoEsq(atual.getNoEsq()); // O filho esquerdo do filho aponta para o filho esquerdo do
															// nó atual
					}
					filho.setNoDir(atual.getNoDir()); // O filho direito do filho aponta para o filho direito do nó
														// atual
					// Colocamos o filho em lugar do nó atual
					if (pai.getNoEsq() == atual) {
						pai.setNoEsq(filho); // Se o nó atual é o filho esquerdo do pai, colocamos o filho em seu lugar
					} else {
						pai.setNoDir(filho); // Caso contrário, colocamos o filho em lugar do filho direito do pai
					}
				}
			}
			// Tratamento de exceção para quando não é possível encontrar o conteúdo a ser
			// removido
		} catch (NullPointerException e) {
			System.out.print("Conteudo nao encontrado. Bloco Catch");
		}
	}

}
