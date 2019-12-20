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
	for x := 2; x*x <= n; x++ {
		if n%x == 0 {
			fmt.Println("NO")
			return
		}
	}
	fmt.Println("YES")
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
