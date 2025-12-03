CREATE TABLE animales (
    id_animal INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    especie VARCHAR(100) NOT NULL,
    zona VARCHAR(50) NOT NULL, -- 'africa', 'amazonas', 'asia', 'artico'
    dieta VARCHAR(50), -- 'carnivoro', 'herbivoro', 'omnivoro'
    descripcion TEXT,
    imagen_url VARCHAR(255)
);

-- Insertar datos de ejemplo (África)
INSERT INTO animales (nombre, especie, zona, dieta, descripcion, imagen_url) VALUES
('León', 'Panthera leo', 'africa', 'carnivoro', 'El rey de la sabana, vive en manadas', 'images/animales/leon.jpg'),
('Elefante', 'Loxodonta africana', 'africa', 'herbivoro', 'El animal terrestre más grande', 'images/animales/elefante.jpg'),
('Jirafa', 'Giraffa camelopardalis', 'africa', 'herbivoro', 'El animal más alto del mundo', 'images/animales/jirafa.jpg'),
('Cebra', 'Equus quagga', 'africa', 'herbivoro', 'Reconocida por sus rayas únicas', 'images/animales/cebra.jpg'),
('Rinoceronte', 'Diceros bicornis', 'africa', 'herbivoro', 'Animal en peligro de extinción', 'images/animales/rinoceronte.jpg'),
('Guepardo', 'Acinonyx jubatus', 'africa', 'carnivoro', 'El animal terrestre más rápido', 'images/animales/guepardo.jpg');

-- Amazonas
INSERT INTO animales (nombre, especie, zona, dieta, descripcion, imagen_url) VALUES
('Jaguar', 'Panthera onca', 'amazonas', 'carnivoro', 'El felino más grande de América', 'images/animales/jaguar.jpg'),
('Anaconda', 'Eunectes murinus', 'amazonas', 'carnivoro', 'Una de las serpientes más grandes', 'images/animales/anaconda.jpg'),
('Tucán', 'Ramphastos toco', 'amazonas', 'omnivoro', 'Ave con pico grande y colorido', 'images/animales/tucan.jpg'),
('Perezoso', 'Bradypus variegatus', 'amazonas', 'herbivoro', 'Conocido por su lentitud', 'images/animales/perezoso.jpg'),
('Capibara', 'Hydrochoerus hydrochaeris', 'amazonas', 'herbivoro', 'El roedor más grande del mundo', 'images/animales/capibara.jpg'),
('Mono Araña', 'Ateles geoffroyi', 'amazonas', 'omnivoro', 'Primate muy ágil', 'images/animales/mono.jpg');

-- Asia
INSERT INTO animales (nombre, especie, zona, dieta, descripcion, imagen_url) VALUES
('Tigre', 'Panthera tigris', 'asia', 'carnivoro', 'El felino más grande del mundo', 'images/animales/tigre.jpg'),
('Panda', 'Ailuropoda melanoleuca', 'asia', 'herbivoro', 'Se alimenta principalmente de bambú', 'images/animales/panda.jpg'),
('Orangután', 'Pongo pygmaeus', 'asia', 'omnivoro', 'Gran simio de Borneo y Sumatra', 'images/animales/orangutan.jpg'),
('Elefante Asiático', 'Elephas maximus', 'asia', 'herbivoro', 'Más pequeño que el africano', 'images/animales/elefante_asiatico.jpg'),
('Oso Pardo', 'Ursus arctos', 'asia', 'omnivoro', 'Hiberna en invierno', 'images/animales/oso_pardo.jpg'),
('Leopardo de las Nieves', 'Panthera uncia', 'asia', 'carnivoro', 'Vive en las montañas', 'images/animales/leopardo_nieve.jpg');

-- Ártico
INSERT INTO animales (nombre, especie, zona, dieta, descripcion, imagen_url) VALUES
('Oso Polar', 'Ursus maritimus', 'artico', 'carnivoro', 'Excelente nadador, vive en el hielo', 'images/animales/oso_polar.jpg'),
('Pingüino Emperador', 'Aptenodytes forsteri', 'artico', 'carnivoro', 'El pingüino más grande', 'images/animales/pinguino.jpg'),
('Foca', 'Phoca vitulina', 'artico', 'carnivoro', 'Mamífero marino adaptado al frío', 'images/animales/foca.jpg'),
('Zorro Ártico', 'Vulpes lagopus', 'artico', 'carnivoro', 'Pelaje blanco en invierno', 'images/animales/zorro_artico.jpg'),
('Morsa', 'Odobenus rosmarus', 'artico', 'carnivoro', 'Reconocible por sus colmillos', 'images/animales/morsa.jpg'),
('Reno', 'Rangifer tarandus', 'artico', 'herbivoro', 'Animal migratorio del ártico', 'images/animales/reno.jpg');

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `iduser` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `pass` varchar(45) NOT NULL,
  `id` int DEFAULT NULL,
  `cod` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'juan','juan@gmail.com','123',123,'juan125','user'),(2,'david','david@gmail.com','123',123,'david123','user'),(6,'felipe','felipe@gmail.com','123',321,'felipe123','user'),(8,'juan1','juan@gmail.com','123',123,'juan1123','admin');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-05 13:10:37
