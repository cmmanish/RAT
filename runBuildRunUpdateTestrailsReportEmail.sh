#!/bin/sh

#Clean and Assemble the Project

./gradlew clean assemble compileJava compileTestJava

#running a single Testsuite

./gradlew -Dtest.single=TestSuiteRunner test --rerun-tasks

#To run TestRails report
#python testrail_scripts/testrail_reporter.py testrail_scripts/testrail.json

#send Email
#./gradlew -Dtest.single=QaReportProcessor test


gradle -Dtest.single=TestRailsMethods test