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
	w := make([]string, n)
	for i := 0; i < n; i++ {
		w[i] = read()
	}
	d := map[string]bool{
		"TAKAHASHIKUN":  true,
		"Takahashikun":  true,
		"takahashikun":  true,
		"TAKAHASHIKUN.": true,
		"Takahashikun.": true,
		"takahashikun.": true,
	}

	c := 0
	for _, k := range w {
		if _, ok := d[k]; ok {
			c++
		}
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

func readInt() int {
	n, _ := strconv.Atoi(read())
	return n
}
