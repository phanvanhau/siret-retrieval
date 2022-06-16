FROM openjdk:11
EXPOSE 9000
WORKDIR /usr/app/siret-retrieval
COPY ./pom.xml /usr/app/siret-retrieval
COPY ./docker /usr/app/siret-retrieval
COPY ./target /usr/app/siret-retrieval
COPY ./src/main/resources /usr/db/siret-retrieval/
ENV DB_FILE_LOCATION=/usr/db/siret-retrieval/
RUN apt-get update && apt-get install -y procps
RUN chmod 755 /usr/app/siret-retrieval/start.sh
CMD "/usr/app/siret-retrieval/start.sh" "run"
