// Crear una base de datos llamada "peliculas"
use peliculas

// Crear una colección llamada "peliculas"
db.createCollection("peliculas")

// Insertar documentos (películas) en la colección "peliculas"
db.peliculas.insertMany([
    {
        titulo: "El padrino",
        director: "Francis Ford Coppola",
        año: 1972,
        genero: "Drama",
        duracion_minutos: 175,
        clasificacion: "R",
        sinopsis: "La historia de la familia criminal Corleone durante la década de 1940 en Nueva York.",
        actores: [
            { nombre: "Marlon Brando", personaje: "Don Vito Corleone", edad: 47, nacionalidad: "Estadounidense" },
            { nombre: "Al Pacino", personaje: "Michael Corleone", edad: 31, nacionalidad: "Estadounidense" },
            { nombre: "James Caan", personaje: "Sonny Corleone", edad: 32, nacionalidad: "Estadounidense" }
        ],
        criticas: [
            { critico: "Roger Ebert", puntuacion: 4.5, comentario: "Una obra maestra del cine" },
            { critico: "Peter Travers", puntuacion: 5, comentario: "Una experiencia cinematográfica inolvidable" }
        ]
    },
    {
        titulo: "Forrest Gump",
        director: "Robert Zemeckis",
        año: 1994,
        genero: "Drama",
        duracion_minutos: 142,
        clasificacion: "PG-13",
        sinopsis: "La vida de Forrest Gump, un hombre con un coeficiente intelectual bajo, pero con un corazón de oro.",
        actores: [
            { nombre: "Tom Hanks", personaje: "Forrest Gump", edad: 38, nacionalidad: "Estadounidense" },
            { nombre: "Robin Wright", personaje: "Jenny Curran", edad: 28, nacionalidad: "Estadounidense" },
            { nombre: "Gary Sinise", personaje: "Teniente Dan Taylor", edad: 39, nacionalidad: "Estadounidense" }
        ],
        criticas: [
            { critico: "Gene Siskel", puntuacion: 4, comentario: "Una película emocionante y conmovedora" },
            { critico: "Rotten Tomatoes", puntuacion: 4.8, comentario: "Una obra maestra del cine contemporáneo" }
        ]
    },
     {
        titulo: "Pulp Fiction",
        director: "Quentin Tarantino",
        año: 1994,
        genero: "Crimen",
        duracion_minutos: 154,
        clasificacion: "R",
        sinopsis: "Una serie de historias entrelazadas sobre gánsters, boxeadores, y otros personajes en Los Ángeles.",
        actores: [
            { nombre: "John Travolta", personaje: "Vincent Vega", edad: 40, nacionalidad: "Estadounidense" },
            { nombre: "Uma Thurman", personaje: "Mia Wallace", edad: 24, nacionalidad: "Estadounidense" },
            { nombre: "Samuel L. Jackson", personaje: "Jules Winnfield", edad: 45, nacionalidad: "Estadounidense" }
        ],
        criticas: [
            { critico: "Roger Ebert", puntuacion: 4.5, comentario: "Una obra maestra del cine moderno" },
            { critico: "Empire Magazine", puntuacion: 5, comentario: "Una película revolucionaria que redefine el género" }
        ]
    },
    {
        titulo: "El club de la pelea",
        director: "David Fincher",
        año: 1999,
        genero: "Drama",
        duracion_minutos: 139,
        clasificacion: "R",
        sinopsis: "La historia de un insomne y un vendedor de jabón que forman un club de lucha clandestino.",
        actores: [
            { nombre: "Brad Pitt", personaje: "Tyler Durden", edad: 36, nacionalidad: "Estadounidense" },
            { nombre: "Edward Norton", personaje: "Narrador", edad: 30, nacionalidad: "Estadounidense" },
            { nombre: "Helena Bonham Carter", personaje: "Marla Singer", edad: 33, nacionalidad: "Británica" }
        ],
        criticas: [
            { critico: "The New York Times", puntuacion: 4, comentario: "Una película provocativa y visualmente impactante" },
            { critico: "Los Angeles Times", puntuacion: 4.5, comentario: "Un clásico moderno que desafía las convenciones del cine convencional" }
        ]
    },
    {
        titulo: "La lista de Schindler",
        director: "Steven Spielberg",
        año: 1993,
        genero: "Biografía",
        duracion_minutos: 195,
        clasificacion: "R",
        sinopsis: "La historia de Oskar Schindler, un empresario alemán que salvó a más de mil judíos durante el Holocausto.",
        actores: [
            { nombre: "Liam Neeson", personaje: "Oskar Schindler", edad: 41, nacionalidad: "Irlandesa" },
            { nombre: "Ben Kingsley", personaje: "Itzhak Stern", edad: 49, nacionalidad: "Británica" },
            { nombre: "Ralph Fiennes", personaje: "Amon Göth", edad: 30, nacionalidad: "Británica" }
        ],
        criticas: [
            { critico: "Rotten Tomatoes", puntuacion: 4.9, comentario: "Una obra maestra que captura la tragedia y el heroísmo del Holocausto" },
            { critico: "Variety", puntuacion: 5, comentario: "Una película épica que deja una impresión duradera en el espectador" }
        ]
    },
    {
        titulo: "Kill Bill: Volumen 1",
        director: "Quentin Tarantino",
        año: 2003,
        genero: "Acción",
        duracion_minutos: 111,
        clasificacion: "R",
        sinopsis: "Una mujer busca venganza contra un grupo de asesinos que intentaron matarla.",
        actores: [
            { nombre: "Uma Thurman", personaje: "La Novia", edad: 33, nacionalidad: "Estadounidense" },
            { nombre: "Lucy Liu", personaje: "O-Ren Ishii", edad: 35, nacionalidad: "Estadounidense" },
            { nombre: "Vivica A. Fox", personaje: "Vernita Green", edad: 38, nacionalidad: "Estadounidense" }
        ],
        criticas: [
            { critico: "Roger Ebert", puntuacion: 4, comentario: "Una película llena de acción y estilo, pero también de profundidad emocional" },
            { critico: "The Guardian", puntuacion: 4.5, comentario: "Una oda al cine de género con una heroína inolvidable" }
        ]
    },
    {
        titulo: "Kill Bill: Volumen 2",
        director: "Quentin Tarantino",
        año: 2004,
        genero: "Acción",
        duracion_minutos: 137,
        clasificacion: "R",
        sinopsis: "La Novia continúa su búsqueda de venganza, enfrentándose a nuevos enemigos y revelando su pasado.",
        actores: [
            { nombre: "Uma Thurman", personaje: "La Novia", edad: 34, nacionalidad: "Estadounidense" },
            { nombre: "David Carradine", personaje: "Bill", edad: 68, nacionalidad: "Estadounidense" },
            { nombre: "Michael Madsen", personaje: "Budd", edad: 46, nacionalidad: "Estadounidense" }
        ],
        criticas: [
            { critico: "Variety", puntuacion: 4.8, comentario: "Una conclusión satisfactoria para una saga épica de venganza y redención" },
            { critico: "Empire Magazine", puntuacion: 5, comentario: "Tarantino en su mejor momento, combinando acción, humor y drama de manera magistral" }
        ]
    },
    {
        titulo: "Inception",
        director: "Christopher Nolan",
        año: 2010,
        genero: "Ciencia ficción",
        duracion_minutos: 148,
        clasificacion: "PG-13",
        sinopsis: "Un ladrón experto roba secretos del subconsciente durante el sueño, pero ahora tiene una misión diferente.",
        actores: [
            { nombre: "Leonardo DiCaprio", personaje: "Dom Cobb", edad: 35, nacionalidad: "Estadounidense" },
            { nombre: "Joseph Gordon-Levitt", personaje: "Arthur", edad: 29, nacionalidad: "Estadounidense" },
            { nombre: "Ellen Page", personaje: "Ariadne", edad: 23, nacionalidad: "Canadiense" }
        ],
        criticas: [
            { critico: "Rotten Tomatoes", puntuacion: 4.9, comentario: "Un viaje alucinante por el mundo de los sueños, repleto de acción y profundidad filosófica" },
            { critico: "The Telegraph", puntuacion: 4.5, comentario: "Una obra maestra moderna que desafía las convenciones del género" }
        ]
    },
    {
        titulo: "Reservoir Dogs",
        director: "Quentin Tarantino",
        año: 1992,
        genero: "Crimen",
        duracion_minutos: 99,
        clasificacion: "R",
        sinopsis: "Un grupo de criminales se reúne para llevar a cabo un robo, pero las cosas salen mal.",
        actores: [
            { nombre: "Harvey Keitel", personaje: "Sr. Blanco", edad: 53, nacionalidad: "Estadounidense" },
            { nombre: "Tim Roth", personaje: "Mr. Orange", edad: 31, nacionalidad: "Británico" },
            { nombre: "Michael Madsen", personaje: "Mr. Blonde", edad: 34, nacionalidad: "Estadounidense" }
        ],
        criticas: [
            { critico: "Roger Ebert", puntuacion: 4.5, comentario: "Un debut impresionante de Tarantino, lleno de diálogos ingeniosos y acción intensa" },
            { critico: "Empire Magazine", puntuacion: 5, comentario: "Una película cruda y emocionante que establece a Tarantino como un talento a tener en cuenta" }
        ]
    },
    {
        titulo: "Jackie Brown",
        director: "Quentin Tarantino",
        año: 1997,
        genero: "Crimen",
        duracion_minutos: 154,
        clasificacion: "R",
        sinopsis: "Una azafata se ve envuelta en un plan de contrabando de dinero con un traficante de armas y la policía.",
        actores: [
            { nombre: "Pam Grier", personaje: "Jackie Brown", edad: 48, nacionalidad: "Estadounidense" },
            { nombre: "Samuel L. Jackson", personaje: "Ordell Robbie", edad: 48, nacionalidad: "Estadounidense" },
            { nombre: "Robert Forster", personaje: "Max Cherry", edad: 56, nacionalidad: "Estadounidense" }
        ],
        criticas: [
            { critico: "The Guardian", puntuacion: 4, comentario: "Un thriller inteligente y emocionante que muestra la versatilidad de Tarantino como director" },
            { critico: "Los Angeles Times", puntuacion: 4.5, comentario: "Una película llena de estilo y sustancia, con actuaciones destacadas y una trama envolvente" }
        ]
    },
    {
        titulo: "The Dark Knight",
        director: "Christopher Nolan",
        año: 2008,
        genero: "Acción",
        duracion_minutos: 152,
        clasificacion: "PG-13",
        sinopsis: "Batman se enfrenta al Joker, un criminal psicótico que siembra el caos en Gotham City.",
        actores: [
            { nombre: "Christian Bale", personaje: "Bruce Wayne / Batman", edad: 34, nacionalidad: "Británico" },
            { nombre: "Heath Ledger", personaje: "Joker", edad: 29, nacionalidad: "Australiano" },
            { nombre: "Aaron Eckhart", personaje: "Harvey Dent / Dos Caras", edad: 40, nacionalidad: "Estadounidense" }
        ],
        criticas: [
            { critico: "Rotten Tomatoes", puntuacion: 4.9, comentario: "Una de las mejores películas de superhéroes jamás realizadas, con una actuación legendaria de Heath Ledger como el Joker" },
            { critico: "Variety", puntuacion: 5, comentario: "Un emocionante thriller que eleva el género de superhéroes a nuevas alturas" }
        ]
    },
    {
        titulo: "Memento",
        director: "Christopher Nolan",
        año: 2000,
        genero: "Misterio",
        duracion_minutos: 113,
        clasificacion: "R",
        sinopsis: "Un hombre sufre de amnesia anterógrada y utiliza tatuajes y notas para rastrear al asesino de su esposa.",
        actores: [
            { nombre: "Guy Pearce", personaje: "Leonard Shelby", edad: 33, nacionalidad: "Británico" },
            { nombre: "Carrie-Anne Moss", personaje: "Natalie", edad: 32, nacionalidad: "Canadiense" },
            { nombre: "Joe Pantoliano", personaje: "Teddy", edad: 49, nacionalidad: "Estadounidense" }
        ],
        criticas: [
            { critico: "Roger Ebert", puntuacion: 4.5, comentario: "Una experiencia cinematográfica única que desafía las convenciones narrativas" },
            { critico: "The New York Times", puntuacion: 4, comentario: "Un thriller psicológico ingeniosamente construido que te dejará pensando durante días" }
        ]
    },
    {
        titulo: "Interstellar",
        director: "Christopher Nolan",
        año: 2014,
        genero: "Ciencia ficción",
        duracion_minutos: 169,
        clasificacion: "PG-13",
        sinopsis: "Un grupo de astronautas se embarca en un viaje interestelar en busca de un nuevo hogar para la humanidad.",
        actores: [
            { nombre: "Matthew McConaughey", personaje: "Cooper", edad: 45, nacionalidad: "Estadounidense" },
            { nombre: "Anne Hathaway", personaje: "Brand", edad: 32, nacionalidad: "Estadounidense" },
            { nombre: "Jessica Chastain", personaje: "Murph (adulta)", edad: 38, nacionalidad: "Estadounidense" }
        ],
        criticas: [
            { critico: "Rotten Tomatoes", puntuacion: 4.8, comentario: "Una epopeya espacial emocionalmente impactante que te hace reflexionar sobre el tiempo, el amor y el destino" },
            { critico: "Empire Magazine", puntuacion: 5, comentario: "Una obra maestra del cine de ciencia ficción que cautiva y emociona de principio a fin" }
        ]
    },
    {
        titulo: "The Godfather: Part II",
        director: "Francis Ford Coppola",
        año: 1974,
        genero: "Drama",
        duracion_minutos: 202,
        clasificacion: "R",
        sinopsis: "La continuación de la historia de la familia Corleone, con flashbacks de la juventud de Vito Corleone en Sicilia.",
        actores: [
            { nombre: "Al Pacino", personaje: "Michael Corleone", edad: 34, nacionalidad: "Estadounidense" },
            { nombre: "Robert De Niro", personaje: "Vito Corleone (joven)", edad: 30, nacionalidad: "Estadounidense" },
            { nombre: "Robert Duvall", personaje: "Tom Hagen", edad: 43, nacionalidad: "Estadounidense" }
        ],
        criticas: [
            { critico: "Roger Ebert", puntuacion: 4.9, comentario: "Una obra maestra indiscutible, igualmente convincente y épica que su predecesora" },
            { critico: "Variety", puntuacion: 5, comentario: "Una secuela magistral que profundiza en los temas de poder, familia y redención" }
        ]
    }
])
