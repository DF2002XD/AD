package com.example.Repositorios;

public interface Repositorio<T> {
    void guardar(T t);
    void eliminar(int id);

}
