#TODO: devel/all.jar gets regenerated every time
#TODO: `source/config/make.config` doesn't work

JARS=/org.apache.log4j_1.2.15.v201012070815.jar\
/org.eclipse.xtext_2.1.0.v201111010612.jar\
/org.eclipse.emf.ecore.xmi_2.7.0.v20110520-1406.jar\
/org.eclipse.emf.ecore_2.7.0.v20110912-0920.jar\
/org.eclipse.emf.common_2.7.0.v20110912-0920.jar\
/org.eclipse.xtext.util_2.1.0.v201111010612.jar\
/com.google.collect_1.0.0.v201105210816.jar\
/com.google.inject_2.0.0.v201105231817.jar\
/org.antlr.runtime_3.2.0.v201101311130.jar\
/org.eclipse.emf.mwe.core_1.2.1.v201110310946.jar\
/org.apache.commons.cli_1.2.0.v201105210650.jar\
/org.eclipse.emf.mwe2.runtime_2.1.0.v201110310946.jar\
/org.eclipse.emf.mwe.utils_1.2.1.v201110310946.jar\
/org.eclipse.xtext.generator_2.1.0.v201111010612.jar\
/org.eclipse.xtend_1.1.0.v201108020519.jar\
/com.ibm.icu_4.4.2.v20110208.jar\
/org.eclipse.xpand_1.1.0.v201108020519.jar\
/org.eclipse.xtend.typesystem.emf_1.0.1.v201108020519.jar\
/org.eclipse.emf.codegen.ecore_2.7.0.v20110913-1156.jar\
/org.eclipse.emf.codegen_2.6.0.v20110913-1156.jar\
/de.itemis.xtext.antlr_2.0.0.v201108011202.jar\
/org.antlr.generator_3.2.0.v201108011202.jar\
/org.eclipse.emf.mwe2.lib_2.1.0.v201110310946.jar\
/org.eclipse.xtext.xbase.lib_2.1.0.v201111010612.jar\
/org.eclipse.xtext.xtend2.lib_2.1.0.v201111010612.jar\
/org.eclipse.emf.mwe2.launch_2.1.0.v201111010714.jar\
/org.eclipse.emf.mwe2.language_2.1.0.v201111010714.jar\
/org.eclipse.xtext.common.types_2.1.0.v201111010612.jar\
/org.eclipse.xtext.xbase_2.1.0.v201111010612.jar\
/org.apache.commons.logging_1.0.4.v201101211617.jar\
/org.eclipse.xtext.ui.codetemplates_2.1.0.v201111010612.jar

convert: devel/all.jar
	cd csep; java -jar ../devel/all.jar src/csep/GenerateCoffeeScript.mwe2

init: devel/lib

config:
	source config/make.config

devel/lib:
	mkdir -p devel/lib

devel/all.jar: init devel/manifest devel/copyjars.sh
	sh devel/copyjars.sh
	jar cvfm devel/all.jar devel/manifest -C csep/bin .

devel/manifest:
	echo 'Main-Class: org.eclipse.emf.mwe2.launch.runtime.Mwe2Launcher' > devel/manifest
	echo "Class-Path: ${JARS}" | sed -e 's| | \n |g' -e "s|/|lib/|g" >> devel/manifest

devel/copyjars.sh: 
	echo ${JARS} | sed 's/ /\n/g' | sed -e "s|^|cp ${ECLIPSE_HOME}/plugins/|" -e 's|.jar|.jar devel/lib|' > devel/copyjars.sh


