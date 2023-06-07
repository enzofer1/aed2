

public class SymbolTable_VetorOrdenado<Key extends Comparable<Key>> {
    Key[] keys = (Key[]) new Comparable [1];
    int[] ocorrenciasPalavra = new int[1];
    int[] tamanhoPalavra = new int[1];
    int[] numVogaisSemRep =  new int[1];
    boolean [] repeteLetras = new boolean[1];
    int size = 1;
    int usedSize = 0;
    
    int rank(Key key) {
    	int inicio = 0;
    	int fim = usedSize-1;
    	while(inicio <= fim) {
    		int meio = inicio + (fim-inicio)/2;
    		int cmp = key.compareTo(keys[meio]);
    		if(cmp < 0 ) {
    			fim = meio-1;
    		}
    		else if (cmp > 0) {
    			inicio = meio+1;
    		}
    		else {
    			return meio;
    		}
    	}
    	return inicio;
    }
    void add(Key key, int tamanho, int nvogais,boolean repete) {
    	if(size==usedSize) {
    		resize();
    	}
    	int index = rank(key);
    	if(index < usedSize && keys[index].compareTo(key)==0) {
    		ocorrenciasPalavra[index]++;
    		return;
    	}
    	for(int i = usedSize; i > index; i--) {
    		keys[i] = keys[i-1];
    		ocorrenciasPalavra[i] = ocorrenciasPalavra[i-1];
    		tamanhoPalavra[i] = tamanhoPalavra[i-1];
    		numVogaisSemRep[i] = numVogaisSemRep[i-1];
    		repeteLetras[i] = repeteLetras[i-1];
    	}
    	keys[index] = key;
    	ocorrenciasPalavra[index] = 1;
    	tamanhoPalavra[index] = tamanho;
    	numVogaisSemRep[index]=nvogais;
    	repeteLetras[index]= repete;
    	
    	usedSize++;
    }
    void resize() {
    	Key[] novokeys = (Key[]) new Comparable [this.size+100];
    	int[] novoocorrenciasPalavra =  new int[this.size+100];
        int[] novotamanhoPalavra =  new int[this.size+100];
        int[] novonumVogaisSemRep =  new int[this.size+100];
        boolean [] novorepeteLetras = new boolean[this.size+100];
    	for(int i = 0;i < this.size;i++) {
    		novokeys[i] = keys[i];
    		novoocorrenciasPalavra[i] = ocorrenciasPalavra[i];
    		novotamanhoPalavra[i] = tamanhoPalavra[i];
    		novonumVogaisSemRep[i] = numVogaisSemRep[i];
    		novorepeteLetras[i] = repeteLetras[i];
    	}
    	this.size+=100;
    	ocorrenciasPalavra = novoocorrenciasPalavra;
    	tamanhoPalavra = novotamanhoPalavra;
    	numVogaisSemRep = novonumVogaisSemRep;
    	keys = novokeys;
    	repeteLetras = novorepeteLetras;
    	
    }
    void value(Key key) {
    	int index = rank(key);
    	if(keys[index].compareTo(key)==0)
    	{
    		System.out.println("Para a palavra "+key+" : OCORRENCIAS = "+ocorrenciasPalavra[index]+" TAMANHO : "+tamanhoPalavra[index]+"NUM VOGAIS: "+numVogaisSemRep[index]);
    	}
    	else
    	{
    		System.out.println("Palavra não encontrada");
    	}
    }
    void print() {
    	for(int i = 0; i<usedSize; i++) {
    		System.out.println("KEY ="+keys[i]+"OCORRENCIAS = "+ocorrenciasPalavra[i]+ "TAMANHO = "+tamanhoPalavra[i]);
    	}
    }
    void maisFrequentes() {
    	int [] nomes = new int[100900];
    	int tamanhoMaior = 0;
    	int indicePalavras=0;
    	tamanhoMaior=0;
    	for (int i = 0; i<usedSize;i++) {
    		if(ocorrenciasPalavra[i]>tamanhoMaior) {
    			tamanhoMaior = ocorrenciasPalavra[i];
    			indicePalavras=0;
    			nomes[indicePalavras] = i;
    			indicePalavras++;
    		}
    		else if(ocorrenciasPalavra[i]==tamanhoMaior) {
    			nomes[indicePalavras]=i;
    			indicePalavras++;
    		}
    	}
    	System.out.println("MAIS REPETIDAS ("+tamanhoMaior+" VEZES):");
    	for(int i = 0; i<indicePalavras;i++) {
    		System.out.println(keys[nomes[i]]);
    	}
    }
    void encontraPalavra(String palavra) {
    	for(int i = 0; i<usedSize;i++) {
    		if(palavra.compareTo((String) keys[i])==0) {
    			System.out.println("A palavra "+palavra+" aparece "+ocorrenciasPalavra[i]+" vezes");
    			return;
    		}
    	}
    	System.out.println("A palavra "+palavra+" aparece 0 vezes");
    }
    void palavrasLongas() {
    	// encontrando a maior palavra
    	int tamanhoMaior = 0;
    	int indicePalavras=0;
    	int[] nomes = new int [100];
    	for (int i = 0; i<usedSize;i++) {
    		if(tamanhoPalavra[i]>tamanhoMaior) {
    			tamanhoMaior = tamanhoPalavra[i];
    			indicePalavras=0;
    			nomes[indicePalavras] = i;
    			indicePalavras++;
    		}
    		else if(tamanhoPalavra[i]==tamanhoMaior) {
    			nomes[indicePalavras]=i;
    			indicePalavras++;
    		}
    	}
    	System.out.println("MAIORES PALAVRAS ("+tamanhoMaior+" LETRAS):");
    	for(int i = 0; i<indicePalavras;i++) {
    		System.out.println(keys[nomes[i]]);
    	}
    }
    void maioresSemRep() {
    	int tamanhoMaior = 0;
    	int indicePalavras=0;
    	int[] nomes = new int [100];
    	tamanhoMaior=0;
    	indicePalavras=0;
    	// encontrando as maiores palavras que nao repetem letras
    	for (int i = 0; i<usedSize;i++) {
    		if(tamanhoPalavra[i]>tamanhoMaior && repeteLetras[i]==false) {
    			tamanhoMaior = tamanhoPalavra[i];
    			indicePalavras=0;
    			nomes[indicePalavras] = i;
    			indicePalavras++;
    		}
    		else if(tamanhoPalavra[i]==tamanhoMaior && repeteLetras[i]==false) {
    			nomes[indicePalavras]=i;
    			indicePalavras++;
    		}
    	}
    	System.out.println("MAIORES PALAVRAS QUE NAO REPETEM LETRAS ("+tamanhoMaior+" LETRAS):");
    	for(int i = 0; i<indicePalavras;i++) {
    		System.out.println(keys[nomes[i]]);
    	}
    }
    void menoresPalavrasVogalSemRep() {
    	int menorVogal=0;
    	int menorPalavra=0;
    	int indicePalavras=0;
    	int [] nomes = new int [100];
    	for(int i = 0;i<usedSize;i++) {
    		if(numVogaisSemRep[i]>menorVogal) {
    			indicePalavras=0;
    			menorPalavra = tamanhoPalavra[i];
    			menorVogal = numVogaisSemRep[i];
    			nomes[indicePalavras] =i;
    			indicePalavras++;
  
    		}
    		else if(numVogaisSemRep[i]==menorVogal ) {
    			if(tamanhoPalavra[i]< menorPalavra) {
    				indicePalavras=0;
    				menorPalavra = tamanhoPalavra[i];
    				nomes[indicePalavras]=i;
    				indicePalavras++;
    			}
    			else if(tamanhoPalavra[i]==menorPalavra) {
    				nomes[indicePalavras]=i;
    				indicePalavras++;
    			}
    		}
    	}
    	System.out.println("A(s) "+indicePalavras+" menor(es) palavra(s) com mais vogais sem repetição contém "+menorPalavra+" letras e "+menorVogal+" vogais. São elas: ");
    	for(int i = 0; i<indicePalavras;i++) {
    		System.out.print(keys[nomes[i]]+" ");
    	}
    	System.out.println();
    }
}
