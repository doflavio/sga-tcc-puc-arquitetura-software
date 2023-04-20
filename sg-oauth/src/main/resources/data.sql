/*
INSERT INTO `person` (`id`, `address`, `first_name`, `gender`, `last_name`) VALUES
	(1, 'São Paulo', 'Ayrton', 'Male', 'Senna'),
	(2, 'Anchiano - Italy', 'Leonardo', 'Male', 'da Vinci'),
	(4, 'Porbandar - India', 'Indira', 'Female', 'Gandhi'),
	(5, 'Porbandar - India', 'Mahatma', 'Male', 'Gandhi'),
	(7, 'Kentucky - US', 'Muhammad', 'Male', 'Ali'),
	(9, 'Mvezo – South Africa', 'Nelson', 'Male', 'Mvezo'),
	(10, 'Smiljan - Croácia', 'Nikola', 'Male', 'Tesla');


INSERT INTO `books` (`author`, `launch_date`, `price`, `title`) VALUES
	('Michael C. Feathers', '2017-11-29 13:50:05.878000', 49.00, 'Working effectively with legacy code'),
	('Ralph Johnson, Erich Gamma, John Vlissides e Richard Helm', '2017-11-29 15:15:13.636000', 45.00, 'Design Patterns'),
	('Robert C. Martin', '2009-01-10 00:00:00.000000', 77.00, 'Clean Code'),
	('Crockford', '2017-11-07 15:09:01.674000', 67.00, 'JavaScript'),
	('Steve McConnell', '2017-11-07 15:09:01.674000', 58.00, 'Code complete'),
	('Martin Fowler e Kent Beck', '2017-11-07 15:09:01.674000', 88.00, 'Refactoring'),
	('Eric Freeman, Elisabeth Freeman, Kathy Sierra, Bert Bates', '2017-11-07 15:09:01.674000', 110.00, 'Head First Design Patterns'),
	('Eric Evans', '2017-11-07 15:09:01.674000', 92.00, 'Domain Driven Design'),
	('Brian Goetz e Tim Peierls', '2017-11-07 15:09:01.674000', 80.00, 'Java Concurrency in Practice'),
	('Susan Cain', '2017-11-07 15:09:01.674000', 123.00, 'O poder dos quietos'),
	('Roger S. Pressman', '2017-11-07 15:09:01.674000', 56.00, 'Engenharia de Software: uma abordagem profissional'),
	('Viktor Mayer-Schonberger e Kenneth Kukier', '2017-11-07 15:09:01.674000', 54.00, 'Big Data: como extrair volume, variedade, velocidade e valor da avalanche de informação cotidiana'),
	('Richard Hunter e George Westerman', '2017-11-07 15:09:01.674000', 95.00, 'O verdadeiro valor de TI'),
	('Marc J. Schiller', '2017-11-07 15:09:01.674000', 45.00, 'Os 11 segredos de líderes de TI altamente influentes'),
	('Aguinaldo Aragon Fernandes e Vladimir Ferraz de Abreu', '2017-11-07 15:09:01.674000', 54.00, 'Implantando a governança de TI');
	
	
INSERT INTO 'permission' ('description') VALUES
	('ADMIN'),
	('MANAGER'),
	('COMMON_USER');	
	
	
INSERT INTO 'users' ('user_name', 'full_name', 'password', 'account_non_expired', 'account_non_locked', 'credentials_non_expired', 'enabled') VALUES
	('leandro', 'Leandro Costa', '1e3cdeeaaaeeda173ff6d002e7cb5e3f91ebc354dcff52156c9eaba1793a3a5e5bee306c11099e22', b'1', b'1', b'1', b'1'),
	('flavio', 'Flavio Costa', '362ad02420268beeb22d3a1f0d92749df461d7f4b74c9433d7415bdeef1b2902f4eb1edaecb37cb3', b'1', b'1', b'1', b'1');
	
INSERT INTO 'user_permission' ('id_user', 'id_permission') VALUES
	(1, 1),
	(2, 1),
	(1, 2);		
	*/
	