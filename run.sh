# USAGE:
#   run [path/to/parlay] [std,tsr,sup,rev] [week#]

./gradlew build && ./gradlew -Djava.util.logging.config.file=logging.properties run -Pargs="${1} ${2} ${3}"
