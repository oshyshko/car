Setting up development environment
==================================


Prepare development machine
---------------------------
- JDK 8 http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
- IntelliJ or any other IDE


Prepare Pi machine
------------------
Enable SSH with:
$ sudo raspi-config

Update everything:
$ sudo rpi-update

Download JDK 8 for ARM from http://www.oracle.com/technetwork/java/javase/downloads/jdk8-arm-downloads-2187472.html

$ sudo tar zxvf ~/Downloads/jdk-8u6-linux-arm-vfp-hflt.tar.gz -C /opt

$ sudo update-alternatives --install /usr/bin/javac javac /opt/jdk1.8.0_06/bin/javac 1
$ sudo update-alternatives --install /usr/bin/java java /opt/jdk1.8.0_06/bin/java 1

$ sudo update-alternatives --config javac
$ sudo update-alternatives --config java

Verify that version 8 is default:
$ java -version
$ javac -version


Install lein
------------
$ mkdir -p ~/bin
$ cd ~/bin
$ wget https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein
$ chmod 755 lein
$ ./lein

Then add ~/bin to your PATH (e.g. alter ~/profile)


Set password-less SSH
---------------------
On development machine (replace <PI-HOST> with actual IP):

If you haven't done it yet:
$ ssh-keygen -t rsa

$ ssh pi@<PI-HOST> mkdir -p .ssh
$ cat ~/.ssh/id_rsa.pub | ssh pi@<PI-HOST> 'cat >> .ssh/authorized_keys'

Test with:
$ ssh pi@$<PI-HOST>
Ctrl+D to leave

- Clone this repo and setup IntelliJ 
$ git clone https://github.com/oshyshko/car.git
$ cd car
$ lein pom

Open IntelliJ and import Maven project from 'car/pom.xml'.


Override default variables with yours
-------------------------------------
$ cd car
$ touch .setvars.sh

Edit '.setvars.sh' to override with your values, e.g.:
PI_USER=pi
PI_HOST=192.168.1.20

Don't commit this file, it's for your machine only.


Building
--------
$ cd car
$ ./uberjar.sh

Pick result from 'target/car-0.1.0-SNAPSHOT-standalone.jar'.


Building + running on PI via SSH
----------------------------------
$ cd car
$ ./deploy.sh


Other stuff
-----------
$ cd car
$ ./remote_shutdown.sh
$ ./remote_restart.sh
