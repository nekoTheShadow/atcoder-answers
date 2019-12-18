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
	t := make([]int, n)
	for i := 0; i < n; i++ {
		t[i] = readInt()
	}

	for i := 2; i < n; i++ {
		s := t[i-2] + t[i-1] + t[i]
		if s < k {
			fmt.Println(i + 1)
			return
		}
	}
	fmt.Println(-1)
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
