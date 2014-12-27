Setting up development environment
==================================


Prepare development machine
---------------------------
- Install JDK 8 http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
- Install IntelliJ or any other IDE


Prepare Pi machine
------------------
- Install Raspbian, e.g. via NOOBS http://www.raspberrypi.org/help/noobs-setup/

- Enable SSH with<br>
$ sudo raspi-config

- Update everything<br>
$ sudo rpi-update

- Install JDK 8u6+ for ARM from http://www.oracle.com/technetwork/java/javase/downloads/jdk8-arm-downloads-2187472.html<br>
$ sudo tar zxvf ~/Downloads/jdk-8u6-linux-arm-vfp-hflt.tar.gz -C /opt<br>
<br>
$ sudo update-alternatives --install /usr/bin/javac javac /opt/jdk1.8.0_06/bin/javac 1<br>
$ sudo update-alternatives --install /usr/bin/java java /opt/jdk1.8.0_06/bin/java 1<br>
<br>
$ sudo update-alternatives --config javac<br>
$ sudo update-alternatives --config java<br>
<br>
Verify that version 8 is default:<br>
$ java -version<br>
$ javac -version


Set password-less SSH
---------------------
On development machine (replace <PI-HOST> with actual IP):

If you haven't done it yet:<br>
$ ssh-keygen -t rsa

$ ssh pi@<PI-HOST> mkdir -p .ssh<br>
$ cat ~/.ssh/id_rsa.pub | ssh pi@<PI-HOST> 'cat >> .ssh/authorized_keys'<br>

Test with:<br>
$ ssh pi@$<PI-HOST><br>
Ctrl+D to leave

Clone this repo and setup IntelliJ:<br>
$ git clone https://github.com/oshyshko/car.git<br>
$ cd car<br>
$ lein pom

Open IntelliJ and import Maven project from 'car/pom.xml'.


Override default variables with yours
-------------------------------------
$ cd car<br>
$ touch .setvars.sh

Edit '.setvars.sh' to override with your values, e.g.:<br>
PI_USER=pi<br>
PI_HOST=192.168.1.20

Don't commit this file, it's for your machine only.


Building
--------
$ cd car<br>
$ ./uberjar.sh

Pick result from 'target/car-0.1.0-SNAPSHOT-standalone.jar'.


Building + running on PI via SSH
----------------------------------
$ cd car<br>
$ ./deploy.sh


Other stuff
-----------
$ cd car<br>
$ ./remote_shutdown.sh<br>
$ ./remote_restart.sh
