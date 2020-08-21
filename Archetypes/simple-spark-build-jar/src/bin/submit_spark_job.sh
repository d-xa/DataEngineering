#!/usr/bin/env bash
##############################################
### Variables
##############################################
RED='\033[0;31m'
YELLOW='\033[0;33m'
GREEN='\033[0;32m'
NC='\033[0m' # No Color

APP="spark"
cd ../..
PROJECTPATH=$PWD
SPARKVERSION=$(mvn help:evaluate -Dexpression=spark.version -q -DforceStdout)
MAINCLASS=$(mvn help:evaluate -Dexpression=main.class -q -DforceStdout)
JARWITHDEP=$(find . -type f -iname "*-with-dependencies.jar" )
URL="http://mirror.checkdomain.de/apache/spark/spark-${SPARKVERSION}/spark-${SPARKVERSION}-bin-hadoop2.7.tgz"
INSTFILE=${URL##*/}
APPDIR="${INSTFILE%.tgz}"

##############################################
### Script start
##############################################
echo -e "${GREEN}*********************************************************************"
echo "* submit sparkjob: $JARWITHDEP and class $MAINCLASS"
echo -e "*********************************************************************${NC}"


cd ~/$APP/$APPDIR
./bin/spark-submit \
  --class $MAINCLASS \
  --master local[4] \
 ${PROJECTPATH}/${JARWITHDEP}
