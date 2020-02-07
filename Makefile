bin=bin
build=build
src=src/com/yuri/dbAPI
name=dbAPI.jar
jar=$(bin)/$(name)
class=$(build)/*.class

.PHONY: all build compile clean

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
all:
	if [ -e .android ]; then \
		ecj $(src)/*.java -d $(build) -verbose; \
		dx $(build) --dex --no-strict --output=$(jar) --verbose; \
	else \
		javac $(src)/*.java -d $(build) -verbose \
		jar $(build) -cvf $(jar) --verbose \
	fi
build: $(class)
compile: $(jar)
clean:
	rm -rv $(bin)/* $(build)/*
