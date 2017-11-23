DROP TABLE IF EXISTS token_store;
CREATE TABLE token_store
(
  uuid character varying(255) NOT NULL,
  token text,
  expire_at timestamp without time zone,
  CONSTRAINT token_store_pkey PRIMARY KEY (uuid)
)
WITH (
OIDS=FALSE
);

DROP TABLE IF EXISTS media;
CREATE TABLE media
(
  id_media NUMERIC,
  type_media text,
  author text,
  title text,
  creation text,
  genre text,
  isAvailable BOOLEAN,
  CONSTRAINT media_pkey PRIMARY KEY(id_media)
)
WITH (
OIDS=FALSE
);

DROP TABLE IF EXISTS users;
CREATE TABLE users
(
  id_user NUMERIC,
  username text,
  email text,
  salt text,
  passhash text,
  CONSTRAINT users_pkey PRIMARY KEY(id_user)
)
WITH (
OIDS=FALSE
);

DROP TABLE IF EXISTS loan;
CREATE TABLE loan
(
  id_loan SERIAL,
  id_media NUMERIC REFERENCES media (id_media),
  id_user NUMERIC REFERENCES users (id_user),
  CONSTRAINT loan_pkey PRIMARY KEY (id_media,id_user)
)
WITH (
OIDS=FALSE
);

INSERT INTO media (id_media, type_media, author, title, creation, genre,isAvailable)
VALUES (1,'music', 'Dream Theater', 'The Astonishing','Metal Progressive Rock','2016/01/29',true);

INSERT INTO media (id_media, type_media, author, title, creation, genre,isAvailable)
VALUES (2,'music','The Neal Morse Band','The Similitude of a Dream','2016/11/11','Christian Metal Progressive Rock',true);

INSERT INTO media (id_media, type_media, author, title, creation, genre,isAvailable)
VALUES(3,'movie','Steven Spielberg','E.T','1982/12/01','Science-fiction',true);

INSERT INTO media (id_media, type_media, author, title, creation, genre,isAvailable)
VALUES(4,'tvshow','	Damon Lindelof, J.J. Abrams','LOST','2004','Aventure, Drame, Action',true);

INSERT INTO users (id_user, username, email, salt, passhash)
VALUES(1,'ph','ph@yopmail.com','E1F53135E559C253','72AE25495A7981C40622D49F9A52E4F1565C90F048F59027BD9C8C8900D5C3D8
');

INSERT INTO loan (id_loan, id_media, id_user)
VALUES(1,1,1);


