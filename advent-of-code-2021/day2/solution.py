def main():
    print("Part one: ", part_one())
    print("Part two: ", part_two())

def part_one():
    position = 0; depth = 0

    with open("input.txt") as file:
        while (line := file.readline().rstrip()):
            firstChar = line[0]
            lastChar = line[-1]
            value = int(lastChar)

            if (firstChar == 'f'):
                position += value
            elif (firstChar == 'd'):
                depth += value
            elif (firstChar == 'u'):
                depth -= value
            else:
                print("Unexpected first character encountered.")
    
    return position * depth

def part_two():
    position = 0; depth = 0; aim = 0

    with open("input.txt") as file:
        while (line := file.readline().rstrip()):
            firstChar = line[0]
            lastChar = line[-1]
            value = int(lastChar)

            if (firstChar == 'f'):
                position += value
                depth += (aim * value)
            elif (firstChar == 'd'):
                aim += value
            elif (firstChar == 'u'):
                aim -= value
            else:
                print("Unexpected first character encountered.")
    
    return position * depth


if __name__ == "__main__":
    main()
