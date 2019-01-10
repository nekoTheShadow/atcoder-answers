using System;
using System.Linq;

class D
{
    public static void Main(string[] args)
    {
        var first = Console.ReadLine().Split(' ').Select(int.Parse).ToList();
        int N = first[0], W = first[1];
        var dp = new long[N + 1, W + 1];
        for (int i = 0; i < N; i++)
        {
            var line = Console.ReadLine().Split(' ').Select(long.Parse).ToList();
            long w = line[0], v = line[1];
            for (int j = 0; j <= W; j++)
            {
                if (dp[i, j] == 0)
                {
                    continue;
                }

                update(dp, i + 1, j, dp[i, j]);
                if (j + w <= W)
                {
                    update(dp, i + 1, j + w, dp[i, j] + v);
                }
            }
            update(dp, i + 1, w, v);
        }

        long ans = long.MinValue;
        for (int i = 0; i <= W; i++)
        {
            ans = Math.Max(ans, dp[N, i]);
        }
        Console.WriteLine(ans);
    }

    private static void update(long[,] dp, long a, long b, long v)
    {
        dp[a, b] = Math.Max(dp[a, b], v);
    }
}