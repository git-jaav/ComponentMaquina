package pe.jaav.sistemas.algoritmos;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ProblemasMain {

	public static void main(String[] args) {
		System.out.println("***** [INICIO RESOLUCION] ****");	
		ProblemasMain prob = new ProblemasMain();
		prob.getInput();
		
		//prob.resolverProblemaDistancias();
		
		
	}

	private List<Double> distanciasRecorridas;
	
	public  void getInput(){
		String input="AAAAA\nBBBBB\nCCCCC\n1 2 3 ";
		StringTokenizer stInput = new StringTokenizer(input,"\n");
		int countLinea=0;
		while (stInput.hasMoreElements()) {
			String linea = stInput.nextToken();
			StringTokenizer stInputLinea = new StringTokenizer(linea," ");
			while (stInputLinea.hasMoreElements()) {
				String object =  stInputLinea.nextToken();
				
			}			
			System.out.println("[TEST:]("+(countLinea+1)+")>>:"+linea+"<<");
			countLinea++;
		}							
	}
	
	public  void resolverProblemaDistancias(){
		distanciasRecorridas = new ArrayList<Double>();
		
		int N = 6;
		int E = 1;
		int S = 4;
		Punto puntos[] = new Punto[N];
		puntos[0] = new Punto(0,0,1);
		puntos[1] = new Punto(10,0,2);
		puntos[2] = new Punto(20,0,3);
		puntos[3] = new Punto(20,1,4);
		puntos[4] = new Punto(10,1,5);
		puntos[5] = new Punto(0,1,6);
		
		Punto puntosRestantes[] = new Punto[N-2];
		int j = 0;				
		for(int k = 0; k < puntos.length;k++){
			if( (E-1) != k && (S-1) != k){
				puntosRestantes[j] = puntos[k];
				j++;
			}
		}
		
		recorrerDistancias(puntos[E-1],puntosRestantes, puntos[S-1],(double) 0);
		if(distanciasRecorridas.size()>0){
			mostrarLista(distanciasRecorridas);
		}
		//MOSTRAR MÍNIMA DISTANCIA
		Double distanciaMinima = Double.MAX_VALUE; 
		for(Double distanciaMin : distanciasRecorridas){
			if(distanciaMin < distanciaMinima){
				distanciaMinima = distanciaMin;
			}
		}
		System.out.println("SOLUCIÓN : LA DISTANCIA MÍNIMA ES: "+distanciaMinima);
		
	}
	
	public void recorrerDistancias(Punto Po, Punto[] Vp, Punto Pf, Double distPoPx){
		if(Vp.length > 0){
			Punto[] VpAux = new Punto[Vp.length-1];
			//Recorrer conjunt de puntos a buscar
			for(int i = 0; i < Vp.length;i++){
				//crear nuevo conjunto de puntos para el siguiene nivel (Sin considerar el actual)
				int j = 0;				
				for(int k = 0; k < Vp.length;k++){
					if( i != k){
						VpAux[j] = Vp[k];
						j++;
					}
				}
				//Ir al siguiente nivel
				recorrerDistancias(Vp[i], VpAux, Pf, distPoPx +moduloFn(Po,Vp[i]));
			}
		}else{
			distanciasRecorridas.add(distPoPx + moduloFn(Po,Pf) );
		}
	}
	
	public void mostrarLista(List<Double> lista){
		int i = 0;
		System.out.println("START MOSTRAR LISTA");		
		for(Double valor : lista){
			System.out.println("Elemento "+(i+1)+" : "+valor);
			i++;
		}
		System.out.println("END MOSTRAR LISTA");
	}
	
	public static double moduloFn(Punto A,Punto B){
		return moduloFn(A.getX(), A.getY(),B.getX(),B.getY());
	}
	
	/***/
	
	public static double moduloFn(int Ax,int Ay,int Bx,int By){
		Double numero = (double) ((Bx - Ax)*(Bx - Ax) + (By -Ay)*(By -Ay));						
		numero =Math.sqrt(numero);				
		double suma = numero;
		return numero;
	}
	
	
	public class Punto{
		int x;
		int y;
		int indice;
						
		public Punto(int x, int y, int indice) {
			super();
			this.x = x;
			this.y = y;
			this.indice = indice;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public int getIndice() {
			return indice;
		}
		public void setIndice(int indice) {
			this.indice = indice;
		}
		
		
	}


}
