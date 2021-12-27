docker_start:
	docker start my-app || docker run -p 5434:5432 --name my-app -e POSTGRES_PASSWORD=mysecretpassword -d postgres:latest

create_db:
	PGPASSWORD=mysecretpassword createdb -h localhost -p 5434 -U postgres my_app_dev
	PGPASSWORD=mysecretpassword createdb -h localhost -p 5434 -U postgres my_app_test

drop_db:
	PGPASSWORD=mysecretpassword dropdb -h localhost -p 5434 -U postgres my_app_dev
	PGPASSWORD=mysecretpassword dropdb -h localhost -p 5434 -U postgres my_app_test

test_ci:
	mvn test

config:
	cp -n src/main/resources/application-dev.properties.sample src/main/resources/application-dev.properties | true
	cp -n src/test/resources/application-test.properties.sample src/test/resources/application-test.properties | true

run: compile
	SPRING_PROFILES_ACTIVE=dev \
	mvn spring-boot:run

test: compile
	SPRING_PROFILES_ACTIVE=test \
	FLYWAY_CONFIG_FILES=src/test/resources/appication-test.properties \
	mvn flyway:clean flyway:migrate test
	awk -F"," '{ instruction += $$4 + $$5; covered += $$5 } END { print covered, "/", instructions, " instructions covered"; print 100*covered/instructions,"% covered" } target/site/jacoco.csv'

build:
	mvn -Dmaven.test.skip.exec package

migrate: compile
	FLYWAY_CONFIG_FILES=src/test/resources/appication-dev.properties
	mvn flyway:clean

compile:
	mvn clean
	mvn compile

reset: clean migrate

sanity: test build