package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
)

func main() {
	a := readInt64()
	b := readInt64()
	c := gcd(a, b)
	fmt.Println(a * b / c)
}

func gcd(x, y int64) int64 {
	if y == 0 {
		return x
	}

	if x > y {
		return gcd(y, x%y)
	} else {
		return gcd(y, x)
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

func readInt64() int64 {
	n, _ := strconv.ParseInt(read(), 10, 64)
	return n
}

func readFloat64() float64 {
	n, _ := strconv.ParseFloat(read(), 64)
	return n
}
