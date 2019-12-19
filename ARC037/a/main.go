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
	m := make([]int, n)
	for i := 0; i < n; i++ {
		m[i] = readInt()
	}

	ans := 0
	for _, v := range m {
		if v < 80 {
			ans += 80 - v
		}
	}
	fmt.Println(ans)
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
