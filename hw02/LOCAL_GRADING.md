# HW02 本地评分

在 `hw02` 目录运行：

```sh
./grade.sh
```

评分器只需要 JDK，不依赖 Gradescope、JUnit 或网络。它会编译 `src/` 中的四个
作业文件，然后按以下四部分各计 25 分：

- `StarTriangle5.starTriangle5`
- `StarTriangleN.starTriangle`
- `PrintIndexed.printIndexed`
- `DoubleUp.doubleUp`

输出比较是严格的：空格、换行以及方法是打印还是返回都会被检查。每部分包含题目
示例和额外边界用例；部分用例通过时可以获得该部分的部分分数。满分时脚本退出码为
`0`，编译或测试失败时为 `1`，便于在终端和自动化脚本中使用。

这是依据 [Spring 2026 HW02 题目](https://sp26.datastructur.es/homeworks/hw02/)
制作的非官方自测工具。它无法保证与 Gradescope 的隐藏测试和官方权重完全一致，
但覆盖了当前题目公开要求以及常见边界情况。
