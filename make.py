#!/usr/bin/env python
from glob import glob
from subprocess import call
import ConfigParser
import os
from string import Template

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
    return user_config().get('paths', 'eclipse')

def global_config():
    cfg = ConfigParser.ConfigParser()
    cfg.read('config/global.ini')
    return cfg

def user_config():
    cfg = ConfigParser.ConfigParser()
    cfg.read('config/user.ini')
    return cfg

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

def compile_grammar(class_path, prj, grammar):
    params = dict(prj=prj, grammar=grammar)
    src_gen = '{prj}/src-gen'.format(**params)
    src_dir = '{prj}/src'.format(**params)
    out_dir = '{prj}/bin'.format(**params)
    params['src_gen'] = src_gen
    real_class_path = os.pathsep.join(class_path + [src_gen, src_dir])
    lexer = '{src_gen}/{prj}/parser/antlr/internal/Internal{grammar}Lexer.java'.format(**params)
    parser = '{src_gen}/{prj}/parser/antlr/internal/Internal{grammar}Parser.java'.format(**params)
    raw_cmd = '''
        javac -cp {class_path} -d {out_dir} -sourcepath {source_path} {lexer} {parser}
    '''
    compile_params = {
        'class_path': real_class_path,
        'out_dir': out_dir,
        'source_path': src_dir,
        'lexer': lexer,
        'parser': parser
    }
    cmd = raw_cmd.format(**compile_params).split()
    call(cmd)

def call_antlrworks(antlrworks_jar, class_path, prj, grammar):
    """
    Set up classpath and call antlrworks to debug grammar
    """
    prj_bin = '{prj}/bin'.format(prj=prj)
    real_class_path = os.pathsep.join(class_path + [prj_bin, antlrworks_jar])
    main_class = 'org.antlr.works.IDE'
    grammar_file = '{prj}/src-gen/{prj}/parser/antlr/internal/Internal{grammar}.g'.format(prj=prj, grammar=grammar)
    print grammar_file
    raw_cmd = '''
        java -cp {classpath} {main_class} -open {grammar_file}
    '''
    cmd = raw_cmd.format(\
        classpath=real_class_path,
        main_class=main_class,
        grammar_file=grammar_file).split()
    call(cmd)

def main():
    eclipse_home = find_eclipse_home()
    antlrworks_jar = user_config().get('paths', 'antlrworks')
    cfg = global_config()
    plugins_dir = os.path.join(eclipse_home, 'plugins')
    jars = [real_jar(plugins_dir, bare) for bare in bare_jars]
    prj_name = cfg.get('project', 'name')
    prj_grammar = cfg.get('project', 'grammar')
    compile_grammar(jars, prj_name, prj_grammar)
    call_antlrworks(antlrworks_jar, jars, prj_name, prj_grammar)

if __name__ == '__main__':
    main()
