

public class SymbolTable_BST <Key extends Comparable <Key>> {
	Node raiz=null;
	
	private class Node {
		private Key key;
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
	    	ocorrenciasPalavra=1;
	    	filhoEsquerdo = null;
	    	filhoDireito = null;
	    }
	}
	
	public SymbolTable_BST() {
		
	}
	
	void insere(Key key, int tamanhoPalavra, boolean repeteLetras, int numVogaisSemRep) {
		if(raiz==null) {
			raiz = new Node(key,tamanhoPalavra,repeteLetras,numVogaisSemRep);
			return;
		}
		Node aux = raiz;
		while(aux!=null) {
			if(key.compareTo(aux.key)==0) {
				aux.ocorrenciasPalavra+=1;
				return;
			}
			else if(key.compareTo(aux.key)<0){
				if(aux.filhoEsquerdo==null) {
					aux.filhoEsquerdo= new Node(key,tamanhoPalavra,repeteLetras,numVogaisSemRep);
					aux.filhoEsquerdo.ocorrenciasPalavra=1;
					return;
				}
				aux = aux.filhoEsquerdo;
			}
			else {
				if(aux.filhoDireito==null) {
					aux.filhoDireito = new Node(key,tamanhoPalavra,repeteLetras,numVogaisSemRep);
					aux.filhoDireito.ocorrenciasPalavra=1;
					return;
				}
				aux = aux.filhoDireito;
			}
		}
	}
	void percorre(Node raiz) {
		if(raiz!=null) {
			percorre(raiz.filhoEsquerdo);
			System.out.print(raiz.key+" ");
			percorre(raiz.filhoDireito);
		}
	}
	void maisLongas() {
		int[] maior = new int[1];
		String [] nomes = new String[100000];
		int [] indices = new int[100000];
		encontraMaisLongas(raiz,maior,nomes,indices);
		System.out.println("As maiores palavras contém "+maior[0]+" letras, são elas:");
		for(int i = 0; i< indices[0];i++) {
			System.out.print(nomes[i]+" ");
		}
		System.out.println();
	}
	private void encontraMaisLongas(Node noAtual,int[] maior, String [] nomes, int[] indices) {
		if(noAtual!=null) {
			encontraMaisLongas(noAtual.filhoEsquerdo,maior,nomes,indices);
			if(noAtual.tamanhoPalavra == maior[0]) {
				nomes[indices[0]] = (String)noAtual.key;
				indices[0]++;
			}
			else if(noAtual.tamanhoPalavra > maior[0]) {
				indices[0] = 0;
				maior[0] = noAtual.tamanhoPalavra;
				nomes[indices[0]] = (String)noAtual.key;
				indices[0]++;
			}
			encontraMaisLongas(noAtual.filhoDireito,maior,nomes,indices);
		}
	}		
	void maisFrequentes() {
		int[] maior = new int[1];
		String [] nomes = new String[100000];
		int [] indices = new int[100000];
		encontraMaisFrequentes(raiz,maior,nomes,indices);
		System.out.println("As palavras mais frequentes aparecem "+(maior[0])+" vezes, são elas:");
		for(int i = 0; i< indices[0];i++) {
			System.out.print(nomes[i]+" ");
		}
		System.out.println();
	}
	private void encontraMaisFrequentes(Node noAtual,int[] maior, String [] nomes, int[] indices) {
		if(noAtual!=null) {
			encontraMaisFrequentes(noAtual.filhoEsquerdo,maior,nomes,indices);
			if(noAtual.ocorrenciasPalavra == maior[0]) {
				nomes[indices[0]] = (String)noAtual.key;
				indices[0]++;
			}
			else if(noAtual.ocorrenciasPalavra > maior[0]) {
				indices[0] = 0;
				maior[0] = noAtual.ocorrenciasPalavra;
				nomes[indices[0]] = (String)noAtual.key;
				indices[0]++;
			}
			encontraMaisFrequentes(noAtual.filhoDireito,maior,nomes,indices);
		}
	}	
	void maioresSemRep() {
		int[] maior = new int[1];
		String [] nomes = new String[100];
		int [] indices = new int[1];
		encontraMaioresSemRep(raiz,maior,nomes,indices);
		System.out.println("As maiores palavras que não repetem letra contém "+maior[0]+" letras, são elas:");
		for(int i = 0; i< indices[0];i++) {
			System.out.print(nomes[i]+" ");
		}
		System.out.println();
	}
	private void encontraMaioresSemRep(Node noAtual,int[] maior, String [] nomes, int[] indices) {
		if(noAtual!=null) {
			encontraMaioresSemRep(noAtual.filhoEsquerdo,maior,nomes,indices);
			if(noAtual.tamanhoPalavra == maior[0] && noAtual.repeteLetras==false) {
				nomes[indices[0]] = (String)noAtual.key;
				indices[0]++;
			}
			else if(noAtual.tamanhoPalavra > maior[0] && noAtual.repeteLetras==false) {
				indices[0] = 0;
				maior[0] = noAtual.tamanhoPalavra;
				nomes[indices[0]] = (String)noAtual.key;
				indices[0]++;
			}
			encontraMaioresSemRep(noAtual.filhoDireito,maior,nomes,indices);
		}
	}
	void menoresPalavrasSemRep() {
		int[] menor = new int[1];
		String [] nomes = new String[100];
		int [] indices = new int[1];
		int [] vogais = new int[1];
		menor[0] = raiz.tamanhoPalavra;
		encontraMenoresPalavrasSemRep(raiz,menor,nomes,indices,vogais);
		System.out.println("As menores palavras com mais vogais repetidas contém "+menor[0]+" letras, são elas:");
		for(int i = 0; i< indices[0];i++) {
			System.out.print(nomes[i]+" ");
		}
		System.out.println();
	}
	void encontraMenoresPalavrasSemRep(Node noAtual,int[] menor, String [] nomes, int[] indices,int[]vogais) {
		if(noAtual!=null) {
			encontraMenoresPalavrasSemRep(noAtual.filhoEsquerdo,menor,nomes,indices,vogais);
			if(noAtual.numVogaisSemRep > vogais[0]) {
				vogais[0] = noAtual.numVogaisSemRep;
				indices[0]=0;
				menor[0] = noAtual.tamanhoPalavra;
				nomes[indices[0]] = (String)noAtual.key;
				indices[0]++;
			}
			else if(noAtual.numVogaisSemRep == vogais[0]) {
				if(noAtual.tamanhoPalavra < menor[0]) {
					menor[0] = noAtual.tamanhoPalavra;
					indices[0] = 0;
					nomes[indices[0]] = (String)noAtual.key;
					indices[0]++;
				}
				else if(noAtual.tamanhoPalavra == menor[0]) {
					nomes[indices[0]] = (String)noAtual.key;
					indices[0]++;
				}
			}
			encontraMenoresPalavrasSemRep(noAtual.filhoDireito,menor,nomes,indices,vogais);
		}
	}
	void encontraPalavra(String s) {
		int ocorrencias[] = new int [1];
		retornaOcorrencias(raiz,s,ocorrencias);
		System.out.println("A palavra "+s+" aparece "+ocorrencias[0]+" vezes");
		
	}
	private void retornaOcorrencias(Node noAtual,String s, int[] ocorrencias) {
		if(noAtual==null) {
			ocorrencias[0] = 0;
			return;
		}
		Key aux = (Key)s;
		if(aux.compareTo(noAtual.key)==0) {
			
			ocorrencias[0] = noAtual.ocorrenciasPalavra;
			return;
		}
		if(aux.compareTo(noAtual.key) < 0){
			retornaOcorrencias(noAtual.filhoEsquerdo,s,ocorrencias);
		}
		else {
			retornaOcorrencias(noAtual.filhoDireito,s,ocorrencias);
		}
		
	}
}
