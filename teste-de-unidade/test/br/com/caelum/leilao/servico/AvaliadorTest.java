package br.com.caelum.leilao.servico;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class AvaliadorTest {

	@Test
	public void verificaMaiorEMenorLance() {
		
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		Usuario jose = new Usuario("José");
		
		Leilao leilao = new Leilao("Carro top");
		
		leilao.propoe(new Lance(joao, 200.0));
		leilao.propoe(new Lance(maria, 600.0));
		leilao.propoe(new Lance(jose, 400.0));
		
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);
		
		assertEquals(600, avaliador.getMaiorLance(), 0.00001);
		assertEquals(200, avaliador.getMenorLance(), 0.00001);
	}
	
	@Test
	public void verificaMediaLances() {
		
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		Usuario jose = new Usuario("José");
		
		Leilao leilao = new Leilao("Carro top");
		
		leilao.propoe(new Lance(joao, 200.0));
		leilao.propoe(new Lance(maria, 600.0));
		leilao.propoe(new Lance(jose, 400.0));
		
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);
		
		assertEquals(400, avaliador.getMedia(), 0.00001);
	}
	
	@Test
	public void verificaApenasUmLance() {
		Usuario joao = new Usuario("João");
		
		Leilao leilao = new Leilao("Carro top");
		
		leilao.propoe(new Lance(joao, 200.0));
		
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);
		
		assertEquals(200, avaliador.getMaiorLance(), 0.00001);
		assertEquals(200, avaliador.getMenorLance(), 0.00001);
	}
	
	@Test
	public void verificaMaiorEMenorEmLancesAleatorios() {
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("Carro top");
		
		leilao.propoe(new Lance(joao, 200.0));
		leilao.propoe(new Lance(maria, 600.0));
		leilao.propoe(new Lance(joao, 100.0));
		leilao.propoe(new Lance(maria, 500.0));
		leilao.propoe(new Lance(joao, 780.0));
		leilao.propoe(new Lance(maria, 290.0));
		leilao.propoe(new Lance(joao, 145.0));
		leilao.propoe(new Lance(maria, 630.0));
		leilao.propoe(new Lance(joao, 780.0));
		leilao.propoe(new Lance(maria, 90.0));
		
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);
		
		assertEquals(780, avaliador.getMaiorLance(), 0.00001);
		assertEquals(90, avaliador.getMenorLance(), 0.00001);
	}
	
	@Test
	public void verificaLancesDecrescentes() {
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("Carro top");
		
		leilao.propoe(new Lance(joao, 400.0));
		leilao.propoe(new Lance(maria, 300.0));
		leilao.propoe(new Lance(joao, 200.0));
		leilao.propoe(new Lance(maria, 100.0));
		
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);
		
		assertEquals(400, avaliador.getMaiorLance(), 0.00001);
		assertEquals(100, avaliador.getMenorLance(), 0.00001);
	}
	
	@Test
	public void verificaTresMaiores() {
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("Carro top");
		
		leilao.propoe(new Lance(joao, 400.0));
		leilao.propoe(new Lance(maria, 300.0));
		leilao.propoe(new Lance(joao, 200.0));
		leilao.propoe(new Lance(maria, 100.0));
		leilao.propoe(new Lance(joao, 700.0));
		
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);
		
		assertEquals(3, avaliador.getTresMaiores().size(), 0.00001);
		assertEquals(700, avaliador.getTresMaiores().get(0).getValor(), 0.00001);
		assertEquals(400, avaliador.getTresMaiores().get(1).getValor(), 0.00001);
		assertEquals(300, avaliador.getTresMaiores().get(2).getValor(), 0.00001);
	}
	
	@Test
	public void verificaSomente2Maiores() {
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("Carro top");
		
		leilao.propoe(new Lance(joao, 400.0));
		leilao.propoe(new Lance(maria, 300.0));
		
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);
		
		assertEquals(2, avaliador.getTresMaiores().size(), 0.00001);
		assertEquals(400, avaliador.getTresMaiores().get(0).getValor(), 0.00001);
		assertEquals(300, avaliador.getTresMaiores().get(1).getValor(), 0.00001);
	}
	
	@Test
	public void verificaNenhumLance() {
		Leilao leilao = new Leilao("Carro top");
		
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);
		
		assertEquals(0, avaliador.getTresMaiores().size(), 0.00001);
	}
	
	@Test
	public void naoAceitaDoisLancesEmSequenciaDoMesmoUsuario() {
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("Carro top");
		
		leilao.propoe(new Lance(joao, 400.0));
		leilao.propoe(new Lance(maria, 300.0));
		
		// esse deve ser ignorado
		leilao.propoe(new Lance(maria, 350.0));
		
		assertEquals(2, leilao.getLances().size(), 0.00001);
		
		assertEquals(400, leilao.getLances().get(0).getValor(), 0.00001);
		assertEquals(joao, leilao.getLances().get(0).getUsuario());
		
		assertEquals(300, leilao.getLances().get(1).getValor(), 0.00001);
		assertEquals(maria, leilao.getLances().get(1).getUsuario());
	}
	
	@Test
	public void naoAceitaMaisQueCincoLancesDoMesmoUsuario() {
		
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("Carro top");
		
		leilao.propoe(new Lance(joao, 900.0));
		leilao.propoe(new Lance(maria, 800.0));
		
		leilao.propoe(new Lance(joao, 700.0));
		leilao.propoe(new Lance(maria, 600.0));
		
		leilao.propoe(new Lance(joao, 500.0));
		leilao.propoe(new Lance(maria, 400.0));
		
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(maria, 200.0));
		
		leilao.propoe(new Lance(joao, 100.0));
		leilao.propoe(new Lance(maria, 90.0));
		
		// esse deve ser ignorado
		leilao.propoe(new Lance(joao, 80.0));
		
		assertEquals(10, leilao.getLances().size());
		assertEquals(90, leilao.getLances().get(9).getValor(), 0.0001);
	}
	
	@Test
	public void verificaLanceDobrado() {
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("Carro top");
		
		leilao.propoe(new Lance(joao, 900.0));
		leilao.propoe(new Lance(maria, 800.0));
		
		leilao.propoe(new Lance(joao, 700.0));
		leilao.propoe(new Lance(maria, 600.0));
		
		leilao.propoe(new Lance(joao, 500.0));
		leilao.propoe(new Lance(maria, 400.0));
		
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(maria, 200.0));
		
		leilao.propoe(new Lance(joao, 100.0));
		leilao.propoe(new Lance(maria, 90.0));
		
		leilao.dobraLance(joao);
		
		assertEquals(11, leilao.getLances().size());
		int indexUltimoLance = leilao.getLances().size() - 1;
		assertEquals(200, leilao.getLances().get(indexUltimoLance).getValor(), 0.0001);
	}
}
