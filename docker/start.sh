#!/bin/bash
pom_version=$(sed -n -e 's/.*<version>\(.*\)<\/version>.*/\1/p' pom.xml | sed -n 2p)
jar_file="siret-retrieval-${pom_version}.jar"
java -XX:MaxRAMPercentage=75 -Dlog4j2.formatMsgNoLookups=true -jar ${jar_file} --spring.config.location=classpath:application.yaml
