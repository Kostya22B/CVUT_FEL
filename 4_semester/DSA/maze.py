import sys

def validate_maze(maze):
    if any(len(row) != len(maze[0]) for row in maze):
        return "Error: Bludiste neni obdelnikove!"

    if not (100 >= len(maze[0]) >= 5):
        return "Error: Sirka bludiste je mimo rozsah!"
    if not (50 >= len(maze) >= 5):
        return "Error: Delka bludiste je mimo rozsah!"

    if maze[0][1] != '.':
        return "Error: Vstup neni vlevo nahore!"

    if maze[-1][-2] != '.':
        return "Error: Vystup neni vpravo dole!"

    if any(set(row) - {'#', '.'} for row in maze):
        return "Error: Bludiste obsahuje nezname znaky!"

    if not all(maze[i][0] == '#' and maze[i][-1] == '#' for i in range(len(maze))) or \
            not all(char == '#' for char in maze[0][2:]) or \
            not all(char == '#' for char in maze[-1][:-2]):
        return "Error: Bludiste neni oplocene!"
    return

def find_path_dfs(maze, start, end, path=None, visited=None):
    if visited is None:
        visited = set()
    if path is None:
        path = []
    if start == end:
        return path + [end]
    x, y = start
    for dx, dy in [(1, 0), (0, 1), (-1, 0), (0, -1)]:
        next_step = (x + dx, y + dy)
        if ((0 <= next_step[0] < len(maze)) and
                (0 <= next_step[1] < len(maze[0])) and
                (maze[next_step[0]][next_step[1]] == '.') and
                (next_step not in visited)):
            visited.add(next_step)
            if result := find_path_dfs(maze, next_step, end, path + [start], visited):
                return result
    return None

def is_path_essential(maze, start, end, path):
    essential = [False] * len(path)
    for i, point in enumerate(path):
        maze[point[0]][point[1]] = '#'
        if not find_path_dfs(maze, start, end, [], set()):
            essential[i] = True
        maze[point[0]][point[1]] = '.'
    return essential

def main():
    maze_input = []
    while True:
        try:
            line = input()
            if not line:
                break
            maze_input.append(line)
        except EOFError:
            break
    maze = [list(row) for row in maze_input]

    error_message = validate_maze(maze)
    if error_message:
        print(error_message, file=sys.stderr)
        sys.exit(1)

    maze[(0, 1)[0]][(0, 1)[1]] = '!'

    path = find_path_dfs(maze, (0, 1), (len(maze) - 1, len(maze[0]) - 2), [], set())
    if path:
        essential = is_path_essential(maze, (0, 1), (len(maze) - 1, len(maze[0]) - 2), path)
        for i, (x, y) in enumerate(path):
            if essential[i]:
                maze[x][y] = '!'
        maze[(0, 1)[0]][(0, 1)[1]] = '!'
        for row in maze:
            print(''.join(row))
    else:
        print("Error: Cesta neexistuje!", file=sys.stderr)
        sys.exit(1)

def print_error_and_exit(error_message):
    sys.stderr.write(error_message + '\n')
    sys.exit(1)

if __name__ == "__main__":
    main()