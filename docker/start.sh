#!/bin/bash
pom_version=$(sed -n -e 's/.*<version>\(.*\)<\/version>.*/\1/p' pom.xml | sed -n 2p)
jar_file="siret-retrieval-${pom_version}.jar"
java -XX:MaxRAMPercentage=75 -Dlog4j2.formatMsgNoLookups=true -Dhttps.protocols=TLSv1,TLSv1.1,TLSv1.2 -jar ${jar_file} --spring.config.location=classpath:application.yaml
