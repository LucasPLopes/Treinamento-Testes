package la.foton.treinamento.testes.comum.excecao;

public class NegocioException extends Exception {

	private static final long serialVersionUID = 1L;

	private Mensagem mensagem;

	public NegocioException(Mensagem mensagem) {
		super(mensagem.getTexto());

		this.mensagem = mensagem;
	}

	public Mensagem getMensagem() {
		return mensagem;
	}

}
