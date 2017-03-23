package pe.jaav.sistemas.maquina;

/**
 * @(#)Automata.java
 *
 *
 * @author  JAAV
 * @version 1.00 2010/3/4
 */


public class Automata {

	private String subSecuencia;
	private int subSec[];
	
	private int tabEstados[][];
	private int tabSalidas[][];
	
	private int cantEstados;
	private int cantEntradas;
	
	/**Constructor*/
    public Automata(String alfabeto[],int dimAlf,String subSecuencia[],int dimSec) {
    	this.cantEntradas = dimAlf;
    	this.cantEstados = dimSec;
    	armaSecuencia(subSecuencia,alfabeto);
    	iniciarTabEstados();
    	iniciarTabSalidas();
    	/*
		Runtime aplicacion = Runtime.getRuntime(); 
        //try{aplicacion.exec("C:/Windows/System32/NOTEPAD.EXE"); }
        try{aplicacion.exec("D:/Documentos/5_FISI/ComputacionGrafica/CurvasPol/Debug/CurvasPol.exe"); }        
        catch(Exception e){}    	
    	*/
    }
    /**crea el vector con la secuencia a reconocer*/
    public void armaSecuencia(String subSecuencia[],String alfabeto[]){
    	int i;
    	subSec = new int[cantEstados];
    	for(i = 0;i< cantEstados;i++){
    		subSec[i] = indiceSimbolo(alfabeto,subSecuencia[i]);
    	}
    }
    /**Crea la tabla de estados*/
    public void iniciarTabEstados(){
    	int i,j,k,contador;
    	int menor,mayorPosible,diferencia;
    	tabEstados = new int [cantEstados][cantEntradas];
    	
    	for(j = 0;j < cantEntradas;j++)
    		tabEstados[0][j] = 0;
    	tabEstados[0][subSec[0]] = 1;
    	
    	for(i = 1;i < cantEstados;i++){    		
    		for(j = 0;j < cantEntradas;j++){
    			if(j == subSec[i]){
    				tabEstados[i][j] = i+1;
    			}
    			else{
	    			if(tabEstados[i-1][j]==i && tabEstados[0][j] == 1){
		    			diferencia = 1;
    					contador = 1;
    					k = i-1;
    					while(diferencia == 1 && k >0){
    						diferencia = tabEstados[k][j] - tabEstados[k-1][j];
    						if(diferencia == 1)
    							contador++;
		    				k--;
    					}
    					if(diferencia == 1)
    						tabEstados[i][j] = tabEstados[i-1][j];
    					else{
	    					mayorPosible = -1;
    						for(k = i-1;k >=0; k--){    				
	    						if(tabEstados[k][j] > mayorPosible && tabEstados[k][j] <= contador+1)
    								mayorPosible = tabEstados[k][j];
    						}
    						tabEstados[i][j] = mayorPosible;
    					}    				
	    			}	
    				else{
    					if(perteneceSec(j)){
		    				menor = cantEstados;
    						for(k = i-1;k >=0; k--)
    							if(tabEstados[k][j]< menor)
    								menor = tabEstados[k][j];
	    					tabEstados[i][j] = menor;    						
    					}
    					else{
    						tabEstados[i][j] = 0;
    					}    					    					
    				}	
    			}
    		}
    	}   
    	tabEstados[cantEstados-1][subSec[cantEstados-1]] = 0;    	 	
    }
    /**crea  la tabla de las salidas (cambiable)*/
    public void iniciarTabSalidas(){
    	int i,j;
    	tabSalidas = new int[cantEstados][cantEntradas];
    	for(i = 0;i< cantEstados;i++)
    		for(j = 0;j< cantEntradas;j++)    			
    				tabSalidas[i][j] = 0;
    		
    	tabSalidas[cantEstados-1][subSec[cantEstados-1]] = 1;    	
    }
    /**Devuelve el estado siguiente de acuerdo al estado recibido y el simbolo de entrada*/ 
    public int estadoSiguiente(int estActual,int entrada){
    	int sgte = -1;
    	if(estActual < cantEstados && estActual >= 0){
    		sgte = tabEstados[estActual][entrada];
    	}
    	return sgte;
    }
    /**Devuelve la salida respectiva del paso al estado siguiente*/
    public int salida(int estActual,int entrada){
    	int salida = -1;
    	if(estActual < cantEstados){
    		salida = tabSalidas[estActual][entrada];
    	}    	
    	return salida;
    }

    
    //*******************************************************************************//
	/***/    
    public boolean perteneceSec(int valor){    	
    	for(int i = 0 ;i < cantEstados;i++)
    		if(valor == subSec[i])
    			return true;
    	return false;
    }
    /***/
    public int indiceSimbolo(String alfabeto[],String simbolo){
    	int i,indice =-1;
    	boolean hallado = false;
    	for(i = 0;i < cantEntradas && !hallado;i++)    		
    		if(simbolo.equals(alfabeto[i])){
    			hallado = true;
    			indice = i;
    		}
    	return indice;
    }
    /***/
    public void mostrarTabEstados(int i ,int j){    	
		if(i <  cantEstados){				
			if(j < cantEntradas){
				System.out.print("  "+tabEstados[i][j]);
				mostrarTabEstados(i,j+1);
			}
			else{
				System.out.println("");
				mostrarTabEstados(i+1,0);
			}
		}		
    }        	    
}
