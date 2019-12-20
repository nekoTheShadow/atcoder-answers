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
	k := readInt()
	a := make([]int, n)
	for i := 0; i < n; i++ {
		a[i] = readInt()
	}

	x := 0
	y := k - 1
	ok := 0
	for i := x; i < y; i++ {
		if a[i] < a[i+1] {
			ok++
		}
	}

	c := 0
	for y < n-1 {
		if ok == k-1 {
			c++
		}

		if a[x] < a[x+1] {
			ok--
		}
		if a[y] < a[y+1] {
			ok++
		}
		x++
		y++
	}

	if ok == k-1 {
		c++
	}
	fmt.Println(c)
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

func readFloat64() float64 {
	n, _ := strconv.ParseFloat(read(), 64)
	return n
}

func readInt() int {
	n, _ := strconv.Atoi(read())
	return n
}
