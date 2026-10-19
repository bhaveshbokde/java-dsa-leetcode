# Java DSA Mastery

Data structures, algorithms and interview problems, implemented and tested in
Java 21. **71 problems** across **33 topic areas**, every one
with a compilable solution and a JUnit test class.

This is a study repository rather than a library. The value is meant to be in
the reasoning: each solution carries a comment explaining *why* that algorithm,
what the straightforward alternative costs, and which detail would be easy to
get wrong.

[![CI](https://github.com/bhaveshbokde/java-dsa-mastery/actions/workflows/ci.yml/badge.svg)](https://github.com/bhaveshbokde/java-dsa-mastery/actions/workflows/ci.yml)

---

## What is here

| | |
|---|---|
| Problems implemented | **71** |
| Topic areas | **33** |
| From LeetCode | 67 |
| Classic algorithms / Java exercises | 4 |
| Tests | **533**, all passing |
| Line coverage | **97.6%** (measured by JaCoCo, not estimated) |
| Checkstyle violations | 0 |

Full listing with IDs, difficulty, patterns and file paths:
**[PROBLEM-INDEX.md](PROBLEM-INDEX.md)**

### Difficulty mix

| Easy | Medium | Hard |
|---|---|---|
| 19 | 49 | 6 |

Difficulty follows LeetCode where a problem ID exists. A few entries cover two
related problems with one implementation (Linked List Cycle I and II, Course
Schedule I and II, Jump Game I and II, House Robber I and II, Single Number and
its two-unique variant), and those list both labels.

---

## Requirements

| | Version | Notes |
|---|---|---|
| Java | **21** | `java -version`. The POM targets release 21. |
| Maven | — | Not required: use the committed `./mvnw` wrapper. |

No database, container, broker or network access is needed. The only runtime
dependency is the JDK; JUnit 5 is the sole test dependency.

## Build and test

```bash
./mvnw clean test       # compile, run Checkstyle, run all 533 tests
./mvnw clean verify     # the above, plus the JaCoCo coverage report
./mvnw clean package    # build the jar
```

Run a single test class or method:

```bash
./mvnw test -Dtest=TwoSumTest
./mvnw test -Dtest=TwoSumTest#overflowSafety
```

### Coverage report

`./mvnw clean verify` writes an HTML report to:

```
target/site/jacoco/index.html
```

Coverage is **measured and reported, never asserted at a threshold**. A minimum
percentage only encourages tests that raise the number rather than tests that
find defects.

### Code quality

Checkstyle runs in the `validate` phase and **fails the build** on any
violation, so style problems surface before compilation. The ruleset
([`config/checkstyle.xml`](config/checkstyle.xml)) is deliberately small: naming,
imports, and the checks that catch real defects (fall-through in `switch`,
`equals` without `hashCode`, empty catch blocks, assignment inside a condition).
It does not enforce Javadoc on every member, because on a repository about
algorithms that generates noise rather than signal.

---

## Repository structure

```
04-dsa-leetcode/
├── src/main/java/com/bhavesh/dsa/
│   ├── support/            shared ListNode, TreeNode and test builders
│   ├── arrays/  strings/  hashing/  twopointers/  slidingwindow/
│   ├── prefixsum/  binarysearch/  sorting/  matrix/  linkedlist/
│   ├── stacks/  queues/  monotonic/  recursion/  backtracking/
│   ├── bitmanipulation/  greedy/  intervals/  trees/  bst/
│   ├── heaps/  tries/  graphs/  unionfind/  dp/  dpadvanced/
│   ├── math/  stringmatching/  design/  advanced/  graphsadvanced/
│   └── javacore/  fundamentals/
├── src/test/java/com/bhavesh/dsa/   one test class per solution, mirrored
├── templates/              scaffolding for adding a new problem
├── config/checkstyle.xml
├── PROBLEM-INDEX.md        the full catalogue
└── docs/                   personal study notes - not published, see Scope
```

Each topic is its own package and each problem its own class. There is no
`Solutions.java` holding fifty static methods.

## Finding a solution

1. **By problem** — search [PROBLEM-INDEX.md](PROBLEM-INDEX.md) for the ID or
   title; it gives the exact solution and test paths.
2. **By topic** — browse `src/main/java/com/bhavesh/dsa/<topic>/`.
3. **By pattern** — the index's *Key pattern* column groups problems by
   technique (monotonic stack, prefix sums, binary search on the answer, and
   so on), which is usually more useful for revision than by topic.

---

## Topics covered

| | | |
|---|---|---|
| Fundamentals & analysis | Arrays | Strings |
| Hashing | Two pointers | Sliding window |
| Prefix sums & difference arrays | Binary search | Sorting |
| Matrix | Linked lists | Stacks |
| Queues | Monotonic stack & deque | Recursion |
| Backtracking | Bit manipulation | Greedy |
| Intervals | Trees | Binary search trees |
| Heaps & priority queues | Tries | Graphs (BFS, DFS, topological sort) |
| Union-Find | Dynamic programming | Advanced DP |
| Maths & number theory | String matching (KMP) | Design |
| Advanced data structures (Fenwick tree) | Advanced graphs (Dijkstra) | Java-specific exercises |

---

## How the tests are written

Tests are the main reason to trust anything here, so they are written to find
defects rather than to raise a coverage figure.

- **Deterministic.** Randomised tests use a fixed seed, so a failure reproduces.
- **Compared against a reference.** Most optimised solutions are checked against
  a simple, obviously-correct implementation on hundreds of small random inputs.
  Where two strategies exist (bucket sort vs sorting, two-pointer vs prefix
  arrays, fast vs recomputed sliding window) the implementations are asserted to
  agree with each other.
- **Edge cases are explicit**: empty and single-element inputs, duplicates,
  negatives, already-sorted and reverse-sorted input, and `Integer.MIN_VALUE` /
  `MAX_VALUE` wherever arithmetic occurs.
- **Large inputs** are used where they distinguish algorithms: a 100,000-element
  linked list that a recursive solution could not survive, a 300×300 all-land
  grid, a strictly decreasing 100,000-element array for the monotonic stack, and
  an adversarial input for KMP that makes naive matching quadratic.

**Four genuine bugs in this repository were found by its own tests**, all
integer-overflow defects that every normal-sized input passed:

| Where | Defect |
|---|---|
| `TwoSum` | `target - nums[i]` wrapped in `int`, producing a false match for extreme values |
| `LongestConsecutiveSequence` | `value ± 1` wrapped at `Integer.MIN_VALUE`/`MAX_VALUE`, splicing unrelated runs |
| `KokoEatingBananas` | `(pile + speed - 1L)` evaluated `pile + speed` in `int` before the `long` applied |
| `ClimbingStairs` | the documented overflow boundary was off by two |

Each is now covered by a regression test.

---

## Scope: what is published and what is not

The repository publishes **source code, tests, the problem index, templates and
this README**.

It deliberately does **not** publish `docs/`, which holds 71 personal
per-problem study notes (8,182 lines) covering intuition, dry runs,
correctness arguments, interview discussion and revision summaries. Those are
written for my own preparation rather than for a reader of this repository, and
they stay on local disk. `.gitignore` excludes the `docs/` tree by directory
rather than by a blanket `*.md` rule, so legitimate documentation is unaffected,
and CI fails the build if anything under `docs/` is ever committed.

If you have cloned this repository, the `docs/` directory will be absent. That
is intended, not a packaging mistake.

---

## Adding a problem

```bash
cp templates/Solution.java.template     src/main/java/com/bhavesh/dsa/<topic>/NewProblem.java
cp templates/SolutionTest.java.template src/test/java/com/bhavesh/dsa/<topic>/NewProblemTest.java
cp templates/explanation.md.template    docs/solutions/<topic>/NNNN-new-problem.md
# add a row to PROBLEM-INDEX.md, then:
./mvnw clean verify
```

---

## Continuous integration

[`.github/workflows/ci.yml`](.github/workflows/ci.yml) runs on every push and
pull request to `main`:

- **build** — `./mvnw clean verify` on **Java 21 and Java 25**, uploading the
  Surefire and JaCoCo reports as artifacts and writing a test summary.
- **hygiene** — fails if anything under `docs/` is tracked, if build output or
  IDE files are committed, or if a credential-shaped literal appears in tracked
  files.

CI uses the committed wrapper, so it builds with the same Maven version used
locally.

---

## Honest limitations

- **71 problems is a starting collection, not comprehensive coverage.**
  Every topic area has at least one worked problem and the structure scales, but
  several areas have only one or two. Notable gaps include advanced DP beyond
  edit distance, segment trees with lazy propagation, minimum spanning trees,
  Bellman-Ford as a first-class solution, and string algorithms beyond KMP.
- **No performance benchmarks.** Complexity claims come from analysis, and the
  large-input tests only confirm that an algorithm does not degrade
  catastrophically. No timings have been measured, so none are quoted.
- **Tests are correctness checks, not proofs.** Randomised comparison against a
  reference gives good confidence, not certainty.
- **The 26-letter alphabet assumption** is used by several string solutions
  where the problem guarantees lowercase English. Where it matters
  (`ValidAnagram`) a Unicode-safe variant is provided alongside; elsewhere the
  constraint is documented and enforced with an explicit exception rather than
  being silently assumed.
- **Java 25 is tested in CI but not targeted.** The POM compiles to release 21.
- **Some entries are structures, not puzzles.** `MergeSort`, `DisjointSetUnion`,
  `FenwickTree`, `Dijkstra`, `Money` and `AlgorithmAnalysis` are classic
  algorithms or Java exercises rather than LeetCode problems; the index labels
  them so.

## Possible next steps

- Segment tree with lazy propagation; Bellman-Ford and MST as first-class entries.
- More advanced DP: interval DP, bitmask DP, digit DP.
- Rabin-Karp and Z-algorithm alongside KMP.
- JMH benchmarks, so the complexity claims can be backed by measurements rather
  than analysis alone.

## Licence

MIT - see [LICENSE](LICENSE).
