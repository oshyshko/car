Setting up development environment
==================================
- Install Oracle Java 8 on your machine

- Install Oracle Java 8 on Pi machine

- Install lein:

$ mkdir -p ~/bin
$ cd ~/bin
$ wget https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein
$ chmod 755 lein
$ ./lein

Then add ~/bin to your PATH (e.g. alter ~/profile)


- Set password-less SSH

On your machine (replace <PI-HOST> with actual IP):

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


- Override default variables with yours:
$ cd car
$ touch .setvars.sh

Edit '.setvars.sh' to override with your values, e.g.:
PI_USER=pi
PI_HOST=192.168.1.20

Don't commit this file, it's for your machine only.


- Building
$ cd car
$ ./uberjar.sh

Pick result from 'target/car-0.1.0-SNAPSHOT-standalone.jar'.


- Building and running on PI via SSH
$ cd car
$ ./deploy.sh


- Other stuff
$ cd car
$ ./remote_shutdown.sh
$ ./remote_restart.sh
