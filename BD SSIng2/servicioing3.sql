-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-10-2015 a las 14:31:29
-- Versión del servidor: 5.5.36
-- Versión de PHP: 5.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `servicioing3`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `colegio`
--

CREATE TABLE IF NOT EXISTS `colegio` (
  `IdColegio` int(11) NOT NULL,
  `Nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`IdColegio`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `colegioprofesor`
--

CREATE TABLE IF NOT EXISTS `colegioprofesor` (
  `Profesor_IdProfesor` int(11) NOT NULL,
  `Colegio_IdColegio` int(11) NOT NULL,
  PRIMARY KEY (`Profesor_IdProfesor`,`Colegio_IdColegio`),
  KEY `fk_ColegioProfesor_Colegio1_idx` (`Colegio_IdColegio`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `material`
--

CREATE TABLE IF NOT EXISTS `material` (
  `IdMaterial` int(11) NOT NULL,
  `Nombre` varchar(45) NOT NULL,
  `Descripcion` varchar(45) DEFAULT NULL,
  `Band` tinyint(1) NOT NULL,
  PRIMARY KEY (`IdMaterial`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `material`
--

INSERT INTO `material` (`IdMaterial`, `Nombre`, `Descripcion`, `Band`) VALUES
(1, 'Laptop 1', NULL, 0),
(2, 'Laptop2', NULL, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prestamo`
--

CREATE TABLE IF NOT EXISTS `prestamo` (
  `IdPrestamo` int(11) NOT NULL AUTO_INCREMENT,
  `Profesor_IdProfesor` int(11) NOT NULL,
  `Material_IdMaterial` int(11) NOT NULL,
  `Usuario_IdPrestador` int(11) NOT NULL,
  `Fecha_Entrega` varchar(10) NOT NULL,
  `Hora_Entrega` varchar(10) NOT NULL,
  `Fecha_Recibo` varchar(10) DEFAULT NULL,
  `Hora_Recibo` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`IdPrestamo`),
  UNIQUE KEY `Material_IdMaterial` (`Material_IdMaterial`),
  KEY `IdMaterial_idx` (`Material_IdMaterial`),
  KEY `IdPrestador_idx` (`Usuario_IdPrestador`),
  KEY `IdProfesor` (`Profesor_IdProfesor`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Volcado de datos para la tabla `prestamo`
--

INSERT INTO `prestamo` (`IdPrestamo`, `Profesor_IdProfesor`, `Material_IdMaterial`, `Usuario_IdPrestador`, `Fecha_Entrega`, `Hora_Entrega`, `Fecha_Recibo`, `Hora_Recibo`) VALUES
(1, 1, 1, 1, '28/10/2015', '12:52:53', '28/10/2015', '12:52:53'),
(2, 1, 2, 1, '28/10/2015', '12:52:57', '28/10/2015', '12:52:57');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profesor`
--

CREATE TABLE IF NOT EXISTS `profesor` (
  `IdProfesor` int(11) NOT NULL AUTO_INCREMENT,
  `HuellaProfesor` longblob NOT NULL,
  `Nombre` varchar(45) NOT NULL,
  `Apellidos` varchar(45) NOT NULL,
  `Celular` int(11) DEFAULT NULL,
  PRIMARY KEY (`IdProfesor`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `profesor`
--

INSERT INTO `profesor` (`IdProfesor`, `HuellaProfesor`, `Nombre`, `Apellidos`, `Celular`) VALUES
(1, 0x00f87501c82ae3735cb5492ed93f8c65ee97eb8247a2f0434babc777b5ad5e38f7632188a41d26dc6dbec49b2862a888d8f1d8c810ac4b88b54d948992f9dd4a586bfd6e1981fc1166f8afb609b5f92bb734197b0e14621c29d7cf52aded67325b07eeec7826970a36f0778d977283239ac02810f6411e470c73e37b68efd899ad3714810a6c1cb76a7e13a9a8a9149fe6b90edf4d972d42b3b6e6eb0cedc4c3a6821bfe67fa34eccd3c074372af62fdca6f942d0c11dcdae1e7e1d488d93890bef277d282e9335b9cbaec073eb83c5b1960a73101124be5b41a94c1a68698146e8847dcfe4db3b7c58846ebae76c7d66ee8a2fb975b21f2537c523b71f7d5219f34c1a47cf30fe8403011047b405efa4a961474c6f3b90834ca15f60bfdb19f477974b6589b5f89b46c4c0e3d57054bf6f8e82d7d9bc38c6f5c78894075c3521ff755873a8845c40d917b6180f4882c2c0a5564a18c7eb50e6d5c52e1a9ec97e4022149e3bcabfd9f1664b6daef9abaa616f36b3c650b98be6f00f87b01c82ae3735cb6492ff70ac2a73074ad7a9a5471970192ea55521f8aaeba09e924151500bd4a2dbe186c4f474a6ad18ac5914c8528947efdf91e19d0bbdd96b3ae4cd9c50fbd73328a3cc519b1dbf0388d5dd9e652e3feaf4fa8e35296c0bb5f05da889f5cccb8a75e0396ceadc0f0c4fe714161d59633ec9187aa752b519091bbc0c47461b07cd25ca85d6ca03fa2b61fca450563f4751b52203e34f1afc3647c681ae3f81b8d66232f502fb0564cda68efa8f88d3bb17febd88a7b7c95746d129de089248e849428af75828eb2a8ed0824017377dd21f6699bec10c971163360540911be19525345a968c632ba42cc442f603dae3081f1f6f05179b7fca59cb664836ae257dc4207a1ff9007935985d0293651bccc3f8bb48ae29f8d4c925f4bbdf454468348f9f1cf93df5eb934d20d22a4850171c4ea0c132b1f7b45b2410aa481b493e1130ae269a1168cdddebc965448fe72878c23ae57fcd096513841613bb816ddcb12f9941b57dbe14ac6976741d1c70f87c6862390804b6f00f87401c82ae3735cbb4b20bd4a28f1c77743d36861aec5bfbbef3b514df77221f543e0697c346da720126dfd52a831411ec41cbc8db45390e22dee74403360670b4a1bcdfd187b30239deb32b1d32b24828d8b3dbffa80bf1b39a8a28c91f750470f7cf45820969d861354ef05cfdbab5ce09848a29e84256a425ed6117315a10fa53ca124af1214b867f3bb76c49efff5224fbdf16d05a16350bc880245971309ba293429b59075e418fe4328e29d7f54cba7f213cb87863bbacd55c094699f22275c26a5620629692de2371ace1e1cf0620174c3c370b0e40cb9fd39f3d78bce6d880175e88be18ff10eec368aab14c0591ddae4eb42afe0c8823443c0703e6b7651904c9ce98827ce1db611d1a7e10e28b5aa1b716eed0aa3f695d139d77e665d52c6a61267382eeb840aa7daf33357a000875ce071f3cb0e05f1e34cfe60783fed6d38cc02973b52cf3a76dd11adad5dd185b3e34ad5cc90d02361a86d8f1b521ac1b497ced1a4d9ff74ddb7f9908c3bf76a1c4d396f00e87201c82ae3735cbe4a238917624b90f502c83c12a76cbede08c75665bb98bbb2159e886a9638ca2df9146ba34836bdf26c752728d83dac20b0dcabf8e277af149cd440f90de878bd6bece09e894217582ff38ae4d732750a5074ecbb67f85ebfa911041a4f559fab8d46086c648b383fc7e45ed2c81b0a03cab890f0ba9176a6fb49eb371b539592b0f16c78094e10b1cb21f594e6cb7366035475c6f8bbf9b166b41d9c775363a92f8ab945ea74db7f2ed0a1c7f4025b7e62266e142b7b73181b8ade5947da4ccac687c1af8ac4398bb6bd584bf3a0b6a80af8724d759b44f362f57cfd26127dfa0992b5bc9564818cc838cb46dd4cff8da3fd40aba5ae5c2af2e565eade38e2032c29d9b33da1d52b541b88b2085fabe4976133483ed37c78d54419efd265a01b6742fa5d4bb7f9de9e5fa8dbc081e9f9cbbfc713a990bf499a7591f25e8c37c8ce9db346e77f0a1cbb5a5524f8dd790aef131d6836d4d6ab18c300bd7d83d0fca8802f4ec73e4576dfd7c8e16f00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, 'Profesor', 'Probando', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `IdPrestador` int(11) NOT NULL AUTO_INCREMENT,
  `HuellaUsuario` longblob NOT NULL,
  `Nombre` varchar(45) NOT NULL,
  `Apellidos` varchar(45) NOT NULL,
  `TipoUsuario` tinyint(1) NOT NULL DEFAULT '0',
  `Usuario` varchar(45) DEFAULT NULL,
  `Password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`IdPrestador`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`IdPrestador`, `HuellaUsuario`, `Nombre`, `Apellidos`, `TipoUsuario`, `Usuario`, `Password`) VALUES
(1, 0x00f88c01c82ae3735ca14e2fab21e035bfe1dce2c001fe56e36796e789705f3fa67b011c8cc8a45203999f252b15ebc15fbe395174701967882e57b57e3e8615312214eaf2e06834ebe3fc3f2e242088ea237526003da312b9f8dbd1a297a3d4fcb9c283794ef337056c8152082f53d19aa9495c87b5ea156e30fc1046d3067244fd35ae8d88c348942eceae98d6c1bcaf02d62267dff313b70ca4dd2540ba5994d6dc3c8981c0815846cb9afcd58a674d29a1efc534f6ebd54e12cdfcc2a3daad2abb603e682f6a6e15d6c37ee18ac3ff9b0f3d5ffdd6c80e6ba52812a7cc6a124d3ec35c3cb0a7878f33ea582d664abd9abd0f3b709d45a5adb2d5c32e1904c779b3372515c2e58b0208d4140e553b7b277788c386cc8836dc82fbb2f073ae6c6e971f4061a86672976034d344c5e123d0c47d2a7c067762daf0a35d196c6a47f51cd5a1b776793c310aa016ac8fe0c92bfe7acd1b307506d4fea375abc05e0b7374487c277ea76aa3e79407efb3900b8f13e7ecce00ebbb6a7a6266397fd45bf139c4ed6ec4237c9fea3a4c64e0376f00f89001c82ae3735cdf4d2e4658d88cd5c0e419ab57460c188c5612a14f724e3b936c89a26f29dd47e9036d0b8ebb9560be238ad7caab0e497d5c6adcda903debab962bfff622f346d881cb7ca244da619b5db7b83273f362379fcd7e9e198779b2b088dfd3438803ff132aef99570d1e33ab46d423d4b3a7319923be3a956f33bc35095002219ddb3acdfe7528e5dd6cbc14a703ef934d880f8e3abcf00c07452475975fd6744bac730606b6137733186c361d30174b75e935f6a87e97256cbe7fddc70a3a6ba91206b9586bffcdfc7a6c5522512bc510a2a4ec3d67ca8e1d550a5944347d3499fce755aba0dc7d018af0ab7364fdee98d22e15fc3cf0d33cdec1e2bd91d945370f2dbdfd74b10954eb96ab5e2be8a4f2a78f06addd41c26defb1a5bd5469ed1186db5855af60c1127d0cf44ef6de71d11ffc1f474d01ab068e0889fbb5fe2a78be5f42320728beb89b7aed0c6873cf88aa748a78d3c5d747a7230e3042d7555445f55da0144fe3047533bf38c3df3a8c0426b6f1ffb39db59c1059450e670c3f08ba2a2150737e2310020ec26f00f88c01c82ae3735cab4d2f7280414fdf911a04b072b9a7624337503ac0b2891adb9f0724f744c1dd6fb7f385dec44b3928fcaba12be186f7f76d609668da6bd71122ba1608130270a58a39cb1e9f99c7492a5d88c4e79c05f42a121f558e3868ead64edf81ba5df06c1f40e14e0a39b18af0258f453b5fb4cd1a02a9c911991b20abe2588c1cdc3957ca0064b74b68c04a65bd044f198f2cbb4a7348986fe36807a647eed18035d56bc27dd12b9b221321f018c7d3fc5c5c6b1b7dae4fb5d587a36ffc1b43006a0ebd98a4019079b0431298519ee36be3cdf90867b71754e899f65f20f5528daedb8c19b2868e47fe4e660dbc729858aa11792e6b29fea97ff9496e5697c48a4712f34d2ea9d815e35e82fdf40e7298ffbd97828d1d2823727a8ee7751727d05f961705c3bdbba7f8cb1791a590c032aa9d37416d3dfa9951f9e71c9a0b61c8cc2132bdfab915cbfdab8c859630df626de7fa95ed4c901587e78238a5d47d485aac754e0e13cfab357a26ea814c5633653ae2bc332817ee16715143bacd7c5e60ba2fb6722a3c9a2f6f00e88b01c82ae3735cd04e2fb8138f4c093df79d4a4eee4a0eb8c611e7081b645d0bf63c80d47482dbae98c5e0eab678780f3cccba8e653b2a4d3950946f6ea0edc1a456ea2c29345d57fa538639e0a76714cb3a87a46d0b2c6993801c7a2b79727ce337a515cac81855ba316f19882af55f4175e5e1eaa28a468e0c4ad3ca77cc6dc40c75cacd7ea2269fc37ecfd7f0e787ef009cd93f40c7e6e927c77222a355d1f310bcea36f54a0bc0abcda1adb87730f7ec6c169ef10bd08ae1028fb4d8f105a22f00c6bac28368b3954c64352f0ec3bdcf9b596b5c57ac8e846865d694dab34f402cf0224ede91dc4f6fe44c615f41a1fd1e85d9e4452fb1778e4ed84dbb268bb1c7065d2801f094ff7e366e5f270658817070f283e4b93ab449f2e7ba969c58db4fcadee09171f6e3c0738d4028a4850a711b502558210d7e48941594ae03c142ec7e11f54288463735160f49d3de7ebf91f2a7eff6ff5e18b9e6ab11cd34b8e11770b1d7776cb0455bc4db9680147c45c0455e1cde037661dde7630043c9656a1e61e9cb6b29c9a225711e6f00000000000000000000000000000000000000000000000000, 'Usuario', 'Administrador', 1, 'admin', '1234');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `colegioprofesor`
--
ALTER TABLE `colegioprofesor`
  ADD CONSTRAINT `fk_ColegioProfesor_Profesor1` FOREIGN KEY (`Profesor_IdProfesor`) REFERENCES `profesor` (`IdProfesor`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_ColegioProfesor_Colegio1` FOREIGN KEY (`Colegio_IdColegio`) REFERENCES `colegio` (`IdColegio`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `prestamo`
--
ALTER TABLE `prestamo`
  ADD CONSTRAINT `IdProfesor` FOREIGN KEY (`Profesor_IdProfesor`) REFERENCES `profesor` (`IdProfesor`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `IdMaterial` FOREIGN KEY (`Material_IdMaterial`) REFERENCES `material` (`IdMaterial`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `IdPrestador` FOREIGN KEY (`Usuario_IdPrestador`) REFERENCES `usuario` (`IdPrestador`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
