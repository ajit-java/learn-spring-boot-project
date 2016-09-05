:one: 

java -version

Install oracle java 8 if not already installed.

http://www.webupd8.org/2014/03/how-to-install-oracle-java-8-in-debian.html


:two: 
Create group/user

```
sudo groupadd tomcat

sudo useradd -s /bin/false -g tomcat -d /opt/tomcat tomcat
```


:three: 
install tomcat

```
apt-get remove tomcat7 #if tomcat 7 is installed and running

wget http://mirror.sdunix.com/apache/tomcat/tomcat-8/v8.0.30/bin/apache-tomcat-8.0.30.tar.gz

sudo mkdir /opt/tomcat

sudo tar xvf apache-tomcat-8*tar.gz -C /opt/tomcat --strip-components=1
```

:four: 

```
cd /opt

chown -R tomcat:tomcat tomcat
```

:five: 
Create linux service script

```
vim /etc/init.d/tomcat
```

[tomcat.init.script](/uploads/ce2dcb1b6239c598bd7a31a2116ae673/tomcat.init.script)

**run to find java home for java 8** 

```
update-alternatives --config java
```

**update vim /etc/init.d/tomcat with line** ```JDK_DIRS="/usr/lib/jvm/java-8-oracle"```

**update vim /etc/init.d/tomcat with line** ```JAVA_OPTS="-Djava.awt.headless=true -Xmx512M"```


:six:
provide execute permission to to linux service script

```
chmod +x /etc/init.d/tomcat

update-rc.d tomcat defaults
```

:seven:
install connectors etc 

more at 

http://www.mogilowski.net/lang/en-us/2014/04/22/install-apache-tomcat-8-on-debian-7-wheezy-with-virtual-hosts-and-apache2-integration/
