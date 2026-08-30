# Local grading

Run all JUnit tests from the `hw01` directory:

```sh
./grade.sh
```

Run one or more specific test classes:

```sh
./grade.sh ArithmeticTest
```

The score is the percentage of discovered JUnit test methods that pass. The
script exits with status `0` only when every test passes, `1` for a compilation
or test failure, and `2` when setup is missing or no tests are found.

This is a local feedback tool. It cannot reproduce unavailable Gradescope
hidden tests or the course's official point weights. Add edge-case tests under
`tests/` to make the local score more useful.

## Discussion exercises

Run the Java and Python exercises under `disc/` together:

```sh
./disc/grade.sh
```

The Java and Python portions are each worth 50 local points. A portion earns
its 50 points when all of its local tests pass. The tests live in
`disc/DiscTest.java` and `disc/test_disc.py`, so they can be extended as new
discussion exercises are added.
