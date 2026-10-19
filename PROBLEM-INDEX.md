# Problem Index

**85 implemented entries** across **33 topic areas**. Every entry has a
compilable Java solution and a JUnit test class; both paths are given below.

Some entries cover more than one LeetCode problem where the problems share an
algorithm. Entries with no ID are classic algorithms or Java exercises rather
than LeetCode problems, and are labelled as such.

> **On the explanation column.** Each entry also has a detailed written
> explanation kept as personal study notes and **deliberately not published**
> (see the README's Scope section). Filenames are listed so a local copy can be
> navigated, not as repository links.

## Summary

| | Count |
|---|---|
| Total entries | **85** |
| Distinct LeetCode problems | 90 |
| Topic areas | 33 |
| Entries from LeetCode | 81 |
| Classic algorithms / Java exercises | 4 |
| Tagged Easy | 24 |
| Tagged Medium | 58 |
| Tagged Hard | 6 |
| Blind 75 coverage (non-premium) | **55/69** |

Difficulty follows LeetCode where an ID exists; entries covering two problems
list both labels. Non-LeetCode entries are labelled by judgement.

Blind 75 coverage counts only the non-premium problems, since the six premium
ones are not freely accessible. It is a measured figure, not an aspiration.

---

## Fundamentals & analysis

| ID | Problem | Difficulty | Key pattern | Solution | Test |
|---|---|---|---|---|---|
| — | Algorithm analysis: summation and Fibonacci contrasts | Easy | complexity comparison | [`AlgorithmAnalysis.java`](src/main/java/com/bhavesh/dsa/fundamentals/AlgorithmAnalysis.java) | [`AlgorithmAnalysisTest.java`](src/test/java/com/bhavesh/dsa/fundamentals/AlgorithmAnalysisTest.java) |

## Arrays

| ID | Problem | Difficulty | Key pattern | Solution | Test |
|---|---|---|---|---|---|
| 121 | Best Time to Buy and Sell Stock | Easy | prefix minimum | [`BestTimeToBuyAndSellStock.java`](src/main/java/com/bhavesh/dsa/arrays/BestTimeToBuyAndSellStock.java) | [`BestTimeToBuyAndSellStockTest.java`](src/test/java/com/bhavesh/dsa/arrays/BestTimeToBuyAndSellStockTest.java) |
| 53 | Maximum Subarray | Medium | Kadane / linear DP | [`MaximumSubarray.java`](src/main/java/com/bhavesh/dsa/arrays/MaximumSubarray.java) | [`MaximumSubarrayTest.java`](src/test/java/com/bhavesh/dsa/arrays/MaximumSubarrayTest.java) |
| 283 | Move Zeroes | Easy | stable partition | [`MoveZeroes.java`](src/main/java/com/bhavesh/dsa/arrays/MoveZeroes.java) | [`MoveZeroesTest.java`](src/test/java/com/bhavesh/dsa/arrays/MoveZeroesTest.java) |
| 238 | Product of Array Except Self | Medium | prefix/suffix accumulation | [`ProductOfArrayExceptSelf.java`](src/main/java/com/bhavesh/dsa/arrays/ProductOfArrayExceptSelf.java) | [`ProductOfArrayExceptSelfTest.java`](src/test/java/com/bhavesh/dsa/arrays/ProductOfArrayExceptSelfTest.java) |
| 189 | Rotate Array | Medium | triple reversal | [`RotateArray.java`](src/main/java/com/bhavesh/dsa/arrays/RotateArray.java) | [`RotateArrayTest.java`](src/test/java/com/bhavesh/dsa/arrays/RotateArrayTest.java) |

## Strings

| ID | Problem | Difficulty | Key pattern | Solution | Test |
|---|---|---|---|---|---|
| 5, 647 | Longest Palindromic Substring; Palindromic Substrings | Medium | expand around centre | [`LongestPalindromicSubstring.java`](src/main/java/com/bhavesh/dsa/strings/LongestPalindromicSubstring.java) | [`LongestPalindromicSubstringTest.java`](src/test/java/com/bhavesh/dsa/strings/LongestPalindromicSubstringTest.java) |
| 242 | Valid Anagram | Easy | frequency counting | [`ValidAnagram.java`](src/main/java/com/bhavesh/dsa/strings/ValidAnagram.java) | [`ValidAnagramTest.java`](src/test/java/com/bhavesh/dsa/strings/ValidAnagramTest.java) |
| 125 | Valid Palindrome | Easy | converging two pointers | [`ValidPalindrome.java`](src/main/java/com/bhavesh/dsa/strings/ValidPalindrome.java) | [`ValidPalindromeTest.java`](src/test/java/com/bhavesh/dsa/strings/ValidPalindromeTest.java) |

## Hashing

| ID | Problem | Difficulty | Key pattern | Solution | Test |
|---|---|---|---|---|---|
| 217 | Contains Duplicate | Easy | set membership | [`ContainsDuplicate.java`](src/main/java/com/bhavesh/dsa/hashing/ContainsDuplicate.java) | [`ContainsDuplicateTest.java`](src/test/java/com/bhavesh/dsa/hashing/ContainsDuplicateTest.java) |
| 49 | Group Anagrams | Medium | canonical hash key | [`GroupAnagrams.java`](src/main/java/com/bhavesh/dsa/hashing/GroupAnagrams.java) | [`GroupAnagramsTest.java`](src/test/java/com/bhavesh/dsa/hashing/GroupAnagramsTest.java) |
| 128 | Longest Consecutive Sequence | Medium | set + run-start detection | [`LongestConsecutiveSequence.java`](src/main/java/com/bhavesh/dsa/hashing/LongestConsecutiveSequence.java) | [`LongestConsecutiveSequenceTest.java`](src/test/java/com/bhavesh/dsa/hashing/LongestConsecutiveSequenceTest.java) |
| 1 | Two Sum | Easy | complement lookup | [`TwoSum.java`](src/main/java/com/bhavesh/dsa/hashing/TwoSum.java) | [`TwoSumTest.java`](src/test/java/com/bhavesh/dsa/hashing/TwoSumTest.java) |

## Two pointers

| ID | Problem | Difficulty | Key pattern | Solution | Test |
|---|---|---|---|---|---|
| 11 | Container With Most Water | Medium | greedy discard | [`ContainerWithMostWater.java`](src/main/java/com/bhavesh/dsa/twopointers/ContainerWithMostWater.java) | [`ContainerWithMostWaterTest.java`](src/test/java/com/bhavesh/dsa/twopointers/ContainerWithMostWaterTest.java) |
| 15 | 3Sum | Medium | sort + converging scan | [`ThreeSum.java`](src/main/java/com/bhavesh/dsa/twopointers/ThreeSum.java) | [`ThreeSumTest.java`](src/test/java/com/bhavesh/dsa/twopointers/ThreeSumTest.java) |
| 42 | Trapping Rain Water | Hard | running maxima | [`TrappingRainWater.java`](src/main/java/com/bhavesh/dsa/twopointers/TrappingRainWater.java) | [`TrappingRainWaterTest.java`](src/test/java/com/bhavesh/dsa/twopointers/TrappingRainWaterTest.java) |
| 167 | Two Sum II - Input Array Is Sorted | Medium | converging two pointers | [`TwoSumSortedArray.java`](src/main/java/com/bhavesh/dsa/twopointers/TwoSumSortedArray.java) | [`TwoSumSortedArrayTest.java`](src/test/java/com/bhavesh/dsa/twopointers/TwoSumSortedArrayTest.java) |

## Sliding window

| ID | Problem | Difficulty | Key pattern | Solution | Test |
|---|---|---|---|---|---|
| 424 | Longest Repeating Character Replacement | Medium | window with budget | [`LongestRepeatingCharacterReplacement.java`](src/main/java/com/bhavesh/dsa/slidingwindow/LongestRepeatingCharacterReplacement.java) | [`LongestRepeatingCharacterReplacementTest.java`](src/test/java/com/bhavesh/dsa/slidingwindow/LongestRepeatingCharacterReplacementTest.java) |
| 3 | Longest Substring Without Repeating Characters | Medium | variable window | [`LongestSubstringWithoutRepeating.java`](src/main/java/com/bhavesh/dsa/slidingwindow/LongestSubstringWithoutRepeating.java) | [`LongestSubstringWithoutRepeatingTest.java`](src/test/java/com/bhavesh/dsa/slidingwindow/LongestSubstringWithoutRepeatingTest.java) |
| 209 | Minimum Size Subarray Sum | Medium | expand-then-shrink window | [`MinimumSizeSubarraySum.java`](src/main/java/com/bhavesh/dsa/slidingwindow/MinimumSizeSubarraySum.java) | [`MinimumSizeSubarraySumTest.java`](src/test/java/com/bhavesh/dsa/slidingwindow/MinimumSizeSubarraySumTest.java) |

## Prefix sums & difference arrays

| ID | Problem | Difficulty | Key pattern | Solution | Test |
|---|---|---|---|---|---|
| 1109 | Corporate Flight Bookings | Medium | difference array | [`CorporateFlightBookings.java`](src/main/java/com/bhavesh/dsa/prefixsum/CorporateFlightBookings.java) | [`CorporateFlightBookingsTest.java`](src/test/java/com/bhavesh/dsa/prefixsum/CorporateFlightBookingsTest.java) |
| 303 | Range Sum Query - Immutable | Easy | prefix sums | [`RangeSumQueryImmutable.java`](src/main/java/com/bhavesh/dsa/prefixsum/RangeSumQueryImmutable.java) | [`RangeSumQueryImmutableTest.java`](src/test/java/com/bhavesh/dsa/prefixsum/RangeSumQueryImmutableTest.java) |
| 560 | Subarray Sum Equals K | Medium | prefix sums + hashing | [`SubarraySumEqualsK.java`](src/main/java/com/bhavesh/dsa/prefixsum/SubarraySumEqualsK.java) | [`SubarraySumEqualsKTest.java`](src/test/java/com/bhavesh/dsa/prefixsum/SubarraySumEqualsKTest.java) |

## Binary search

| ID | Problem | Difficulty | Key pattern | Solution | Test |
|---|---|---|---|---|---|
| 704 | Binary Search (+ lower/upper bound) | Easy | halving search | [`BinarySearch.java`](src/main/java/com/bhavesh/dsa/binarysearch/BinarySearch.java) | [`BinarySearchTest.java`](src/test/java/com/bhavesh/dsa/binarysearch/BinarySearchTest.java) |
| 34 | Find First and Last Position of Element | Medium | boundary searches | [`FindFirstAndLastPosition.java`](src/main/java/com/bhavesh/dsa/binarysearch/FindFirstAndLastPosition.java) | [`FindFirstAndLastPositionTest.java`](src/test/java/com/bhavesh/dsa/binarysearch/FindFirstAndLastPositionTest.java) |
| 875 | Koko Eating Bananas | Medium | binary search on answer | [`KokoEatingBananas.java`](src/main/java/com/bhavesh/dsa/binarysearch/KokoEatingBananas.java) | [`KokoEatingBananasTest.java`](src/test/java/com/bhavesh/dsa/binarysearch/KokoEatingBananasTest.java) |
| 33 | Search in Rotated Sorted Array | Medium | structural binary search | [`SearchInRotatedSortedArray.java`](src/main/java/com/bhavesh/dsa/binarysearch/SearchInRotatedSortedArray.java) | [`SearchInRotatedSortedArrayTest.java`](src/test/java/com/bhavesh/dsa/binarysearch/SearchInRotatedSortedArrayTest.java) |

## Sorting

| ID | Problem | Difficulty | Key pattern | Solution | Test |
|---|---|---|---|---|---|
| — | Merge sort (used by LeetCode 912) | Medium | divide and conquer | [`MergeSort.java`](src/main/java/com/bhavesh/dsa/sorting/MergeSort.java) | [`MergeSortTest.java`](src/test/java/com/bhavesh/dsa/sorting/MergeSortTest.java) |
| 75 | Sort Colors | Medium | Dutch national flag | [`SortColors.java`](src/main/java/com/bhavesh/dsa/sorting/SortColors.java) | [`SortColorsTest.java`](src/test/java/com/bhavesh/dsa/sorting/SortColorsTest.java) |

## Matrix

| ID | Problem | Difficulty | Key pattern | Solution | Test |
|---|---|---|---|---|---|
| 48 | Rotate Image | Medium | transpose + reverse | [`RotateImage.java`](src/main/java/com/bhavesh/dsa/matrix/RotateImage.java) | [`RotateImageTest.java`](src/test/java/com/bhavesh/dsa/matrix/RotateImageTest.java) |
| 73 | Set Matrix Zeroes | Medium | in-place markers in row/col 0 | [`SetMatrixZeroes.java`](src/main/java/com/bhavesh/dsa/matrix/SetMatrixZeroes.java) | [`SetMatrixZeroesTest.java`](src/test/java/com/bhavesh/dsa/matrix/SetMatrixZeroesTest.java) |
| 54 | Spiral Matrix | Medium | shrinking boundaries | [`SpiralMatrix.java`](src/main/java/com/bhavesh/dsa/matrix/SpiralMatrix.java) | [`SpiralMatrixTest.java`](src/test/java/com/bhavesh/dsa/matrix/SpiralMatrixTest.java) |

## Linked lists

| ID | Problem | Difficulty | Key pattern | Solution | Test |
|---|---|---|---|---|---|
| 141, 142 | Linked List Cycle I and II | Easy, Medium | Floyd's cycle detection | [`LinkedListCycle.java`](src/main/java/com/bhavesh/dsa/linkedlist/LinkedListCycle.java) | [`LinkedListCycleTest.java`](src/test/java/com/bhavesh/dsa/linkedlist/LinkedListCycleTest.java) |
| 21 | Merge Two Sorted Lists | Easy | dummy head merge | [`MergeTwoSortedLists.java`](src/main/java/com/bhavesh/dsa/linkedlist/MergeTwoSortedLists.java) | [`MergeTwoSortedListsTest.java`](src/test/java/com/bhavesh/dsa/linkedlist/MergeTwoSortedListsTest.java) |
| 19 | Remove Nth Node From End of List | Medium | gap two pointers + dummy head | [`RemoveNthFromEnd.java`](src/main/java/com/bhavesh/dsa/linkedlist/RemoveNthFromEnd.java) | [`RemoveNthFromEndTest.java`](src/test/java/com/bhavesh/dsa/linkedlist/RemoveNthFromEndTest.java) |
| 143 | Reorder List | Medium | split, reverse, alternate merge | [`ReorderList.java`](src/main/java/com/bhavesh/dsa/linkedlist/ReorderList.java) | [`ReorderListTest.java`](src/test/java/com/bhavesh/dsa/linkedlist/ReorderListTest.java) |
| 206 | Reverse Linked List | Easy | three-pointer relinking | [`ReverseLinkedList.java`](src/main/java/com/bhavesh/dsa/linkedlist/ReverseLinkedList.java) | [`ReverseLinkedListTest.java`](src/test/java/com/bhavesh/dsa/linkedlist/ReverseLinkedListTest.java) |

## Stacks

| ID | Problem | Difficulty | Key pattern | Solution | Test |
|---|---|---|---|---|---|
| 155 | Min Stack | Medium | augmented stack | [`MinStack.java`](src/main/java/com/bhavesh/dsa/stacks/MinStack.java) | [`MinStackTest.java`](src/test/java/com/bhavesh/dsa/stacks/MinStackTest.java) |
| 20 | Valid Parentheses | Easy | stack for nesting | [`ValidParentheses.java`](src/main/java/com/bhavesh/dsa/stacks/ValidParentheses.java) | [`ValidParenthesesTest.java`](src/test/java/com/bhavesh/dsa/stacks/ValidParenthesesTest.java) |

## Queues

| ID | Problem | Difficulty | Key pattern | Solution | Test |
|---|---|---|---|---|---|
| 232 | Implement Queue using Stacks | Easy | two stacks, amortised | [`QueueUsingStacks.java`](src/main/java/com/bhavesh/dsa/queues/QueueUsingStacks.java) | [`QueueUsingStacksTest.java`](src/test/java/com/bhavesh/dsa/queues/QueueUsingStacksTest.java) |

## Monotonic stack & deque

| ID | Problem | Difficulty | Key pattern | Solution | Test |
|---|---|---|---|---|---|
| 739 | Daily Temperatures | Medium | monotonic stack | [`DailyTemperatures.java`](src/main/java/com/bhavesh/dsa/monotonic/DailyTemperatures.java) | [`DailyTemperaturesTest.java`](src/test/java/com/bhavesh/dsa/monotonic/DailyTemperaturesTest.java) |
| 84 | Largest Rectangle in Histogram | Hard | nearest smaller on both sides | [`LargestRectangleInHistogram.java`](src/main/java/com/bhavesh/dsa/monotonic/LargestRectangleInHistogram.java) | [`LargestRectangleInHistogramTest.java`](src/test/java/com/bhavesh/dsa/monotonic/LargestRectangleInHistogramTest.java) |
| 239 | Sliding Window Maximum | Hard | monotonic deque | [`SlidingWindowMaximum.java`](src/main/java/com/bhavesh/dsa/monotonic/SlidingWindowMaximum.java) | [`SlidingWindowMaximumTest.java`](src/test/java/com/bhavesh/dsa/monotonic/SlidingWindowMaximumTest.java) |

## Recursion

| ID | Problem | Difficulty | Key pattern | Solution | Test |
|---|---|---|---|---|---|
| 50 | Pow(x, n) | Medium | binary exponentiation | [`PowerFunction.java`](src/main/java/com/bhavesh/dsa/recursion/PowerFunction.java) | [`PowerFunctionTest.java`](src/test/java/com/bhavesh/dsa/recursion/PowerFunctionTest.java) |

## Backtracking

| ID | Problem | Difficulty | Key pattern | Solution | Test |
|---|---|---|---|---|---|
| 51 | N-Queens | Hard | pruned backtracking | [`NQueens.java`](src/main/java/com/bhavesh/dsa/backtracking/NQueens.java) | [`NQueensTest.java`](src/test/java/com/bhavesh/dsa/backtracking/NQueensTest.java) |
| 46 | Permutations | Medium | swap-based backtracking | [`Permutations.java`](src/main/java/com/bhavesh/dsa/backtracking/Permutations.java) | [`PermutationsTest.java`](src/test/java/com/bhavesh/dsa/backtracking/PermutationsTest.java) |
| 78 | Subsets | Medium | include/exclude tree | [`Subsets.java`](src/main/java/com/bhavesh/dsa/backtracking/Subsets.java) | [`SubsetsTest.java`](src/test/java/com/bhavesh/dsa/backtracking/SubsetsTest.java) |

## Bit manipulation

| ID | Problem | Difficulty | Key pattern | Solution | Test |
|---|---|---|---|---|---|
| 338 | Counting Bits | Easy | DP over bit structure | [`CountingBits.java`](src/main/java/com/bhavesh/dsa/bitmanipulation/CountingBits.java) | [`CountingBitsTest.java`](src/test/java/com/bhavesh/dsa/bitmanipulation/CountingBitsTest.java) |
| 268 | Missing Number | Easy | XOR pairing / Gauss sum | [`MissingNumber.java`](src/main/java/com/bhavesh/dsa/bitmanipulation/MissingNumber.java) | [`MissingNumberTest.java`](src/test/java/com/bhavesh/dsa/bitmanipulation/MissingNumberTest.java) |
| 191, 190 | Number of 1 Bits; Reverse Bits | Easy | bit scanning / Kernighan | [`NumberOfOneBits.java`](src/main/java/com/bhavesh/dsa/bitmanipulation/NumberOfOneBits.java) | [`NumberOfOneBitsTest.java`](src/test/java/com/bhavesh/dsa/bitmanipulation/NumberOfOneBitsTest.java) |
| 136, 260 | Single Number (+ two-unique variant) | Easy, Medium | XOR cancellation | [`SingleNumber.java`](src/main/java/com/bhavesh/dsa/bitmanipulation/SingleNumber.java) | [`SingleNumberTest.java`](src/test/java/com/bhavesh/dsa/bitmanipulation/SingleNumberTest.java) |
| 371 | Sum of Two Integers | Medium | XOR sum + carry shift | [`SumOfTwoIntegers.java`](src/main/java/com/bhavesh/dsa/bitmanipulation/SumOfTwoIntegers.java) | [`SumOfTwoIntegersTest.java`](src/test/java/com/bhavesh/dsa/bitmanipulation/SumOfTwoIntegersTest.java) |

## Greedy

| ID | Problem | Difficulty | Key pattern | Solution | Test |
|---|---|---|---|---|---|
| 55, 45 | Jump Game I and II | Medium | furthest-reach greedy | [`JumpGame.java`](src/main/java/com/bhavesh/dsa/greedy/JumpGame.java) | [`JumpGameTest.java`](src/test/java/com/bhavesh/dsa/greedy/JumpGameTest.java) |

## Intervals

| ID | Problem | Difficulty | Key pattern | Solution | Test |
|---|---|---|---|---|---|
| 56 | Merge Intervals | Medium | sort + sweep | [`MergeIntervals.java`](src/main/java/com/bhavesh/dsa/intervals/MergeIntervals.java) | [`MergeIntervalsTest.java`](src/test/java/com/bhavesh/dsa/intervals/MergeIntervalsTest.java) |

## Trees

| ID | Problem | Difficulty | Key pattern | Solution | Test |
|---|---|---|---|---|---|
| 124 | Binary Tree Maximum Path Sum | Hard | post-order, two quantities | [`BinaryTreeMaximumPathSum.java`](src/main/java/com/bhavesh/dsa/trees/BinaryTreeMaximumPathSum.java) | [`BinaryTreeMaximumPathSumTest.java`](src/test/java/com/bhavesh/dsa/trees/BinaryTreeMaximumPathSumTest.java) |
| 104, 102 | Maximum Depth; Level Order (+ DFS orders) | Easy, Medium | DFS stack / BFS queue | [`BinaryTreeTraversals.java`](src/main/java/com/bhavesh/dsa/trees/BinaryTreeTraversals.java) | [`BinaryTreeTraversalsTest.java`](src/test/java/com/bhavesh/dsa/trees/BinaryTreeTraversalsTest.java) |
| 105 | Construct Binary Tree from Preorder and Inorder | Medium | preorder root + inorder split | [`ConstructBinaryTree.java`](src/main/java/com/bhavesh/dsa/trees/ConstructBinaryTree.java) | [`ConstructBinaryTreeTest.java`](src/test/java/com/bhavesh/dsa/trees/ConstructBinaryTreeTest.java) |
| 226 | Invert Binary Tree | Easy | mirror recursion | [`InvertBinaryTree.java`](src/main/java/com/bhavesh/dsa/trees/InvertBinaryTree.java) | [`InvertBinaryTreeTest.java`](src/test/java/com/bhavesh/dsa/trees/InvertBinaryTreeTest.java) |
| 236, 235 | Lowest Common Ancestor (tree and BST) | Medium | post-order aggregation | [`LowestCommonAncestor.java`](src/main/java/com/bhavesh/dsa/trees/LowestCommonAncestor.java) | [`LowestCommonAncestorTest.java`](src/test/java/com/bhavesh/dsa/trees/LowestCommonAncestorTest.java) |
| 100 | Same Tree | Easy | structural comparison | [`SameTree.java`](src/main/java/com/bhavesh/dsa/trees/SameTree.java) | [`SameTreeTest.java`](src/test/java/com/bhavesh/dsa/trees/SameTreeTest.java) |
| 572 | Subtree of Another Tree | Easy | subtree matching | [`SubtreeOfAnotherTree.java`](src/main/java/com/bhavesh/dsa/trees/SubtreeOfAnotherTree.java) | [`SubtreeOfAnotherTreeTest.java`](src/test/java/com/bhavesh/dsa/trees/SubtreeOfAnotherTreeTest.java) |

## Binary search trees

| ID | Problem | Difficulty | Key pattern | Solution | Test |
|---|---|---|---|---|---|
| 230 | Kth Smallest Element in a BST | Medium | in-order with early exit | [`KthSmallestInBst.java`](src/main/java/com/bhavesh/dsa/bst/KthSmallestInBst.java) | [`KthSmallestInBstTest.java`](src/test/java/com/bhavesh/dsa/bst/KthSmallestInBstTest.java) |
| 98 | Validate Binary Search Tree | Medium | bound propagation | [`ValidateBinarySearchTree.java`](src/main/java/com/bhavesh/dsa/bst/ValidateBinarySearchTree.java) | [`ValidateBinarySearchTreeTest.java`](src/test/java/com/bhavesh/dsa/bst/ValidateBinarySearchTreeTest.java) |

## Heaps & priority queues

| ID | Problem | Difficulty | Key pattern | Solution | Test |
|---|---|---|---|---|---|
| 215 | Kth Largest Element in an Array | Medium | min-heap / quickselect | [`KthLargestElement.java`](src/main/java/com/bhavesh/dsa/heaps/KthLargestElement.java) | [`KthLargestElementTest.java`](src/test/java/com/bhavesh/dsa/heaps/KthLargestElementTest.java) |
| 347 | Top K Frequent Elements | Medium | bucket sort by frequency | [`TopKFrequentElements.java`](src/main/java/com/bhavesh/dsa/heaps/TopKFrequentElements.java) | [`TopKFrequentElementsTest.java`](src/test/java/com/bhavesh/dsa/heaps/TopKFrequentElementsTest.java) |

## Tries

| ID | Problem | Difficulty | Key pattern | Solution | Test |
|---|---|---|---|---|---|
| 208 | Implement Trie (Prefix Tree) | Medium | prefix tree | [`Trie.java`](src/main/java/com/bhavesh/dsa/tries/Trie.java) | [`TrieTest.java`](src/test/java/com/bhavesh/dsa/tries/TrieTest.java) |

## Graphs

| ID | Problem | Difficulty | Key pattern | Solution | Test |
|---|---|---|---|---|---|
| 207, 210 | Course Schedule I and II | Medium | Kahn topological sort | [`CourseSchedule.java`](src/main/java/com/bhavesh/dsa/graphs/CourseSchedule.java) | [`CourseScheduleTest.java`](src/test/java/com/bhavesh/dsa/graphs/CourseScheduleTest.java) |
| 200 | Number of Islands | Medium | flood fill (BFS) | [`NumberOfIslands.java`](src/main/java/com/bhavesh/dsa/graphs/NumberOfIslands.java) | [`NumberOfIslandsTest.java`](src/test/java/com/bhavesh/dsa/graphs/NumberOfIslandsTest.java) |

## Union-Find

| ID | Problem | Difficulty | Key pattern | Solution | Test |
|---|---|---|---|---|---|
| — | Disjoint Set Union (Union-Find) | Medium | union by size + path compression | [`DisjointSetUnion.java`](src/main/java/com/bhavesh/dsa/unionfind/DisjointSetUnion.java) | [`DisjointSetUnionTest.java`](src/test/java/com/bhavesh/dsa/unionfind/DisjointSetUnionTest.java) |
| 547 | Number of Provinces | Medium | connected components | [`NumberOfProvinces.java`](src/main/java/com/bhavesh/dsa/unionfind/NumberOfProvinces.java) | [`NumberOfProvincesTest.java`](src/test/java/com/bhavesh/dsa/unionfind/NumberOfProvincesTest.java) |

## Dynamic programming

| ID | Problem | Difficulty | Key pattern | Solution | Test |
|---|---|---|---|---|---|
| 70 | Climbing Stairs | Easy | linear recurrence | [`ClimbingStairs.java`](src/main/java/com/bhavesh/dsa/dp/ClimbingStairs.java) | [`ClimbingStairsTest.java`](src/test/java/com/bhavesh/dsa/dp/ClimbingStairsTest.java) |
| 322 | Coin Change | Medium | unbounded knapsack | [`CoinChange.java`](src/main/java/com/bhavesh/dsa/dp/CoinChange.java) | [`CoinChangeTest.java`](src/test/java/com/bhavesh/dsa/dp/CoinChangeTest.java) |
| 198, 213 | House Robber I and II | Medium | take-or-skip DP | [`HouseRobber.java`](src/main/java/com/bhavesh/dsa/dp/HouseRobber.java) | [`HouseRobberTest.java`](src/test/java/com/bhavesh/dsa/dp/HouseRobberTest.java) |
| 1143 | Longest Common Subsequence | Medium | two-sequence grid DP | [`LongestCommonSubsequence.java`](src/main/java/com/bhavesh/dsa/dp/LongestCommonSubsequence.java) | [`LongestCommonSubsequenceTest.java`](src/test/java/com/bhavesh/dsa/dp/LongestCommonSubsequenceTest.java) |
| 300 | Longest Increasing Subsequence | Medium | patience sorting | [`LongestIncreasingSubsequence.java`](src/main/java/com/bhavesh/dsa/dp/LongestIncreasingSubsequence.java) | [`LongestIncreasingSubsequenceTest.java`](src/test/java/com/bhavesh/dsa/dp/LongestIncreasingSubsequenceTest.java) |
| 152 | Maximum Product Subarray | Medium | track running max and min | [`MaximumProductSubarray.java`](src/main/java/com/bhavesh/dsa/dp/MaximumProductSubarray.java) | [`MaximumProductSubarrayTest.java`](src/test/java/com/bhavesh/dsa/dp/MaximumProductSubarrayTest.java) |
| 62 | Unique Paths | Medium | grid DP + combinatorics | [`UniquePaths.java`](src/main/java/com/bhavesh/dsa/dp/UniquePaths.java) | [`UniquePathsTest.java`](src/test/java/com/bhavesh/dsa/dp/UniquePathsTest.java) |
| 139 | Word Break | Medium | prefix DP + dictionary set | [`WordBreak.java`](src/main/java/com/bhavesh/dsa/dp/WordBreak.java) | [`WordBreakTest.java`](src/test/java/com/bhavesh/dsa/dp/WordBreakTest.java) |

## Advanced DP

| ID | Problem | Difficulty | Key pattern | Solution | Test |
|---|---|---|---|---|---|
| 72 | Edit Distance | Hard | grid DP, three predecessors | [`EditDistance.java`](src/main/java/com/bhavesh/dsa/dpadvanced/EditDistance.java) | [`EditDistanceTest.java`](src/test/java/com/bhavesh/dsa/dpadvanced/EditDistanceTest.java) |

## Maths & number theory

| ID | Problem | Difficulty | Key pattern | Solution | Test |
|---|---|---|---|---|---|
| 204 | Count Primes | Medium | Sieve of Eratosthenes | [`CountPrimes.java`](src/main/java/com/bhavesh/dsa/math/CountPrimes.java) | [`CountPrimesTest.java`](src/test/java/com/bhavesh/dsa/math/CountPrimesTest.java) |

## String matching

| ID | Problem | Difficulty | Key pattern | Solution | Test |
|---|---|---|---|---|---|
| 28 | Find the Index of the First Occurrence | Easy | KMP prefix function | [`KmpSearch.java`](src/main/java/com/bhavesh/dsa/stringmatching/KmpSearch.java) | [`KmpSearchTest.java`](src/test/java/com/bhavesh/dsa/stringmatching/KmpSearchTest.java) |

## Design

| ID | Problem | Difficulty | Key pattern | Solution | Test |
|---|---|---|---|---|---|
| 146 | LRU Cache | Medium | hash map + doubly linked list | [`LruCache.java`](src/main/java/com/bhavesh/dsa/design/LruCache.java) | [`LruCacheTest.java`](src/test/java/com/bhavesh/dsa/design/LruCacheTest.java) |

## Advanced data structures

| ID | Problem | Difficulty | Key pattern | Solution | Test |
|---|---|---|---|---|---|
| 307 | Range Sum Query - Mutable (Fenwick tree) | Medium | binary indexed tree | [`FenwickTree.java`](src/main/java/com/bhavesh/dsa/advanced/FenwickTree.java) | [`FenwickTreeTest.java`](src/test/java/com/bhavesh/dsa/advanced/FenwickTreeTest.java) |

## Advanced graphs

| ID | Problem | Difficulty | Key pattern | Solution | Test |
|---|---|---|---|---|---|
| 743 | Network Delay Time (Dijkstra) | Medium | greedy frontier + heap | [`Dijkstra.java`](src/main/java/com/bhavesh/dsa/graphsadvanced/Dijkstra.java) | [`DijkstraTest.java`](src/test/java/com/bhavesh/dsa/graphsadvanced/DijkstraTest.java) |

## Java-specific exercises

| ID | Problem | Difficulty | Key pattern | Solution | Test |
|---|---|---|---|---|---|
| — | Money: immutable value type | Medium | equals/hashCode contract | [`Money.java`](src/main/java/com/bhavesh/dsa/javacore/Money.java) | [`MoneyTest.java`](src/test/java/com/bhavesh/dsa/javacore/MoneyTest.java) |

---

## How to add a new problem

Templates live in [`templates/`](templates/):

1. Copy `Solution.java.template` into the right topic package and implement it.
2. Copy `SolutionTest.java.template` into the mirrored test package.
3. Copy `explanation.md.template` into `docs/solutions/<topic>/` (stays local).
4. Add an entry to the tracker and regenerate this index.
5. Run `./mvnw clean verify`.

