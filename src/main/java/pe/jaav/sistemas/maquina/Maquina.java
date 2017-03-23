package pe.jaav.sistemas.maquina;

/**
 * @(#)Maquina.java
 *
 *
 * @author JAAV
 * @version 1.00 2010/3/4
 */


public class Maquina {

	private Automata automata;
	private String alfabeto[];
	private String subSecuencia[];
	private int cantSimbolos;
	private int dimSubSecuencia;
	
	/**Constructor*/
    public Maquina(String filaInAlfabeto,String filaInSubSec) {
    	alfabeto = new String [400];
    	subSecuencia = new String [400];
    	this.cantSimbolos = recibeAlfabeto(filaInAlfabeto);
    	this.dimSubSecuencia = recibeSubSecuencia(filaInSubSec);
    	automata = new Automata(alfabeto,cantSimbolos,subSecuencia,dimSubSecuencia);
    	automata.mostrarTabEstados(0,0);
    }
    //****************************************entrada***********************************************//
    /***/
    public String leerArchivo(String filaInAlfabeto){
    	
    	String rec = "D:\\Documentos\\_OWN_PROJECTS\\ERP_PYME\\Proy_CodigoFuente\\WORKSPACE_GIT_MODULOS\\ComponentMaquina\\src\\main\\resources\\";
    	String aux = new String();
    	String cadena = new String();
    	FTexto fila = new FTexto(rec+filaInAlfabeto);
    	try{
    		fila.abrirIn();
    		aux = fila.leerlinea();
    		while(aux != null){
    			cadena = cadena+aux;
    			aux = fila.leerlinea();
    		}
    		fila.cerrarIn();    			
    	}
    	catch(Exception e){
    		System.out.println("error en entrada");
    	}
		return  cadena;    	
    
    }
    /**recibe los simbolos del alfabeto y devuelve la cantidad de estos*/
    public int recibeAlfabeto(String filaInAlfabeto){
    	int indice,i;    	    	
    	String aux;
    	String cadena = leerArchivo(filaInAlfabeto);
    	
    	indice = 0;
    	for(i = 0;i < cadena.length();i++){
    		aux = "";
    		while(cadena.charAt(i) != ' '){
    			aux = aux+cadena.charAt(i);
    			i++;
    			if(i == cadena.length())
    				break;
    		}
    		if(aux != null){ 	   			
    			alfabeto[indice] = aux;
    			indice++;
    		}
   		}
   		System.out.println("alfa: "+indice);
   		return indice;
    }
    /**recibe la secuencia verificable y decuelve la cantidad de simbolos de esta*/
    public int recibeSubSecuencia(String filaInSubSec){
    	int indice,i;    	
    	
    	String aux;
    	String cadena = leerArchivo(filaInSubSec);
    	
    	indice = 0;
    	for(i = 0;i < cadena.length();i++){
    		aux = "";
    		while(cadena.charAt(i) != ' '){
    			aux = aux+cadena.charAt(i);
    			i++;
    			if(i == cadena.length())
    				break;
    			
    		}
    		if(aux != null){ 	   			
    			subSecuencia[indice] = aux;     			
    			indice++;
    		}
   		}
   		System.out.println("secu: "+indice);
   		return indice;    	
    }
    //**************************************************************************//
    /***/
    public void detectarSecuencias(String filaIn){
    	int i,indice;
    	String cadena = leerArchivo(filaIn);
    	String aux;
    	String entrada[] = new String[cadena.length()];
    	
    	indice = 0;
    	for(i = 0;i < cadena.length();i++){
    		aux = "";
    		while(cadena.charAt(i) != ' '){
    			aux = aux+cadena.charAt(i);
    			i++;
    			if(i == cadena.length())
    				break;
    			
    		}
    		if(aux != null){ 	   			
    			entrada[indice] = aux;     			
    			indice++;
    		}
   		}
   		detectarSecuencias(entrada,indice);
    }
    /**contabiliza las veces en aparecer de la secuencia deseada*/
    public void detectarSecuencias(String entrada[],int tam){
    	int i,j,estActual,estSgte,input,output;
    	int cantDetec = 0;
    	estActual = 0;
    	for(i = 0;i < tam;i++){
    		input = indiceSimboloEnAlfabeto(entrada[i]);
    		if(input!= -1){
    			estSgte = automata.estadoSiguiente(estActual,input);
    			output = automata.salida(estActual,input);
    			estActual = estSgte;
    			if(output == 1)
    				cantDetec++;
    		}    		
    	}
    	System.out.println("FINAL DE RECORRIDO:");
    	System.out.print("se detectaron "+cantDetec+" subsecuencias de la forma: ");
    	mostrarSubSecuencia();    	
    }    
    //*****************************************************************//
    /***/
    public int indiceSimboloEnAlfabeto(String simbolo){
    	int i,indice = -1;
    	boolean hallado = false;
    	for(i = 0;i < cantSimbolos && !hallado;i++)
    		if(simbolo.equals(alfabeto[i])){
    			hallado = true;
    			indice = i;
    		}
    	return indice;
    }
    /***/
    public void mostrarAlfabeto(){
    	int i;
    	for(i = 0;i < cantSimbolos;i++)
    		System.out.println((i+1)+": "+alfabeto[i]);
    }
    /***/
    public void mostrarSubSecuencia(){
    	int i;
    	for(i = 0;i < dimSubSecuencia;i++)
    		System.out.print(subSecuencia[i]);    		
    	System.out.println("");
    }
}