echo "Iniciando script..."
mkdir -p ./build
echo "Copiando archivos..."

javac -encoding UTF-8 -cp "./lib/mysql.jar:./lib/AbsoluteLayout.jar:./lib/jcalendar.jar" -d ./build/ ./src/Logica/* ./src/WebServices/* ./src/Presentacion/*.java ./src/Persistencia/* > log.txt

echo "Empaquetando archivos..."
sudo jar -cvfm ./ServidorCulturarte.jar ./src/Manifest.txt -C ./build/ . > log2.txt
echo "Archivos empaquetados!"
