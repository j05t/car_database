#!/bin/sh
java -jar ../lib/schemaSpy_5.0.0.jar -t pgsql -db postgres -host localhost -u postgres -p test -o ./schemaspy -dp ../lib/postgresql-9.4.1211.jar -s public -noads
