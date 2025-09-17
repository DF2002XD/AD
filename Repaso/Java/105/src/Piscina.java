public class Piscina {
private int longitud;
private int anchura;

public Piscina(int longitud, int anchura) {
    this.longitud = longitud;
    this.anchura = anchura;

}

public int getLongitud() {
    return longitud;
}

public void setLongitud(int longitud) {
    this.longitud = longitud;
}

public int getAnchura() {
    return anchura;
}

public void setAnchura(int anchura) {
    this.anchura = anchura;
}
public int calcularArea() {
    return longitud * anchura;
}



}