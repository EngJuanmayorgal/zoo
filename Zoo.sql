-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: colnagostore
-- ------------------------------------------------------
-- Server version	8.0.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `compras`
--

DROP TABLE IF EXISTS `compras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compras` (
  `id` int NOT NULL AUTO_INCREMENT,
  `usuarioid` int NOT NULL,
  `idproducto` int NOT NULL,
  `fecha` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `usuarioid` (`usuarioid`),
  KEY `compras_ibfk_2` (`idproducto`),
  CONSTRAINT `compras_ibfk_1` FOREIGN KEY (`usuarioid`) REFERENCES `usuarios` (`iduser`),
  CONSTRAINT `compras_ibfk_2` FOREIGN KEY (`idproducto`) REFERENCES `productos` (`idproductos`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compras`
--

LOCK TABLES `compras` WRITE;
/*!40000 ALTER TABLE `compras` DISABLE KEYS */;
INSERT INTO `compras` VALUES (1,1,2,'2025-07-03 11:40:56'),(2,1,8,'2025-07-03 11:41:28'),(3,1,9,'2025-07-03 11:41:28'),(4,1,3,'2025-07-03 11:43:35'),(5,1,10,'2025-07-03 13:31:16'),(6,1,14,'2025-07-03 13:38:15'),(7,1,8,'2025-07-03 13:45:00'),(8,2,2,'2025-07-03 14:25:48');
/*!40000 ALTER TABLE `compras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `idproductos` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `descripcion` varchar(200) NOT NULL,
  `imagen` varchar(45) NOT NULL,
  `precio` varchar(45) NOT NULL,
  `estado` varchar(45) NOT NULL DEFAULT 'valid',
  PRIMARY KEY (`idproductos`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'Colnago C-68','bicicletas','una bici muy rapidah','images/Colnago C-68.jpg','€ 6.000','valid'),(2,'Colnago V4RS','bicicletas','una bici','images/Conlago V4RS.jpg','€ 12.000','valid'),(3,'Colnago Y1RS','bicicletas','una bici','images/Conlago Y1RS.jpg','€ 5.000','valid'),(4,'Colnago V3','bicicletas','una bici','images/Conlago V3.jpg','€ 3.500','valid'),(5,'Colnago K.One','bicicletas','una bici','images/Conlago K.One.jpg','€ 4.500','valid'),(6,'Colnago C64 Disc','bicicletas','una bici','images/Colnago C64 Disc.jpg','€ 7.500','valid'),(7,'Ciclocomputador','accesorios','C506 SE Ordenador de bicicleta GPS inteligente','images/Ciclocomputador.jpg','€ 100','valid'),(8,'porta herramientas','accesorios','Guarda tus herramientas de forma segura','images/porta herramientas.jpg','€ 50','valid'),(9,'Casco GW Mantis','accesorios','El complemento necesario para tus actividades Este casco te dará comodidad y seguridad para que puedas disfrutar de las actividades que más te gustan sin preocupaciones.','images/Casco GW Mantis.jpg','€ 20','valid'),(10,'Bomba Gw ','accesorios','Funciona para Todos los tipos de Válvulas, Genera hasta 160psi','images/Bomba Gw.jpg','€ 17','valid'),(11,'Buso Camiseta Compresion','vestimenta','Nuestra prenda está diseñada para darte la máxima comodidad posible mientras haces tu deporte favorito','images/Buso Camiseta Compresion.jpg','€ 10','valid'),(12,'Zapatillas De Ciclismo Smart','vestimenta','Compatible con todo tipo de calas.','images/Zapatillas De Ciclismo Smart.jpg','€ 60','valid'),(13,'Guantes Ciclismo Gw','vestimenta','Tejido superior proporciona comodidad y flexibilidad. Pantalla táctil compatible con el dedo índice y el pulgar.','images/Guantes Ciclismo Gw.jpg','€ 22','valid'),(14,'Chaqueta Reflectiva','vestimenta','Chaqueta reflectiva Rhinowalk con cremalleras impermeables','images/Chaqueta Reflectiva.jpg','€ 40','valid');
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

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
