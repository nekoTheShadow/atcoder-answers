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
	h := make([]int, n)
	for i := 0; i < n; i++ {
		h[i] = readInt()
	}

	a := create(n)
	b := create(n)
	for i := 0; i < n-1; i++ {
		if h[i] <= h[i+1] {
			a[i+1] += a[i]
		}
	}
	for i := n - 1; i >= 1; i-- {
		if h[i-1] >= h[i] {
			b[i-1] += b[i]
		}
	}

	ans := 0
	for i := 0; i < n; i++ {
		ans = max(ans, a[i]+b[i]-1)
	}
	fmt.Println(ans)
}

func create(n int) []int {
	a := make([]int, n)
	for i := 0; i < n; i++ {
		a[i] = 1
	}
	return a
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

func max(a int, b int) int {
	if a < b {
		return b
	} else {
		return a
	}
}
