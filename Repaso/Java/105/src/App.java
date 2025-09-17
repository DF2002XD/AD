import java.util.Scanner;

public class App {
    private static Scanner sc = new Scanner(System.in);
    private static Comunidades comu = new Comunidades();

    public static void main(String[] args) throws Exception {
        int contador = 0;
        while (true) {

            if (contador == 2) {
                break;

            } else {
                contador++;
                int longitud = enteroCorrecto("Introduzca la longitud de la piscina (mínimo 1 metro)", 1, 100);
                int anchura = enteroCorrecto("Introduzca la anchura de la piscina (mínimo 1 metro)", 1, 100);
                Piscina pisc = new Piscina(longitud, anchura);
                comu.addPiscina(pisc);
            }
        }
        comu.mostrarAforoMenorpiscinas();
    }

    public static int enteroCorrecto(String dato, int minInclusive, int maxInclusive) {
        int num = 0;
        boolean datoOk = false;

        while (!datoOk) {
            System.out.println(dato);
            String input = sc.nextLine();
            try {
                num = Integer.parseInt(input);
                if (num >= minInclusive && num <= maxInclusive) {
                    datoOk = true;
                } else {
                    System.out.println("El valor debe estar entre " + minInclusive + " y " + maxInclusive);
                }
            } catch (NumberFormatException exception) {
                System.out.println("Debe introducir un número válido.");
            }
        }
        return num;
    }
}
