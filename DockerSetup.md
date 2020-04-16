To run the dockerfile CD to the top level of the repo and execute:
 
docker build . 

There are some configuration changes that will need to be in place:

These are constants as we will always want to build testrunner and helpers,
this setup means they will be put in the  following directory within the docker container:
 /usr/local/testframework/testrunner 
 /usr/local/testframework/helpers 
 
RUN cd /usr/local/testframework/testrunner && sudo mvn clean install
RUN cd /usr/local/testframework/helpers && sudo mvn clean install


These are variables for configuration:
Using this format you will need to insert your project into the relevant places 

RUN cd /usr/local/testframework/YOUR_PROJECT && sudo mvn clean install
RUN cd /usr/local/testframework/YOUR_PROJECT && sudo mvn -Dtest=YOURTESTRUNNER test -e


-e argument offers some output
-X enables full debugging logging if you are having issues change any mvn command run to have -X at the end for full 
outputs