def main():
    print("Part one: ", part_one())
    print("Part two: ", part_two())

def part_one():
    answer = 0; prev = 0

    with open("input.txt") as file:
        for line in file:
            curr = int(line)
            if (prev != 0 and curr > prev):
                answer += 1
            prev = curr

    return answer

def part_two():
    nums = []
    answer = 0; c = 0; prevSum = 0

    with open("input.txt") as file:
        for line in file:
            curr = int(line)

            if (len(nums) == 3):
                nums[c % 3] = curr
                sum = nums[0] + nums[1] + nums[2]

                if (sum > prevSum):
                    answer += 1
                
                prevSum = sum
            else:
                nums.append(curr)

            c += 1

    return answer


if __name__ == "__main__":
    main()
