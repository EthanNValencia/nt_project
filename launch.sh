# docker-compose-001.yaml  launch.sh	 nt-naming-server     sql
# docker-compose.yaml	 MySQL.txt	 nt-security-service
# email_service		 npt-server	 postgres-data
# error_service		 nt-api-gateway  sms_service
#!/bin/sh
#ls

# xfce4-taskmanager &
# systemctl start docker &&
# sleep 5
terminator --new-tab -e "cd naming_server/; bash -c 'echo -ne \"\033]0;Naming\007\"'; mvn spring-boot:run" &
sleep 5
terminator --new-tab -e "cd api_gateway/; bash -c 'echo -ne \"\033]0;Gateway\007\"'; mvn spring-boot:run" &
terminator --new-tab -e "cd security_service/; bash -c 'echo -ne \"\033]0;Security\007\"'; mvn spring-boot:run" &
terminator --new-tab -e "cd sms_service/; bash -c 'echo -ne \"\033]0;SMS\007\"'; mvn spring-boot:run" &
terminator --new-tab -e "cd email_service/; bash -c 'echo -ne \"\033]0;Email\007\"'; mvn spring-boot:run" &
terminator --new-tab -e "cd error_service/; bash -c 'echo -ne \"\033]0;Error\007\"'; mvn spring-boot:run" &
terminator --new-tab -e "cd npt/; bash -c 'echo -ne \"\033]0;NPT\007\"'; mvn spring-boot:run" &
# sleep 10
terminator --new-tab -e "cd admin_front/; bash -c 'echo -ne \"\033]0;Admin\007\"'; npm run dev" &
terminator --new-tab -e "cd npt_front/; bash -c 'echo -ne \"\033]0;Npt\007\"'; npm run dev" &
sleep 5
firefox --new-tab http://localhost:5050 &
firefox --new-tab http://localhost:4001/login &
firefox --new-tab http://localhost:4000 &
# bash curl.sh 