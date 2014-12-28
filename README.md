This page describes how to set up development environment for the project.

Prerequisites
=============
- a Raspberry Pi with Wi-Fi (or at least Ethernet)
- an OS X or Linux development machine (Windows with pre-installed Cygwin or MinGW should work too)


Prepare your Pi
===============
- Install Raspbian, e.g. via NOOBS http://www.raspberrypi.org/help/noobs-setup/<br>
TL;DR: download NOOBS LITE (its just 20MB).
Format your SD card.
Unzip NOOBS LITE contents to SD root.
Stick the SD into your Pi.
Plug in ethernet cable.
Boot the machine and pick "Raspbian" from menu.
Wait when it's done and continue with the rest:
<br>

- Enable SSH in "Advanced Options" via<br>
$ sudo raspi-config<br>
OPTIONAL: set "Overclocking" to "Medium" or even "Turbo" if you dare.

- Setup Wi-Fi<br>
$ startx<br>
Run Wi-Fi preferences and join your Wi-Fi network. Remember your IP (use $ ifconfig).<br>
OPTIONAL: give your Pi a static IP (by it's MAC addr) via Wi-Fi router admin panel, so the IP address won't change in future.

- Update everything<br>
$ sudo rpi-update

- Install the latest JDK for ARM. Open a browser on you Pi, and navigate to
http://www.oracle.com/technetwork/java/javase/downloads/jdk8-arm-downloads-2187472.html<br>
Click "Accept" and download the TAR.GZ.<br> 
<br>
$ cd ~/Downloads
$ sudo tar zxvf ~/Downloads/jdk-8u6-linux-arm-vfp-hflt.tar.gz -C /opt<br>
<br>
$ sudo update-alternatives --install /usr/bin/javac javac /opt/jdk1.8.0_06/bin/javac 1<br>
$ sudo update-alternatives --install /usr/bin/java java /opt/jdk1.8.0_06/bin/java 1<br>
<br>
$ sudo update-alternatives --config javac<br>
$ sudo update-alternatives --config java<br>
<br>
Verify that the newly installed version is active:<br>
$ java -version<br>
$ javac -version


Prepare your development machine
================================
- Install the latest JDK 8 http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
- Install IntelliJ or any other IDE
- Install lein
$ mkdir -p ~/bin<br>
$ cd ~/bin<br>
$ wget https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein<br>
$ chmod 755 lein<br>
<br>
Make sure that ~/bin is in your PATH (e.g. in ~/profile). Test it with:<br>
$ lein<br>

- Clone this repo and setup IntelliJ:<br>
$ mkdir ~/work<br>
$ cd ~/work<br>
$ git clone https://github.com/oshyshko/car.git<br>

- Generate POM for IntelliJ:<br>
$ cd ~/work/car<br>
$ lein pom<br>

- Open IntelliJ and import Maven project from 'car/pom.xml'

- Enable password-less SSH access to your Pi<br>
If you haven't generated your private/public SSH keys yet (e.g. for Github), do:<br>
$ ssh-keygen -t rsa<br>
<br>
NOTE: replace PI_HOST with the actual IP:
<br>
$ ssh pi@PI_HOST mkdir -p .ssh<br>
$ cat ~/.ssh/id_rsa.pub | ssh pi@PI_HOST 'cat >> .ssh/authorized_keys'<br>
<br>
Now you should be able to SSH into your Pi without typing password every time. Test it with:<br>
$ ssh pi@$PI_HOST<br>
Ctrl+D to leave


Override default PI_HOST and other variables
--------------------------------------------
$ cd ~/work/car<br>
$ touch .setvars.sh

Edit '.setvars.sh' to override with your values, e.g.:<br>
PI_HOST=192.168.1.21

IMPORTANT: don't commit this file, it's for your local use only.


Building locally, then running remotely on PI
---------------------------------------------
$ cd ~/work/car<br>
$ ./deploy.sh
<br>


Other stuff
-----------
$ cd ~/work/car<br>
$ ./uberjar.sh                       
$ ./remote_shutdown.sh<br>
$ ./remote_restart.sh
