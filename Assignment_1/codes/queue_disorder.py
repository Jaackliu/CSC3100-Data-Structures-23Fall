
def merge_sort(sort_height):
    if len(sort_height) == 1:
        return
    else:
        mid = len(sort_height)//2
        left = sort_height[:mid]
        right = sort_height[mid:]

        merge_sort(left)
        merge_sort(right)
        sorted_merge(left, right, sort_height)


def sorted_merge(left, right, sort_arr):
    global count
    left_order = right_order = sort_arr_order = 0

    while left_order < len(left) and right_order < len(right):
        if left[left_order] <= right[right_order]:
            sort_arr[sort_arr_order] = left[left_order]
            left_order += 1
        else:
            count += len(left) - left_order
            sort_arr[sort_arr_order] = right[right_order]
            right_order += 1
        sort_arr_order += 1

    while left_order < len(left):
        sort_arr[sort_arr_order] = left[left_order]
        left_order += 1
        sort_arr_order += 1
    while right_order < len(right):
        sort_arr[sort_arr_order] = right[right_order]
        right_order += 1
        sort_arr_order += 1


def main():
    input_num = int(input())
    heights_list = list()
    input_heights = input().split()
    for i in range(input_num):
        heights_list.append(int(input_heights[i]))

    global count
    count = 0
    merge_sort(heights_list)
    print(count)


main()
