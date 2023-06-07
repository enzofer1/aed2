

import java.util.Scanner;

import ep2AED2.SymbolTable_Treap.Node;

public class Main {
	public static int encontraVogais (String s) {
	    boolean temA = false;
	    boolean temE = false;
	    boolean temI = false;
	    boolean temO = false;
	    boolean temU = false;
	    int qtd=0;
	    	
	    for (int i = 0; i<s.length();i++) {
	    	if((s.charAt(i)=='A'|| s.charAt(i)=='a') && temA==false) {
	    		temA = true;
	    		qtd++;
	    	}
	    	if((s.charAt(i)=='E'|| s.charAt(i)=='e')  && temE==false) {
	    		temE = true;
	    		qtd++;
	    	}
	    	if((s.charAt(i)=='I'|| s.charAt(i)=='i') && temI==false) {
	    		temI = true;
	   			qtd++;
	   		}
	   		if((s.charAt(i)=='O'|| s.charAt(i)=='o')&& temO==false) {
	   			temO = true;
    			qtd++;
    		}
	    	if((s.charAt(i)=='U'|| s.charAt(i)=='u') && temU==false) {
	   			temU = true;
	   			qtd++;
	   		}
	   	}
    	return qtd;
    }
	public static boolean repete(String s) {
    	char [] letras = new char[32];
    	int letrasinseridas=0;
    	for(int i = 0; i<s.length();i++) {
    		for(int j = 0 ;j<letrasinseridas; j++) {
    			if(letras[j] == s.charAt(i)) {
    				return true;
    			}
    		}
    		letras[letrasinseridas] = s.charAt(i);
    		letrasinseridas++;
    	}
    	return false;
    }
	public static void rodaVO() {
		Scanner in = new Scanner(System.in);
		SymbolTable_VetorOrdenado<String>  vo = new SymbolTable_VetorOrdenado<>();
		int n = in.nextInt();
		in.nextLine();
		for(int i = 0; i<n; ) {
			String [] tokens = in.nextLine().split("\\s");
			for(String s:tokens){
				if(s.charAt(s.length()-1)==',' ||s.charAt(s.length()-1)=='.'|| s.charAt(s.length()-1)=='?'||s.charAt(s.length()-1)=='!' ) {
					s = s.substring(0, s.length()-1);
				}
	            vo.add(s.toLowerCase(), s.length(),encontraVogais(s),repete(s));
	            i++;
	        }
		}
		String [] operacoes = new  String[10];
		int indexOps=0;
		//System.out.println("Insira o número de operações a serem realizadas");
		int qtdOps = Integer.parseInt(in.nextLine());

		while(indexOps<qtdOps) {
			operacoes[indexOps]=in.nextLine();
			indexOps++;
		}
		long tempoInicial = System.currentTimeMillis();
		for(int i = 0 ;i<indexOps;i++) {
			if(operacoes[i].compareTo("F")==0) {
				vo.maisFrequentes();
			}
			else if(operacoes[i].compareTo("L") == 0) {
				vo.palavrasLongas();
			}
			else if(operacoes[i].compareTo("SR")==0) {
				vo.maioresSemRep();
			}
			else if(operacoes[i].compareTo("VD")==0) {
				vo.menoresPalavrasVogalSemRep();
			}
			else if(operacoes[i].charAt(0)=='O') {
				vo.encontraPalavra(operacoes[i].substring(2));
			}
		}
		//long total = System.currentTimeMillis() - tempoInicial;
		//System.out.println("TEMPO TOTAL = " +total+" milissegundos" );
		in.close();
		
	}
	public static void rodaBST() {
		SymbolTable_BST<String> bst = new SymbolTable_BST<>();
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		in.nextLine();
		for(int i = 0; i<n; ) {
			String [] tokens = in.nextLine().split("\\s");
			for(String s:tokens){
				if(s.charAt(s.length()-1)==',' ||s.charAt(s.length()-1)=='.'|| s.charAt(s.length()-1)=='?'||s.charAt(s.length()-1)=='!' ) {
					s = s.substring(0, s.length()-1);
				}
	            bst.insere(s, s.length(), repete(s),encontraVogais(s));
	            i++;
	        }
		}
		String [] operacoes = new  String[10];
		int indexOps=0;
		System.out.println("Insira o número de operações a serem realizadas");
		int qtdOps = Integer.parseInt(in.nextLine());

		while(indexOps<qtdOps) {
			operacoes[indexOps]=in.nextLine();
			indexOps++;
		}
		//long tempoInicial = System.currentTimeMillis();

		for(int i = 0 ;i<indexOps;i++) {
			if(operacoes[i].compareTo("L")==0) {
				bst.maisLongas();
			}
			else if(operacoes[i].compareTo("F") == 0) {
				bst.maisFrequentes();
			}
			else if(operacoes[i].compareTo("SR")==0) {
				bst.maioresSemRep();
			}
			
			else if(operacoes[i].compareTo("VD")==0) {
				bst.menoresPalavrasSemRep();
			}
			else if(operacoes[i].charAt(0)=='O') {
				bst.encontraPalavra(operacoes[i].substring(2));
			}
		}
		//long total = System.currentTimeMillis() - tempoInicial;
		//System.out.println("TEMPO TOTAL = " +total+" milissegundos" );
	
		in.close();
		
		
	}
	public static void rodaARN() {
		Scanner in = new Scanner(System.in);
		SymbolTable_RubroNegra<String> arn = new SymbolTable_RubroNegra<>();
		int n = in.nextInt();
		in.nextLine();
		for(int i = 0; i<n; ) {
			String [] tokens = in.nextLine().split("\\s");
			for(String s:tokens){
				if(s.charAt(s.length()-1)==',' ||s.charAt(s.length()-1)=='.'|| s.charAt(s.length()-1)=='?'||s.charAt(s.length()-1)=='!' ) {
					s = s.substring(0, s.length()-1);
				}
	            arn.insere(s, s.length(), repete(s),encontraVogais(s));
	            i++;
	        }
		}
		String [] operacoes = new  String[10];
		int indexOps=0;
		System.out.println("Insira o número de operações a serem realizadas");
		int qtdOps = Integer.parseInt(in.nextLine());

		while(indexOps<qtdOps) {
			operacoes[indexOps]=in.nextLine();
			indexOps++;
		}
		//long tempoInicial = System.currentTimeMillis();
		for(int i = 0 ;i<indexOps;i++) {
			if(operacoes[i].compareTo("L")==0) {
				arn.maisLongas();
			}
			else if(operacoes[i].compareTo("F") == 0) {
				arn.maisFrequentes();
			}
			else if(operacoes[i].compareTo("SR")==0) {
				arn.maioresSemRep();
			}
			
			else if(operacoes[i].compareTo("VD")==0) {
				arn.menoresPalavrasSemRep();
			}
			else if(operacoes[i].charAt(0)=='O') {
				arn.encontraPalavra(operacoes[i].substring(2));
			}
		}
		//long total = System.currentTimeMillis() - tempoInicial;
		//System.out.println("TEMPO TOTAL = " +total+" milissegundos" );
		in.close();
	}
	public static void rodaTreap() {
		SymbolTable_Treap <String> treap = new SymbolTable_Treap<>();
		Node raiz = null;
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		in.nextLine();
		for(int i = 0; i<n; ) {
			String [] tokens = in.nextLine().split("\\s");
			for(String s:tokens){
				if(s.charAt(s.length()-1)==',' ||s.charAt(s.length()-1)=='.'|| s.charAt(s.length()-1)=='?'||s.charAt(s.length()-1)=='!' ) {
					s = s.substring(0, s.length()-1);
				}
	            raiz = treap.insere(raiz, s, s.length(), repete(s), encontraVogais(s));
	            i++;
	        }
		}
		String [] operacoes = new  String[10];
		int indexOps=0;
		System.out.println("Insira o número de operações a serem realizadas");
		int qtdOps = Integer.parseInt(in.nextLine());

		while(indexOps<qtdOps) {
			operacoes[indexOps]=in.nextLine();
			indexOps++;
		}
		//long tempoInicial = System.currentTimeMillis();
		for(int i = 0 ;i<indexOps;i++) {
			if(operacoes[i].compareTo("L")==0) {
				treap.maisLongas();
			}
			else if(operacoes[i].compareTo("F") == 0) {
				treap.maisFrequentes();
			}
			else if(operacoes[i].compareTo("SR")==0) {
				treap.maioresSemRep();
			}
			
			else if(operacoes[i].compareTo("VD")==0) {
				treap.menoresPalavrasSemRep();
			}
			else if(operacoes[i].charAt(0)=='O') {
				treap.encontraPalavra(operacoes[i].substring(2),raiz);
			}
		}
		//long total = System.currentTimeMillis() - tempoInicial;
		//System.out.println("TEMPO TOTAL = " +total+" milissegundos" );
		in.close();
	}
	public static void main(String []args) {
		Scanner in = new Scanner(System.in);
		String opc = in.nextLine().toLowerCase();
		if(opc.compareTo("vo")==0) {
			rodaVO();
		}
		else if(opc.compareTo("abb")==0) {
			rodaBST();
		}
		else if (opc.compareTo("tr")==0) {
			rodaTreap();
		}
		else if(opc.compareTo("arn")==0) {
			rodaARN();
			
		}
	}
}
