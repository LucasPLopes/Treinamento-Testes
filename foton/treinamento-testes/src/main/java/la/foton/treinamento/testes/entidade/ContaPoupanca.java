package la.foton.treinamento.testes.entidade;

import la.foton.treinamento.testes.comum.excecao.Mensagem;
import la.foton.treinamento.testes.comum.excecao.NegocioException;

public class ContaPoupanca extends Conta {
	
	private Integer diaDoAniversario;
	
	public ContaPoupanca() {
		this.tipo = TipoDaConta.POUPANCA;
	}

	@Override
	public void debita(Double valor) throws NegocioException {
		if (saldo < valor) {
			throw new NegocioException(Mensagem.SALDO_INSUFICIENTE);
		}

		saldo -= valor;		
	}

}
