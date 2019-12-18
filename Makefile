bin=bin
build=build
src=src/com/yuri/dbAPI
name=dbAPI.jar
jar=$(bin)/$(name)
class=$(build)/*.class

.PHONY: build compile clean

$(build)/%.class: $(src)/%.java
	if [ -e .android ]; then \
		ecj $< -d $(build) -verbose; \
	else \
		javac $< -d $(build) -verbose; \
	fi
$(jar): $(class)
	if [ -e .android ]; then \
		dx --dex --no-strict --verbose --output=$(jar) $(build); \
	else \
		jar --verbose -cvf $(jar) $(build); \
	fi
build: $(class)
compile: $(jar)
clean:
	rm -rv $(bin)/* $(build)/*
