CREATE TABLE users 
	(id INTEGER not NULL AUTO_INCREMENT PRIMARY KEY ,
	first_name VARCHAR(255) NOT NULL ,
	last_name VARCHAR(255) NOT NULL ,
	age INTEGER
);

CREATE TABLE doctors 
	(id INTEGER not NULL AUTO_INCREMENT PRIMARY KEY ,
	first_name VARCHAR(255) NOT NULL ,
	last_name VARCHAR(255) NOT NULL ,
	department VARCHAR(255),
	age INTEGER
);

CREATE TABLE diagnosis 
	(id INTEGER not NULL PRIMARY KEY AUTO_INCREMENT,
	description VARCHAR(255) not NULL
);

CREATE TABLE cards 
	(id INTEGER not NULL AUTO_INCREMENT,
	doctor_id INTEGER not NULL,
	user_id INTEGER not NULL,
	diagnosis_id INTEGER not NULL,
	name VARCHAR(255),
	PRIMARY KEY ( id ),

	CONSTRAINT cards_doctors
	FOREIGN KEY (doctor_id)
		REFERENCES doctors(id),

	CONSTRAINT cards_users
	FOREIGN KEY (user_id)
		REFERENCES users(id),

	CONSTRAINT cards_diagnosis
	FOREIGN KEY (diagnosis_id)
		REFERENCES diagnosis(id)
);

CREATE TABLE notes 
	(id INTEGER not NULL AUTO_INCREMENT,
	doctor_id INTEGER NOT NULL ,
	card_id INTEGER NOT NULL ,
	note_text TEXT,
	PRIMARY KEY ( id ),

	CONSTRAINT notes_doctors
	FOREIGN KEY (doctor_id)
		REFERENCES doctors(id),

	CONSTRAINT notes_cards
	FOREIGN KEY (card_id)
		REFERENCES cards(id)
);