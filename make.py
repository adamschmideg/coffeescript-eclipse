#!/usr/bin/env python
from glob import glob
from subprocess import call
import ConfigParser
import os

bare_jars = [
    'com.google.collect_1.0.0',
    'com.google.inject_2.0.0',
    'com.ibm.icu_4.4.2',
    'de.itemis.xtext.antlr_2.0.0',
    'org.antlr.generator_3.2.0',
    'org.antlr.runtime_3.2.0',
    'org.apache.commons.cli_1.2.0',
    'org.apache.commons.logging_1.0.4',
    'org.apache.log4j_1.2.15',
    'org.eclipse.emf.codegen_2.6.0',
    'org.eclipse.emf.codegen.ecore_2.7.0',
    'org.eclipse.emf.common_2.7.0',
    'org.eclipse.emf.ecore_2.7.0',
    'org.eclipse.emf.ecore.xmi_2.7.0',
    'org.eclipse.emf.mwe2.language_2.1.0',
    'org.eclipse.emf.mwe2.launch_2.1.0',
    'org.eclipse.emf.mwe2.lib_2.1.0',
    'org.eclipse.emf.mwe2.runtime_2.1.0',
    'org.eclipse.emf.mwe.core_1.2.1',
    'org.eclipse.emf.mwe.utils_1.2.1',
    'org.eclipse.xpand_1.1.0',
    'org.eclipse.xtend_1.1.0',
    'org.eclipse.xtend.typesystem.emf_1.0.1',
    'org.eclipse.xtext_2.1.0',
    'org.eclipse.xtext.common.types_2.1.0',
    'org.eclipse.xtext.generator_2.1.0',
    'org.eclipse.xtext.ui.codetemplates_2.1.0',
    'org.eclipse.xtext.util_2.1.0',
    'org.eclipse.xtext.xbase_2.1.0',
    'org.eclipse.xtext.xbase.lib_2.1.0',
    'org.eclipse.xtext.xtend2.lib_2.1.0',
]

def find_eclipse_home():
    config = ConfigParser.ConfigParser()
    config.read('config/make.ini')
    return config.get('paths', 'eclipse')

def real_jar(directory, bare_jar):
    """
    Return the actual jar file name in eclipse plugins,
    given its beginning and the directory to be searched
    """
    pattern = os.path.join(directory, '%s.*.jar' % bare_jar)
    found = glob(pattern)
    if found:
        return found[0]
    else:
        print 'Missing %s' % bare_jar
        return None

def compile_grammar(class_path):
    source_dir = 'csep/src-gen'
    src_dirs = ['csep/src-gen', 'csep/src']
    cp = os.pathsep.join(class_path + src_dirs)
    files = [
        'csep/parser/antlr/internal/InternalCoffeeScriptLexer.java',
        'csep/parser/antlr/internal/InternalCoffeeScriptParser.java',
    ]
    real_files = ' '.join(['%s/%s' % (source_dir, f) for f in files])
    raw_cmd = 'javac -cp %s -sourcepath %s %s' % (cp, source_dir, real_files)
    print raw_cmd
    cmd = raw_cmd.split()
    call(cmd)

def main():
    eclipse_home = find_eclipse_home()
    plugins_dir = os.path.join(eclipse_home, 'plugins')
    jars = [real_jar(plugins_dir, bare) for bare in bare_jars]
    compile_grammar(jars)

if __name__ == '__main__':
    main()
