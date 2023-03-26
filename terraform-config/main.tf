provider "aws" {
  region = "us-west-2"
}

module "inventory" {
  source = "./modules/microservice"
  service_name = "inventory"
}

module "order" {
  source = "./modules/microservice"
  service_name = "order"
}

module "warehouse" {
  source = "./modules/microservice"
  service_name = "warehouse"
}
