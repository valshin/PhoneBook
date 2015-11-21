#!/bin/bash
export MAVEN_OPTS=-noverify
mvn spring-boot:run -Dlardi.conf=./phonebook.properties
