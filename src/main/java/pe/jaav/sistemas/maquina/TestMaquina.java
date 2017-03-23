package pe.jaav.sistemas.maquina;

/**
 * @(#)TestMaquina.java
 *
 * TestMaquina application
 *
 * @author JAAV
 * @version 1.00 2010/3/4
 */
 
public class TestMaquina {

	public TestMaquina(){
		
		String alfa[] = {"a","b","c"};//{"a","b","c","d","e","f","g"};
		String sec[] = {"a","a","b","c","b"};//{"d","d","f","a"};
		Automata aut = new Automata(alfa,3,sec,5);
		aut.mostrarTabEstados(0,0);
		
		/*
		Maquina maq = new Maquina("alfabeto.txt","secuencia.txt");
		//maq.mostrarAlfabeto();		
		maq.detectarSecuencias("entrada.txt");
		*/
	}    
    public static void main(String[] args) {
    	new TestMaquina();
    }
}
