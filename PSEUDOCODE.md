# Part E — Pseudocode and Algorithm Representation

## Queue

```
PROCEDURE enqueue(student)
    newNode <- create Node(student)
    IF rear IS NULL THEN
        front <- newNode
        rear <- newNode
    ELSE
        rear.next <- newNode
        rear <- newNode
    END IF
    size <- size + 1
END PROCEDURE

PROCEDURE dequeue()
    IF front IS NULL THEN
        PRINT "Queue is empty"
        RETURN NULL
    END IF
    served <- front
    front <- front.next
    IF front IS NULL THEN
        rear <- NULL
    END IF
    size <- size - 1
    RETURN served
END PROCEDURE
```

## Stack (used for push, pop, and postfix evaluation)

```
PROCEDURE push(value)
    IF top = capacity - 1 THEN
        PRINT "Stack overflow"
    ELSE
        top <- top + 1
        items[top] <- value
    END IF
END PROCEDURE

PROCEDURE pop()
    IF top = -1 THEN
        PRINT "Stack underflow"
    ELSE
        value <- items[top]
        top <- top - 1
        RETURN value
    END IF
END PROCEDURE

PROCEDURE evaluatePostfix(expression)
    FOR EACH token IN expression
        IF token IS a number THEN
            push(token)
        ELSE  // token is an operator
            b <- pop()
            a <- pop()
            result <- applyOperator(a, b, token)
            push(result)
        END IF
    END FOR
    RETURN pop()   // final answer is the only value left on the stack
END PROCEDURE
```

## Singly Linked List

```
PROCEDURE insertNode(student, position)
    newNode <- create Node(student)
    IF position <= 1 OR head IS NULL THEN
        newNode.next <- head
        head <- newNode
        RETURN
    END IF
    current <- head
    index <- 1
    WHILE index < position - 1 AND current.next IS NOT NULL
        current <- current.next
        index <- index + 1
    END WHILE
    newNode.next <- current.next
    current.next <- newNode
END PROCEDURE

PROCEDURE deleteNode(studentNo)
    IF head IS NULL THEN
        RETURN false
    END IF
    IF head.data.studentNo = studentNo THEN
        head <- head.next
        RETURN true
    END IF
    current <- head
    WHILE current.next IS NOT NULL AND current.next.data.studentNo != studentNo
        current <- current.next
    END WHILE
    IF current.next IS NULL THEN
        RETURN false   // not found
    END IF
    current.next <- current.next.next
    RETURN true
END PROCEDURE

PROCEDURE searchNode(studentNo)
    current <- head
    WHILE current IS NOT NULL
        IF current.data.studentNo = studentNo THEN
            RETURN current.data
        END IF
        current <- current.next
    END WHILE
    RETURN NULL   // not found
END PROCEDURE

PROCEDURE traverseList()
    current <- head
    WHILE current IS NOT NULL
        PRINT current.data
        current <- current.next
    END WHILE
END PROCEDURE
```

## Sorting Algorithms

```
PROCEDURE selectionSort(arr)
    n <- length(arr)
    FOR i <- 0 TO n - 2
        minIndex <- i
        FOR j <- i + 1 TO n - 1
            IF arr[j] < arr[minIndex] THEN
                minIndex <- j
            END IF
        END FOR
        IF minIndex != i THEN
            SWAP arr[i] AND arr[minIndex]
        END IF
    END FOR
END PROCEDURE

PROCEDURE insertionSort(arr)
    n <- length(arr)
    FOR i <- 1 TO n - 1
        key <- arr[i]
        j <- i - 1
        WHILE j >= 0 AND arr[j] > key
            arr[j + 1] <- arr[j]
            j <- j - 1
        END WHILE
        arr[j + 1] <- key
    END FOR
END PROCEDURE

PROCEDURE mergeSort(arr, left, right)
    IF left >= right THEN
        RETURN   // base case: 0 or 1 element is already sorted
    END IF
    mid <- (left + right) / 2
    mergeSort(arr, left, mid)
    mergeSort(arr, mid + 1, right)
    merge(arr, left, mid, right)
END PROCEDURE

PROCEDURE merge(arr, left, mid, right)
    copy arr[left..right] into temp[left..right]
    i <- left, j <- mid + 1, k <- left
    WHILE i <= mid AND j <= right
        IF temp[i] <= temp[j] THEN
            arr[k] <- temp[i]; i <- i + 1
        ELSE
            arr[k] <- temp[j]; j <- j + 1
        END IF
        k <- k + 1
    END WHILE
    COPY remaining elements of temp[i..mid] and temp[j..right] into arr
END PROCEDURE

PROCEDURE quickSort(arr, low, high)
    IF low < high THEN
        pivotIndex <- partition(arr, low, high)
        quickSort(arr, low, pivotIndex - 1)
        quickSort(arr, pivotIndex + 1, high)
    END IF
END PROCEDURE

PROCEDURE partition(arr, low, high)
    pivot <- arr[high]   // last element chosen as pivot
    i <- low - 1
    FOR j <- low TO high - 1
        IF arr[j] < pivot THEN
            i <- i + 1
            SWAP arr[i] AND arr[j]
        END IF
    END FOR
    SWAP arr[i + 1] AND arr[high]
    RETURN i + 1
END PROCEDURE
```
