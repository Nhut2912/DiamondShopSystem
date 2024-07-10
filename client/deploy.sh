echo "Building app..."
npm run build

echo "Deploy files to server ..."
scp -r -i ~/Desktop/diamond_shop build/* root@178.128.106.3:/var/www/html/build/
echo "Done!"