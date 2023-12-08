docker compose volume: - ./sql/create_dbs.sql:/docker-entrypoint-initd.b/create_dbs.sql


CREATE DATABASE auth_db
    WITH
    OWNER = postuser
    ENCODING = 'UTF8'
    LOCALE_PROVIDER = 'libc'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

CREATE USER auth_user WITH PASSWORD 'jrtji3892jfj';
CREATE SCHEMA IF NOT auth_001 AUTHORIZATION postuser;
GRANT CONNECT ON DATABASE auth_db TO auth_user;
GRANT USAGE, CREATE ON SCHEMA auth_001 TO auth_user;


CREATE USER npt_user WITH PASSWORD '312jfii4j49ek';

-- CREATE DATABASE database_name;

CREATE DATABASE npt_db
    WITH
    OWNER = postuser
    ENCODING = 'UTF8'
    LOCALE_PROVIDER = 'libc'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

-- CREATE SCHEMA database_name.schema_name;
CREATE SCHEMA IF NOT auth_db.auth_001 AUTHORIZATION postuser;
CREATE SCHEMA IF NOT npt_db.npt AUTHORIZATION postuser;

-- GRANT CONNECT ON DATABASE database_name TO username;
GRANT CONNECT ON DATABASE auth_db TO auth_user;
GRANT CONNECT ON DATABASE npt_db TO npt_user;

-- GRANT USAGE, CREATE ON SCHEMA schema_name TO username;
GRANT USAGE, CREATE ON SCHEMA auth_001 TO auth_user;
GRANT USAGE, CREATE ON SCHEMA npt TO npt_user;
