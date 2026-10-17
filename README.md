Create JWT Key:
openssl rand -base64 32
export JWT_SECRET='YOUR_GENERATED_SECRET'
mvn spring-boot:run
