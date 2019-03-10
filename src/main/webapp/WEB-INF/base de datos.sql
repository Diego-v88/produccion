CREATE TABLE producto (
  idProducto integer NOT NULL,
  codBarra varchar(150) NOT NULL,
  nombre varchar(150) NOT NULL,
  peso integer NOT NULL,
  largo integer NOT NULL,
  ancho integer NOT NULL,
  alto integer NOT NULL,
  descripcion varchar(150),
  PRIMARY KEY (idProducto)
);

CREATE TABLE PlanProduccion (
  idPlan integer NOT NULL,
  ordenDeProduccion varchar(150) NOT NULL,
  fechaCreacion Date NOT NULL,
  fechaInicioEstimada Date NOT NULL,
  fechaFinEstimada Date NOT NULL,
  descripcion varchar(150),
  PRIMARY KEY (idPlan)
);

CREATE TABLE Estaciones (
  idEstaciones integer NOT NULL,
  nombre varchar(150) NOT NULL,
  ubicacion varchar(150) NOT NULL,
  descripcion varchar(150),
  fechaAlta Date NOT NULL,
  fechaBaja Date,
  PRIMARY KEY (idEstaciones)
);

CREATE TABLE Material (
  idMaterial integer NOT NULL,
  nombre varchar(150) NOT NULL,
  fechaAlta Date NOT NULL,
  fechaBaja Date,
  unidad varchar(150) NOT NULL,
  cantidadMinima float NOT NULL,
  PRIMARY KEY (idMaterial)
);

CREATE TABLE LoteDeProduccion (
  idLote integer NOT NULL,
  numeroLote integer NOT NULL,
  fk_idProducto INTEGER,
  fk_idPlan INTEGER,
  PRIMARY KEY (idLote),
  FOREIGN KEY (fk_idProducto) REFERENCES Producto,
  FOREIGN KEY (fk_idPlan) REFERENCES PlanProduccion
);

CREATE TABLE CantidadProductos ( --Producto * ---- * PlanProduccion
  idProducto integer NOT NULL,
  idPlan integer NOT NULL,
  cantidad integer NOT NULL,
  PRIMARY KEY (idProducto, idPlan),
  FOREIGN KEY (idPlan) REFERENCES PlanProduccion,
  FOREIGN KEY (idProducto) REFERENCES PRODUCTO
);
--------------------------------

--------------------------------
CREATE TABLE Pedidos (
  idPedido integer NOT NULL,
  fechaAlta Date NOT NULL,
  fechaesperada Date NOT NULL,
  atendido boolean NOT NULL,
  idPlan integer NOT NULL,
  PRIMARY KEY (idPedido),
  FOREIGN KEY (idPlan) REFERENCES PlanProduccion
);


CREATE TABLE CantidadMP ( --Material * ---- * Pedidos
  idPedido integer NOT NULL,
  idMaterial integer NOT NULL,
  cantidad integer NOT NULL,
  PRIMARY KEY (idPedido,idMaterial),
  FOREIGN KEY (idPedido) REFERENCES PEDIDOS,
  FOREIGN KEY (idMaterial) REFERENCES MATERIAL
);

CREATE TABLE Receta (
  idReceta integer NOT NULL,
  fechaAlta Date NOT NULL,
  fechaBaja Date,
  idProducto integer NOT NULL,
  PRIMARY KEY (idReceta),
  FOREIGN KEY (idProducto) REFERENCES Producto
);

CREATE TABLE CantidadMR ( --Material * ---- * Receta
  idReceta integer NOT NULL,
  idMaterial integer NOT NULL,
  cantidad integer NOT NULL,
  PRIMARY KEY (idReceta, idMaterial),
  FOREIGN KEY (idReceta) REFERENCES RECETA,
  FOREIGN KEY (idMaterial) REFERENCES MATERIAL
);
--------------------------------

CREATE TABLE Circuito (
  idCircuito integer NOT NULL,
  fechaAlta Date NOT NULL,
  fechaBaja Date,
  idProducto integer NOT NULL,
  PRIMARY KEY (idCircuito),
  FOREIGN KEY (idProducto) REFERENCES Producto
);

CREATE TABLE Recurso (
  idRecurso integer NOT NULL,
  fechaAlta Date NOT NULL,
  fechaBaja Date,
  cantidadMaterial integer NOT NULL,
  orden integer NOT NULL,
  idCircuito integer NOT NULL,
  idEstaciones integer NOT NULL,
  fk_idMaterial INTEGER NOT NULL,
  PRIMARY KEY (idRecurso),
  FOREIGN KEY (idCircuito) REFERENCES CIRCUITO,
  FOREIGN KEY (fk_idMaterial) REFERENCES Material,
  FOREIGN KEY (idEstaciones) REFERENCES Estaciones
);

--------------------------------
CREATE TABLE Ejecucion (
  idRenglon integer NOT NULL,
  fechaAlta Date NOT NULL,
  fechaInicio Date NOT NULL,
  fechaFin Date NOT NULL,
  idRecurso integer NOT NULL,
  idLote integer NOT NULL,
  PRIMARY KEY (idRenglon),
  FOREIGN KEY (idRecurso) REFERENCES RECURSO,
  FOREIGN KEY (idLote) REFERENCES LoteDeProduccion
);

CREATE TABLE EstadoDeEjecucion (
  idEstado integer NOT NULL,
  fechaAlta Date NOT NULL,
  estado varchar(150) NOT NULL,
  reporte varchar(150) NOT NULL,
  idRenglon integer NOT NULL,
  PRIMARY KEY (idEstado),
  FOREIGN KEY (idRenglon) REFERENCES Ejecucion
);


CREATE TABLE ProductosProducidos (
  idPProducido integer NOT NULL,
  numeroSerie integer NOT NULL,
  desensamble boolean,
  idLote integer NOT NULL,
  PRIMARY KEY (idPProducido),
  FOREIGN KEY (idLote) REFERENCES LoteDeProduccion
);
