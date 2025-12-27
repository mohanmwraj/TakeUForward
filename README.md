# Take U Forward — DSA Preparation

This repository is for your Data Structures & Algorithms (DSA) preparation following the Take U Forward curriculum. Use this as a personal study tracker and practice repository.

## What this repo is for
- Organize solved problems, notes, and implementations.
- Track progress across core DSA topics.
- Keep references to problem sets and learning resources.

## Suggested Study Plan (12 weeks)
- Week 1: Arrays, Strings — basics, two-pointers, sliding window
- Week 2: Sorting & Searching, Binary Search
- Week 3: Linked Lists
- Week 4: Stacks & Queues, Monotonic stacks
- Week 5: Trees (BST, traversal, recursion)
- Week 6: Heaps & Priority Queues
- Week 7: Hashing, Maps, Sets
- Week 8: Graphs (BFS, DFS, shortest paths basics)
- Week 9: Dynamic Programming — patterns and memoization
- Week 10: Greedy algorithms & Interval problems
- Week 11: Bit manipulation & Math problems
- Week 12: Mock interviews, mixed problem sets, review

## Topics Checklist
- Arrays & Strings
- Linked Lists
- Stacks & Queues
- Trees & Binary Search Trees
- Graphs
- Heaps & Priority Queues
- Hashing
- Sorting & Searching
- Dynamic Programming
- Greedy Algorithms
- Bits & Math
- Complexity Analysis (Big O)

## How to use this repo
1. Create a folder per topic (e.g., `arrays/`, `trees/`).
2. Add solved problems as files named by platform and problem id (example: `leetcode_2_add_two_numbers.py`).
3. Add short notes or a `notes.md` per topic for patterns and tips.
4. Use `README.md` to track weekly goals and progress.

## Recommended Resources
- Take U Forward (YouTube / playlist)
- LeetCode — practice & contests
- GeeksforGeeks — topic explanations
- Codeforces — competitive practice
- Books: "Elements of Programming Interviews", "Introduction to Algorithms" (CLRS)

## Contribution
- Keep commits focused: one problem or one note per commit.
- Add tests where helpful and a small example showing usage.

## Quick Commands
```bash
# Run a single script (example)
python3 arrays/leetcode_1_two_sum.py
```

## Running Java via VS Code Task
This workspace includes a VS Code task at `.vscode/tasks.json` that compiles and runs a Java file while redirecting standard input from `input.txt` and standard output to `output.txt`.

- To use the task in VS Code: open the Command Palette → `Run Task...` → select `Compile and run Java`.
- The task copies the current file to `TempJavaProgram.java`, compiles and runs it, feeding input from `input.txt` and saving output to `output.txt`.

If you prefer running manually from the terminal, use (example):
```bash
# Compile
javac MyProgram.java
# Run using input.txt and save output
java MyProgram < input.txt > output.txt
```

Note about `package` declarations
- If your Java file uses a `package` declaration, don't remove it; instead compile and run from the workspace root using the source path and the fully-qualified class name. Example:

```bash
# From workspace root
javac src/com/example/MyProgram.java
java -cp "${workspaceFolder}" com.example.MyProgram < input.txt > output.txt
```

The VS Code task above assumes files are in the default (unnamed) package. If you use packages, adjust the compile/run command to reference the correct path and fully-qualified class name.

Updated task behavior
- The workspace task `Compile and run Java (with input/output)` now detects a `package` declaration in the current file and runs the fully-qualified class name automatically. This lets you run `Basics/Basics.java` (package `Basics`) without editing the task.

---
If you'd like, I can: (a) create topic folders and add starter templates, (b) add a weekly checklist file, or (c) generate a prioritized problem list from Take U Forward — tell me which next.
