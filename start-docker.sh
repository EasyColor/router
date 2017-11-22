docker rm -f backend-cassandra
docker run -d -p 9042:9042 -p 9160:9160 --name backend-cassandra cassandra:latest


