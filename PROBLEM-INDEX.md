# Problem Index

**71 implemented entries** across **33 topic areas**. Every entry has a
compilable Java solution and a JUnit test class; both paths are given below.

Some entries cover more than one LeetCode problem where the problems share an
algorithm (for example Linked List Cycle I and II, or Course Schedule I and II).
Entries with no ID are classic algorithms or Java exercises rather than LeetCode
problems, and are labelled as such.

> **Note on the explanation column.** Each entry also has a detailed written
> explanation (intuition, dry run, correctness argument, complexity analysis,
> edge cases). Those are personal study notes and are **deliberately not
> published** - see the Scope section of the README. The filenames are listed
> so the local copy can be navigated, not as repository links.

## Summary

| | Count |
|---|---|
| Total entries | **71** |
| Topic areas | 33 |
| From LeetCode | 67 |
| Classic algorithms / Java exercises | 4 |
| Tagged Easy | 19 |
| Tagged Medium | 49 |
| Tagged Hard | 6 |

Difficulty labels follow LeetCode where an ID exists; entries covering two
problems list both labels. Non-LeetCode entries are labelled by judgement.

---

## Fundamentals & analysis

| ID | Problem | Difficulty | Key pattern | Solution | Test | Explanation (local only) |
|---|---|---|---|---|---|---|
| — | Algorithm analysis: summation and Fibonacci contrasts | Easy | complexity comparison | [`AlgorithmAnalysis.java`](src/main/java/com/bhavesh/dsa/fundamentals/AlgorithmAnalysis.java) | [`AlgorithmAnalysisTest.java`](src/test/java/com/bhavesh/dsa/fundamentals/AlgorithmAnalysisTest.java) | `docs/solutions/fundamentals/algorithm-analysis.md` |

## Arrays

| ID | Problem | Difficulty | Key pattern | Solution | Test | Explanation (local only) |
|---|---|---|---|---|---|---|
| 121 | Best Time to Buy and Sell Stock | Easy | prefix minimum | [`BestTimeToBuyAndSellStock.java`](src/main/java/com/bhavesh/dsa/arrays/BestTimeToBuyAndSellStock.java) | [`BestTimeToBuyAndSellStockTest.java`](src/test/java/com/bhavesh/dsa/arrays/BestTimeToBuyAndSellStockTest.java) | `docs/solutions/arrays/0121-best-time-to-buy-and-sell-stock.md` |
| 53 | Maximum Subarray | Medium | Kadane / linear DP | [`MaximumSubarray.java`](src/main/java/com/bhavesh/dsa/arrays/MaximumSubarray.java) | [`MaximumSubarrayTest.java`](src/test/java/com/bhavesh/dsa/arrays/MaximumSubarrayTest.java) | `docs/solutions/arrays/0053-maximum-subarray.md` |
| 238 | Product of Array Except Self | Medium | prefix/suffix accumulation | [`ProductOfArrayExceptSelf.java`](src/main/java/com/bhavesh/dsa/arrays/ProductOfArrayExceptSelf.java) | [`ProductOfArrayExceptSelfTest.java`](src/test/java/com/bhavesh/dsa/arrays/ProductOfArrayExceptSelfTest.java) | `docs/solutions/arrays/0238-product-of-array-except-self.md` |
| 189 | Rotate Array | Medium | triple reversal | [`RotateArray.java`](src/main/java/com/bhavesh/dsa/arrays/RotateArray.java) | [`RotateArrayTest.java`](src/test/java/com/bhavesh/dsa/arrays/RotateArrayTest.java) | `docs/solutions/arrays/0189-rotate-array.md` |
| 283 | Move Zeroes | Easy | stable partition | [`MoveZeroes.java`](src/main/java/com/bhavesh/dsa/arrays/MoveZeroes.java) | [`MoveZeroesTest.java`](src/test/java/com/bhavesh/dsa/arrays/MoveZeroesTest.java) | `docs/solutions/arrays/0283-move-zeroes.md` |

## Strings

| ID | Problem | Difficulty | Key pattern | Solution | Test | Explanation (local only) |
|---|---|---|---|---|---|---|
| 125 | Valid Palindrome | Easy | converging two pointers | [`ValidPalindrome.java`](src/main/java/com/bhavesh/dsa/strings/ValidPalindrome.java) | [`ValidPalindromeTest.java`](src/test/java/com/bhavesh/dsa/strings/ValidPalindromeTest.java) | `docs/solutions/strings/0125-valid-palindrome.md` |
| 242 | Valid Anagram | Easy | frequency counting | [`ValidAnagram.java`](src/main/java/com/bhavesh/dsa/strings/ValidAnagram.java) | [`ValidAnagramTest.java`](src/test/java/com/bhavesh/dsa/strings/ValidAnagramTest.java) | `docs/solutions/strings/0242-valid-anagram.md` |

## Hashing

| ID | Problem | Difficulty | Key pattern | Solution | Test | Explanation (local only) |
|---|---|---|---|---|---|---|
| 1 | Two Sum | Easy | complement lookup | [`TwoSum.java`](src/main/java/com/bhavesh/dsa/hashing/TwoSum.java) | [`TwoSumTest.java`](src/test/java/com/bhavesh/dsa/hashing/TwoSumTest.java) | `docs/solutions/hashing/0001-two-sum.md` |
| 49 | Group Anagrams | Medium | canonical hash key | [`GroupAnagrams.java`](src/main/java/com/bhavesh/dsa/hashing/GroupAnagrams.java) | [`GroupAnagramsTest.java`](src/test/java/com/bhavesh/dsa/hashing/GroupAnagramsTest.java) | `docs/solutions/hashing/0049-group-anagrams.md` |
| 128 | Longest Consecutive Sequence | Medium | set + run-start detection | [`LongestConsecutiveSequence.java`](src/main/java/com/bhavesh/dsa/hashing/LongestConsecutiveSequence.java) | [`LongestConsecutiveSequenceTest.java`](src/test/java/com/bhavesh/dsa/hashing/LongestConsecutiveSequenceTest.java) | `docs/solutions/hashing/0128-longest-consecutive-sequence.md` |

## Two pointers

| ID | Problem | Difficulty | Key pattern | Solution | Test | Explanation (local only) |
|---|---|---|---|---|---|---|
| 167 | Two Sum II - Input Array Is Sorted | Medium | converging two pointers | [`TwoSumSortedArray.java`](src/main/java/com/bhavesh/dsa/twopointers/TwoSumSortedArray.java) | [`TwoSumSortedArrayTest.java`](src/test/java/com/bhavesh/dsa/twopointers/TwoSumSortedArrayTest.java) | `docs/solutions/twopointers/0167-two-sum-ii.md` |
| 15 | 3Sum | Medium | sort + converging scan | [`ThreeSum.java`](src/main/java/com/bhavesh/dsa/twopointers/ThreeSum.java) | [`ThreeSumTest.java`](src/test/java/com/bhavesh/dsa/twopointers/ThreeSumTest.java) | `docs/solutions/twopointers/0015-three-sum.md` |
| 11 | Container With Most Water | Medium | greedy discard | [`ContainerWithMostWater.java`](src/main/java/com/bhavesh/dsa/twopointers/ContainerWithMostWater.java) | [`ContainerWithMostWaterTest.java`](src/test/java/com/bhavesh/dsa/twopointers/ContainerWithMostWaterTest.java) | `docs/solutions/twopointers/0011-container-with-most-water.md` |
| 42 | Trapping Rain Water | Hard | running maxima | [`TrappingRainWater.java`](src/main/java/com/bhavesh/dsa/twopointers/TrappingRainWater.java) | [`TrappingRainWaterTest.java`](src/test/java/com/bhavesh/dsa/twopointers/TrappingRainWaterTest.java) | `docs/solutions/twopointers/0042-trapping-rain-water.md` |

## Sliding window

| ID | Problem | Difficulty | Key pattern | Solution | Test | Explanation (local only) |
|---|---|---|---|---|---|---|
| 3 | Longest Substring Without Repeating Characters | Medium | variable window | [`LongestSubstringWithoutRepeating.java`](src/main/java/com/bhavesh/dsa/slidingwindow/LongestSubstringWithoutRepeating.java) | [`LongestSubstringWithoutRepeatingTest.java`](src/test/java/com/bhavesh/dsa/slidingwindow/LongestSubstringWithoutRepeatingTest.java) | `docs/solutions/slidingwindow/0003-longest-substring-without-repeating.md` |
| 209 | Minimum Size Subarray Sum | Medium | expand-then-shrink window | [`MinimumSizeSubarraySum.java`](src/main/java/com/bhavesh/dsa/slidingwindow/MinimumSizeSubarraySum.java) | [`MinimumSizeSubarraySumTest.java`](src/test/java/com/bhavesh/dsa/slidingwindow/MinimumSizeSubarraySumTest.java) | `docs/solutions/slidingwindow/0209-minimum-size-subarray-sum.md` |
| 424 | Longest Repeating Character Replacement | Medium | window with budget | [`LongestRepeatingCharacterReplacement.java`](src/main/java/com/bhavesh/dsa/slidingwindow/LongestRepeatingCharacterReplacement.java) | [`LongestRepeatingCharacterReplacementTest.java`](src/test/java/com/bhavesh/dsa/slidingwindow/LongestRepeatingCharacterReplacementTest.java) | `docs/solutions/slidingwindow/0424-longest-repeating-character-replacement.md` |

## Prefix sums & difference arrays

| ID | Problem | Difficulty | Key pattern | Solution | Test | Explanation (local only) |
|---|---|---|---|---|---|---|
| 303 | Range Sum Query - Immutable | Easy | prefix sums | [`RangeSumQueryImmutable.java`](src/main/java/com/bhavesh/dsa/prefixsum/RangeSumQueryImmutable.java) | [`RangeSumQueryImmutableTest.java`](src/test/java/com/bhavesh/dsa/prefixsum/RangeSumQueryImmutableTest.java) | `docs/solutions/prefixsum/0303-range-sum-query-immutable.md` |
| 560 | Subarray Sum Equals K | Medium | prefix sums + hashing | [`SubarraySumEqualsK.java`](src/main/java/com/bhavesh/dsa/prefixsum/SubarraySumEqualsK.java) | [`SubarraySumEqualsKTest.java`](src/test/java/com/bhavesh/dsa/prefixsum/SubarraySumEqualsKTest.java) | `docs/solutions/prefixsum/0560-subarray-sum-equals-k.md` |
| 1109 | Corporate Flight Bookings | Medium | difference array | [`CorporateFlightBookings.java`](src/main/java/com/bhavesh/dsa/prefixsum/CorporateFlightBookings.java) | [`CorporateFlightBookingsTest.java`](src/test/java/com/bhavesh/dsa/prefixsum/CorporateFlightBookingsTest.java) | `docs/solutions/prefixsum/1109-corporate-flight-bookings.md` |

## Binary search

| ID | Problem | Difficulty | Key pattern | Solution | Test | Explanation (local only) |
|---|---|---|---|---|---|---|
| 704 | Binary Search (+ lower/upper bound) | Easy | halving search | [`BinarySearch.java`](src/main/java/com/bhavesh/dsa/binarysearch/BinarySearch.java) | [`BinarySearchTest.java`](src/test/java/com/bhavesh/dsa/binarysearch/BinarySearchTest.java) | `docs/solutions/binarysearch/0704-binary-search.md` |
| 33 | Search in Rotated Sorted Array | Medium | structural binary search | [`SearchInRotatedSortedArray.java`](src/main/java/com/bhavesh/dsa/binarysearch/SearchInRotatedSortedArray.java) | [`SearchInRotatedSortedArrayTest.java`](src/test/java/com/bhavesh/dsa/binarysearch/SearchInRotatedSortedArrayTest.java) | `docs/solutions/binarysearch/0033-search-in-rotated-sorted-array.md` |
| 34 | Find First and Last Position of Element | Medium | boundary searches | [`FindFirstAndLastPosition.java`](src/main/java/com/bhavesh/dsa/binarysearch/FindFirstAndLastPosition.java) | [`FindFirstAndLastPositionTest.java`](src/test/java/com/bhavesh/dsa/binarysearch/FindFirstAndLastPositionTest.java) | `docs/solutions/binarysearch/0034-find-first-and-last-position.md` |
| 875 | Koko Eating Bananas | Medium | binary search on answer | [`KokoEatingBananas.java`](src/main/java/com/bhavesh/dsa/binarysearch/KokoEatingBananas.java) | [`KokoEatingBananasTest.java`](src/test/java/com/bhavesh/dsa/binarysearch/KokoEatingBananasTest.java) | `docs/solutions/binarysearch/0875-koko-eating-bananas.md` |

## Sorting

| ID | Problem | Difficulty | Key pattern | Solution | Test | Explanation (local only) |
|---|---|---|---|---|---|---|
| — | Merge sort (used by LeetCode 912) | Medium | divide and conquer | [`MergeSort.java`](src/main/java/com/bhavesh/dsa/sorting/MergeSort.java) | [`MergeSortTest.java`](src/test/java/com/bhavesh/dsa/sorting/MergeSortTest.java) | `docs/solutions/sorting/merge-sort.md` |
| 75 | Sort Colors | Medium | Dutch national flag | [`SortColors.java`](src/main/java/com/bhavesh/dsa/sorting/SortColors.java) | [`SortColorsTest.java`](src/test/java/com/bhavesh/dsa/sorting/SortColorsTest.java) | `docs/solutions/sorting/0075-sort-colors.md` |

## Matrix

| ID | Problem | Difficulty | Key pattern | Solution | Test | Explanation (local only) |
|---|---|---|---|---|---|---|
| 48 | Rotate Image | Medium | transpose + reverse | [`RotateImage.java`](src/main/java/com/bhavesh/dsa/matrix/RotateImage.java) | [`RotateImageTest.java`](src/test/java/com/bhavesh/dsa/matrix/RotateImageTest.java) | `docs/solutions/matrix/0048-rotate-image.md` |
| 54 | Spiral Matrix | Medium | shrinking boundaries | [`SpiralMatrix.java`](src/main/java/com/bhavesh/dsa/matrix/SpiralMatrix.java) | [`SpiralMatrixTest.java`](src/test/java/com/bhavesh/dsa/matrix/SpiralMatrixTest.java) | `docs/solutions/matrix/0054-spiral-matrix.md` |

## Linked lists

| ID | Problem | Difficulty | Key pattern | Solution | Test | Explanation (local only) |
|---|---|---|---|---|---|---|
| 206 | Reverse Linked List | Easy | three-pointer relinking | [`ReverseLinkedList.java`](src/main/java/com/bhavesh/dsa/linkedlist/ReverseLinkedList.java) | [`ReverseLinkedListTest.java`](src/test/java/com/bhavesh/dsa/linkedlist/ReverseLinkedListTest.java) | `docs/solutions/linkedlist/0206-reverse-linked-list.md` |
| 21 | Merge Two Sorted Lists | Easy | dummy head merge | [`MergeTwoSortedLists.java`](src/main/java/com/bhavesh/dsa/linkedlist/MergeTwoSortedLists.java) | [`MergeTwoSortedListsTest.java`](src/test/java/com/bhavesh/dsa/linkedlist/MergeTwoSortedListsTest.java) | `docs/solutions/linkedlist/0021-merge-two-sorted-lists.md` |
| 141, 142 | Linked List Cycle I and II | Easy, Medium | Floyd's cycle detection | [`LinkedListCycle.java`](src/main/java/com/bhavesh/dsa/linkedlist/LinkedListCycle.java) | [`LinkedListCycleTest.java`](src/test/java/com/bhavesh/dsa/linkedlist/LinkedListCycleTest.java) | `docs/solutions/linkedlist/0141-linked-list-cycle.md` |

## Stacks

| ID | Problem | Difficulty | Key pattern | Solution | Test | Explanation (local only) |
|---|---|---|---|---|---|---|
| 20 | Valid Parentheses | Easy | stack for nesting | [`ValidParentheses.java`](src/main/java/com/bhavesh/dsa/stacks/ValidParentheses.java) | [`ValidParenthesesTest.java`](src/test/java/com/bhavesh/dsa/stacks/ValidParenthesesTest.java) | `docs/solutions/stacks/0020-valid-parentheses.md` |
| 155 | Min Stack | Medium | augmented stack | [`MinStack.java`](src/main/java/com/bhavesh/dsa/stacks/MinStack.java) | [`MinStackTest.java`](src/test/java/com/bhavesh/dsa/stacks/MinStackTest.java) | `docs/solutions/stacks/0155-min-stack.md` |

## Queues

| ID | Problem | Difficulty | Key pattern | Solution | Test | Explanation (local only) |
|---|---|---|---|---|---|---|
| 232 | Implement Queue using Stacks | Easy | two stacks, amortised | [`QueueUsingStacks.java`](src/main/java/com/bhavesh/dsa/queues/QueueUsingStacks.java) | [`QueueUsingStacksTest.java`](src/test/java/com/bhavesh/dsa/queues/QueueUsingStacksTest.java) | `docs/solutions/queues/0232-queue-using-stacks.md` |

## Monotonic stack & deque

| ID | Problem | Difficulty | Key pattern | Solution | Test | Explanation (local only) |
|---|---|---|---|---|---|---|
| 739 | Daily Temperatures | Medium | monotonic stack | [`DailyTemperatures.java`](src/main/java/com/bhavesh/dsa/monotonic/DailyTemperatures.java) | [`DailyTemperaturesTest.java`](src/test/java/com/bhavesh/dsa/monotonic/DailyTemperaturesTest.java) | `docs/solutions/monotonic/0739-daily-temperatures.md` |
| 239 | Sliding Window Maximum | Hard | monotonic deque | [`SlidingWindowMaximum.java`](src/main/java/com/bhavesh/dsa/monotonic/SlidingWindowMaximum.java) | [`SlidingWindowMaximumTest.java`](src/test/java/com/bhavesh/dsa/monotonic/SlidingWindowMaximumTest.java) | `docs/solutions/monotonic/0239-sliding-window-maximum.md` |
| 84 | Largest Rectangle in Histogram | Hard | nearest smaller on both sides | [`LargestRectangleInHistogram.java`](src/main/java/com/bhavesh/dsa/monotonic/LargestRectangleInHistogram.java) | [`LargestRectangleInHistogramTest.java`](src/test/java/com/bhavesh/dsa/monotonic/LargestRectangleInHistogramTest.java) | `docs/solutions/monotonic/0084-largest-rectangle-in-histogram.md` |

## Recursion

| ID | Problem | Difficulty | Key pattern | Solution | Test | Explanation (local only) |
|---|---|---|---|---|---|---|
| 50 | Pow(x, n) | Medium | binary exponentiation | [`PowerFunction.java`](src/main/java/com/bhavesh/dsa/recursion/PowerFunction.java) | [`PowerFunctionTest.java`](src/test/java/com/bhavesh/dsa/recursion/PowerFunctionTest.java) | `docs/solutions/recursion/0050-pow-x-n.md` |

## Backtracking

| ID | Problem | Difficulty | Key pattern | Solution | Test | Explanation (local only) |
|---|---|---|---|---|---|---|
| 78 | Subsets | Medium | include/exclude tree | [`Subsets.java`](src/main/java/com/bhavesh/dsa/backtracking/Subsets.java) | [`SubsetsTest.java`](src/test/java/com/bhavesh/dsa/backtracking/SubsetsTest.java) | `docs/solutions/backtracking/0078-subsets.md` |
| 46 | Permutations | Medium | swap-based backtracking | [`Permutations.java`](src/main/java/com/bhavesh/dsa/backtracking/Permutations.java) | [`PermutationsTest.java`](src/test/java/com/bhavesh/dsa/backtracking/PermutationsTest.java) | `docs/solutions/backtracking/0046-permutations.md` |
| 51 | N-Queens | Hard | pruned backtracking | [`NQueens.java`](src/main/java/com/bhavesh/dsa/backtracking/NQueens.java) | [`NQueensTest.java`](src/test/java/com/bhavesh/dsa/backtracking/NQueensTest.java) | `docs/solutions/backtracking/0051-n-queens.md` |

## Bit manipulation

| ID | Problem | Difficulty | Key pattern | Solution | Test | Explanation (local only) |
|---|---|---|---|---|---|---|
| 136, 260 | Single Number (+ two-unique variant) | Easy, Medium | XOR cancellation | [`SingleNumber.java`](src/main/java/com/bhavesh/dsa/bitmanipulation/SingleNumber.java) | [`SingleNumberTest.java`](src/test/java/com/bhavesh/dsa/bitmanipulation/SingleNumberTest.java) | `docs/solutions/bitmanipulation/0136-single-number.md` |
| 338 | Counting Bits | Easy | DP over bit structure | [`CountingBits.java`](src/main/java/com/bhavesh/dsa/bitmanipulation/CountingBits.java) | [`CountingBitsTest.java`](src/test/java/com/bhavesh/dsa/bitmanipulation/CountingBitsTest.java) | `docs/solutions/bitmanipulation/0338-counting-bits.md` |
| 268 | Missing Number | Easy | XOR pairing / Gauss sum | [`MissingNumber.java`](src/main/java/com/bhavesh/dsa/bitmanipulation/MissingNumber.java) | [`MissingNumberTest.java`](src/test/java/com/bhavesh/dsa/bitmanipulation/MissingNumberTest.java) | `docs/solutions/bitmanipulation/0268-missing-number.md` |

## Greedy

| ID | Problem | Difficulty | Key pattern | Solution | Test | Explanation (local only) |
|---|---|---|---|---|---|---|
| 55, 45 | Jump Game I and II | Medium | furthest-reach greedy | [`JumpGame.java`](src/main/java/com/bhavesh/dsa/greedy/JumpGame.java) | [`JumpGameTest.java`](src/test/java/com/bhavesh/dsa/greedy/JumpGameTest.java) | `docs/solutions/greedy/0055-jump-game.md` |

## Intervals

| ID | Problem | Difficulty | Key pattern | Solution | Test | Explanation (local only) |
|---|---|---|---|---|---|---|
| 56 | Merge Intervals | Medium | sort + sweep | [`MergeIntervals.java`](src/main/java/com/bhavesh/dsa/intervals/MergeIntervals.java) | [`MergeIntervalsTest.java`](src/test/java/com/bhavesh/dsa/intervals/MergeIntervalsTest.java) | `docs/solutions/intervals/0056-merge-intervals.md` |

## Trees

| ID | Problem | Difficulty | Key pattern | Solution | Test | Explanation (local only) |
|---|---|---|---|---|---|---|
| 104, 102 | Maximum Depth; Level Order (+ DFS orders) | Easy, Medium | DFS stack / BFS queue | [`BinaryTreeTraversals.java`](src/main/java/com/bhavesh/dsa/trees/BinaryTreeTraversals.java) | [`BinaryTreeTraversalsTest.java`](src/test/java/com/bhavesh/dsa/trees/BinaryTreeTraversalsTest.java) | `docs/solutions/trees/binary-tree-traversals.md` |
| 236, 235 | Lowest Common Ancestor (tree and BST) | Medium | post-order aggregation | [`LowestCommonAncestor.java`](src/main/java/com/bhavesh/dsa/trees/LowestCommonAncestor.java) | [`LowestCommonAncestorTest.java`](src/test/java/com/bhavesh/dsa/trees/LowestCommonAncestorTest.java) | `docs/solutions/trees/0236-lowest-common-ancestor.md` |
| 124 | Binary Tree Maximum Path Sum | Hard | post-order, two quantities | [`BinaryTreeMaximumPathSum.java`](src/main/java/com/bhavesh/dsa/trees/BinaryTreeMaximumPathSum.java) | [`BinaryTreeMaximumPathSumTest.java`](src/test/java/com/bhavesh/dsa/trees/BinaryTreeMaximumPathSumTest.java) | `docs/solutions/trees/0124-binary-tree-maximum-path-sum.md` |

## Binary search trees

| ID | Problem | Difficulty | Key pattern | Solution | Test | Explanation (local only) |
|---|---|---|---|---|---|---|
| 98 | Validate Binary Search Tree | Medium | bound propagation | [`ValidateBinarySearchTree.java`](src/main/java/com/bhavesh/dsa/bst/ValidateBinarySearchTree.java) | [`ValidateBinarySearchTreeTest.java`](src/test/java/com/bhavesh/dsa/bst/ValidateBinarySearchTreeTest.java) | `docs/solutions/bst/0098-validate-binary-search-tree.md` |

## Heaps & priority queues

| ID | Problem | Difficulty | Key pattern | Solution | Test | Explanation (local only) |
|---|---|---|---|---|---|---|
| 215 | Kth Largest Element in an Array | Medium | min-heap / quickselect | [`KthLargestElement.java`](src/main/java/com/bhavesh/dsa/heaps/KthLargestElement.java) | [`KthLargestElementTest.java`](src/test/java/com/bhavesh/dsa/heaps/KthLargestElementTest.java) | `docs/solutions/heaps/0215-kth-largest-element.md` |
| 347 | Top K Frequent Elements | Medium | bucket sort by frequency | [`TopKFrequentElements.java`](src/main/java/com/bhavesh/dsa/heaps/TopKFrequentElements.java) | [`TopKFrequentElementsTest.java`](src/test/java/com/bhavesh/dsa/heaps/TopKFrequentElementsTest.java) | `docs/solutions/heaps/0347-top-k-frequent-elements.md` |

## Tries

| ID | Problem | Difficulty | Key pattern | Solution | Test | Explanation (local only) |
|---|---|---|---|---|---|---|
| 208 | Implement Trie (Prefix Tree) | Medium | prefix tree | [`Trie.java`](src/main/java/com/bhavesh/dsa/tries/Trie.java) | [`TrieTest.java`](src/test/java/com/bhavesh/dsa/tries/TrieTest.java) | `docs/solutions/tries/0208-implement-trie.md` |

## Graphs

| ID | Problem | Difficulty | Key pattern | Solution | Test | Explanation (local only) |
|---|---|---|---|---|---|---|
| 200 | Number of Islands | Medium | flood fill (BFS) | [`NumberOfIslands.java`](src/main/java/com/bhavesh/dsa/graphs/NumberOfIslands.java) | [`NumberOfIslandsTest.java`](src/test/java/com/bhavesh/dsa/graphs/NumberOfIslandsTest.java) | `docs/solutions/graphs/0200-number-of-islands.md` |
| 207, 210 | Course Schedule I and II | Medium | Kahn topological sort | [`CourseSchedule.java`](src/main/java/com/bhavesh/dsa/graphs/CourseSchedule.java) | [`CourseScheduleTest.java`](src/test/java/com/bhavesh/dsa/graphs/CourseScheduleTest.java) | `docs/solutions/graphs/0207-course-schedule.md` |

## Union-Find

| ID | Problem | Difficulty | Key pattern | Solution | Test | Explanation (local only) |
|---|---|---|---|---|---|---|
| — | Disjoint Set Union (Union-Find) | Medium | union by size + path compression | [`DisjointSetUnion.java`](src/main/java/com/bhavesh/dsa/unionfind/DisjointSetUnion.java) | [`DisjointSetUnionTest.java`](src/test/java/com/bhavesh/dsa/unionfind/DisjointSetUnionTest.java) | `docs/solutions/unionfind/disjoint-set-union.md` |
| 547 | Number of Provinces | Medium | connected components | [`NumberOfProvinces.java`](src/main/java/com/bhavesh/dsa/unionfind/NumberOfProvinces.java) | [`NumberOfProvincesTest.java`](src/test/java/com/bhavesh/dsa/unionfind/NumberOfProvincesTest.java) | `docs/solutions/unionfind/0547-number-of-provinces.md` |

## Dynamic programming

| ID | Problem | Difficulty | Key pattern | Solution | Test | Explanation (local only) |
|---|---|---|---|---|---|---|
| 70 | Climbing Stairs | Easy | linear recurrence | [`ClimbingStairs.java`](src/main/java/com/bhavesh/dsa/dp/ClimbingStairs.java) | [`ClimbingStairsTest.java`](src/test/java/com/bhavesh/dsa/dp/ClimbingStairsTest.java) | `docs/solutions/dp/0070-climbing-stairs.md` |
| 198, 213 | House Robber I and II | Medium | take-or-skip DP | [`HouseRobber.java`](src/main/java/com/bhavesh/dsa/dp/HouseRobber.java) | [`HouseRobberTest.java`](src/test/java/com/bhavesh/dsa/dp/HouseRobberTest.java) | `docs/solutions/dp/0198-house-robber.md` |
| 322 | Coin Change | Medium | unbounded knapsack | [`CoinChange.java`](src/main/java/com/bhavesh/dsa/dp/CoinChange.java) | [`CoinChangeTest.java`](src/test/java/com/bhavesh/dsa/dp/CoinChangeTest.java) | `docs/solutions/dp/0322-coin-change.md` |
| 300 | Longest Increasing Subsequence | Medium | patience sorting | [`LongestIncreasingSubsequence.java`](src/main/java/com/bhavesh/dsa/dp/LongestIncreasingSubsequence.java) | [`LongestIncreasingSubsequenceTest.java`](src/test/java/com/bhavesh/dsa/dp/LongestIncreasingSubsequenceTest.java) | `docs/solutions/dp/0300-longest-increasing-subsequence.md` |
| 1143 | Longest Common Subsequence | Medium | two-sequence grid DP | [`LongestCommonSubsequence.java`](src/main/java/com/bhavesh/dsa/dp/LongestCommonSubsequence.java) | [`LongestCommonSubsequenceTest.java`](src/test/java/com/bhavesh/dsa/dp/LongestCommonSubsequenceTest.java) | `docs/solutions/dp/1143-longest-common-subsequence.md` |
| 62 | Unique Paths | Medium | grid DP + combinatorics | [`UniquePaths.java`](src/main/java/com/bhavesh/dsa/dp/UniquePaths.java) | [`UniquePathsTest.java`](src/test/java/com/bhavesh/dsa/dp/UniquePathsTest.java) | `docs/solutions/dp/0062-unique-paths.md` |

## Advanced DP

| ID | Problem | Difficulty | Key pattern | Solution | Test | Explanation (local only) |
|---|---|---|---|---|---|---|
| 72 | Edit Distance | Hard | grid DP, three predecessors | [`EditDistance.java`](src/main/java/com/bhavesh/dsa/dpadvanced/EditDistance.java) | [`EditDistanceTest.java`](src/test/java/com/bhavesh/dsa/dpadvanced/EditDistanceTest.java) | `docs/solutions/dpadvanced/0072-edit-distance.md` |

## Maths & number theory

| ID | Problem | Difficulty | Key pattern | Solution | Test | Explanation (local only) |
|---|---|---|---|---|---|---|
| 204 | Count Primes | Medium | Sieve of Eratosthenes | [`CountPrimes.java`](src/main/java/com/bhavesh/dsa/math/CountPrimes.java) | [`CountPrimesTest.java`](src/test/java/com/bhavesh/dsa/math/CountPrimesTest.java) | `docs/solutions/math/0204-count-primes.md` |

## String matching

| ID | Problem | Difficulty | Key pattern | Solution | Test | Explanation (local only) |
|---|---|---|---|---|---|---|
| 28 | Find the Index of the First Occurrence | Easy | KMP prefix function | [`KmpSearch.java`](src/main/java/com/bhavesh/dsa/stringmatching/KmpSearch.java) | [`KmpSearchTest.java`](src/test/java/com/bhavesh/dsa/stringmatching/KmpSearchTest.java) | `docs/solutions/stringmatching/0028-kmp-search.md` |

## Design

| ID | Problem | Difficulty | Key pattern | Solution | Test | Explanation (local only) |
|---|---|---|---|---|---|---|
| 146 | LRU Cache | Medium | hash map + doubly linked list | [`LruCache.java`](src/main/java/com/bhavesh/dsa/design/LruCache.java) | [`LruCacheTest.java`](src/test/java/com/bhavesh/dsa/design/LruCacheTest.java) | `docs/solutions/design/0146-lru-cache.md` |

## Advanced data structures

| ID | Problem | Difficulty | Key pattern | Solution | Test | Explanation (local only) |
|---|---|---|---|---|---|---|
| 307 | Range Sum Query - Mutable (Fenwick tree) | Medium | binary indexed tree | [`FenwickTree.java`](src/main/java/com/bhavesh/dsa/advanced/FenwickTree.java) | [`FenwickTreeTest.java`](src/test/java/com/bhavesh/dsa/advanced/FenwickTreeTest.java) | `docs/solutions/advanced/fenwick-tree.md` |

## Advanced graphs

| ID | Problem | Difficulty | Key pattern | Solution | Test | Explanation (local only) |
|---|---|---|---|---|---|---|
| 743 | Network Delay Time (Dijkstra) | Medium | greedy frontier + heap | [`Dijkstra.java`](src/main/java/com/bhavesh/dsa/graphsadvanced/Dijkstra.java) | [`DijkstraTest.java`](src/test/java/com/bhavesh/dsa/graphsadvanced/DijkstraTest.java) | `docs/solutions/graphsadvanced/dijkstra.md` |

## Java-specific exercises

| ID | Problem | Difficulty | Key pattern | Solution | Test | Explanation (local only) |
|---|---|---|---|---|---|---|
| — | Money: immutable value type | Medium | equals/hashCode contract | [`Money.java`](src/main/java/com/bhavesh/dsa/javacore/Money.java) | [`MoneyTest.java`](src/test/java/com/bhavesh/dsa/javacore/MoneyTest.java) | `docs/solutions/javacore/money-value-type.md` |

---

## How to add a new problem

Templates live in [`templates/`](templates/):

1. Copy `Solution.java.template` into the right topic package and implement it.
2. Copy `SolutionTest.java.template` into the mirrored test package.
3. Copy `explanation.md.template` into `docs/solutions/<topic>/` (stays local).
4. Add a row to the table above.
5. Run `./mvnw clean verify`.

