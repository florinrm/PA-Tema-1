# Darius-Florentin Neatu <neatudarius@gmail.com>
# Copyright 2018

# ignorati
# Makegfile intern folosit de checker

all: build_verif

build_verif:
	g++ -Wall -lm -O3 ${ONLINE_JUDGE} _utils/verif.cpp -o verif
	javac Frati.java
	javac Ursi.java
	javac Planificare.java
	javac Numaratoare.java
run-p1:
	java Frati
run-p2:
	java Ursi
run-p3:
	java Planificare
run-p4:
	java Numaratoare

clean:
	rm -f verif
