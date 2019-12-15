package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
)

func main() {
	n := readInt()
	r := readInt()
	s := read()

	t := make([]bool, n)
	for i := 0; i < n; i++ {
		t[i] = s[i:i+1] == "o"
	}

	right := -1
	for i := n - 1; i >= 0; i-- {
		if !t[i] {
			right = i
			break
		}
	}

	if right == -1 {
		fmt.Println(0)
		return
	}

	c := 0
	for i := 0; i < right-r+1; i++ {
		if !t[i] {
			c++
			for j := 0; j < r; j++ {
				if i+j < n {
					t[i+j] = true
				}
			}
		}
		c++
	}

	ok := true
	for j := 0; j < n; j++ {
		if !t[j] {
			ok = false
			break
		}
	}

	if ok {
		fmt.Println(c)
	} else {
		fmt.Println(c + 1)
	}
}

var stdin *bufio.Scanner

func read() string {
	if stdin == nil {
		stdin = bufio.NewScanner(os.Stdin)
		stdin.Split(bufio.ScanWords)
		stdin.Buffer(make([]byte, bufio.MaxScanTokenSize), int(math.MaxInt32))
	}
	stdin.Scan()
	return stdin.Text()
}

func readInt() int {
	n, _ := strconv.Atoi(read())
	return n
}
