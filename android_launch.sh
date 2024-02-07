# admin_front  docker-compose.yaml  naming_server  postgres_data     sql
# api_gateway  email_service        npt            README.md
# curl.sh      error_service        npt_front      security_service
# dev.sh       launch.sh            old_data       sms_service
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
terminator --new-tab -e "cd oesa_service/; bash -c 'echo -ne \"\033]0;OESA\007\"'; mvn spring-boot:run" &
terminator --new-tab -e "cd faqs_service/; bash -c 'echo -ne \"\033]0;Faqs\007\"'; mvn spring-boot:run" &
terminator --new-tab -e "cd website_service/; bash -c 'echo -ne \"\033]0;Website\007\"'; mvn spring-boot:run" &
terminator --new-tab -e "cd stripe_service/; bash -c 'echo -ne \"\033]0;Stripe\007\"'; mvn spring-boot:run" &
sleep 5
firefox --new-tab http://localhost:5050 