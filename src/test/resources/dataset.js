db = connect( 'mongodb://root:example@localhost:27018/UniLocal?authSource=admin' );
db.clientes.insertMany([
    {
        _id: 'Cliente1',
        nickname: 'juanito',
        ciudad: 'Armenia',
        fotoPerfil: 'mi foto',
        email: 'juan@email.com',
        password: 'mipassword',
        nombre: 'Juan',
        estado: 'ACTIVO',
        _class: 'co.edu.uniquindio.UniLocal.modelo.documentos.Cliente'
    },
    {
        _id: 'Cliente2',
        nickname: 'maria',
        ciudad: 'Armenia',
        fotoPerfil: 'mi foto',
        email: 'maria@email.com',
        password: 'mipassword',
        nombre: 'Maria',
        estado: 'ACTIVO',
        _class: 'co.edu.uniquindio.UniLocal.modelo.documentos.Cliente'
    },
    {
        _id: 'Cliente3',
        nickname: 'pedrito',
        ciudad: 'Armenia',
        fotoPerfil: 'mi foto',
        email: 'pedro@email.com',
        password: 'mipassword',
        nombre: 'Pedro',
        estado: 'ACTIVO',
        _class: 'co.edu.uniquindio.UniLocal.modelo.documentos.Cliente'
    },
    {   _id: 'Cliente4',
        nickname: 'lulu',
        ciudad: 'Armenia',
        fotoPerfil: 'mi foto',
        email: 'luisa@email.com',
        password: 'mipassword',
        nombre: 'Luisa',
        estado: 'ACTIVO',
        _class: 'co.edu.uniquindio.UniLocal.modelo.documentos.Cliente'

    },
    {
        _id: 'Cliente5',
        nickname: 'majo',
        ciudad: 'Armenia',
        fotoPerfil: 'mi foto',
        email: 'pedro@email.com',
        password: 'mipassword',
        nombre: 'Maria Jose',
        estado: 'ACTIVO',
        _class: 'co.edu.uniquindio.UniLocal.modelo.documentos.Cliente'
    }
]);
db.negocios.insertMany([
    {
        _id: 'Negocio1',
        nombre: 'Restaurante Mexicano',
        descripcion: 'Restaurante de comida mexicana en Armenia',
        codigoCliente: 'Cliente1',
        ubicacion: {
            latitud: 5.540130,
            longitud: -65.665660
        },
        imagenes: ['imagen1', 'imagen2'],
        tipoNegocio: 'RESTAURANTE',
        horarios: [
            {
                dia: 'LUNES',
                horaInicio: '08:00',
                horaFin: '20:00'
            }
        ],
        historialRevicion: [
            {
                fecha: '2024/04/15',
                descripcion : 'Bla bla bla bla',
                estado : 'RECHAZADO',
                codigoModerador: '1'
            }
        ],

        telefonos: ['1234567', '7654321'],
        estado: 'ACTIVO',
        _class: 'co.edu.uniquindio.UniLocal.modelo.documentos.Negocio'
    },
    {    _id: 'Negocio2',
        nombre: 'Viva la pizza',
        descripcion: 'Restaurante de comida italiana en  Armenia',
        codigoCliente: 'Cliente2',
        ubicacion: {
            latitud: 45.321,
            longitud: +12.7894
        },
        imagenes: ['imagen3', 'imagen4'],
        tipoNegocio: 'RESTAURANTE',
        horarios: [
            {
                dia: 'LUNES',
                horaInicio: '18:00',
                horaFin: '23:00'
            }
        ],
        telefonos: ['3231578565', '7321345'],
        estado: 'ACTIVO',
        _class: 'co.edu.uniquindio.UniLocal.modelo.documentos.Negocio'

    },
    {
        _id: 'Negocio3',
        nombre: 'Museo Civil',
        descripcion: 'Museo de la historia civil Armenia ',
        codigoCliente: 'Cliente3',
        ubicacion: {
            latitud: 5.540130,
            longitud: -75.665660
        },
        imagenes: ['imagen1', 'imagen2'],
        tipoNegocio: 'MUSEO',
        horarios: [
            {
                dia: 'LUNES',
                horaInicio: '08:00',
                horaFin: '16:00'
            }
        ],
        telefonos: ['310493', '201799'],
        estado: 'ACTIVO',
        _class: 'co.edu.uniquindio.UniLocal.modelo.documentos.Negocio'
    },
    {
        _id: 'Negocio4',
        nombre: 'Cafeteria Sebas',
        descripcion: 'Cafeteria del norte de armenia',
        codigoCliente: 'Cliente1',
        ubicacion: {
            latitud: 3.1234,
            longitud: 5.7654
        },
        imagenes: ['imagen1', 'imagen2'],
        tipoNegocio: 'CAFETERIA',
        horarios: [
            {
                dia: 'LUNES',
                horaInicio: '14:00',
                horaFin: '22:00'
            }
        ],
        telefonos: ['3456789', '7462525'],
        estado: 'ACTIVO',
        _class: 'co.edu.uniquindio.UniLocal.modelo.documentos.Negocio'
    },
    {
        _id: 'Negocio5',
        nombre: 'Hotel el eclipce',
        descripcion: 'Agradable hotel ubicado a la salidad de armenia hacia montenegro ',
        codigoCliente: 'Cliente1',
        ubicacion: {
            latitud: 4.4444,
            longitud: -22.6789
        },
        imagenes: ['imagen1', 'imagen2'],
        tipoNegocio: 'Hotel',
        horarios: [
            {
                dia: 'LUNES',
                horaInicio: '07:00',
                horaFin: '07:00'
            }
        ],
        telefonos: ['321564789', '7654451'],
        estado: 'ACTIVO',
        _class: 'co.edu.uniquindio.UniLocal.modelo.documentos.Negocio'
    }
]);
db.comentarios.insertMany([
    {
        mensaje: "Excelente sitio, muy buena atención",
        fecha: new Date(),
        codigoCliente: 'Cliente1',
        codigoNegocio: 'Negocio1',
        calificacion: 4,
        _class: 'co.edu.uniquindio.UniLocal.modelo.documentos.Comentario'
    },
    {
        mensaje: "el servicio es un poco malo se demoran un poco en atender, pero la com ida es exelente  ",
        fecha: new Date(),
        codigoCliente: 'Cliente2',
        codigoNegocio: 'Negocio2',
        calificacion: 3.5,
        _class: 'co.edu.uniquindio.UniLocal.modelo.documentos.Comentario'
    },
    {
        mensaje: "Muy bonito lugar super recomendado",
        fecha: new Date(),
        codigoCliente: 'Cliente3',
        codigoNegocio: 'Negocio3',
        calificacion: 5,
        _class: 'co.edu.uniquindio.UniLocal.modelo.documentos.Comentario'
    },
    {
        mensaje: "Pesima atencion y los cefes super malucos",
        fecha: new Date(),
        codigoCliente: 'Cliente4',
        codigoNegocio: 'Negocio4',
        calificacion: 2,
        _class: 'co.edu.uniquindio.UniLocal.modelo.documentos.Comentario'
    },
    {
        mensaje: "Muy mala atencion y la habitaciones super sucias y el precio muy caro",
        fecha: new Date(),
        codigoCliente: 'Cliente5',
        codigoNegocio: 'Negocio5',
        calificacion: 1,
        _class: 'co.edu.uniquindio.UniLocal.modelo.documentos.Comentario'
    }
]);