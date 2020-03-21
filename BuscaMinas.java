//Simula el juego buscaminas
import java.util.Random;
import java.util.Scanner;

// Author: Johan Astudillo
// By: www.FileTechn.com

public class BuscaMinas {
    public static void main(String[] args) {
        int tamX, tamY;
        
        Scanner consola = new Scanner(System.in);  //Lectura por consola del tamaño del tablero
        do{
            System.out.print("Total filas (mínimo 6): "); 
            tamX = consola.nextInt();
            System.out.print("Total columnas (mínimo 6): "); 
            tamY = consola.nextInt();
        }while(tamX<=5 || tamY<=5);
        
        Random azar = new Random(); //El generador de números aleatorios
        
        //Arreglo bdimensional que será el tablero de Buscaminas. Se inicializa con el caracter punto
        char Tablero[][] = new char[tamX][tamY];
        for (int fila=0; fila < Tablero.length; fila++)
            for (int col=0; col < Tablero[fila].length; col++)
                Tablero[fila][col] = '.';
        
        //Pone el total de minas (30% del tamaño del tablero) al azar
        int posX, posY;
        for (int minas=1; minas <= (int) (tamX*tamY*0.3); minas++){
            do{
                posX = azar.nextInt(Tablero.length);
                posY = azar.nextInt(Tablero[posX].length);
            }while(Tablero[posX][posY]!='.');
            Tablero[posX][posY]='m'; //Pone la mina
        }
        
        //Ciclo del juego
        boolean siguevivo = true; //Controla el ciclo del juego
        do{
            do{ //Pregunta la coordenada a pisar y que sea válida
                System.out.print("Escriba fila donde pisa: "); 
                posX = consola.nextInt();
                System.out.print("Escriba columna donde pisa: "); 
                posY = consola.nextInt();
            }while(posX<0 || posY<0 || posX>=Tablero.length || posY>=Tablero[posX].length);
            
            if (Tablero[posX][posY]=='m'){ //Si ha pisado una mina
                System.out.println("Ha pisado una mina. Juego terminado");
                Tablero[posX][posY]='X';
                siguevivo=false;
            }
            else{ //Cuenta las minas alrededor. Verifica que no cuente fuera de los límites del tablero
                Tablero[posX][posY]='P';
                int minasAlrededor = 0;
                if (posX>0 && posY>0 && Tablero[posX-1][posY-1]=='m') minasAlrededor++;
                if (posY>0 && Tablero[posX][posY-1]=='m') minasAlrededor++;
                if (posX<tamX-1 && posY>0 && Tablero[posX+1][posY-1]=='m') minasAlrededor++;
                if (posX>0 && Tablero[posX-1][posY]=='m') minasAlrededor++;
                if (posX<tamX-1 && Tablero[posX+1][posY]=='m') minasAlrededor++;
                if (posX>0 && posY<tamY-1 && Tablero[posX-1][posY+1]=='m') minasAlrededor++;
                if (posY<tamY-1 && Tablero[posX][posY+1]=='m') minasAlrededor++;
                if (posX<tamX-1 && posY<tamY-1 && Tablero[posX+1][posY+1]=='m') minasAlrededor++;
                System.out.println("Minas alrededor: " + minasAlrededor);
            }
        }while (siguevivo);
        
        //Muestra el tablero con las minas ubicadas y el recorrido del jugador
        for (int fila=0; fila < Tablero.length; fila++){
            System.out.println(" ");
            for (int col=0; col < Tablero[fila].length; col++)
                System.out.print(Tablero[fila][col]);
        }        
    }
    
}
