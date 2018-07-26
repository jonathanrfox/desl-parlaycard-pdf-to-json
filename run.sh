# USAGE:
#   run [path/to/parlay] [std,tsr,sup,rev] [week#]

./gradlew -q build && ./gradlew -q -Djava.util.logging.config.file=logging.properties run -Pargs="${1} ${2} ${3}"
