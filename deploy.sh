rm -rf build
./gradlew build
sudo docker stop backend
sudo docker rm backend
sudo docker build -t clickspring .
sudo docker run -d -p 8080:8080 --name backend -e TZ=Asia/Seoul --network click-net clickspring