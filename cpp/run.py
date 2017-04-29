#!/usr/bin/python
from subprocess import Popen, PIPE, STDOUT
from multiprocessing import Pool
import sys
import time

LIST_SOLUTIONS = 'ls -1 | grep ^S...[.]cpp$'
COMPILE = 'g++ -std=c++11 -Werror -I libs -O3 {0} -o out/{1}.out'
RUN = './out/{0}.out'

# Append answers as they are solved
ANS = ["233168", "4613732", "6857", "906609", "232792560", "25164150", "104743", "23514624000", "31875000",\
        "142913828922", "70600674", "76576500", "5537376230", "837799", "137846528820", "1366", "21124", "1074",\
        "171", "648", "31626", "871198282", "4179871", "2783915460", "4782", "983", "-59231", "669171001",\
        "9183", "443839", "73682", "45228"]

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
    output = "{0}: {1} ({2}ms)".format(idx + 1, ans, total_time_ms)
    if idx >= len(ANS):
        output += " * UNVERIFIED *"
    elif ans != ANS[idx]:
        output += " * INCORRECT *"

    print output
