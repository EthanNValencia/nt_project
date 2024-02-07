# Status: Back in black!

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

## Create Database and User

```sql
CREATE DATABASE nt_db;

CREATE USER nt_user WITH PASSWORD 'password';

GRANT ALL PRIVILEGES ON DATABASE nt_db TO nt_user;

ALTER DATABASE nt_db OWNER TO nt_user;
```

# Terminal Command(s)

## Launch Services and UIs

```bash
bash install.sh
bash launch.sh
```