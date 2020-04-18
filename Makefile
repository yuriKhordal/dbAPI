name=dbAPI
bin=bin
build=build
source=src

jar=$(bin)/$(name).jar
src=$(source)/$(name)
classpath=$(build)/$(name)
class=$(patsubst $(src)/%.java, $(classpath)/%.class, $(wildcard $(src)/*.java) )

.PHONY: all build compile clean doc edit

$(jar): $(class)
	if [ -e .android ]; then \
		dx --dex --no-strict --verbose --output=$(jar) $(build); \
	else \
		jar -cvf $(jar) -C $(build) .; \
	fi
$(classpath)/%.class: $(src)/%.java
	if [ -e .android ]; then \
		ecj $< -d $(build) -classpath $(build) -sourcepath $(source) -parameters -verbose; \
	else \
		javac $< -d $(build) -classpath $(build) -sourcepath $(source) -parameters -verbose; \
	fi
all:
	mkdir -p $(build) $(bin); \
	if [ -e .android ]; then \
		ecj $(src)/*.java -d $(build) -classpath $(build) -sourcepath $(source) -parameters -verbose; \
		dx $(build) --dex --no-strict --output=$(jar) --verbose; \
	else \
		javac $(src)/*.java -d $(build) -classpath $(build) -sourcepath $(source) -parameters -verbose; \
		jar -cvf $(jar) -C $(build) .; \
		make doc; \
	fi
build: $(class)
compile: $(jar)
clean:
	rm -rv $(bin)/* $(build)/*
doc:
	javadoc dbAPI -d doc -sourcepath $(source) -link https://docs.oracle.com/en/java/javase/14/docs/api -source 8 -private -verbose; \
	pushd doc; zip -ruv ../doc.zip *; popd
edit:
	vim Makefile
