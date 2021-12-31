import sys

def main():
    print("Part one: ", part_one())
    print("Part two: ", part_two())

def part_one():
    crabs = []
    maxPos = 0

    with open("input.txt") as file:
        while (line := file.readline().rstrip()):
            currentline = line.split(",")
            crabs = list(map(int, currentline))
        
    for crab in crabs:
        maxPos = max(crab, maxPos)

    minDist = sys.maxsize
    for i in range(0, maxPos):
        currDist = 0
        for dist in crabs:
            currDist += abs(i - dist)
        minDist = min(minDist, currDist)

    return minDist

def part_two():
    distances = [0]
    crabs = []
    maxPos = 0

    with open("input.txt") as file:
        while (line := file.readline().rstrip()):
            currentline = line.split(",")
            crabs = list(map(int, currentline))
        
    for crab in crabs:
        maxPos = max(crab, maxPos)

    minDist = sys.maxsize
    for i in range(0, maxPos):
        currDist = 0
        for dist in crabs:
            currDist += calculate_distance(distances, abs(i - dist))
        minDist = min(minDist, currDist)

    return minDist

def calculate_distance(distances, d):
    if d >= len(distances):
        for i in range(len(distances)-1, d):
            distances.append(0)
        for j in range(1, d+1):
            distances[j] = distances[j-1] + j
        return distances[d]
    else:
        return distances[d]
    

if __name__ == "__main__":
    main()
