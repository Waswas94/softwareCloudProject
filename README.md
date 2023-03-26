# Project : Software Engineering for cloud 

# Subject Description:

The subject of the project is a cloud-based e-commerce application that consists of three microservices: Inventory, Order and Warehouse.

Inventory Microservice: Manages the inventory of products, including the stock levels, product details, and product categories.

Order Microservice: Manages customer orders, including order creation, order updates, and order cancellation.

Warehouse Microservice: Manages warehouses and the stock items stored within them, including warehouse locations and stock availability.

The communication between the microservices is implemented using Kafka message streaming.

# Architecture:

The architecture of the project follows a microservices-based architecture pattern. Each microservice is implemented as an independent and modular service that can be deployed, scaled, and updated separately. Each microservice has its own data store and can communicate with other microservices using Kafka message streaming.
The project uses Spring Boot for developing the microservices, Kafka for message streaming, and MySQL for data storage. Kubernetes is used for container orchestration.


Tools and Technologies:

• Java

•	Spring Boot

•	Docker

•	Kubernetes

•	Apache Kafka

•	MySQL

•	Terraform

# Running the project:

To run the project, you will need to have the following installed on your machine:

•	Java 17

•	Apache Maven

•	Docker

•	Kubernetes

•	Kafka

•	Terraform


Once you have installed these dependencies, follow these steps:

•	Clone the project repository to your local machine.

•	Update the configuration files (e.g, application.properties) for each microservice with the appropriate settings, such as database connection details and Kafka server details.

•	Navigate to the project directory in your terminal.


Build the Docker images for each microservice by running the following commands:

Inventory microservice :

•	cd inventory-service

•	mvn clean package docker:build


Order microservice :

•	cd ../order-service

•	mvn clean package docker:build


Warehouse microservice :

•	cd ../warehouse-service

•	mvn clean package docker:build


Deploy the microservices to your local Kubernetes cluster by running the following commands:

•	kubectl create -f inventory-config.yaml

•	kubectl create -f order-config.yaml

•	kubectl create -f warehouse-config.yaml


Test the Terraform scripts locally with  the following command :

•	terraform init

•	terraform plan

•	terraform apply


You can test the communication between the microservices by sending test requests to the APIs exposed by each microservice.
