def main():
    print("Part one: ", part_one())
    print("Part two: ", part_two())

def part_one():
    lanternfish = []
    NUM_DAYS = 80

    with open("input.txt") as file:
        while (line := file.readline().rstrip()):
            currentline = line.split(",")
            lanternfish = list(map(int, currentline))

    for i in range(0, NUM_DAYS):
        for j in range(0, len(lanternfish)):
            lanternfish[j] -= 1
            if lanternfish[j] == -1:
                lanternfish.append(8)
                lanternfish[j] = 6
            
    return len(lanternfish)

def part_two():
    lanternfish = []
    initState = []
    NUM_DAYS = 256

    with open("input.txt") as file:
        while (line := file.readline().rstrip()):
            currentline = line.split(",")
            initState = list(map(int, currentline))

    for i in range(0, 10):
        pair = [0, 0]
        pair[0] = i-1
        pair[1] = 0
        for j in initState:
            if j == i-1:
                pair[1] += 1
        lanternfish.append(pair)

    for i in range(0, NUM_DAYS):
        for j in range(len(lanternfish)):
            if j != 0:
                lanternfish[j-1][1] = lanternfish[j][1]
        lanternfish[9][1] = 0

        ready = lanternfish[0][1]
        lanternfish[9][1] += ready
        lanternfish[7][1] += ready
        lanternfish[0][1] = 0

    c = 0
    for i in range(len(lanternfish)):
        c += lanternfish[i][1]

    return c


if __name__ == "__main__":
    main()
