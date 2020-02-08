name=dbAPI
bin=bin
build=build
src=src/$(name)

jar=$(bin)/$(name).jar
classpath=$(build)/$(name)
class=$(classpath)/*.class

.PHONY: all build compile clean edit

$(jar): $(class)
	if [ -e .android ]; then \
		dx --dex --no-strict --verbose --output=$(jar) $(build); \
	else \
		jar -cvf $(jar) -C $(build) .; \
	fi
$(classpath)/%.class: $(src)/%.java
	if [ -e .android ]; then \
		ecj $< -d $(build) -verbose; \
	else \
		javac $< -d $(build) -cp $(build) -parameters -verbose; \
	fi
all:
	mkdir -p $(build) $(bin); \
	if [ -e .android ]; then \
		ecj $(src)/*.java -d $(build) -verbose; \
		dx $(build) --dex --no-strict --output=$(jar) --verbose; \
	else \
		javac $(src)/*.java -d $(build) -parameters -verbose; \
		jar -cvf $(jar) -C $(build) .; \
	fi
build: $(class)
compile: $(jar)
clean:
	rm -rv $(bin)/* $(build)/*
edit:
	vim Makefile
