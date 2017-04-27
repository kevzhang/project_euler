#!/usr/bin/python
from subprocess import Popen, PIPE, STDOUT
from multiprocessing import Pool
import sys
import time

LIST_SOLUTIONS = 'ls -1 | grep ^S...[.]cpp$'
COMPILE = 'g++ -Werror -I libs -O3 {0} -o out/{1}.out'
RUN = './out/{0}.out'

def shell(cmd):
    res = Popen(cmd, shell=True, stdout=PIPE, stderr=STDOUT)
    stdout_lines = [line[:-1] for line in res.stdout.readlines()]
    res.communicate()[0]
    return_code = res.returncode
    return {
        "output": stdout_lines,
        "return_code": return_code
    }

SOURCE_EXTS = shell(LIST_SOLUTIONS)['output']
# ensure out directory
shell('mkdir out')

# Compile all sources
print 'Compiling all solutions...'
compile_commands = [COMPILE.format(ext, ext.split('.')[0]) for ext in SOURCE_EXTS]
pool = Pool(16)
results = pool.map(shell, compile_commands)
pool.close()
for result in results:
    if result['return_code'] != 0:
        print '\n'.join(result['output'])
        sys.exit(0)

# Run all solutions
print 'Running all solutions...'
for idx in range(len(SOURCE_EXTS)):
    name = SOURCE_EXTS[idx].split('.')[0]
    start = time.time()
    ans = shell(RUN.format(name))['output'][0]
    total_time_ms = int((time.time() - start) * 1000)
    print "{0}: {1} ({2}ms)".format(idx + 1, ans, total_time_ms)
