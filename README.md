## Exercise solved by Alexandre Pinto

### Frontend

Implemented using React, which is a familiar framework for me.
<br>UI URL: http://localhost:3000

### Backend

My goal was to focus on the Backend, as I worked more with it than the Frontend during academic projects and I feel more confortable. As suggested, I implemented using Quarkus, a new tool for me, because it seems to be a framework with good potential and performance. I used this exercise to learn about it. I created tests and documentation. I also implemented a simple cache using quarkus dependencies to speed up the calculation of repeated values.
<br>Endpoint URL: http://localhost:8080/labseq/{n}
<br>Documentation URL (Swagger): http://localhost:8080/swagger-ui

### Containers

As Quarkus is a docker-ready framework and creates automatically Dockerfile, i also tried to explore this. I created a Dockerfile for the React application and a docker-compose.yml configuration file to build/run both services at the same time.

### Important Commands

```
# build the application
docker-compose build

# run the application
docker-compose up

# IF THERE IS ANY PROBLEM RUNNING REACT (because of node_modules)
cd my-app
npm install
cd ..
docker-compose build
docker-compose up

# stop and remove the containers
docker-compose down
```
