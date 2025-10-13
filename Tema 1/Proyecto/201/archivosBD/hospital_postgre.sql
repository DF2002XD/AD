CREATE DATABASE IF NOT EXISTS hospital_postgre;

CREATE TABLE IF NOT EXISTS salas(
    id_sala SERIAL PRIMARY KEY,
    nombre_sala VARCHAR(100) NOT NULL,
    ubicacion VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS salas_tratamiiento(
);