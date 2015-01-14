# Problem Description page: https://www.hackerrank.com/challenges/quicksort3
# Short Summary: Create an in-place version of quicksort, with the pivot at the end of the array. Print out the array at each stage of sorting.

# partition method: takes in indices that indicate which subarray to sort. 
# It then sorts that array and print out the result at the end, before recursively calling on itself if the subarray is less than 3 elements long. 
def partition (start, end) : # the subarray goes from start UP TO AND INCLUDING end
    global ar
    pivot = ar [end]      # pivot is the end iem
    pivot_pointer = None
    if (end - start + 1) == 2: # there are only two items 
        if ar [start] > pivot:    # we switch the two items if the first item is larger
            start_item = ar[start]
            ar[start] = pivot
            ar[end] = start_item
    else:
        # start splitting the subarray into two parts smaller and larger than the pivot
        larger_pointer = end + 1  # starting point of the larger partition
        for i in range(start, end):
            if ar[i] < pivot:   # item is smaller than pivot
                if i > larger_pointer:
                    # swap i with larger_pointer, assign larger_pointer to the item one ahead
                    smaller = ar[i] 
                    larger = ar[larger_pointer]
                    ar[i] = larger
                    ar[larger_pointer] = smaller
                    larger_pointer = larger_pointer + 1 
            else:               # item is larger than pivot - we move the larger_pointer to this item if appropriate
                if i < larger_pointer: 
                    larger_pointer = i
         
        # swap pivot with larger_pointer item
        if larger_pointer == end + 1: # if larger_pointer has not changed
            pivot_pointer = end       # pivot pointer won't change either
        else:
            larger = ar[larger_pointer]
            ar[larger_pointer] = pivot
            ar[end] = larger
            pivot_pointer = larger_pointer
    print_ar()
    
    if pivot_pointer != None and (pivot_pointer - 1 - start + 1) > 1:
        partition(start, pivot_pointer - 1) # left partition
    if pivot_pointer != None and (end - (pivot_pointer + 1) + 1) > 1: 
        partition(pivot_pointer + 1, end) # right partition

def print_ar():
    global ar
    s = ""
    for i in range(0, len(ar)):
        s += str(ar[i])
        if i < len(ar) - 1:
            s += " "
    print(s)
    
def inplace_quick_sort(n):
    global ar
    if n == 1:
        print_ar()
    else:
        partition (0, n-1)
    
if __name__ == "__main__":
    n = int(input())
    global ar
    ar = []
    for i in input().split(" "):
        ar.append(int(i))
    inplace_quick_sort(n)
