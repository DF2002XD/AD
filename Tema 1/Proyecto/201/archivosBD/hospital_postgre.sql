CREATE DATABASE hospital_postgre;

CREATE SCHEMA hospital;

CREATE TABLE hospital.salas(
    id_sala SERIAL PRIMARY KEY,
    nombre_sala VARCHAR(100) NOT NULL,
    ubicacion VARCHAR(100) NOT NULL
);

CREATE TABLE hospital.especialidades(
    id_especialidad SERIAL PRIMARY KEY,
    nombre_especialidad VARCHAR(100) NOT NULL
);

CREATE TYPE hospital.contacto_medico AS(
    nif CHAR(9),
    nombre VARCHAR(100),
    telefono VARCHAR(15),
    email VARCHAR(100) 
);

CREATE TABLE hospital.medicos(
    id_medico SERIAL PRIMARY KEY,
    nombre_medico VARCHAR(100) NOT NULL,
    contacto hospital.contacto_medico NOT NULL
);

CREATE TABLE hospital.tratamientos(
    id_tratamiento SERIAL PRIMARY KEY,
    id_medico INT NOT NULL REFERENCES medicos(id_medico),
    id_especialidad INT NOT NULL REFERENCES especialidades(id_especialidad)
);

CREATE TABLE hospital.salas_tratamiento(
    id_sala INT NOT NULL REFERENCES salas(id_sala),
    id_tratamiento INT NOT NULL REFERENCES tratamientos(id_tratamiento),
    PRIMARY KEY (id_sala, id_tratamiento) 
);


INSERT INTO hospital.medicos (nombre_medico, contacto) VALUES
('Dr. Luis Martinez', ROW('12345678A', 'Luis Martinez', '600123456', 'luis.martinez@gmail.com')),
('Dra. Ana Torres', ROW('87654321B', 'Ana Torres', '600654321', 'ana.torres@gmail.com')),
('Dr. Pedro Ruiz', ROW('11223344C', 'Pedro Ruiz', '600112233', 'pedro.ruiz@gmail.com'));

INSERT INTO hospital.especialidades (nombre_especialidad) VALUES
('Cardiología'),
('Neurología'),
('Pediatría');

INSERT INTO hospital.salas (nombre_sala, ubicacion) VALUES
('Sala A', 'Planta 1'),
('Sala B', 'Planta 2'),
('Sala C', 'Planta 3');

INSERT INTO hospital.tratamientos (id_medico, id_especialidad) VALUES
(1, 1),
(2, 2),
(3, 3);

INSERT INTO hospital.salas_tratamiento (id_sala, id_tratamiento) VALUES
(1, 1),
(2, 2),
(3, 3);
