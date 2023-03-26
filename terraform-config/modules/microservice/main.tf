variable "service_name" {}

resource "aws_security_group" "microservice_sg" {
  name        = "${var.service_name}_sg"
  description = "Allow inbound traffic for ${var.service_name} microservice"
}

resource "aws_instance" "microservice" {
  ami           = "ami-0c55b159cbfafe1f0"
  instance_type = "t2.micro"

  key_name          = "your_key_pair_name"
  security_groups   = [aws_security_group.microservice_sg.id]
  availability_zone = "us-west-2a"

  tags = {
    Name = "${var.service_name}_instance"
  }
}
