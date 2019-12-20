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
	ans := math.Inf(-1)
	for i := 0; i < n; i++ {
		a := readFloat64()
		b := readFloat64()
		c := readFloat64()
		d := readFloat64()
		e := readFloat64()
		ans = math.Max(ans, a+b+c+d+e*110/900)
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

func readFloat64() float64 {
	n, _ := strconv.ParseFloat(read(), 64)
	return n
}

func readInt() int {
	n, _ := strconv.Atoi(read())
	return n
}
