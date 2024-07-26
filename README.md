# lego-problem
HackerRanks legoBlocks problem highlighting rounding errors in Kotlin/Java calculations.
See https://www.youtube.com/watch?v=u9fL52Hryiw for solution theory
This repo also contains files describing the original challenge.

# Instructions:
- Copy the .kt files to a freshly created Kotlin IntelliJ project's test folder and run the test in `Lego`.
- Observe test failures in stdout.
- Comment line 15 and uncomment line 14 then run the test again
- Observe no test failures.

The only difference between the two algorithms is that the first uses `Double` whilst the second uses `BigDecimal`.
Unfortunately, attempting to upload the `BigDecimal` version to HackerRank results in a "Time limit exceeded" error (see BigDecimalError.png)
