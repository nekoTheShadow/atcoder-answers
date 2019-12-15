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
	s := make([]string, n)
	for i := 0; i < n; i++ {
		s[i] = read()
	}

	r := 0
	b := 0
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			c := s[i][j : j+1]
			if c == "R" {
				r++
			}
			if c == "B" {
				b++
			}
		}
	}

	if r > b {
		fmt.Println("TAKAHASHI")
	} else if r < b {
		fmt.Println("AOKI")
	} else {
		fmt.Println("DRAW")
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
