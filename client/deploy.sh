echo "Building app..."
npm run build

echo "Deploy files to server ..."
scp -r -i ~/Desktop/diamond_shop build/* root@204.48.31.151:/var/www/html/build/
echo "Done!"