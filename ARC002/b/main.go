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
	t, _ := time.Parse("2006/01/02", read())
	for !ok(t) {
		t = t.AddDate(0, 0, 1)
	}
	fmt.Println(t.Format("2006/01/02"))
}

func ok(t time.Time) bool {
	y := t.Year()
	m := int(t.Month())
	d := t.Day()
	return y%m == 0 && (y/m)%d == 0
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
