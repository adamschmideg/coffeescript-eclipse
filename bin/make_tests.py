
"""
Create test cases from coffee script files.
This is probably throw-away code, but who knows...

Note: ''' in source is not handled
"""
import itertools
import os

def indent(text, count):
    "Indent `text` with `count` spaces"
    return ' ' * count + text

def trim_list(list):
    "Trim false elements on both ends"
    ltrimmed = [x for x in itertools.dropwhile(lambda x: not x, list)]
    ltrimmed.reverse()
    trimmed = [x for x in itertools.dropwhile(lambda x: not x, ltrimmed)]
    trimmed.reverse()
    return trimmed

def one_case(dir, fname):
    "Read `fname` from `dir` and create a single test case from it"
    f = os.path.join(dir, fname)
    basename = os.path.splitext(fname)[0]
    with open(f, 'r') as file:
        lines = trim_list([line.rstrip() for line in file.readlines()])
        content = '\n'.join([indent(line, 8) for line in lines])
    template = """
    @Test
    def void test_{name}() {{
      ok('''
{content}
      ''')
    }}
    """
    case = template.format(name=basename, content=content)
    return case

def main(dir):
    "Return a list of all test cases generated from the files in `dir`"
    cases = [one_case(dir, fname) for fname in os.listdir(dir)]
    return '\n'.join(cases)

if __name__ == '__main__':
    import sys
    print main(sys.argv[1])
