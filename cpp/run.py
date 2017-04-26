#!/usr/bin/python
from subprocess import Popen, PIPE, STDOUT
import time

LIST_SOLUTIONS = 'ls -1 | grep ^S...[.]cpp$'
COMPILE = 'g++ -O3 {0} -o out/{1}.out'
RUN = './out/{0}.out'

def shell(cmd):
    return [line[:-1] for line in\
        Popen(cmd, shell=True, stdout=PIPE, stderr=STDOUT).stdout.readlines()]

SOURCE_EXTS = shell(LIST_SOLUTIONS)
# ensure out directory
shell('mkdir out')

# Compile all sources
print 'Compiling all solutions...'
compile_commands = []
for ext in SOURCE_EXTS:
    name = ext[:-4]
    compile_commands.append(COMPILE.format(ext, name))
shell(' & '.join(compile_commands))

# Run all solutions
print 'Running all solutions...'
for idx in range(len(SOURCE_EXTS)):
    name = SOURCE_EXTS[idx][:-4]
    start = time.time()
    ans = shell(RUN.format(name))[0]
    total_time_ms = int((time.time() - start) * 1000)
    print "{0}: {1} ({2}ms)".format(idx, ans, total_time_ms)
