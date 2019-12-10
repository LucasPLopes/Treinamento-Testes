package la.foton.treinamento.testes.entidade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import la.foton.treinamento.testes.comum.excecao.Mensagem;
import la.foton.treinamento.testes.comum.excecao.NegocioException;

public class ContaTest {

	private Conta conta;

	@Before
	public void setUp() {
		conta = new ContaCorrente();
		conta.credita(10.0);
	}

	@Test
	public void deveCreditarUmValorNaConta() {
		conta.credita(19.0);

		assertEquals(29.0, conta.getSaldo(), 0.0);
	}

	@Test
	public void deveDebitarUmValorNaContaQuePossuiSaldoSuficiente() {
		try {
			conta.debita(9.9);

			assertEquals(0.1, conta.getSaldo(), 0.01);
		} catch (NegocioException e) {
			fail();
		}
	}

	@Test
	public void naoDeveDebitarValorEmContaQueNaoPossuiSaldoSuficiente() {
		try {
			conta.debita(10.1);

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
			contaOrigem.transfere(9.99, conta);

			assertEquals(0.01, contaOrigem.getSaldo(), 0.001);
			assertEquals(19.99, conta.getSaldo(), 0.001);
		} catch (NegocioException e) {
			fail(e.getMensagem().getTexto());
		}
	}

}
