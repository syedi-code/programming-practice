import re

def main():
    print("Part one: ", part_one())
    print("Part two: ", part_two())

def part_one():
    points = {}

    with open("input.txt") as file:
        while (line := file.readline().rstrip()):
            coords = re.split(',| -> |\*|\n', line) # remove whitespace and arrow
            coords = list(map(int, coords)) # convert 'coords' list to int
            x1 = coords[0]
            y1 = coords[1]
            x2 = coords[2]
            y2 = coords[3]

            if x1 == x2:
                yMin = min(y1,y2)
                yMax = max(y1,y2)
                i = 0
                for p in range(yMin, yMax+1):
                    yCurr = yMin + i
                    key = str(x1) + "," + str(yCurr)
                    if key in points:
                        points[key] += 1
                    else:
                        points[key] = 1
                    i += 1

            if y1 == y2:
                xMin = min(x1,x2)
                xMax = max(x1,x2)
                i = 0
                for p in range(xMin, xMax+1):
                    xCurr = xMin + i
                    key = str(xCurr) + "," + str(y1)
                    if key in points:
                        points[key] += 1
                    else:
                        points[key] = 1
                    i += 1

    c = 0
    for point, freq in points.items():
        if freq > 1:
            c += 1

    return c

def part_two():
    points = {}

    with open("input.txt") as file:
        while (line := file.readline().rstrip()):
            coords = re.split(',| -> |\*|\n', line) # remove whitespace and arrow
            coords = list(map(int, coords)) # convert 'coords' list to int
            x1 = coords[0]
            y1 = coords[1]
            x2 = coords[2]
            y2 = coords[3]

            if x1 == x2:
                yMin = min(y1,y2)
                yMax = max(y1,y2)
                i = 0
                for p in range(yMin, yMax+1):
                    yCurr = yMin + i
                    key = str(x1) + "," + str(yCurr)
                    if key in points:
                        points[key] += 1
                    else:
                        points[key] = 1
                    i += 1
            elif y1 == y2:
                xMin = min(x1,x2)
                xMax = max(x1,x2)
                i = 0
                for p in range(xMin, xMax+1):
                    xCurr = xMin + i
                    key = str(xCurr) + "," + str(y1)
                    if key in points:
                        points[key] += 1
                    else:
                        points[key] = 1
                    i += 1
            else:
                xMin = min(x1,x2)
                yMin = min(y1,y2)
                i = 0
                for p in range(xMin, xMax+1):
                    if x1 == xMin:
                        xCurr = x1 + i
                    else:
                        xCurr = x1 - i
                    if y1 == yMin:
                        yCurr = y1 + i
                    else:
                        yCurr = y1 - i

                    key = str(xCurr) + "," + str(yCurr)
                    if key in points:
                        points[key] += 1
                    else:
                        points[key] = 1
                    i += 1

    c = 0
    for point, freq in points.items():
        if freq > 1:
            c += 1

    return c

if __name__ == "__main__":
    main()
