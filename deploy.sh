rm -rf build
./gradlew build
sudo docker build -t clickspring .
sudo docker run -d -p 8080:8080 --network click-net clickspring