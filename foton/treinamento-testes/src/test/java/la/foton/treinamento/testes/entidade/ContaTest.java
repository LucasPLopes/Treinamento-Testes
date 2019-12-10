package la.foton.treinamento.testes.entidade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import la.foton.treinamento.testes.comum.excecao.Mensagem;
import la.foton.treinamento.testes.comum.excecao.NegocioException;

public class ContaTest {

	private Conta contaCorrente;
	private Conta contaPoupanca;

	@Before
	public void setUp() {
		contaCorrente = new ContaCorrente();
		contaCorrente.credita(10.0);
		
		contaPoupanca = new ContaPoupanca();
		contaPoupanca.credita(10.0);
	}

	@Test
	public void deveCreditarUmValorNaConta() {
		contaCorrente.credita(19.0);

		assertEquals(29.0, contaCorrente.getSaldo(), 0.0);
	}

	@Test
	public void deveDebitarUmValorNaContaQuePossuiSaldoSuficiente() {
		try {
			contaCorrente.debita(9.9);

			assertEquals(0.1, contaCorrente.getSaldo(), 0.01);
		} catch (NegocioException e) {
			fail();
		}
	}

	@Test
	public void naoDeveDebitarValorEmContaQueNaoPossuiSaldoSuficiente() {
		try {
			contaCorrente.debita(10.1);

			fail();
		} catch (NegocioException e) {
			assertEquals(Mensagem.SALDO_INSUFICIENTE, e.getMensagem());
		}
	}

	@Test
	public void deveTransferirValoresEntreContas() {
		Conta contaOrigem = new ContaPoupanca();
		contaOrigem.credita(10.00);

		try {
			contaOrigem.transfere(9.99, contaCorrente);

			assertEquals(0.01, contaOrigem.getSaldo(), 0.001);
			assertEquals(19.99, contaCorrente.getSaldo(), 0.001);
		} catch (NegocioException e) {
			fail(e.getMensagem().getTexto());
		}
	}
	@Test
	public void naoDeveDebitar() {
		try {
			contaPoupanca.debita(100.0);
			fail();
		} catch (NegocioException e) {
			assertEquals(Mensagem.SALDO_INSUFICIENTE,e.getMensagem());
		}
	}
	@Test
	public void tentarEncerrarCSaldo() {
		try {
			contaCorrente.encerra();
			fail();
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			assertEquals(Mensagem.CONTA_NAO_PODE_SER_ENCERRADA, e.getMensagem());
		}
	}

}
