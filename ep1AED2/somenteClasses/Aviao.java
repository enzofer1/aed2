

public class Aviao {
	String id;
	// operacao == 0 -> Pouso, operacao == 1-> decolagem
	boolean decolagem;
	// o voo eh de emergencia se emergencia==1
	boolean emergencia;
	String airport;
	int tempoCombustivel;
	int tempoEspera = 0;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public boolean isDecolagem() {
		return decolagem;
	}
	public void setDecolagem(boolean decolagem) {
		this.decolagem = decolagem;
	}
	public boolean isEmergencia() {
		return emergencia;
	}
	public void setEmergencia(boolean emergencia) {
		this.emergencia = emergencia;
	}
	public String getAirport() {
		return airport;
	}
	public void setAirport(String airport) {
		this.airport = airport;
	}
	public int getTempoCombustivel() {
		return tempoCombustivel;
	}
	public void setTempoCombustivel(int tempoCombustivel) {
		this.tempoCombustivel = tempoCombustivel;
	}
	public int getTempoEspera() {
		return tempoEspera;
	}
	public void setTempoEspera(int tempoEspera) {
		this.tempoEspera = tempoEspera;
	}
	@Override
	public String toString() {
		return "Aviao [id=" + id + ", decolagem=" + decolagem + ", emergencia=" + emergencia + ", airport=" + airport
				+ ", tempoCombustivel=" + tempoCombustivel + ", tempoEspera=" + tempoEspera + "]";
	}
	
	
	
	
	
}
