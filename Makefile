# Darius-Florentin Neatu <neatudarius@gmail.com>
# Copyright 2018

# ignorati
# Makegfile intern folosit de checker

build:
	javac Frati.java
	javac Ursi.java
	javac Planificare.java
	g++ numaratoare.cpp -o numaratoare
run-p1:
	java Frati
run-p2:
	java Ursi
run-p3:
	java Planificare
run-p4:
	./numaratoare

clean:
	rm -f *.class numaratoare
