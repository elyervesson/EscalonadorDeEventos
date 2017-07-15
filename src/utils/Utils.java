package utils;

public class Utils {

    public static void print(String evento , int size01, int size02, int idFregues) {
        System.out.println("\n************************ Evento ************************\n");
        System.out.println("Tipo de evento: " + evento);
        System.out.println("Elementos na Fila 1: " + size01);
        System.out.println("Elementos na Fila 2: " + size02);
        System.out.println("Elemento no serviço: Id "+ idFregues + "\n");
    }
}
