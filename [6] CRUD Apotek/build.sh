if [[ $1 == 1 ]]; then
    clear;
fi

javac -d bin/ $(find src -name "*.java")
java -cp bin src.main.Main

echo "Done"
read -s

if [[ $1 == 1 ]]; then
    clear;
fi