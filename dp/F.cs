using System;
using System.Collections.Generic;
using System.Linq;

class F
{
    public static void Main()
    {
        var s = Console.ReadLine();
        var t = Console.ReadLine();

        var m = s.Count();
        var n = t.Count();
        var dp = new int[m + 1, n + 1];

        for (int x = 1; x <= m; x++)
        {
            for (int y = 1; y <= n; y++)
            {
                if (s[x - 1] == t[y - 1])
                {
                    dp[x, y] = dp[x - 1, y - 1] + 1;
                }
                else
                {
                    dp[x, y] = Math.Max(dp[x - 1, y], dp[x, y - 1]);
                }
            }
        }

        int p = m, q = n;
        var chars = new List<char>();
        while (p > 0 && q > 0)
        {
            if (dp[p, q] == dp[p - 1, q])
            {
                p--;
            }
            else if (dp[p, q] == dp[p, q - 1])
            {
                q--;
            }
            else
            {
                p--;
                q--;
                chars.Add(s[p]);
            }
        }

        chars.Reverse();
        Console.WriteLine(string.Join("", chars));
    }
}