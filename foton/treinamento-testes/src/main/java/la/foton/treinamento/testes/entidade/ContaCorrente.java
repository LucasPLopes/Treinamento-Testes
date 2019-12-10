package la.foton.treinamento.testes.entidade;

import la.foton.treinamento.testes.comum.excecao.Mensagem;
import la.foton.treinamento.testes.comum.excecao.NegocioException;

public class ContaCorrente extends Conta {

	private Double limiteDoChequeEspecial;

	public ContaCorrente() {
		this.tipo = TipoDaConta.CORRENTE;
		this.limiteDoChequeEspecial = 0.0;
	}

	@Override
	public void debita(Double valor) throws NegocioException {
		if (saldo + limiteDoChequeEspecial < valor) {
			throw new NegocioException(Mensagem.SALDO_INSUFICIENTE);
		}

		saldo = saldo - valor;
	}

}
