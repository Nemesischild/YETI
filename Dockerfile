FROM ubuntu:latest@sha256:6d0e0c26489e33f5a6f0020edface2727db9489744ecc9b4f50c7fa671f23c49

ENV NODE_VERSION 8.11.2

LABEL author="Vincent Voyer <vincent@zeroload.net>"
LABEL maintainer="Serban Ghita <serbanghita@gmail.com>"

ENV LC_ALL=C
ENV DEBIAN_FRONTEND=noninteractive
ENV DEBCONF_NONINTERACTIVE_SEEN=true

EXPOSE 4444

RUN apt-get -qqy update
RUN apt-get -qqy install \
    apt-transport-https \
    ca-certificates \
    curl \
    software-properties-common \
    sudo

# Ollys additions
#RUN apt-get install -y default-jdk - may be causing dependency issues later
RUN apt-get install -y maven
RUN apt-get install -y build-essential libssl-dev libffi-dev python3-dev
RUN apt-get install -y python3-pip
RUN pip3 install awscli --upgrade


RUN curl -sS -o - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add -
RUN echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list
RUN echo "deb http://archive.ubuntu.com/ubuntu xenial main universe\n" > /etc/apt/sources.list \
  && echo "deb http://archive.ubuntu.com/ubuntu xenial-updates main universe\n" >> /etc/apt/sources.list \
  && echo "deb http://security.ubuntu.com/ubuntu xenial-security main universe\n" >> /etc/apt/sources.list
RUN apt-get -qqy update


RUN groupadd --gid 1000 node \
  && useradd --uid 1000 --gid node --shell /bin/bash --create-home node
RUN echo 'node ALL=(ALL) NOPASSWD: ALL' >> /etc/sudoers

RUN curl -sL https://deb.nodesource.com/setup_8.x | bash -
RUN apt-get -qqy --no-install-recommends install \
  nodejs \
  firefox \
  google-chrome-stable \
  openjdk-8-jre-headless \
  xvfb \
  xfonts-100dpi \
  xfonts-75dpi \
  xfonts-scalable \
  xfonts-cyrillic

RUN export DISPLAY=:99.0
RUN Xvfb :99 -shmem -screen 0 1366x768x16 &
RUN export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
RUN update-java-alternatives -s java-1.8.0-openjdk-amd64 
RUN export PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/lib/jvm/java-8-openjdk-amd64
RUN apt-get update
RUN apt-get install -y openjdk-8-jdk
WORKDIR /home/node
# For development
# ADD . ./selenium-standalone-local
# RUN chown node:node -R .
USER node
RUN export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
RUN sudo update-java-alternatives -s java-1.8.0-openjdk-amd64
RUN npm init -y
# RUN npm install -i ./selenium-standalone-local
RUN npm install -i selenium-standalone

ADD helpers /usr/local/testframework/helpers
ADD testrunner /usr/local/testframework/testrunner
ADD ABRSM_PopMusic_LoadTest /usr/local/testframework/ABRSM_PopMusic

RUN cd /usr/local/testframework/testrunner && sudo mvn clean install
RUN cd /usr/local/testframework/helpers && sudo mvn clean install
RUN cd /usr/local/testframework/ABRSM_PopMusic && sudo mvn clean install
RUN cd /usr/local/testframework/ABRSM_PopMusic && sudo mvn -Dtest=ABRSMPopMusicWebRunner test -e

#CMD DEBUG=selenium-standalone:* ./node_modules/.bin/selenium-standalone install && DEBUG=selenium-standalone:* ./node_modules/.bin/selenium-standalone start
CMD /bin/bash -c "trap : TERM INT; sleep infinity & wait"