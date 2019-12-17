package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
)

func main() {
	y := readInt() - 1
	x := readInt() - 1
	w := read()
	c := make([][]int, 9)
	for i := 0; i < 9; i++ {
		c[i] = make([]int, 9)
		s := read()
		for j := 0; j < 9; j++ {
			x, _ := strconv.Atoi(s[j : j+1])
			c[i][j] = x
		}
	}

	var p, q int
	if w == "R" {
		p = 0
		q = 1
	} else if w == "L" {
		p = 0
		q = -1
	} else if w == "U" {
		p = -1
		q = 0
	} else if w == "D" {
		p = 1
		q = 0
	} else if w == "RU" {
		p = -1
		q = 1
	} else if w == "RD" {
		p = 1
		q = 1
	} else if w == "LU" {
		p = -1
		q = -1
	} else { // w == "LD"
		p = 1
		q = -1
	}

	a := make([]int, 0)
	for i := 0; i < 4; i++ {
		a = append(a, c[x][y])
		if !(0 <= x+p && x+p < 9) {
			p *= -1
		}
		if !(0 <= y+q && y+q < 9) {
			q *= -1
		}
		x += p
		y += q
	}

	fmt.Printf("%d%d%d%d\n", a[0], a[1], a[2], a[3])
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
