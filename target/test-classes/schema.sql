DROP TABLE IF EXISTS TOKEN_STORE;
CREATE TABLE TOKEN_STORE
(
  uuid VARCHAR(255) NOT NULL,
  token TEXT NOT NULL ,
  expire_at timestamp without time zone,
  PRIMARY KEY (uuid)
);

DROP TABLE IF EXISTS user_preferences;
CREATE TABLE user_preferences
(
  id text,
  key text,
  value text
)
WITH (
OIDS=FALSE
);