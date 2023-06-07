
import java.util.Random;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

public class Aeroporto {
	public static void main(String [] args) {
		//instanciando objetos que serão úteis durante a execução
		Random rand = new Random();
		rand.setSeed(6);
		int T = rand.nextInt(1, 50); // TEMPO TOTAL DA SIMULAÇÃO
		
		// criando o vetor de aviões, pistas e as filas
		Aviao [] avioes = new Aviao[10000];
		Pista [] pistas = new Pista[3];
		criaPistas(pistas);
		
		Queue <Aviao> fila1 = new LinkedList<>();
		Queue <Aviao> fila2 = new LinkedList<>();
		Queue <Aviao> fila3 = new LinkedList<>();
		Queue <Aviao> fila4 = new LinkedList<>();
		
		//iniciando a simulação
		int index = 0;
		mensagemBoasVindas();
		while(T > 0) {
			index = geraAvioes(index,avioes,fila1,fila2,fila3,fila4);
			printaStatus(pistas,fila1,fila2,fila3,fila4,T,avioes);
			realizaOperacoes(pistas,fila1,fila2,fila3,fila4,T,avioes);
			atualizaPistas(pistas);
			reorganizaFilas(fila1,fila2,fila3,fila4);
			mensagemFinal(T);
			T--;
		}
	}
	public static int geraAvioes(int index, Aviao avioes[], Queue <Aviao> fila1, Queue <Aviao> fila2, Queue <Aviao> fila3, Queue <Aviao> fila4) {
		Random rand = new Random();
		int novosAvioes = sorteiaQtdNovosAvioes(index+2);
		System.out.println("Aviões que acabaram de chegar:");
		for(int i = 0; i<novosAvioes;i++) {
			int probPouso = rand.nextInt(101);
			int probEmergencia = rand.nextInt(10);
			criaAviao(index,avioes,probPouso,probEmergencia);
			int encontraPrioridade = encontraPrioridade(avioes,index);
			if(encontraPrioridade==4) {
				fila4.add(avioes[index]);
			}
			else if(encontraPrioridade==3) {
				fila3.add(avioes[index]);
			}
			else {
				fila1.add(avioes[index]);
			}
			index++;
			System.out.println(avioes[index-1].toString());
		}
		return index;
	}
	public static void criaAviao(int index, Aviao avioes[],int probPouso,int probEmergencia){
		/* GERANDO O ID DO AVIAO */
		avioes[index] = new Aviao();
		avioes[index].setId(gerarID(index));
		
		/* GERANDO OPERAÇAO ( POUSO OU DECOLAGEM )*/
		Random r = new Random();
		r.setSeed(index);
		if (r.nextInt(101) <= probPouso) {
			avioes[index].setDecolagem(false);
		}
		else {
			avioes[index].setDecolagem(true);
		}
		
		/*GERANDO CODIGO DO AEROPORTO DE CHEGADA/DESTINO*/
		if(avioes[index].isDecolagem()) {
			avioes[index].setAirport(gerarCodigoAeroporto(index));
		}
		else {
			avioes[index].setAirport("GRU");
		}
		
		//GERANDO STATUS EMERGENCIA
		if(r.nextInt(101) <= probEmergencia) {
			avioes[index].setEmergencia(true);
		}
		else {
			avioes[index].setEmergencia(false);
		}
		
		//GERANDO TEMPOCOMBUSTIVEL
		if(avioes[index].isDecolagem()) {
			avioes[index].setTempoCombustivel(r.nextInt(300, 1000));
		}
		else {
			avioes[index].setTempoCombustivel(r.nextInt(5, 10));
		}
		
		
	}
	public static String gerarID(int index)
	{
		String[] possiveisLetras = {"AR","AF","AA","ET","EY","QR","TK","TP","LH","KL","JJ"};
		Random r = new Random();
		r.setSeed(index);
		int i = r.nextInt(11);
		return possiveisLetras[i]+r.nextInt(10)+r.nextInt(10)+r.nextInt(10);
	}
	public static String gerarCodigoAeroporto(int index) {
		String[] possiveisAeroportos = {"EZE","SCL","ADD","FRA","CDG","JFK","DME","IST","TBS","VKO","LAX","LIS","DXB","DOH"};
		Random r = new Random();
		return possiveisAeroportos[r.nextInt(14)];
	}
	public static void criaPistas(Pista[] pistas) {
		pistas[0] = new Pista();
		pistas[1] = new Pista();
		pistas[2] = new Pista();
		pistas[0].setDisponivel(true);
		pistas[1].setDisponivel(true);
		pistas[2].setDisponivel(true);
		pistas[0].setId(1);
		pistas[1].setId(2);
		pistas[2].setId(3);
		pistas[0].setSomenteDecolagem(false);
		pistas[1].setSomenteDecolagem(false);
		pistas[2].setSomenteDecolagem(true);
	}
	public static int sorteiaQtdNovosAvioes(int seed) {
		Random r = new Random();
		r.setSeed(seed++);
		int sorteado = r.nextInt(1,100);
		if(sorteado <= 20) {
			return 1;
		}
		else if(sorteado <= 40) {
			return 1;
		}
		else if(sorteado<= 60){
			return 2;
		}
		else if(sorteado <= 80) {
			return 2;
		}
		else {
			return 2;
		}
	}
	public static void mensagemBoasVindas() {
		System.out.println("---------------------------------------------------------------------------------------------------------------------");
		System.out.println("BEM-VINDO AO AEROPORTO DE GUARULHOS(GRU)");
		System.out.println("Pressione ENTER para iniciar a simulação");
		System.out.println("---------------------------------------------------------------------------------------------------------------------");
		Scanner in = new Scanner(System.in);
		in.nextLine();
	}
	public static int encontraPrioridade(Aviao avioes[],int index) {
		if(avioes[index].getTempoCombustivel()==1) {
			return 4;
		}
		else if(avioes[index].isEmergencia()) {
			return 3;
		}
		return 1;
	}
	public static void printaStatus(Pista [] pistas, Queue<Aviao> fila1, Queue<Aviao> fila2, Queue<Aviao> fila3, Queue<Aviao> fila4,int t,Aviao [] avioes) {
		System.out.println();
		System.out.println("Status das pistas antes das operações:");
		for(int i = 0; i<3; i++) {
			System.out.print("Pista "+(i+1)+" -> ");
			if(pistas[i].isDisponivel()) {
				System.out.println("DISPONÍVEL");
			}
			else {
				System.out.println("INDISPONÍVEL, SERÁ LIBERADA EM "+pistas[i].getTempoLiberacao()+" UNIDADES DE TEMPO");
			}
		}
		System.out.println();
		System.out.println("Status das filas antes das operações (com os aviões que acabaram de chegar já enfileirados):");
		System.out.println();
		System.out.print("Fila 1 (prioridade mínima)                    -> ");
		for(Aviao teste : fila1) {
			System.out.print("["+teste.getId()+"],");
		}
		System.out.println();
		System.out.print("Fila 2 (decolagens que esperaram 10% ou mais) -> ");
		for(Aviao teste : fila2) {
			System.out.print("["+teste.getId()+"],");
		}
		System.out.println();
		System.out.print("Fila 3 (voos emergenciais)                    -> ");
		for(Aviao teste : fila3) {
			System.out.print("["+teste.getId()+"],");
		}
		System.out.println();
		System.out.print("Fila 4 (combustivel encerrando)               -> ");
		for(Aviao teste : fila4) {
			System.out.print("["+teste.getId()+"],");
		}
		System.out.println();
		System.out.println();
	}
	public static void realizaOperacoes(Pista [] pistas, Queue<Aviao> fila1, Queue<Aviao> fila2, Queue<Aviao> fila3, Queue<Aviao> fila4,int t,Aviao [] avioes) {
		System.out.println("Operações realizadas:");
		//verificando se há aviões na fila 4 (de emergência máxima)
		ArrayList <Aviao> avioesRemovidos1 = new ArrayList<>();
		ArrayList <Aviao> avioesRemovidos2 = new ArrayList<>();
		ArrayList <Aviao> avioesRemovidos3 = new ArrayList<>();
		ArrayList <Aviao> avioesRemovidos4 = new ArrayList<>();
		for(Aviao aviao: fila4) {
			if(pistas[0].isDisponivel()) {
				if(aviao.isDecolagem()) {
					System.out.println(aviao.getId()+" decolou na pista 1");
				}
				else {
					System.out.println(aviao.getId()+" pousou na pista 1");
				}
				pistas[0].setDisponivel(false);
				pistas[0].setTempoLiberacao(3);
				avioesRemovidos4.add(aviao);
			}
			else if(pistas[1].isDisponivel()) {
				if(aviao.isDecolagem()) {
					System.out.println(aviao.getId()+" decolou na pista 2");
				}
				else {
					System.out.println(aviao.getId()+" pousou na pista 2");
				}
				pistas[1].setDisponivel(false);
				pistas[1].setTempoLiberacao(3);
				avioesRemovidos4.add(aviao);
			}
			else if(pistas[2].isDisponivel()) {
				if(aviao.isDecolagem()) {
					System.out.println(aviao.getId()+" decolou na pista 3");
				}
				else {
					System.out.println(aviao.getId()+" pousou na pista 3");
				}
				pistas[2].setDisponivel(false);
				pistas[2].setTempoLiberacao(3);
				avioesRemovidos4.add(aviao);
			}
			else {
				System.out.println(aviao.getId()+" foi enviado para Congonhas(CGH) pois não conseguiremos pousá-lo a tempo");
				avioesRemovidos4.add(aviao);
			}
			
		}
		for(Aviao aviao: fila3) {
			if(pistas[0].isDisponivel()) {
				if(aviao.isDecolagem()) {
					System.out.println(aviao.getId()+" decolou na pista 1");
				}
				else {
					System.out.println(aviao.getId()+" pousou na pista 1");
				}
				pistas[0].setDisponivel(false);
				pistas[0].setTempoLiberacao(3);
				avioesRemovidos3.add(aviao);
			}
			else if(pistas[1].isDisponivel()) {
				if(aviao.isDecolagem()) {
					System.out.println(aviao.getId()+" decolou na pista 2");
				}
				else {
					System.out.println(aviao.getId()+" pousou na pista 2");
				}
				pistas[1].setDisponivel(false);
				pistas[1].setTempoLiberacao(3);
				avioesRemovidos3.add(aviao);
			}
			else if(pistas[2].isDisponivel()) {
				if(aviao.isDecolagem()) {
					System.out.println(aviao.getId()+" decolou na pista 3");
				}
				else {
					System.out.println(aviao.getId()+" pousou na pista 3");
				}
				pistas[2].setDisponivel(false);
				pistas[2].setTempoLiberacao(3);
				avioesRemovidos3.add(aviao);
			}
			
		}
		for(Aviao aviao: fila2) {
			if(pistas[0].isDisponivel()) {
				if(aviao.isDecolagem()) {
					System.out.println(aviao.getId()+" decolou na pista 1");
				}
				else {
					System.out.println(aviao.getId()+" pousou na pista 1");
				}
				pistas[0].setDisponivel(false);
				pistas[0].setTempoLiberacao(3);
				avioesRemovidos2.add(aviao);
			}
			else if(pistas[1].isDisponivel()) {
				if(aviao.isDecolagem()) {
					System.out.println(aviao.getId()+" decolou na pista 2");
				}
				else {
					System.out.println(aviao.getId()+" pousou na pista 2");
				}
				pistas[1].setDisponivel(false);
				pistas[1].setTempoLiberacao(3);
				avioesRemovidos2.add(aviao);
			}
			else if(pistas[2].isDisponivel() && aviao.isDecolagem()) {
				if(aviao.isDecolagem()) {
					System.out.println(aviao.getId()+" decolou na pista 3");
				}
				else {
					System.out.println(aviao.getId()+" pousou na pista 3");
				}
				pistas[2].setDisponivel(false);
				pistas[2].setTempoLiberacao(3);
				avioesRemovidos2.add(aviao);
			}
			
		}
		for(Aviao aviao: fila1) {
			if(pistas[0].isDisponivel()) {
				if(aviao.isDecolagem()) {
					System.out.println(aviao.getId()+" decolou na pista 1");
				}
				else {
					System.out.println(aviao.getId()+" pousou na pista 1");
				}
				pistas[0].setDisponivel(false);
				pistas[0].setTempoLiberacao(3);
				avioesRemovidos1.add(aviao);
			}
			else if(pistas[1].isDisponivel()) {
				if(aviao.isDecolagem()) {
					System.out.println(aviao.getId()+" decolou na pista 2");
				}
				else {
					System.out.println(aviao.getId()+" pousou na pista 2");
				}
				pistas[1].setDisponivel(false);
				pistas[1].setTempoLiberacao(3);
				avioesRemovidos1.add(aviao);
			}
			else if(pistas[2].isDisponivel() && aviao.isDecolagem()) {
				if(aviao.isDecolagem()) {
					System.out.println(aviao.getId()+" decolou na pista 3");
				}
				else {
					System.out.println(aviao.getId()+" pousou na pista 3");
				}
				pistas[2].setDisponivel(false);
				pistas[2].setTempoLiberacao(3);
				avioesRemovidos1.add(aviao);
			}
			
		}
		
		for(Aviao aviao : avioesRemovidos1) {
			fila1.remove(aviao);
		}
		for(Aviao aviao : avioesRemovidos2) {
			fila2.remove(aviao);
		}
		for(Aviao aviao : avioesRemovidos3) {
			fila3.remove(aviao);
		}
		for(Aviao aviao : avioesRemovidos4) {
			fila4.remove(aviao);
		}
		avioesRemovidos1.clear();
		avioesRemovidos2.clear();
		avioesRemovidos3.clear();
		avioesRemovidos4.clear();
		
		for(Aviao aviao : fila1) {
			aviao.setTempoEspera(aviao.getTempoEspera()+1);
			aviao.setTempoCombustivel(aviao.getTempoCombustivel()-1);
		}
		for(Aviao aviao : fila2) {
			aviao.setTempoEspera(aviao.getTempoEspera()+1);
			aviao.setTempoCombustivel(aviao.getTempoCombustivel()-1);
		}
		for(Aviao aviao : fila3) {
			aviao.setTempoEspera(aviao.getTempoEspera()+1);
			aviao.setTempoCombustivel(aviao.getTempoCombustivel()-1);
		}
		for(Aviao aviao : fila4) {
			aviao.setTempoEspera(aviao.getTempoEspera()+1);
			aviao.setTempoCombustivel(aviao.getTempoCombustivel()-1);
		}
	}
	public static void atualizaPistas(Pista[] pistas) {
		if(pistas[0].getTempoLiberacao()>0) {
			pistas[0].setTempoLiberacao(pistas[0].getTempoLiberacao()-1);
			if(pistas[0].getTempoLiberacao()==0) {
				pistas[0].setDisponivel(true);
			}
		}
		if(pistas[1].getTempoLiberacao()>0) {
			pistas[1].setTempoLiberacao(pistas[1].getTempoLiberacao()-1);
			if(pistas[1].getTempoLiberacao()==0) {
				pistas[1].setDisponivel(true);
			}
		}
		if(pistas[2].getTempoLiberacao()>0) {
			pistas[2].setTempoLiberacao(pistas[2].getTempoLiberacao()-1);
			if(pistas[2].getTempoLiberacao()==0) {
				pistas[2].setDisponivel(true);
			}
		}
		
	}
	public static void reorganizaFilas(Queue<Aviao> fila1,Queue<Aviao> fila2,Queue<Aviao> fila3,Queue<Aviao> fila4) {
		/*
		 POSSÍVEIS CASOS:
		 1- AVIÃO DA FILA 1 PASSAR PARA A FILA 2 (SE FOR UMA DECOLAGEM E O TEMPO DE ESPERA EXCEDER 10% DO COMBUSTIVEL
		 2- AVIÃO DA FILA 1 PASSAR PARA A FILA 4 (COMBUSTÍVEL QUASE ACABANDO)
		 3- AVIÃO DA FILA 3 PASSAR PARA A FILA 4 (COMBUSTÍVEL QUASE ACABANDO)
		 */
		ArrayList<Aviao> avioesCaso1 = new ArrayList<>();
		ArrayList<Aviao> avioesCaso2 = new ArrayList<>();
		ArrayList<Aviao> avioesCaso3 = new ArrayList<>();
		//CASO 1:
		for(Aviao aviao: fila1) {
			if((aviao.getTempoEspera() >= aviao.getTempoCombustivel()*0.1) && aviao.isDecolagem()) {
				avioesCaso1.add(aviao);
			}
		}
		for(Aviao aviao: avioesCaso1) {
			fila2.add(aviao);
			fila1.remove(aviao);
			System.out.println(aviao.getId()+" movido para a fila 2");
		}
		//CASO 2:
		for(Aviao aviao: fila1) {
			if(aviao.getTempoCombustivel()==1) {
				avioesCaso2.add(aviao);
			}
		}
		for(Aviao aviao: avioesCaso2) {
			fila4.add(aviao);
			fila1.remove(aviao);
			System.out.println(aviao.getId()+" movido para a fila 4");
		}
		//CASO 3:
		for(Aviao aviao: fila3) {
			if(aviao.getTempoCombustivel()==1) {
				avioesCaso3.add(aviao);
			}
		}
		for(Aviao aviao: avioesCaso3) {
			fila4.add(aviao);
			fila3.remove(aviao);
			System.out.println(aviao.getId()+" movido para a fila 4");
		}
	}
	public static void mensagemFinal(int T) {
		System.out.println();
		System.out.println("TEMPO RESTANTE = "+(T-1));
		System.out.println("PRESSIONE ENTER TECLA PARA AVANÇAR UMA UNIDADE DE TEMPO");
		System.out.println("---------------------------------------------------------------------------------------------------------------------");
		Scanner in = new Scanner(System.in);
		in.nextLine();
	}
}
