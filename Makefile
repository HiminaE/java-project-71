.DEFAULT_GOAL := build-run

run-dist:
	make -C app run-dist

build:
	make -C app build

clean:
	make -C app clean

test:
	make -C app test

report:
	make -C app report

lint:
	make -C app lint

build-run:
	build run

.PHONY: build
