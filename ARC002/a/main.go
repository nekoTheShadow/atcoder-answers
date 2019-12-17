package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
	"time"
)

func main() {
	y := readInt()
	t := time.Date(y, time.December, 31, 0, 0, 0, 0, time.Local)
	if t.YearDay() == 365 {
		fmt.Println("NO")
	} else {
		fmt.Println("YES")
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
