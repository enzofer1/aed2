

import java.util.Random;
import java.util.ArrayList;

public class SymbolTable_Treap<Key extends Comparable<Key>> {
	ArrayList <Key> maiores = new ArrayList<>();
	int tamanhoMaior=0;
	ArrayList <Key> frequentes = new ArrayList<>();
	int maiorFrequencia = 1;
	ArrayList <Key> maioresSemRep = new ArrayList<>();
	int maiorSemRep=0;
	ArrayList <Key> menoresVogaisSemRep = new ArrayList<>();
	int qtdVogais=0;
	int menorSemRep=100;
	
	public class Node{
		private Key key;
		private int prioridade;
		private int ocorrenciasPalavra;
	    private int tamanhoPalavra ;
	    private int numVogaisSemRep;
	    private boolean repeteLetras;
	    private Node filhoEsquerdo;
	    private Node filhoDireito;
	    
	    
	    public Node(Key key, int tamanhoPalavra, boolean repeteLetras, int numVogaisSemRep) {
	    	this.key = key;
	    	this.tamanhoPalavra=tamanhoPalavra;
	    	this.repeteLetras=repeteLetras;
	    	this.numVogaisSemRep=numVogaisSemRep;
	    	this.ocorrenciasPalavra=1;
	    	this.filhoEsquerdo = null;
	    	this.filhoDireito = null;
	    	Random rand = new Random();
	    	this.prioridade = rand.nextInt();
	    }
	}
	public Node rotacaoEsq(Node raiz) {
		Node d = raiz.filhoDireito;
		Node e = raiz.filhoDireito.filhoEsquerdo;
		
		d.filhoEsquerdo=raiz;
		raiz.filhoDireito=e;
		
		return d;
	}
	public Node rotacaoDir(Node raiz) {
		Node e = raiz.filhoEsquerdo;
		Node d = raiz.filhoEsquerdo.filhoDireito;
		
		e.filhoDireito = raiz;
		raiz.filhoEsquerdo = d;
		
		return e;
	}
	public Node insere(Node raiz,Key key, int tamanhoPalavra, boolean repeteLetras, int numVogaisSemRep) {
		if(raiz == null) {
			Node novoNo = new Node(key,tamanhoPalavra,repeteLetras,numVogaisSemRep);
			if(maiorFrequencia==1) {
				frequentes.add(key);
			}
			if(tamanhoPalavra>tamanhoMaior) {
				tamanhoMaior = tamanhoPalavra;
				maiores.clear();
				maiores.add(key);
			}
			else if(tamanhoPalavra==tamanhoMaior) {
				maiores.add(key);
			}
			if(repeteLetras==false){
				if(maiorSemRep<tamanhoPalavra) {
					maiorSemRep = tamanhoPalavra;
					maioresSemRep.clear();
					maioresSemRep.add(key);
				}
				else if(maiorSemRep == tamanhoPalavra) {
					maioresSemRep.add(key);
				}
			}
			if(numVogaisSemRep > qtdVogais) {
				qtdVogais = numVogaisSemRep;
				menoresVogaisSemRep.clear();
				menoresVogaisSemRep.add(key);
			}
			else if(numVogaisSemRep == qtdVogais) {
				if(tamanhoPalavra<menorSemRep) {
					menorSemRep = tamanhoPalavra;
					menoresVogaisSemRep.clear();
					menoresVogaisSemRep.add(key);
				}
				else if(tamanhoPalavra==menorSemRep) {
					menoresVogaisSemRep.add(key);				
				}
			}
			return novoNo;
		}
		if(key.compareTo(raiz.key)<0) {
			raiz.filhoEsquerdo = insere(raiz.filhoEsquerdo,key,tamanhoPalavra,repeteLetras,numVogaisSemRep);
			
			if(raiz.filhoEsquerdo!=null && raiz.filhoEsquerdo.prioridade>raiz.prioridade) {
				raiz = rotacaoDir(raiz);
			}
		}
		else if(key.compareTo(raiz.key)>0) {
			raiz.filhoDireito = insere(raiz.filhoDireito,key,tamanhoPalavra,repeteLetras,numVogaisSemRep);
			
			if(raiz.filhoDireito!=null && raiz.filhoDireito.prioridade>raiz.prioridade) {
				raiz = rotacaoEsq(raiz);
			}
		}
		else {
			raiz.ocorrenciasPalavra++;
			if(maiorFrequencia < raiz.ocorrenciasPalavra) {
				frequentes.clear();
				frequentes.add(key);
				maiorFrequencia = raiz.ocorrenciasPalavra;
			}
			else if(maiorFrequencia == raiz.ocorrenciasPalavra) {
				frequentes.add(key);
			}
		}
		return raiz;
	}
	public void inorder(Node raiz) {
		if(raiz!=null) {
			inorder(raiz.filhoEsquerdo);
			System.out.println(raiz.key);
			inorder(raiz.filhoDireito);
		}
	}
	public void maisLongas() {
		System.out.println("As palavra(s) mais longa(s) contém "+tamanhoMaior+" letras, elas são:");
		for(Key objetos : maiores) {
			System.out.print(objetos+" ");
		}
		System.out.println();
	}
	public void maisFrequentes() {
		System.out.println("As palavra(s) mais frequente(s) contém "+maiorFrequencia+" letras, elas são:");
		for(Key objetos : frequentes) {
			System.out.print(objetos+" ");
		}
		System.out.println();
	}
	public void maioresSemRep() {
		System.out.println("As maior(es) palavra(s) sem repetição contém "+maiorSemRep+" letras, elas são:");
		for(Key objetos: maioresSemRep) {
			System.out.println(objetos);
		}
	}
	public void menoresPalavrasSemRep() {
		System.out.println("As menor(es) palavra(s) com mais vogais contém "+qtdVogais+" vogais, elas são:");
		for(Key objetos: menoresVogaisSemRep) {
			System.out.print(objetos+" ");
		}
		System.out.println();
	}
	public void encontraPalavra(Key s, Node raiz) {
		helpPalavra(s,raiz);
	}
	private void helpPalavra(Key s, Node raiz) {
		if(raiz==null) {
			System.out.println("A palavra "+s+" apareceu 0 vezes");
			return;
		}
		else if(s.compareTo(raiz.key)==0) {
			System.out.println("A palavra "+s+" aparece "+raiz.ocorrenciasPalavra+ "vezes");
			return;
		}
		else {
			if(s.compareTo(raiz.key)<0) {
				helpPalavra(s,raiz.filhoEsquerdo);
			}
			else {
				helpPalavra(s, raiz.filhoDireito);
			}
		}
	}
}
