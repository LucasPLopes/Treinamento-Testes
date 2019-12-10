package la.foton.treinamento.testes.entidade;

import la.foton.treinamento.testes.comum.excecao.Mensagem;
import la.foton.treinamento.testes.comum.excecao.NegocioException;

public abstract class Conta {

	private Integer numero;

	private Integer agencia;

	private Cliente titular;

	protected Double saldo;

	private EstadoDaConta estado;

	public EstadoDaConta getEstado() {
		return estado;
	}

	protected TipoDaConta tipo;

	public Conta() {
		this.saldo = 0.0;
	}

	public void credita(Double valor) {
		saldo = saldo + valor;
	}

	public abstract void debita(Double valor) throws NegocioException;

	public void transfere(Double valor, Conta contaDestino) throws NegocioException {
		//Template Method
		this.debita(valor);

		contaDestino.credita(valor);
	}

	public void encerra() throws NegocioException {
		if (this.saldo > 0) {
			throw new NegocioException(Mensagem.CONTA_NAO_PODE_SER_ENCERRADA);
		}

		if (EstadoDaConta.ENCERRADA.equals(estado)) {
			throw new NegocioException(Mensagem.CONTA_JA_ENCERRADA);
		}

		encerrada();
	}

	private void encerrada() {
		this.estado = EstadoDaConta.ENCERRADA;
	}

	public Double getSaldo() {
		return saldo;
	}

}
