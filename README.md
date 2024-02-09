# Status: Back in black!

### TODO
- Bring react uis together. Create a versioning system. 
- Notify

# Recruiter/HR/Interview Password

#### What is this? If you are a recruiter, hr, or someone involved in a hiring process in any way, and my resume wound up in front of you. If you choose to contact me, then I will ask you what the password is. 

```bash
password = jammy jellyfish
```

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