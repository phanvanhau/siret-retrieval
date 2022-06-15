#!/bin/bash
SERVICE_NAME=siret-retrievel

function prepareVersion() {
  pom_version=$(sed -n -e 's/.*<version>\(.*\)<\/version>.*/\1/p' pom.xml | sed -n 2p)
  version=$(echo ${pom_version} | cut -d '-' -f 1)
  IMAGE_VERSION="${SERVICE_NAME}:${version}"
}

function mvnBuild() {
  echo "Building Project ..."
  ./mvnw clean package
}

function buildContainer() {
  echo "Building Container..."
  docker build --rm  --file ./docker/Dockerfile --tag "${IMAGE_VERSION}" .
}

function uploadToDockerRegistry() {
  echo "Nothing to do for now"
}

prepareVersion
mvnBuild
buildContainer
uploadToDockerRegistry
