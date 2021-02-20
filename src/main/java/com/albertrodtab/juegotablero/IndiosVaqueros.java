/*
 * ACTIVIDAD DE APRENDIZAJE:JUEGO DE INDIOS - VAQUEROS
 * Vamos a realizar un juego de tablero que permita jugar por turnos a dos
 * jugadores.
 */
package com.albertrodtab.juegotablero;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Alberto Rodríguez Taboada
 */
public class IndiosVaqueros {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //DECLARACIÓN CONSTANTES
        final int FILAS = 5;           //Núm. filas del tablero del jugador
        final int COLUMNAS = 5;        //Núm. columnas del tablero del jugador
        final char LIBRE_CHAR = 'L';   //Letra para la celda libre
        final char INDIO_CHAR = 'I';   //Letra para la celda ocupada por Indio
        final char VAQUERO_CHAR = 'V'; //Letra para la celda ocupada por Vaquero
        final int NUM_INDIOS = 5;      //Núm. indios en tablero Vaquero
        final int NUM_VAQUEROS = 5;    //Núm. vaqueros en tablero Indios

        //DECLARACIÓN VARIABLE
        int filaIndio = 0;              //fila tablero indio
        int columnaIndio = 0;           //columna tablero indio
        int filaVaquero = 0;            //fila tablero vaquero
        int columnaVaquero = 0;         //columna tablero vaquero
        int vidasIndio = 3;             //Núm. vidas iniciales
        int vidasVaquero = 3;           //Núm. vidas iniciales
        boolean muerto = false;         //Variable de control para fin del juego

        String teclaJugador = " ";   //Variable para lectura por teclado

        //VARIABLES PARA FORMAR TABLEROS
        char[][] tableroIndio = new char[FILAS][COLUMNAS];
        char[][] tableroVaquero = new char[FILAS][COLUMNAS];
        char[][] tableroIndioM = new char[FILAS][COLUMNAS];
        char[][] tableroVaqueroM = new char[FILAS][COLUMNAS];

        //CLASES UTILIDAD
        Random genAleatorio = new Random();
        Scanner tc = new Scanner(System.in);

        /*
        PARTE 1 - DISEÑO DEL TABLERO
         */
        dibujarTablero(tableroIndio, tableroIndioM, FILAS, COLUMNAS, LIBRE_CHAR);
        dibujarTablero(tableroVaquero, tableroVaqueroM, FILAS, COLUMNAS, LIBRE_CHAR);

        //IMPRESIÓN TABLERO EN CUALQUIER MOMENTO UTILIZA ESTA PARTE DE CÓDIGO
        /*
        Utilizamos una función para evitar escribir siempre el código que
        obliga a recorrer toda la matriz para imprimirla
         */
//        mostrarTablero(tableroIndio, FILAS, COLUMNAS);
//        mostrarTablero(tableroVaquero, FILAS, COLUMNAS);

        /*
        PARTE 2 - COLOCAMOS FICHAS DE JUEGO EN EL TABLERO
         */
        //GENERAMOS POSICION INICIAL INDIO
        int[] posicionIndio = generarJugadores(FILAS, COLUMNAS, tableroIndio, tableroIndioM, INDIO_CHAR);
        filaIndio = posicionIndio[0];
        columnaIndio = posicionIndio[1];

        //GENERAMOS POSICION INICIAL VAQUERO
        int[] posicionVaquero = generarJugadores(FILAS, COLUMNAS, tableroVaquero, tableroVaqueroM, VAQUERO_CHAR);
        filaVaquero = posicionVaquero[0];
        columnaVaquero = posicionVaquero[1];

        //PARA VER DONDE ESTÁN COLOCADOS SOLO LOS JUGADORES
//        mostrarTablero(tableroIndio, FILAS, COLUMNAS);
//        mostrarTablero(tableroVaquero, FILAS, COLUMNAS);

        /*
        COLOCAMOS LOS ENEMIGOS CORRESPONDIENTES EN CADA TABLERO
         */
        generarEnemigos(NUM_INDIOS, FILAS, COLUMNAS, tableroVaquero, INDIO_CHAR);
        generarEnemigos(NUM_VAQUEROS, FILAS, COLUMNAS, tableroIndio, VAQUERO_CHAR);

        //PARA VER DONDE ESTÁN COLOCADAS TODAS LAS PIEZAS
//        mostrarTablero(tableroIndio, FILAS, COLUMNAS);
//        mostrarTablero(tableroVaquero, FILAS, COLUMNAS);

        /*
        PARTE 3 - EJECUCIÓN DEL JUEGO
         */
        while (!muerto) {
            System.out.println("----------------------------");
            System.out.println("TABLERO INDIO");
            //INFORMO DEL NÚMERO DE VIDAS AL INICIO DEL TURNO
            switch (vidasIndio) {
                case 2:
                    System.out.println("RECUERDA que un Vaquero "
                            + "te ha herido en una pierna por lo que te"
                            + " quedan " + vidasIndio + " vidas.");
                    break;
                case 1:
                    System.out.println("RECUERDA YA TE HAN HERIDO 2 Vaqueros "
                            + "estas herido en el abdomen por lo que te"
                            + " queda " + vidasIndio + ""
                            + " vida. Ten cuidado si te disparan una vez más "
                            + " estarás muerto.");
                    break;
                case 3:
                    System.out.println("Tienes 3 vidas");
                    break;
            }

            /*
            IMPLEMENTO 2 OPCIONES DE TABLERO UNA CON LOS OPONENTES VISIBLES
            PARA HACER PRUEBAS Y OTRA SOLO CON LA PIEZA VISIBLE DEL JUGADOR
            PARA PODER EJECUTAR EL JUEGO Y HACERLO FUNCIONAL, POR DEFECTO AHORA
            MUESTRO EL TRABLERO CON LOS OPONENTES PARA PODER COMPROBAR FACILMENTE
            FUNCIONALIDADES
            */
            //MOSTRAR TABLERO CON INDIO Y OPONENTES
            mostrarTablero(tableroIndio, FILAS, COLUMNAS);

//            //MOSTRAR TABLERO SOLO CON JUGADOR INDIO
//            mostrarTablero(tableroIndioM, FILAS, COLUMNAS);

            System.out.println("Indio pulsa una tecla para moverte (W A S D): "
                    + "recuerda tener cuidado con los vaqueros");
            teclaJugador = tc.nextLine();

            switch (teclaJugador) {
                /*
                PARA QUE SE MUEVA CON MAYÚSCULAS O MINÚSCULAS IMPLEMENTO DOS
                CASE UNA EN MAYUSCULA Y OTRO EN MINÚSCULA
                */
                case "W":
                case "w":
                    //ESTA TECLA MUEVE AL JUGADOR UNA CELDA HACIA ARRIBA
                    /*
                    SI EL JUGADOR ESTÁ EN EL LIMITE SUPERIOR DEL TABLERO CON
                    ESTE CONDICIONAL EVITO LOS LIMITES Y APAREZCO POR LA PARTE
                    DE ABAJO DEL TABLERO
                    */
                    if (filaIndio == 0) {
                        tableroIndio[filaIndio][columnaIndio] = LIBRE_CHAR;
                        tableroIndioM[filaIndio][columnaIndio] = LIBRE_CHAR;
                        filaIndio = 4;

                        //Si lo que hay en la casilla nueva, NO ES UN VAQUERO
                        if (tableroIndio[filaIndio][columnaIndio] != VAQUERO_CHAR) {
                            //Casilla nueva (hacia donde me desplazo)
                            tableroIndio[filaIndio][columnaIndio] = INDIO_CHAR;
                            tableroIndioM[filaIndio][columnaIndio] = INDIO_CHAR;
                            //Si lo que hay en la casilla nueva, es un ENEMIGO
                        } else {
                            tableroIndio[filaIndio][columnaIndio] = INDIO_CHAR;
                            tableroIndioM[filaIndio][columnaIndio] = INDIO_CHAR;
                            vidasIndio = quitaVida(vidasIndio);
                            System.out.println("Celda enemiga pierdes una vida.");
                        }
                    } else {
                        //Si lo que hay en la casilla nueva, NO ES UN VAQUERO
                        if (tableroIndio[filaIndio - 1][columnaIndio] != VAQUERO_CHAR) {
                            //Casilla nueva (hacia donde me desplazo)
                            tableroIndio[filaIndio - 1][columnaIndio] = INDIO_CHAR;
                            tableroIndioM[filaIndio - 1][columnaIndio] = INDIO_CHAR;
                            //Casilla vieja (la que abandono)
                            tableroIndio[filaIndio][columnaIndio] = LIBRE_CHAR;
                            tableroIndioM[filaIndio][columnaIndio] = LIBRE_CHAR;
                            filaIndio--;
                            //Si lo que hay en la casilla nueva es un ENEMIGO
                        } else if (tableroIndio[filaIndio - 1][columnaIndio] == VAQUERO_CHAR) {
                            //Casilla nueva (hacia donde me desplazo)
                            tableroIndio[filaIndio - 1][columnaIndio] = INDIO_CHAR;
                            tableroIndioM[filaIndio - 1][columnaIndio] = INDIO_CHAR;
                            //Casilla vieja (la que abandono)
                            tableroIndio[filaIndio][columnaIndio] = LIBRE_CHAR;
                            tableroIndioM[filaIndio][columnaIndio] = LIBRE_CHAR;
                            filaIndio--;
                            vidasIndio = quitaVida(vidasIndio);
                            System.out.println("Celda enemiga pierdes una vida.");
                        }
                    }
                    break;
                /*
                PARA QUE SE MUEVA CON MAYÚSCULAS O MINÚSCULAS IMPLEMENTO DOS
                CASE UNA EN MAYUSCULA Y OTRO EN MINÚSCULA
                */
                case "S":
                case "s":
                    //ESTA TECLA MUEVE AL JUGADOR UNA CELDA HACIA ABAJO
                    /*
                    SI EL JUGADOR ESTÁ EN EL LIMITE INFERIOR DEL TABLERO CON ESTE
                    CONDICIONAL EVITO LOS LIMITES Y APAREZCO POR LA PARTE
                    SUPERIOR DEL TABLERO
                    */
                    if (filaIndio == 4) {
                        tableroIndio[filaIndio][columnaIndio] = LIBRE_CHAR;
                        tableroIndioM[filaIndio][columnaIndio] = LIBRE_CHAR;
                        filaIndio = 0;
                        //Si lo que hay en la casilla nueva, NO ES UN VAQUERO
                        if (tableroIndio[filaIndio][columnaIndio] != VAQUERO_CHAR) {
                            //Casilla nueva (hacia donde me desplazo)
                            tableroIndio[filaIndio][columnaIndio] = INDIO_CHAR;
                            tableroIndioM[filaIndio][columnaIndio] = INDIO_CHAR;
                            //Si lo que hay en la casilla nueva, es un ENEMIGO
                        } else {
                            tableroIndio[filaIndio][columnaIndio] = INDIO_CHAR;
                            tableroIndioM[filaIndio][columnaIndio] = INDIO_CHAR;
                            vidasIndio = quitaVida(vidasIndio);
                            System.out.println("Celda enemiga pierdes una vida.");
                        }
                    } else {
                        //Si lo que hay en la casilla nueva, NO ES UN VAQUERO
                        if (tableroIndio[filaIndio + 1][columnaIndio] != VAQUERO_CHAR) {
                            //Casilla nueva (hacia donde me desplazo)
                            tableroIndio[filaIndio + 1][columnaIndio] = INDIO_CHAR;
                            tableroIndioM[filaIndio + 1][columnaIndio] = INDIO_CHAR;
                            //Casilla vieja (la que abandono)
                            tableroIndio[filaIndio][columnaIndio] = LIBRE_CHAR;
                            tableroIndioM[filaIndio][columnaIndio] = LIBRE_CHAR;
                            filaIndio++;
                            //Si lo que hay en la casilla nueva es un ENEMIGO
                        } else if (tableroIndio[filaIndio + 1][columnaIndio] == VAQUERO_CHAR) {
                            //Casilla nueva (hacia donde me desplazo)
                            tableroIndio[filaIndio + 1][columnaIndio] = INDIO_CHAR;
                            tableroIndioM[filaIndio + 1][columnaIndio] = INDIO_CHAR;
                            //Casilla vieja (la que abandono)
                            tableroIndio[filaIndio][columnaIndio] = LIBRE_CHAR;
                            tableroIndioM[filaIndio][columnaIndio] = LIBRE_CHAR;
                            filaIndio++;
                            vidasIndio = quitaVida(vidasIndio);
                            System.out.println("Celda enemiga pierdes una vida.");
                        }
                    }
                    break;
                /*
                PARA QUE SE MUEVA CON MAYÚSCULAS O MINÚSCULAS IMPLEMENTO DOS
                CASE UNA EN MAYUSCULA Y OTRO EN MINÚSCULA
                */
                case "A":
                case "a":
                    //ESTA TECLA MUEVE AL JUGADOR UNA CELDA HACIA LA IZQUIERDA
                    /*
                    SI EL JUGADOR ESTÁ EN EL LIMITE IZQUIERDO DEL TABLERO CON
                    ESTE CONDICIONAL EVITO LOS LIMITES Y APAREZCO POR LA PARTE
                    DERECHA DEL TABLERO
                    */
                    if (columnaIndio == 0) {
                        tableroIndio[filaIndio][columnaIndio] = LIBRE_CHAR;
                        tableroIndioM[filaIndio][columnaIndio] = LIBRE_CHAR;
                        columnaIndio = 4;
                        //Si lo que hay en la casilla nueva, NO ES UN VAQUERO
                        if (tableroIndio[filaIndio][columnaIndio] != VAQUERO_CHAR) {
                            //Casilla nueva (hacia donde me desplazo)
                            tableroIndio[filaIndio][columnaIndio] = INDIO_CHAR;
                            tableroIndioM[filaIndio][columnaIndio] = INDIO_CHAR;
                            //Si lo que hay en la casilla nueva, es un ENEMIGO
                        } else {
                            tableroIndio[filaIndio][columnaIndio] = INDIO_CHAR;
                            tableroIndioM[filaIndio][columnaIndio] = INDIO_CHAR;
                            vidasIndio = quitaVida(vidasIndio);
                            System.out.println("Celda enemiga pierdes una vida.");
                        }
                    } else {
                        //Si lo que hay en la casilla nueva, NO ES UN VAQUERO
                        if (tableroIndio[filaIndio][columnaIndio - 1] != VAQUERO_CHAR) {
                            //Casilla nueva (hacia donde me desplazo)
                            tableroIndio[filaIndio][columnaIndio - 1] = INDIO_CHAR;
                            tableroIndioM[filaIndio][columnaIndio - 1] = INDIO_CHAR;
                            //Casilla vieja (la que abandono)
                            tableroIndio[filaIndio][columnaIndio] = LIBRE_CHAR;
                            tableroIndioM[filaIndio][columnaIndio] = LIBRE_CHAR;
                            columnaIndio--;
                            //Si lo que hay en la casilla nueva es un ENEMIGO
                        } else if (tableroIndio[filaIndio][columnaIndio - 1] == VAQUERO_CHAR) {
                            //Casilla nueva (hacia donde me desplazo)
                            tableroIndio[filaIndio][columnaIndio - 1] = INDIO_CHAR;
                            tableroIndioM[filaIndio][columnaIndio - 1] = INDIO_CHAR;
                            //Casilla vieja (la que abandono)
                            tableroIndio[filaIndio][columnaIndio] = LIBRE_CHAR;
                            tableroIndioM[filaIndio][columnaIndio] = LIBRE_CHAR;
                            columnaIndio--;
                            vidasIndio = quitaVida(vidasIndio);
                            System.out.println("Celda enemiga pierdes una vida.");
                        }
                    }
                    break;
                /*
                PARA QUE SE MUEVA CON MAYÚSCULAS O MINÚSCULAS IMPLEMENTO DOS
                CASE UNA EN MAYUSCULA Y OTRO EN MINÚSCULA
                */
                case "D":
                case "d":
                    //ESTA TECLA MUEVE AL JUGADOR UNA CELDA HACIA LA DERECHA
                    /*
                    SI EL JUGADOR ESTÁ EN EL LIMITE DERECHO DEL TABLERO CON ESTE
                    CONDICIONAL EVITO LOS LIMITES Y APAREZCO POR LA PARTE
                    IZQUIERDA DEL TABLERO
                    */
                    if (columnaIndio == 4) {
                        tableroIndio[filaIndio][columnaIndio] = LIBRE_CHAR;
                        tableroIndioM[filaIndio][columnaIndio] = LIBRE_CHAR;
                        columnaIndio = 0;

                        //Si lo que hay en la casilla nueva, NO ES UN VAQUERO
                        if (tableroIndio[filaIndio][columnaIndio] != VAQUERO_CHAR) {
                            //Casilla nueva (hacia donde me desplazo)
                            tableroIndio[filaIndio][columnaIndio] = INDIO_CHAR;
                            tableroIndioM[filaIndio][columnaIndio] = INDIO_CHAR;
                            //Si lo que hay en la casilla nueva, es un ENEMIGO
                        } else {
                            tableroIndio[filaIndio][columnaIndio] = INDIO_CHAR;
                            tableroIndioM[filaIndio][columnaIndio] = INDIO_CHAR;
                            vidasIndio = quitaVida(vidasIndio);
                            System.out.println("Celda enemiga pierdes una vida.");
                        }
                    } else {
                        //Si lo que hay en la casilla nueva, NO ES UN VAQUERO
                        if (tableroIndio[filaIndio][columnaIndio + 1] != VAQUERO_CHAR) {
                            //Casilla nueva (hacia donde me desplazo)
                            tableroIndio[filaIndio][columnaIndio + 1] = INDIO_CHAR;
                            tableroIndioM[filaIndio][columnaIndio + 1] = INDIO_CHAR;
                            //Casilla vieja (la que abandono)
                            tableroIndio[filaIndio][columnaIndio] = LIBRE_CHAR;
                            tableroIndioM[filaIndio][columnaIndio] = LIBRE_CHAR;
                            columnaIndio++;
                            //Si lo que hay en la casilla nueva es un ENEMIGO
                        } else if (tableroIndio[filaIndio][columnaIndio + 1] == VAQUERO_CHAR) {
                            //Casilla nueva (hacia donde me desplazo)
                            tableroIndio[filaIndio][columnaIndio + 1] = INDIO_CHAR;
                            tableroIndioM[filaIndio][columnaIndio + 1] = INDIO_CHAR;
                            //Casilla vieja (la que abandono)
                            tableroIndio[filaIndio][columnaIndio] = LIBRE_CHAR;
                            tableroIndioM[filaIndio][columnaIndio] = LIBRE_CHAR;
                            columnaIndio++;
                            vidasIndio = quitaVida(vidasIndio);
                            System.out.println("Celda enemiga pierdes una vida.");
                        }
                    }
                    break;
                /*
                SI UN JUGADOR PULSA UNA TECLA ERRONEA LE AVISO DE QUE PIERDE EL
                TURNO Y QUE SU JUGADOR NO SE MUEVE
                */
                default:
                    System.out.println("TECLA ERRÓNEA PIERDES TURNO NO TE HAS "
                            + "MOVIDO");
            }

            //MOSTRAR TABLERO CON INDIO Y OPONENTES TRAS MOVIMIENTO
            System.out.println("Nueva posicion tras movimiento");
            mostrarTablero(tableroIndio, FILAS, COLUMNAS);

//            //MOSTRAR TABLERO SOLO CON JUGADOR INDIO
//            System.out.println("Nueva posicion tras movimiento");
//            mostrarTablero(tableroIndioM, FILAS, COLUMNAS);

            /*
            Con esté condicional evito que si el indio ya ha terminado las
            vidas el juego se continue ejecutando y salga ya por tanto del bucle
            y finalice el juego.
             */
            if (vidasIndio != 0) {
                //MOVIMIENTOS PARA EL TRABLERO DEL VAQUERO
                System.out.println("--------------------------");
                System.out.println("TABLERO VAQUERO");
                //INFORMO DEL NÚMERO DE VIDAS AL INICIO DEL TURNO
                switch (vidasVaquero) {
                    case 2:
                        System.out.println("RECUERDA que un Indio "
                                + "te ha herido en una pierna por lo que te"
                                + " quedan " + vidasVaquero + " vidas.");
                        break;
                    case 1:
                        System.out.println("RECUERDA YA TE HAN HERIDO 2 Indios "
                                + "estás herido en el abdomen por lo que te"
                                + " queda " + vidasVaquero + " vida. Ten cuidado si te "
                                + "disparan una vez más estarás muerto.");
                        break;
                    case 3:
                        System.out.println("Tienes 3 vidas");
                        break;
                }

                /*
                IMPLEMENTO 2 OPCIONES DE TABLERO UNA CON LOS OPONENTES VISIBLES
                PARA HACER PRUEBAS Y OTRA SOLO CON LA PIEZA VISIBLE DEL JUGADOR
                PARA PODER EJECUTAR EL JUEGO Y HACERLO FUNCIONAL, POR DEFECTO AHORA
                MUESTRO EL TRABLERO CON LOS OPONENTES PARA PODER COMPROBAR FACILMENTE
                FUNCIONALIDADES
                */
                //MOSTRAR TABLERO CON VAQYERO Y OPONENTES
                mostrarTablero(tableroVaquero, FILAS, COLUMNAS);

//                //MOSTRAR TABLLERO SOLO CON JUGADOR VAQUERO
//                mostrarTablero(tableroVaqueroM, FILAS, COLUMNAS);
                System.out.println("Vaquero pulsa una tecla para moverte (W A S D): "
                        + "recuerda tener cuidado con los Indios");

                teclaJugador = tc.nextLine();

                switch (teclaJugador) {
                    /*
                    PARA QUE SE MUEVA CON MAYÚSCULAS O MINÚSCULAS IMPLEMENTO DOS
                    CASE UNA EN MAYUSCULA Y OTRO EN MINÚSCULA
                    */
                    case "W":
                    case "w":
                        //ESTA TECLA MUEVE AL JUGADOR UNA CELDA HACIA ARRIBA
                        /*
                        SI EL JUGADOR ESTÁ EN EL LIMITE SUPERIOR DEL TABLERO CON
                        ESTE CONDICIONAL EVITO LOS LIMITES Y APAREZCO POR LA PARTE
                        DE ABAJO DEL TABLERO
                        */
                        if (filaVaquero == 0) {
                            tableroVaquero[filaVaquero][columnaVaquero] = LIBRE_CHAR;
                            tableroVaqueroM[filaVaquero][columnaVaquero] = LIBRE_CHAR;
                            filaVaquero = 4;

                            //Si lo que hay en la casilla nueva, NO ES UN INDIO
                            if (tableroVaquero[filaVaquero][columnaVaquero] != INDIO_CHAR) {
                                //Casilla nueva (hacia donde me desplazo)
                                tableroVaquero[filaVaquero][columnaVaquero] = VAQUERO_CHAR;
                                tableroVaqueroM[filaVaquero][columnaVaquero] = VAQUERO_CHAR;
                                //Si lo que hay en la casilla nueva, es un INDIO
                            } else {
                                tableroVaquero[filaVaquero][columnaVaquero] = VAQUERO_CHAR;
                                tableroVaqueroM[filaVaquero][columnaVaquero] = VAQUERO_CHAR;
                                vidasVaquero = quitaVida(vidasVaquero);
                                System.out.println("Celda enemiga pierdes una vida.");
                            }
                        } else {
                            //Si lo que hay en la casilla nueva, NO ES UN INDIO
                            if (tableroVaquero[filaVaquero - 1][columnaVaquero] != INDIO_CHAR) {
                                //Casilla nueva (hacia donde me desplazo)
                                tableroVaquero[filaVaquero - 1][columnaVaquero] = VAQUERO_CHAR;
                                tableroVaqueroM[filaVaquero - 1][columnaVaquero] = VAQUERO_CHAR;
                                //Casilla vieja (la que abandono)
                                tableroVaquero[filaVaquero][columnaVaquero] = LIBRE_CHAR;
                                tableroVaqueroM[filaVaquero][columnaVaquero] = LIBRE_CHAR;
                                filaVaquero--;
                                //Si lo que hay en la casilla nueva es un INDIO
                            } else if (tableroVaquero[filaVaquero - 1][columnaVaquero] == INDIO_CHAR) {
                                //Casilla nueva (hacia donde me desplazo)
                                tableroVaquero[filaVaquero - 1][columnaVaquero] = VAQUERO_CHAR;
                                tableroVaqueroM[filaVaquero - 1][columnaVaquero] = VAQUERO_CHAR;
                                //Casilla vieja (la que abandono)
                                tableroVaquero[filaVaquero][columnaVaquero] = LIBRE_CHAR;
                                tableroVaqueroM[filaVaquero][columnaVaquero] = LIBRE_CHAR;
                                filaVaquero--;
                                vidasVaquero = quitaVida(vidasVaquero);
                                System.out.println("Celda enemiga pierdes una vida.");
                            }
                        }
                        break;
                    /*
                    PARA QUE SE MUEVA CON MAYÚSCULAS O MINÚSCULAS IMPLEMENTO DOS
                    CASE UNA EN MAYUSCULA Y OTRO EN MINÚSCULA
                    */
                    case "S":
                    case "s":
                        //ESTA TECLA MUEVE AL JUGADOR UNA CELDA HACIA ABAJO
                        /*
                        SI EL JUGADOR ESTÁ EN EL LIMITE INFERIOR DEL TABLERO CON ESTE
                        CONDICIONAL EVITO LOS LIMITES Y APAREZCO POR LA PARTE
                        SUPERIOR DEL TABLERO
                        */
                        if (filaVaquero == 4) {
                            tableroVaquero[filaVaquero][columnaVaquero] = LIBRE_CHAR;
                            tableroVaqueroM[filaVaquero][columnaVaquero] = LIBRE_CHAR;
                            filaVaquero = 0;
                            //Si lo que hay en la casilla nueva, NO ES UN INDIO
                            if (tableroVaquero[filaVaquero][columnaVaquero] != INDIO_CHAR) {
                                //Casilla nueva (hacia donde me desplazo)
                                tableroVaquero[filaVaquero][columnaVaquero] = VAQUERO_CHAR;
                                tableroVaqueroM[filaVaquero][columnaVaquero] = VAQUERO_CHAR;
                                //Si lo que hay en la casilla nueva, es un INDIO
                            } else {
                                tableroVaquero[filaVaquero][columnaVaquero] = VAQUERO_CHAR;
                                tableroVaqueroM[filaVaquero][columnaVaquero] = VAQUERO_CHAR;
                                vidasVaquero = quitaVida(vidasVaquero);
                                System.out.println("Celda enemiga pierdes una vida.");
                            }
                        } else {
                            //Si lo que hay en la casilla nueva, NO ES UN INDIO
                            if (tableroVaquero[filaVaquero + 1][columnaVaquero] != INDIO_CHAR) {
                                //Casilla nueva (hacia donde me desplazo)
                                tableroVaquero[filaVaquero + 1][columnaVaquero] = VAQUERO_CHAR;
                                tableroVaqueroM[filaVaquero + 1][columnaVaquero] = VAQUERO_CHAR;
                                //Casilla vieja (la que abandono)
                                tableroVaquero[filaVaquero][columnaVaquero] = LIBRE_CHAR;
                                tableroVaqueroM[filaVaquero][columnaVaquero] = LIBRE_CHAR;
                                filaVaquero++;
                                //Si lo que hay en la casilla nueva es un INDIO
                            } else if (tableroVaquero[filaVaquero + 1][columnaVaquero] == INDIO_CHAR) {
                                //Casilla nueva (hacia donde me desplazo)
                                tableroVaquero[filaVaquero + 1][columnaVaquero] = VAQUERO_CHAR;
                                tableroVaqueroM[filaVaquero + 1][columnaVaquero] = VAQUERO_CHAR;
                                //Casilla vieja (la que abandono)
                                tableroVaquero[filaVaquero][columnaVaquero] = LIBRE_CHAR;
                                tableroVaqueroM[filaVaquero][columnaVaquero] = LIBRE_CHAR;
                                filaVaquero++;
                                vidasVaquero = quitaVida(vidasVaquero);
                                System.out.println("Celda enemiga pierdes una vida.");
                            }
                        }
                        break;
                    /*
                    PARA QUE SE MUEVA CON MAYÚSCULAS O MINÚSCULAS IMPLEMENTO DOS
                    CASE UNA EN MAYUSCULA Y OTRO EN MINÚSCULA
                    */
                    case "A":
                    case "a":
                        //ESTA TECLA MUEVE AL JUGADOR UNA CELDA HACIA LA IZQUIERDA
                        /*
                        SI EL JUGADOR ESTÁ EN EL LIMITE IZQUIERDO DEL TABLERO CON
                        ESTE CONDICIONAL EVITO LOS LIMITES Y APAREZCO POR LA PARTE
                        DERECHA DEL TABLERO
                        */
                        if (columnaVaquero == 0) {
                            tableroVaquero[filaVaquero][columnaVaquero] = LIBRE_CHAR;
                            tableroVaqueroM[filaVaquero][columnaVaquero] = LIBRE_CHAR;
                            columnaVaquero = 4;
                            //Si lo que hay en la casilla nueva, NO ES UN INDIO
                            if (tableroVaquero[filaVaquero][columnaVaquero] != INDIO_CHAR) {
                                //Casilla nueva (hacia donde me desplazo)
                                tableroVaquero[filaVaquero][columnaVaquero] = VAQUERO_CHAR;
                                tableroVaqueroM[filaVaquero][columnaVaquero] = VAQUERO_CHAR;
                                //Si lo que hay en la casilla nueva, es un INDIO
                            } else {
                                tableroVaquero[filaVaquero][columnaVaquero] = VAQUERO_CHAR;
                                tableroVaqueroM[filaVaquero][columnaVaquero] = VAQUERO_CHAR;
                                vidasVaquero = quitaVida(vidasVaquero);
                                System.out.println("Celda enemiga pierdes una vida.");
                            }
                        } else {
                            //Si lo que hay en la casilla nueva, NO ES UN INDIO
                            if (tableroVaquero[filaVaquero][columnaVaquero - 1] != INDIO_CHAR) {
                                //Casilla nueva (hacia donde me desplazo)
                                tableroVaquero[filaVaquero][columnaVaquero - 1] = VAQUERO_CHAR;
                                tableroVaqueroM[filaVaquero][columnaVaquero - 1] = VAQUERO_CHAR;
                                //Casilla vieja (la que abandono)
                                tableroVaquero[filaVaquero][columnaVaquero] = LIBRE_CHAR;
                                tableroVaqueroM[filaVaquero][columnaVaquero] = LIBRE_CHAR;
                                columnaVaquero--;
                                //Si lo que hay en la casilla nueva es un INDIO
                            } else if (tableroVaquero[filaVaquero][columnaVaquero - 1] == INDIO_CHAR) {
                                //Casilla nueva (hacia donde me desplazo)
                                tableroVaquero[filaVaquero][columnaVaquero - 1] = VAQUERO_CHAR;
                                tableroVaqueroM[filaVaquero][columnaVaquero - 1] = VAQUERO_CHAR;
                                //Casilla vieja (la que abandono)
                                tableroVaquero[filaVaquero][columnaVaquero] = LIBRE_CHAR;
                                tableroVaqueroM[filaVaquero][columnaVaquero] = LIBRE_CHAR;
                                columnaVaquero--;
                                vidasVaquero = quitaVida(vidasVaquero);
                                System.out.println("Celda enemiga pierdes una vida.");
                            }
                        }
                        break;
                    case "D":
                    case "d":
                        //ESTA TECLA MUEVE AL JUGADOR UNA CELDA HACIA LA DERECHA
                        /*
                        SI EL JUGADOR ESTÁ EN EL LIMITE DERECHO DEL TABLERO CON ESTE
                        CONDICIONAL EVITO LOS LIMITES Y APAREZCO POR LA PARTE
                        IZQUIERDA DEL TABLERO
                        */
                        if (columnaVaquero == 4) {
                            tableroVaquero[filaVaquero][columnaVaquero] = LIBRE_CHAR;
                            tableroVaqueroM[filaVaquero][columnaVaquero] = LIBRE_CHAR;
                            columnaVaquero = 0;

                            //Si lo que hay en la casilla nueva, NO ES UN INDIO
                            if (tableroVaquero[filaVaquero][columnaVaquero] != INDIO_CHAR) {
                                //Casilla nueva (hacia donde me desplazo)
                                tableroVaquero[filaVaquero][columnaVaquero] = VAQUERO_CHAR;
                                tableroVaqueroM[filaVaquero][columnaVaquero] = VAQUERO_CHAR;
                                //Si lo que hay en la casilla nueva, es un INDIO
                            } else {
                                tableroVaquero[filaVaquero][columnaVaquero] = VAQUERO_CHAR;
                                tableroVaqueroM[filaVaquero][columnaVaquero] = VAQUERO_CHAR;
                                vidasVaquero = quitaVida(vidasVaquero);
                                System.out.println("Celda enemiga pierdes una vida.");
                            }
                        } else {
                            //Si lo que hay en la casilla nueva, NO ES UN INDIO
                            if (tableroVaquero[filaVaquero][columnaVaquero + 1] != INDIO_CHAR) {
                                //Casilla nueva (hacia donde me desplazo)
                                tableroVaquero[filaVaquero][columnaVaquero + 1] = VAQUERO_CHAR;
                                tableroVaqueroM[filaVaquero][columnaVaquero + 1] = VAQUERO_CHAR;
                                //Casilla vieja (la que abandono)
                                tableroVaquero[filaVaquero][columnaVaquero] = LIBRE_CHAR;
                                tableroVaqueroM[filaVaquero][columnaVaquero] = LIBRE_CHAR;
                                columnaVaquero++;
                                //Si lo que hay en la casilla nueva es un INDIO
                            } else if (tableroVaquero[filaVaquero][columnaVaquero + 1] == INDIO_CHAR) {
                                //Casilla nueva (hacia donde me desplazo)
                                tableroVaquero[filaVaquero][columnaVaquero + 1] = VAQUERO_CHAR;
                                tableroVaqueroM[filaVaquero][columnaVaquero + 1] = VAQUERO_CHAR;
                                //Casilla vieja (la que abandono)
                                tableroVaquero[filaVaquero][columnaVaquero] = LIBRE_CHAR;
                                tableroVaqueroM[filaVaquero][columnaVaquero] = LIBRE_CHAR;
                                columnaVaquero++;
                                vidasVaquero = quitaVida(vidasVaquero);
                                System.out.println("Celda enemiga pierdes una vida.");
                            }
                        }
                        break;
                    /*
                    SI UN JUGADOR PULSA UNA TECLA ERRONEA LE AVISO DE QUE PIERDE EL
                    TURNO Y QUE SU JUGADOR NO SE MUEVE
                    */
                    default:
                        System.out.println("TECLA ERRÓNEA PIERDES TURNO");

                }
                //MOSTRAR TABLERO CON JUGADOR VAQUERO Y OPONENTES TRAS MOVIMIENTO
                System.out.println("Nueva posicion tras movimiento");
                mostrarTablero(tableroVaquero, FILAS, COLUMNAS);

//                //MOSTRAR TABLLERO SOLO CON JUGADOR VAQUERO
//                System.out.println("Nueva posicion tras movimiento");
//                mostrarTablero(tableroVaqueroM, FILAS, COLUMNAS);
            }
            /*
            CON ESTE CONDICIONAL COMPRUEBO LAS VIDAS DE CADA JUGADOR ANTES DE
            VOLVER A INCIAR EL CONDICIONAL PARA COMPROBAR SI EL JUEGO DEBE
            CONTINUAR O DEBE FINALIZAR.
            */
            if (vidasIndio == 0 || vidasVaquero == 0) {
                muerto = true;
            }
        }

        /*
        INDICO QUE JUGADOR HA GANADO EL JUEGO
        */
        System.out.println("");
        if (vidasIndio == 0) {
            System.out.println("INDIO TE HAN HERIDO TRES VECES LOS "
                    + "VAQUEROS HAN GANADO.");
        } else if (vidasVaquero == 0) {
            System.out.println("VAQUERO TE HAN HERIDO TRES VECES LOS "
                    + "INDIOS HAN GANADO.");
        }
    }

    /*
    FUNCIONES QUE HE APLICADO A MI CODIGO
    */

    //FUNCIÓN PARA CREAR LOS DISTINTOS TABLEROS DE JUEGO CON TODAS LAS CELDAS LIBRES
    public static void dibujarTablero(char[][] matriz, char[][] matriz2, int filas, int columnas, char letra) {

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = letra;
                matriz2[i][j] = letra;
            }
        }
    }

    //FUNCIÓN PARA IMPRIMIR EL TABLERO EN LA CONSOLA
    public static void mostrarTablero(char[][] matriz, int filas, int columnas) {
        /*
         *MOSTRAR TABLERO POR PANTALLA
         */
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(matriz[i][j] + "");
            }
            System.out.println("");
        }
    }

    //FUNCIÓN PARA GENERAR LOS JUGAODRES INICIALES EN LOS TABLEROS
    public static int[] generarJugadores(int numFilas, int numColumnas,
                                         char[][] matriz, char[][] matriz2, char letra) {
        Random genAleatorio = new Random();           //Generador de núm. aleatorios
        int filaJugador = 0;
        int columnaJugador = 0;

        //Generamos una coordenada aleatoria para colocar al Indio en su tablero
        filaJugador = genAleatorio.nextInt(numFilas);
        columnaJugador = genAleatorio.nextInt(numColumnas);

        matriz[filaJugador][columnaJugador] = letra;
        matriz2[filaJugador][columnaJugador] = letra;

        int[] posicionJugadores = {filaJugador, columnaJugador};

        return posicionJugadores;

    }

    //FUNCIÓN PARA COLOCAR A LOS ENEMIGOS EN LOS TABLEROS
    public static char[][] generarEnemigos(int cantidad, int numFilas,
                                           int numColumnas, char[][] matriz, char letra) {
        Random genAleatorio = new Random();           //Generador de núm. aleatorios
        int filaAleatoria = 0;
        int columnaAleatoria = 0;

        /*
         *GENERAR POSICION ALEATORIA POSICIÓN ALEATORIA VALIDA PARA COLOCAR PIEZAS
         */
        for (int i = 0; i < cantidad; i++) {
            do {
                filaAleatoria = genAleatorio.nextInt(numFilas);  //Genero núm. del 0 al tam. máx de filas
                columnaAleatoria = genAleatorio.nextInt(numColumnas);    //Genero núm. del 0 al tam. máx de columnas
            } while (matriz[filaAleatoria][columnaAleatoria] != 'L');

            //COORDENADA APROPIADA PARA COLOCAR ELEMENTOS
            matriz[filaAleatoria][columnaAleatoria] = letra;
        }

        return matriz;
    }

    //FUNCIÓN PARA RESTAR VIDAS A LOS JUGADORES CUANDO SE ENCUENTRAN UN ENEMIGO
    public static int quitaVida(int vidas) {
        vidas--;
        return vidas;
    }
}
