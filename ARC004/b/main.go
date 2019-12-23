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
	d := make([]int, n)
	for i := 0; i < n; i++ {
		d[i] = readInt()
	}

	sum := 0
	for _, v := range d {
		sum += v
	}

	mx := 0
	for _, v := range d {
		mx = max(mx, v)
	}

	fmt.Println(sum)
	fmt.Println(max(0, mx-(sum-mx)))
}

func max(x int, y int) int {
	if x < y {
		return y
	} else {
		return x
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
