package proyecto;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class matrizSudominoku {
	private static int [][] memoriza;
	private static int [][] tablero;
	private static int [][] candidatos;
	private static ArrayList<Ficha> listaDeFichas;
	private static ArrayList<Posicion> sinUsar;
	private static ArrayList<Ficha> copiaFichas;
	 
	/** METODO QUE BUSCA EN LA FILA DADA SI EL NUMERO YA SE ENCUENTRA USADO */
	public static boolean ingresaFila(int t[][], int fila, int n){
		for(int i = 0; i<9; i++){
			if(n == t[fila][i]){
				return false;
			}
		}
		return true;
	}
	
	/** METODO QUE BUSCA EN LA COLUMNA DADA SI EL NUMERO YA SE ENCUENTRA USADO */
	public static boolean ingresaColumna(int t[][], int columna, int n){
		for(int i = 0; i<9; i++){
			if(n == t[i][columna]){
				return false;
			}
		}
		return true;
	}
	
	/** METODO QUE BUSCA EN LA REGION EN DONDE SE ENCUENTRA EL NUMERO SI EL NUMERO YA SE ENCUENTRA USADO */
	public static boolean ingresaRegion(int[][] matriz, int x, int y, int n1) {
        if ((x >= 0 && x <= 2) && (y >= 0 && y <= 2)) {
            for (int i = 0; i <= 2; i++) {
                for (int j = 0; j <= 2; j++) {
                    if (n1 == matriz[i][j]) {
                        return false;
                    }
                }
            }
        }
        if ((x >= 3 && x <= 5) && (y >= 0 && y <= 2)) {
            for (int i = 3; i <= 5; i++) {
                for (int j = 0; j <= 2; j++) {
                    if (n1 == matriz[i][j]) {
                        return false;
                    }
                }
            }
        }
        if ((x >= 6 && x <= 8) && (y >= 0 && y <= 2)) {
            for (int i = 6; i <= 8; i++) {
                for (int j = 0; j <= 2; j++) {
                    if (n1 == matriz[i][j]) {
                        return false;
                    }
                }
            }
        }

        if ((x >= 0 && x <= 2) && (y >= 3 && y <= 5)) {
            for (int i = 0; i <= 2; i++) {
                for (int j = 3; j <= 5; j++) {
                    if (n1 == matriz[i][j]) {
                        return false;
                    }
                }
            }
        }
        if ((x >= 3 && x <= 5) && (y >= 3 && y <= 5)) {
            for (int i = 3; i <= 5; i++) {
                for (int j = 3; j <= 5; j++) {
                    if (n1 == matriz[i][j]) {
                        return false;
                    }
                }
            }
        }
        if ((x >= 6 && x <= 8) && (y >= 3 && y <= 5)) {
            for (int i = 6; i <= 8; i++) {
                for (int j = 3; j <= 5; j++) {
                    if (n1 == matriz[i][j]) {
                        return false;
                    }
                }
            }
        }

        if ((x >= 0 && x <= 2) && (y >= 6 && y <= 8)) {
            for (int i = 0; i <= 2; i++) {
                for (int j = 6; j <= 8; j++) {
                    if (n1 == matriz[i][j]) {
                        return false;
                    }
                }
            }
        }
        if ((x >= 3 && x <= 5) && (y >= 6 && y <= 8)) {
            for (int i = 3; i <= 5; i++) {
                for (int j = 6; j <= 8; j++) {
                    if (n1 == matriz[i][j]) {
                        return false;
                    }
                }
            }
        }
        if ((x >= 6 && x <= 8) && (y >= 6 && y <= 8)) {
            for (int i = 6; i <= 8; i++) {
                for (int j = 6; j <= 8; j++) {
                    if (n1 == matriz[i][j]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
	
	/** METODO QUE NOS INDICA SI ES POSIBLE ASIGNAR UN NUMERO EN UNA FICHA EN UNA POSICION DEL TABLERO */
	public static int asignar(int [][] tabla, int fila, int columna, Ficha f){
			int grados = f.getAngulo();
			int contenedorFila = fila;
			int contenedorColumna = columna;
			int respuesta = 0;
			
			//System.out.println("QUIERO COLOCAR ESTA MIERDA: |" + f.getL1() + "|" + f.getL2() + "|" + " en un angulo de " + f.getAngulo());
			//System.out.println("En la posicion" +"[" + fila + "]" +"[" + columna +"]");
			if(tabla[fila][columna] == 0){	
				if(grados == 0){
					//System.out.println("Entre a los 0 grados wey");
					//System.out.println("La fila de esta mierda es: " + fila + " y la columna " + columna);
					//PARA 0 GRADOS CUANDO SE CUMPLE LAS CONDICIONES
					if((fila >= 0 && fila <= 8) && (columna >= 0 && columna <= 8)){
						//System.out.println("Cumplo con el rango de fila columna de los 0 grados");
						if(ingresaFila(tabla, fila, f.getL1()) && ingresaColumna(tabla, columna, f.getL1())
							&& ingresaRegion(tabla, fila, columna, f.getL1())){
						
							if(fila+1 <= 8 && fila+1 >= 0){
							contenedorFila = fila + 1;
							//System.out.println("Cumplio con las condiciones del primer numero");
						
							if(ingresaFila(tabla, contenedorFila, f.getL2()) && ingresaColumna(tabla, columna, f.getL2())
									&& ingresaRegion(tabla, contenedorFila, columna, f.getL2())){
								//System.out.println("Cumplio con las condiciones del segundo numero");
								
								//System.out.println("Quiero imprimir el numero " + f.getL1() + "en ["+ fila +"]" + "["+ columna+ "]");
								agregaDato(tabla, fila, columna, f.getL1());
							
								//System.out.println("Quiero imprimir el numero " + f.getL2() + "en ["+ contenedorFila +"]" + "["+ columna+ "]");
								agregaDato(tabla, contenedorFila, columna, f.getL2());
								//imprime_vector(tabla);
							
								respuesta = 1;	
							}else if(grados != 270){
								int nuevoAngulo = f.getAngulo() + 90;
								f.setAngulo(nuevoAngulo);
								//System.out.println("A la ficha " + f.getL1() + "!" + f.getL2() + " le di un angulo de " + f.getAngulo());
								asignar(tabla, fila, columna, f); 
							}
							}
						}else if(grados != 270){
							int nuevoAngulo = f.getAngulo() + 90;
							f.setAngulo(nuevoAngulo);
							//System.out.println("A la ficha " + f.getL1() + "!" + f.getL2() + " le di un angulo de " + f.getAngulo());
							asignar(tabla, fila, columna, f); 
						}
					}
		
			}else if(grados == 90){
					//System.out.println("Entre a los 90 grados wey");
					//System.out.println("La fila de esta mierda es: " + fila + " y la columna " + columna);
					//PARA 90 GRADOS CUANDO SE CUMPLE LAS CONDICIONES
					//System.out.print("EL DATO l1 ES : " + f.getL1() + " Y EL DATO L2 ES " + f.getL2());
					if((fila >= 0 && fila <= 8) && (columna >= 0 && columna <= 8)){
						//System.out.println("Cumplo con el rango de fila columna de los 90 grados");
						if(ingresaFila(tabla, fila, f.getL2()) && ingresaColumna(tabla, columna, f.getL2())
								&& ingresaRegion(tabla, fila, columna, f.getL2())){
							//System.out.println("Puedo poner el " + f.getL2() + " en la pos " + "["+ fila +"]" + "["+ columna+ "]");
							contenedorColumna = columna + 1;
							
							if((fila <= 8 && fila >= 0) && (columna+1 <= 8 && columna+1 >= 0)){
							if(ingresaFila(tabla, fila, f.getL1()) && ingresaColumna(tabla, contenedorColumna, f.getL1())
									&& ingresaRegion(tabla, fila, contenedorColumna, f.getL1())){
								//System.out.println("Puedo poner el " + f.getL1() + " en la pos " + "["+ fila +"]" + "["+ contenedorColumna + "]");
								agregaDato(tabla, fila, columna, f.getL2());
								agregaDato(tabla, fila, contenedorColumna, f.getL1()); 
								//imprime_vector(tabla);

								respuesta = 1;
							}else if(grados != 270){
								int nuevoAngulo = f.getAngulo() + 90;
								f.setAngulo(nuevoAngulo);
								//System.out.println("A la ficha " + f.getL1() + "!" + f.getL2() + " le di un angulo de " + f.getAngulo());
								asignar(tabla, fila, columna, f); 
							}
							}
						}else if(grados != 270){
							int nuevoAngulo = f.getAngulo() + 90;
							f.setAngulo(nuevoAngulo);
							//System.out.println("A la ficha " + f.getL1() + "!" + f.getL2() + " le di un angulo de " + f.getAngulo());
							asignar(tabla, fila, columna, f); 
						}
					}
				}else if(grados == 180){
				//System.out.println("Entre a los 180 grados wey");
				//System.out.println("La fila de esta mierda es: " + fila + " y la columna " + columna);
				//PARA 180 GRADOS CUANDO SE CUMPLE LAS CONDICIONES
				if((fila >= 0 && fila <= 8) && (columna >= 0 && columna <= 8)){
					//System.out.println("Cumplo con el rango de fila columna de los 0 grados");
					if(ingresaFila(tabla, fila, f.getL2()) && ingresaColumna(tabla, columna, f.getL2())
							&& ingresaRegion(tabla, fila, columna, f.getL2())){
						
						contenedorColumna = columna + 1;
						//System.out.println("Cumplio con las condiciones del primer numero");
						if(columna+1 <= 8 && columna+1 >= 0){
						if(ingresaFila(tabla, fila, f.getL1()) && ingresaColumna(tabla, contenedorColumna, f.getL1())
								&& ingresaRegion(tabla, fila, contenedorColumna, f.getL1())){
						
							//System.out.println("Quiero imprimir el numero " + f.getL2() + "en ["+ fila +"]" + "["+ columna+ "]");
							agregaDato(tabla, fila, columna, f.getL2());
							
							//System.out.println("Quiero imprimir el numero " + f.getL1() + "en ["+ fila +"]" + "["+ contenedorColumna+ "]");
							agregaDato(tabla, fila, contenedorColumna, f.getL1());
							//imprime_vector(tabla);
							
							respuesta = 1;
							
						}else if(grados != 270){
							int nuevoAngulo = f.getAngulo() + 90;
							f.setAngulo(nuevoAngulo);
							//System.out.println("A la ficha " + f.getL1() + "!" + f.getL2() + " le di un angulo de " + f.getAngulo());
							asignar(tabla, fila, columna, f); 
						}
						}
					}else if(grados != 270){
						int nuevoAngulo = f.getAngulo() + 90;
						f.setAngulo(nuevoAngulo);
						//System.out.println("A la ficha " + f.getL1() + "!" + f.getL2() + " le di un angulo de " + f.getAngulo());
						asignar(tabla, fila, columna, f); 
					}
				}
			}else if(grados == 270){
				//System.out.println("Entre a los 270 grados wey");
				//System.out.println("La fila de esta mierda es: " + fila + " y la columna " + columna);
				//PARA 270 GRADOS CUANDO SE CUMPLE LAS CONDICIONES
				if((fila >= 0 && fila <= 8) && (columna >= 0 && columna <= 8)){
					if(ingresaFila(tabla, fila, f.getL1()) && ingresaColumna(tabla, columna, f.getL1())
							&& ingresaRegion(tabla, fila, columna, f.getL1())){
						//System.out.println("Puedo poner el " + f.getL1() + " en la pos " + "["+ fila +"]" + "["+ columna+ "]");
						contenedorColumna = columna + 1;
					
						if((fila <= 8 && fila >= 0) && (columna+1 <= 8 && columna+1 >= 0)){
							if(ingresaFila(tabla, fila, f.getL2()) && ingresaColumna(tabla, contenedorColumna, f.getL2())
									&& ingresaRegion(tabla, fila, contenedorColumna, f.getL2())){
								//System.out.println("Puedo poner el " + f.getL2() + " en la pos " + "["+ fila +"]" + "["+ contenedorColumna + "]");
								agregaDato(tabla, fila, columna, f.getL1());
								agregaDato(tabla, fila, contenedorColumna, f.getL2()); 
						
								respuesta = 1;	
							}
						}
					}
				}
			}
		}
		/** Si no se cumple que es 0 el dato en la matriz entonces retorna falso porque ya hay algo */	
		return respuesta;
	}
	
	
	public static void agregaDato(int [][] tabla, int fila, int columna, int dato){
		if((fila >= 0 && fila <= 8) && (columna >= 0 && columna <= 8) && tabla[fila][columna] == 0){
			tabla[fila][columna] = dato;
		}		
		else{
			System.out.println("No puede poner el dato " + dato + " en [" + fila +"]" + "[" + columna +"]" );
		}
	}
	
	/** METODO PARA SABER SI EL JUEGO YA SE TERMINO. EN EL CASO EN QUE TODAS LAS POSICIONES ESTEN LLENAS */
	public static boolean termino(int[][] matriz){
		boolean resultado = true;
		
		for(int i = 0; i<matriz.length; i++){
			for(int j = 0; j < matriz[0].length; j++){
				if(matriz[i][j] == 0){
					resultado = false;
				}
			}
		}
		return resultado;
	}
	
	public void eliminaDato(int[][] tabla, int fila, int columna){
		tabla[fila][columna] = 0;
	}
	
	
	//METODO QUE IMPRIME EL VECTOR.
    public static void imprime_vector(int[][] matriz) {
        System.out.println(" °°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°° ");
        
        for(int f = 0; f < matriz.length; f++){
            for(int c = 0; c < matriz.length; c++){
                    System.out.print(matriz[f][c] + " ");
            }
            System.out.println();
        }
    }
    
    /** Creamos una funcion que retorna la posicion del primer espacio libre en mi matriz */
    public static Point buscaEspacio(int[][] tabla){
    	Point posicion = new Point();
    	boolean resp = false;
    	
    	for(int i = 0; i<9 && (resp != true); i++){
    		for(int j = 0; j<9 && (resp != true); j++){
    			if(tabla[i][j] == 0){
    				posicion.setLocation(i,j);
    				resp = true;
    			}
    		}
    	}
    	return posicion;
    }
    
    /**
     * Coloca en una matriz los valores que nos dan inicialmente en la entrada
     */
    public static int[][] cargar_juego() {

        int[][] matriz = new int[9][9];
        
                matriz[0][1] = 7;
                matriz[0][2] = 2;
                matriz[0][8] = 5;
                matriz[1][1] = 6;
                matriz[1][2] = 1;
                matriz[1][6] = 8;
                matriz[1][7] = 4;
                matriz[1][8] = 2;
                matriz[2][2] = 9;
                matriz[2][3] = 2;
                matriz[2][4] = 1;
                matriz[2][5] = 8;
                matriz[2][8] = 7;
                matriz[3][2] = 6;
                matriz[3][8] = 3;
                matriz[4][2] = 8;
                matriz[4][4] = 6;
                matriz[5][0] = 7;
                matriz[5][4] = 3;
                matriz[5][5] = 2;
                matriz[5][6] = 7;
                matriz[5][7] = 6;
                matriz[6][0] = 4;
                matriz[6][3] = 5;
                matriz[6][4] = 9;
                matriz[8][0] = 9;
                matriz[8][3] = 4;
                matriz[8][7] = 7;
                matriz[8][8] = 8; 

        return matriz;
    }
    
 /*   public static void resuelveS2(int x, int y, ArrayList<Ficha> f){
    	boolean fin = termino(tablero);
    	
    	if(!fin) imprime_vector(tablero);
    	else{
    			for(int k = 0; k<f.size(); k++){
    				for(int i = 0; i<9; i++){
    		    		for(int j = 0; j<9; j++){
    		    			if(asignar(tablero, i, j, f.get(k)) == 1){
    		    			}
    		    		}		
    				}
    			}
    		}
    } */
    
    /**
     * Este metodo era usado para la funcion resuelveConCandidatos. Retorna true si es posible meter una ficha en una pos
     * dada de la matriz. (Aun falta rotarla. O Tal vez meter un contador de rotaciones en que sirvio)
     */
    public static boolean sacaPosibilidad(int [][] tabla, int fila, int columna, Ficha f){
		int grados = f.getAngulo();
		int contenedorFila = fila;
		int contenedorColumna = columna;
		boolean respuesta = false;
		
		if(tabla[fila][columna] == 0){	
			
			if(grados == 90){
				if((fila >= 0 && fila <= 8) && (columna >= 0 && columna <= 8)){
		
					if(ingresaFila(tabla, fila, f.getL1()) && ingresaColumna(tabla, columna, f.getL1())
							&& ingresaRegion(tabla, fila, columna, f.getL1())){
						
						contenedorFila = fila - 1;
						contenedorColumna = columna - 1;
						if((fila-1 <= 8 && fila-1 >= 0) && (columna-1 <= 8 && columna-1 >= 0)){
							if(ingresaFila(tabla, contenedorFila, f.getL2()) && ingresaColumna(tabla, contenedorColumna, f.getL2())
									&& ingresaRegion(tabla, contenedorFila, contenedorColumna, f.getL2())){

							respuesta = true;
							}
						}
					}
				}
			}else if(grados == 0){
				if((fila >= 0 && fila <= 8) && (columna >= 0 && columna <= 8)){
					
					if(ingresaFila(tabla, fila, f.getL1()) && ingresaColumna(tabla, columna, f.getL1())
						&& ingresaRegion(tabla, fila, columna, f.getL1())){
					
						if(fila+1 <= 8 && fila+1 >= 0){
						contenedorFila = fila + 1;
						
							if(ingresaFila(tabla, contenedorFila, f.getL2()) && ingresaColumna(tabla, columna, f.getL2())
									&& ingresaRegion(tabla, contenedorFila, columna, f.getL2())){
						
								respuesta = true;	
							}
						}
					}
				}
	
		}else if(grados == 180){

			if((fila >= 0 && fila <= 8) && (columna >= 0 && columna <= 8)){
				
				if(ingresaFila(tabla, fila, f.getL2()) && ingresaColumna(tabla, columna, f.getL2())
						&& ingresaRegion(tabla, fila, columna, f.getL2())){
					
					contenedorColumna = columna + 1;
					
					if(columna+1 <= 8 && columna+1 >= 0){
						if(ingresaFila(tabla, fila, f.getL1()) && ingresaColumna(tabla, contenedorColumna, f.getL1())
								&& ingresaRegion(tabla, fila, contenedorColumna, f.getL1())){
				
							respuesta = true;
						
						}
					}
				}
			}
		}else if(grados == 270){
			
			if((fila >= 0 && fila <= 8) && (columna >= 0 && columna <= 8)){
				
				if(ingresaFila(tabla, fila, f.getL2()) && ingresaColumna(tabla, columna, f.getL2())
						&& ingresaRegion(tabla, fila, columna, f.getL2())){

					contenedorFila = fila - 1;
					contenedorColumna = columna - 1;
					if ((columna - 1 >= 0 && columna - 1 <= 8) && (fila-1 <= 8 && fila-1 >= 0)){
						if(ingresaFila(tabla, contenedorFila, f.getL1()) && ingresaColumna(tabla, contenedorColumna, f.getL1())
								&& ingresaRegion(tabla, contenedorFila, contenedorColumna, f.getL1())){
					
						respuesta = true;
						}
					}
				}
			}
		}
	}
	return respuesta;
}
    
    /*
     * Crea una matriz que contiene la cantidad de candidatos en un espacio de la matriz. El que tenga 1 en la matriz
     * solo tiene una posible ficha para entrar. (Falta rotarla. Aqui solo lo hace con 0 grados)
     */
    public static int[][] creaMatrizDePosibilidades(int [][] tabla, ArrayList<Ficha> listaF, int [][] candi){
    	for(int i = 0; i<9; i++){
    		for(int j = 0; j<9; j++){
    			if((i >= 0 && i <= 8) && (j >= 0 && j <= 8)){
    				for(int k = 0; k<listaF.size(); k++){
    					Ficha f = listaF.get(k);
    					if(sacaPosibilidad(tabla, i, j, f)){
    						candi[i][j] = candi[i][j] + 1;
    					}
    				}
    			}
    		}
    	}
    	return candi;
    }
   
    /**
     * Funcion que resuelve el sudoku y retorna una solucion no factible
     */
    public static void resuelveSudoku(int [][] tablero, ArrayList<Ficha> listaF){
        	//Point p = buscaEspacio(tablero);

    	for(int i = 0; i<9; i++){
    		for(int j = 0; j<9; j++){
    			if(tablero[i][j] == 0){
    				for(int k = 0; k<listaF.size(); k++){
    					asignar(tablero, i, j, listaF.get(k));
    				}
    			}
    		}
    	}
    /*	if(termino(tablero) != true){
    		for(int i = 0; i<9; i++){
        		for(int j = 0; j<9; j++){
        			if(tablero[i][j] == 0){
        				for(int k = 0; k<listaF.size(); k++){
        					asignar(tablero, i, j, listaF.get(k));
        				}
        			}
        		}
        	}
    	} */
   }
    /**
     * Funcion que como su nombre lo indica retorna la posiciòn en donde se encuentra el dato menor de una matriz
     */
 /*   public static Point menorDeMatriz(int [][] matriz){
    	int i,j;
    	i=0;
    	Point posicion = new Point();
    	
    	while (i < matriz.length){
    		for ( j=0;j<matriz[i].length;j++){
    			if (matriz[i][j] > matriz[i][matriz.length-1]){
    				posicion.setLocation(i,matriz.length-1);
    			}
    			i++;
    		}
    	}
    	return posicion;
    } */
    
    /**
     * Funcion que resuelve el sudominoku teniendo en cuenta la matriz de candidatos
     */
    public static void resuelveConCandidatos(int [][] tablero, int [][] candi, ArrayList<Ficha> listaF){
    	for(int i = 0; i<9; i++){
    		for(int j = 0; j<9; j++){
    			if(tablero[i][j] == 0 && candi[i][j] == 1){
    				for(int k = 0; k<listaF.size(); k++){
    					asignar(tablero, i, j, listaF.get(k));
    				}
    			}
    		}
    	}
    	if(termino(tablero) != true){ //Esto es algo innesesario XD
    		for(int i = 0; i<9; i++){
        		for(int j = 0; j<9; j++){
        			if(tablero[i][j] == 0 && candi[i][j] == 1){
        				for(int k = 0; k<listaF.size(); k++){
        					asignar(tablero, i, j, listaF.get(k));
        				}
        			}
        		}
        	}
    	}
    }
    
    /**
     * Funcion usada para otra de las soluciones que propuse. La que solucionaba una vez la matriz y almacenaba en un array
     * de posiciones las posiciones en donde en la soluciòn anterior se habia quedado en 0. Esta le llega ese arreglo y le da
     * prioridad a las posiciones que estan en el para colocar las fichas
     */
    public static void darlePrioridad(int [][] tabla, ArrayList<Posicion> pos, ArrayList<Ficha> listaF){
    	for(int i = 0; i<pos.size(); i++){
    		for(int k = 0; k<listaF.size(); k++){
    			Posicion p = pos.get(i);
				asignar(tabla, p.getX(), p.getY(), listaF.get(k));
			}
    	}
    }
    
    /**
     * Recibe como parametro 2 matrices t1 y t2. Copia en t1 todo lo que contiene t2
     */
    public static int[][] copiaMatriz(int [][] t1, int [][] t2){
    	for(int i = 0; i<9; i++){
    		for(int j = 0; j<9; j++){
    			t1[i][j] = t2[i][j];
    		}
    	}
    	return t1;
    }
	
    public static int[][] borraMatriz(int [][] t1){
    	for(int i = 0; i<9; i++){
    		for(int j = 0; j<9; j++){
    			t1[i][j] = 0;
    		}
    	}
    	return t1;
    }
    
    /**
     * Pone en un arreglo de posiciones. Las posiciones en donde encontro que no puso nada en la tabla
     */
    public static void guardaPosicionesSinUsar(int [][] tabla){
    	for(int i = 0; i<9; i++){
    		for(int j = 0; j<9; j++){
    			if(tabla[i][j] == 0){
    				Posicion p = new Posicion(i,j);
    				sinUsar.add(p);
    			}
    		}
    	}
    }
    
    /**
     * Funcion usada para imprimir las posiciones de la funcion de arriba. Fue solo una prueba
     */
    public static void imprimePosiciones(ArrayList<Posicion> p){
    	for(int i = 0; i<p.size(); i++){
    		Posicion algo = p.get(i);
    		System.out.println("No usaste la posicion " + algo.getX() + "," + algo.getY());
    	}
    }
        
    
	public static void main(String[] args){
		int [][] tablero = new int[9][9];
		int [][] memoriza = new int[9][9];
		int [][] candidatos = new int[9][9];
        //VARIABLES.
        //BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
	
		//LAS FICHAS QUE TENGO COMENTADAS SON LAS QUE YA PUSIERON INICIALMENTE EN LA ENTRADA
        listaDeFichas = new ArrayList<Ficha>(36);
        listaDeFichas.add(new Ficha(1,2,0));
        listaDeFichas.add(new Ficha(1,3,0));
        listaDeFichas.add(new Ficha(1,4,0));
        listaDeFichas.add(new Ficha(1,5,0));
        //listaDeFichas.add(new Ficha(1,6,0));
        listaDeFichas.add(new Ficha(1,7,0));
        listaDeFichas.add(new Ficha(1,8,0));
        listaDeFichas.add(new Ficha(1,9,0));
        //listaDeFichas.add(new Ficha(2,3,0));
        listaDeFichas.add(new Ficha(2,4,0));
        listaDeFichas.add(new Ficha(2,5,0));
        listaDeFichas.add(new Ficha(2,6,0));
        //listaDeFichas.add(new Ficha(2,7,0));
        listaDeFichas.add(new Ficha(2,8,0));
        //listaDeFichas.add(new Ficha(2,9,0));
        listaDeFichas.add(new Ficha(3,4,0));
        listaDeFichas.add(new Ficha(3,5,0));
        listaDeFichas.add(new Ficha(3,6,0));
        listaDeFichas.add(new Ficha(3,7,0));
        listaDeFichas.add(new Ficha(3,8,0));
        listaDeFichas.add(new Ficha(3,9,0));
        
        listaDeFichas.add(new Ficha(4,5,0));
        listaDeFichas.add(new Ficha(4,6,0));
        //listaDeFichas.add(new Ficha(4,7,0));
        //listaDeFichas.add(new Ficha(4,8,0));
        listaDeFichas.add(new Ficha(4,9,0));
        
        listaDeFichas.add(new Ficha(5,6,0));
        listaDeFichas.add(new Ficha(5,7,0));
        listaDeFichas.add(new Ficha(5,8,0));
        //listaDeFichas.add(new Ficha(5,9,0));
        //listaDeFichas.add(new Ficha(6,7,0));
        //listaDeFichas.add(new Ficha(6,8,0));
        listaDeFichas.add(new Ficha(6,9,0));
        //listaDeFichas.add(new Ficha(7,8,0));
        listaDeFichas.add(new Ficha(7,9,0));
        listaDeFichas.add(new Ficha(8,9,0));
        
        //COPIAMOS LAS FICHAS QUE TENEMOS ACTUALMENTE EN LA LISTA
        //copiaFichas = listaDeFichas;
        System.out.println();
        System.out.println("Esta es la matriz original" );
        tablero = cargar_juego();
        imprime_vector(tablero);
       
        
      /*  System.out.println();
        System.out.println("ESTA ES LA MATRIZ DE POSIBILIDADES" );
        creaMatrizDePosibilidades(tablero, listaDeFichas, candidatos);
        imprime_vector(candidatos); */
        System.out.println();
        
        //Ficha temp = new Ficha(7,6,0);
        //asignar(tablero,1,3,temp);
        //resuelveConCandidatos(tablero, candidatos, listaDeFichas);
        //resuelveS2(0, 0, listaDeFichas);
        resuelveSudoku(tablero, listaDeFichas);
        System.out.println("Esta es la primera solucion que encontro del sudoku! ");
        imprime_vector(tablero);
		
       while(!termino(tablero)){
    	    sinUsar = new ArrayList<Posicion>();
        	copiaMatriz(memoriza, tablero);
        	guardaPosicionesSinUsar(memoriza);
        	System.out.println();
        	//imprimePosiciones(sinUsar);
        	
        
        	borraMatriz(tablero);
        	tablero = cargar_juego();
        
        	//System.out.println();
        	System.out.println("Reiniciamos el tablero del juego y luego buscamos otra solucion ");
        	//imprime_vector(memoriza);
        
        	System.out.println();
        	darlePrioridad(tablero, sinUsar, listaDeFichas); //Aqui iba la copia de fichas
        	System.out.println("Este es el tablero vacio. Al que se le dio prioridad los vacios del otro! : " );
        	imprime_vector(tablero);
        
        
        	System.out.println();
        	System.out.println("Esta es la nueva solucion que encontro del sudoku! ");
        	resuelveConCandidatos(tablero, candidatos, listaDeFichas);
        	//resuelveS2(0, 0, listaDeFichas);
        	//resuelveSudoku(tablero, listaDeFichas);
        	imprime_vector(tablero); 
       } 
	} 
}
