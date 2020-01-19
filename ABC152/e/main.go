package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
)

func exec(stdin *Stdin, stdout *Stdout) {
	n := stdin.ReadInt()
	a := []int{}
	for i := 0; i < n; i++ {
		a = append(a, stdin.ReadInt())
	}

	c := map[int]int{}
	for i := 0; i < n; i++ {
		d := f(a[i])
		for k := range d {
			if _, ok := c[k]; ok {
				c[k] = Max(c[k], d[k])
			} else {
				c[k] = d[k]
			}
		}
	}

	lcm := 1
	for k := range c {
		lcm = (lcm * ModPow(k, c[k])) % MOD
	}

	ans := 0
	for i := 0; i < n; i++ {
		ans = (ans + lcm*ModInv(a[i])) % 1000000007
	}

	stdout.Println(ans)
}

func f(x int) map[int]int {
	primes := map[int]int{}
	for i := 2; i*i <= x; i++ {
		for x%i == 0 {
			if _, ok := primes[i]; ok {
				primes[i]++
			} else {
				primes[i] = 1
			}
			x /= i
		}
	}

	if x > 1 {
		if _, ok := primes[x]; ok {
			primes[x]++
		} else {
			primes[x] = 1
		}
	}

	return primes
}

func Gcd(x, y int) int {
	if x < y {
		x, y = y, x
	}

	for y > 0 {
		x, y = y, x%y
	}

	return x
}

func Lcm(x, y int) int {
	return x * y / Gcd(x, y) % MOD
}

func Max(a ...int) int {
	v := a[0]
	for i := 1; i < len(a); i++ {
		if v < a[i] {
			v = a[i]
		}
	}
	return v
}

const MOD = 1000000007

func ModPow(x, y int) int {
	z := 1
	for y > 0 {
		if y%2 == 0 {
			x = (x * x) % MOD
			y /= 2
		} else {
			z = (z * x) % MOD
			y--
		}
	}
	return z
}

func ModInv(x int) int {
	return ModPow(x, MOD-2)
}

func main() {
	stdout := NewStdout()
	defer stdout.Flush()
	exec(NewStdin(bufio.ScanWords), stdout)
}

type Stdin struct {
	stdin *bufio.Scanner
}

func NewStdin(split bufio.SplitFunc) *Stdin {
	s := Stdin{bufio.NewScanner(os.Stdin)}
	s.stdin.Split(split)
	s.stdin.Buffer(make([]byte, bufio.MaxScanTokenSize), int(math.MaxInt32))
	return &s
}

func (s *Stdin) Read() string {
	s.stdin.Scan()
	return s.stdin.Text()
}

func (s *Stdin) ReadInt() int {
	n, _ := strconv.Atoi(s.Read())
	return n
}

func (s *Stdin) ReadFloat64() float64 {
	n, _ := strconv.ParseFloat(s.Read(), 64)
	return n
}

type Stdout struct {
	stdout *bufio.Writer
}

func NewStdout() *Stdout {
	return &Stdout{bufio.NewWriter(os.Stdout)}
}

func (s *Stdout) Flush() {
	s.stdout.Flush()
}

func (s *Stdout) Println(a ...interface{}) {
	fmt.Fprintln(s.stdout, a...)
}
