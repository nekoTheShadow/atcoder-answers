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

	a := make([]int, 0)
	for x := max(n-9*18, 0); x <= n; x++ {
		if x+f(x) == n {
			a = append(a, x)
		}
	}

	fmt.Println(len(a))
	for _, v := range a {
		fmt.Println(v)
	}
}

func f(x int) int {
	y := 0
	for x > 0 {
		y += x % 10
		x /= 10
	}
	return y
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

func max(x, y int) int {
	if x < y {
		return y
	} else {
		return x
	}
}
