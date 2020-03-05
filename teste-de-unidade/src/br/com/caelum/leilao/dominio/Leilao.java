package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao {

	private String descricao;
	private List<Lance> lances;

	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}

	public void propoe(Lance lance) {
		if (lances.isEmpty() || usuarioValido(lance.getUsuario())) {
			lances.add(lance);
		}
	}

	private boolean usuarioValido(Usuario usuario) {
		return (!usuario.equals(ultimoLance().getUsuario()) && qtdLancesDoUsuario(usuario) < 5);
	}

	private int qtdLancesDoUsuario(Usuario usuario) {
		int qtd = 0;
		for (Lance l : lances) {
			if (l.getUsuario().equals(usuario))
				qtd++;
		}
		return qtd;
	}

	private Lance ultimoLance() {
		return lances.get(lances.size() - 1);
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}

	public void dobraLance(Usuario usuario) {
		
		Lance ultimo = ultimoLanceDoUsuario(usuario);
		
		if(ultimo != null) {
			propoe(new Lance(usuario, ultimo.getValor() * 2));
		}
        
	}

	private Lance ultimoLanceDoUsuario(Usuario usuario) {
		Lance ultimo = null;
		
		for(Lance lance : lances) {
            if(lance.getUsuario().equals(usuario)) ultimo = lance;
        }
		
		return ultimo;
	}

	
}
