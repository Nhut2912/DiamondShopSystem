
echo "Building app..."
./mvnw clean package

echo "Deploy files to server..."
scp -r -i ~/Desktop/diamond_shop target/server.jar root@204.48.31.151:/var/www/diamond_shop/

ssh -i ~/Desktop/diamond_shop root@204.48.31.151 <<EOF
pid=\$(sudo lsof -t -i :8080)

if [ -z "\$pid" ]; then
    echo "Start server..."
else
    echo "Restart server..."
    sudo kill -9 "\$pid"
fi
cd /var/www/diamond_shop
java -jar server.jar
EOF
exit
echo "Done!"