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

}
