
import java.util.ArrayList;


public class SymbolTable_RubroNegra <Key extends Comparable <Key>> {
	Node raiz=null;
	ArrayList <Key> maiores = new ArrayList<>();
	int tamanhoMaior=0;
	ArrayList <Key> frequentes = new ArrayList<>();
	int maiorFrequencia = 1;
	ArrayList <Key> maioresSemRep = new ArrayList<>();
	int maiorSemRep=0;
	ArrayList <Key> menoresVogaisSemRep = new ArrayList<>();
	int qtdVogais=0;
	int menorSemRep=100;
	private class Node {
		private Key key;
		private int ocorrenciasPalavra;
	    private int tamanhoPalavra ;
	    private int numVogaisSemRep;
	    private boolean repeteLetras;
	    private Node filhoEsquerdo;
	    private Node filhoDireito;
	    private Node pai;
	    private char cor;
	    
	    public Node(Key key, int tamanhoPalavra, boolean repeteLetras, int numVogaisSemRep) {
	    	this.key = key;
	    	this.tamanhoPalavra=tamanhoPalavra;
	    	this.repeteLetras=repeteLetras;
	    	this.numVogaisSemRep=numVogaisSemRep;
	    	ocorrenciasPalavra=1;
	    	filhoEsquerdo = null;
	    	filhoDireito = null;
	    	pai = null;
	    	cor = 'V';
	    }
	      	
	}
	  	Node rotacaoEsq(Node node) {
	    	Node x = node.filhoDireito;
	    	Node y = node.filhoEsquerdo;
	    	x.filhoEsquerdo = node;
	    	node.filhoDireito = y;
	    	node.pai = x;
	    	if(y!=null) {
	    		y.pai = node;
	    	}
	    	return x;
	    }
	  	Node rotacaoDir(Node node) {
	    	Node x = node.filhoEsquerdo;
	    	Node y = x.filhoDireito;
	    	x.filhoDireito = node;
	    	node.filhoEsquerdo = y;
	    	node.pai = x;
	    	if(y!=null) {
	    		y.pai = node;
	    	}
	    	return x;
	    }
	    // flags para saber se as rotações foram feitas
	    boolean ee = false;
	    boolean dd = false;
	    boolean ed = false;
	    boolean de = false;
	    
	    Node insereNode(Node node,Key key, int tamanhoPalavra, boolean repeteLetras, int numVogaisSemRep) {
	    	boolean vv = false; // true se pai e filho são vermelhos
	    	
	    	if(node==null) {
	    		return(new Node(key,tamanhoPalavra,repeteLetras,numVogaisSemRep));
	    	}
	    	else if(key.compareTo(node.key)<0) {
	    		node.filhoEsquerdo = insereNode(node.filhoEsquerdo,key,tamanhoPalavra,repeteLetras,numVogaisSemRep);
	    		node.filhoEsquerdo.pai = node;
	    		if(node!=raiz) {
	    			if(node.cor=='V' && node.filhoEsquerdo.cor=='V') {
	    				vv = true;
	    			}
	    		}
	    	}
	    	else {
	    		node.filhoDireito = insereNode(node.filhoDireito,key,tamanhoPalavra,repeteLetras,numVogaisSemRep);
	    		node.filhoDireito.pai=node;
	    		if(node!=raiz) {
	    			if(node.cor=='V' && node.filhoDireito.cor == 'V') {
	    				vv = true;
	    			}
	    		}
	    	}
	    	
	    	if(this.ee) {
	    		node = rotacaoEsq(node);
	    		node.cor='P';
	    		node.filhoEsquerdo.cor='V';
	    		this.ee=false;
	    	}
	    	else if(this.dd) {
	    		node = rotacaoDir(node);
	    		node.cor='P';
	    		node.filhoDireito.cor='V';
	    		this.dd = false;
	    	}
	    	else if(this.de) {
	    		node.filhoDireito= rotacaoDir(node.filhoDireito);
	    		node.filhoDireito.pai=node;
	    		node = rotacaoEsq(node);
	    		node.cor='P';
	    		node.filhoEsquerdo.cor='V';
	    		this.de = false;
	    	}
	    	else if(this.ed) {
	    		node.filhoEsquerdo = rotacaoEsq(node.filhoEsquerdo);
	    		node.filhoEsquerdo.pai= node;
	    		node = rotacaoDir(node);
	    		node.cor='P';
	    		node.filhoDireito.cor='V';
	    		this.ed=false;
	    	}
	    	if(vv) {
	    		if(node.pai.filhoDireito==node) {
	    			if(node.pai.filhoEsquerdo==null || node.pai.filhoEsquerdo.cor=='P') {
	    				if(node.filhoEsquerdo!=null && node.filhoEsquerdo.cor=='V') {
	    					this.de=true;
	    				}
	    					else if(node.filhoDireito!=null && node.filhoDireito.cor=='V') {
	    						this.ee = true;
	    					}
	    				}
	    			else {
	    				node.pai.filhoEsquerdo.cor='P';
	    				node.cor='P';
	    				if(node.pai!=raiz) {
	    					node.pai.cor='V';
	    				}
	    			}
	    		}
	    		else {
	    			if(node.pai.filhoDireito==null || node.pai.filhoDireito.cor=='P') {
	    				if(node.filhoEsquerdo!=null && node.filhoEsquerdo.cor=='V') {
	    					this.dd = true;	
	    				}
	    				else if(node.filhoDireito!=null && node.filhoDireito.cor=='V') {
	    					this.ed=true;
	    				}
	    			}
	    			else {
	    				node.pai.filhoDireito.cor='P';
	    				node.cor='P';
	    				if(node.pai != raiz) {
	    					node.pai.cor='V';
	    				}
	    			}
	    		}
	    		vv=false;
	    	}
	    	return node;
	    }
	    void insere(Key key, int tamanhoPalavra, boolean repeteLetras, int numVogaisSemRep) {
	    	if(raiz==null) {
	    		raiz = new Node(key,tamanhoPalavra,repeteLetras,numVogaisSemRep);
	    		tamanhoMaior = tamanhoPalavra;
	    		maiores.add(key);
	    		frequentes.add(key);
	    		if(repeteLetras==false) {
	    			maioresSemRep.add(key);
	    			maiorSemRep=tamanhoPalavra;
	    		}
	    		menoresVogaisSemRep.add(key);
	    		qtdVogais=numVogaisSemRep;
	    		menorSemRep=tamanhoPalavra;
	    		
	    		raiz.cor='P';
	    	}
	    	else if(jaExiste(key)==false){
	    		raiz = insereNode(raiz,key,tamanhoPalavra,repeteLetras,numVogaisSemRep);
	    		 if(tamanhoMaior< tamanhoPalavra) {
	    			 tamanhoMaior = tamanhoPalavra;
	    			 maiores.clear();
	    			 maiores.add(key);
	    		 }
	    		 else if(tamanhoMaior== tamanhoPalavra) {
	    			 maiores.add(key);
	    		 }
	    		 if(repeteLetras==false) {
	    			 if(maiorSemRep<tamanhoPalavra) {
	    				 maiorSemRep=tamanhoPalavra;
	    				 maioresSemRep.clear();
	    				 maioresSemRep.add(key);
	    			 }
	    			 else if(maiorSemRep==tamanhoPalavra) {
	    				 maioresSemRep.add(key);
	    			 }
	    		 }
	    		 if(numVogaisSemRep>qtdVogais) {
	    			 menorSemRep=tamanhoPalavra;
	    			 qtdVogais = numVogaisSemRep;
	    			 menoresVogaisSemRep.clear();
	    			 menoresVogaisSemRep.add(key);
	    		 }
	    		 else if(numVogaisSemRep==qtdVogais) {
	    			 if(tamanhoPalavra>numVogaisSemRep) {
	    				 menorSemRep = tamanhoPalavra;
	    				 menoresVogaisSemRep.clear();
	    				 menoresVogaisSemRep.add(key);
	    			 }
	    			 else if(tamanhoPalavra==numVogaisSemRep) {
	    				 menoresVogaisSemRep.add(key);
	    			 }
	    		 }
	    	}
	    }
	    void inorder(Node node) {
	    	if(node!=null) {
	    		inorder(node.filhoEsquerdo);
	    		System.out.println(node.key);
	    		inorder(node.filhoDireito);
	    	}
	    }
	    void percorre() {
	    	inorder(raiz);
	    }
	    boolean aux = false;
	    boolean jaExiste(Key key) {
	    	aux = false;
	    	auxJaExiste(raiz,key);
	    	return aux;
	    }
	    void auxJaExiste(Node node, Key key) {
	    	if(node==null || aux == true) {
	    		return;
	    	}
	    	
	    	if(node.key.compareTo(key)<0) {
	    		auxJaExiste(node.filhoEsquerdo,key);
	    	}
	    	else if(node.key.compareTo(key)>0) {
	    		auxJaExiste(node.filhoDireito,key);
	    	}
	    	else {
	    		aux=true;
	    		node.ocorrenciasPalavra+=1;
	    		if(node.ocorrenciasPalavra>maiorFrequencia) {
	    			maiorFrequencia = node.ocorrenciasPalavra;
	    			frequentes.clear();
	    			frequentes.add(node.key);
	    		}
	    		else if(node.ocorrenciasPalavra==maiorFrequencia) {
	    			frequentes.add(node.key);
	    		}
	    	}
	    }
	    void maisLongas() {
	    	
			System.out.println("As maiores palavras contém "+tamanhoMaior+" letras, são elas:");
			for(Key k: maiores) {
				System.out.print(k+" ");
			}
			System.out.println();
		}
	    void maisFrequentes() {
			System.out.println("As palavras mais frequentes aparecem "+maiorFrequencia+" vezes, são elas:");
			for(Key k: frequentes) {
				System.out.print(k+" ");
			}
			System.out.println();
		}
		void maioresSemRep() {
			System.out.println("As maiores palavras que não repetem letra contém "+maiorSemRep+" letras, são elas:");
			for(Key k:maioresSemRep) {
				System.out.print(k+" ");
			}
			System.out.println();
		}
		void menoresPalavrasSemRep() {

			System.out.println("As menores palavras com mais vogais repetidas contém "+menorSemRep+" letras, são elas:");
			for(Key k: menoresVogaisSemRep) {
				System.out.print(k+" ");
			}
			System.out.println();
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
