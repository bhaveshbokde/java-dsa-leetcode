package com.bhavesh.dsa.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode 207 and 210 - Course Schedule I and II (Medium). Kahn's algorithm.
 *
 * <p>Prerequisites form a directed graph; a valid ordering exists exactly when
 * that graph is acyclic, and the ordering itself is a topological sort.
 *
 * <p>Kahn's algorithm repeatedly removes a vertex with no remaining incoming
 * edges. The correctness argument runs in both directions:
 *
 * <ul>
 *   <li>If the graph is acyclic, some vertex always has in-degree zero (follow
 *       incoming edges backwards; in a finite acyclic graph that walk must
 *       terminate), so the process never stalls early and emits all n.</li>
 *   <li>If a cycle exists, every vertex on it permanently retains an incoming
 *       edge from another cycle member, so none can ever be emitted. The count
 *       falls short of n - which is precisely the cycle test.</li>
 * </ul>
 *
 * <p>This is preferred over depth-first cycle detection here because it yields
 * the ordering and the acyclicity answer from the same pass, with no recursion
 * and therefore no stack-depth limit.
 *
 * <p>Time O(V + E), auxiliary space O(V + E).
 */
public final class CourseSchedule {

    private CourseSchedule() {
    }

    /** LeetCode 207 - can every course be finished? */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        return findOrder(numCourses, prerequisites).length == numCourses;
    }

    /**
     * LeetCode 210 - a valid ordering, or an empty array if none exists.
     *
     * <p>Each pair {@code [course, prerequisite]} means the prerequisite must
     * come first, so the edge runs prerequisite to course.
     */
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) {
            return new int[0];
        }
        List<List<Integer>> dependents = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            dependents.add(new ArrayList<>());
        }
        int[] inDegree = new int[numCourses];
        if (prerequisites != null) {
            for (int[] pair : prerequisites) {
                int course = pair[0];
                int prerequisite = pair[1];
                dependents.get(prerequisite).add(course);
                inDegree[course]++;
            }
        }

        Deque<Integer> ready = new ArrayDeque<>();
        for (int course = 0; course < numCourses; course++) {
            if (inDegree[course] == 0) {
                ready.add(course);
            }
        }

        int[] order = new int[numCourses];
        int placed = 0;
        while (!ready.isEmpty()) {
            int course = ready.poll();
            order[placed++] = course;
            for (int dependent : dependents.get(course)) {
                if (--inDegree[dependent] == 0) {
                    ready.add(dependent);
                }
            }
        }
        // Falling short of numCourses means a cycle blocked the remainder.
        return placed == numCourses ? order : new int[0];
    }
}
