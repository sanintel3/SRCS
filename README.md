# SRCS

Install docker and run below command to access application on port 8080. Docker host should have application.yaml under mapped volume
    docker run -d -p 8080:8080 -v /var/app/srcs/conf:/var/app/srcs/conf --name srcs_spring_boot srcs

View logs:
   docker logs --follow <CONTAINER_ID>

Push image to docker registry:
$ docker login
$ docker push sanintel3/srcs:latest