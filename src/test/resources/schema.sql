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
  id_media text,
  type text,
  author text,
  title text,
  creation text,
  genre text,
  CONSTRAINT music_pkey PRIMARY KEY(id_media)
)
WITH (
OIDS=FALSE
);

DROP TABLE IF EXISTS users;
CREATE TABLE users
(
  id_user text,
  username text,
  email text,
  salt text,
  passhash text,
  CONSTRAINT media_pkey PRIMARY KEY(id_user)
)
WITH (
OIDS=FALSE
);

DROP TABLE IF EXISTS loan;
CREATE TABLE loan
(
  id_loan text,
  id_media text REFERENCES media (id_media),
  id_user text REFERENCES users (id_user),
  CONSTRAINT loan_pkey PRIMARY KEY (id_media,id_user)
)
WITH (
OIDS=FALSE
);

INSERT INTO media (id_media, type, author, title, creation, genre)
VALUES('1','movie','Steven Spielberg','E.T','1982/12/01','Science-fiction');

INSERT INTO users (id_user, username, email, salt, passhash)
VALUES('1','ph','ph@yopmail.com','E1F53135E559C253','72AE25495A7981C40622D49F9A52E4F1565C90F048F59027BD9C8C8900D5C3D8
');

INSERT INTO loan (id_loan, id_media, id_user)
VALUES('1','1','1');