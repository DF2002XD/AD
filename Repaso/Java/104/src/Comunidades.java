import java.util.ArrayList;
import java.util.List;

public class Comunidades {
    private static List<Piscina> piscina = new ArrayList<>();

    public void addPiscina(Piscina piscina2) {
        if (piscina2 != null) {
            piscina.add(piscina2);
            System.out.println("Piscina añadida correctamente: " + piscina2.getLongitud() + "x" + piscina2.getAnchura());
        } else {
            System.out.println("No se pudo añadir la piscina. El objeto es nulo.");
        }

    }

    public void mostrarAforoMenorpiscinas() {
        if (piscina.isEmpty()) {
            System.out.println("No hay piscinas registradas.");
            return;
        }

        Piscina piscinaMenor = piscina.get(0);
        for (Piscina p : piscina) {
            if (p.calcularArea() < piscinaMenor.calcularArea()) {
                piscinaMenor = p;
            }
        }

        int aforo = piscinaMenor.calcularArea() / 2;
        System.out.println("La piscina con menor área tiene un aforo de: " + aforo + " personas.");
    }
        
}
