# 8085 npt, 8000 sec, 8400 err, 8200 em, 8300 sms, security-service
urls=("http://localhost:8000/api/v1/public/health" "http://localhost:8400/api/v1/public/health" "http://localhost:8085/api/v1/public/health" "http://localhost:8200/api/v1/public/health" "http://localhost:8300/api/v1/public/health" "http://localhost:8765/npt-service/api/v1/public/health" "http://localhost:8765/error-service/api/v1/public/health" "http://localhost:8765/security-service/api/v1/public/health")
success=false

for url in "${urls[@]}"; do
    while [ "$success" != true ]; do
        response=$(curl -s -o /dev/null -w "%{http_code}" "$url")

        if [ "$response" -ge 200 ] && [ "$response" -lt 300 ]; then
            echo "Successful response received for $url (HTTP $response)"
            success=true
        else
            echo "Retrying in a few seconds for $url..."
            sleep 5  # Adjust the sleep duration as needed
        fi
    done

    # Reset success flag for the next URL
    success=false
done

echo -e "\e[32mAll services are active!\e[0m"