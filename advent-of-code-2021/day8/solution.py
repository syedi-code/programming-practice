import re

def main():
    # print("Part one: ", part_one())
    print("Part two: ", part_two())

def part_one():
    c = 0

    with open("input.txt") as file:
        while (line := file.readline().rstrip()):
            currentline = re.split(r"[|\s]", line)
            currentline = currentline[-4:]

            for digit in currentline:
                if len(digit) == 2 or len(digit) == 3 or len(digit) == 4 or len(digit) == 7:
                    c += 1

    return c

def part_two():
    c = 0
    with open("input.txt") as file:
        while (line := file.readline().rstrip()):
            currentline = re.split(r"[|\s]", line)
            patterns = currentline[:-6]
            output = currentline[-4:]

            digits = {
                0: "",
                1: "",
                2: "",
                3: "",
                4: "",
                5: "",
                6: "",
                7: "",
                8: "",
                9: ""
            }
            discoveredChars = []

            # Seed digits table with given patterns
            for pattern in list(patterns):
                discovered = False
                if len(pattern) == 2: digits[1] = pattern; discovered = True
                if len(pattern) == 3: digits[7] = pattern; discovered = True
                if len(pattern) == 4: digits[4] = pattern; discovered = True
                if len(pattern) == 7: digits[8] = pattern; patterns.remove(pattern)

                if discovered == True:
                    for char in pattern:
                        discoveredChars.append(char)
                    patterns.remove(pattern)
            discoveredChars = set(discoveredChars)

            # Find 9
            # 9 is a superset of all the currently discovered characters
            for pattern in list(patterns):
                if len(pattern) == 6:
                    if (discoveredChars.issubset(pattern)):
                        digits[9] = pattern
                        patterns.remove(pattern)
            
            # Find 0 and 6
            # 0 is a superset of 7
            # 6 is not
            for pattern in list(patterns):
                if len(pattern) == 6:
                    if set(pattern).issuperset(set(digits[7])):
                        digits[0] = pattern
                        patterns.remove(pattern)
                    else:
                        digits[6] = pattern
                        patterns.remove(pattern)
            
            # Find 3, 5, 2
            # 3 is a superset of 7, and the remaining digits are not
            # 5 is a subset of 9
            # 2 is neither
            for pattern in list(patterns):
                if set(pattern).issuperset(set(digits[7])):
                    digits[3] = pattern
                    patterns.remove(pattern)

            for pattern in list(patterns):
                if set(pattern).issubset(set(digits[9])):
                    digits[5] = pattern
                    patterns.remove(pattern)
                else:
                    digits[2] = pattern
                    patterns.remove(pattern)

            out = ""
            for value in output:
                for digit, pattern in digits.items():
                    if set(value) == set(pattern):
                        out += str(digit)

            out = int(out)
            c += out

    return c
    

if __name__ == "__main__":
    main()
