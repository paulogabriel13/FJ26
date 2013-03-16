package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;

import br.com.caelum.notasfiscais.dao.DAO;
import br.com.caelum.notasfiscais.modelo.Produto;

@ViewScoped
public class ProdutoBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5646169330925910264L;
	private Produto produto = new Produto();
	private List<Produto> produtos;
	private double somatorio;

	public Produto getProduto() {
		return produto;
	}

	public void grava() {

		DAO<Produto> dao = new DAO<Produto>(Produto.class);
		if (produto.getId() == null) {
			dao.adiciona(produto);
		} else {
			dao.atualiza(produto);
		}
		produto = new Produto();
		this.produtos = dao.listaTodos();
		this.calculaSomatorio();
	}

	public void setProduto(Produto produto) {
		System.out.println(produto.getNome());
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		if (produtos == null) {
			produtos = new DAO<Produto>(Produto.class).listaTodos();
		}
		this.calculaSomatorio();
		return produtos;
	}

	public void remove(Produto produto) {
		DAO<Produto> dao = new DAO<>(Produto.class);
		dao.remove(produto);
		this.produtos = dao.listaTodos();
		this.calculaSomatorio();
	}

	public void calculaSomatorio() {

		somatorio = 0;

		for (Produto produto : produtos) {
			this.somatorio += produto.getPreco();
		}
	}

	public double getSomatorio() {
		return somatorio;
	}

	public void cancela() {
		System.out.println("Cancela a edição");
		this.produto = new Produto();
	}

}
