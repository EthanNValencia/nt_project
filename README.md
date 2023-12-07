# Local Setup

## Run Docker Compose

```bash
docker compose up -d
```

## Connecting to pgAdmin

**Credentials:**
- **Username:** nephewtechnologies@gmail.com
- **Password:** ekju3892jejke

## Connecting to PostgreSQL Database via pgAdmin

**Connection Details:**
- **Name:** nt
- **Host:** host.docker.internal
- **Port:** 5432
- **Database:** postgres
- **User:** postuser
- **Password:** jkk3i92jfjrio

# SQL Commands

## Create Databases

```sql
CREATE DATABASE npt_db;
CREATE DATABASE error_db;
CREATE DATABASE email_db;
CREATE DATABASE auth_db;
CREATE DATABASE sms_db;
```

## Create Users and Grant Privileges

```sql
CREATE USER npt_user WITH PASSWORD 'password';
CREATE USER error_user WITH PASSWORD 'password';
CREATE USER email_user WITH PASSWORD 'password';
CREATE USER auth_user WITH PASSWORD 'password';
CREATE USER sms_user WITH PASSWORD 'password';

GRANT ALL PRIVILEGES ON DATABASE npt_db TO npt_user;
GRANT ALL PRIVILEGES ON DATABASE error_db TO error_user;
GRANT ALL PRIVILEGES ON DATABASE email_db TO email_user;
GRANT ALL PRIVILEGES ON DATABASE auth_db TO auth_user;
GRANT ALL PRIVILEGES ON DATABASE sms_db TO sms_user;

ALTER DATABASE npt_db OWNER TO npt_user;
ALTER DATABASE error_db OWNER TO error_user;
ALTER DATABASE email_db OWNER TO email_user;
ALTER DATABASE auth_db OWNER TO auth_user;
ALTER DATABASE sms_db OWNER TO sms_user;
```

# Terminal Command(s)

## Launch Services and UIs

```bash
bash launch.sh
```