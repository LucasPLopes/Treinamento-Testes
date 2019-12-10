package la.foton.treinamento.testes.comum.excecao;

public enum Mensagem {

	SALDO_INSUFICIENTE("Saldo Insuficiente"), //
	CONTA_JA_ENCERRADA("Conta já encerrada"), //
	CONTA_NAO_PODE_SER_ENCERRADA("Conta não pode ser encerrada por possuir saldo");

	private String texto;

	private Mensagem(String texto) {
		this.texto = texto;
	}

	public String getTexto() {
		return texto;
	}

}
