package ep1AED2;

public class Pista {
	int id;
	boolean disponivel;
	boolean somenteDecolagem;
	int tempoLiberacao = 0;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isDisponivel() {
		return disponivel;
	}
	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}
	public boolean isSomenteDecolagem() {
		return somenteDecolagem;
	}
	public void setSomenteDecolagem(boolean somenteDecolagem) {
		this.somenteDecolagem = somenteDecolagem;
	}
	public int getTempoLiberacao() {
		return tempoLiberacao;
	}
	public void setTempoLiberacao(int tempoLiberacao) {
		this.tempoLiberacao = tempoLiberacao;
	}
	@Override
	public String toString() {
		return "Pista [id=" + id + ", disponivel=" + disponivel + ", somenteDecolagem=" + somenteDecolagem
				+ ", tempoLiberacao=" + tempoLiberacao + "]";
	}

}
