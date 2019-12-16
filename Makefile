bin=bin
build=build
src=src/com/yuri/dbAPI
name=dbAPI.jar
jar=$(bin)/$(name)
class=$(build)/*.class

.PHONY: build compile clean

$(build)/%.class: $(src)/%.java
		javac $(src)/$< -d $(build)

$(jar): $(class)
	if [[ -e .android ]]
		dx --dex --no-strict --output=$(jar) $(class)
	else
		jar -cvf $(jar) $(class)
	fi

build: $(class)
compile: $(jar)
clean:
	rm -rv $(bin)/* $(build)/*
