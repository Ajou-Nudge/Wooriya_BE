gradle build
docker build -t witt_be:latest . --platform=linux/amd64
docker tag witt_be:latest dlckswn334/witt_be:latest
docker push dlckswn334/witt_be:latest