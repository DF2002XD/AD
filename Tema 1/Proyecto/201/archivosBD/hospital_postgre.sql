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
('Dr. Pedro Ruiz', ROW('11223344C', 'Pedro Ruiz', '600112233', 'pedro.ruiz@gmail.com')),
('Dr. Luis Martinez', ROW('12345678A', 'Luis Martinez', '600123456', 'luis.martinez@gmail.com')),
('Dra. Ana Torres', ROW('87654321B', 'Ana Torres', '600654321', 'ana.torres@gmail.com')),
('Dr. Pedro Ruiz', ROW('11223344C', 'Pedro Ruiz', '600112233', 'pedro.ruiz@gmail.com')),
('Dra. Sofia Gómez', ROW('33445566D', 'Sofia Gómez', '600334455', 'sofia.gomez@gmail.com')),
('Dr. Carlos López', ROW('44556677E', 'Carlos López', '600445566', 'carlos.lopez@gmail.com')),
('Dr. Manuel Díaz', ROW('99887766F', 'Manuel Díaz', '600998877', 'manuel.diaz@gmail.com')),
('Dra. Marta Gomez', ROW('44332211D', 'Marta Gomez', '600443322', 'marta.gomez@gmail.com')),
('Dr. Carlos Fernandez', ROW('55667788E', 'Carlos Fernandez', '600556677', 'carlos.fernandez@gmail.com'));

INSERT INTO hospital.especialidades (nombre_especialidad) VALUES
('Cardiología'),
('Neurología'),
('Pediatría'),
('Dermatología'),
('Oncología'),
('Ginecología'),
('Psiquiatría'),
('Traumatología'),
('Oftalmología'),
('Endocrinología'),
('Drermatología');

INSERT INTO hospital.salas (nombre_sala, ubicacion) VALUES
('Sala A', 'Planta 1'),
('Sala B', 'Planta 2'),
('Sala C', 'Planta 3'),
('Sala D', 'Planta 4'),
('Sala E', 'Planta 5'),
('Sala F', 'Planta 6');

INSERT INTO hospital.tratamientos (id_medico, id_especialidad) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4), 
(5, 5), 
(1, 2), 
(2, 1), 
(6, 4),
(7, 5),
(8, 6),
(9, 7);

INSERT INTO hospital.salas_tratamiento (id_sala, id_tratamiento) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(2, 6),
(1, 7),
(4, 8),
(5, 9),
(6, 10),
(3, 11);
