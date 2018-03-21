DROP TABLE if EXISTS users;

CREATE TABLE `users` (
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `enable` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO users
 VALUES
 ('hgf','****', TRUE ),
 ('xxx','****', FALSE )
